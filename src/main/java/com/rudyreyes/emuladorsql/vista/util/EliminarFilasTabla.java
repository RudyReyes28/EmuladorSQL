/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.emuladorsql.vista.util;

import com.rudyreyes.emuladorsql.modelo.Columna;
import com.rudyreyes.emuladorsql.modelo.InstruccionEliminar;
import com.rudyreyes.emuladorsql.modelo.archivos.util.CrearArchivos;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;

/**
 *
 * @author rudyo
 */
public class EliminarFilasTabla {
    
    public static void eliminarFilasTablas(String datosCSV,InstruccionEliminar eliminar, JTextArea areaConsola){
        String[] lineas = datosCSV.split("\n");

        // Obtener nombres de columnas
        String[] columnas = lineas[0].split(",");
        
        if(eliminar.getCondiciones()!=null && !eliminar.getCondiciones().isEmpty()){
            int tipoLogico = CondicionesUtil.isCondicionANDOR(eliminar.getOperadoresLogicos());
            ArrayList<Integer> filasFiltradas = null;
            if (tipoLogico == 1 || tipoLogico == 3) {
                filasFiltradas = filtrarDatosCondicionesAND(lineas, eliminar, columnas, areaConsola);

            } else if (tipoLogico == 2) {
                filasFiltradas = filtrarDatosCondicionesOR(lineas, eliminar, columnas, areaConsola);
            } else {
                //SON DE LOS DOS TIPOS 
                areaConsola.append("No se permite tener mas de dos operadores logicos diferentes en una consulta");

            }
            
            if (tipoLogico != 0) {
                List<Object[]> datosList = new ArrayList<>();
                filtrarDatosConCondiciones(lineas, filasFiltradas, datosList, eliminar);
                    actualizarTabla(columnas, datosList);
                    String path = eliminar.getPath().replace("\"", "");
                    String csv = actualizarTabla(columnas, datosList);
                    
                    System.out.println(csv);
                    
                    boolean realizado = CrearArchivos.actualizarArchivo(path, csv);
                    if (realizado) {
                        areaConsola.append("Los datos se han actualizado correctamente al archivo");
                    } else {
                        areaConsola.append("No se han podido actualizar correctamente los datos");
                    }
                        
            }
        } else {
            String csv = lineas[0];
            String path = eliminar.getPath().replace("\"", "");
            boolean realizado = CrearArchivos.actualizarArchivo(path, csv);
            if (realizado) {
                areaConsola.append("Los datos se han eliminado correctamente al archivo");
            } else {
                areaConsola.append("No se han podido eliminar correctamente los datos");
            }
            //System.out.println(csv);
        }
    }
    
    private static void filtrarDatosConCondiciones(String[] lineas, ArrayList<Integer> filasFiltradas,List<Object[]> datosList, InstruccionEliminar eliminar) {
        
        for (int i = 1; i < lineas.length; i++) {
            String[] valores = lineas[i].split(",");

            if (!filasFiltradas.contains(i)) {
                datosList.add(valores);
            }

        }


    }
    
    private static String actualizarTabla(String [] columnas, List<Object[]> datosList){
        String csv = "";
        
        for (int i = 0; i < columnas.length; i++) {
            if (i > 0) {
                csv += ","; // Agregar coma si no es el primer elemento
            }
            String columnaSinEspacios = columnas[i].trim();
            csv += columnaSinEspacios;

           
        }
        
        for (Object[] fila : datosList) {
            csv+="\n";
            for (int i = 0; i < fila.length; i++) {
                if (i > 0) {
                    csv += ","; // Agregar coma si no es el primer elemento
                }
                csv += fila[i];
            }
            
        }
        
        return csv;

    }
    
    private static ArrayList<Integer> filtrarDatosCondicionesAND(String[] lineas, InstruccionEliminar InstruccionEliminar, String [] columnas, JTextArea areaConsola){
        ArrayList<Columna> condiciones = InstruccionEliminar.getCondiciones();
        ArrayList<Integer> filasFiltradas = new ArrayList<>();
        
        for (int i = 1; i < lineas.length; i++) {
            String[] valores = lineas[i].split(",");
            boolean comprobar= false;
            for (int j = condiciones.size() - 1; j >= 0; j--) {
                Columna condicion = condiciones.get(j);
                
                int posicionColumna = CondicionesUtil.indiceColumna(condicion.getColumna(), columnas);
                if(posicionColumna >= 0){
                    comprobar = CondicionesUtil.compararElementos(valores[posicionColumna], condicion.getValor(), condicion.getOperador(), areaConsola);
                
                    if(!comprobar){
                        //si esto es falso es porque no se cumplíó la primera condicion
                        //HACER ALGO Y SALIR DE UNA VEZ FOR CONDICIONES PARA SEGUIR ANALIZADO LA SIGUENTE LINEA
                        break;
                    }
                }else{
                    areaConsola.append("Error: Existen columnas inexistentes, revise la consulta\n");
                }
            }
            if(comprobar){
                filasFiltradas.add(i);
            }
        }
        return filasFiltradas;
    }
    
    private static ArrayList<Integer> filtrarDatosCondicionesOR(String[] lineas, InstruccionEliminar eliminar, String [] columnas, JTextArea areaConsola){
        ArrayList<Columna> condiciones = eliminar.getCondiciones();
        ArrayList<Integer> filasFiltradas = new ArrayList<>();
        
        for (int i = 1; i < lineas.length; i++) {
            String[] valores = lineas[i].split(",");
            boolean comprobar= false;
            for (int j = condiciones.size() - 1; j >= 0; j--) {
                Columna condicion = condiciones.get(j);
                
                int posicionColumna = CondicionesUtil.indiceColumna(condicion.getColumna(), columnas);
                if(posicionColumna >= 0){
                    comprobar = CondicionesUtil.compararElementos(valores[posicionColumna], condicion.getValor(), condicion.getOperador(), areaConsola);
                
                    if(comprobar){
                        //si esto es falso es porque no se cumplíó la primera condicion
                        //HACER ALGO Y SALIR DE UNA VEZ FOR CONDICIONES PARA SEGUIR ANALIZADO LA SIGUENTE LINEA
                        break;
                    }
                }else{
                    areaConsola.append("Error: Existen columnas inexistentes, revise la consulta\n");
                }
            }
            if(comprobar){
                filasFiltradas.add(i);
            }
        }
        return filasFiltradas;
    }
}
