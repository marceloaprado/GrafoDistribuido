/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafoJava;

import GrafoThrift.Identificador;

/**
 *
 * @author MarceloPrado
 */
public class IdentificadorAresta extends Identificador{
    public IdentificadorAresta(int v1, int v2){
        
    }
    
    @Override
    public int hashCode(){
        return 1;
    }
    
    @Override
    public boolean equals(Identificador id){
        return true;
    }
}   
