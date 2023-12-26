package br.com.luiz.smktsystem.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.persistence.EntityManager;
import javax.swing.*;
import javax.swing.text.html.parser.Entity;

import br.com.luiz.smktsystem.service.EmployeerService;
import br.com.luiz.smktsystem.service.dao.EmployeerDAO;
import br.com.luiz.smktsystem.utils.JpaUtil;
import br.com.luiz.smktsystem.view.panel.ResetPasswordPanel;

public class LoginView extends JFrame {

    private EmployeerService employeerService; // Certifique-se de ter uma instância válida aqui

    public LoginView(EmployeerService employeerService) {
        this.employeerService = employeerService;
        initComponents();
    }

    private void initComponents() {
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // LeftPanel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.RED);
        leftPanel.setPreferredSize(new Dimension(450, leftPanel.getHeight()));

        // RightPanel
        JPanel rightPanel = new JPanel(new GridBagLayout());
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

        JPanel mainPanel = new JPanel();

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.EAST);

        setVisible(true);
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
        resetPasswordFrame.add(new ResetPasswordPanel(employeerService));
        resetPasswordFrame.setVisible(true);
    }

    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EmployeerDAO employeerDAO = new EmployeerDAO(entityManager);
        EmployeerService employeerService = new EmployeerService(employeerDAO);
        SwingUtilities.invokeLater(() -> new LoginView(employeerService));
    }

}