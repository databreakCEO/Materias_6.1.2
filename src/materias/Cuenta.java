/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package materias;

import static java.awt.Frame.MAXIMIZED_HORIZ;
import static java.awt.Frame.MAXIMIZED_VERT;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;

/**
 *
 * @author Frodo
 */
public class Cuenta extends javax.swing.JFrame {

    /**
     * Creates new form Cuenta
     */
    DefaultListModel listaTotal, listaPendientes;
    MateriasFrame mf;
    String nombre;
    String carrera;
    Archivo a1;
    DefaultListModel auxiliar = new DefaultListModel();
    Object carreraID = 0;
    File carpeta;
    String[] listado;
    String directorio;
    boolean cargando = true;

    public Cuenta(String carrera, String nombre, String cuenta) {
        initComponents();
        try {
            try {
                this.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/rayo.jpg")).getImage());
            } catch (Exception e) {

            }
//        jLabImagenFondo.setSize(MAXIMIZED_HORIZ, MAXIMIZED_VERT);
            this.setTitle("Cuenta");

            switch (carrera) {

                case "Administracion":
                    carreraID = 7;
                    break;

                case "Electrica":
                    carreraID = 3;
                    break;

                case "Electronica":
                    carreraID = 6;
                    break;

                case "Energias Renovables":
                    carreraID = 9;
                    break;

                case "Gestion":
                    carreraID = 'Z';
                    break;

                case "Industrial":
                    carreraID = 2;
                    break;

                case "Mecanica":
                    carreraID = 4;
                    break;

                case "Mecatronica":
                    carreraID = 8;
                    break;

                case "Quimica":
                    carreraID = 5;
                    break;

                case "Sistemas":
                    carreraID = 1;
                    break;
                    

            }
            ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/recargar.jpg"));
            Image image = Imagenes.getScaledImage(icon.getImage(), this.jLabRecargar.getWidth(), this.jLabRecargar.getHeight());
            this.jLabRecargar.setIcon(new ImageIcon(image));

            this.setLocationRelativeTo(null);
            this.nombre = nombre;
            this.carrera = carrera;
            listaTotal = new DefaultListModel();
            listaPendientes = new DefaultListModel();
//        String direccion = "";
            a1 = new Archivo(nombre + "\\materiasEstado.txt");
            a1.crearLectura();
            jLabCarrera.setText(carrera);
            jLabCuenta.setText(cuenta);
//        jSpinner1.setValue(1);
            int con = 0;
            String line = a1.LeerLinea();
            listaTotal.addElement(line);
            boolean band = true;
            while (line != null) {
                band = true;
                for (int i = 0; i < listaTotal.size(); i++) {
                    if (line.equals(listaTotal.get(i)) || line.equals("RESIDENCIA f")) {
                        band = false;
                    }
                }
                if (band == true) {
                    listaTotal.addElement(line);
                    con++;
                }
                line = a1.LeerLinea();
            }

//            listaAprobadas.addElement(line);
            a1.CerrarLectura();
            auxiliar = listaTotal;
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            //Object [] o = new Object[2];
            for (int i = 0; i < listaTotal.size(); i++) {
                modelo.setRowCount(i + 1);
                String cadena = listaTotal.get(i).toString().substring(0, (listaTotal.get(i).toString().length() - 2));
                jTable1.setValueAt(cadena, i, 1);
                if (listaTotal.get(i).toString().charAt(listaTotal.get(i).toString().length() - 1) == "t".charAt(0)) {
                    //o[1]=true;
                    jTable1.setValueAt(true, i, 0);
                } else {
                    //o[1]=false;
                    jTable1.setValueAt(false, i, 0);
                }

            }
        } catch (Exception e) {

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jLabCuenta = new javax.swing.JLabel();
        jLabCarrera = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jCheckBoxSeleccionarTodo = new javax.swing.JCheckBox();
        jLabRecargar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabImagen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Nombre de materia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(1000);
        }

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton3.setText("Crear horarios");
        jButton3.setToolTipText("Oprima para empezar a crear sus pre-horarios");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 150, -1));

        jLabCuenta.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabCuenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 450, 28));

        jLabCarrera.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        getContentPane().add(jLabCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 370, 30));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Ver horarios guardados");
        jButton1.setToolTipText("Oprima para visualizar los horarios guardados.");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, -1, -1));

        jCheckBoxSeleccionarTodo.setText("Seleccionar todo");
        jCheckBoxSeleccionarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSeleccionarTodoActionPerformed(evt);
            }
        });
        getContentPane().add(jCheckBoxSeleccionarTodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabRecargar.setBackground(new java.awt.Color(0, 0, 0));
        jLabRecargar.setToolTipText("Oprima este botón para descargar los horarios de la página oficial.");
        jLabRecargar.setOpaque(true);
        jLabRecargar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabRecargarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabRecargarMouseEntered(evt);
            }
        });
        getContentPane().add(jLabRecargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 38, 38));

        jLabel1.setText("Marque las casillas de las materias que no usará para hacer los horarios.");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 420, -1));

        jLabImagen.setBackground(new java.awt.Color(255, 255, 255));
        jLabImagen.setOpaque(true);
        getContentPane().add(jLabImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void Guardar() {
        try {
            String nombreArchivo = jLabCuenta.getText();
            nombreArchivo = nombreArchivo.toUpperCase();
            a1.crearEscritura();

            for (int i = 0; i < listaTotal.size(); i++) {
                char b = 'f';
                if ((boolean) jTable1.getValueAt(i, 0) == true) {
                    b = 't';
                }
                String cadena = listaTotal.get(i).toString().substring(0, (listaTotal.get(i).toString().length() - 2));
                a1.EscribirLinea(cadena + "-" + b);
                if (i < listaTotal.size() - 1) {
                    a1.NuevaLinea();
                }
            }
            a1.CerrarEscritura();
        } catch (Exception e) {

        }
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            Guardar();
            listaPendientes.clear();

            for (int i = 0; i < jTable1.getRowCount(); i++) {
                if ((boolean) jTable1.getValueAt(i, 0) == false) {
                    String cadena = listaTotal.get(i).toString().substring(0, (listaTotal.get(i).toString().length() - 2));
                    listaPendientes.addElement(cadena);
                }
            }

            mf = new MateriasFrame(listaPendientes, carrera, nombre);
            mf.setVisible(true);
            mf.c = this;
            this.setVisible(false);
        } catch (Exception e) {

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        try {
            Loggin loggin = new Loggin();
            loggin.setVisible(true);
        } catch (Exception e) {

        }
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        try {
            Guardar();
        } catch (Exception e) {

        }
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            VisualizarHorarios vh = new VisualizarHorarios(carrera, nombre);
            vh.setVisible(true);
            vh.c = this;
            this.setVisible(false);
        } catch (Exception e) {

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBoxSeleccionarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSeleccionarTodoActionPerformed
        // TODO add your handling code here:
        try {
            if (jCheckBoxSeleccionarTodo.isSelected()) {
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    jTable1.setValueAt(true, i, 0);
                }
            } else {
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    jTable1.setValueAt(false, i, 0);
                }
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_jCheckBoxSeleccionarTodoActionPerformed

    private void jLabRecargarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabRecargarMouseClicked
        // TODO add your handling code here:
        try {
            try {
                CloseableHttpClient httpclient = HttpClients.createDefault();
                HttpGet httpGet = new HttpGet("http://apps.itlalaguna.edu.mx/servicios/academicos/horario_materias_2020/horarios.asp");
                try {
                    CloseableHttpResponse response1 = httpclient.execute(httpGet);

                    try {
                        HttpEntity entity1 = response1.getEntity();

                        EntityUtils.consume(entity1);
                    } finally {
                        response1.close();
                    }

                    HttpPost httpPost = new HttpPost("http://apps.itlalaguna.edu.mx/servicios/academicos/horario_materias_2020/horarios.asp");
                    List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                    nvps.add(new BasicNameValuePair("ESPECIALIDAD", carreraID + ""));
                    httpPost.setEntity(new UrlEncodedFormEntity(nvps));
                    CloseableHttpResponse response2 = httpclient.execute(httpPost);

                    try {
                        ArrayList<String> lineasAEscribir = new ArrayList<>();
                        HttpEntity entity2 = response2.getEntity();
                        InputStreamReader is = new InputStreamReader(entity2.getContent());
//                        File materias = new File("C:\\Users\\aniel\\Downloads\\horarios 2020.html");
//                        FileInputStream fis = new FileInputStream(materias);
//                        InputStreamReader is = new InputStreamReader(fis);
                            BufferedReader bf = new BufferedReader(new InputStreamReader(entity2.getContent()));
//                        BufferedReader bf = new BufferedReader(is);

                            File archivo = new File("Materias por carrera\\" + carrera + ".txt");
                            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
                            String cadena;
                            int conteo = 0;
                            int con = 0;
                            boolean primeraLinea = false;
                            boolean band = true;
                            String dato = "", linea = "";
                            while ((cadena = bf.readLine()) != null) {
                                for (int i = 0; i < cadena.length(); i++) {

                                    if (cadena.charAt(i) == '<') {
                                        band = true;
                                    }

                                    if (band == false) {
                                        if (dato.length() == 0 && cadena.charAt(i) == ' ') {
                                        } else if ('�' != cadena.charAt(i)) {
                                            dato += cadena.charAt(i);
                                        } else {
                                            dato += 'Ñ';
                                        }
                                    }
                                    if (cadena.charAt(i) == '>') {
                                        band = false;
                                    }

                                }
                                if (dato.length() > 0) {

                                    if (con > 11) {
                                        conteo++;
                                        if (dato.charAt(dato.length() - 1) != ' ') {
                                            dato += " ";
                                        }
                                        linea += (dato);

                                        if (conteo == 8) {
//                                        if (primeraLinea) {
//                                            bw.write("\n");
//                                        }
                                            lineasAEscribir.add(linea);
//                                        bw.write(linea);
                                            linea = "";
                                            primeraLinea = true;
                                            conteo = 0;
                                        }
                                    }
                                    con++;
                                }
                                dato = "";
                            }

                            if (lineasAEscribir.size() > 10) {
                                for (int i = 0; i < lineasAEscribir.size(); i++) {

                                    if (i < lineasAEscribir.size() - 1) {
                                        bw.write(lineasAEscribir.get(i) + "\n");
                                    } else {
                                        bw.write(lineasAEscribir.get(i));
                                    }
                                }
                            }

                            bw.close();
                            bf.close();
                            EntityUtils.consume(entity2);

                    } finally {
                        response2.close();

                    }

                    crearArchivos(carrera + ".txt");
                    Archivo a1 = new Archivo(carrera + "\\NombreMaterias.txt");
                    Archivo a2 = new Archivo(nombre + "\\materiasEstado.txt");
                    boolean band;
                    a2.crearLectura();
                    if (a1.crearLectura()) {

                        ArrayList<String> lista = new ArrayList<>();
                        ArrayList<String> lista2 = new ArrayList<>();
                        ArrayList<String> listaAGuardar = new ArrayList<>();
                        String line = a1.LeerLinea();
                        String line2 = a2.LeerLinea();
                        while (line2 != null) {

                            lista2.add(line2);
                            line2 = a2.LeerLinea();
                        }
                        band = true;
                        String materia;
                        boolean bandera = true;

                        while (line != null) {
                            bandera = true;
                            if (!line.equals("RESIDENCIA") && !line.equals("RESIDENCIA PROFESIONAL") && !line.equals("TUTORIA")) {
                                for (int i = 0; i < lista.size(); i++) {
                                    if (line.equals(lista.get(i))) {
                                        bandera = false;
                                        break;
                                    }
                                }
                                if (bandera) {
                                    lista.add(line);
                                }
                            }
                            line = a1.LeerLinea();

                        }

                        String linea = "";
                        for (int j = 0; j < lista.size(); j++) {
                            bandera = true;
                            for (int i = 0; i < lista2.size(); i++) {

                                String[] split = lista2.get(i).split("-");
                                materia = split[0];
                                if (materia.equals(lista.get(j))) {
                                    linea = lista2.get(i);
                                    bandera = false;
                                    break;
                                }
                            }
                            if (bandera) {
                                listaAGuardar.add(lista.get(j) + "-f");
                            } else {
                                listaAGuardar.add(linea);
                            }
                        }

                        a1.CerrarLectura();
                        a2.CerrarLectura();
                        a2.crearEscritura();

                        for (int j = 0; j < listaAGuardar.size(); j++) {
                            if (j > 0) {
                                a2.NuevaLinea();
                            }
                            a2.EscribirLinea(listaAGuardar.get(j));
                        }
                        a2.CerrarEscritura();
                    }

                    listaTotal = new DefaultListModel();
                    listaPendientes = new DefaultListModel();
                    a1 = new Archivo(nombre + "\\materiasEstado.txt");
                    a1.crearLectura();
                    int con = 0;
                    String line = a1.LeerLinea();
                    listaTotal.addElement(line);
                    band = true;
                    while (line != null) {
                        band = true;
                        for (int i = 0; i < listaTotal.size(); i++) {
                            if (line.equals(listaTotal.get(i)) || line.equals("RESIDENCIA f")) {
                                band = false;
                            }
                        }
                        if (band == true) {
                            listaTotal.addElement(line);
                            con++;
                        }
                        line = a1.LeerLinea();
                    }

//            listaAprobadas.addElement(line);
                    a1.CerrarLectura();
                    auxiliar = listaTotal;
                    DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
                    //Object [] o = new Object[2];
                    for (int i = 0; i < listaTotal.size(); i++) {
                        modelo.setRowCount(i + 1);
                        String cadena = listaTotal.get(i).toString().substring(0, (listaTotal.get(i).toString().length() - 2));
                        jTable1.setValueAt(cadena, i, 1);
                        if (listaTotal.get(i).toString().charAt(listaTotal.get(i).toString().length() - 1) == "t".charAt(0)) {
                            //o[1]=true;
                            jTable1.setValueAt(true, i, 0);
                        } else {
                            //o[1]=false;
                            jTable1.setValueAt(false, i, 0);
                        }

                    }
                    JOptionPane.showMessageDialog(this, "Horarios de materias actualizados.");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error en actualización, asegúrese de que tiene acceso a internet. Si el problema persiste pongase en contacto con el creador del programa.");
                }
            } catch (Exception e) {

            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_jLabRecargarMouseClicked

    private void jLabRecargarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabRecargarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabRecargarMouseEntered

    public void crearArchivos(String nombre) {
        try {
            Archivo archivo = new Archivo("Materias por carrera\\" + nombre);
            archivo.crearLectura();
            String line = archivo.LeerLinea();
            ArrayList<String> materias = new ArrayList<>();
            ArrayList<String> claves = new ArrayList<>();
            ArrayList<String> horarios = new ArrayList<>();
            ArrayList<String> maestros = new ArrayList<>();
            while (line != null) {
                String cadena = "";
                String cadenaTotal = "";
                String lineNueva = "";
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) != " ".charAt(0) && line.charAt(i) != "\t".charAt(0) && i < line.length()) {
                        if (((int) line.charAt(i)) < 47 || ((int) line.charAt(i)) > 58) {
                            cadena += line.charAt(i) + "";

                        } else {

                            break;
                        }
                    } else {
                        cadenaTotal += cadena + " ";
                        cadena = "";

                    }
                }
                materias.add(cadenaTotal);

                for (int i = cadenaTotal.length(); i < line.length(); i++) {
                    lineNueva += line.charAt(i);
                }
                line = lineNueva;
                cadena = "";
                cadenaTotal = "";
                lineNueva = "";
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) != " ".charAt(0) && line.charAt(i) != "\t".charAt(0)) {
                        cadena += line.charAt(i);
                    } else {
                        break;
                    }
                }
                claves.add(cadena);
                for (int i = cadena.length(); i < line.length(); i++) {
                    lineNueva += line.charAt(i);
                }
                line = lineNueva;
                cadena = "";
                cadenaTotal = "";
                lineNueva = "";
                int con = 0;
                for (int i = 1; i < line.length(); i++) {

                    if (line.charAt(i) == " ".charAt(0) || line.charAt(i) == "\t".charAt(0)) {
                        con++;
                        if (con == 5) {
                            cadenaTotal += cadena;
                            break;
                        } else {
                            cadena += line.charAt(i);
                        }
                    } else {
                        cadena += line.charAt(i);
                    }
                }

                horarios.add(cadena);
                for (int i = cadena.length() + 2; i < line.length(); i++) {
                    lineNueva += line.charAt(i);
                }
                line = lineNueva;
                maestros.add(line);

                line = archivo.LeerLinea();

            }
            String n = "";
            for (int i = 0; i < nombre.length() - 4; i++) {
                n += nombre.charAt(i) + "";
            }
            String materia = "";
            for (int i = 0; i < materias.size(); i++) {
                for (int j = 0; j < materias.get(i).length() - 1; j++) {
                    materia += materias.get(i).charAt(j);
                }
                materias.set(i, materia);
                materia = "";
            }
            nombre = n;
            Archivo NombreMaterias = new Archivo(n + "\\NombreMaterias.txt");
            Archivo ClaveMaterias = new Archivo(n + "\\ClaveMaterias.txt");
            Archivo HorarioMaterias = new Archivo(n + "\\HorarioMaterias.txt");
            Archivo MaestroMaterias = new Archivo(n + "\\MaestroMaterias.txt");
            NombreMaterias.crearEscritura();
            ClaveMaterias.crearEscritura();
            HorarioMaterias.crearEscritura();
            MaestroMaterias.crearEscritura();
            for (int i = 0; i < materias.size(); i++) {
                NombreMaterias.EscribirLinea(materias.get(i));
                ClaveMaterias.EscribirLinea(claves.get(i));
                HorarioMaterias.EscribirLinea(horarios.get(i));
                MaestroMaterias.EscribirLinea(maestros.get(i));
                if (i < materias.size() - 1) {
                    NombreMaterias.NuevaLinea();
                    ClaveMaterias.NuevaLinea();
                    HorarioMaterias.NuevaLinea();
                    MaestroMaterias.NuevaLinea();

                }
            }

            NombreMaterias.CerrarEscritura();
            ClaveMaterias.CerrarEscritura();
            HorarioMaterias.CerrarEscritura();
            MaestroMaterias.CerrarEscritura();
        } catch (Exception e) {

        }
    }

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
            java.util.logging.Logger.getLogger(Cuenta.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cuenta.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cuenta.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cuenta.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cuenta("", "", "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBoxSeleccionarTodo;
    private javax.swing.JLabel jLabCarrera;
    private javax.swing.JLabel jLabCuenta;
    private javax.swing.JLabel jLabImagen;
    private javax.swing.JLabel jLabRecargar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
