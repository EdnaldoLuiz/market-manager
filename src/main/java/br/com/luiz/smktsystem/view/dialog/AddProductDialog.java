package br.com.luiz.smktsystem.view.dialog;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import br.com.luiz.smktsystem.app.enums.Category;
import br.com.luiz.smktsystem.service.ProductService;
import br.com.luiz.smktsystem.service.dao.ProductDAO;
import br.com.luiz.smktsystem.service.dto.ProductRegisterDTO;
import br.com.luiz.smktsystem.service.mapper.ProductMapper;
import br.com.luiz.smktsystem.utils.hibernate.JpaUtil;
import br.com.luiz.smktsystem.utils.products.ImageByteUtil;
import br.com.luiz.smktsystem.utils.products.ResizeIcon;

public class AddProductDialog extends JDialog {

    private JTextField productNameField;
    private JTextField priceField;
    private JTextField quantityField;

    public AddProductDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        initializeUI();
    }

    private void initializeUI() {
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        productNameField = new JTextField();
        priceField = new JTextField();
        quantityField = new JTextField();
        JButton chooseImageButton = new JButton("Escolher Imagem");

        inputPanel.add(new JLabel("Nome do Produto:"));
        inputPanel.add(productNameField);
        inputPanel.add(new JLabel("Preço do Produto:"));
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("Quantidade do Produto:"));
        inputPanel.add(quantityField);
        inputPanel.add(new JLabel("Imagem do Produto:"));
        inputPanel.add(chooseImageButton);

        JButton addButton = new JButton("Adicionar");
        addButton.addActionListener(e -> addProduct());
        
        chooseImageButton.addActionListener(e -> chooseImage());

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.CENTER);
        add(addButton, BorderLayout.SOUTH);

        setSize(new Dimension(400, 250));
        setLocationRelativeTo(null);
    }

    private void addProduct() {
        try {
            String productName = productNameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Escolha uma imagem");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png", "gif"));

            int userSelection = fileChooser.showOpenDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                byte[] imageBytes = ImageByteUtil.encode(selectedFile.getAbsolutePath());

                ProductRegisterDTO registerDTO = new ProductRegisterDTO();
                registerDTO.setProductName(productName);
                registerDTO.setProductPrice(price);
                registerDTO.setProductQuantity(quantity);
                registerDTO.setCategory(Category.FOOD);
                registerDTO.setImage(imageBytes);

                ProductService productService = new ProductService(new ProductDAO(JpaUtil.getEntityManager()));
                productService.registerProduct(registerDTO);
                JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Operação cancelada. Nenhuma imagem selecionada.");
            }
        } catch (NumberFormatException | IOException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos para preço e quantidade.");
        }
    }

    private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Escolha uma imagem");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png", "gif"));
        fileChooser.showOpenDialog(this);
    }
}
