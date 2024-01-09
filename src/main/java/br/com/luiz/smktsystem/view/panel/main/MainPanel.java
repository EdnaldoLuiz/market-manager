package br.com.luiz.smktsystem.view.panel.main;

import javax.swing.*;

import br.com.luiz.smktsystem.service.EmployeerService;
import br.com.luiz.smktsystem.service.ProductService;
import br.com.luiz.smktsystem.view.component.ContentComponent;
import br.com.luiz.smktsystem.view.component.FooterComponent;
import br.com.luiz.smktsystem.view.component.HeaderComponent;
import br.com.luiz.smktsystem.view.component.SidebarComponent;

import java.awt.*;

public class MainPanel extends JPanel {

    private EmployeerService employeerService;  // Certifique-se de declarar essa variável se ainda não estiver

    public MainPanel(EmployeerService employeerService) {
        this.employeerService = employeerService;

        ContentComponent contentComponent = new ContentComponent();  
        setLayout(new BorderLayout());
        add(new SidebarComponent(contentComponent), BorderLayout.WEST); 
        add(new HeaderComponent(employeerService), BorderLayout.NORTH);
        add(new FooterComponent(), BorderLayout.SOUTH);
        add(contentComponent, BorderLayout.CENTER);  
    }
}
