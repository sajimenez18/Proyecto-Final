package components;

import java.awt.*;
import javax.swing.*;

public class Header extends JPanel {
    private JLabel textoIzquierda;
    private JLabel textoDerecha;

    public Header() {
        this("", ""); // Valores por defecto
    }

    public Header(String tituloIzquierdo, String tituloDerecho) {
        this.setPreferredSize(new Dimension(1000, 100));
        this.setOpaque(true);
        this.setBackground(new Color(194, 205, 253));
        this.setLayout(new BorderLayout());

        // Crear textos con los parámetros
        textoIzquierda = new JLabel(tituloIzquierdo);
        textoDerecha = new JLabel(tituloDerecho);

        // Configurar estilos
        textoIzquierda.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        textoIzquierda.setForeground(Color.WHITE);
        textoIzquierda.setFont(new Font("Arial", Font.BOLD, 25));

        textoDerecha.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        textoDerecha.setForeground(Color.WHITE);
        textoDerecha.setFont(new Font("Arial", Font.BOLD, 24));

        // Agregar textos al panel
        this.add(textoIzquierda, BorderLayout.WEST);
        this.add(textoDerecha, BorderLayout.EAST);
    }

    // Métodos para cambiar los textos dinámicamente
    public void setTituloIzquierdo(String titulo) {
        textoIzquierda.setText(titulo);
    }

    public void setTituloDerecho(String titulo) {
        textoDerecha.setText(titulo);
    }

    public void setTextos(String izquierdo, String derecho) {
        setTituloIzquierdo(izquierdo);
        setTituloDerecho(derecho);
    }

    // Getters por si necesitas los textos actuales
    public String getTituloIzquierdo() {
        return textoIzquierda.getText();
    }

    public String getTituloDerecho() {
        return textoDerecha.getText();
    }
}
