/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.rudyreyes.emuladorsql;

import java.io.StringReader;

/**
 *
 * @author rudyo
 */
public class EmuladorSQL {

    public static void main(String[] args) throws Exception {
        //String st =  "SELECCIONAR columna1 EN \"proyecto.archivo1\" ;";
        //String st =  "SELECCIONAR columna1 , columna2 EN \"proyecto.archivo1\" FILTRAR columna1 = \"hola\" AND columna2 = 5 ;";
        //String st = "INSERTAR EN \"proyecto.archivo1\" (columna1,columna2) VALORES (\"val1\",2);";
        //String st = "INSERTAR EN \"proyecto.archivo1\" VALORES (\"val1\",2);";
        //String st = "ACTUALIZAR EN \"proyecto.archivo1\" ASIGNAR columna1=\"Valor\",columna2=2 FILTRAR columna2=1 AND columna3>=5;";
        //String st = "ACTUALIZAR EN \"proyecto.archivo1\" ASIGNAR columna1=\"Valor\",columna2=2;";
        String st = "ELIMINAR \n EN \"proyecto.archivo1\" \n ;";
        LexerSQL lexer = new LexerSQL(new StringReader(st));
        ParserSQL parser = new ParserSQL(lexer);
        
        parser.parse();
        
        
    }
}
