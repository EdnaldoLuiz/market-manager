package br.com.luiz.smktsystem.view.dialog;

import javax.swing.*;
import br.com.luiz.smktsystem.app.enums.Category;
import br.com.luiz.smktsystem.service.ProductService;
import br.com.luiz.smktsystem.service.dto.ProductRegisterDTO;
import br.com.luiz.smktsystem.utils.javax.CustomButton;
import br.com.luiz.smktsystem.utils.javax.CustomColor;
import br.com.luiz.smktsystem.utils.products.ResizeIcon;
import br.com.luiz.smktsystem.view.panel.ProductsPanel;

import java.awt.*;
import java.math.BigDecimal;

public class AddProductDialog extends JFrame {

    private JTextField nameField;
    private JTextField priceField;
    private JTextField quantityField;
    private JComboBox<String> categoryComboBox;
    private ProductService service;
    private ProductsPanel panel;

    public AddProductDialog(ProductService service, ProductsPanel panel) {
        this.service = service;
        this.panel = panel;
        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        Font font = new Font("Arial", Font.PLAIN, 18);

        ImageIcon adminIcon = ResizeIcon.createResizedIcon("src/main/resources/icons/products.png", 80, 80);
        JLabel iconLabel = new JLabel(adminIcon);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        add(iconLabel, constraints);

        constraints.gridwidth = 1;

        nameField = createField("Nome:", font, 1, constraints, 10);
        priceField = createField("Preço:", font, 2, constraints, 10);
        quantityField = createField("Quantidade:", font, 3, constraints, 10);

        JLabel categoryLabel = new JLabel("Categoria:");
        categoryLabel.setFont(font);
        constraints.gridx = 0;
        constraints.gridy = 4;
        add(categoryLabel, constraints);

        categoryComboBox = new JComboBox<>();
        categoryComboBox.setFont(font);
        for (Category category : Category.values()) {
            categoryComboBox.addItem(category.getDescription());
        }
        constraints.gridx = 1;
        add(categoryComboBox, constraints);

        CustomButton addButton = new CustomButton("Adicionar", CustomColor.MAIN_RED, Color.WHITE, 100, 30, 18,
                e -> addProduct());
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        add(addButton, constraints);

        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - this.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - this.getHeight()) / 2);
        setLocation(x, y);
    }

    private JTextField createField(String labelText, Font font, int gridy, GridBagConstraints constraints, int columns) {
        JLabel label = new JLabel(labelText);
        label.setFont(font);
        constraints.gridx = 0;
        constraints.gridy = gridy;
        add(label, constraints);

        JTextField field = new JTextField(columns);
        field.setFont(font);
        constraints.gridx = 1;
        add(field, constraints);

        return field;
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
        panel.updateProductData();
        this.dispose();
    }
}