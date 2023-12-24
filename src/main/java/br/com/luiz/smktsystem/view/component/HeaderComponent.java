package br.com.luiz.smktsystem.view.component;

import javax.swing.*;
import java.awt.*;

public class HeaderComponent extends JPanel {

    public HeaderComponent() {
        setPreferredSize(new Dimension(getWidth(), 100));
        setBackground(Color.BLUE);

        JLabel label = new JLabel("Header Component");
        label.setForeground(Color.WHITE);
        add(label);
    }
}