/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.rudyreyes.emuladorsql;

import com.rudyreyes.emuladorsql.modelo.InstruccionSeleccionar;
import java.io.StringReader;
import java_cup.runtime.Symbol;

/**
 *
 * @author rudyo
 */
public class EmuladorSQL {

    public static void main(String[] args) throws Exception {
        String st =  "SELECCIONAR columna1 EN \"proyecto.archivo1\" ;";
        //String st =  "SELECCIONAR columna1 , columna2 EN \"proyecto.archivo1\" FILTRAR columna1 = \"hola\" AND columna2 = 5 ;";
        //String st = "INSERTAR EN \"proyecto.archivo1\" (columna1,columna2) VALORES (\"val1\",2);";
        //String st = "INSERTAR EN \"proyecto.archivo1\" VALORES (\"val1\",2);";
        //String st = "ACTUALIZAR EN \"proyecto.archivo1\" ASIGNAR columna1=\"Valor\",columna2=2 FILTRAR columna2=1 AND columna3>=5;";
        //String st = "ACTUALIZAR EN \"proyecto.archivo1\" ASIGNAR columna1=\"Valor\",columna2=2;";
        //String st = "ELIMINAR \n EN \"proyecto.archivo1\" \n ;";
        LexerSQL lexer = new LexerSQL(new StringReader(st));
        ParserSQL parser = new ParserSQL(lexer);
        
        
        try {
            Symbol symbol = parser.parse();
            if (symbol.value instanceof InstruccionSeleccionar) {
                InstruccionSeleccionar seleccion = (InstruccionSeleccionar) symbol.value;
                if (seleccion != null) {
                // Hacer algo con el objeto resultado...
                System.out.println("Se encontró una instrucción seleccionar");
                System.out.println("Path: " + seleccion.getPath());
                //System.out.println("Columnas: " + seleccion.getColumnas().get(0));
                //System.out.println("Condiciones: " + resultado.getCondiciones());
                // ...otros métodos y propiedades según tu implementación...
                }
            }else{
                System.out.println("No es instancia");
            }
            
            
        } catch (Exception e) {
            // Manejar excepciones si ocurren durante el análisis
            e.printStackTrace();
        }
        
        
    }
}
