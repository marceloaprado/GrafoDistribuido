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
        IdentificadorAresta ida = new IdentificadorAresta(a.getV1().nome, a.getV2().nome);
        AtomicBoolean emUso = new AtomicBoolean(false); 
        boolean ok = false;
        if(emUso.compareAndSet(false,true)){ 
            //regiao critica
            //bomba de insulina
            if(!A.containsKey(ida)){
                A.put(ida, a);
                ok = true;
            }
            emUso.set(false); 
            return ok;
        }
        //criar exceção para concorrencia
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Aresta buscaAresta(int id) throws TException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean atualizaAresta(Aresta a) throws TException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addVertice(Vertice v) throws TException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vertice buscaVertice(String nome) throws TException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean atualizaVertice(Vertice v) throws TException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluiVertice(int id) throws TException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
