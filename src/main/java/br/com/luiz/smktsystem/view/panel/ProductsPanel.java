package br.com.luiz.smktsystem.view.panel;

import java.awt.*;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import br.com.luiz.smktsystem.service.ProductService;
import br.com.luiz.smktsystem.service.dto.ProductListDTO;

public class ProductsPanel extends JPanel {

    private ProductService service;
    private JTable productsTable;

    public ProductsPanel(ProductService service) {
        this.service = service;
        initializeUI();
        loadEmployeeData();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        productsTable = new JTable();
        productsTable.setFont(new Font("Arial", Font.PLAIN, 16));

        productsTable.setRowHeight(30);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        productsTable.setDefaultRenderer(Object.class, centerRenderer);

        JTableHeader tableHeader = productsTable.getTableHeader();
        tableHeader.setFont(new Font("Arial", Font.BOLD, 18));

        JScrollPane scrollPane = new JScrollPane(productsTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadEmployeeData() {
        List<ProductListDTO> productList = service.listProducts();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nome");
        model.addColumn("Preço Unitário");
        model.addColumn("Qtd. Estoque");
        model.addColumn("Preço Total");

        for (ProductListDTO product : productList) {
            model.addRow(new Object[] { product.getName(), product.getPrice(), product.getQuantity(), product.getTotalPrice()});
        }

        productsTable.setModel(model);
    }
}
