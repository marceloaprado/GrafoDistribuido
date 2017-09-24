/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafoHandler;

import GrafoThrift.GrafoHandler;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 *
 * @author MarceloPrado, Rhaniel Cristhian
 */
public class GrafoServidor {
    public static Handler handler;
    public static GrafoHandler.Processor processor;
    
    public static void main(String args[]){
        try{
            handler = new Handler();
            processor = new GrafoHandler.Processor(handler);

            TServerTransport serverTransport = new TServerSocket(9090);
            
            //Aqui está sendo usado o TSimpleServer, só que ele não aceita conexões concorrentes... Precisamos descobrir qual é o correto!
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));

            System.out.println("Starting the simple server...");
            server.serve();
        }
        catch (Exception x){
            x.printStackTrace();
        }
    }
}
