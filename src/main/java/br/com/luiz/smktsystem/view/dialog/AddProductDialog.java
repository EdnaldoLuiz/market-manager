package br.com.luiz.smktsystem.view.dialog;

import javax.swing.*;

import br.com.luiz.smktsystem.app.enums.Category;
import br.com.luiz.smktsystem.service.ProductService;
import br.com.luiz.smktsystem.service.dto.ProductRegisterDTO;
import br.com.luiz.smktsystem.utils.javax.CustomButton;
import br.com.luiz.smktsystem.utils.javax.CustomColor;

import java.awt.*;
import java.math.BigDecimal;

public class AddProductDialog extends JFrame {

    private JTextField nameField;
    private JTextField priceField;
    private JTextField quantityField;
    private JComboBox<String> categoryComboBox;
    private ProductService service;

    public AddProductDialog(ProductService service) {
        this.service = service;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        pack();
        setLocationRelativeTo(null);
        setSize(600, 400);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        Font font = new Font("Arial", Font.PLAIN, 18);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Nome:");
        nameLabel.setFont(font);
        add(nameLabel, gbc);
        nameField = new JTextField(20);
        nameField.setFont(font);
        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel priceLabel = new JLabel("Preço:");
        priceLabel.setFont(font);
        add(priceLabel, gbc);
        priceField = new JTextField(20);
        priceField.setFont(font);
        gbc.gridx = 1;
        add(priceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel quantityLabel = new JLabel("Quantidade:");
        quantityLabel.setFont(font);
        add(quantityLabel, gbc);
        quantityField = new JTextField(20);
        quantityField.setFont(font);
        gbc.gridx = 1;
        add(quantityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel categoryLabel = new JLabel("Categoria:");
        categoryLabel.setFont(font);
        add(categoryLabel, gbc);
        categoryComboBox = new JComboBox<>();
        categoryComboBox.setFont(font);
        for (Category category : Category.values()) {
            categoryComboBox.addItem(category.getDescription());
        }
        gbc.gridx = 1;
        add(categoryComboBox, gbc);

        CustomButton addButton = new CustomButton("Adicionar Produto", CustomColor.MAIN_RED, Color.WHITE, 170, 40, 18,
                e -> addProduct());
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(addButton, gbc);
    }

    private void addProduct() {
        String name = nameField.getText();
        BigDecimal price = new BigDecimal(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());
        Category category = Category.fromDescription((String) categoryComboBox.getSelectedItem());

        ProductRegisterDTO registerDTO = new ProductRegisterDTO(name, price, quantity, category);
        service.registerProduct(registerDTO);
        nameField.setText("");
        priceField.setText("");
        quantityField.setText("");
        categoryComboBox.setSelectedIndex(0);
    }
}