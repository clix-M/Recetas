package clix.main;

import clix.login.Login;
import clix.manager.FormsManager;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author clint
 */
public class Application extends javax.swing.JFrame {
    public Application() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

      setTitle("Recetas");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(new Dimension(1200,700));
      setLocationRelativeTo(null);
      setContentPane(new Login());
        FormsManager.getInstance().initApplication(this);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("clixThemes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacLightLaf.setup();
        java.awt.EventQueue.invokeLater(() -> {
            new Application().setVisible(true);
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
