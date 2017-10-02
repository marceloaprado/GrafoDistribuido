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
            int porta = 9090;
            
            if(args.length != 0)
                porta = Integer.parseInt(args[0]);

            TServerTransport serverTransport = new TServerSocket(porta);            
            
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
            
            System.out.println("Iniciando servidor na porta " + porta);
            System.out.println("Servidor aberto com sucesso!");
            System.out.println("Aguardando conexões...");     
            server.serve();
        }
        catch (Exception x){
            System.out.println("Não foi possível iniciar o servidor!");
            x.printStackTrace();
        }
    }
}
