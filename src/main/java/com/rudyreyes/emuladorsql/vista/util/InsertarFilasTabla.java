/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.emuladorsql.vista.util;

import com.rudyreyes.emuladorsql.modelo.InstruccionInsertar;
import com.rudyreyes.emuladorsql.modelo.archivos.util.CrearArchivos;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JTextArea;

/**
 *
 * @author rudyo
 */
public class InsertarFilasTabla {
    
    public static void insertarFilasTabla(String datosCSV,InstruccionInsertar insertar, JTextArea areaConsola){
        //AGREGAR NUEVA FILA
        String[] lineas = datosCSV.split("\n");

        // Obtener nombres de columnas
        String[] columnas = lineas[0].split(",");
        
        ArrayList<String> columnasSeleccionadas= insertar.getColumnas();
        ArrayList<String> valores= insertar.getValores();
        
        //COMPRABAMOS SI AMBOS TIENEN ALGO
        if(!columnasSeleccionadas.isEmpty() && !valores.isEmpty()){
            if(columnasSeleccionadas.size() == valores.size()){
                insertarLinea(columnasSeleccionadas, valores, columnas,insertar,areaConsola);
            }else{
                areaConsola.append("ERROR: la consulta insertar no tiene la misma cantidad de columnas como de valores");
            }
            
        }else if(columnasSeleccionadas.isEmpty() && !valores.isEmpty()){
            insertarFilaSinColumnas(valores, columnas,insertar,areaConsola);
        }
    }
    
    private static void insertarLinea(ArrayList<String> columnasSeleccionadas,ArrayList<String> valores, String [] columnas, InstruccionInsertar insertar, JTextArea areaConsola){
        ArrayList<Integer> indicesColumnas = new ArrayList<>();
        ArrayList<String> valoresOrdenados = new ArrayList<>();
        boolean errorColumna = false;
        for (int i = columnasSeleccionadas.size() - 1; i >= 0; i--) {
            int indice = CondicionesUtil.indiceColumna(columnasSeleccionadas.get(i), columnas);
            if (indice < 0) {
                errorColumna = true;
            }

            indicesColumnas.add(indice);
            valoresOrdenados.add(valores.get(i));
        }

        //AGREGAR LOS VALORES
        if (!errorColumna) {
            int totalColumnas = columnas.length;

// Crear un array para almacenar los valores de la fila
            String[] filaCSV = new String[totalColumnas];

// Llenar la fila con null en todas las columnas
            Arrays.fill(filaCSV, null);

// Llenar las columnas especificadas con los valores correspondientes
            for (int i = 0; i < indicesColumnas.size(); i++) {
                int indiceColumna = indicesColumnas.get(i);
                String valor = valoresOrdenados.get(i).replace("\"", "");

                // Verificar si el índice está dentro de los límites
                if (indiceColumna >= 0 && indiceColumna < totalColumnas) {
                    filaCSV[indiceColumna] = valor;
                }
            }
            
            String csv = construirCSV(filaCSV);
            String path = insertar.getPath().replace("\"", "");
            
            boolean realizado = CrearArchivos.insertarContenidoArchivo(path, csv);
            if(realizado){
                areaConsola.append("Los datos se han insertado correctamente al archivo");
            }else{
                areaConsola.append("No se han podido insertar correctamente los datos");
            }
        }

    }
    
    private static void insertarFilaSinColumnas(ArrayList<String> valores, String [] columnas, InstruccionInsertar insertar,JTextArea areaConsola){
        ArrayList<String> valoresOrdenados = new ArrayList<>();
        int totalColumnas = columnas.length;
        for (int i = valores.size() - 1; i >= 0; i--) {

            valoresOrdenados.add(valores.get(i));
        }

        // Crear un array para almacenar los valores de la fila
        String[] filaCSV = new String[totalColumnas];

        Arrays.fill(filaCSV, null);

        for (int i = 0; i < valores.size(); i++) {
            String valor = valoresOrdenados.get(i).replace("\"", "");

            filaCSV[i] = valor;
        }

        String csv = construirCSV(filaCSV);
        String path = insertar.getPath().replace("\"", "");
        
        boolean realizado = CrearArchivos.insertarContenidoArchivo(path, csv);
        if (realizado) {
            areaConsola.append("Los datos se han insertado correctamente al archivo");
        } else {
            areaConsola.append("No se han podido insertar correctamente los datos");
        }
    }
    
    private static String construirCSV(String[] filaCSV){
        String csv = "";

        for (int i = 0; i < filaCSV.length; i++) {
            if (i > 0) {
                csv += ","; // Agregar coma si no es el primer elemento
            }

            if (filaCSV[i] != null) {
                csv += filaCSV[i]; 
            } else {
                csv += "null"; 
            }
        }

        return csv;
    }
    
}
