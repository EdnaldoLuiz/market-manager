package br.com.luiz.smktsystem.utils;

import javax.swing.*;

import br.com.luiz.smktsystem.utils.javax.icon.ResizeIcon;

import java.awt.*;

public class ProductCard extends JPanel {

    public ProductCard(String productName, String description, double price, String imagePath) {
        setPreferredSize(new Dimension(150, 150));
        setBackground(Color.white);
        setLayout(new BorderLayout());

        ImageIcon productImage = ResizeIcon.createResizedIcon(imagePath, 200, 150);
        JLabel imageLabel = new JLabel() {
            @Override
            public void paintComponent(Graphics g) {
                g.drawImage(productImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        add(imageLabel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        JLabel nameLabel = new JLabel(productName);
        JLabel descriptionLabel = new JLabel(description);
        JLabel priceLabel = new JLabel(String.format("R$ %.2f", price));

        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        priceLabel.setForeground(Color.BLUE);

        infoPanel.add(nameLabel);
        infoPanel.add(descriptionLabel);
        infoPanel.add(priceLabel);

        add(infoPanel, BorderLayout.SOUTH);
    }
}
