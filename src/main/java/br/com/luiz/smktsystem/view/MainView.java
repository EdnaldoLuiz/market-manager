package br.com.luiz.smktsystem.view;

import javax.swing.*;

public class MainView extends JFrame {

    public MainView() {
        setSize(1300, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        add(new MainPanel());
        setVisible(true);
    }
}
