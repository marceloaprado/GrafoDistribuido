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
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.thrift.TException;

/**
 *
 * @author MarceloPrado, Rhaniel Cristhian
 */
public class Handler implements GrafoHandler.Iface{
    HashMap<Integer, Vertice> V = new HashMap<>();
    HashMap<Identificador, Aresta> A = new HashMap<>();
    
    Grafo grafo = new Grafo(V, A);
    
    @Override
    public boolean addAresta(Aresta a) throws TException {
        if(a.getV1() != a.getV2()){
            IdentificadorAresta ida = new IdentificadorAresta(a.getV1().nome, a.getV2().nome, true,  A.size());
            IdentificadorAresta ida1 = new IdentificadorAresta(a.getV2().nome, a.getV1().nome, true, A.size());
            IdentificadorAresta ida2 = new IdentificadorAresta(a.getV1().nome, a.getV2().nome, false, A.size());
            
            AtomicBoolean emUso = new AtomicBoolean(false); 
            boolean ok = false;        
            
            if(emUso.compareAndSet(false,true)){ //regiao critica                
                if(!a.isDirecionada()){ //se não for direcionada, verificar se existe alguma direcionada e não direcionada que entra em conflito
                    if(!A.containsKey(ida) && !A.containsKey(ida1) && !A.containsKey(ida2)){
                        A.put(ida2, a);
                        ok = true;
                    }
                }
                else{//se for direcionada, procurar alguma direcionada ou não direcionada que entre em conflito                    
                    if(!A.containsKey(ida) && !A.containsKey(ida2)){
                        A.put(ida, a);
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
            ida.isDirecionada = false;
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
        AtomicBoolean emUso = new AtomicBoolean(false); 
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
        AtomicBoolean emUso = new AtomicBoolean(false); 
        boolean ok = false;
        if(emUso.compareAndSet(false,true)){ 
            //regiao critica
            if(!V.containsKey(v.nome)){
                V.put(v.nome, v);
                ok = true;
            }
            emUso.set(false); 
            return ok;
        }
        return false;
    }

    @Override
    public boolean atualizaVertice(Vertice v) throws TException {        
        AtomicBoolean emUso = new AtomicBoolean(false); 
        boolean ok = false;
        if(emUso.compareAndSet(false,true)){ 
            //regiao critica            
            Vertice vt = V.get(v.getNome());
            
            if(vt != null){
                V.replace(vt.nome, v);
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
    public boolean excluiVertice(int id) throws TException {
        AtomicBoolean emUso = new AtomicBoolean(false); 
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
            }
            else
                ok = false;
            emUso.set(false); 
            return ok;
        }
        return false;
    }

    @Override
    public List<Vertice> listarVertices() throws TException {
        return (List<Vertice>)V.values();        
    }

    @Override
    public List<Aresta> listarArestas() throws TException {
        return (List<Aresta>)A.values();
    }
}
