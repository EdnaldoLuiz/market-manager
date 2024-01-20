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
    private List<ProductCard> productCards = new ArrayList<>();
    private JPanel wideArea;
    private JPanel currentPanel;
    private JPanel headerPanel;
    private Category selectedCategory = Category.FOOD;
    private JButton selectedCategoryButton;

    public ContentComponent() {
        setLayout(new BorderLayout(10, 10));
        setBackground(CustomColor.DARK_GRAY);

        wideArea = createWideArea();

        currentPanel = createWideArea();
        JScrollPane scrollPane = createWideAreaScrollPane();
        scrollPane.setViewportView(currentPanel);

        wideArea.add(createHeader(), BorderLayout.NORTH);

        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(CustomColor.DARK_GRAY);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));

        ImageIcon addIcon = ResizeIcon.createResizedIcon("src/main/resources/icons/add-product.png", 50, 50);
        JButton addButton = new JButton(addIcon);
        addButton.setPreferredSize(new Dimension(40, 40));
        addButton.setBackground(Color.RED);
        addButton.setBorderPainted(false);
        addButton.setFocusPainted(false);
        addButton.addActionListener(e -> showAddProductDialog());

        buttonPanel.add(addButton, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private JScrollPane createWideAreaScrollPane() {
        setBackground(CustomColor.DARK_GRAY);
        wideArea = createWideArea();
        scrollPane = new JScrollPane(wideArea);
        scrollPane.setPreferredSize(new Dimension(990, 450));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        scrollPane.setBorder(BorderFactory.createLineBorder(CustomColor.DARK_GRAY, 15, false));

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
                updateViewForPanel(createProductsPanel());
                break;
            case "Funcionarios":
                updateViewForPanel(createEmployeesPanel());
                break;
        }
    }

    private JPanel createEmployeesPanel() {
        return new EmployeesPanel(new EmployeerService(new EmployeerDAO(JpaUtil.getEntityManager())));
    }

    private JPanel createProductsPanel() {
        return new ProductsPanel(new ProductService(new ProductDAO(JpaUtil.getEntityManager())));
    }

    private class CategoryButtonListener implements ActionListener {
        private final Category category;
    
        public CategoryButtonListener(Category category) {
            this.category = category;
        }
    
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            if (selectedCategoryButton != null) {
                selectedCategoryButton.setBackground(CustomColor.MAIN_RED);
            }
    
            selectedCategory = category;
            updateViewForPanel(createWideArea());
            selectedCategoryButton = (CustomButton) e.getSource();
            selectedCategoryButton.setBackground(Color.YELLOW);
        }
    }

    private JPanel createHeader() {
        JPanel headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(700, 50));
        headerPanel.setLayout(new GridLayout(1, 4, 0, 0));
    
        headerPanel.setBorder(BorderFactory.createLineBorder(CustomColor.DARK_GRAY, 10, false));
    
        for (Category category : Category.values()) {
            CustomButton categoryButton = new CustomButton(category.getDescription(), CustomColor.MAIN_RED, Color.WHITE, 100, 50, 18);
            CategoryButtonListener listener = new CategoryButtonListener(category);
            categoryButton.addActionListener(listener);
            headerPanel.add(categoryButton);
    
            if (category == selectedCategory) {
                selectedCategoryButton = categoryButton;
                selectedCategoryButton.setForeground(Color.YELLOW);
            }
        }
    
        return headerPanel;
    }
    

    private JPanel createWideArea() {
        JPanel wideArea = new JPanel(new BorderLayout());
        wideArea.setBackground(Color.GRAY);
    
        headerPanel = createHeader();
        wideArea.add(headerPanel, BorderLayout.NORTH);
    
        JPanel productsPanel = new JPanel(new GridLayout(0, 4, 10, 10));
        productsPanel.setBackground(CustomColor.DARK_GRAY);
        ProductService productService = new ProductService(new ProductDAO(JpaUtil.getEntityManager()));
        
        List<Product> products;
        
        if (selectedCategory != null) {
            products = productService.getProductsByCategory(selectedCategory);
        } else {
            products = productService.getAllProducts();
        }
    
        for (Product product : products) {
            ProductRegisterDTO productDTO = ProductMapper.INSTANCE.entityToRegisterDTO(product);
            productsPanel.add(createProductCard(productDTO));
        }
    
        wideArea.add(productsPanel, BorderLayout.CENTER);
    
        return wideArea;
    }
}