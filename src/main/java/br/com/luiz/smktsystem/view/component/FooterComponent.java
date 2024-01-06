package br.com.luiz.smktsystem.view.component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import br.com.luiz.smktsystem.utils.javax.CustomColor;

import java.awt.*;

public class FooterComponent extends JPanel {

    public FooterComponent() {
        setPreferredSize(new Dimension(getWidth(), 50));
        setBackground(CustomColor.MAIN_RED); 

        JLabel label = new JLabel("© 2024 Copyright - SmktSystem. Todos os direitos reservados.");
        label.setForeground(Color.WHITE);

        label.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(label);
    }
    // test@gmail.com
}
