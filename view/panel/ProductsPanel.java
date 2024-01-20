package br.com.luiz.smktsystem.view.panel;

import br.com.luiz.smktsystem.app.model.Product;
import br.com.luiz.smktsystem.service.ProductService;
import br.com.luiz.smktsystem.service.dto.ProductListDTO;
import br.com.luiz.smktsystem.service.mapper.ProductMapper;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class ProductsPanel extends JPanel {

    private ProductService productService;
    private JTable productsTable;

    public ProductsPanel(ProductService productService) {
        this.productService = productService;
        initializeUI();
        loadProductData();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        productsTable = new JTable();
        productsTable.setFont(new Font("Arial", Font.PLAIN, 16));
        productsTable.setRowHeight(34);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        productsTable.setDefaultRenderer(Object.class, centerRenderer);

        JTableHeader tableHeader = productsTable.getTableHeader();
        tableHeader.setFont(new Font("Arial", Font.BOLD, 17));

        JScrollPane scrollPane = new JScrollPane(productsTable);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadProductData() {
        List<Product> productList = productService.getAllProducts();

        List<ProductListDTO> productListDTOs = ProductMapper.INSTANCE.entityToListDTO(productList);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Product Name");
        model.addColumn("Product Price");
        model.addColumn("Product Quantity");

        for (ProductListDTO product : productListDTOs) {
            model.addRow(new Object[] { product.getName(), product.getPrice(), product.getQuantity() });
        }

        productsTable.setModel(model);
    }
}