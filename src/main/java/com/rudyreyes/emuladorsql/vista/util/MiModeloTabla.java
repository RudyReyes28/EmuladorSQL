/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.emuladorsql.vista.util;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author rudyo
 */
public class MiModeloTabla extends AbstractTableModel{
    private String[] columnas;
    private Object[][] datos;

    public MiModeloTabla(String[] columnas, Object[][] datos) {
        this.columnas = columnas;
        this.datos = datos;
    }

    @Override
    public int getRowCount() {
        return datos.length;
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        return datos[fila][columna];
    }

    @Override
    public String getColumnName(int columna) {
        return columnas[columna];
    }
    
}
