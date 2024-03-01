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
        String st =  "PROYECTO";
        GenerarIde lexer = new GenerarIde(new StringReader(st));
        ParserIde parser = new ParserIde(lexer);
        
        System.out.println(parser.parse().value);
        
    }
}
