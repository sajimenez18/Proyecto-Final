import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import components.*;

// Importamos nuestra clase del botón
import module.BotonLogin;

public class Main {

    public static void main(String[] args) {

        JFrame ventana = new JFrame("Agenda de tareas");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ventana.setLayout(new BorderLayout());
        ventana.setLocationRelativeTo(null);

        // Header con textos personalizados
        Header header = new Header("Agenda de Tareas", "Iniciar Sesión");
        ventana.add(header, BorderLayout.NORTH);

        LoginPanel panelLogin = new LoginPanel();
        ventana.add(panelLogin.getJPanel(), BorderLayout.CENTER);
            
        ventana.setVisible(true);
    }
}
