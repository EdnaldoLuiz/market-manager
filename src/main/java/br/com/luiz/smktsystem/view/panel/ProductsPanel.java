package br.com.luiz.smktsystem.view.panel;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
    private static final int FONT_SIZE = 16;
    private static final String[] ORDER_OPTIONS = { "Crescente", "Decrescente" };

    private ProductService service;
    private JTable productsTable;
    private List<ProductListDTO> productList;

    public ProductsPanel(ProductService service) {
        this.service = service;
        initializeUI();
        this.productList = service.getProductsByCategory(Category.FOOD);
        sortProductList("Crescente");
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
        productsTable.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE));
        productsTable.setRowHeight(30);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        productsTable.setDefaultRenderer(Object.class, centerRenderer);

        JTableHeader tableHeader = productsTable.getTableHeader();
        tableHeader.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
    }

    private JComboBox<String> createCategoryComboBox() {
        JComboBox<String> categoryComboBox = new JComboBox<>();
        for (Category category : Category.values()) {
            categoryComboBox.addItem(category.getDescription());
        }

        categoryComboBox.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE));
        categoryComboBox.setSelectedItem("Alimentos");
        categoryComboBox.setPreferredSize(new Dimension(180, 40));
        categoryComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedCategory = (String) e.getItem();

                Category category = Category.fromDescription(selectedCategory);
                productList = service.getProductsByCategory(category);

                String selectedOrder = (String) ((JComboBox<String>) createOrderComboBox()).getSelectedItem();
                sortProductList(selectedOrder);
                loadProductData(productList);
                ((JComboBox<String>) createOrderComboBox()).setSelectedItem(selectedOrder);
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
                sortProductList(selectedOrder);
                loadProductData(productList);
            }
        });

        return orderComboBox;
    }

    private void sortProductList(String order) {
        if (order.equals("Crescente")) {
            productList.sort(Comparator.comparing(ProductListDTO::getPrice));
        } else if (order.equals("Decrescente")) {
            productList.sort(Comparator.comparing(ProductListDTO::getPrice).reversed());
        }
    }

    private JButton createButton(String text) {
        JButton button = new CustomButton(text, CustomColor.MAIN_RED, Color.WHITE, 200, 40, FONT_SIZE, e -> {
            if (text.equals("Adicionar Produto")) {
                AddProductDialog dialog = new AddProductDialog(service);
                dialog.pack();
                dialog.setVisible(true);
            } else if (text.equals("Importar Produtos")) {
                new ImportProductsDialog(service);
            }
        });
        return button;
    }

    private void loadProductData(List<ProductListDTO> productList) {
        this.productList = productList;
        DefaultTableModel model = createTableModel();

        for (ProductListDTO product : productList) {
            model.addRow(new Object[] { product.getName(), "R$ " + product.getPrice(), product.getQuantity(),
                    "R$ " + product.getTotalPrice() });
        }

        productsTable.setModel(model);
        setPriceCellRenderer();
    }

    private DefaultTableModel createTableModel() {
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
                        updateProductPrice(product, newData, row, model, isUpdating);
                        break;
                    case "Qtd. Estoque":
                        updateProductQuantity(product, newData, row, model, isUpdating);
                        break;
                }

                service.updateProduct(product);
            }
        });

        return model;
    }

    private void updateProductPrice(ProductListDTO product, Object newData, int row, DefaultTableModel model,
        boolean[] isUpdating) {
    try {
        BigDecimal newPrice = new BigDecimal(newData.toString());
        product.setPrice(newPrice);
        BigDecimal newTotalPrice = newPrice.multiply(new BigDecimal(product.getQuantity()));
        product.setTotalPrice(newTotalPrice);
        isUpdating[0] = true;
        model.setValueAt("R$ " + newTotalPrice.toString(), row, 3);
        model.fireTableCellUpdated(row, 3);
        isUpdating[0] = false;
    } catch (NumberFormatException e) {
        System.err.println("Invalid price: " + newData);
    }
}

    private void updateProductQuantity(ProductListDTO product, Object newData, int row, DefaultTableModel model,
            boolean[] isUpdating) {
        int newQuantity = Integer.parseInt(newData.toString());
        product.setQuantity(newQuantity);
        product.setTotalPrice(product.getPrice().multiply(new BigDecimal(newQuantity)));
        updateTotalPriceCell(product, row, model, isUpdating);
    }

    private void updateTotalPriceCell(ProductListDTO product, int row, DefaultTableModel model, boolean[] isUpdating) {
        isUpdating[0] = true;
        model.setValueAt("R$ " + product.getTotalPrice().toString(), row, 3);
        isUpdating[0] = false;
    }

    private void setPriceCellRenderer() {
        productsTable.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                if (value instanceof BigDecimal) {
                    value = "R$ " + ((BigDecimal) value).toString();
                }
                setHorizontalAlignment(JLabel.CENTER);
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });
    }

}
