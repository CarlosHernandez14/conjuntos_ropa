/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.vistas.admin;

import com.mycompany.dao.ExportarDatos;
import com.mycompany.dao.WebServiceManager;
import com.mycompany.domainclasses.Prenda;
import com.mycompany.domainclasses.Publicacion;
import com.mycompany.domainclasses.Usuario;
import com.mycompany.vistas.Login;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;


public class HomeAdmin extends javax.swing.JFrame {

    private Usuario usuario;
    private ArrayList<Publicacion> publicaciones;
    private ArrayList<Prenda> prendas;
    /**
     * Creates new form HomeAdmin
     */
    public HomeAdmin() {
        initComponents();
    }
    
    public HomeAdmin(Usuario usuario) {
        initComponents();
        this.usuario = usuario;
        this.setLocationRelativeTo(null);
        
        this.panelListPublicaciones.setLayout(new BoxLayout(this.panelListPublicaciones, BoxLayout.Y_AXIS));
        this.panelListPrendas.setLayout(new BoxLayout(this.panelListPrendas, BoxLayout.Y_AXIS));
        this.containerListUsuarios.setLayout(new BoxLayout(this.containerListUsuarios, BoxLayout.Y_AXIS));
        
        cargarPublicaciones();
        cargarPrendas();
        cargarUsuarios();
        
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
            PanelPublicacion panelPublicacion = new PanelPublicacion(publicacion, this.usuario, null, this);
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
        ArrayList<Prenda> prendas = (ArrayList<Prenda>) WebServiceManager.obtenerPrendas();
        
        for (Prenda prenda : prendas) {
            PanelPrenda panelPrenda = new PanelPrenda(prenda, null, usuario);
            
            this.panelListPrendas.add(panelPrenda);
            this.panelListPrendas.add(Box.createVerticalStrut(10));
            
        }
        
        
        this.panelListPrendas.revalidate();
        this.panelListPrendas.repaint();

    }
    
    private void cargarUsuarios() {
        this.containerListUsuarios.removeAll();
        // Cargamos los usuarios

        List<Usuario> usuarios = WebServiceManager.obtenerUsuarios();

        for (Usuario usuario : usuarios) {
            PanelUsuario panelUsuario = new PanelUsuario(usuario);
            this.containerListUsuarios.add(panelUsuario);
            this.containerListUsuarios.add(Box.createVerticalStrut(10));
        }

        this.containerListUsuarios.revalidate();
        this.containerListUsuarios.repaint();
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

        menuExport = new javax.swing.JPopupMenu();
        itemExcelListPublicaciones = new javax.swing.JMenuItem();
        itemPdfListUsuarios = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        containerSideBar = new javax.swing.JPanel();
        panelImage1 = new com.mycompany.vistas.RoundedPanelImage();
        labelUsername = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        btnPublicaciones = new javax.swing.JButton();
        btnPrendas = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        btnUsuarios = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        containerPublicaciones = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        scrollPublicaciones = new javax.swing.JScrollPane();
        panelListPublicaciones = new javax.swing.JPanel();
        containerPrendas = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        scrollPrendas = new javax.swing.JScrollPane();
        panelListPrendas = new javax.swing.JPanel();
        containerUsuarios = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        scrollUsuarios = new javax.swing.JScrollPane();
        containerListUsuarios = new javax.swing.JPanel();

        itemExcelListPublicaciones.setText("Excel con Publicaciones");
        itemExcelListPublicaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemExcelListPublicacionesActionPerformed(evt);
            }
        });
        menuExport.add(itemExcelListPublicaciones);

        itemPdfListUsuarios.setText("PDF con Usuarios");
        itemPdfListUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPdfListUsuariosActionPerformed(evt);
            }
        });
        menuExport.add(itemPdfListUsuarios);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        btnExport.setBackground(new java.awt.Color(129, 75, 21));
        btnExport.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnExport.setForeground(new java.awt.Color(255, 255, 255));
        btnExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-exportar.png"))); // NOI18N
        btnExport.setText("Exportar");
        btnExport.setBorder(null);
        btnExport.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        btnUsuarios.setBackground(new java.awt.Color(129, 75, 21));
        btnUsuarios.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        btnUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-user.png"))); // NOI18N
        btnUsuarios.setText("Usuarios");
        btnUsuarios.setBorder(null);
        btnUsuarios.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosActionPerformed(evt);
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
                    .addComponent(btnPublicaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(btnExport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addComponent(btnPrendas, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jPanel1.add(containerSideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, -1));

        containerPublicaciones.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 51, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Publicaciones de los usuarios");

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
                .addGap(56, 56, 56)
                .addComponent(scrollPublicaciones)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Publiccaciones", containerPublicaciones);

        containerPrendas.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 51, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Prendas del usuario");

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
                .addGap(50, 50, 50)
                .addComponent(scrollPrendas)
                .addGap(18, 18, 18))
        );

        jTabbedPane1.addTab("Prendas", containerPrendas);

        containerUsuarios.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 51, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Usuarios registrados");

        scrollUsuarios.setBorder(null);

        containerListUsuarios.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout containerListUsuariosLayout = new javax.swing.GroupLayout(containerListUsuarios);
        containerListUsuarios.setLayout(containerListUsuariosLayout);
        containerListUsuariosLayout.setHorizontalGroup(
            containerListUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 636, Short.MAX_VALUE)
        );
        containerListUsuariosLayout.setVerticalGroup(
            containerListUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        scrollUsuarios.setViewportView(containerListUsuarios);

        javax.swing.GroupLayout containerUsuariosLayout = new javax.swing.GroupLayout(containerUsuarios);
        containerUsuarios.setLayout(containerUsuariosLayout);
        containerUsuariosLayout.setHorizontalGroup(
            containerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
            .addComponent(jSeparator3)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, containerUsuariosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        containerUsuariosLayout.setVerticalGroup(
            containerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerUsuariosLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(scrollUsuarios)
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab3", containerUsuarios);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, -40, 660, 620));

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

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnPublicacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPublicacionesActionPerformed
        // TODO add your handling code here:\
        this.jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_btnPublicacionesActionPerformed

    private void btnPrendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrendasActionPerformed
        // TODO add your handling code here:
        this.jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnPrendasActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        // TODO add your handling code here:
        this.menuExport.show(this.btnExport, this.btnExport.getWidth(), 0);
    }//GEN-LAST:event_btnExportActionPerformed

    private void itemExcelListPublicacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemExcelListPublicacionesActionPerformed
        // TODO add your handling code here:
        // ORDENAMOS LISTA DE PUBLICACIONES POR CANTIDAD DE REACCIONES

        ArrayList<Publicacion> publicacionesOrdenadas = new ArrayList<>(this.publicaciones);
        publicacionesOrdenadas.sort((p1, p2) -> p2.getReacciones().size() - p1.getReacciones().size());

        // Obtenemos el top 10 de publicaciones
        ArrayList<Publicacion> topPublicaciones = new ArrayList<>(publicacionesOrdenadas.subList(0, Math.min(10, publicacionesOrdenadas.size())));

        ExportarDatos.generarReporteExcelPublicaciones(topPublicaciones);

    }//GEN-LAST:event_itemExcelListPublicacionesActionPerformed

    private void itemPdfListUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPdfListUsuariosActionPerformed
        // TODO add your handling code here:
        
        List<Usuario> usuarios = WebServiceManager.obtenerUsuarios();
        
        // HashMap con la cantidad de publicaciones por usuario
        HashMap<Integer, Integer> publicacionesPorUsuario = new HashMap<>();

        for (Usuario usuario : usuarios) {
            int cantidadPublicaciones = WebServiceManager.obtenerPublicacionesPorUsuario(usuario.getIdUsuario()).size();
            publicacionesPorUsuario.put(usuario.getIdUsuario(), cantidadPublicaciones);
        }

        ExportarDatos.generarReportePDFUsuarios(usuarios, publicacionesPorUsuario);
        
    }//GEN-LAST:event_itemPdfListUsuariosActionPerformed

    private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosActionPerformed
        // TODO add your handling code here:
        this.jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_btnUsuariosActionPerformed

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
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPrendas;
    private javax.swing.JButton btnPublicaciones;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JPanel containerListUsuarios;
    private javax.swing.JPanel containerPrendas;
    private javax.swing.JPanel containerPublicaciones;
    private javax.swing.JPanel containerSideBar;
    private javax.swing.JPanel containerUsuarios;
    private javax.swing.JMenuItem itemExcelListPublicaciones;
    private javax.swing.JMenuItem itemPdfListUsuarios;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelUsername;
    private javax.swing.JPopupMenu menuExport;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private javax.swing.JPanel panelListPrendas;
    private javax.swing.JPanel panelListPublicaciones;
    private javax.swing.JScrollPane scrollPrendas;
    private javax.swing.JScrollPane scrollPublicaciones;
    private javax.swing.JScrollPane scrollUsuarios;
    // End of variables declaration//GEN-END:variables
}
