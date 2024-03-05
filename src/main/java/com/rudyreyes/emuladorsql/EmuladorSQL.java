/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.rudyreyes.emuladorsql;

import com.rudyreyes.emuladorsql.modelo.*;
import java.io.StringReader;
import java.util.ArrayList;
import java_cup.runtime.Symbol;

/**
 *
 * @author rudyo
 */
public class EmuladorSQL {

    public static void EmuladorSQL(){
        //String st =  "SELECCIONAR columna1, columna2, columna3 EN \"proyecto.archivo1\" ;";
        String st1 =  "SELECCIONAR columna1 , columna2 EN \"proyecto.archivo1\" FILTRAR columna1 = \"hola\" AND columna2 = 5 AND columna2 > 5;";
        String st = "INSERTAR EN \"proyecto.archivo1\" (columna1,columna2) VALORES (\"val1\",2);";
        //String st = "INSERTAR EN \"proyecto.archivo1\" VALORES (\"val1\",2);";
        //String st = "ACTUALIZAR EN \"proyecto.archivo1\" ASIGNAR columna1=\"Valor\",columna2=2 FILTRAR columna2=1 AND columna3>=5;";
        String st2 = "ACTUALIZAR EN \"proyecto.archivo1\" ASIGNAR columna1=\"Valor\",columna2=2;";
        String st4 = "ELIMINAR \n EN \"proyecto.archivo1\" FILTRAR columna1 = \"hola\" AND columna2 = 5 AND columna2 > 5 ;";
        String todo = st1+"\n"+st+st2+st4;
        //LexerSQL lexer = new LexerSQL(new StringReader(todo));
        //ParserSQL parser = new ParserSQL(lexer);
        
        /*
        try {
            Symbol symbol = parser.parse();
            //ArrayList<Object> consultas = new ArrayList<>(parser.obtenerConsultas());
            
            for(Object objetos: consultas){
                if (objetos instanceof InstruccionSeleccionar) {
                    InstruccionSeleccionar seleccion = (InstruccionSeleccionar) objetos;
                    if (seleccion != null) {
                        System.out.println("\nConsultas para seleccionar :");
                        seleccion.imprimirDatos();
                    }
                } else if (objetos instanceof InstruccionInsertar) {
                    InstruccionInsertar insertar = (InstruccionInsertar) objetos;
                    if (insertar != null) {
                        System.out.println("\nConsultas para insertar: ");
                        insertar.mostrarDatos();
                    }

                } else if (objetos instanceof InstruccionActualizar) {
                    InstruccionActualizar actualizar = (InstruccionActualizar) objetos;
                    if (actualizar != null) {
                        System.out.println("\nConsultas para actualizar: ");
                        actualizar.mostrarDatos();
                    }

                } else if (objetos instanceof InstruccionEliminar) {
                    InstruccionEliminar eliminar = (InstruccionEliminar) objetos;
                    if (eliminar != null) {
                        System.out.println("\nConsultas para Eliminar: ");
                        eliminar.mostrarDatos();
                    }

                } else {
                    System.out.println("No es instancia");
                }
            }
            /*if (symbol.value instanceof InstruccionSeleccionar) {
                InstruccionSeleccionar seleccion = (InstruccionSeleccionar) symbol.value;
                if (seleccion != null) {
                    seleccion.imprimirDatos();
                }
            }else if(symbol.value instanceof InstruccionInsertar){
                InstruccionInsertar insertar = (InstruccionInsertar) symbol.value;
                if(insertar != null){
                    insertar.mostrarDatos();
                }
            
            }else{
                System.out.println("No es instancia");
            }*/
            
         /*   
        } catch (Exception e) {
            // Manejar excepciones si ocurren durante el análisis
            e.printStackTrace();
        }
        */
        
    }
}
