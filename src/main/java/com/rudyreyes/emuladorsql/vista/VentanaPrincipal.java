/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.rudyreyes.emuladorsql.vista;

import com.rudyreyes.emuladorsql.modelo.InstruccionActualizar;
import com.rudyreyes.emuladorsql.modelo.InstruccionEliminar;
import com.rudyreyes.emuladorsql.modelo.InstruccionInsertar;
import com.rudyreyes.emuladorsql.modelo.InstruccionSeleccionar;
import com.rudyreyes.emuladorsql.modelo.archivos.Proyecto;
import com.rudyreyes.emuladorsql.modelo.archivos.util.CrearArchivos;
import com.rudyreyes.emuladorsql.vista.util.InsertarFilasTabla;
import com.rudyreyes.emuladorsql.vista.util.MiModeloTabla;
import com.rudyreyes.emuladorsql.vista.util.MostrarConsultasSeleccionar;
import com.rudyreyes.emuladorsql.vista.util.NodoDirectorio;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java_cup.runtime.Symbol;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.*;
/**
 *
 * @author rudyo
 */
public class VentanaPrincipal extends javax.swing.JFrame {
    Proyecto proyecto;
    private DefaultMutableTreeNode nodoProyecto;
    private DefaultTreeModel modeloArbol;

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        
        initComponents();
        nodoProyecto = new DefaultMutableTreeNode("Proyecto");
        modeloArbol = new DefaultTreeModel(nodoProyecto);
        arbolProyecto.setModel(modeloArbol);
        tooltipArbol();
        crearArchivos();
        verArchivos();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        nuevoProyecto = new javax.swing.JButton();
        scrollArbol = new javax.swing.JScrollPane();
        arbolProyecto = new javax.swing.JTree();
        jLabel1 = new javax.swing.JLabel();
        scrollAreaConsultas = new javax.swing.JScrollPane();
        areaConsultas = new javax.swing.JTextArea();
        seccionArchivos = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaLecturaArchivos = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        scrollConsola = new javax.swing.JScrollPane();
        areaConsola = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consultas SQL");

        nuevoProyecto.setText("Nuevo");
        nuevoProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoProyectoActionPerformed(evt);
            }
        });

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        arbolProyecto.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        scrollArbol.setViewportView(arbolProyecto);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel1.setText("Area de Consultas:");

        areaConsultas.setColumns(20);
        areaConsultas.setRows(5);
        areaConsultas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                areaConsultasKeyReleased(evt);
            }
        });
        scrollAreaConsultas.setViewportView(areaConsultas);

        areaLecturaArchivos.setColumns(20);
        areaLecturaArchivos.setRows(5);
        jScrollPane1.setViewportView(areaLecturaArchivos);

        seccionArchivos.addTab("tab1", jScrollPane1);

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel2.setText("Area de Consola:");

        areaConsola.setColumns(20);
        areaConsola.setRows(5);
        scrollConsola.setViewportView(areaConsola);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nuevoProyecto)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(scrollArbol, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(scrollAreaConsultas, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                            .addComponent(seccionArchivos)))
                    .addComponent(jLabel2)
                    .addComponent(scrollConsola))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nuevoProyecto)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(seccionArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(scrollAreaConsultas))
                    .addComponent(scrollArbol, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollConsola, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tooltipArbol(){
        arbolProyecto.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
             public void mouseMoved(MouseEvent evt) {
            // Obtener la información del nodo
            TreePath path = arbolProyecto.getPathForLocation(evt.getX(), evt.getY());
            if (path != null) {
                Object node = path.getLastPathComponent();
                if (node instanceof NodoDirectorio) {
                        NodoDirectorio nodoDirectorio = (NodoDirectorio) node;
                        arbolProyecto.setToolTipText(nodoDirectorio.getUbicacion());
                }
            }
        }
        });
    }
    
    private void crearArchivos(){
        arbolProyecto.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    TreePath path = arbolProyecto.getPathForLocation(e.getX(), e.getY());
                    if (path != null) {
                        arbolProyecto.setSelectionPath(path);
                        Object selectedNode = path.getLastPathComponent();
                        
                        if (selectedNode instanceof NodoDirectorio) {
                            mostrarMenuContextual(e);
                        }
                    }
                }
            }
        });
    }
    
    private void verArchivos(){
        arbolProyecto.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 1) {
                    TreePath path = arbolProyecto.getPathForLocation(e.getX(), e.getY());
                    if (path != null) {
                        arbolProyecto.setSelectionPath(path);
                        Object selectedNode = path.getLastPathComponent();
                        
                        if (selectedNode instanceof NodoDirectorio) {
                            NodoDirectorio nodoSeleccionado = (NodoDirectorio) selectedNode;
                            if (!nodoSeleccionado.isDirectorio()) {
                                String rutaArchivo = nodoSeleccionado.getUbicacion();
                                String contenidoArchivo = CrearArchivos.obtenerContenidoArchivo(rutaArchivo);
                                JTextArea areaContenido = new JTextArea(contenidoArchivo);
                                
                                seccionArchivos.addTab(nodoSeleccionado.getNombre(), new JScrollPane(areaContenido));
                            }
                        }
                    }
                }
            }
        });
    }
    
    private void mostrarMenuContextual(MouseEvent e) {
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem nuevoDirectorioItem = new JMenuItem("Nuevo Directorio");
        JMenuItem nuevoArchivoItem = new JMenuItem("Nuevo Archivo");

        nuevoDirectorioItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para crear un nuevo directorio
                TreePath selectedPath = arbolProyecto.getSelectionPath();
                NodoDirectorio selectedNode = (NodoDirectorio) selectedPath.getLastPathComponent();
                String ubicacionProyecto = selectedNode.getUbicacion();
                
                
                if (selectedNode.isDirectorio()) {
                    
                    String nombreDirectorio = JOptionPane.showInputDialog(null, "Ingrese el nombre del directorio:");

                    if (nombreDirectorio != null && !nombreDirectorio.isEmpty()) {

                        String pathDirectorio = ubicacionProyecto + File.separator + nombreDirectorio;

                        boolean creado = proyecto.crearDirectorio(nombreDirectorio, pathDirectorio);
                        
                        if (creado) {
                            NodoDirectorio nuevoNodo = new NodoDirectorio(nombreDirectorio, pathDirectorio);
                            selectedNode.add(nuevoNodo);

                            modeloArbol.reload();
                        }

                        

                    }
                }else{
                    JOptionPane.showMessageDialog(null, "No se puede crear un archivo en un archivo");
                }

                
            }
        });

        nuevoArchivoItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para crear un nuevo directorio
                TreePath selectedPath = arbolProyecto.getSelectionPath();
                NodoDirectorio nodoSeleccion = (NodoDirectorio) selectedPath.getLastPathComponent();
                String ubicacionProyecto = nodoSeleccion.getUbicacion();
                
                if (nodoSeleccion.isDirectorio()) {
                    String nombreArchivo = JOptionPane.showInputDialog(null, "Ingrese el nombre del archivo:");

                    if (nombreArchivo != null && !nombreArchivo.isEmpty()) {

                        String pathArchivo = ubicacionProyecto + File.separator + nombreArchivo+".csv";

                        boolean creado = proyecto.crearArchivo(nombreArchivo, pathArchivo);
                       
                        if (creado) {
                            NodoDirectorio nuevoNodo = new NodoDirectorio(nombreArchivo, pathArchivo);
                            nodoSeleccion.add(nuevoNodo);

                            modeloArbol.reload();
                        }
                        

                    }
                }else{
                    JOptionPane.showMessageDialog(null, "No se puede crear un archivo en un archivo");
                }
            }
        });

        popupMenu.add(nuevoDirectorioItem);
        popupMenu.add(nuevoArchivoItem);

        popupMenu.show(e.getComponent(), e.getX(), e.getY());
    }
    
    private void nuevoProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoProyectoActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int seleccion = fileChooser.showDialog(this, "Seleccionar Carpeta");

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            String nombreProyecto = JOptionPane.showInputDialog(this, "Ingrese el nombre del proyecto:");

            if (nombreProyecto != null && !nombreProyecto.isEmpty()) {
                String ubicacionProyecto = fileChooser.getSelectedFile().getPath();
                
                String pathProyecto = ubicacionProyecto+File.separator+nombreProyecto;
                
                System.out.println(pathProyecto);
                proyecto = new Proyecto(nombreProyecto, pathProyecto);
                
                proyecto.crearProyecto();
                NodoDirectorio nuevoNodo = new NodoDirectorio(nombreProyecto,pathProyecto);
                nodoProyecto.add(nuevoNodo);
                modeloArbol.reload();
                
            }
        }
    }//GEN-LAST:event_nuevoProyectoActionPerformed

    private void mostrarConsultasSeleccionar(String datosCSV,InstruccionSeleccionar seleccion ){
        
        JScrollPane scroll = MostrarConsultasSeleccionar.mostrarTablasSeleccionar(datosCSV, seleccion,areaConsola);
        
        if(scroll == null){
            System.out.println("Es null");
        }else{
        
        JDialog dialog = new JDialog((Frame) null, "Tabla desde CSV", true);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setLayout(new BorderLayout());
            dialog.add(scroll, BorderLayout.CENTER);
            dialog.setSize(400, 300);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }
    }
    
    private void areaConsultasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_areaConsultasKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && evt.getModifiersEx() == 0) {
            // Verificar si la consulta está completa al detectar un punto y coma
            String query = areaConsultas.getText().trim();
            if (query.endsWith(";")) {
                areaConsola.setText("");
                LexerSQL lexer = new LexerSQL(new StringReader(query));
                ParserSQL parser = new ParserSQL(lexer);

                try {
                    Symbol symbol = parser.parse();
                    ArrayList<Object> consultas = new ArrayList<>(parser.obtenerConsultas());
                    //C:\Users\rudyo\OneDrive\Escritorio\proyecto1\archivo1.csv
                    for (Object objetos : consultas) {
                        if (objetos instanceof InstruccionSeleccionar) {
                            InstruccionSeleccionar seleccion = (InstruccionSeleccionar) objetos;
                            if (seleccion != null) {
                                //seleccion.imprimirDatos();
                                String path = seleccion.getPath().replace("\"", "");
                                String extraerArchivo = CrearArchivos.obtenerContenidoArchivo(path);
                                mostrarConsultasSeleccionar(extraerArchivo, seleccion);

                                //SELECCIONAR columna1 , columna2 EN "C:\Users\rudyo\OneDrive\Escritorio\proyecto1\archivo1.csv" FILTRAR columna1 = "hola" AND columna2 = 5 AND columna2 > 5;
                                //SELECCIONAR * EN "C:\Users\rudyo\OneDrive\Escritorio\proyecto1\archivo1.csv" FILTRAR Salario>10;
                            }
                        } else if (objetos instanceof InstruccionInsertar) {
                            InstruccionInsertar insertar = (InstruccionInsertar) objetos;
                            if (insertar != null) {
                                insertar.mostrarDatos();
                                
                                String path = insertar.getPath().replace("\"", "");
                                String extraerArchivo = CrearArchivos.obtenerContenidoArchivo(path);
                                
                                InsertarFilasTabla.insertarFilasTabla(extraerArchivo, insertar, areaConsola);
                                //SELECCIONAR * EN "C:\Users\rudyo\OneDrive\Escritorio\proyecto1\archivo1.csv" FILTRAR Salario>10;
                                //INSERTAR EN "C:\Users\rudyo\OneDrive\Escritorio\proyecto1\archivo1.csv" (Nombre,Edad) VALORES ("Emerson",28);
                            }

                        } else if (objetos instanceof InstruccionActualizar) {
                            InstruccionActualizar actualizar = (InstruccionActualizar) objetos;
                            if (actualizar != null) {
                                System.out.println("\nConsultas para actualizar: ");
                                actualizar.mostrarDatos();
                            }

                        } else if (objetos instanceof InstruccionEliminar) {
                            InstruccionEliminar eliminar = (InstruccionEliminar) objetos;
                            if (eliminar != null) {
                                System.out.println("\nConsultas para Eliminar: ");
                                eliminar.mostrarDatos();
                            }

                        } else {
                            System.out.println("No es instancia");
                        }
                    }

                } catch (Exception e) {
                    // Manejar excepciones si ocurren durante el análisis
                    e.printStackTrace();
                }
            }
        }

    }//GEN-LAST:event_areaConsultasKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree arbolProyecto;
    private javax.swing.JTextArea areaConsola;
    private javax.swing.JTextArea areaConsultas;
    private javax.swing.JTextArea areaLecturaArchivos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nuevoProyecto;
    private javax.swing.JScrollPane scrollArbol;
    private javax.swing.JScrollPane scrollAreaConsultas;
    private javax.swing.JScrollPane scrollConsola;
    private javax.swing.JTabbedPane seccionArchivos;
    // End of variables declaration//GEN-END:variables
}
