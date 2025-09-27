package control;

import components.SecionPanel;
import module.BotonTarea;
import views.InicioSecion;
import database.HistorialDataBase; // ← Importar la clase de historial

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class SecionControl {
    private SecionPanel agenda;
    private InicioSecion vista;
    private JFrame ventana;
    private JPanel panelTareas;

    public SecionControl(SecionPanel agenda, InicioSecion vista, JFrame ventana, JPanel panelTareas) {
        this.agenda = agenda;
        this.vista = vista;
        this.ventana = ventana;
        this.panelTareas = panelTareas;
    }

    public void accionNuevaTarea() {
        abrirDialogo(-1);
    }

    public void accionEditarTarea(int indice) {
        if (indice != -1) {
            abrirDialogo(indice);
        }
    }

    public void accionEliminarTarea(int indice) {
        if (indice != -1) {
            BotonTarea tarea = agenda.getTarea(indice);
            int confirmacion = JOptionPane.showConfirmDialog(
                ventana, 
                "¿Estás seguro de que quieres eliminar la tarea: " + tarea.getTitulo() + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
            );
            
            if (confirmacion == JOptionPane.YES_OPTION) {
                // Guardar en historial antes de eliminar
                HistorialDataBase.guardarAccionTarea("ELIMINADA", tarea.getTitulo(), tarea.getPrioridad());
                agenda.eliminarTarea(indice);
                actualizarPanel();
            }
        }
    }

    private void abrirDialogo(int indexEditar) {
        JDialog dialog = new JDialog(ventana, indexEditar == -1 ? "Nueva Tarea" : "Editar Tarea", true);
        dialog.setSize(450, 400);
        dialog.setLocationRelativeTo(ventana);
        dialog.setLayout(new BorderLayout(10, 10));
        
        // Panel de contenido
        JPanel panelContenido = new JPanel(new GridLayout(4, 2, 10, 10));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField titulo = new JTextField();
        JTextArea descripcion = new JTextArea(3, 20);
        descripcion.setLineWrap(true);
        descripcion.setWrapStyleWord(true);
        
        String[] prioridades = {"Alta", "Media", "Baja"};
        JComboBox<String> comboPrioridad = new JComboBox<>(prioridades);
        
        JSpinner fechaSpinner = new JSpinner(new SpinnerDateModel());
        fechaSpinner.setEditor(new JSpinner.DateEditor(fechaSpinner, "dd/MM/yyyy"));
        fechaSpinner.setValue(new Date()); // Fecha actual por defecto

        // Cargar datos si estamos editando
        if (indexEditar != -1) {
            BotonTarea t = agenda.getTarea(indexEditar);
            titulo.setText(t.getTitulo());
            descripcion.setText(t.getDescripcion());
            comboPrioridad.setSelectedItem(t.getPrioridad());
            fechaSpinner.setValue(t.getFechaLimite());
        }

        panelContenido.add(new JLabel("Título:"));
        panelContenido.add(titulo);
        panelContenido.add(new JLabel("Descripción:"));
        panelContenido.add(new JScrollPane(descripcion));
        panelContenido.add(new JLabel("Prioridad:"));
        panelContenido.add(comboPrioridad);
        panelContenido.add(new JLabel("Fecha límite:"));
        panelContenido.add(fechaSpinner);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnGuardar = new JButton(indexEditar == -1 ? "Crear Tarea" : "Guardar Cambios");
        
        btnCancelar.addActionListener(e -> dialog.dispose());
        btnGuardar.addActionListener(ev -> guardarTarea(dialog, titulo, descripcion, comboPrioridad, fechaSpinner, indexEditar));

        panelBotones.add(btnCancelar);
        panelBotones.add(btnGuardar);

        dialog.add(panelContenido, BorderLayout.CENTER);
        dialog.add(panelBotones, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
private void guardarTarea(JDialog dialog, JTextField titulo, JTextArea descripcion, 
                             JComboBox<String> comboPrioridad, JSpinner fechaSpinner, int indexEditar) {
        String t = titulo.getText().trim();
        String d = descripcion.getText().trim();
        
        if (t.isEmpty() || d.isEmpty()) {
            JOptionPane.showMessageDialog(dialog, "Título y descripción son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String p = (String) comboPrioridad.getSelectedItem();
        Date f = (Date) fechaSpinner.getValue();
        BotonTarea tarea = new BotonTarea(t, d, p, f);

        if (indexEditar == -1) {
            // Nueva tarea
            agenda.agregarTarea(tarea);
            HistorialDataBase.guardarAccionTarea("CREADA", tarea.getTitulo(), tarea.getPrioridad());
        } else {
            // Editar tarea existente
            BotonTarea tareaAnterior = agenda.getTarea(indexEditar);
            agenda.editarTarea(indexEditar, tarea);
            HistorialDataBase.guardarEdicionTarea(tareaAnterior.getTitulo(), tarea.getTitulo(), tarea.getPrioridad());
        }
        
        actualizarPanel();
        dialog.dispose();
    }

    // Método para mostrar el historial
    private void mostrarHistorial() {
        String historial = HistorialDataBase.leerHistorialTareas();
        
        JTextArea textArea = new JTextArea(historial, 20, 50);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(ventana, scrollPane, 
            "Historial de Tareas", JOptionPane.INFORMATION_MESSAGE);
    }

    public void actualizarPanel() {
        panelTareas.removeAll();

        // Agregar botón de historial
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnHistorial = new JButton("📜 Ver Historial");
        btnHistorial.addActionListener(e -> mostrarHistorial());
        panelSuperior.add(btnHistorial);
        panelTareas.add(panelSuperior);

        // Agregar las tareas
        for (int i = 0; i < agenda.getTareas().size(); i++) {
            final int indice = i;
            BotonTarea tarea = agenda.getTareas().get(i);
            
            JPanel card = tarea.crearPanelTarea(
                () -> accionEditarTarea(indice),
                () -> accionEliminarTarea(indice)
            );
            
            panelTareas.add(card);
        }

        panelTareas.revalidate();
        panelTareas.repaint();
    }

    public void actualizarVista() {
        actualizarPanel();
    }
}