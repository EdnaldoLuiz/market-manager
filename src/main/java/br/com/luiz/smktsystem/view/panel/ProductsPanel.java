package br.com.luiz.smktsystem.view.panel;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import br.com.luiz.smktsystem.app.enums.Category;
import br.com.luiz.smktsystem.service.ProductService;
import br.com.luiz.smktsystem.service.dto.ProductListDTO;
import br.com.luiz.smktsystem.utils.javax.CustomButton;
import br.com.luiz.smktsystem.utils.javax.CustomColor;
import br.com.luiz.smktsystem.view.dialog.AddProductDialog;
import br.com.luiz.smktsystem.view.dialog.ImportProductsDialog;

public class ProductsPanel extends JPanel {

    private static final String FONT_NAME = "Arial";
    private static final int FONT_SIZE = 18;
    private static final String[] ORDER_OPTIONS = { "Crescente", "Decrescente" };

    private ProductService service;
    private JTable productsTable;
    private List<ProductListDTO> productList;

    public ProductsPanel(ProductService service) {
        this.service = service;
        initializeUI();
        this.productList = service.listProducts();
        productList.sort(Comparator.comparing(ProductListDTO::getPrice));
        loadProductData(productList);
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        initializeProductsTable();
        JScrollPane scrollPane = new JScrollPane(productsTable);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        buttonPanel.add(createCategoryComboBox());
        buttonPanel.add(createOrderComboBox());
        buttonPanel.add(createButton("Importar Produtos"));
        buttonPanel.add(createButton("Adicionar Produto"));

        add(buttonPanel, BorderLayout.PAGE_START);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void initializeProductsTable() {
        productsTable = new JTable();
        productsTable.setFont(new Font(FONT_NAME, Font.PLAIN, 16));
        productsTable.setRowHeight(35);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        productsTable.setDefaultRenderer(Object.class, centerRenderer);

        JTableHeader tableHeader = productsTable.getTableHeader();
        tableHeader.setFont(new Font(FONT_NAME, Font.BOLD, 18));
    }

    private JComboBox<String> createCategoryComboBox() {
        JComboBox<String> categoryComboBox = new JComboBox<>();
        for (Category category : Category.values()) {
            categoryComboBox.addItem(category.getDescription());
        }

        categoryComboBox.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE));
        categoryComboBox.setSelectedItem("Todos");
        categoryComboBox.setPreferredSize(new Dimension(200, 40));
        categoryComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedCategory = (String) e.getItem();
                Category category = Category.fromDescription(selectedCategory);
                productList = service.getProductsByCategory(category);
                loadProductData(productList);
            }
        });

        return categoryComboBox;
    }

    private JComboBox<String> createOrderComboBox() {
        JComboBox<String> orderComboBox = new JComboBox<>(ORDER_OPTIONS);
        orderComboBox.setSelectedItem("Crescente");
        orderComboBox.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE));
        orderComboBox.setPreferredSize(new Dimension(200, 40));
        orderComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedOrder = (String) e.getItem();
                List<ProductListDTO> sortedProducts = new ArrayList<>(productList);
                if (selectedOrder.equals("Crescente")) {
                    sortedProducts.sort(Comparator.comparing(ProductListDTO::getPrice));
                } else if (selectedOrder.equals("Decrescente")) {
                    sortedProducts.sort(Comparator.comparing(ProductListDTO::getPrice).reversed());
                }
                loadProductData(sortedProducts);
            }
        });

        return orderComboBox;
    }

    private JButton createButton(String text) {
    JButton button = new CustomButton(text, CustomColor.MAIN_RED, Color.WHITE, 200, 40, FONT_SIZE, e -> {
        if (text.equals("Adicionar Produto")) {
            AddProductDialog dialog = new AddProductDialog(service);
            dialog.pack();
            dialog.setVisible(true);
        } else if (text.equals("Importar Produtos")) {
            ImportProductsDialog dialog = new ImportProductsDialog(service);
            dialog.pack();
            dialog.setVisible(true);
        }
    });
    return button;
}

    private void loadProductData(List<ProductListDTO> productList) {
        this.productList = productList;
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        model.addColumn("Nome");
        model.addColumn("Preço Unitário");
        model.addColumn("Qtd. Estoque");
        model.addColumn("Preço Total");

        final boolean[] isUpdating = { false };

        model.addTableModelListener(e -> {
            if (isUpdating[0]) {
                return;
            }

            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                String columnName = model.getColumnName(column);
                Object newData = model.getValueAt(row, column);

                ProductListDTO product = productList.get(row);
                switch (columnName) {
                    case "Nome":
                        product.setName((String) newData);
                        break;
                    case "Preço Unitário":
                        BigDecimal newPrice = new BigDecimal(newData.toString());
                        product.setPrice(newPrice);
                        product.setTotalPrice(newPrice.multiply(new BigDecimal(product.getQuantity())));
                        break;
                    case "Qtd. Estoque":
                        int newQuantity = Integer.parseInt(newData.toString());
                        product.setQuantity(newQuantity);
                        product.setTotalPrice(product.getPrice().multiply(new BigDecimal(newQuantity)));
                        break;
                }

                service.updateProduct(product);
                isUpdating[0] = true;
                model.setValueAt(product.getTotalPrice(), row, 3);
                isUpdating[0] = false;
            }
        });

        for (ProductListDTO product : productList) {
            model.addRow(new Object[] { product.getName(), "R$ " + product.getPrice(), product.getQuantity(),
            "R$ " + product.getTotalPrice() });
        }

        productsTable.setModel(model);
    }

}
