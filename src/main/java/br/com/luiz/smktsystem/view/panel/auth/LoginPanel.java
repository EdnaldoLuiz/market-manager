package br.com.luiz.smktsystem.view.panel.auth;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    public LoginPanel() {
        setLayout(new BorderLayout());
        add(new LeftPanel(), BorderLayout.WEST);
        add(new RightPanel(), BorderLayout.CENTER);
    }
}
