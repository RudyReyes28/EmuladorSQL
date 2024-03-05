/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.emuladorsql.vista.util;

import com.rudyreyes.emuladorsql.modelo.InstruccionSeleccionar;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author rudyo
 */
public class MostrarConsultasSeleccionar {
    
    public static JScrollPane mostrarTablasSeleccionar(String datosCSV,InstruccionSeleccionar seleccion){
        JScrollPane scrollPane = null;
        String[] lineas = datosCSV.split("\n");

        // Obtener nombres de columnas
        String[] columnas = lineas[0].split(",");

        // Obtener datos
        
        
        //YA OBTENEMOS LAS COLUMNAS Y DATOS AHORA SOLO FALTA OBTENER SOLO TABLAS O CONDICIONES
        if (seleccion.getSeleccionarTodo().equals("*")) {
            System.out.println("Entramos a seleccionar todo");
            //SELECCIONAMOS TODAS LAS TABLAS
            if(seleccion.getCondiciones()==null){
                //PUES DEBEMOS FILTRAR APLICADO CONDICIONES
            }else{
                System.out.println("Miramos las tablas");
                
                List<Object[]> datosList = new ArrayList<>();
                //SOLO FILTRAMOS DE MANERA NORMAL
                for (int i = 1; i < lineas.length; i++) {
                    String[] valores = lineas[i].split(",");
                    datosList.add(valores);
                }
                
                Object[][] datos = obtenerTabla(datosList, columnas);
                MiModeloTabla modeloTabla = new MiModeloTabla(columnas, datos);

                // Crear la tabla
                JTable tabla = new JTable(modeloTabla);
                // Crear el JScrollPane con la tabla
                 scrollPane = new JScrollPane(tabla);
                return scrollPane;
            }
        }else{
            //SELECCIONAR TABLAS
            ArrayList<Integer> columnasSeleccionadas = seleccionarTablas(columnas, seleccion);
            List<Object[]> datosList = new ArrayList<>();
            if(seleccion.getCondiciones()==null){
                //PUES DEBEMOS FILTRAR APLICADO CONDICIONES
            }else{
                //SOLO FILTRAMOS DE MANERA NORMAL
                String [] nuevasColumnas = obtenerColumnasSeleccionadas(columnas, columnasSeleccionadas);
                filtrarDatosSinCondiciones(lineas, columnasSeleccionadas, datosList);
                Object[][] datos = obtenerTabla(datosList, nuevasColumnas);
                
                MiModeloTabla modeloTabla = new MiModeloTabla(nuevasColumnas, datos);

                // Crear la tabla
                JTable tabla = new JTable(modeloTabla);
                // Crear el JScrollPane con la tabla
                 scrollPane = new JScrollPane(tabla);
                return scrollPane;

            }
        }
        
        // Crear el modelo de tabla
       
        
        return scrollPane;
    }
    
    private static ArrayList<Integer> seleccionarTablas(String [] columnas,InstruccionSeleccionar seleccion){
        ArrayList<Integer> filtroColumnas = new ArrayList<>();
        
        ArrayList<String> columnasSeleccionadas = seleccion.getColumnas();

    for (int i = columnasSeleccionadas.size() - 1; i >= 0; i--) {
        String columnaSeleccionada = columnasSeleccionadas.get(i);

        
        for (int j = 0; j < columnas.length; j++) {
            if (columnas[j].equals(columnaSeleccionada)) {
                // Agregar la posición al filtroColumnas
                filtroColumnas.add(j);
                break; // Salir del bucle interno una vez encontrada la columna
            }
        }
    }

    return filtroColumnas;
        
    }
    
    private static void filtrarDatosSinCondiciones(String[] lineas, ArrayList<Integer> columnasFiltradas,List<Object[]> datosList) {
        for (int i = 1; i < lineas.length; i++) {
            String[] valores = lineas[i].split(",");

            // Crear un array para los valores de las columnas filtradas
            Object[] valoresFiltrados = new Object[columnasFiltradas.size()];

            // Llenar el array de valores filtrados con los valores correspondientes
            for (int j = 0; j < columnasFiltradas.size(); j++) {
                int indiceColumna = columnasFiltradas.get(j);
                valoresFiltrados[j] = valores[indiceColumna];
            }

            datosList.add(valoresFiltrados);
        }
    }
    
    private static String[] obtenerColumnasSeleccionadas( String[] columnas,ArrayList<Integer> columnasSeleccionadas){
        String [] nuevaColumna = new String[columnasSeleccionadas.size()];
        
        for (int j = 0; j < columnasSeleccionadas.size(); j++) {
            int indiceColumna = columnasSeleccionadas.get(j);
            nuevaColumna[j] = columnas[indiceColumna];
        }
        
        return nuevaColumna;
    }
    
    private static  Object[][] obtenerTabla(List<Object[]> datosList, String [] columnas){
        // Convertir List<Object[]> a Object[][]
        Object[][] datos = new Object[datosList.size()][columnas.length];
        for (int i = 0; i < datosList.size(); i++) {
            datos[i] = datosList.get(i);
        }
        
        return datos;
    }
}
