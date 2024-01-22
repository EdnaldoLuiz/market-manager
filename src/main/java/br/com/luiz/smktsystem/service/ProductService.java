package br.com.luiz.smktsystem.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.luiz.smktsystem.app.enums.Category;
import br.com.luiz.smktsystem.app.model.Product;
import br.com.luiz.smktsystem.service.dao.ProductDAO;
import br.com.luiz.smktsystem.service.dto.ProductListDTO;
import br.com.luiz.smktsystem.service.dto.ProductRegisterDTO;
import br.com.luiz.smktsystem.service.mapper.ProductMapper;

public class ProductService {

    private ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO; 
    }

    public void registerProduct(ProductRegisterDTO registerDTO) {
        Product product = new Product(registerDTO);
        productDAO.createProduct(product);
    }

    public void deleteProduct(String name) {
        productDAO.deleteProduct(name);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public void updateProduct(ProductListDTO updatedProductDTO) {
        Product existingProduct = productDAO.findProductByName(updatedProductDTO.getName());
    
        if (existingProduct != null) {
            existingProduct.setName(updatedProductDTO.getName());
            existingProduct.setPrice(updatedProductDTO.getPrice());
            existingProduct.setQuantity(updatedProductDTO.getQuantity());
            productDAO.updateProduct(existingProduct);
        }
    }

    public List<ProductListDTO> listProducts() {
        List<Product> products = productDAO.getAllProducts();
        return products.stream()
                .map(employeer -> ProductMapper.INSTANCE.entityToListDTO(employeer))
                .collect(Collectors.toList());
    }

    public List<ProductListDTO> getProductsByCategory(Category category) {
        return productDAO.getProductsByCategory(category).stream()
                .map(product -> ProductMapper.INSTANCE.entityToListDTO(product))
                .collect(Collectors.toList());
    }

    public List<ProductListDTO> sortProducts(List<ProductListDTO> products, String order) {
        products.sort((p1, p2) -> {
            if (order.equals("Crescente")) {
                return p1.getName().compareTo(p2.getName());
            } else {
                return p2.getName().compareTo(p1.getName());
            }
        });
        return products;
    }
}

