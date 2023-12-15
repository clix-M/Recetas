
package clix.view.login;

import clix.components.PasswordStrengthStatus;
import clix.view.home.Home;
import clix.manager.FormsManager;
import clix.util.db;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import raven.alerts.MessageAlerts;
import raven.toast.Notifications;

import javax.swing.*;
import java.awt.*;
import java.sql.Statement;

/**
 *
 * @author clint
 */
public class Register extends javax.swing.JPanel {
    public Register() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        setLayout(new MigLayout("fill, insets 20","[center]","[center]"));
        txtUsername = new JTextField();
        txtEmail = new JTextField();
        txtPassword = new JPasswordField();
        txtConfirmPassword = new JPasswordField();
        cmdRegister = new JButton("Registrarse");
        passwordStrengthStatus = new PasswordStrengthStatus();

        JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "[fill,360]"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:20;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%)");

        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese su nombre de usuario");
        txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese su correo electrónico");
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese su contraseña");
        txtConfirmPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Confirme su contraseña");


        txtPassword.putClientProperty(FlatClientProperties.STYLE, "" +
                "showRevealButton:true");
        txtConfirmPassword.putClientProperty(FlatClientProperties.STYLE, "" +
                "showRevealButton:true");

        cmdRegister.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]background:darken(@background,10%);" +
                "[dark]background:lighten(@background,10%);" +
                "borderWidth:0;" +
                "focusWidth:0;" +
                "innerFocusWidth:0"
        );
        cmdRegister.addActionListener(e -> {
            String user = txtUsername.getText();
            String email = txtEmail.getText();
            String pass = new String(txtPassword.getPassword());
            String confirmPass = new String(txtConfirmPassword.getPassword());

            // validaciones a considerar
            // 1. que no este vacio el campo usuario
            // 2. que no este vacio el campo email ademas de que sea un email valido (regex)
            // 2.1 que no exista el email en la base de datos
            /* 3. el campo password tiene un validacion si es fuerte o no con passwordStrengthStatus.initPasswordField(txtPassword);
                 entonces si es debil no se puede registrar
                 solo cuando es fuerte se puede registrar
             */
            // 4. que el campo password y confirm password sean iguales

            // 1
            if (user.isEmpty()) {
                MessageAlerts.getInstance().showMessage("CUIDADO! ", "Debe ingresar usuario", MessageAlerts.MessageType.ERROR);
            } else {
                // 2
                if (email.isEmpty()) {
                    MessageAlerts.getInstance().showMessage("CUIDADO! ", "Debe ingresar email", MessageAlerts.MessageType.WARNING);

                    // regex sirve para validar que el email sea valido
                } else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                    MessageAlerts.getInstance().showMessage("CUIDADO! ", "Debe ingresar un email válido", MessageAlerts.MessageType.WARNING);
                } else {
                    // 2.1
                    try {
                        Statement st = db.getConnection().createStatement();
                        String sql = "SELECT * FROM Usuario WHERE correo_electronico='" + email + "'";
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(sql);

                        if (resultSet.next()) {
                            MessageAlerts.getInstance().showMessage("CUIDADO! ", "El correo ya existe", MessageAlerts.MessageType.WARNING);
                        } else {
                            // 3
                            if (passwordStrengthStatus.initPasswordField(txtPassword) < 2) {
                                MessageAlerts.getInstance().showMessage("CUIDADO! ", "La contraseña debe es muy debil", MessageAlerts.MessageType.WARNING);
                            } else {
                                // 4
                                if (!pass.equals(confirmPass)) {
                                    MessageAlerts.getInstance().showMessage(" ERROR! ", "Las contraseñas no coinciden", MessageAlerts.MessageType.ERROR);
                                } else {
                                    try {
                                        Statement st2 = db.getConnection().createStatement();
                                        String sql2 = "INSERT INTO Usuario (nombre, correo_electronico, contrasena) VALUES ('" + user + "','" + email + "','" + pass + "')";
                                        st2.executeUpdate(sql2);
                                        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER,"Usuario registrado correctamente");
                                        FormsManager.getInstance().showForm(new Home());


                                    } catch (Exception ex) {
                                        System.out.println(ex);
                                    }
                                }
                            }

                        }
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }




        });

        JLabel lbTitle = new JLabel("¡Bienvenido a tu Aplicación de Recetas!");
        JLabel description = new JLabel("Por favor, regístrese para acceder a su cuenta");
        lbTitle.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +10");
        description.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%)");

       passwordStrengthStatus.initPasswordField(txtPassword);

        panel.add(lbTitle);
        panel.add(description);
        panel.add(new JLabel("Nombre de usuario "),"gapy 8");
        panel.add(txtUsername);
        panel.add(new JLabel("<html><head><meta charset=\"UTF-8\"></head><body><p>Correo electrónico <span style=\"color: aqua;\">\uD83D\uDCE7</span></p></body></html>"));
        panel.add(txtEmail);
        panel.add(new JSeparator(), "gapy 5 5");
        panel.add(new JLabel("<html><head><meta charset=\"UTF-8\"></head><body><p>Contraseña <span style=\"color: yellow;\">\uD83D\uDD0F</span></p></body></html>"),"gapy 8");
        panel.add(txtPassword);
        panel.add(passwordStrengthStatus,"gapy 0");
        panel.add(new JLabel("<html><head><meta charset=\"UTF-8\"></head><body><p>Confirmar contraseña <span style=\"color: yellow;\">\uD83D\uDD10</span></p></body></html>"),"gapy 0");
        panel.add(txtConfirmPassword);
        panel.add(cmdRegister,"gapy 20");
        panel.add(createLoginLabel(),"gapy 10");

        add(panel);
    }// </editor-fold>//GEN-END:initComponents

    public Component createLoginLabel(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null");
        JButton cmdLogin = new JButton("<html><a href=\"#\">Iniciar sesión aquí</a></html>");
        cmdLogin.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:3,3,3,3");
        cmdLogin.setContentAreaFilled(false);
        cmdLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdLogin.addActionListener(e -> {
            FormsManager.getInstance().showForm(new Login());
        });
        JLabel label = new JLabel("¿Ya tienes una cuenta?");
        label.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%)");
        panel.add(label);
        panel.add(cmdLogin);
        return panel;

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JTextField txtUsername;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JButton cmdRegister;
    private PasswordStrengthStatus passwordStrengthStatus;
    // End of variables declaration//GEN-END:variables
}
