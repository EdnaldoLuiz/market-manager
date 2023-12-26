package br.com.luiz.smktsystem.view.panel;

import javax.swing.*;
import java.awt.*;

public class ResetPasswordPanel extends JPanel {

    public ResetPasswordPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.insets = new Insets(20, 5, 5, 5);

        Font titleFont = new Font("Arial", Font.BOLD, 24);
        Font labelFont = new Font("Arial", Font.BOLD, 18);
        Font inputFont = new Font("Arial", Font.PLAIN, 16);

        JLabel titleLabel = new JLabel("Redefinição de Senha");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.RED);

        gbc.gridy++;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        ImageIcon originalIcon = new ImageIcon("src/main/resources/icons/forgot-password.png");

        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(110, 90, Image.SCALE_SMOOTH);

        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
        iconPanel.add(new JLabel(scaledIcon));

        gbc.gridy++;
        gbc.gridwidth = 2; 
        add(iconPanel, gbc);

        JLabel emailLabel = new JLabel("Insira seu E-mail:");
        emailLabel.setFont(labelFont);

        gbc.anchor = GridBagConstraints.WEST; 
        gbc.gridy++;
        gbc.gridwidth = 1; 
        add(emailLabel, gbc);

        JTextField emailField = new JTextField(25);
        emailField.setPreferredSize(new Dimension(280, 30));
        emailField.setFont(inputFont);

        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.gridy++;
        add(emailField, gbc);
        gbc.gridy++;

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 5, 5, 5);
        JButton submitButton = new JButton("ENVIAR");
        submitButton.setBackground(Color.RED);
        submitButton.setForeground(Color.WHITE);
        submitButton.setPreferredSize(new Dimension(220, 40));
        submitButton.setFont(new Font("Arial", Font.BOLD, 20));
        submitButton.addActionListener(e -> submitAction());

        add(submitButton, gbc);
    }

    private void submitAction() {
        System.out.println("Enviar e-mail para redefinição de senha");
    }
}
