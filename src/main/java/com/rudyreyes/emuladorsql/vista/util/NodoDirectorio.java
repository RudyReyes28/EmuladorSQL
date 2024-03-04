/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.emuladorsql.vista.util;

import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author rudyo
 */
public class NodoDirectorio extends DefaultMutableTreeNode{
    
    private final String path;
    
    private final String nombre;

    public NodoDirectorio(String nombre, String path) {
            super(nombre);
            this.nombre = nombre;
            this.path = path;
    }

        
    public String getUbicacion() {
           return path;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public boolean isDirectorio() {
        File file = new File(path);
        if (file.isDirectory()) {
            // Es un directorio
            return true;
        } 
        
        return false;
    }

}
