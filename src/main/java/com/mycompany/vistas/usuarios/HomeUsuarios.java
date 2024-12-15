/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.vistas.usuarios;

import com.mycompany.dao.WebServiceManager;
import com.mycompany.domainclasses.Prenda;
import com.mycompany.domainclasses.Publicacion;
import com.mycompany.domainclasses.Usuario;
import com.mycompany.vistas.Login;
import com.mycompany.vistas.admin.CreatePublicacionForm;
import com.mycompany.vistas.admin.PanelPrenda;
import com.mycompany.vistas.admin.PanelPublicacion;

import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;

/**
 *
 * @author carlo
 */
public class HomeUsuarios extends javax.swing.JFrame {

    private Usuario usuario;
    private ArrayList<Publicacion> publicaciones;
    private ArrayList<Prenda> prendas;
    /**
     * Creates new form HomeUsuarios
     */
    public HomeUsuarios() {
        initComponents();
    }
    
    public HomeUsuarios(Usuario usuario) {
        initComponents();
        this.usuario = usuario;
        
        this.setLocationRelativeTo(null);
        
        this.panelListPublicaciones.setLayout(new BoxLayout(this.panelListPublicaciones, BoxLayout.Y_AXIS));
        this.panelListPrendas.setLayout(new BoxLayout(this.panelListPrendas, BoxLayout.Y_AXIS));
        
        cargarPublicaciones();
        cargarPrendas();
    }
    
    private void cargarPublicaciones() {
        this.panelListPublicaciones.removeAll();
        // Cargamos las publicaciones
        this.publicaciones = (ArrayList<Publicacion>) WebServiceManager.obtenerPublicaciones();
        
        this.publicaciones = new ArrayList<>(
                this.publicaciones.stream()
                .filter(p -> !(p.getTitulo().equals("DEFAULT")))
                .toList()
            );

        for (Publicacion publicacion : this.publicaciones) {
            PanelPublicacion panelPublicacion = new PanelPublicacion(publicacion, this.usuario, this);
            this.panelListPublicaciones.add(panelPublicacion);

            // Agregamos un rigid area para separar los paneles
            this.panelListPublicaciones.add(Box.createVerticalStrut(10));
        }

        this.panelListPublicaciones.revalidate();
        this.panelListPublicaciones.repaint();
    }
    
    private void cargarPrendas() {
        this.panelListPrendas.removeAll();
        // Cargamos las prendas del usuario
        this.prendas = new ArrayList<>();
        ArrayList<Publicacion> publicacionesUsuario = (ArrayList<Publicacion>) WebServiceManager.obtenerPublicacionesPorUsuario(this.usuario.getIdUsuario());

        for (Publicacion publicacion : publicacionesUsuario) {
            // Agregamos las prendas de la publicacion a la lista
            publicacion.getPrendas().forEach(prenda -> {
                this.prendas.add(prenda);
                PanelPrenda panelPrenda = new PanelPrenda(prenda, publicacion, this.usuario);
                this.panelListPrendas.add(panelPrenda);

                // Agregamos un rigid area para separar los paneles
                this.panelListPrendas.add(Box.createVerticalStrut(10));
            });
        }

        this.panelListPrendas.revalidate();
        this.panelListPrendas.repaint();

    }
    
    public void actualizarPublicaciones() {
        cargarPublicaciones();
    }
    
    public void actualizarPrendas() {
        cargarPrendas();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        containerHome = new javax.swing.JPanel();
        containerSideBar = new javax.swing.JPanel();
        panelImage1 = new com.mycompany.vistas.RoundedPanelImage();
        labelUsername = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        btnPublicaciones = new javax.swing.JButton();
        btnPrendas = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        containerPublicaciones = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnPost = new javax.swing.JButton();
        scrollPublicaciones = new javax.swing.JScrollPane();
        panelListPublicaciones = new javax.swing.JPanel();
        containerPrendas = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btnAddPrenda = new javax.swing.JButton();
        scrollPrendas = new javax.swing.JScrollPane();
        panelListPrendas = new javax.swing.JPanel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        containerHome.setBackground(new java.awt.Color(255, 255, 255));
        containerHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        containerSideBar.setBackground(new java.awt.Color(129, 75, 21));

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logo-ropa.jpg"))); // NOI18N

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        labelUsername.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        labelUsername.setForeground(new java.awt.Color(255, 255, 255));
        labelUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelUsername.setText("Nombre");

        btnLogout.setBackground(new java.awt.Color(129, 75, 21));
        btnLogout.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-logout.png"))); // NOI18N
        btnLogout.setText("Cerrar sesion");
        btnLogout.setBorder(null);
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnPublicaciones.setBackground(new java.awt.Color(129, 75, 21));
        btnPublicaciones.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnPublicaciones.setForeground(new java.awt.Color(255, 255, 255));
        btnPublicaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-post.png"))); // NOI18N
        btnPublicaciones.setText("Publicaciones");
        btnPublicaciones.setBorder(null);
        btnPublicaciones.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPublicaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPublicacionesActionPerformed(evt);
            }
        });

        btnPrendas.setBackground(new java.awt.Color(129, 75, 21));
        btnPrendas.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnPrendas.setForeground(new java.awt.Color(255, 255, 255));
        btnPrendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-prendas.png"))); // NOI18N
        btnPrendas.setText("Prendas");
        btnPrendas.setBorder(null);
        btnPrendas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPrendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrendasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout containerSideBarLayout = new javax.swing.GroupLayout(containerSideBar);
        containerSideBar.setLayout(containerSideBarLayout);
        containerSideBarLayout.setHorizontalGroup(
            containerSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerSideBarLayout.createSequentialGroup()
                .addGroup(containerSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(containerSideBarLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(containerSideBarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(containerSideBarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)))
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, containerSideBarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(containerSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPrendas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPublicaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );
        containerSideBarLayout.setVerticalGroup(
            containerSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerSideBarLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelUsername)
                .addGap(36, 36, 36)
                .addComponent(btnPublicaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnPrendas, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 213, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        containerHome.add(containerSideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, -1));

        containerPublicaciones.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 51, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Publicaciones de los usuarios");

        btnPost.setBackground(new java.awt.Color(255, 102, 0));
        btnPost.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnPost.setForeground(new java.awt.Color(255, 255, 255));
        btnPost.setText("Nueva publicacion");
        btnPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPostActionPerformed(evt);
            }
        });

        scrollPublicaciones.setBorder(null);

        panelListPublicaciones.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelListPublicacionesLayout = new javax.swing.GroupLayout(panelListPublicaciones);
        panelListPublicaciones.setLayout(panelListPublicacionesLayout);
        panelListPublicacionesLayout.setHorizontalGroup(
            panelListPublicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 648, Short.MAX_VALUE)
        );
        panelListPublicacionesLayout.setVerticalGroup(
            panelListPublicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 452, Short.MAX_VALUE)
        );

        scrollPublicaciones.setViewportView(panelListPublicaciones);

        javax.swing.GroupLayout containerPublicacionesLayout = new javax.swing.GroupLayout(containerPublicaciones);
        containerPublicaciones.setLayout(containerPublicacionesLayout);
        containerPublicacionesLayout.setHorizontalGroup(
            containerPublicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerPublicacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(containerPublicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, containerPublicacionesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPost, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollPublicaciones, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        containerPublicacionesLayout.setVerticalGroup(
            containerPublicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerPublicacionesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPost, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPublicaciones)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Publiccaciones", containerPublicaciones);

        containerPrendas.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 51, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Prendas del usuario");

        btnAddPrenda.setBackground(new java.awt.Color(255, 102, 0));
        btnAddPrenda.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnAddPrenda.setForeground(new java.awt.Color(255, 255, 255));
        btnAddPrenda.setText("Agregar prenda");
        btnAddPrenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPrendaActionPerformed(evt);
            }
        });

        scrollPrendas.setBorder(null);

        panelListPrendas.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelListPrendasLayout = new javax.swing.GroupLayout(panelListPrendas);
        panelListPrendas.setLayout(panelListPrendasLayout);
        panelListPrendasLayout.setHorizontalGroup(
            panelListPrendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 648, Short.MAX_VALUE)
        );
        panelListPrendasLayout.setVerticalGroup(
            panelListPrendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 452, Short.MAX_VALUE)
        );

        scrollPrendas.setViewportView(panelListPrendas);

        javax.swing.GroupLayout containerPrendasLayout = new javax.swing.GroupLayout(containerPrendas);
        containerPrendas.setLayout(containerPrendasLayout);
        containerPrendasLayout.setHorizontalGroup(
            containerPrendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerPrendasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(containerPrendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, containerPrendasLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAddPrenda, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollPrendas))
                .addContainerGap())
        );
        containerPrendasLayout.setVerticalGroup(
            containerPrendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerPrendasLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddPrenda, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPrendas)
                .addGap(18, 18, 18))
        );

        jTabbedPane1.addTab("Prendas", containerPrendas);

        containerHome.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, -40, 660, 620));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(containerHome, javax.swing.GroupLayout.DEFAULT_SIZE, 843, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(containerHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrendasActionPerformed
        // TODO add your handling code here:
        this.jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnPrendasActionPerformed

    private void btnPublicacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPublicacionesActionPerformed
        // TODO add your handling code here:\
        this.jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_btnPublicacionesActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPostActionPerformed
        // TODO add your handling code here:

        new CreatePublicacionForm(this.usuario, this).setVisible(true);

    }//GEN-LAST:event_btnPostActionPerformed

    private void btnAddPrendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPrendaActionPerformed
        // TODO add your handling code here:
        
        new CreatePrendaForm(this.usuario, this).setVisible(true);
    }//GEN-LAST:event_btnAddPrendaActionPerformed

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
            java.util.logging.Logger.getLogger(HomeUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddPrenda;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPost;
    private javax.swing.JButton btnPrendas;
    private javax.swing.JButton btnPublicaciones;
    private javax.swing.JPanel containerHome;
    private javax.swing.JPanel containerPrendas;
    private javax.swing.JPanel containerPublicaciones;
    private javax.swing.JPanel containerSideBar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel labelUsername;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private javax.swing.JPanel panelListPrendas;
    private javax.swing.JPanel panelListPublicaciones;
    private javax.swing.JScrollPane scrollPrendas;
    private javax.swing.JScrollPane scrollPublicaciones;
    // End of variables declaration//GEN-END:variables
}