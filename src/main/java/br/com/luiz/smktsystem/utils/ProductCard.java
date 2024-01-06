package br.com.luiz.smktsystem.utils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ProductCard extends JPanel {

    private final String productName;
    private final String description;
    private final double price;
    private final ImageIcon productImage;

    public ProductCard(String productName, String description, double price, byte[] base64Image) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.productImage = new ImageIcon(base64Image);

        initComponents();
    }

    private void initComponents() {
        setPreferredSize(new Dimension(250, 300));
        setBackground(Color.white);
        setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel() {
            @Override
            public void paintComponent(Graphics g) {
                System.out.println("Image loaded successfully");
                g.drawImage(productImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        add(imageLabel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new GridLayout(4, 1));
        JLabel nameLabel = new JLabel(productName);
        JLabel descriptionLabel = new JLabel(description);
        JLabel priceLabel = new JLabel(String.format("R$ %.2f", price));

        JButton deleteButton = new JButton("Excluir");
        deleteButton.addActionListener(e -> handleDeleteButtonClick());

        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        priceLabel.setForeground(Color.BLUE);

        infoPanel.add(nameLabel);
        infoPanel.add(descriptionLabel);
        infoPanel.add(priceLabel);
        infoPanel.add(deleteButton);

        add(infoPanel, BorderLayout.SOUTH);

        Border redBorder = BorderFactory.createLineBorder(Color.RED, 2);
        setBorder(redBorder);
    }

    private void handleDeleteButtonClick() {
        // Adicione aqui a lógica para excluir o produto
        JOptionPane.showMessageDialog(this, "Produto excluído com sucesso!");
        // Atualize a visualização após excluir o produto
        updateView();
    }

    private void updateView() {
        // Adicione aqui a lógica para atualizar a visualização conforme necessário
    }
}
