package br.com.luiz.smktsystem.view.component;

import javax.persistence.EntityManager;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

import br.com.luiz.smktsystem.app.enums.Category;
import br.com.luiz.smktsystem.app.model.Product;
import br.com.luiz.smktsystem.service.ProductService;
import br.com.luiz.smktsystem.service.dao.ProductDAO;
import br.com.luiz.smktsystem.service.dto.ProductRegisterDTO;
import br.com.luiz.smktsystem.service.mapper.ProductMapper;
import br.com.luiz.smktsystem.utils.ImageByteUtil;
import br.com.luiz.smktsystem.utils.JpaUtil;
import br.com.luiz.smktsystem.utils.ProductCard;
import br.com.luiz.smktsystem.utils.javax.CustomScrollbar;

public class ContentComponent extends JPanel {

    public ContentComponent() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(createWideArea());
        scrollPane.setPreferredSize(new Dimension(990, 450));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        scrollPane.getVerticalScrollBar().setUI(new CustomScrollbar());

        add(scrollPane);
        add(createEmptyArea("Standard Area", 650, 200));
        add(createEmptyArea("Narrow Area", 330, 200));

        JButton addButton = new JButton("Adicionar Produto");
        addButton.addActionListener(e -> showAddProductDialog());
        add(addButton);
    }

    private JPanel createWideArea() {
        JPanel wideArea = new JPanel(new GridLayout(0, 4, 10, 10));

        ProductService productService = new ProductService(new ProductDAO(JpaUtil.getEntityManager()));
        List<Product> products = productService.getAllProducts();

        for (Product product : products) {
            ProductRegisterDTO productDTO = ProductMapper.INSTANCE.entityToRegisterDTO(product);

            wideArea.add(new ProductCard(
                productDTO.getProductName(),
                productDTO.getCategory().toString(),
                productDTO.getProductPrice(),
                productDTO.getImage()
        ));
        }

        return wideArea;
    }

    private JPanel createEmptyArea(String areaName, int width, int height) {
        JPanel area = new JPanel();
        area.setPreferredSize(new Dimension(width, height));
        Border redBorder = BorderFactory.createLineBorder(Color.RED, 2);
        area.setBorder(BorderFactory.createTitledBorder(redBorder, areaName));
        return area;
    }

    private void showAddProductDialog() {
        String productName = JOptionPane.showInputDialog(this, "Nome do Produto:");
        String priceStr = JOptionPane.showInputDialog(this, "Preço do Produto:");
        String quantityStr = JOptionPane.showInputDialog(this, "Quantidade do Produto:");

        try {
            double price = Double.parseDouble(priceStr);
            int quantity = Integer.parseInt(quantityStr);

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Escolha uma imagem");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png", "gif"));

            int userSelection = fileChooser.showOpenDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                byte[] imageBytes = ImageByteUtil.encode(selectedFile.getAbsolutePath());

                EntityManager entityManager = JpaUtil.getEntityManager();
                ProductDAO productDAO = new ProductDAO(entityManager);
                ProductService productService = new ProductService(productDAO);

                ProductRegisterDTO registerDTO = new ProductRegisterDTO();
                registerDTO.setProductName(productName);
                registerDTO.setProductPrice(price);
                registerDTO.setProductQuantity(quantity);
                registerDTO.setCategory(Category.FOOD);
                registerDTO.setImage(imageBytes);

                updateView();

                productService.registerProduct(registerDTO);
                JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Operação cancelada. Nenhuma imagem selecionada.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos para preço e quantidade.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao ler a imagem selecionada.");
        }
    }

    private void updateView() {
        // Atualizar a visualização conforme necessário
        removeAll();
        revalidate();
        repaint();
    }
}
