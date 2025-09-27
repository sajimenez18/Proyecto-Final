package module;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BotonTarea {
    private String titulo;
    private String descripcion;
    private String prioridad;
    private Date fechaLimite;
    private int id;

    public BotonTarea(String titulo, String descripcion, String prioridad, Date fechaLimite) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.fechaLimite = fechaLimite;
    }

    public BotonTarea(int id, String titulo, String descripcion, String prioridad, Date fechaLimite) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.fechaLimite = fechaLimite;
    }

    // Getters y Setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getPrioridad() { return prioridad; }
    public void setPrioridad(String prioridad) { this.prioridad = prioridad; }

    public Date getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(Date fechaLimite) { this.fechaLimite = fechaLimite; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    // Método para crear un panel visual de la tarea con botones de editar (izquierda) y eliminar (derecha)
    public JPanel crearPanelTarea(Runnable onEditar, Runnable onEliminar) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(getColorPrioridad(), 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(500, 100));

        // Panel principal con la información (centro)
        JPanel panelInfo = new JPanel(new BorderLayout());
        panelInfo.setBackground(Color.WHITE);
        
        JLabel lblTitulo = new JLabel(this.titulo);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(50, 50, 50));

        JLabel lblDescripcion = new JLabel("<html><body style='width: 300px'>" + this.descripcion + "</body></html>");
        lblDescripcion.setFont(new Font("Arial", Font.PLAIN, 12));
        lblDescripcion.setForeground(new Color(100, 100, 100));

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        JLabel lblInfo = new JLabel("Prioridad: " + this.prioridad + " | Vence: " + formato.format(this.fechaLimite));
        lblInfo.setFont(new Font("Arial", Font.ITALIC, 11));
        lblInfo.setForeground(new Color(150, 150, 150));

        JPanel contenido = new JPanel(new GridLayout(2, 1));
        contenido.setBackground(Color.WHITE);
        contenido.add(lblDescripcion);
        contenido.add(lblInfo);

        panelInfo.add(lblTitulo, BorderLayout.NORTH);
        panelInfo.add(contenido, BorderLayout.CENTER);

        // Panel de botones a la izquierda (Editar)
        JPanel panelBotonesIzquierda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBotonesIzquierda.setBackground(Color.WHITE);
        panelBotonesIzquierda.setPreferredSize(new Dimension(80, 60));
        
        // Botón Editar (○) - IZQUIERDA
        JButton btnEditar = new JButton("○ Editar");
        btnEditar.setFont(new Font("Arial", Font.BOLD, 12));
        btnEditar.setBackground(new Color(70, 130, 180));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        btnEditar.setToolTipText("Editar tarea");
        btnEditar.addActionListener(e -> onEditar.run());
        
        panelBotonesIzquierda.add(btnEditar);

        // Panel de botones a la derecha (Eliminar)
        JPanel panelBotonesDerecha = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotonesDerecha.setBackground(Color.WHITE);
        panelBotonesDerecha.setPreferredSize(new Dimension(80, 60));
        
        // Botón Eliminar (×) - DERECHA
        JButton btnEliminar = new JButton("× Eliminar");
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 12));
        btnEliminar.setBackground(new Color(220, 80, 60));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        btnEliminar.setToolTipText("Eliminar tarea");
        btnEliminar.addActionListener(e -> onEliminar.run());
        
        panelBotonesDerecha.add(btnEliminar);

        // Agregar todos los componentes al card
        card.add(panelBotonesIzquierda, BorderLayout.WEST);  // Editar a la izquierda
        card.add(panelInfo, BorderLayout.CENTER);            // Información en el centro
        card.add(panelBotonesDerecha, BorderLayout.EAST);    // Eliminar a la derecha

        return card;
    }

    // Versión alternativa con los botones en la parte inferior (izquierda/derecha)
    public JPanel crearPanelTareaAlternativo(Runnable onEditar, Runnable onEliminar) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(getColorPrioridad(), 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(500, 100));

        // Información de la tarea
        JPanel panelInfo = new JPanel(new BorderLayout());
        panelInfo.setBackground(Color.WHITE);
        
        JLabel lblTitulo = new JLabel(this.titulo);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(50, 50, 50));

        JLabel lblDescripcion = new JLabel("<html><body style='width: 300px'>" + this.descripcion + "</body></html>");
        lblDescripcion.setFont(new Font("Arial", Font.PLAIN, 12));
        lblDescripcion.setForeground(new Color(100, 100, 100));

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        JLabel lblInfo = new JLabel("Prioridad: " + this.prioridad + " | Vence: " + formato.format(this.fechaLimite));
        lblInfo.setFont(new Font("Arial", Font.ITALIC, 11));
        lblInfo.setForeground(new Color(150, 150, 150));

        JPanel contenido = new JPanel(new GridLayout(2, 1));
        contenido.setBackground(Color.WHITE);
        contenido.add(lblDescripcion);
        contenido.add(lblInfo);

        panelInfo.add(lblTitulo, BorderLayout.NORTH);
        panelInfo.add(contenido, BorderLayout.CENTER);

        // Panel de botones en la parte inferior
        JPanel panelBotones = new JPanel(new BorderLayout());
        panelBotones.setBackground(Color.WHITE);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        // Botón Editar a la IZQUIERDA
        JButton btnEditar = new JButton("○ Editar");
        btnEditar.setFont(new Font("Arial", Font.BOLD, 12));
        btnEditar.setBackground(new Color(70, 130, 180));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setPreferredSize(new Dimension(80, 30));
        btnEditar.setToolTipText("Editar tarea");
        btnEditar.addActionListener(e -> onEditar.run());

        // Botón Eliminar a la DERECHA
        JButton btnEliminar = new JButton("× Eliminar");
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 12));
        btnEliminar.setBackground(new Color(220, 80, 60));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setPreferredSize(new Dimension(80, 30));
        btnEliminar.setToolTipText("Eliminar tarea");
        btnEliminar.addActionListener(e -> onEliminar.run());

        panelBotones.add(btnEditar, BorderLayout.WEST);
        panelBotones.add(btnEliminar, BorderLayout.EAST);

        // Agregar componentes al card
        card.add(panelInfo, BorderLayout.CENTER);
        card.add(panelBotones, BorderLayout.SOUTH);

        return card;
    }

    private Color getColorPrioridad() {
        switch (this.prioridad.toLowerCase()) {
            case "alta": return new Color(220, 80, 60);    // Rojo
            case "media": return new Color(255, 165, 0);   // Naranja
            case "baja": return new Color(60, 180, 75);    // Verde
            default: return Color.GRAY;
        }
    }
}