package br.com.luiz.smktsystem.view.panel.auth;

import javax.swing.*;
import br.com.luiz.smktsystem.service.EmployeerService;
import br.com.luiz.smktsystem.utils.ResizeIcon;
import br.com.luiz.smktsystem.view.shared.modal.Modal;

import java.awt.*;

public class ResetPasswordPanel extends JPanel {

    private EmployeerService employeerService;

    public ResetPasswordPanel(EmployeerService employeerService) {
        this.employeerService = employeerService;
        initComponents();
    }

    private static ImageIcon createResizedIcon(String path, int width, int height) {
        return ResizeIcon.createResizedIcon(path, width, height);
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

        ImageIcon originalIcon = createResizedIcon("src/main/resources/icons/forgot-password.png", 80, 80);
        JLabel iconLabel = new JLabel(originalIcon);

        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        iconPanel.add(iconLabel);

        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 5, 20, 5);
        add(iconPanel, gbc);

        JLabel emailLabel = new JLabel("Insira seu E-mail:");
        emailLabel.setFont(labelFont);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
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

        submitButton.addActionListener(e -> submitAction(emailField.getText()));

        add(submitButton, gbc);
    }

    private void submitAction(String email) {
        if (employeerService.isEmailOnDatabase(email)) {
            Modal.showSuccessDialog(this);
        } else {
            Modal.showErrorDialog(this, "O e-mail informado ainda não foi cadastrado.");
        }
    }
}
