package br.com.luiz.smktsystem.view.component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import br.com.luiz.smktsystem.utils.javax.icon.ResizeIcon;

import java.awt.*;

public class ContentComponent extends JPanel {

    public ContentComponent() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); 

        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new GridLayout(0, 3, 10, 10)); 
        mainContainer.setBackground(Color.lightGray);

        for (int i = 1; i <= 20; i++) {
            JPanel card = createCard("Card " + i, "", 0.0, () -> "src/main/resources/imgs/products/pao.jpg");
            mainContainer.add(card);
        }

        JScrollPane scrollPane = new JScrollPane(mainContainer);
        scrollPane.setPreferredSize(new Dimension(650, 470));
        add(scrollPane);
    }

    private JPanel createCard(String productName, String description, double price, ImageProvider imageProvider) {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(200, 250)); 
        card.setBackground(Color.white);
        card.setLayout(new BorderLayout());

        ImageIcon productImage = ResizeIcon.createResizedIcon(imageProvider.getImagePath(), 200, 150);
        JLabel imageLabel = new JLabel(productImage);
        card.add(imageLabel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        JLabel nameLabel = new JLabel(productName);
        JLabel descriptionLabel = new JLabel(description);
        JLabel priceLabel = new JLabel(String.format("R$ %.2f", price));

        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        priceLabel.setForeground(Color.BLUE);

        infoPanel.add(nameLabel);
        infoPanel.add(descriptionLabel);
        infoPanel.add(priceLabel);

        card.add(infoPanel, BorderLayout.SOUTH);

        return card;
    }

    public interface ImageProvider {
        String getImagePath();
    }
}
