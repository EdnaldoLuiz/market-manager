package br.com.luiz.smktsystem.view.panel.auth;

import javax.swing.*;
import java.awt.*;

import br.com.luiz.smktsystem.view.MainPanel;

public class RightPanel extends JPanel {

    public RightPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel emailLabel = new JLabel("Username:");
        JTextField emailField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");

        add(emailLabel, gbc);
        gbc.gridy++;
        add(emailField, gbc);
        gbc.gridy++;
        add(passwordLabel, gbc);
        gbc.gridy++;
        add(passwordField, gbc);
        gbc.gridy++;
        add(loginButton, gbc);

        loginButton.addActionListener(e -> openMainView());
    }

    private void openMainView() {
        JFrame mainFrame = new JFrame();
        mainFrame.setSize(1300, 900);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.add(new MainPanel());
        mainFrame.setVisible(true);
    }
}
