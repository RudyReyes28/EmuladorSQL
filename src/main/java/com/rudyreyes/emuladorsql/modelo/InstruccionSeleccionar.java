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
public class InstruccionSeleccionar {
    private String seleccionarTodo;
    private ArrayList<String> columnas;
    private String path;
    private ArrayList<Columna> condiciones;
    private ArrayList<String> operadoresLogicos;

    public InstruccionSeleccionar(String seleccionarTodo, ArrayList<String> columnas, String path, ArrayList<Columna> condiciones, ArrayList<String> operadoresLogicos) {
        this.seleccionarTodo = seleccionarTodo;
        this.columnas = columnas;
        this.path = path;
        this.condiciones = condiciones;
        this.operadoresLogicos = operadoresLogicos;
    }

    public InstruccionSeleccionar(String seleccionarTodo, ArrayList<String> columnas, ArrayList<Columna> condiciones, ArrayList<String> operadoresLogicos) {
        this.seleccionarTodo = seleccionarTodo;
        this.columnas = columnas;
        this.condiciones = condiciones;
        this.operadoresLogicos = operadoresLogicos;
    }
    
    

    public InstruccionSeleccionar() {
        columnas = new ArrayList<>();
        condiciones = new ArrayList<>();
        operadoresLogicos = new ArrayList<>();
    }

    public InstruccionSeleccionar(String seleccionarTodo, String path, ArrayList<Columna> condiciones, ArrayList<String> operadoresLogicos) {
        this.seleccionarTodo = seleccionarTodo;
        this.path = path;
        this.condiciones = condiciones;
        this.operadoresLogicos = operadoresLogicos;
    }

    public InstruccionSeleccionar(ArrayList<String> columnas, String path) {
        this.columnas = columnas;
        this.path = path;
    }
    
    

    public String getSeleccionarTodo() {
        return seleccionarTodo;
    }

    public ArrayList<String> getColumnas() {
        return columnas;
    }

    public String getPath() {
        return path;
    }

    public ArrayList<Columna> getCondiciones() {
        return condiciones;
    }

    public ArrayList<String> getOperadoresLogicos() {
        return operadoresLogicos;
    }
    
    public void agregarColumna(String columna){
        this.columnas.add(columna);
    }
    
    public void agregarCondicion(String columna, String operador, String valor){
        this.condiciones.add(new Columna(columna, operador, valor));
    }
    
    public void agregarOperadorLogico(String operador){
        this.operadoresLogicos.add(operador);
    }

    public void setSeleccionarTodo(String seleccionarTodo) {
        this.seleccionarTodo = seleccionarTodo;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    public void imprimirDatos(){
        System.out.println("Columnas a mostrar: ");
        if(seleccionarTodo == "*"){
            System.out.println("Columnas: *");
        }else{
            if(columnas!=null){
                for (String elemento : columnas) {
                    System.out.println("Columna: "+elemento);
                }   
            }
        }
        
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
