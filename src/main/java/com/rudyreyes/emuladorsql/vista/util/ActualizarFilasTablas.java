/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.emuladorsql.vista.util;

import com.rudyreyes.emuladorsql.modelo.Columna;
import com.rudyreyes.emuladorsql.modelo.InstruccionActualizar;
import com.rudyreyes.emuladorsql.modelo.archivos.util.CrearArchivos;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;

/**
 *
 * @author rudyo
 */
public class ActualizarFilasTablas {
    
    public static void actualizarFilasTablas(String datosCSV,InstruccionActualizar actualizar, JTextArea areaConsola){
        String[] lineas = datosCSV.split("\n");

        // Obtener nombres de columnas
        String[] columnas = lineas[0].split(",");
        
        if(actualizar.getCondiciones()!=null && !actualizar.getCondiciones().isEmpty()){
            //aqui iran las condiciones
            int tipoLogico= CondicionesUtil.isCondicionANDOR(actualizar.getOperadoresLogicos());
            ArrayList<Integer> filasFiltradas = null;
                if(tipoLogico == 1 || tipoLogico == 3){
                    filasFiltradas = filtrarDatosCondicionesAND(lineas, actualizar, columnas, areaConsola);
                    
                    
                }else if(tipoLogico == 2){
                    filasFiltradas = filtrarDatosCondicionesOR(lineas, actualizar, columnas, areaConsola);
                }else{
                    //SON DE LOS DOS TIPOS 
                    areaConsola.append("No se permite tener mas de dos operadores logicos diferentes en una consulta");
                    
                }
                
                if (tipoLogico != 0) {
                ArrayList<Integer> columnasSeleccionadas = new ArrayList<>();
                boolean seguir = seleccionarTablas(columnasSeleccionadas, columnas, actualizar, areaConsola);

                if (seguir) {
                    //AHORA ya tenemos localizadas las columnas, ahora solo falta modificar las filas
                    List<Object[]> datosList = new ArrayList<>();
                    filtrarDatosConCondiciones(lineas, columnas, filasFiltradas, columnasSeleccionadas, datosList, actualizar);
                    actualizarTabla(columnas, datosList);
                    String path = actualizar.getPath().replace("\"", "");
                    String csv = actualizarTabla(columnas, datosList);
                    
                    boolean realizado = CrearArchivos.actualizarArchivo(path, csv);
                    if (realizado) {
                        areaConsola.append("Los datos se han actualizado correctamente al archivo");
                    } else {
                        areaConsola.append("No se han podido actualizar correctamente los datos");
                    }
                }
            }

                
            
        
        }else{
            System.out.println("Entramos a actualizar sin condiciones");
            //aqui nada mas obtenemos las columnas
            ArrayList<Integer> columnasSeleccionadas = new ArrayList<>();
            boolean seguir = seleccionarTablas( columnasSeleccionadas,columnas, actualizar, areaConsola);
            
            if(seguir){
                //AHORA ya tenemos localizadas las columnas, ahora solo falta modificar las filas
                List<Object[]> datosList = new ArrayList<>();
                filtrarDatosSinCondiciones(lineas, columnas, columnasSeleccionadas, datosList, actualizar);
                actualizarTabla(columnas,datosList);
                String path = actualizar.getPath().replace("\"", "");
                String csv = actualizarTabla(columnas, datosList);
                
                boolean realizado = CrearArchivos.actualizarArchivo(path, csv);
                if (realizado) {
                    areaConsola.append("Los datos se han actualizado correctamente al archivo");
                } else {
                    areaConsola.append("No se han podido actualizar correctamente los datos");
                }
            }
        }
        
    }
    
    
    
    private static boolean seleccionarTablas(ArrayList<Integer> filtroColumnas,String [] columnas,InstruccionActualizar actualizar, JTextArea areaConsola){
        
        ArrayList<Columna> columnasSeleccionadas = actualizar.getColumnas();
        System.out.println("Cantidad de columnas "+columnasSeleccionadas.size());

        for (int i = columnasSeleccionadas.size() - 1; i >= 0; i--) {
            String columnaSeleccionada = columnasSeleccionadas.get(i).getColumna();
            //System.out.println(columnaSeleccionada);
            for (int j = 0; j < columnas.length; j++) {
                String columnaSinEspacios = columnas[j].trim();
                
                if (columnaSinEspacios.equals(columnaSeleccionada)) {
                    //System.out.println(columnas[j]+" "+columnaSeleccionada);
                    // Agregar la posición al filtroColumnas
                    filtroColumnas.add(j);
                    break; // Salir del bucle interno una vez encontrada la columna
                }
            }
        }
        if (columnasSeleccionadas.size() != filtroColumnas.size()) {
            areaConsola.append("Error: Existen " + (columnasSeleccionadas.size() - filtroColumnas.size()) + " columnas inexistentes, revise la consulta\n");
            return false;
        }else{
            return true;
        }

        

    }
    
    private static void filtrarDatosSinCondiciones(String[] lineas, String[] columnas, ArrayList<Integer> columnasFiltradas,List<Object[]> datosList, InstruccionActualizar actualizar) {
        ArrayList<Columna> obtenerCambios = actualizar.getColumnas();
        ArrayList<String> cambios = new ArrayList<>();
        for (int i = obtenerCambios.size() - 1; i >= 0; i--) {
            String ca = obtenerCambios.get(i).getValor();
            cambios.add(ca);
        }
        
        for (int i = 1; i < lineas.length; i++) {
            String[] valores = lineas[i].split(",");

            // Crear un array para los valores de las columnas filtradas
            Object[] valoresFiltrados = new Object[columnas.length];

            // Llenar el array de valores filtrados con los valores correspondientes
            for (int j = 0; j < columnasFiltradas.size(); j++) {
                int indiceColumna = columnasFiltradas.get(j);
                String valor = cambios.get(j).replace("\"", "");
                valores[indiceColumna]= valor;
            }
            datosList.add(valores);
        }
        
        
    }
    
    private static void filtrarDatosConCondiciones(String[] lineas, String[] columnas, ArrayList<Integer> filasFiltradas, ArrayList<Integer> columnasFiltradas,List<Object[]> datosList, InstruccionActualizar actualizar) {
        ArrayList<Columna> obtenerCambios = actualizar.getColumnas();
        ArrayList<String> cambios = new ArrayList<>();
        for (int i = obtenerCambios.size() - 1; i >= 0; i--) {
            String ca = obtenerCambios.get(i).getValor();
            cambios.add(ca);
        }
        
        for (int i = 1; i < lineas.length; i++) {
            String[] valores = lineas[i].split(",");


            // Llenar el array de valores filtrados con los valores correspondientes
            for (int j = 0; j < columnasFiltradas.size(); j++) {
                int indiceColumna = columnasFiltradas.get(j);
                if (filasFiltradas.contains(i)) {
                    String valor = cambios.get(j).replace("\"", "");
                    valores[indiceColumna] = valor;
                }

            }
            datosList.add(valores);
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
    
    private static ArrayList<Integer> filtrarDatosCondicionesAND(String[] lineas, InstruccionActualizar actualizar, String [] columnas, JTextArea areaConsola){
        ArrayList<Columna> condiciones = actualizar.getCondiciones();
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
    
    private static ArrayList<Integer> filtrarDatosCondicionesOR(String[] lineas, InstruccionActualizar actualizar, String [] columnas, JTextArea areaConsola){
        ArrayList<Columna> condiciones = actualizar.getCondiciones();
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
