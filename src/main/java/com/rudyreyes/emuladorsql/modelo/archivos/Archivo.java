/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.emuladorsql.modelo.archivos;

import com.rudyreyes.emuladorsql.modelo.archivos.util.CrearArchivos;


/**
 *
 * @author rudyo
 */
public class Archivo {
    String nombreArchivo;
    String pathArchivo;

    public Archivo(String nombreArchivo, String pathArchivo) {
        this.nombreArchivo = nombreArchivo;
        this.pathArchivo = pathArchivo;
    }

    public Archivo() {
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getPathArchivo() {
        return pathArchivo;
    }

    public void setPathArchivo(String pathArchivo) {
        this.pathArchivo = pathArchivo;
    }
   
    public boolean crearArchivo(){
        return CrearArchivos.crearArchivo(pathArchivo);
    }
}
