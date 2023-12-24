package br.com.luiz.smktsystem.view;

import javax.swing.*;

import br.com.luiz.smktsystem.view.panel.auth.LoginPanel;

public class LoginView extends JFrame {

    public LoginView() {
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new LoginPanel());
        setVisible(true);
    }
}
