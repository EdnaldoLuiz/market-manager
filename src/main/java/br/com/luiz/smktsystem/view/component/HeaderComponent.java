package br.com.luiz.smktsystem.view.component;

import javax.swing.*;

import br.com.luiz.smktsystem.utils.javax.CustomColor;

import java.awt.*;

public class HeaderComponent extends JPanel {

    public HeaderComponent() {
        setPreferredSize(new Dimension(getWidth(), 100));
        setBackground(CustomColor.MAIN_RED);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        // ImageIcon originalIcon = new ImageIcon("src/main/resources/icons/icon.png");

        // Image originalImage = originalIcon.getImage();
        // Image scaledImage = originalImage.getScaledInstance(140, 80, Image.SCALE_SMOOTH);

        // ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // JLabel logoLabel = new JLabel(scaledIcon);
        // logoLabel.setBorder(BorderFactory.createEmptyBorder(5, 30, 0, 0));
        // add(logoLabel);
    }
}
