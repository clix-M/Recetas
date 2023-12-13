/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package clix.login;

import clix.home.Home;
import clix.manager.FormsManager;
import clix.manager.SessionManager;
import clix.model.Usuario;
import clix.util.db;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.sql.Statement;

/**
 *
 * @author clint
 */
public class Login extends JPanel {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        chRememberMe = new JCheckBox("Recordarme");
        cmdLogin = new JButton("Iniciar sesión");
        JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "fill,250:280"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:20;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%)");

        txtPassword.putClientProperty(FlatClientProperties.STYLE, "" +
                "showRevealButton:true");
        cmdLogin.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]background:darken(@background,10%);" +
                "[dark]background:lighten(@background,10%);" +
                "borderWidth:0;" +
                "focusWidth:0;" +
                "innerFocusWidth:0"
        );
        cmdLogin.addActionListener(e -> {
            String user = txtUsername.getText();
            String pass = new String(txtPassword.getPassword());

            if (user.equals("") || pass.equals("")) {
                JOptionPane.showMessageDialog(null, "Debe ingresar usuario y contraseña");
            } else {
                try {
                    Statement st = db.getConnection().createStatement();
                    String sql = "SELECT * FROM Usuario WHERE nombre='" + user + "' AND contrasena='" + pass + "'";
                    java.sql.ResultSet resultSet;
                    resultSet = st.executeQuery(sql);

                    if (resultSet.next()) {
                        int userId = resultSet.getInt("id_usuario");
                        String nombre = resultSet.getString("nombre");
                        String correo_electronico = resultSet.getString("correo_electronico");
                        // insertar en la sesion el id del usuario
                        Usuario usuario = new Usuario(userId, nombre, correo_electronico);
                        SessionManager.getInstance().setUserId(usuario.getId_usuario());
                        FormsManager.getInstance().showForm(new Home());
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese su nombre de usuario");
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese su contraseña");

        JLabel lbTitle = new JLabel("¡Bienvenido de nuevo!");
        JLabel description = new JLabel("Por favor, inicie sesión para acceder a su cuenta");
        lbTitle.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +10");
        description.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%)");

        panel.add(lbTitle);
        panel.add(description);
        panel.add(new JLabel("Nombre de usuario"), "gapy 8");
        panel.add(txtUsername);
        panel.add(new JLabel("<html><head><meta charset=\"UTF-8\"></head><body><p>Contraseña <span style=\"color: yellow;\">\uD83D\uDD0F</span></p></body></html>"), "gapy 8");
        panel.add(txtPassword);
        panel.add(chRememberMe, "grow 0");
        panel.add(cmdLogin, "gapy 10");
        panel.add(createSignupLabel(), "gapy 10");

        add(panel);

    }// </editor-fold>//GEN-END:initComponents

    private Component createSignupLabel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null");
        JButton cmdRegister = new JButton("<html><a href=\"#\">Registrarse</a></html>");
        cmdRegister.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:3,3,3,3");
        cmdRegister.setContentAreaFilled(false);
        cmdRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdRegister.addActionListener(e -> {
           FormsManager.getInstance().showForm(new Register());
          //  JOptionPane.showConfirmDialog(null, "Register");
        });
        JLabel label = new JLabel("¿No tienes una cuenta?");
        label.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%)");
        panel.add(label);
        panel.add(cmdRegister);
        return panel;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JCheckBox chRememberMe;
    private JButton cmdLogin;
    // End of variables declaration//GEN-END:variables

}
