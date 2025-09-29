package module;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import control.LoginControl;
import views.InicioSecion;

public class BotonLogin {

    // Método que construye y devuelve el botón listo
    public static JButton crear(JTextField emailField, JPasswordField passwordField, JFrame ventana) {
        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setPreferredSize(new Dimension(120, 40));
        btnIngresar.setBackground(new Color(60, 130, 200));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnIngresar.setFocusPainted(false);
        btnIngresar.setBorderPainted(false);
        btnIngresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //Acción del botón
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entradaUsuario = emailField.getText();
                String entradaContra = new String(passwordField.getPassword());

                // Llama a LoginControl
                if (new LoginControl().validacionDatos(entradaUsuario, entradaContra)) {
                    ventana.dispose();
                    // Abre la vista InicioSecion
                    new InicioSecion(entradaUsuario);
                } else {
                    JOptionPane.showMessageDialog(btnIngresar,
                            "Credenciales incorrectas",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return btnIngresar;
    }
}
