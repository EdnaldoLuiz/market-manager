package br.com.luiz.smktsystem.utils.javax;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomButton extends JButton {

    public CustomButton(String text, Color backgroundColor, Color foregroundColor, int width, int height, int fontSize) {
        super(text);
        setPreferredSize(new Dimension(width, height));
        setBackground(backgroundColor);
        setForeground(foregroundColor);
        setFont(new Font("Arial", Font.BOLD, fontSize));
    }

    public CustomButton(String text, Color backgroundColor, Color foregroundColor, int width, int height, int fontSize, ActionListener actionListener) {
        this(text, backgroundColor, foregroundColor, width, height, fontSize);
        addActionListener(actionListener);
    }
}