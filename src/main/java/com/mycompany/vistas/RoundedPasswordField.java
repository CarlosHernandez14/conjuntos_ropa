/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vistas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Clase personalizada para JPasswordField con bordes redondeados
class RoundedPasswordField extends JPasswordField {
    private int cornerRadius;

    public RoundedPasswordField(int cornerRadius) {
        super();
        this.cornerRadius = cornerRadius;
        setOpaque(false); // Permitir transparencia para el fondo redondeado
//        setBorder(new EmptyBorder(5, 20, 5, 5)); // Margen interno
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (g instanceof Graphics2D) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Fondo del campo de texto
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        }

        // Llamar al método padre para renderizar el texto
        super.paintComponent(g);
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        repaint();
    }
}