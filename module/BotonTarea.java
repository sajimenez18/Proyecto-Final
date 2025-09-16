package module;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BotonTarea {

    // ahora recibe también el modelo de la lista
    public static JButton crear(JFrame ventanaPadre, DefaultListModel<String> modeloTareas) {
        JButton btnNuevaTarea = new JButton("Nueva tarea");

        btnNuevaTarea.addActionListener(e -> {
            // Abre un diálogo para ingresar datos
            JDialog dialog = new JDialog(ventanaPadre, "Crear nueva tarea", true);
            dialog.setSize(300, 200);
            dialog.setLocationRelativeTo(ventanaPadre);
            dialog.setLayout(new BorderLayout());

            JTextField titulo = new JTextField("Título de la tarea");
            JTextArea descripcion = new JTextArea("Descripción");
            JButton guardar = new JButton("Guardar");

            guardar.addActionListener(ev -> {
                String t = titulo.getText();
                String d = descripcion.getText();
                // Añadir la tarea al modelo de la lista
                modeloTareas.addElement(t + " - " + d);
                dialog.dispose();
            });

            dialog.add(titulo, BorderLayout.NORTH);
            dialog.add(new JScrollPane(descripcion), BorderLayout.CENTER);
            dialog.add(guardar, BorderLayout.SOUTH);
            dialog.setVisible(true);
        });

        return btnNuevaTarea;
    }
}