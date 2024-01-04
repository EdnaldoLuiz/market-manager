package br.com.luiz.smktsystem.view.panel.main;

import javax.swing.*;

import br.com.luiz.smktsystem.service.ProductService;
import br.com.luiz.smktsystem.view.component.ContentComponent;
import br.com.luiz.smktsystem.view.component.FooterComponent;
import br.com.luiz.smktsystem.view.component.HeaderComponent;
import br.com.luiz.smktsystem.view.component.SidebarComponent;

import java.awt.*;

public class MainPanel extends JPanel {

    private ProductService productService;

    public MainPanel() {
        setLayout(new BorderLayout());
        add(new SidebarComponent(), BorderLayout.WEST);
        add(new HeaderComponent(), BorderLayout.NORTH);
        add(new FooterComponent(), BorderLayout.SOUTH);
        add(new ContentComponent(productService), BorderLayout.CENTER);
    }
}