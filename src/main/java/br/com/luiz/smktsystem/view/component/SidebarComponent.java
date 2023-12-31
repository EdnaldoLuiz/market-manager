package br.com.luiz.smktsystem.view.component;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;


public class SidebarComponent extends JPanel {
    
    public SidebarComponent() {
        setBackground(Color.RED);
        setPreferredSize(new Dimension(250, getHeight()));
    }
}
