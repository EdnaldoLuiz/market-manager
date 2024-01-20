package br.com.luiz.smktsystem.view.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;

import br.com.luiz.smktsystem.utils.javax.CustomColor;
import br.com.luiz.smktsystem.utils.products.ResizeIcon;

public class SidebarComponent extends JPanel {

    private JPanel selectedOption = null;
    private ContentComponent contentComponent;

    public SidebarComponent(ContentComponent contentComponent) {
        this.contentComponent = contentComponent;
        setBackground(Color.RED);
        setPreferredSize(new Dimension(250, getHeight()));
        setBorder(new EmptyBorder(40, 0, 0, 0));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel mercadoriasOption = createOption("Mercadorias",
                ResizeIcon.createResizedIcon("src/main/resources/icons/market.png", 30, 30));
        add(mercadoriasOption);
        selectOption(mercadoriasOption);

        add(createOption("Funcionarios",
                ResizeIcon.createResizedIcon("src/main/resources/icons/employee.png", 30, 30)));
    }

    private JPanel createOption(String text, ImageIcon icon) {
        JPanel optionPanel = new JPanel();
        optionPanel.setBackground(Color.RED);
        optionPanel.setBorder(new EmptyBorder(20, 10, 20, 10));
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.X_AXIS));

        JLabel textLabel = new JLabel(text);
        JLabel iconLabel = new JLabel(icon);

        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 22));

        optionPanel.add(Box.createHorizontalGlue());
        optionPanel.add(textLabel);
        optionPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        optionPanel.add(iconLabel);
        optionPanel.add(Box.createHorizontalGlue());

        optionPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectOption(optionPanel);
                contentComponent.handleSidebarOption(text);
            }
        });

        return optionPanel;
    }

    private void selectOption(JPanel optionPanel) {
        if (selectedOption != null) {
            selectedOption.setBackground(Color.RED);
            JLabel selectedLabel = (JLabel) selectedOption.getComponent(1);
            selectedLabel.setForeground(Color.WHITE);
            selectedLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        }
        selectedOption = optionPanel;
        selectedOption.setBackground(CustomColor.HOVER_RED);
        JLabel selectedLabel = (JLabel) selectedOption.getComponent(1);
        selectedLabel.setFont(new Font("Arial", Font.BOLD, 22));
    }
}
