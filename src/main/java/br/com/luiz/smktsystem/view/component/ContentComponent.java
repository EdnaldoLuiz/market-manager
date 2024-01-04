package br.com.luiz.smktsystem.view.component;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import br.com.luiz.smktsystem.app.enums.Category;
import br.com.luiz.smktsystem.service.ProductService;
import br.com.luiz.smktsystem.service.dto.ProductRegisterDTO;
import br.com.luiz.smktsystem.utils.AddProductButton;
import br.com.luiz.smktsystem.utils.ProductCard;
import br.com.luiz.smktsystem.utils.javax.color.CustomColor;

public class ContentComponent extends JPanel {

    private ProductService productService;

    public ContentComponent(ProductService productService) {
        this.productService = productService;

        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Usando FlowLayout
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(createEmptyArea("Wide Area", 990, 450));
        add(createEmptyArea("Standard Area", 650, 200));
        add(createEmptyArea("Narrow Area", 330, 200));
    }

    private JPanel createEmptyArea(String areaName, int width, int height) {
        JPanel area = new JPanel();
        area.setPreferredSize(new Dimension(width, height));
        Border redBorder = BorderFactory.createLineBorder(Color.RED, 2);
        area.setBorder(BorderFactory.createTitledBorder(redBorder, areaName));
        return area;
    }

    private void showAddProductDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Escolha uma imagem");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png", "gif"));

        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try {
                byte[] imageBytes = Files.readAllBytes(selectedFile.toPath());

                String productName = JOptionPane.showInputDialog(this, "Nome do Produto:");
                String priceStr = JOptionPane.showInputDialog(this, "Preço do Produto:");
                String quantityStr = JOptionPane.showInputDialog(this, "Quantidade do Produto:");

                try {
                    double price = Double.parseDouble(priceStr);
                    int quantity = Integer.parseInt(quantityStr);

                    ProductRegisterDTO registerDTO = new ProductRegisterDTO();
                    registerDTO.setProductName(productName);
                    registerDTO.setProductPrice(price);
                    registerDTO.setProductQuantity(quantity);
                    registerDTO.setCategory(Category.FOOD);
                    registerDTO.setImage(imageBytes);

                    productService.registerProduct(registerDTO);
                    updateView();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos para preço e quantidade.");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateView() {
    }

    public interface ImageProvider {
        String getImagePath();
    }
}
