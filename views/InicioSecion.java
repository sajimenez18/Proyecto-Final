package views;

import components.Header;

import javax.swing.*;          
import java.awt.*;             
import java.text.SimpleDateFormat; // Para formatear fechas
import java.util.ArrayList;    // Lista dinámica
import java.util.Date;         // Para manejar fechas
import java.util.List;         // Interfaz de listas

// Clase para las tareas, cada tarea tiene título, descripción, prioridad y fecha límite
class Tarea {
    private String titulo;      
    private String descripcion;  
    private String prioridad;    
    private Date fechaLimite;    

    // Constructor: se usa para crear un objeto Tarea con todos sus atributos
    public Tarea(String titulo, String descripcion, String prioridad, Date fechaLimite) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.fechaLimite = fechaLimite;
    }

    // Getters y setters para obtener o cambiar los valores de cada atributo
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getPrioridad() { return prioridad; }
    public void setPrioridad(String prioridad) { this.prioridad = prioridad; }

    public Date getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(Date fechaLimite) { this.fechaLimite = fechaLimite; }
}

// Clase Agenda esta clase es como un “contenedor” de tareas. Permite agregar, eliminar, editar y obtener todas las tareas
class Agenda {
    private List<Tarea> tareas;  // Creamos una lista para almacenar las tareas

    // Constructor: inicializa la lista vacía
    public Agenda() {
        tareas = new ArrayList<>();
    }

    // Método para agregar una tarea a la lista
    public void agregarTarea(Tarea t) { tareas.add(t); }

    // Método para eliminar una tarea de la lista usando su índice
    public void eliminarTarea(int index) { tareas.remove(index); }

    // Método para editar una tarea existente
    public void editarTarea(int index, Tarea t) { tareas.set(index, t); }

    // Método para obtener la lista completa de tareas
    public List<Tarea> getTareas() { return tareas; }
}

// -------------------
// Clase principal que crea la interfaz de usuario
// Ahora puede recibir el nombre del usuario que inició sesión
// -------------------
public class InicioSecion {

    private JFrame ventana;      
    private JPanel panelTareas;   // Panel donde se mostrarán las tarjetas de las tareas
    private Agenda agenda;        // Objeto Agenda que controla la lista de tareas

    // Nuevo constructor que recibe el nombre del usuario
    public InicioSecion(String usuario) {
        ventana = new JFrame("Agenda Avanzada");
        ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout());
        ventana.getContentPane().setBackground(new Color(240, 240, 240));

        agenda = new Agenda();

        // Header con textos personalizados
        Header header = new Header("Agenda de Tareas", "Bienvenido " + usuario);
        ventana.add(header, BorderLayout.NORTH);

        // Panel central donde se mostrarán las tarjetas de cada tarea
        panelTareas = new JPanel();
        panelTareas.setLayout(new GridLayout(0, 1, 10, 10));
        JScrollPane scroll = new JScrollPane(panelTareas);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        ventana.add(scroll, BorderLayout.CENTER);

        // Panel inferior con botones
        // Botones: Nueva tarea, Editar tarea, Eliminar tarea
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10)); //Coloca los componentes de forma horizontal
        JButton btnNueva = new JButton("Nueva tarea");
        JButton btnEditar = new JButton("Editar tarea");
        JButton btnEliminar = new JButton("Eliminar tarea");
        panelBotones.add(btnNueva);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        ventana.add(panelBotones, BorderLayout.SOUTH);

        // Acciones de los botones
        btnNueva.addActionListener(e -> abrirDialogo(-1)); // Crear nueva tarea
        btnEditar.addActionListener(e -> {                 // Editar tarea seleccionada
            int fila = seleccionarTarea();
            if (fila != -1) abrirDialogo(fila);
            else JOptionPane.showMessageDialog(ventana, "Selecciona una tarea primero");
        });
        btnEliminar.addActionListener(e -> {               // Eliminar tarea seleccionada
            int fila = seleccionarTarea();
            if (fila != -1) {
                agenda.eliminarTarea(fila);
                actualizarPanel();
            } else {
                JOptionPane.showMessageDialog(ventana, "Selecciona una tarea primero");
            }
        });

        // Mostrar la ventana 
        ventana.setVisible(true);
    }

    // Método para abrir el diálogo de crear o editar tarea
    // indexEditar = -1 -> crear nueva, >=0 -> editar tarea existente
    private void abrirDialogo(int indexEditar) {
        JDialog dialog = new JDialog(ventana, "Tarea", true);
        dialog.setSize(400, 350);
        dialog.setLocationRelativeTo(ventana);
        dialog.setLayout(new GridLayout(5, 2, 10, 10));

        JTextField titulo = new JTextField();
        JTextArea descripcion = new JTextArea();
        descripcion.setLineWrap(true);
        String[] prioridades = {"Alta", "Media", "Baja"};
        JComboBox<String> comboPrioridad = new JComboBox<>(prioridades);
        JSpinner fechaSpinner = new JSpinner(new SpinnerDateModel());
        fechaSpinner.setEditor(new JSpinner.DateEditor(fechaSpinner, "dd/MM/yyyy"));

        JButton guardar = new JButton("Guardar");

        if (indexEditar != -1) {
            Tarea t = agenda.getTareas().get(indexEditar);
            titulo.setText(t.getTitulo());
            descripcion.setText(t.getDescripcion());
            comboPrioridad.setSelectedItem(t.getPrioridad());
            fechaSpinner.setValue(t.getFechaLimite());
        }

        dialog.add(new JLabel("Título:"));
        dialog.add(titulo);
        dialog.add(new JLabel("Descripción:"));
        dialog.add(new JScrollPane(descripcion));
        dialog.add(new JLabel("Prioridad:"));
        dialog.add(comboPrioridad);
        dialog.add(new JLabel("Fecha límite:"));
        dialog.add(fechaSpinner);
        dialog.add(new JLabel());
        dialog.add(guardar);

        guardar.addActionListener(ev -> {
            String t = titulo.getText();
            String d = descripcion.getText();
            String p = (String) comboPrioridad.getSelectedItem();
            Date f = (Date) fechaSpinner.getValue();
            Tarea tarea = new Tarea(t, d, p, f);

            if (indexEditar == -1) {
                agenda.agregarTarea(tarea);
            } else {
                agenda.editarTarea(indexEditar, tarea);
            }
            actualizarPanel();
            dialog.dispose();
        });

        dialog.setVisible(true);
    }

    // Método que actualiza el panel de tareas
    private void actualizarPanel() {
        panelTareas.removeAll();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        for (Tarea t : agenda.getTareas()) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
            card.setBackground(Color.WHITE);
            card.setPreferredSize(new Dimension(400, 80));

            JLabel lblTitulo = new JLabel(t.getTitulo());
            lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));

            JLabel lblDescripcion = new JLabel("<html>" + t.getDescripcion() + "</html>");
            lblDescripcion.setFont(new Font("Arial", Font.PLAIN, 14));

            JLabel lblInfo = new JLabel("Prioridad: " + t.getPrioridad() + " | Fecha: " + formato.format(t.getFechaLimite()));
            lblInfo.setFont(new Font("Arial", Font.ITALIC, 12));

            JPanel contenido = new JPanel(new GridLayout(2, 1));
            contenido.add(lblDescripcion);
            contenido.add(lblInfo);

            card.add(lblTitulo, BorderLayout.NORTH);
            card.add(contenido, BorderLayout.CENTER);

            panelTareas.add(card);
        }

        panelTareas.revalidate();
        panelTareas.repaint();
    }

    // Método para seleccionar una tarea usando un diálogo
    // Retorna el índice de la tarea seleccionada
    private int seleccionarTarea() {
        String[] opciones = new String[agenda.getTareas().size()];
        for (int i = 0; i < agenda.getTareas().size(); i++) {
            opciones[i] = agenda.getTareas().get(i).getTitulo();
        }
        if (opciones.length == 0) return -1;
        String seleccion = (String) JOptionPane.showInputDialog(
                ventana, "Selecciona una tarea:",
                "Seleccionar tarea",
                JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
        for (int i = 0; i < opciones.length; i++) {
            if (opciones[i].equals(seleccion)) return i;
        }
        return -1;
    }

    // Inicia la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InicioSecion(""));
    }
}