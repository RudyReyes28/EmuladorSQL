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
public class InstruccionInsertar {
    private String path;
    private ArrayList<String> columnas;
    private ArrayList<String> valores;

    public InstruccionInsertar(String path, ArrayList<String> columnas, ArrayList<String> valores) {
        this.path = path;
        this.columnas = columnas;
        this.valores = valores;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ArrayList<String> getColumnas() {
        return columnas;
    }

    public void setColumnas(ArrayList<String> columnas) {
        this.columnas = columnas;
    }

    public ArrayList<String> getValores() {
        return valores;
    }

    public void setValores(ArrayList<String> valores) {
        this.valores = valores;
    }
    
    
    public void mostrarDatos(){
        System.out.println("Direccion: "+this.path);
        System.out.println("\nColumnas si hay: ");
        if(columnas!=null){
            for(String columna: columnas){
                System.out.println("Columna: "+columna);
            }
        }
        System.out.println("Valores: ");
        if(valores!=null){
            for(String valor: valores){
                System.out.println("Valor: "+valor);
            }
        }
    }
}
