/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.emuladorsql.modelo.archivos;

import com.rudyreyes.emuladorsql.modelo.archivos.util.CrearArchivos;
import java.util.ArrayList;

/**
 *
 * @author rudyo
 */
public class Proyecto {
    String nombreProyecto;
    String pathProyecto;
    ArrayList<Directorio> directorios;
    ArrayList<Archivo> archivos;

    public Proyecto(String nombreProyecto, String pathProyecto) {
        this.nombreProyecto = nombreProyecto;
        this.pathProyecto = pathProyecto;
        this.directorios = new ArrayList<>();
        this.archivos = new ArrayList<>();
    }
    
    

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getPathProyecto() {
        return pathProyecto;
    }

    public void setPathProyecto(String pathProyecto) {
        this.pathProyecto = pathProyecto;
    }

    public ArrayList<Directorio> getDirectorios() {
        return directorios;
    }

    public void setDirectorios(ArrayList<Directorio> directorios) {
        this.directorios = directorios;
    }

    public ArrayList<Archivo> getArchivos() {
        return archivos;
    }

    public void setArchivos(ArrayList<Archivo> archivos) {
        this.archivos = archivos;
    }
    
    public boolean crearDirectorio(String nombre,String path){
        Directorio dr = new Directorio(nombre, path);
        this.directorios.add(new Directorio(nombre, path));
        return dr.crearDirectorio();
    }
    
    
    public boolean crearArchivo(String nombre, String path){
        Archivo ar = new Archivo(nombre,path);
        
        this.archivos.add(new Archivo(nombre, path));
        
        return ar.crearArchivo();
    }
    
    public void crearProyecto(){
        CrearArchivos.crearDirecctorio(pathProyecto);
    }
    
}
