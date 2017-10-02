/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TesteConcorrencia;

import GrafoThrift.Aresta;
import GrafoThrift.Vertice;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 *
 * @author MarceloPrado, Rhaniel Cristhian
 */
public class ThreadComandos extends Thread {
    TTransport transport;
    TProtocol protocol;    
    String ip;
    int porta;
    String nome;
    GrafoThrift.GrafoHandler.Client client;
    
    public ThreadComandos(String ip, int porta, String nome) throws TTransportException{
        TTransport transport = new TSocket(ip, porta);
        transport.open();
        this.protocol = new TBinaryProtocol(transport);;        
        this.ip = ip;
        this.porta = porta;
        this.client = new GrafoThrift.GrafoHandler.Client(protocol);
        this.nome = nome;        
    }
    
    public void TestarVertices() throws TException{
        for(int i = 1; i <= 50; i++){
            Vertice vet = new Vertice(i, i, "v"+i, i);
            System.out.println("Thread " + this.nome + ": Tentando adicionar vértice " + vet.getNome());
            if(client.addVertice(vet))
                System.out.println("Thread " + this.nome + ": Vertice " + vet.getNome() + " adicionado!");
            else
                System.out.println("Thread " + this.nome + ": Vertice " + vet.getNome() + " não pode ser adicionado!");
        }        
    }
    
    public void TestarArestas() throws TException{
        Vertice vet[] = new Vertice[10];
        Aresta ar[] = new Aresta[10];
        for(int i = 0; i < 10; i++){
            vet[i] = new Vertice(i+1, i+1, "v"+(i+1), i+1);
        }
        
        ar[0] = new Aresta(vet[0], vet[1], 2, false, "a1");
        ar[1] = new Aresta(vet[1], vet[2], 3, true, "a2");
        ar[2] = new Aresta(vet[2], vet[3], 4, true, "a3");
        ar[3] = new Aresta(vet[1], vet[0], 3, true, "a4");
        ar[4] = new Aresta(vet[4], vet[5], 2, false, "a5");
        ar[5] = new Aresta(vet[6], vet[8], 7, true, "a6");
        ar[6] = new Aresta(vet[7], vet[3], 6, false, "a7");
        ar[7] = new Aresta(vet[8], vet[9], 7, true, "a8");
        ar[8] = new Aresta(vet[3], vet[6], 2, false, "a9");
        ar[9] = new Aresta(vet[9], vet[7], 1, false, "a10");
        
        for(int i = 0; i < 7; i++){
            System.out.println("Thread " + this.nome + ": Tentando adicionar aresta " + ar[i].getDescricao());
            if(client.addAresta(ar[i]))
                System.out.println("Thread " + this.nome + ": Aresta " + ar[i].getDescricao() + " adicionada");
            else
                System.out.println("Thread " + this.nome + ": Aresta " + ar[i].getDescricao() + " não pode ser adicionada!");
        }            
    }
    
    public void TestarExclusaoArestas() throws TException{
        for(int i = 1; i < 10; i++){
            for(int j = 1; j < 10; j++){
                System.out.println("Thread " + this.nome + ": Tentando remover aresta " + i + "," + j +" (caso existir)");
                if(client.excluiAresta(i, j)){
                    System.out.println("Thread " + this.nome + ": Aresta " + i + "," + j +" removida com sucesso!");
                }
                else
                    System.out.println("Thread " + this.nome + ": Aresta " + i + "," + j +" não pode ser removida!");
            }
        }
        
    }
    
    public void TestarExclusaoVertices() throws TException{
        for(int i = 1; i < 60; i++){
            System.out.println("Thread " + this.nome + ": Tentando remover vértice " + i);
            if(client.excluiVertice(i)){
                System.out.println("Thread " + this.nome + ": Vértice " + i + " removido com sucesso!");
            }
            else
                System.out.println("Thread " + this.nome + ": Vértice " + i + " não pode ser removido!");
        }
    }
    
    public void TestarBuscaArestas(){
        
    }
    
    public void TestarAdicaoExclusao(){
        
    }
    
    @Override
    public void run() {
        try {
            this.TestarVertices(); 
            System.out.println("####################### THREAD " + this.nome + " #######################");
        } catch (TException ex) {
            System.out.println("Thread " + this.nome + ": Erro desconhecido ao adicionar vértices");
        }
        
        try {
            this.TestarArestas();            
            System.out.println("####################### THREAD " + this.nome + " #######################");
        } catch (TException ex) {
            System.out.println("Thread " + this.nome + ": Erro desconhecido ao adicionar arestas");
        }
        
        try {
            this.TestarExclusaoArestas();            
            System.out.println("####################### THREAD " + this.nome + " #######################");
        } catch (TException ex) {
            System.out.println("Thread " + this.nome + ": Erro desconhecido ao excluir arestas");
        }
        
        try {
            this.TestarExclusaoVertices();            
            System.out.println("####################### THREAD " + this.nome + " #######################");
        } catch (TException ex) {
            System.out.println("Thread " + this.nome + ": Erro desconhecido ao excluir vértices");
        }
    }    
}