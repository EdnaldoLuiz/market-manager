package br.com.luiz.smktsystem.view.panel.auth;

import javax.swing.*;

import br.com.luiz.smktsystem.utils.javax.color.CustomColor;

import java.awt.*;

public class LeftPanel extends JPanel {

    public LeftPanel() {
        setBackground(CustomColor.MAIN_RED);
        setPreferredSize(new Dimension(450, getHeight()));
    }
}
