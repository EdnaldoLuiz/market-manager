package br.com.luiz.smktsystem.view.panel.auth;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import br.com.luiz.smktsystem.view.MainPanel;
import br.com.luiz.smktsystem.view.panel.ResetPasswordPanel;

public class RightPanel extends JPanel {

    public RightPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        Font labelFont = new Font("Arial", Font.BOLD, 18);
        Font inputFont = new Font("Arial", Font.PLAIN, 16);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(labelFont);
        JTextField emailField = new JTextField(20);
        emailField.setPreferredSize(new Dimension(230, 30));
        emailField.setFont(inputFont);

        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setFont(labelFont);
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(230, 30));
        passwordField.setFont(inputFont);

        JButton loginButton = new JButton("LOGIN");
        loginButton.setBackground(Color.RED);
        loginButton.setForeground(Color.WHITE);
        loginButton.setPreferredSize(new Dimension(250, 40));
        loginButton.setFont(new Font("Arial", Font.BOLD, 20));
        loginButton.addActionListener(e -> openMainView());

        JLabel forgotPasswordLabel = new JLabel("Esqueceu a Senha?");
        forgotPasswordLabel.setFont(inputFont);
        forgotPasswordLabel.setForeground(Color.BLUE);
        forgotPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotPasswordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openResetPasswordView();
            }
        });
        

        gbc.gridy++;
        gbc.insets = new Insets(10, 5, 5, 5);
        add(emailLabel, gbc);
        gbc.gridy++;
        add(emailField, gbc);
        gbc.gridy++;
        add(passwordLabel, gbc);
        gbc.gridy++;
        add(passwordField, gbc);
        gbc.gridy++;

        // Adicionar JLabel para "Esqueceu a Senha?" e alinhar à direita
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = 2; // Estender a célula para a direita
        add(forgotPasswordLabel, gbc);

        // Resetar configurações do GridBagConstraints
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 1;

        gbc.gridy++;
        gbc.insets = new Insets(20, 5, 5, 5);
        add(loginButton, gbc);
    }

    private void openMainView() {
        JFrame mainFrame = new JFrame();
        mainFrame.setSize(1300, 900);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.add(new MainPanel());
        mainFrame.setVisible(true);
    }

    private void openResetPasswordView() {
        JFrame resetPasswordFrame = new JFrame();
        resetPasswordFrame.setSize(500, 450);
        resetPasswordFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resetPasswordFrame.setLocationRelativeTo(null);
        resetPasswordFrame.add(new ResetPasswordPanel());
        resetPasswordFrame.setVisible(true);
    }
}

