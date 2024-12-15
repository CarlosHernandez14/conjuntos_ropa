/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vistas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Clase personalizada para JButton con bordes redondeados
class RoundedButton extends JButton {
    private int cornerRadius;

    public RoundedButton(String text, int cornerRadius) {
        super(text);
        this.cornerRadius = cornerRadius;
        setContentAreaFilled(false); // Desactivar relleno por defecto
        setFocusPainted(false); // Desactivar indicador de enfoque
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (g instanceof Graphics2D) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Fondo del botón
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        }

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        if (g instanceof Graphics2D) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Dibujar el borde redondeado
            g2.setColor(getForeground());
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        }
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        repaint();
    }
}