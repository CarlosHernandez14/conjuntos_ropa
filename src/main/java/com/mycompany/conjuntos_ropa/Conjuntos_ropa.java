/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.conjuntos_ropa;

import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.vistas.Login;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author carlo
 */
public class Conjuntos_ropa {

    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            
        } catch (UnsupportedLookAndFeelException ex) {
            System.out.println("Error al cargar el look an feel");
        }
        
        new Login().setVisible(true);
        
    }
}
