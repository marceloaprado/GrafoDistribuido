/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafoHandler;

import GrafoThrift.GrafoHandler;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 *
 * @author MarceloPrado, Rhaniel Cristhian
 */
public class GrafoServidor {
    public static Handler handler;
    @SuppressWarnings("rawtypes")
	public static GrafoHandler.Processor processor;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String args[]){
        try{
            handler = new Handler();
            processor = new GrafoHandler.Processor(handler);

            TServerTransport serverTransport = new TServerSocket(9090);            
            
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
            
            System.out.println("Starting the server...");
            server.serve();
        }
        catch (Exception x){
            x.printStackTrace();
        }
    }
}
