/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafoHandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.thrift.TException;

import GrafoThrift.Aresta;
import GrafoThrift.Grafo;
import GrafoThrift.GrafoHandler;
import GrafoThrift.Identificador;
import GrafoThrift.NotFoundEx;
import GrafoThrift.Vertice;

/**
 *
 * @author MarceloPrado, Rhaniel Cristhian
 */
public class Handler implements GrafoHandler.Iface {

    ConcurrentHashMap<Integer, Vertice> V = new ConcurrentHashMap<>();
    ConcurrentHashMap<Identificador, Aresta> A = new ConcurrentHashMap<>();

    Grafo grafo = new Grafo(V, A);

    @SuppressWarnings("unused")
    @Override
    public boolean addAresta(Aresta a) throws TException {
        if (a.getV1().getNome() != a.getV2().getNome() && V.containsKey(a.getV1().getNome())
                && V.containsKey(a.getV2().getNome())) {
            synchronized (a) {
                try {
                    Aresta ar = buscaAresta(a.getV1().getNome(), a.getV2().getNome());
                    return false;
                } catch (NotFoundEx ex) {
                    Identificador ida = new Identificador(a.getV1().nome, a.getV2().nome, a.direcionada);
                    A.putIfAbsent(ida, a);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public Aresta buscaAresta(int v1, int v2) throws NotFoundEx, TException {
        Identificador ida = new Identificador(v1, v2, true);
        Identificador ida1 = new Identificador(v1, v2, false);
        Identificador ida2 = new Identificador(v2, v1, false);

        synchronized (ida) {
            Aresta a = A.get(ida);
            if (a != null) {
                return a;
            } else {
                a = A.get(ida1);
                if (a != null) {
                    return a;
                } else {
                    a = A.get(ida2);
                    if (a != null) {
                        return a;
                    }
                }
            }
            throw new NotFoundEx();
        }
    }

    @Override
    public Vertice buscaVertice(int nome) throws NotFoundEx, TException {
        Object o = new Object();
        Vertice v = V.get(nome);
        synchronized (o) {
            if (v != null) {
                return v;
            }

            throw new NotFoundEx();
        }
    }

    @Override
    public boolean atualizaAresta(Aresta a) throws TException {
        Identificador ida = new Identificador(a.getV1().nome, a.getV2().nome, a.direcionada);
        synchronized (a) {
            try {
                Aresta ar = buscaAresta(a.getV1().nome, a.getV2().nome);

                A.replace(ida, ar, a);
                return true;
            } catch (NotFoundEx ex) {
                return false;
            }
        }
    }

    @Override
    public boolean addVertice(Vertice v) throws TException {
        if (v.getNome() >= 0 && v.getPeso() >= 0) {
            if (V.putIfAbsent(v.nome, v) == null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean atualizaVertice(Vertice v) throws TException {
        Object o = new Object();
        Vertice vt = V.get(v.getNome());

        synchronized (o) {
            if (vt != null) {
                V.replace(vt.nome, vt, v);
                return true;
            }
            return false;
        }
    }

    @Override
    public boolean excluiAresta(int v1, int v2) throws TException {
        Object o = new Object();
        try {
            Aresta a = buscaAresta(v1, v2);
            synchronized (o) {
                Identificador ida = new Identificador(v1, v2, a.isDirecionada());
                if (A.remove(ida) == null) {
                    return true;
                }
            }
        } catch (NotFoundEx e) {
            return false;
        }
        return false;
}

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean excluiVertice(int id) throws TException {
        Object o = new Object();
        Vertice vt = V.get(id);
        synchronized (o) {
            if (vt != null) {
                // Cria um arraylist de keys a serem removidas posteriormente
                ArrayList<Identificador> arRemovidas = new ArrayList<>();

                // Percorre a hash de arestas e remove aquela que tiver um dos
                // vértices com o nome igual ao parametro "id"
                Set<Map.Entry<Identificador, Aresta>> arestas = A.entrySet();
                for (Iterator i = arestas.iterator(); i.hasNext();) {
                    Map.Entry<Identificador, Aresta> ar;
                    ar = (Map.Entry<Identificador, Aresta>) i.next();
                    Aresta valor = ar.getValue();

                    // Adiciona no arraylist as chaves a serem removidas
                    if (valor.getV1().getNome() == id || valor.getV2().getNome() == id) {
                        arRemovidas.add(ar.getKey());
                    }
                }

                // Remove as chaves
                for (Identificador i : arRemovidas) {
                    A.remove(i);
                }
                V.remove(vt.nome);

                return true;
            }
        }
        return false;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public List<Vertice> listarVertices() throws NotFoundEx, TException {
        synchronized (V.values()) {
            if (!V.isEmpty()) {
                return new ArrayList(V.values());
            }
        }
        throw new NotFoundEx();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public List<Aresta> listarArestas() throws NotFoundEx, TException {
        synchronized (A.values()) {
            if (!A.isEmpty()) {
                return new ArrayList(A.values());
            }
        }
        throw new NotFoundEx();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public List<Aresta> arestasDoVertice(int nome) throws NotFoundEx, TException {
        ArrayList<Aresta> resp = new ArrayList<>();

        synchronized (A.values()) {
            Set<Map.Entry<Identificador, Aresta>> arestas = A.entrySet();
            for (Iterator i = arestas.iterator(); i.hasNext();) {
                Map.Entry<Identificador, Aresta> ar;
                ar = (Map.Entry<Identificador, Aresta>) i.next();
                Aresta valor = ar.getValue();

                // Adiciona no arraylist as chaves a serem removidas
                if (!valor.direcionada) {
                    if (valor.getV1().getNome() == nome || valor.getV2().getNome() == nome) {
                        resp.add(valor);
                    }
                } else if (valor.getV1().getNome() == nome) {
                    resp.add(valor);
                }

            }
            if (!resp.isEmpty()) {
                return resp;
            }
        }

        throw new NotFoundEx();
    }

    @Override
    public List<Vertice> vizinhos(int nome) throws NotFoundEx, TException {
        try {
            ArrayList<Aresta> arestas = (ArrayList<Aresta>) arestasDoVertice(nome);
            synchronized (arestas) {
                ArrayList<Vertice> resp = new ArrayList<>();
                for (Aresta a : arestas) {
                    if (a.getV1().getNome() != nome) {
                        resp.add(a.getV1());
                    } else {
                        resp.add(a.getV2());
                    }
                }
                return resp;
            }
        } catch (NotFoundEx ex) {
            throw new NotFoundEx();
        }
    }

}
