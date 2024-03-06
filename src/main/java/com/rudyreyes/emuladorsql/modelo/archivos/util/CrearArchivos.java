/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.emuladorsql.modelo.archivos.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JOptionPane;

/**
 *
 * @author rudyo
 */
public class CrearArchivos {

    public static boolean crearDirecctorio(String rutaAbsoluta){
        

        // Crear un objeto File con la ruta del directorio
        File directorio = new File(rutaAbsoluta);

        // Verificar si el directorio ya existe
        if (!directorio.exists()) {
            // Crear el directorio
            boolean creado = directorio.mkdir();

            if (creado) {
                JOptionPane.showMessageDialog(null, "Directorio Creado con exito");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No se puede crear el directorio");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "El directorio ya existe");
            return false;
        }
    }
    
    public static boolean crearArchivo(String rutaAbsoluta){
        

        // Crear un objeto File
        File archivo = new File(rutaAbsoluta);

        try {
            // Verificar si el archivo ya existe
            if (archivo.createNewFile()) {
                JOptionPane.showMessageDialog(null, "El archivo se ha creado con exito");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "El archivo ya existe");
                return false;
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se pudo crear el archivo");
            return false;
        }
    }
    
    public static String obtenerContenidoArchivo(String rutaAbsoluta){
         String contenido = "";
        try {
            Path path = Paths.get(rutaAbsoluta);
            contenido = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades
            JOptionPane.showMessageDialog(null,"No se pudo leer el archivo");
        }
        return contenido;
    }
    
    public static boolean insertarContenidoArchivo(String rutaAbsoluta, String contenidoCSV){
        boolean realizado = false;
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaAbsoluta, true))) {
            // Escribir la fila CSV en el archivo
            writer.newLine(); // Agregar una nueva línea para la siguiente entrada
            writer.write(contenidoCSV);
           
            realizado = true;
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Error al insertar");
        }
        return realizado;
    }
    
    public static boolean actualizarArchivo(String rutaAbsoluta, String contenidoCSV){
        boolean realizado = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaAbsoluta))) {
            // Sobrescribir el contenido del archivo con el nuevo contenido CSV
            writer.write(contenidoCSV);
            realizado = true;
        } catch (IOException e) {
            //e.printStackTrace();  // Manejo básico de excepciones
            System.out.println("Error al actualizar");
        }
        
        return realizado;
    }
}
