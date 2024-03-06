/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.emuladorsql.vista.util;

import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 *
 * @author rudyo
 */
public class CondicionesUtil {
    
    public static int indiceColumna(String nombreColumna, String [] columnas){
        int indice = -1;
        for (int j = 0; j < columnas.length; j++) {
            String columnaSinEspacios = columnas[j].trim();
            if (columnaSinEspacios.equals(nombreColumna)) {
                // Agregar la posición al filtroColumnas
                indice = j;
                break; // Salir del bucle interno una vez encontrada la columna
            }
        }
        
        return indice;
    }
    
     public static boolean compararElementos(String elemento1, String elemento2, String operador, JTextArea areaConsola) {
          if ((elemento2.startsWith("\"") && elemento2.endsWith("\"")) | 
                  (elemento1.startsWith("\"") && elemento1.endsWith("\""))) {
                elemento1 = elemento1.replace("\"", "");
                elemento2 = elemento2.replace("\"", "");
              switch (operador) {
                  case "=":
                      return elemento1.equals(elemento2);
                  case "<>":
                      return !elemento1.equals(elemento2);
                  // Agrega más casos según tus necesidades (por ejemplo, "<", ">", "<=", ">=")
                  default:
                      //throw new IllegalArgumentException("Operador no válido: " + operador);
                      areaConsola.append("Error: Existen operadores relacionales no validos, revise la consulta\n");
                      return false;
              }
         } else {
             try {
                 double num1 = Double.parseDouble(elemento1);
                 double num2 = Double.parseDouble(elemento2);

                 switch (operador) {
                     case "<":
                         return num1 < num2;
                     case ">":
                         return num1 > num2;
                     case "<=":
                         return num1 <= num2;
                     case ">=":
                         return num1 >= num2;
                     case "=":
                         return num1 == num2;
                     case "!=":
                         return num1 != num2;
                     default:
                         //throw new IllegalArgumentException("Operador no válido: " + operador);
                         areaConsola.append("Error: Existen operadores relacionales no validos, revise la consulta\n");
                         return false;
                 }
             } catch (NumberFormatException e) {
                 areaConsola.append("Error: No se puede resolver una condicion\n");
                 return false;
             }
         }
         
    }
     
    public static int isCondicionANDOR(ArrayList<String> operadoresLogicos) {
        int logico = 0; //una mezcla de condiciones  1 AND   2 OR 
        // Verificar si todos los operadores son AND
        if (operadoresLogicos.isEmpty()) {
            logico = 3; // Solo hay una condición
        } else {
            // Verificar si todos los operadores son AND
            boolean todosAND = true;
            for (String op : operadoresLogicos) {
                if (!op.equals("AND")) {
                    todosAND = false;
                    break;
                }
            }

            // Verificar si todos los operadores son OR
            boolean todosOR = true;
            for (String op : operadoresLogicos) {
                if (!op.equals("OR")) {
                    todosOR = false;
                    break;
                }
            }

            if (todosAND) {
                logico = 1;
            } else if (todosOR) {
                logico = 2;
            }
        }

        return logico;
    }

}
