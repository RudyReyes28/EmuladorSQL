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
    
    public void crearDirectorio(String nombre,String path){
        Directorio dr = new Directorio(nombre, path);
        dr.crearDirectorio();
        this.directorios.add(new Directorio(dr.getNombreDirectorio(), dr.getPathDirectorio()));
    }
    
    
    public void crearArchivo(String nombre, String path){
        Archivo ar = new Archivo(nombre,path);
        ar.crearArchivo();
        this.archivos.add(new Archivo(nombre, path));
    }
    
    public void crearProyecto(){
        CrearArchivos.crearDirecctorio(pathProyecto);
    }
    
}
