/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package materias;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import jroundborder.JLabelRound;

/**
 *
 * @author Daniel Gonzalez Cabrera
 */
public class Loggin extends javax.swing.JFrame {

    /**
     * Creates new form Loggin
     */
    int contador = 0;
    Cuenta c;
    File carpeta;
    String[] listado;
    String directorio;
    ImageIcon icon;
    Image image;
    JLabelRound labelCircular = new JLabelRound();
    boolean cambiar = false;
    RoundedBorder rb;

    public Loggin() {
        initComponents();
        try {
            this.setTitle("Inicio de sesión");
            try {
                this.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/rayo.jpg")).getImage());
            } catch (Exception e) {

            }//        jLabImagenFondo.setSize(MAXIMIZED_HORIZ, MAXIMIZED_VERT);
            int size = 100;
//            rb=new RoundedBorder(40);
//            jLabEntrar.setBorder(rb);
            Font font = jLabNuevaCuenta.getFont();
            Map attributes = new HashMap<>(font.getAttributes());
            attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            jLabNuevaCuenta.setFont(font.deriveFont(attributes));

            font = jLabEliminar.getFont();
            attributes = new HashMap<>(font.getAttributes());
            attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            jLabEliminar.setFont(font.deriveFont(attributes));
            
            
            labelCircular.setBounds(0, 0, size, size);
            icon = new ImageIcon(getClass().getResource("/Imagenes/ITL.png"));
            image = Imagenes.getScaledImage(icon.getImage(), this.labelCircular.getWidth(), this.labelCircular.getHeight());
            this.labelCircular.setIcon(new ImageIcon(image));
            jPanel1.add(labelCircular);

            icon = new ImageIcon(getClass().getResource("/Imagenes/cancelar.jpg"));
            image = Imagenes.getScaledImage(icon.getImage(), this.jLabCancelar.getWidth(), this.jLabCancelar.getHeight());
            this.jLabCancelar.setIcon(new ImageIcon(image));

            this.setLocationRelativeTo(null);
            this.jTextNick.setVisible(false);
            this.jLabel1.setVisible(false);
            jLabCancelar.setVisible(false);

            carpeta = new File("Materias por carrera");
            listado = carpeta.list();
            for (int i = 0; i < listado.length; i++) {
                crearArchivos(listado[i]);

            }
        } catch (Exception e) {

        }
    }

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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabEntrar = new javax.swing.JLabel();
        jLabEliminar = new javax.swing.JLabel();
        jLabNuevaCuenta = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabCancelar = new javax.swing.JLabel();
        jTextNick = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(340, 365));
        setResizable(false);
        setSize(new java.awt.Dimension(341, 320));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox1.setBackground(new java.awt.Color(204, 204, 255));
        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar cuenta" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 240, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Nombre de cuenta nueva");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 190, 30));

        jComboBox2.setBackground(new java.awt.Color(204, 204, 255));
        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar carrera", "Administracion", "Electronica", "Electrica", "Energias Renovables", "Gestion", "Industrial", "Mecatronica", "Mecanica", "Quimica", "Sistemas" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 240, 30));

        jLabEntrar.setBackground(new java.awt.Color(102, 102, 255));
        jLabEntrar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabEntrar.setForeground(new java.awt.Color(255, 255, 255));
        jLabEntrar.setText("          ENTRAR");
        jLabEntrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabEntrar.setOpaque(true);
        jLabEntrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabEntrarMouseClicked(evt);
            }
        });
        getContentPane().add(jLabEntrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 240, 40));

        jLabEliminar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabEliminar.setForeground(new java.awt.Color(0, 0, 204));
        jLabEliminar.setText("Eliminar Cuenta");
        jLabEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabEliminarMouseClicked(evt);
            }
        });
        getContentPane().add(jLabEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        jLabNuevaCuenta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabNuevaCuenta.setForeground(new java.awt.Color(0, 0, 204));
        jLabNuevaCuenta.setText("Nueva Cuenta");
        jLabNuevaCuenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabNuevaCuenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabNuevaCuentaMouseClicked(evt);
            }
        });
        getContentPane().add(jLabNuevaCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 120, 120));

        jLabCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.jpg"))); // NOI18N
        jLabCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabCancelarMouseClicked(evt);
            }
        });
        getContentPane().add(jLabCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 50, 50));

        jTextNick.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        getContentPane().add(jTextNick, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 240, 30));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setOpaque(true);
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 360));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void crearListado() {
        try {
            if (jComboBox2.getSelectedIndex() > 0) {
                if (jComboBox1.getSelectedIndex() > 0) {
                    c = new Cuenta(this.jComboBox2.getSelectedItem().toString(), this.jComboBox2.getSelectedItem().toString() + "\\Usuarios\\" + jComboBox1.getSelectedItem().toString(), jComboBox1.getSelectedItem().toString());

//                c.cuenta = this.jComboBox1.getSelectedItem().toString();
                    c.setVisible(true);
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Eliga una cuenta y carrera");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Eliga una cuenta y carrera");
            }
        } catch (Exception e) {

        }
    }
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
        try {
            jComboBox1.removeAllItems();
            jComboBox1.addItem("Seleccionar cuenta");
            carpeta = new File(jComboBox2.getSelectedItem().toString() + "\\Usuarios");
            listado = carpeta.list();
            if (listado == null || listado.length == 0) {
                return;
            } else {
                for (int i = 0; i < listado.length; i++) {
                    jComboBox1.addItem(listado[i]);
                }
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jLabEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabEliminarMouseClicked
        // TODO add your handling code here:
        try {

            if (jComboBox1.getSelectedIndex() > 0) {
                int opcion = JOptionPane.showConfirmDialog(this, "¿Está usted seguro de eliminar este usuario?");
                if (opcion == 0) {
                    File archivo = new File(jComboBox2.getSelectedItem().toString() + "\\Usuarios\\" + jComboBox1.getSelectedItem().toString());
                    String[] lista = archivo.list();
                    for (String lista1 : lista) {
                        File fichero = new File(jComboBox2.getSelectedItem().toString() + "\\Usuarios\\" + jComboBox1.getSelectedItem().toString() + "\\" + lista1);
                        fichero.delete();
                    }
                    archivo.delete();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Eliga una cuenta");
            }

            listado = carpeta.list();
            jComboBox1.removeAllItems();
            jComboBox1.addItem("Seleccionar cuenta");
            if (listado == null || listado.length == 0) {

            } else {
                for (int i = 0; i < listado.length; i++) {
                    jComboBox1.addItem(listado[i]);
                }
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_jLabEliminarMouseClicked

    private void jLabNuevaCuentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabNuevaCuentaMouseClicked
        // TODO add your handling code here:
        try {

            this.jTextNick.setVisible(true);
            this.jLabel1.setVisible(true);

            this.jLabNuevaCuenta.setVisible(false);
            this.jComboBox1.setVisible(false);

            jLabEntrar.setText("       REGISTRAR");
            jLabEliminar.setVisible(false);
            jLabCancelar.setVisible(true);
            cambiar = true;

        } catch (Exception e) {

        }
    }//GEN-LAST:event_jLabNuevaCuentaMouseClicked

    private void jLabEntrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabEntrarMouseClicked
        // TODO add your handling code here:
        try {
            if (cambiar) {
                boolean band = false;
                if (jComboBox2.getSelectedIndex() > 0 && (jTextNick.getText().length() > 0 && !jTextNick.getText().equals(" "))) {
                    String nombreArchivo = jTextNick.getText();
                    nombreArchivo = nombreArchivo.toUpperCase();
                    carpeta = new File(jComboBox2.getSelectedItem().toString() + "\\Usuarios");
//                carpeta = new File(PathComplemento.substring(0, PathComplemento.length()-5)+jComboBox2.getSelectedItem().toString() + "\\Usuarios");

                    try {
                        listado = carpeta.list();
                        if (listado.length > 0) {
                            for (int i = 0; i < listado.length; i++) {
                                if (nombreArchivo.equals(listado[i])) {
                                    band = true;
                                    JOptionPane.showMessageDialog(this, "Usuario ya creado");
                                    break;
                                }
                            }
                        }
                    } catch (Exception e) {

                    }
                    if (band == false) {

                        try {

                            Archivo a1 = new Archivo(jComboBox2.getSelectedItem().toString() + "\\NombreMaterias.txt");
                            if (a1.crearLectura()) {

                                File directorio = new File(jComboBox2.getSelectedItem().toString() + "\\Usuarios\\" + nombreArchivo);
                                directorio.mkdir();
                                directorio.createNewFile();
                                Archivo a2 = new Archivo(directorio + "\\materiasEstado.txt");
                                a2.crearEscritura();
                                Archivo guardar = new Archivo(directorio + "\\horariosGuardados.txt");
                                guardar.crearEscritura();
                                ArrayList<String> lista = new ArrayList<>();
                                String line = a1.LeerLinea();
                                band = true;
                                while (line != null) {
                                    band = true;
                                    for (int j = 0; j < lista.size(); j++) {
                                        if (line.equals(lista.get(j)) || line.equals("RESIDENCIA") || line.equals("RESIDENCIA PROFESIONAL") || line.equals("TUTORIA")) {
                                            band = false;
                                        }
                                    }
                                    if (band == true) {
                                        lista.add(line);
                                    }
                                    line = a1.LeerLinea();
                                }

                                for (int j = 0; j < lista.size(); j++) {
                                    a2.EscribirLinea(lista.get(j) + "-f");
                                    a2.NuevaLinea();
                                }
                                a1.CerrarLectura();
                                a2.CerrarEscritura();
                                guardar.CerrarEscritura();
                                jLabNuevaCuenta.setText("Nueva Cuenta");

                                jComboBox1.removeAllItems();
                                jComboBox1.addItem("Seleccionar cuenta");
                                listado = carpeta.list();
                                if (listado == null || listado.length == 0) {
                                    return;
                                } else {
                                    for (int i = 0; i < listado.length; i++) {
                                        jComboBox1.addItem(listado[i]);
                                    }
                                }
                                this.jTextNick.setVisible(false);
                                this.jLabel1.setVisible(false);

                                this.jLabEntrar.setVisible(true);
                                this.jComboBox1.setVisible(true);
                                jLabEliminar.setVisible(true);
                                jLabNuevaCuenta.setVisible(true);
                                jLabCancelar.setVisible(false);
                                cambiar = false;
                            } else {
                                JOptionPane.showMessageDialog(this, "Lo sentimos, aun no existen horarios para esta carrera");
                                this.jTextNick.setText("");
                            }
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(this, "Lo sentimos, aun no existen horarios para esta carrera");
                            this.jTextNick.setText("");

                        }
                    }

                } else {
                    contador--;
                    JOptionPane.showMessageDialog(this, "Elija una carrera y escriba un nombre de cuenta");
                }
            } else {
                crearListado();
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_jLabEntrarMouseClicked

    private void jLabCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabCancelarMouseClicked
        // TODO add your handling code here:
        try {
            this.jTextNick.setVisible(false);
            this.jLabel1.setVisible(false);

            this.jLabEntrar.setVisible(true);
            this.jComboBox1.setVisible(true);
            jLabNuevaCuenta.setText("Nueva Cuenta");
            jLabEntrar.setText("          ENTRAR");
            jLabNuevaCuenta.setVisible(true);
            jLabEliminar.setVisible(true);
            jLabCancelar.setVisible(false);
            contador++;
            cambiar = false;
            this.jTextNick.setText("");
        } catch (Exception e) {

        }
    }//GEN-LAST:event_jLabCancelarMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        Main I = new Main();
//        I.setVisible(true);
//        try {
//            Thread.sleep(3500);
//        } catch (Exception e) {
//
//        }
//        I.setVisible(false);
//        I.dispose();
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Loggin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Loggin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Loggin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Loggin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new Loggin().setVisible(true);
            }
        }
        );

    }

    class RoundedBorder implements Border {

        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);

        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabCancelar;
    private javax.swing.JLabel jLabEliminar;
    private javax.swing.JLabel jLabEntrar;
    private javax.swing.JLabel jLabNuevaCuenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextNick;
    // End of variables declaration//GEN-END:variables
}
