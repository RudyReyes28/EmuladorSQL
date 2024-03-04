/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.emuladorsql.modelo.archivos.util;

import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author rudyo
 */
public class CrearArchivos {

    public static void crearDirecctorio(String rutaAbsoluta){
        

        // Crear un objeto File con la ruta del directorio
        File directorio = new File(rutaAbsoluta);

        // Verificar si el directorio ya existe
        if (!directorio.exists()) {
            // Crear el directorio
            boolean creado = directorio.mkdir();

            if (creado) {
                JOptionPane.showMessageDialog(null, "Directorio Creado con exito");
            } else {
                JOptionPane.showMessageDialog(null, "No se puede crear el directorio");
            }
        } else {
            JOptionPane.showMessageDialog(null, "El directorio ya existe");
        }
    }
    
    public static void crearArchivo(String rutaAbsoluta){
        

        // Crear un objeto File
        File archivo = new File(rutaAbsoluta);

        try {
            // Verificar si el archivo ya existe
            if (archivo.createNewFile()) {
                JOptionPane.showMessageDialog(null, "El archivo creado con exito");
            } else {
                JOptionPane.showMessageDialog(null, "El archivo ya existe");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se pudo crear el directorio");
        }
    }
}
