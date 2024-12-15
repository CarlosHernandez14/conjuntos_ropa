/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.vistas.admin;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.mycompany.dao.WebServiceManager;
import com.mycompany.domainclasses.Prenda;
import com.mycompany.domainclasses.Publicacion;
import com.mycompany.domainclasses.RolUsuario;
import com.mycompany.domainclasses.Usuario;
import com.mycompany.vistas.RoundedPanel;


public class PanelPrenda extends RoundedPanel {

    private Prenda prenda;
    private boolean isEditing;
    private Usuario usuarioLogueado;

    private Publicacion publicacion;
    /**
     * Creates new form PanelPrenda
     */
    public PanelPrenda() {
        super(20);
        initComponents();
    }
    
    public PanelPrenda(Prenda prenda, Publicacion publicacion, Usuario usuarioLogueado) {
        super(20);
        initComponents();
        this.publicacion = publicacion;
        this.prenda = prenda;
        this.usuarioLogueado = usuarioLogueado;

        cargarDatos();
    }
    
    private void cargarDatos() {
        this.fieldNombre.setText(this.prenda.getNombre());
        this.fieldTipo.setText(this.prenda.getTipo());

        // Cargamos la imagen
        if (this.prenda.getFoto() != null) {
            this.panelImagen.setIcon(new ImageIcon(this.prenda.getFoto()));
        }
        
        // Si es admin o la publicacion es del usuario logueado
        if (this.usuarioLogueado.getRol() == RolUsuario.ADMIN || this.publicacion.getIdUsuario() == this.usuarioLogueado.getIdUsuario()) {
            this.btnEditar.setVisible(true);
        } else {
            this.btnEditar.setVisible(false);
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

        panelImagen = new com.mycompany.vistas.RoundedPanelImage();
        labelNombre1 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        fieldTipo = new javax.swing.JTextField();
        fieldNombre = new javax.swing.JTextField();

        setBackground(new java.awt.Color(239, 239, 239));
        setMaximumSize(new java.awt.Dimension(355, 373));
        setName(""); // NOI18N

        panelImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/defaultImage.jpg"))); // NOI18N

        javax.swing.GroupLayout panelImagenLayout = new javax.swing.GroupLayout(panelImagen);
        panelImagen.setLayout(panelImagenLayout);
        panelImagenLayout.setHorizontalGroup(
            panelImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 355, Short.MAX_VALUE)
        );
        panelImagenLayout.setVerticalGroup(
            panelImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 227, Short.MAX_VALUE)
        );

        labelNombre1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        labelNombre1.setForeground(new java.awt.Color(102, 102, 102));
        labelNombre1.setText("Tipo:");

        btnEditar.setBackground(new java.awt.Color(204, 102, 0));
        btnEditar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        fieldTipo.setEditable(false);
        fieldTipo.setBackground(new java.awt.Color(239, 239, 239));
        fieldTipo.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        fieldTipo.setForeground(new java.awt.Color(102, 51, 0));
        fieldTipo.setText("Tip de prenda");
        fieldTipo.setBorder(null);
        fieldTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldTipoActionPerformed(evt);
            }
        });

        fieldNombre.setEditable(false);
        fieldNombre.setBackground(new java.awt.Color(239, 239, 239));
        fieldNombre.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        fieldNombre.setForeground(new java.awt.Color(102, 51, 0));
        fieldNombre.setText("Tip de prenda");
        fieldNombre.setBorder(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelNombre1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldTipo, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(labelNombre1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:

        if (this.isEditing) {
            this.isEditing = false;
            this.fieldNombre.setEditable(false);
            this.fieldTipo.setEditable(false);
            this.btnEditar.setText("Editar");

            // Quitamos los bordes a los textfields
            this.fieldNombre.setBorder(null);
            this.fieldTipo.setBorder(null);
            
            // Guardar cambios
            this.prenda.setNombre(this.fieldNombre.getText());
            this.prenda.setTipo(this.fieldTipo.getText());
            
            int idPrenda = WebServiceManager.actualizarPrenda(prenda);

            if (idPrenda != -1) {
                JOptionPane.showMessageDialog(null, "Prenda actualizada correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar la prenda");
            }

        } else {
            this.isEditing = true;
            this.fieldNombre.setEditable(true);
            this.fieldTipo.setEditable(true);

            // Le ponemos bordes a los textfields
            this.fieldNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 102, 0)));
            this.fieldTipo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 102, 0)));

            this.btnEditar.setText("Guardar");
        }

    }//GEN-LAST:event_btnEditarActionPerformed

    private void fieldTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldTipoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JTextField fieldNombre;
    private javax.swing.JTextField fieldTipo;
    private javax.swing.JLabel labelNombre1;
    private org.edisoncor.gui.panel.PanelImage panelImagen;
    // End of variables declaration//GEN-END:variables
}