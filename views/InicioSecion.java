package views;

import components.SecionPanel;
import components.Header;
import control.SecionControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioSecion {

    private JFrame ventana;
    private JPanel panelTareas;
    private SecionPanel agenda;
    private Header header;
    private SecionControl control;

    public InicioSecion() {
        this("");
    }

    public InicioSecion(String usuario) {
        agenda = new SecionPanel();
        
        // Configuración de la ventana
        ventana = new JFrame("Agenda de Tareas Avanzada");
        ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout());
        ventana.getContentPane().setBackground(new Color(245, 245, 245));

        // Header
        String textoDerecho = usuario.isEmpty() ? "Iniciar Sesión" : "Bienvenido, " + usuario;
        header = new Header("Agenda de Tareas", textoDerecho);
        ventana.add(header, BorderLayout.NORTH);

        // Panel de tar+eas
        panelTareas = new JPanel();
        panelTareas.setLayout(new BoxLayout(panelTareas, BoxLayout.Y_AXIS));
        panelTareas.setBackground(new Color(245, 245, 245));
        
        JScrollPane scroll = new JScrollPane(panelTareas);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        ventana.add(scroll, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnNueva = new JButton("+ Nueva Tarea");
        btnNueva.setFont(new Font("Arial", Font.BOLD, 14));
        btnNueva.setBackground(new Color(70, 130, 180));
        btnNueva.setForeground(Color.WHITE);
        btnNueva.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Inicializar el controlador
        control = new SecionControl(agenda, this, ventana, panelTareas);
        
        // Agregar acción al botón
        btnNueva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.accionNuevaTarea();
            }
        });
        
        panelBotones.add(btnNueva);
        ventana.add(panelBotones, BorderLayout.SOUTH);

        // Actualizar la vista con tareas de ejemplo (opcional)

        control.actualizarPanel();

        ventana.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InicioSecion("Usuario de Prueba"));
    }
}