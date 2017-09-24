/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafoJava;

import GrafoThrift.Identificador;

/**
 *
 * @author MarceloPrado, Rhaniel Cristhian
 */
public class IdentificadorAresta extends Identificador{
    public int v1, v2, size;
    public boolean isDirecionada;
    
    
    public IdentificadorAresta(int v1, int v2, boolean isDirecionada, int size){
        super(v1, v2, isDirecionada);
        this.size = size;
    }
    
    @Override
    public int hashCode(){
        double a = 0.6180339887;
        double val = v1 * v2 * a;
        val = val - (int) val;
        return (int)(val * this.size) + (isDirecionada ? 2 : 1);
    }
    
    @Override
    public boolean equals(Identificador id){
        if(id != null){
            if(id.v1 == this.v1 && id.v1 == this.v2 && id.isDirecionada() == id.isDirecionada())
                return true;
        }
        
        return false;
    }
}   
