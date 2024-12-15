/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.vistas.admin;

import com.mycompany.dao.WebServiceManager;
import com.mycompany.domainclasses.Publicacion;
import com.mycompany.domainclasses.Usuario;
import com.mycompany.vistas.usuarios.HomeUsuarios;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import jnafilechooser.api.JnaFileChooser;


public class CreatePublicacionForm extends javax.swing.JFrame {

    private int idReferencia;
    private Usuario usuarioLogueado;
    private HomeUsuarios homeUsuarios;
    
    private byte[] fotoCargadaBytes;
    /**
     * Creates new form CreatePublicacionForm
     */
    public CreatePublicacionForm() {
        initComponents();
    }
    
    public CreatePublicacionForm(Usuario usuarioLogueado, HomeUsuarios homeUsuarios) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.usuarioLogueado = usuarioLogueado;
        this.homeUsuarios = homeUsuarios;
    }
    
    public CreatePublicacionForm(Usuario usuarioLogueado, HomeUsuarios homeUsuarios, int idReferencia) {
        initComponents();
         this.setLocationRelativeTo(null);
        this.idReferencia = idReferencia;
        this.usuarioLogueado = usuarioLogueado;
        this.homeUsuarios = homeUsuarios;
        
        
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
        containerTittle = new com.mycompany.vistas.RoundedPanel(20);
        jLabel1 = new javax.swing.JLabel();
        fieldTitulo = new RoundedTextField(20);
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fieldDescription = new RoundedTextField(20);
        jLabel4 = new javax.swing.JLabel();
        btnSelectFoto = new javax.swing.JButton();
        panelFoto = new org.edisoncor.gui.panel.PanelImage();
        btnPublicar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        containerTittle.setBackground(new java.awt.Color(255, 226, 198));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Agregar publicacion");

        javax.swing.GroupLayout containerTittleLayout = new javax.swing.GroupLayout(containerTittle);
        containerTittle.setLayout(containerTittleLayout);
        containerTittleLayout.setHorizontalGroup(
            containerTittleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        containerTittleLayout.setVerticalGroup(
            containerTittleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerTittleLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        fieldTitulo.setBackground(new java.awt.Color(237, 237, 237));
        fieldTitulo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        fieldTitulo.setForeground(new java.awt.Color(102, 51, 0));
        fieldTitulo.setText("Escribe el titulo...");
        fieldTitulo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 51, 0));
        jLabel2.setText("Agrega una foto del conjunto");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 51, 0));
        jLabel3.setText("Titulo del conjunto");

        fieldDescription.setBackground(new java.awt.Color(237, 237, 237));
        fieldDescription.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        fieldDescription.setForeground(new java.awt.Color(102, 51, 0));
        fieldDescription.setText("Escribe la descripcion...");
        fieldDescription.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 51, 0));
        jLabel4.setText("Descripcion del conjunto");

        btnSelectFoto.setBackground(new java.awt.Color(102, 51, 0));
        btnSelectFoto.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnSelectFoto.setForeground(new java.awt.Color(255, 255, 255));
        btnSelectFoto.setText("Seleccionar foto");
        btnSelectFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectFotoActionPerformed(evt);
            }
        });

        panelFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/defaultImage.jpg"))); // NOI18N

        javax.swing.GroupLayout panelFotoLayout = new javax.swing.GroupLayout(panelFoto);
        panelFoto.setLayout(panelFotoLayout);
        panelFotoLayout.setHorizontalGroup(
            panelFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelFotoLayout.setVerticalGroup(
            panelFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 204, Short.MAX_VALUE)
        );

        btnPublicar.setBackground(new java.awt.Color(102, 51, 0));
        btnPublicar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnPublicar.setForeground(new java.awt.Color(255, 255, 255));
        btnPublicar.setText("Publicar conjunto");
        btnPublicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPublicarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(containerTittle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fieldTitulo)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                            .addComponent(fieldDescription)
                            .addComponent(btnSelectFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(btnPublicar, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(containerTittle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSelectFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(btnPublicar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelectFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectFotoActionPerformed
        // TODO add your handling code here:
        
        JnaFileChooser fileChooser = new JnaFileChooser();
        if (fileChooser.showOpenDialog(this)) {
            try {
                String imagePath = fileChooser.getSelectedFile().toPath().toString();
                File foto = new File(imagePath); 
                
                this.fotoCargadaBytes = Files.readAllBytes(foto.toPath());
                // Cargamos la foto
                this.panelFoto.setIcon(new ImageIcon(this.fotoCargadaBytes));
                
                this.panelFoto.revalidate();
                this.panelFoto.repaint();
                
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al cargar la foto seleccionada");
                System.out.println("Error al cargar la foto: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnSelectFotoActionPerformed

    private void btnPublicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPublicarActionPerformed
        // TODO add your handling code here:
        
        String titulo = this.fieldTitulo.getText();
        String description = this.fieldDescription.getText();
        
        // Verificamos que los campos no esten vacios
        if (titulo.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor llena todos los campos");
            return;
        }

        // Guardamos la publicacion
        Publicacion publicacion = new Publicacion(this.usuarioLogueado.getIdUsuario(), titulo, description);

        if (this.fotoCargadaBytes != null && this.fotoCargadaBytes.length > 0) {
            publicacion.setFoto(this.fotoCargadaBytes);
        }
        
        int idPublicacion = WebServiceManager.guardarPublicacion(publicacion);

        if (idPublicacion != -1) {
            JOptionPane.showMessageDialog(null, "Publicacion guardada con exito");
            this.homeUsuarios.actualizarPublicaciones();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Error al guardar la publicacion");
        }
        
    }//GEN-LAST:event_btnPublicarActionPerformed

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
            java.util.logging.Logger.getLogger(CreatePublicacionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreatePublicacionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreatePublicacionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreatePublicacionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreatePublicacionForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPublicar;
    private javax.swing.JButton btnSelectFoto;
    private javax.swing.JPanel containerTittle;
    private javax.swing.JTextField fieldDescription;
    private javax.swing.JTextField fieldTitulo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private org.edisoncor.gui.panel.PanelImage panelFoto;
    // End of variables declaration//GEN-END:variables
}