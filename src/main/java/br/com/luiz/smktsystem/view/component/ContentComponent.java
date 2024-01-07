package br.com.luiz.smktsystem.view.component;

import javax.persistence.EntityManager;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

    private JScrollPane scrollPane;
    private JPanel wideArea;
    private JPanel standardArea;
    private JPanel narrowArea;
    private List<ProductCard> productCards = new ArrayList<>();

    private JScrollPane createWideAreaScrollPane() {
        wideArea = createWideArea();
        scrollPane = new JScrollPane(wideArea);
        scrollPane.setPreferredSize(new Dimension(990, 450));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollbar());
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        int spacing = 14;
        scrollPane.setBorder(BorderFactory.createEmptyBorder(spacing, spacing, spacing, spacing));

        return scrollPane;
    }

    public ContentComponent() {
        setLayout(new BorderLayout(10, 10));

        JPanel headerPanel = createHeader();
        add(headerPanel, BorderLayout.NORTH);

        wideArea = createWideArea();
        JScrollPane scrollPane = createWideAreaScrollPane();
        add(scrollPane, BorderLayout.CENTER);

        standardArea = createStandardArea();
        narrowArea = createNarrowArea();

        JPanel sidePanel = new JPanel(new BorderLayout());
        JPanel innerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        innerPanel.add(standardArea);
        innerPanel.add(narrowArea);

        sidePanel.add(innerPanel, BorderLayout.SOUTH);

        add(sidePanel, BorderLayout.SOUTH);
    }

    private class CategoryButtonListener implements ActionListener {
        private final Category category;

        public CategoryButtonListener(Category category) {
            this.category = category;
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            System.out.println("Categoria selecionada: " + category.getDescription());
        }
    }

    private ProductCard createProductCard(ProductRegisterDTO productDTO) {
        ProductCard card = new ProductCard(
                productDTO.getProductName(),
                productDTO.getProductPrice(),
                productDTO.getImage(),
                280,
                getWidth() / 4 - 20,
                productDTO.getId(),
                this);

        productCards.add(card);

        return card;
    }

    private JPanel createHeader() {
        JPanel headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(700, 50));
        headerPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        headerPanel.setLayout(new GridLayout(1, 5, 10, 10));

        for (Category category : Category.values()) {
            JButton categoryButton = new JButton(category.getDescription());
            categoryButton.addActionListener(new CategoryButtonListener(category));
            headerPanel.add(categoryButton);
        }

        return headerPanel;
    }

    private JPanel createWideArea() {
        JPanel wideArea = new JPanel();
        wideArea.setLayout(new GridLayout(0, 4, 10, 10));
        wideArea.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

        ProductService productService = new ProductService(new ProductDAO(JpaUtil.getEntityManager()));
        List<Product> products = productService.getAllProducts();

        for (Product product : products) {
            ProductRegisterDTO productDTO = ProductMapper.INSTANCE.entityToRegisterDTO(product);
            wideArea.add(createProductCard(productDTO));
        }

        return wideArea;
    }

    private JPanel createStandardArea() {
        JPanel standardArea = new JPanel();
        standardArea.setPreferredSize(new Dimension(700, 200));
        standardArea.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        return standardArea;
    }

    private JPanel createNarrowArea() {
        JPanel narrowArea = new JPanel();
        narrowArea.setPreferredSize(new Dimension(300, 200));
        narrowArea.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

        JButton addButton = new JButton("Adicionar Produto");
        addButton.addActionListener(e -> showAddProductDialog());
        narrowArea.add(addButton);

        return narrowArea;
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

                updateViewForNewProduct(registerDTO);

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

    public void updateView() {
        wideArea.removeAll();

        ProductService productService = new ProductService(new ProductDAO(JpaUtil.getEntityManager()));
        List<Product> products = productService.getAllProducts();

        for (Product product : products) {
            ProductRegisterDTO productDTO = ProductMapper.INSTANCE.entityToRegisterDTO(product);
            wideArea.add(createProductCard(productDTO));
        }

        scrollPane.setViewportView(wideArea);
        revalidate();
        repaint();
    }

    public void updateViewForNewProduct(ProductRegisterDTO newProductDTO) {
        wideArea.add(createProductCard(newProductDTO));
        JScrollPane scrollPane = createWideAreaScrollPane();
        scrollPane.setViewportView(wideArea);
        revalidate();
        repaint();
    }
}