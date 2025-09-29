package views;

import components.SecionPanel;
import components.Header;
import control.SecionControl;

import javax.swing.*;
import java.awt.*;

public class InicioSecion {

    private JFrame ventana;
    private SecionControl control;

    public InicioSecion() {
        this("");
    }

    public InicioSecion(String usuario) {
        // Solo inicializa los componentes esenciales
        SecionPanel agenda = new SecionPanel();
        
        // Configurar ventana básica
        ventana = new JFrame("Agenda de Tareas Avanzada");
        ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout());
        
        // Delegar TODA la configuración a SecionControl
        control = new SecionControl(agenda, this, ventana, usuario);
        
        ventana.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InicioSecion("Usuario de Prueba"));
    }
}