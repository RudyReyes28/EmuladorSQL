/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.emuladorsql.vista.util;

import com.rudyreyes.emuladorsql.modelo.Columna;
import com.rudyreyes.emuladorsql.modelo.InstruccionSeleccionar;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

/**
 *
 * @author rudyo
 */
public class MostrarConsultasSeleccionar {
    
    public static JScrollPane mostrarTablasSeleccionar(String datosCSV,InstruccionSeleccionar seleccion, JTextArea areaConsola){
        JScrollPane scrollPane = null;
        String[] lineas = datosCSV.split("\n");

        // Obtener nombres de columnas
        String[] columnas = lineas[0].split(",");

        // Obtener datos
        
        
        //YA OBTENEMOS LAS COLUMNAS Y DATOS AHORA SOLO FALTA OBTENER SOLO TABLAS O CONDICIONES
        if (seleccion.getSeleccionarTodo().equals("*")) {
            //SELECCIONAMOS TODAS LAS TABLAS
            if(seleccion.getCondiciones()!=null && !seleccion.getCondiciones().isEmpty()){
                //PUES DEBEMOS FILTRAR APLICADO CONDICIONES
                //PEDIR LAS FILAS
                List<Object[]> datosList = new ArrayList<>();
                ArrayList<Integer> filasFiltradas = null;
                int tipoLogico= CondicionesUtil.isCondicionANDOR(seleccion.getOperadoresLogicos());
                
                if(tipoLogico == 1 || tipoLogico == 3){
                    filasFiltradas = filtrarDatosCondicionesAND(lineas, seleccion, columnas, areaConsola);
                }else if(tipoLogico == 2){
                    filasFiltradas = filtrarDatosCondicionesOR(lineas, seleccion, columnas, areaConsola);
                }else{
                    //SON DE LOS DOS TIPOS 
                    areaConsola.append("No se permite tener mas de dos operadores logicos diferentes en una consulta");
                    filasFiltradas = new ArrayList<>();
                }
                
                for (int i = 0; i < filasFiltradas.size(); i++) {
                    String[] valores = lineas[filasFiltradas.get(i)].split(",");
                    datosList.add(valores);
                }
                
                Object[][] datos = obtenerTabla(datosList, columnas);
                MiModeloTabla modeloTabla = new MiModeloTabla(columnas, datos);

                // Crear la tabla
                JTable tabla = new JTable(modeloTabla);
                // Crear el JScrollPane con la tabla
                 scrollPane = new JScrollPane(tabla);
                return scrollPane;
            }else{
                
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
            ArrayList<Integer> columnasSeleccionadas = seleccionarTablas(columnas, seleccion, areaConsola);
            List<Object[]> datosList = new ArrayList<>();
            String [] nuevasColumnas = obtenerColumnasSeleccionadas(columnas, columnasSeleccionadas);
            
            if(seleccion.getCondiciones()!=null && !seleccion.getCondiciones().isEmpty() ){
                //PUES DEBEMOS FILTRAR APLICADO CONDICIONES
                int tipoLogico= CondicionesUtil.isCondicionANDOR(seleccion.getOperadoresLogicos());
                 ArrayList<Integer> filasFiltradas;
                if(tipoLogico == 1 || tipoLogico == 3){
                    filasFiltradas = filtrarDatosCondicionesAND(lineas, seleccion, columnas, areaConsola);
                }else if(tipoLogico == 2){
                    filasFiltradas = filtrarDatosCondicionesOR(lineas, seleccion, columnas, areaConsola);
                }else{
                    //SON DE LOS DOS TIPOS 
                    areaConsola.append("No se permite tener mas de dos operadores logicos diferentes en una consulta");
                    filasFiltradas = new ArrayList<>();
                }
                //AHORA DEBEMOS CREAR UN METODO QUE ME AGREGUE LOS DATOS 
                filtrarDatosConCondiciones(lineas, filasFiltradas, columnasSeleccionadas, datosList);
                Object[][] datos = obtenerTabla(datosList, nuevasColumnas);
                MiModeloTabla modeloTabla = new MiModeloTabla(nuevasColumnas, datos);

                // Crear la tabla
                JTable tabla = new JTable(modeloTabla);
                // Crear el JScrollPane con la tabla
                 scrollPane = new JScrollPane(tabla);
                return scrollPane;
            }else{
                //SOLO FILTRAMOS DE MANERA NORMAL
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
    }
    
    private static ArrayList<Integer> seleccionarTablas(String [] columnas,InstruccionSeleccionar seleccion, JTextArea areaConsola){
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
        if (columnasSeleccionadas.size() != filtroColumnas.size()) {
            areaConsola.append("Error: Existen "+(columnasSeleccionadas.size() - filtroColumnas.size())+" columnas inexistentes, revise la consulta\n");
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
    
    private static void filtrarDatosConCondiciones(String[] lineas, ArrayList<Integer> filasFiltradas, ArrayList<Integer> columnasFiltradas,List<Object[]> datosList) {
        
        for (int i = 1; i < filasFiltradas.size(); i++) {
            String[] valores = lineas[filasFiltradas.get(i)].split(",");

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
    
    private static ArrayList<Integer> filtrarDatosCondicionesAND(String[] lineas, InstruccionSeleccionar seleccion, String [] columnas, JTextArea areaConsola){
        ArrayList<Columna> condiciones = seleccion.getCondiciones();
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
    
      private static ArrayList<Integer> filtrarDatosCondicionesOR(String[] lineas, InstruccionSeleccionar seleccion, String [] columnas, JTextArea areaConsola){
        ArrayList<Columna> condiciones = seleccion.getCondiciones();
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
