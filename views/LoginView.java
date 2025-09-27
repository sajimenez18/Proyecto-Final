package views;

import javax.swing.*;
import java.awt.*;
import components.Header;
import components.LoginPanel;

public class LoginView {

    private JFrame ventana;

    public LoginView() {
        ventana = new JFrame("Agenda de tareas");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ventana.setLayout(new BorderLayout());
        ventana.setLocationRelativeTo(null);

        Header header = new Header("Agenda de Tareas", "Iniciar Sesión");
        ventana.add(header, BorderLayout.NORTH);

        LoginPanel loginPanel = new LoginPanel(ventana); // le pasas la ventana
        ventana.add(loginPanel.getJPanel(), BorderLayout.CENTER);

        ventana.setVisible(true); // <- aquí se muestra
    }
}
