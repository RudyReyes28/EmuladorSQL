/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.emuladorsql.modelo;

import java.util.ArrayList;

/**
 *
 * @author rudyo
 */
public class InstruccionEliminar {
    private String path;
    private ArrayList<Columna> condiciones;
    private ArrayList<String> operadoresLogicos;

    public InstruccionEliminar(String path, ArrayList<Columna> condiciones, ArrayList<String> operadoresLogicos) {
        this.path = path;
        this.condiciones = condiciones;
        this.operadoresLogicos = operadoresLogicos;
    }

    public InstruccionEliminar() {
    }

    
    
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ArrayList<Columna> getCondiciones() {
        return condiciones;
    }

    public void setCondiciones(ArrayList<Columna> condiciones) {
        this.condiciones = condiciones;
    }

    public ArrayList<String> getOperadoresLogicos() {
        return operadoresLogicos;
    }

    public void setOperadoresLogicos(ArrayList<String> operadoresLogicos) {
        this.operadoresLogicos = operadoresLogicos;
    }
    
    public void mostrarDatos(){
        System.out.println("Direccion: "+this.path);
        
        
        System.out.println("\nCondiciones si hay: ");
        if(condiciones!=null){
            for(Columna condicion: condiciones){
                System.out.println("Columna: "+condicion.getColumna()+" "+condicion.getOperador()+" "+condicion.getValor());
            }
        }
        
        System.out.println("Operadores logicos: ");
        if(operadoresLogicos != null){
            for(String operador: operadoresLogicos){
                System.out.println("Operador: "+operador);
            }
        }
    }
}
