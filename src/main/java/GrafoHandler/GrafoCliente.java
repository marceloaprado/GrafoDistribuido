/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafoHandler;

import java.util.Scanner;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import GrafoThrift.Aresta;
import GrafoThrift.GrafoHandler;
import GrafoThrift.NotFoundEx;
import GrafoThrift.Vertice;

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
            
            int menu = 1;
            Scanner scan = new Scanner(System.in);
            
            //Menu do usuário
            while(menu != 0){
            	System.out.println("######################################################");
            	System.out.println("#      Bem vindo ao sistema de criação de Grafo!!    #");
            	System.out.println("#           GSI028 - Sistemas Distribuídos           #");
            	System.out.println("#         Marcelo Prado e Rhaniel Cristhian          #");
            	System.out.println("######################################################");
            	
            	System.out.println("Escolha a operação desejada:");
            	//Operações relacionadas as Arestas
            	System.out.println("1) Adicionar Aresta");
            	System.out.println("2) Busca Aresta");
            	System.out.println("3) Atualiza Aresta");
            	System.out.println("4) Lista Arestas");
            	
            	//Operações relacionadas aos Vértices
            	System.out.println("5) Adicionar Vértice");
            	System.out.println("6) Busca Vértice");
            	System.out.println("7) Atualiza Vértice");
            	System.out.println("8) Exclui Vértice");
            	System.out.println("9) Lista Vértices");
            	
            	//Sair
            	System.out.println("0) Sair");
            	
            	menu = scan.nextInt();
            	scan.nextLine();
            	switch(menu){
            		case 1:
            			int menuAresta = 0;
            			System.out.println("###########################################################");
            			System.out.println("#                                                         #");
            			System.out.println("#         Opção selecionada --> Adicionar Aresta          #");
            			System.out.println("#                                                         #");
            			System.out.println("###########################################################");
            			
            			System.out.println();
            			//Obriga a escolher uma operação
            			while(menuAresta != 3){
	            			System.out.println("###########################################################");
	            			System.out.println("#     1) Criar Aresta apartir de vértices existentes      #");
	            			System.out.println("#     2) Criar Aresta apartir de vértices novos           #");
	            			System.out.println("#     3) Sair                                             #");
	            			menuAresta = scan.nextInt();
	            			scan.nextLine();
	            			System.out.println("#                                                         #");
	            			System.out.println("###########################################################");
	            			
	            			System.out.println();
	            			int identificador;
	            			int cor;
	            			String descricao;
	            			int peso;
	            			Vertice v1;
	            			Vertice v2;
	            			//Tipo de criação de arestas
	            			switch(menuAresta){
	            				//criar a partir de vértices existentes
	            				case 1:
	            					System.out.println("###########################################################");
	    	            			System.out.println("#       Criar Aresta apartir de vértices existentes       #");
	    	            			System.out.print("#  Informe o identificador do primeiro vértice: ");
		    	            		identificador = scan.nextInt();
		                            scan.nextLine();
		                            try{
		                            	v1 = client.buscaVertice(identificador);
		                            	System.out.print("#  Informe o identificador do segundo vértice: ");
				    	            	identificador = scan.nextInt();
				                        scan.nextLine();
				                        v2 = client.buscaVertice(identificador);
				                        
				                        System.out.println("###########################################################");
			                    		System.out.println("#              Dados complementares da Aresta             #");
			                    			
			                    		System.out.print("#  Informe o peso da Aresta: ");
			                    		peso = scan.nextInt();
			                            scan.nextLine();
			                            	
			                    		System.out.print("#  A aresta é direcionada? (1- Sim / 2- Não): ");
			                    		int direcionada = scan.nextInt();
			                            scan.nextLine();
			                            	
			                            System.out.print("#  Informe a descrição da aresta: ");
			                    		descricao = scan.nextLine();
			                    		Aresta a;
			                            if(direcionada == 1){
			                            	a = new Aresta(v1,v2, peso, true, descricao);
			                            	
			                            }else{
			                            	a = new Aresta(v1,v2, peso, false, descricao);
			                            }
			                            if(client.addAresta(a)){
			                            	System.out.println("#                Aresta criada com Sucesso                #");
				                            System.out.println("###########################################################");
			                            }else{
			                            	System.out.println("#                  Falha ao criar aresta                  #");
				                            System.out.println("###########################################################");
			                            }
			                            menuAresta = 3;
		                            }catch(NotFoundEx e){
		                            	System.out.println("#             		Vértice não encontrado                #");     
		                            	System.out.println("###########################################################");
		                            }
		                            System.out.println();
	    	            			break;
	    	            			
	    	            		//Criar a partir de vértices novos
	            				case 2:
	            					System.out.println("###########################################################");
	                    			System.out.println("#               Criação do Primeiro Vértice               #");
	                    			System.out.print("#  Informe o identificador do primeiro vértice: ");
	                    			identificador = scan.nextInt();
	                            	scan.nextLine();
	                            	
	                    			System.out.print("#  Informe a cor do primeiro vértice: ");
	                    			cor = scan.nextInt();
	                            	scan.nextLine();
	                            	
	                    			System.out.print("#  Informe a descrição do primeiro vértice: ");
	                    			descricao = scan.nextLine();
	                            	
	                    			System.out.print("#  Informe o peso do primeiro vértice: ");
	                    			peso = scan.nextInt();
	                            	scan.nextLine();
	                            	
	                    			System.out.println("#               Fim Criação Primeiro Vértice              #");
	                            	System.out.println("###########################################################");
	                    			
	                            	v1 = new Vertice(identificador,cor,descricao,peso);
	                            	
	                            	System.out.println();
	        	
	                    			System.out.println("###########################################################");
	                    			System.out.println("#                Criação do Segundo Vértice               #");
	                    			System.out.print("#  Informe o identificador do segundo vértice: ");
	                    			identificador = scan.nextInt();
	                            	scan.nextLine();
	                            	
	                    			System.out.print("#  Informe a cor do segundo vértice: ");
	                    			cor = scan.nextInt();
	                            	scan.nextLine();
	                            	
	                    			System.out.print("#  Informe a descrição do segundo vértice: ");
	                    			descricao = scan.nextLine();
	                            	
	                    			System.out.print("#  Informe o peso do segundo vértice: ");
	                    			peso = scan.nextInt();
	                            	scan.nextLine();
	                            	
	                    			System.out.println("#               Fim Criação Segundo Vértice               #");
	                            	System.out.println("###########################################################");
	                    			
	                            	v2 = new Vertice(identificador,cor,descricao,peso);
	                            	
	                            	System.out.println();
	                            	
	                            	System.out.println("###########################################################");
	                    			System.out.println("#              Dados complementares da Aresta             #");
	                    			
	                    			System.out.print("#  Informe o peso da Aresta: ");
	                    			peso = scan.nextInt();
	                            	scan.nextLine();
	                            	
	                    			System.out.print("#  A aresta é direcionada? (1- Sim / 2- Não): ");
	                    			int direcionada = scan.nextInt();
	                            	scan.nextLine();
	                            	
	                            	System.out.print("#  Informe a descrição da aresta: ");
	                    			descricao = scan.nextLine();
	                    			Aresta a;
	                            	if(direcionada == 1){
	                            		a = new Aresta(v1,v2, peso, true, descricao);
	                            	
	                            	}else{
	                            		a = new Aresta(v1,v2, peso, false, descricao);
	                            	}
	                            	if(client.addAresta(a)){
	                            		System.out.println("#                Aresta criada com Sucesso                #");
		                            	System.out.println("###########################################################");
	                            	}else{
	                            		System.out.println("#                  Falha ao criar aresta                  #");
		                            	System.out.println("###########################################################");
	                            	}
	                            	System.out.println();
	                            	menuAresta = 3;
	            					break;
	            				case 3:
	            					break;
	            				default:
	            					break;
	            			}
            			}	
            			break;
            		case 2:
            			break;
            		case 3:
            			break;
            		case 4:
            			break;
            		case 5:
            			break;
            		case 6:
            			break;
            		case 7:
            			break;
            		case 8:
            			break;
            		case 9:
            			break;
            		case 0:
            			break;
            		default:
            			break;
            			
            	}
            	
           }
            Vertice v1 = new Vertice(1, 1, "Marcelo", 1);

            client.addVertice(v1);
            System.out.println(client.buscaVertice(1).toString());

            transport.close();
        } catch (TException x) {
            x.printStackTrace();
        } 
    }
}
