package br.com.luiz.smktsystem.view.component;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

import br.com.luiz.smktsystem.utils.ResizeIcon;

public class SidebarComponent extends JPanel {

    public SidebarComponent() {
        setBackground(Color.RED);
        setPreferredSize(new Dimension(250, getHeight()));
        setBorder(new EmptyBorder(40, 0, 0, 0)); 

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(createOption("Mercadorias", ResizeIcon.createResizedIcon("src/main/resources/icons/market.png", 30, 30)));
        add(createOption("  Funcionarios", ResizeIcon.createResizedIcon("src/main/resources/icons/employee.png", 30, 30)));
        add(createOption("Estatisticas", ResizeIcon.createResizedIcon("src/main/resources/icons/info.png", 30, 30)));
    }

    private JPanel createOption(String text, ImageIcon icon) {
        JPanel optionPanel = new JPanel();
        optionPanel.setBackground(Color.RED);
        optionPanel.setBorder(new EmptyBorder(20, 10, 20, 10)); 
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.X_AXIS));
    
        JLabel textLabel = new JLabel(text);
        JLabel iconLabel = new JLabel(icon);
    
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.BOLD, 22));
    
        optionPanel.add(Box.createHorizontalGlue());
        optionPanel.add(textLabel);
        optionPanel.add(Box.createRigidArea(new Dimension(10, 0))); 
        optionPanel.add(iconLabel);
        optionPanel.add(Box.createHorizontalGlue()); 
    
        return optionPanel;
    }
    
}
