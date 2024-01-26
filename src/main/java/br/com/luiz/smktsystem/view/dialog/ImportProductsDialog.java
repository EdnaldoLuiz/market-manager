package br.com.luiz.smktsystem.view.dialog;

import javax.swing.*;
import java.io.File;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import br.com.luiz.smktsystem.app.enums.Category;
import br.com.luiz.smktsystem.service.ProductService;
import br.com.luiz.smktsystem.service.dto.ProductRegisterDTO;

public class ImportProductsDialog extends JFrame {

    private ProductService service;
    private static final Pattern LINE_PATTERN = Pattern.compile("\\s*([\\p{L}\\s]+)\\s+R\\$\\s+(\\d+\\.\\d+)\\s+([\\p{L}\\s]+)\\s+(\\d+)\\s*");

    public ImportProductsDialog(ProductService service) {
        this.service = service;
        importProducts();
    }

    private void importProducts() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            importProductsFromFile(selectedFile);
        }
    }

    private void importProductsFromFile(File file) {
        try {
            PDDocument document = PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);
            String[] lines = text.split("\n");
            for (int i = 3; i < lines.length - 1; i++) {
                processLine(lines[i], i);
            }
            dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void processLine(String line, int lineNumber) {
        Matcher matcher = LINE_PATTERN.matcher(line);
        if (matcher.matches()) {
            String name = matcher.group(1).trim();
            String priceStr = matcher.group(2).trim();
            String categoryStr = matcher.group(3).trim();
            String quantityStr = matcher.group(4).trim();
            try {
                BigDecimal price = new BigDecimal(priceStr);
                int quantity = Integer.parseInt(quantityStr);
                Category category = mapToCategory(categoryStr);
                ProductRegisterDTO product = new ProductRegisterDTO(name, price, quantity, category);
                service.registerProduct(product);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        } 
    }

    private Category mapToCategory(String description) {
        switch (description) {
            case "Alimento":
                return Category.FOOD;
            case "Limpeza":
                return Category.CLEANING;
            case "Escritório":
                return Category.OFFICE;
            default:
                throw new IllegalArgumentException("Invalid category: " + description);
        }
    }
}