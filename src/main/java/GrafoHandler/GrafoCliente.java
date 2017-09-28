/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafoHandler;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
            	System.out.println("6) Excluir Aresta");
            	System.out.println("7) Listar Arestas de um vértice");
            	
            	//Operações relacionadas aos Vértices
            	System.out.println("8) Adicionar Vértice");
            	System.out.println("9) Busca Vértice");
            	System.out.println("10) Atualiza Vértice");
            	System.out.println("11) Exclui Vértice");
            	System.out.println("12) Lista Vértices");
            	System.out.println("13) Lista os vizinhos de um vértice");
            	
            	//Sair
            	System.out.println("0) Sair");
            	
            	menu = scan.nextInt();
            	scan.nextLine();
            	
            	int identificador;
    			int identificador1;
    			int cor;
    			String descricao;
    			int direcionada;
    			double peso;
    			Vertice v1;
    			Vertice v2;
    			Aresta a;
    			Vertice v;
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
	            			
	            			//Tipo de criação de arestas
	            			switch(menuAresta){
                                //criar a partir de vértices existentes
                            	case 1:
		                            try{
		                            	System.out.println("###########################################################");
	                                    System.out.println("#       Criar Aresta apartir de vértices existentes       #");
	                                    System.out.print("#  Informe o identificador do primeiro vértice: ");
		                            	identificador = scan.nextInt();
			                            scan.nextLine();
		                            	v1 = client.buscaVertice(identificador);
		                            	System.out.println("#  O vértice v1 eh: "+v1.toString());
		                            	System.out.print("#  Informe o identificador do segundo vértice: ");
				    	            	identificador1 = scan.nextInt();
				                        scan.nextLine();
				                        v2 = client.buscaVertice(identificador1);
				                        System.out.println("#  O vértice v2 eh: "+v2.toString());
				                        System.out.println("###########################################################");
			                    		System.out.println("#              Dados complementares da Aresta             #");
			                    			
			                    		System.out.print("#  Informe o peso da Aresta: ");
			                    		peso = scan.nextDouble();
			                            scan.nextLine();
			                            	
			                    		System.out.print("#  A aresta é direcionada? (1- Sim / 2- Não): ");
			                    		direcionada = scan.nextInt();
			                            scan.nextLine();
			                            	
			                            System.out.print("#  Informe a descrição da aresta: ");
			                    		descricao = scan.nextLine();
			                    		
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
		                            }catch(InputMismatchException e){
		                            	System.out.println("#              Parâmetro informado errado                #");
		                                System.out.println("##########################################################");
		                            }
		                            System.out.println();
	    	            			break;
	    	            			
	    	            		//Criar a partir de vértices novos
	            				case 2:
	            					try{
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
		                    			peso = scan.nextDouble();
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
		                    			peso = scan.nextDouble();
		                            	scan.nextLine();
		                            	
		                    			System.out.println("#               Fim Criação Segundo Vértice               #");
		                            	System.out.println("###########################################################");
		                    			
		                            	v2 = new Vertice(identificador,cor,descricao,peso);
		                            	client.addVertice(v2);
		                            	client.addVertice(v1);
		                            	
		                            	System.out.println();
		                            	
		                            	System.out.println("###########################################################");
		                    			System.out.println("#              Dados complementares da Aresta             #");
		                    			
		                    			System.out.print("#  Informe o peso da Aresta: ");
		                    			peso = scan.nextInt();
		                            	scan.nextLine();
		                            	
		                    			System.out.print("#  A aresta é direcionada? (1- Sim / 2- Não): ");
		                    			direcionada = scan.nextInt();
		                            	scan.nextLine();
		                            	
		                            	System.out.print("#  Informe a descrição da aresta: ");
		                    			descricao = scan.nextLine();
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
	            					}catch(InputMismatchException e){
		                            	System.out.println("#              Parâmetro informado errado                #");
		                                System.out.println("##########################################################");
		                            }
	            					break;
	            				case 3:
	            					break;
	            				default:
	            					break;
	            			}
            			}	
            			break;
            		case 2:
                        System.out.println("###########################################################");
                        System.out.println("#                                                         #");
                        System.out.println("#         Opção selecionada --> Buscar Aresta             #");
                        System.out.println("#                                                         #");
                        System.out.println("###########################################################");
                        System.out.println();
                        try{
	                        System.out.println("###########################################################");
	                        System.out.print("#  Informe os identificadores da aresta: ");
	                        identificador = scan.nextInt();
	                        identificador1 = scan.nextInt();
	                        scan.nextLine();

                            a = client.buscaAresta(identificador,identificador1);                            
                            if(a != null){
                                System.out.println(a.toString());
                            }
                        }catch(NotFoundEx nfe){
                            System.out.println("#                 Aresta não encontrado                  #");
                            System.out.println("##########################################################");
                        }catch(InputMismatchException e){
                        	System.out.println("#              Parâmetro informado errado                #");
                            System.out.println("##########################################################");
                        }
            			break;
            		case 3:
            			System.out.println("###########################################################");
                        System.out.println("#                                                         #");
                        System.out.println("#         Opção selecionada --> Atualizar Aresta          #");
                        System.out.println("#                                                         #");
                        System.out.println("###########################################################");
                        System.out.println();
                       
                        try{
	                        System.out.println("###########################################################");
	                        System.out.print("#  Informe os identificadores da aresta: ");
	                        identificador = scan.nextInt();
	                        identificador1 = scan.nextInt();
	                        scan.nextLine();
                        
                            a = client.buscaAresta(identificador,identificador1);
                            
                            if(a != null){
                                System.out.print("#  Informe se a nova aresta é direcionada (1- Sim / 2- Não): ");
                                direcionada = scan.nextInt();
                                scan.nextLine();

                                System.out.print("#  Informe o novo peso da aresta: ");
                                peso = scan.nextDouble();
                                scan.nextLine();

                                System.out.print("#  Informe a nova descrição da aresta: ");
                                descricao = scan.nextLine();
                                
                                if(direcionada == 1){
                                	a.setDirecionada(true);
                                }else{
                                	a.setDirecionada(false);
                                }
                                a.setDescricao(descricao);
                                a.setPeso(peso);
                                
                                if(client.atualizaAresta(a)){
                                    System.out.println("#             Aresta atualizado com sucesso               #");
                                    System.out.println("###########################################################");
                                }else{
                                    System.out.println("#               Falha ao atualizar Aresta                 #");
                                    System.out.println("###########################################################");
                                }
                                System.out.println();
                            }
                        }catch(NotFoundEx nfe){
                            System.out.println("#                 Aresta não encontrado                   #");
                            System.out.println("###########################################################");
                        }catch(InputMismatchException e){
                        	System.out.println("#              Parâmetro informado errado                #");
                            System.out.println("##########################################################");
                        }
            			break;
                                
            		case 4:
            			System.out.println("###########################################################");
                        System.out.println("#                                                         #");
                        System.out.println("#         Opção selecionada --> Listar arestas            #");
                        System.out.println("#                                                         #");
                        System.out.println("###########################################################");
                        System.out.println();
                        
                        try{
                            ArrayList<Aresta> arestas = (ArrayList<Aresta>)client.listarArestas();                            
                            for(Aresta at:arestas)
                                System.out.println(at.toString());

                            System.out.println();
                        }
                        catch(NotFoundEx nfe){
                            System.out.println("#             Não existem arestas cadastradas             #");
                            System.out.println("###########################################################");                    
                        }
                	   break;
            		case 5:
            			System.out.println("###########################################################");
                        System.out.println("#                                                         #");
                        System.out.println("#         Opção selecionada --> Excluir Aresta            #");
                        System.out.println("#                                                         #");
                        System.out.println("###########################################################");
                        System.out.println();
                        try{
                        	System.out.println("###########################################################");
                            System.out.print("#  Informe os identificadores da aresta: ");
                            identificador = scan.nextInt();
	                        identificador1 = scan.nextInt();
	                        scan.nextLine();
	                        
	                        System.out.print("#  Informe se a aresta eh direcionada (1- Sim / 2- Não): ");
	                        direcionada = scan.nextInt();
	                        scan.nextLine();

                            if(!client.excluiAresta(identificador,identificador1,direcionada)){
                                System.out.println("#            Não foi possível excluir a aresta            #");
                                System.out.println("###########################################################");
                            }else{
                                System.out.println("#              Aresta excluída com sucesso                #");
                                System.out.println("###########################################################");
                            }

                        }catch(NotFoundEx nfe){
                            System.out.println("#                 Vértice não encontrado                  #");
                            System.out.println("###########################################################");
                        }catch(InputMismatchException e){
                        	System.out.println("#              Parâmetro informado errado                #");
                            System.out.println("##########################################################");
                        }
            			break;
            		case 6:
            			break;
            		case 7:
            			break;
            		case 8:                            
                            System.out.println("###########################################################");
                            System.out.println("#                                                         #");
                            System.out.println("#         Opção selecionada --> Adicionar Vértice         #");
                            System.out.println("#                                                         #");
                            System.out.println("###########################################################");
                            System.out.println();
                            try{
                            	System.out.println("###########################################################");
                                System.out.print("#  Informe o identificador do vértice: ");
                                identificador = scan.nextInt();
                                scan.nextLine();
                               
                                System.out.print("#  Informe a cor do vértice: ");
                                cor = scan.nextInt();
                                scan.nextLine();
                                
                                System.out.print("#  Informe o peso do vértice: ");
                                peso = scan.nextDouble();
                                scan.nextLine();
                                
                                System.out.print("#  Informe a descrição do vértice: ");
                                descricao = scan.nextLine();
                                
                                v = new Vertice(identificador, cor, descricao, peso);
                                
                                if(client.addVertice(v)){
                                        System.out.println("#                Vértice criado com sucesso               #");
                                        System.out.println("###########################################################");
                                }else{
                                        System.out.println("#                 Falha ao criar Vértice                  #");
                                        System.out.println("###########################################################");
                                }
                                System.out.println();
                            }catch(InputMismatchException e){
                            	System.out.println("#              Parâmetro informado errado                #");
                                System.out.println("##########################################################");
                            }
                            

                            
                            break;
            		case 9:                                
                            System.out.println("###########################################################");
                            System.out.println("#                                                         #");
                            System.out.println("#         Opção selecionada --> Buscar Vértice            #");
                            System.out.println("#                                                         #");
                            System.out.println("###########################################################");
                            System.out.println();
                            try{
	                            System.out.println("###########################################################");
	                            System.out.print("#  Informe o identificador do vértice: ");
	                            identificador = scan.nextInt();
	                            scan.nextLine();

                                v = client.buscaVertice(identificador);
                                
                                if(v != null){
                                    System.out.println(v.toString());
                                }
                            }catch(NotFoundEx nfe){
                                System.out.println("#                 Vértice não encontrado                  #");
                                System.out.println("###########################################################");
                            }catch(InputMismatchException e){
                            	System.out.println("#              Parâmetro informado errado                #");
                                System.out.println("##########################################################");
                            }
                            
            			break;            		
            		case 10:
                            System.out.println("###########################################################");
                            System.out.println("#                                                         #");
                            System.out.println("#         Opção selecionada --> Atualizar Vértice         #");
                            System.out.println("#                                                         #");
                            System.out.println("###########################################################");
                            System.out.println();
                            
                            try{
	                            System.out.println("###########################################################");
	                            System.out.print("#  Informe o identificador do vértice: ");
	                            identificador = scan.nextInt();
	                            scan.nextLine();
	                            
                           
                                v = client.buscaVertice(identificador);
                                
                                if(v != null){
                                    System.out.print("#  Informe a nova cor do vértice: ");
                                    cor = scan.nextInt();
                                    scan.nextLine();

                                    System.out.print("#  Informe o novo peso do vértice: ");
                                    peso = scan.nextDouble();
                                    scan.nextLine();

                                    System.out.print("#  Informe a nova descrição do vértice: ");
                                    descricao = scan.nextLine();
                                    
                                    v.setCor(cor);
                                    v.setDescricao(descricao);
                                    v.setPeso(peso);
                                    
                                    if(client.atualizaVertice(v)){
                                        System.out.println("#             Vértice atualizado com sucesso              #");
                                        System.out.println("###########################################################");
                                    }else{
                                        System.out.println("#               Falha ao atualizar Vértice                #");
                                        System.out.println("###########################################################");
                                    }
                                    System.out.println();
                                }
                            }catch(NotFoundEx nfe){
                                System.out.println("#                 Vértice não encontrado                  #");
                                System.out.println("###########################################################");
                            }catch(InputMismatchException e){
                            	System.out.println("#              Parâmetro informado errado                #");
                                System.out.println("##########################################################");
                            }
                            break;
            		case 11:
                            System.out.println("###########################################################");
                            System.out.println("#                                                         #");
                            System.out.println("#         Opção selecionada --> Excluir Vértice           #");
                            System.out.println("#                                                         #");
                            System.out.println("###########################################################");
                            System.out.println();
                            try{
                            	System.out.println("###########################################################");
                                System.out.print("#  Informe o identificador do vértice: ");
                                identificador = scan.nextInt();
                                scan.nextLine();
                                                            
                                if(!client.excluiVertice(identificador)){
                                    System.out.println("#            Não foi possível excluir o vértice           #");
                                    System.out.println("###########################################################");
                                }else{
                                    System.out.println("#              Vértice excluído com sucesso               #");
                                    System.out.println("###########################################################");
                                }

                            }catch(NotFoundEx nfe){
                                System.out.println("#                 Vértice não encontrado                  #");
                                System.out.println("###########################################################");
                            }catch(InputMismatchException e){
                            	System.out.println("#              Parâmetro informado errado                #");
                                System.out.println("##########################################################");
                            }
                            break;
            		case 12:
                            System.out.println("###########################################################");
                            System.out.println("#                                                         #");
                            System.out.println("#         Opção selecionada --> Listar vértices           #");
                            System.out.println("#                                                         #");
                            System.out.println("###########################################################");
                            System.out.println();
                            
                            try{
                                ArrayList<Vertice> vertices = (ArrayList<Vertice>)client.listarVertices();                            
                                for(Vertice vt:vertices)
                                    System.out.println(vt.toString());

                                System.out.println();
                            }
                            catch(NotFoundEx nfe){
                                System.out.println("#             Não existem vértices cadastrados            #");
                                System.out.println("###########################################################");                    
                            }catch(InputMismatchException e){
                            	System.out.println("#              Parâmetro informado errado                #");
                                System.out.println("##########################################################");
                            }
                            break;
            		case 13:
            			break;
            		case 0:
            			break;
            		default:
            			break;
            			
            	}            	
            }
            scan.close();
            transport.close();
        } catch (TException x) {
            x.printStackTrace();
        } 
    }
}
