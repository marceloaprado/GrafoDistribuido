/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafoHandler;

import GrafoThrift.GrafoHandler;
import GrafoThrift.Vertice;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 *
 * @author MarceloPrado, Rhaniel Cristhian 
 * 
 * Nessa classe deve ser feito o menu com as operações do grafo de adicionar, remover, buscar e excluir vértices e arestas 
 * (OBS: essas operações estão implementadas na classe Handler do pacote GrafoHandler)
 */
public class GrafoCliente {
    public static void main(String [] args) {
        try {
            TTransport transport = new TSocket("localhost", 9090);
            transport.open();

            TProtocol protocol = new  TBinaryProtocol(transport);
            GrafoHandler.Client client = new GrafoHandler.Client(protocol);

            Vertice v1 = new Vertice(1, 1, "Marcelo", 1);

            client.addVertice(v1);
            System.out.println(client.buscaVertice(1).toString());

            transport.close();
        } catch (TException x) {
            x.printStackTrace();
        } 
    }
}
