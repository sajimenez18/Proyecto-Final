

package module;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotonLogin {

    // Método que construye y devuelve el botón listo
    public static JButton crear(JTextField emailField, JPasswordField passwordField, JFrame ventana) {
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // Validación simple
                if (email.equals("admin") && password.equals("1234")) {
                    JOptionPane.showMessageDialog(ventana, "Login correcto ✅");
                } else {
                    JOptionPane.showMessageDialog(ventana, "Credenciales inválidas ❌");
                }
            }
        });

        return loginButton;
    }
}