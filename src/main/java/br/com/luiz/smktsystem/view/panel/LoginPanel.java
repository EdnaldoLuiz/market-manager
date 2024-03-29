package br.com.luiz.smktsystem.view.panel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import br.com.luiz.smktsystem.app.enums.Role;
import br.com.luiz.smktsystem.app.model.Employeer;
import br.com.luiz.smktsystem.service.EmployeerService;
import br.com.luiz.smktsystem.service.dao.AuthenticationListener;
import br.com.luiz.smktsystem.service.dao.AuthenticationManager;
import br.com.luiz.smktsystem.utils.javax.CustomButton;
import br.com.luiz.smktsystem.utils.products.ResizeIcon;
import br.com.luiz.smktsystem.view.dialog.ResetPasswordDialog;
import br.com.luiz.smktsystem.view.shared.modal.Modal;

public class LoginPanel extends JFrame implements AuthenticationListener {

    private JTextField emailField;
    private JPasswordField passwordField;
    private AuthenticationManager authenticationManager;
    private EmployeerService employeerService;

    public LoginPanel(EmployeerService employeerService) {
        this.employeerService = employeerService;
        initComponents();
        this.authenticationManager = new AuthenticationManager(employeerService, this);
    }

    private void initComponents() {
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel leftPanel = buildLeftPanel();
        JPanel rightPanel = buildRightPanel();

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        add(new JPanel(), BorderLayout.EAST);

        setVisible(true);
    }

    private JPanel buildLeftPanel() {
        JPanel leftPanel = new JPanel(new BorderLayout());
        ImageIcon backgroundIcon = ResizeIcon.createResizedIcon("/icons/auth-logo.jpg", 400, 500);
        JLabel backgroundLabel = new JLabel(backgroundIcon);

        leftPanel.add(backgroundLabel, BorderLayout.CENTER);

        return leftPanel;
    }

    private JPanel buildRightPanel() {

        JPanel rightPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Font inputFont = new Font("Arial", Font.PLAIN, 14);
        ImageIcon loginIcon = ResizeIcon.createResizedIcon("/icons/login.png", 120, 120);
        JLabel iconLabel = new JLabel(loginIcon);

        gbc.gridy++;
        gbc.insets = new Insets(-40, 5, 5, 5);
        rightPanel.add(iconLabel, gbc);

        JLabel emailLabel = createLabel("Email:", labelFont);
        emailField = createTextField(20, inputFont);

        JLabel passwordLabel = createLabel("Senha:", labelFont);
        passwordField = createPasswordField(20, inputFont);

        JButton loginButton = createLoginButton();

        JLabel forgotPasswordLabel = createForgotPasswordLabel(inputFont);

        gbc.gridy++;
        gbc.insets = new Insets(10, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        rightPanel.add(emailLabel, gbc);
        gbc.gridy++;
        rightPanel.add(emailField, gbc);
        gbc.gridy++;
        rightPanel.add(passwordLabel, gbc);
        gbc.gridy++;
        rightPanel.add(passwordField, gbc);
        gbc.gridy++;

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = 2;
        rightPanel.add(forgotPasswordLabel, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 1;

        gbc.gridy++;
        gbc.insets = new Insets(20, 5, 5, 5);
        rightPanel.add(loginButton, gbc);

        return rightPanel;
    }

    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }

    private JTextField createTextField(int columns, Font font) {
        JTextField textField = new JTextField(columns);
        textField.setPreferredSize(new Dimension(200, 23));
        textField.setFont(font);
        return textField;
    }

    private JPasswordField createPasswordField(int columns, Font font) {
        JPasswordField passwordField = new JPasswordField(columns);
        passwordField.setPreferredSize(new Dimension(200, 23));
        passwordField.setFont(font);
        return passwordField;
    }

    private JButton createLoginButton() {
        return new CustomButton("LOGIN", Color.RED, Color.WHITE, 200, 30, 18, e -> openMainView());
    }

    private JLabel createForgotPasswordLabel(Font font) {
        JLabel forgotPasswordLabel = new JLabel("Esqueceu a Senha?");
        forgotPasswordLabel.setFont(font);
        forgotPasswordLabel.setForeground(Color.BLUE);
        forgotPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotPasswordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openResetPasswordView();
            }
        });
        return forgotPasswordLabel;
    }

    private void openMainView() {
        String userEmail = emailField.getText();
        char[] userPasswordChars = passwordField.getPassword();
        String userPassword = new String(userPasswordChars);

        authenticationManager.authenticate(userEmail, userPassword);
    }

    @Override
    public void onAuthenticationSuccess() {
        Employeer authenticatedUser = employeerService.getAuthenticatedUser();
        if (authenticatedUser.getRole() == Role.ADMIN) {
            Modal.showAdminDialog(this);
        }

        dispose();
        JFrame mainFrame = new JFrame();
        mainFrame.setSize(1100, 700);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.add(new MainPanel(employeerService));
        mainFrame.setVisible(true);
    }

    @Override
    public void onAuthenticationFailure() {
        Modal.showErrorDialog(this, "   Credenciais inválidas! Tente novamente.");
    }

    private void openResetPasswordView() {
        JFrame resetPasswordFrame = new JFrame();
        resetPasswordFrame.setSize(400, 350);
        resetPasswordFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resetPasswordFrame.setLocationRelativeTo(null);
        resetPasswordFrame.add(new ResetPasswordDialog(employeerService));
        resetPasswordFrame.setVisible(true);
    }
}
