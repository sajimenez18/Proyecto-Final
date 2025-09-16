package components;

import javax.swing.*;
import views.InicioSecion;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import control.LoginControl;

public class LoginPanel {

    private JPanel panelLogin;

    public LoginPanel() { 
        // Panel contenedor principal
        panelLogin = new JPanel(new GridBagLayout());
        panelLogin.setBackground(new Color(240, 240, 240));
        panelLogin.setPreferredSize(new Dimension(500, 500));

        // Panel tipo tarjeta
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
        JTextField entradaCorreo = new JTextField();
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
        JPasswordField entradaContraseña = new JPasswordField();
        entradaContraseña.setPreferredSize(new Dimension(200, 35));
        entradaContraseña.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        cardPanel.add(entradaContraseña, gbc);

        // ===== Botón Ingresar =====
        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setPreferredSize(new Dimension(120, 40));
        btnIngresar.setBackground(new Color(60, 130, 200));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnIngresar.setFocusPainted(false);
        btnIngresar.setBorderPainted(false);
        btnIngresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        cardPanel.add(btnIngresar, gbc);

        // Añadir cardPanel centrado en panelLogin
        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.anchor = GridBagConstraints.CENTER;
        panelLogin.add(cardPanel, gbcMain);

        // Acción del botón
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entradaUsuario = entradaCorreo.getText();
                String entradaContra = new String(entradaContraseña.getPassword());

                if (new LoginControl().validacionDatos(entradaUsuario, entradaContra)) {
                    Component comp = (Component) e.getSource();
                    JFrame ventanaPapa = (JFrame) SwingUtilities.getWindowAncestor(comp);
                    ventanaPapa.dispose();
                    new InicioSecion();
                } else {
                    JOptionPane.showMessageDialog(panelLogin, 
                            "Credenciales incorrectas", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JPanel getJPanel() {
        return panelLogin;
    }
}
