/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import org.edisoncor.gui.panel.PanelImage;

public class RoundedPanelImage extends PanelImage {

    private int cornerRadius = 20; // Radio para las esquinas redondeadas

    public RoundedPanelImage() {
        super();
        setOpaque(false); // Hacer el fondo transparente para ver el redondeo
    }

    public void setCornerRadius(int radius) {
        this.cornerRadius = radius;
        repaint(); // Refrescar el panel
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar el fondo con esquinas redondeadas
        Shape rounded = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        g2.setClip(rounded);

        // Pintar la imagen del PanelImage
        super.paintComponent(g2);

        g2.dispose();
    }
}