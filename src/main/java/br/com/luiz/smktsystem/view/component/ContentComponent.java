package br.com.luiz.smktsystem.view.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import br.com.luiz.smktsystem.app.enums.Category;
import br.com.luiz.smktsystem.app.model.Product;
import br.com.luiz.smktsystem.service.EmployeerService;
import br.com.luiz.smktsystem.service.ProductService;
import br.com.luiz.smktsystem.service.dao.EmployeerDAO;
import br.com.luiz.smktsystem.service.dao.ProductDAO;
import br.com.luiz.smktsystem.service.dto.ProductRegisterDTO;
import br.com.luiz.smktsystem.service.mapper.ProductMapper;
import br.com.luiz.smktsystem.utils.hibernate.JpaUtil;
import br.com.luiz.smktsystem.utils.javax.CustomButton;
import br.com.luiz.smktsystem.utils.javax.CustomColor;
import br.com.luiz.smktsystem.utils.products.ProductCard;
import br.com.luiz.smktsystem.utils.products.ResizeIcon;
import br.com.luiz.smktsystem.view.dialog.AddProductDialog;
import br.com.luiz.smktsystem.view.panel.EmployeesPanel;

public class ContentComponent extends JPanel {

    private JScrollPane scrollPane;
    private JPanel wideArea;
    private List<ProductCard> productCards = new ArrayList<>();
    private JPanel currentPanel;

    private JScrollPane createWideAreaScrollPane() {
        wideArea = createWideArea();
        scrollPane = new JScrollPane(wideArea);
        scrollPane.setPreferredSize(new Dimension(990, 450));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

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

        wideArea = createWideArea();

        currentPanel = createWideArea();
        JScrollPane scrollPane = createWideAreaScrollPane();
        scrollPane.setViewportView(currentPanel);

        wideArea.add(createHeader(), BorderLayout.NORTH);

        add(scrollPane, BorderLayout.CENTER);

        ImageIcon addIcon = ResizeIcon.createResizedIcon("src/main/resources/icons/add-product.png", 50, 50);
        JButton addButton = new JButton(addIcon);
        addButton.setPreferredSize(new Dimension(40, 40));
        addButton.setBackground(Color.RED);
        addButton.setBorderPainted(false);
        addButton.setFocusPainted(false);
        addButton.addActionListener(e -> showAddProductDialog());

        wideArea.add(addButton, BorderLayout.SOUTH);
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
        headerPanel.setLayout(new GridLayout(1, 5, 0, 0));

        for (Category category : Category.values()) {
            CustomButton categoryButton = new CustomButton(category.getDescription(), Color.RED, Color.WHITE, 100, 30,
                    14);
            categoryButton.addActionListener(new CategoryButtonListener(category));
            headerPanel.add(categoryButton);
        }

        return headerPanel;
    }

    private JPanel createWideArea() {
        JPanel wideArea = new JPanel(new BorderLayout());
        wideArea.setBackground(Color.GRAY);

        JPanel headerPanel = createHeader();
        wideArea.add(headerPanel, BorderLayout.NORTH);

        JPanel productsPanel = new JPanel(new GridLayout(0, 4, 10, 10));
        productsPanel.setBackground(CustomColor.MAIN_RED);

        ProductService productService = new ProductService(new ProductDAO(JpaUtil.getEntityManager()));
        List<Product> products = productService.getAllProducts();

        for (Product product : products) {
            ProductRegisterDTO productDTO = ProductMapper.INSTANCE.entityToRegisterDTO(product);
            productsPanel.add(createProductCard(productDTO));
        }

        wideArea.add(productsPanel, BorderLayout.CENTER);
        wideArea.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

        return wideArea;
    }

    private void showAddProductDialog() {
        AddProductDialog dialog = new AddProductDialog((Frame) null, "Adicionar Produto", true);
        dialog.setVisible(true);
        updateView();
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
