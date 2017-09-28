/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafoHandler;

import GrafoJava.IdentificadorAresta;
import GrafoThrift.Grafo;
import GrafoThrift.Identificador;
import GrafoThrift.Vertice;
import GrafoThrift.GrafoHandler;
import GrafoThrift.Aresta;
import GrafoThrift.NotFoundEx;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.thrift.TException;

/**
 *
 * @author MarceloPrado, Rhaniel Cristhian
 */
public class Handler implements GrafoHandler.Iface{
    ConcurrentHashMap<Integer, Vertice> V = new ConcurrentHashMap<>();
    ConcurrentHashMap<Identificador, Aresta> A = new ConcurrentHashMap<>();
    
    Grafo grafo = new Grafo(V, A);
    AtomicBoolean emUso = new AtomicBoolean(false); 
    
    @Override
    public boolean addAresta(Aresta a) throws TException {
        if(a.getV1().getNome() != a.getV2().getNome() && V.containsKey(a.getV1().getNome()) && V.containsKey(a.getV2().getNome())){
            IdentificadorAresta ida = new IdentificadorAresta(a.getV1().nome, a.getV2().nome, true,  A.size());
            IdentificadorAresta ida1 = new IdentificadorAresta(a.getV2().nome, a.getV1().nome, true, A.size());
            IdentificadorAresta ida2 = new IdentificadorAresta(a.getV1().nome, a.getV2().nome, false, A.size());            
            
            boolean ok = false;        
            
            if(emUso.compareAndSet(false,true)){ //regiao critica                
                if(!a.isDirecionada()){ //se não for direcionada, verificar se existe alguma direcionada e não direcionada que entra em conflito
                    if(!A.containsKey(ida) && !A.containsKey(ida1) && !A.containsKey(ida2)){
                        A.putIfAbsent(ida2, a);
                        ok = true;
                    }
                }
                else{//se for direcionada, procurar alguma direcionada ou não direcionada que entre em conflito                    
                    if(!A.containsKey(ida) && !A.containsKey(ida2)){
                        A.putIfAbsent(ida, a);
                        ok = true;
                    }
                }
                emUso.set(false); 
                return ok;
            }
        }
        return false;
    }    
    
    @Override
    public Aresta buscaAresta(int v1, int v2) throws NotFoundEx, TException {
        IdentificadorAresta ida = new IdentificadorAresta(v1, v2, true, A.size());
        Aresta a = A.get(ida);
        if(a != null)
            return a;
        else{
            ida.setDirecionada(false);
            a = A.get(ida);
            if(a != null)
                return a;
        }
        
        throw new NotFoundEx();
    }

    @Override
    public Vertice buscaVertice(int nome) throws NotFoundEx, TException {
        Vertice v = V.get(nome);
        if(v != null)
            return v;
        
        throw new NotFoundEx();
    }

    @Override
    public boolean atualizaAresta(Aresta a) throws TException {
        IdentificadorAresta ida = new IdentificadorAresta(a.getV1().nome, a.getV2().nome, a.isDirecionada(), A.size());        
        boolean ok = false;
        if(emUso.compareAndSet(false,true)){ 
            //regiao critica            
            Aresta ar = A.get(ida);
            
            if(ar != null){
                A.replace(ida, a);
                ok = true;
            }
            else
                ok = false;
            emUso.set(false); 
            return ok;
        }
        return false;
    }

    @Override
    public boolean addVertice(Vertice v) throws TException {   
        if(v.getNome() >= 0 && v.getPeso() >= 0){
            V.putIfAbsent(v.nome, v);
            return true;
        }
        return false;
    }

    @Override
    public boolean atualizaVertice(Vertice v) throws TException {                             
        Vertice vt = V.get(v.getNome());

        if(vt != null){
            synchronized(vt){
                V.replace(vt.nome, vt, v);
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public boolean excluiAresta(int v1, int v2, int direcionada) throws TException {
        Aresta a = buscaAresta(v1, v2);
        synchronized(a){
            //A.re
            return true;
        }        
    }
    
    @Override
    public boolean excluiVertice(int id) throws TException {        
        boolean ok = false;
        if(emUso.compareAndSet(false,true)){ 
            //regiao critica            
            Vertice vt = V.get(id);
            
            if(vt != null){
                //Cria um arraylist de keys a serem removidas posteriormente
                ArrayList<Identificador> arRemovidas = new ArrayList<>();
                
                //Percorre a hash de arestas e remove aquela que tiver um dos vértices com o nome igual ao parametro "id"
                Set<Map.Entry<Identificador, Aresta>> arestas = A.entrySet();                
                for(Iterator i = arestas.iterator(); i.hasNext();) {
                    Map.Entry<Identificador, Aresta> ar;
                    ar = (Map.Entry<Identificador, Aresta>) i.next();
                    Aresta valor = ar.getValue();
                    
                    //Adiciona no arraylist as chaves a serem removidas
                    if(valor.getV1().getNome() == id || valor.getV2().getNome() == id)
                        arRemovidas.add(ar.getKey());
                }
                
                //Remove as chaves
                for(Identificador i:arRemovidas)
                    A.remove(i); 
                V.remove(vt.nome);
                ok = true;
            }
            emUso.set(false); 
            return ok;
        }
        return false;
    }

    @Override
    public List<Vertice> listarVertices() throws NotFoundEx, TException {       
        if(!V.isEmpty())           
            return new ArrayList(V.values());      

        throw new NotFoundEx();
    }

    @Override
    public List<Aresta> listarArestas() throws NotFoundEx, TException {
        if(!A.isEmpty())
            return new ArrayList(A.values());
        
        throw new NotFoundEx();
    }

    @Override
    public List<Aresta> arestasDoVertice(int nome) throws NotFoundEx, TException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Vertice> vizinhos(int nome) throws NotFoundEx, TException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
}
