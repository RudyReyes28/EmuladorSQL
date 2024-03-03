/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.emuladorsql.modelo;

/**
 *
 * @author rudyo
 */
public class Columna {
    private String columna;
    private String operador;
    private String valor;

    public Columna(String columna, String operador, String valor) {
        this.columna = columna;
        this.operador = operador;
        this.valor = valor;
    }

    public Columna() {
    }

    public String getColumna() {
        return columna;
    }

    public void setColumna(String columna) {
        this.columna = columna;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    
    
    
}
