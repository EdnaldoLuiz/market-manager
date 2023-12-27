package br.com.luiz.smktsystem.view.panel.main;

import javax.swing.*;

import br.com.luiz.smktsystem.view.component.ContentComponent;
import br.com.luiz.smktsystem.view.component.FooterComponent;
import br.com.luiz.smktsystem.view.component.HeaderComponent;

import java.awt.*;

public class MainPanel extends JPanel {

    public MainPanel() {
        setLayout(new BorderLayout());

        add(new HeaderComponent(), BorderLayout.NORTH);
        add(createSidebar(), BorderLayout.WEST);
        add(new ContentComponent(), BorderLayout.CENTER);
        add(new FooterComponent(), BorderLayout.SOUTH);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setBackground(Color.RED);
        sidebar.setPreferredSize(new Dimension(300, getHeight()));
        return sidebar;
    }
}