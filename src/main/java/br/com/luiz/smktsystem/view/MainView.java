package br.com.luiz.smktsystem.view;

import javax.swing.*;

import br.com.luiz.smktsystem.service.EmployeerService;
import br.com.luiz.smktsystem.view.panel.MainPanel;

public class MainView extends JFrame {
    
    private EmployeerService employeerService;

    public MainView(EmployeerService employeerService) {
        this.employeerService = employeerService;
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new MainPanel(employeerService));
        setVisible(true);
    }
}

