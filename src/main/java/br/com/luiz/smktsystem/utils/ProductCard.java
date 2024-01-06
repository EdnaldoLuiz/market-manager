package br.com.luiz.smktsystem.utils;

import javax.swing.*;
import javax.swing.border.Border;

import br.com.luiz.smktsystem.utils.javax.CustomColor;

import java.awt.*;

public class ProductCard extends JPanel {

    private final String productName;
    private final double price;
    private final ImageIcon productImage;

    public ProductCard(String productName, double price, byte[] base64Image, int desiredHeight, int desiredWidth) {
        this.productName = productName;
        this.price = price;
        this.productImage = new ImageIcon(base64Image);
        initComponents(desiredHeight, desiredWidth);
    }

    private void initComponents(int desiredHeight, int desiredWidth) {
        setPreferredSize(new Dimension(desiredWidth, desiredHeight));
        setBackground(Color.white);
        setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel() {
            @Override
            public void paintComponent(Graphics g) {
                System.out.println("Image loaded successfully");
                int scaledWidth = getWidth();
                int scaledHeight = (int) ((double) productImage.getIconHeight() / productImage.getIconWidth()
                        * scaledWidth);
                g.drawImage(productImage.getImage(), 0, 0, scaledWidth, scaledHeight, this);
            }
        };

        add(imageLabel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new GridLayout(4, 1));
        infoPanel.setBackground(CustomColor.MAIN_RED);
        JLabel nameLabel = new JLabel(productName);
        JLabel priceLabel = new JLabel(String.format("R$ %.2f", price));

        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton deleteButton = new JButton("Excluir");
        deleteButton.addActionListener(e -> deleteProduct());
        JButton editButton = new JButton("Editar");
        deleteButton.addActionListener(e -> editProduct());

        priceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        priceLabel.setForeground(Color.YELLOW);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setForeground(Color.WHITE);

        infoPanel.add(nameLabel, BorderLayout.CENTER);
        Border priceLabelBorder = BorderFactory.createEmptyBorder(0, 10, 0, 0);
        priceLabel.setBorder(priceLabelBorder);

        infoPanel.add(priceLabel, BorderLayout.SOUTH);
        infoPanel.add(deleteButton, BorderLayout.EAST);
        infoPanel.add(editButton, BorderLayout.EAST);

        add(infoPanel, BorderLayout.SOUTH);

        Border redBorder = BorderFactory.createLineBorder(Color.RED, 2);
        setBorder(redBorder);
    }

    private void deleteProduct() {
        JOptionPane.showMessageDialog(this, "Produto excluído com sucesso!");
        updateView();
    }

    private void editProduct() {
        JOptionPane.showMessageDialog(this, "Produto atualizado!");
        updateView();
    }

    private void updateView() {
    }
}
