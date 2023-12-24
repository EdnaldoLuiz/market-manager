package br.com.luiz.smktsystem.view;

import javax.swing.*;

import br.com.luiz.smktsystem.view.component.ContentPanel;
import br.com.luiz.smktsystem.view.component.FooterComponent;
import br.com.luiz.smktsystem.view.component.HeaderComponent;

import java.awt.*;

public class MainPanel extends JPanel {

    public MainPanel() {
        setLayout(new BorderLayout());

        add(new HeaderComponent(), BorderLayout.NORTH);
        add(new ContentPanel(), BorderLayout.CENTER);
        add(new FooterComponent(), BorderLayout.SOUTH);
    }
}
