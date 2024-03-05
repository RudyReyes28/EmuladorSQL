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
public class Directorio {
    String nombreDirectorio;
    String pathDirectorio;

    public Directorio(String nombreDirectorio, String pathDirectorio) {
        this.nombreDirectorio = nombreDirectorio;
        this.pathDirectorio = pathDirectorio;
    }

    public Directorio() {
    }

    public String getNombreDirectorio() {
        return nombreDirectorio;
    }

    public void setNombreDirectorio(String nombreDirectorio) {
        this.nombreDirectorio = nombreDirectorio;
    }

    public String getPathDirectorio() {
        return pathDirectorio;
    }

    public void setPathDirectorio(String pathDirectorio) {
        this.pathDirectorio = pathDirectorio;
    }
    
    public boolean crearDirectorio(){
        return CrearArchivos.crearDirecctorio(pathDirectorio);
    }
}
