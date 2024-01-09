package br.com.luiz.smktsystem.view.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.luiz.smktsystem.app.enums.Category;
import br.com.luiz.smktsystem.app.model.Product;
import br.com.luiz.smktsystem.service.EmployeerService;
import br.com.luiz.smktsystem.service.ProductService;
import br.com.luiz.smktsystem.service.dao.EmployeerDAO;
import br.com.luiz.smktsystem.service.dao.ProductDAO;
import br.com.luiz.smktsystem.service.dto.ProductRegisterDTO;
import br.com.luiz.smktsystem.service.mapper.ProductMapper;
import br.com.luiz.smktsystem.utils.ImageByteUtil;
import br.com.luiz.smktsystem.utils.JpaUtil;
import br.com.luiz.smktsystem.utils.ProductCard;
import br.com.luiz.smktsystem.utils.javax.CustomScrollbar;
import br.com.luiz.smktsystem.view.panel.employee.EmployeesPanel;

public class ContentComponent extends JPanel {

    private JScrollPane scrollPane;
    private JPanel wideArea;
    private JPanel standardArea;
    private JPanel narrowArea;
    private List<ProductCard> productCards = new ArrayList<>();
    private JPanel currentPanel;

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

    private void updateViewForPanel(JPanel panel) {
        currentPanel = panel;
        scrollPane.setViewportView(currentPanel);
        revalidate();
        repaint();
    }
    public void handleSidebarOption(String option) {
        switch (option) {
            case "Mercadorias":
                updateViewForPanel(createWideArea());
                break;
            case "Funcionarios":
                updateViewForPanel(createEmployeesPanel());
                break;
        }
    }

    private JPanel createEmployeesPanel() {
        return new EmployeesPanel(new EmployeerService(new EmployeerDAO(JpaUtil.getEntityManager()))); 
    }

    public ContentComponent() {
        setLayout(new BorderLayout(10, 10));

        JPanel headerPanel = createHeader();
        add(headerPanel, BorderLayout.NORTH);

        wideArea = createWideArea();
        

        standardArea = createStandardArea();
        narrowArea = createNarrowArea();

        JPanel sidePanel = new JPanel(new BorderLayout());
        JPanel innerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        innerPanel.add(standardArea);
        innerPanel.add(narrowArea);

        currentPanel = createWideArea();
        JScrollPane scrollPane = createWideAreaScrollPane();
        scrollPane.setViewportView(currentPanel);
        add(scrollPane, BorderLayout.CENTER);

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
        wideArea.setBackground(Color.GRAY);
    
        int externalMargin = 10;
        wideArea.setBorder(BorderFactory.createEmptyBorder(externalMargin, externalMargin, externalMargin, externalMargin));
    
        ProductService productService = new ProductService(new ProductDAO(JpaUtil.getEntityManager()));
        List<Product> products = productService.getAllProducts();
    
        for (Product product : products) {
            ProductRegisterDTO productDTO = ProductMapper.INSTANCE.entityToRegisterDTO(product);
            wideArea.add(createProductCard(productDTO));
        }
        wideArea.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
    
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
        JDialog dialog = new JDialog((Frame) null, "Adicionar Produto", true);
        dialog.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField productNameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField quantityField = new JTextField();
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
        addButton.addActionListener(e -> {
            try {
                String productName = productNameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Escolha uma imagem");
                fileChooser.setFileFilter(new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png", "gif"));

                int userSelection = fileChooser.showOpenDialog(dialog);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    byte[] imageBytes = ImageByteUtil.encode(selectedFile.getAbsolutePath());

                    ProductRegisterDTO registerDTO = new ProductRegisterDTO();
                    registerDTO.setProductName(productName);
                    registerDTO.setProductPrice(price);
                    registerDTO.setProductQuantity(quantity);
                    registerDTO.setCategory(Category.FOOD);
                    registerDTO.setImage(imageBytes);

                    updateViewForNewProduct(registerDTO);

                    ProductService productService = new ProductService(new ProductDAO(JpaUtil.getEntityManager()));
                    productService.registerProduct(registerDTO);
                    JOptionPane.showMessageDialog(dialog, "Produto cadastrado com sucesso!");
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Operação cancelada. Nenhuma imagem selecionada.");
                }
            } catch (NumberFormatException | IOException ex) {
                JOptionPane.showMessageDialog(dialog, "Por favor, insira valores válidos para preço e quantidade.");
            }
        });

        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(addButton, BorderLayout.SOUTH);

        chooseImageButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Escolha uma imagem");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png", "gif"));
        });

        dialog.setSize(new Dimension(400, 250));
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
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