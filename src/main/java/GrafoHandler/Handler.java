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
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.thrift.TException;

/**
 *
 * @author MarceloPrado
 */
public class Handler implements GrafoHandler.Iface{
    HashMap<Integer, Vertice> V = new HashMap<>();
    HashMap<Identificador, Aresta> A = new HashMap<>();
    
    Grafo grafo = new Grafo(V, A);
    
    @Override
    public boolean addAresta(Aresta a) throws TException {
        if(a.getV1() != a.getV2()){
            IdentificadorAresta ida = new IdentificadorAresta(a.getV1().nome, a.getV2().nome);
            IdentificadorAresta ida1 = new IdentificadorAresta(a.getV2().nome, a.getV1().nome);
            AtomicBoolean emUso = new AtomicBoolean(false); 
            boolean ok = false;        
            if(emUso.compareAndSet(false,true)){ 
                //regiao critica
                //bomba de insulina
                if(!a.isDirecionada){//decidir o que fazer em relação ao grafo poder conter arestas direcionadas             
                    if(!A.containsKey(ida) && !A.containsKey(ida1)){
                        A.put(ida, a);
                        ok = true;
                    }
                }
                else{
                    Aresta ar = A.get(ida);
                    
                    if(!A.containsKey(ida)){
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
        IdentificadorAresta ida = new IdentificadorAresta(v1, v2);
        Aresta a = A.get(ida);
        if(a != null)
            return a;
        
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
        IdentificadorAresta ida = new IdentificadorAresta(a.getV1().nome, a.getV2().nome);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Aresta> listarArestas() throws TException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
