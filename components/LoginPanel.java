package components;

import module.BotonLogin;   // 👈 importa tu botón
import javax.swing.*;
import java.awt.*;

public class LoginPanel {

    private JPanel panelLogin;
    private JTextField entradaCorreo;       // 👈 ahora atributos
    private JPasswordField entradaContraseña;

    public LoginPanel(JFrame ventana) {      // 👈 recibe la ventana para pasársela al botón
        panelLogin = new JPanel(new GridBagLayout());
        panelLogin.setBackground(new Color(240, 240, 240));
        panelLogin.setPreferredSize(new Dimension(500, 500));

        JPanel cardPanel = new JPanel(new GridBagLayout());
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setPreferredSize(new Dimension(350, 320));
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // ===== Título =====
        JLabel titulo = new JLabel("Iniciar Sesión");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(new Color(50, 50, 50));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        cardPanel.add(titulo, gbc);

        // ===== Etiqueta Usuario =====
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        cardPanel.add(lblUsuario, gbc);

        // ===== Campo Usuario =====
        entradaCorreo = new JTextField();
        entradaCorreo.setPreferredSize(new Dimension(200, 35));
        entradaCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        cardPanel.add(entradaCorreo, gbc);

        // ===== Etiqueta Contraseña =====
        JLabel lblContra = new JLabel("Contraseña:");
        lblContra.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        cardPanel.add(lblContra, gbc);

        // ===== Campo Contraseña =====
        entradaContraseña = new JPasswordField();
        entradaContraseña.setPreferredSize(new Dimension(200, 35));
        entradaContraseña.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        cardPanel.add(entradaContraseña, gbc);

        // ===== Botón Ingresar =====
        // Aquí se crea un botón con la clase BotonLogin
        JButton btnIngresar = BotonLogin.crear(entradaCorreo, entradaContraseña, ventana);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        cardPanel.add(btnIngresar, gbc);

        // añade la tarjeta al panel principal
        panelLogin.add(cardPanel);
    }

    public JPanel getJPanel() {
        return panelLogin;
    }

    // opcional: getters de los campos
    public JTextField getEntradaCorreo() {
        return entradaCorreo;
    }

    public JPasswordField getEntradaContraseña() {
        return entradaContraseña;
    }
}
