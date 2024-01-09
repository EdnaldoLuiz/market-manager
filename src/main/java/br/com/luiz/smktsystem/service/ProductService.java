package br.com.luiz.smktsystem.service;

import java.util.List;
import java.util.UUID;

import br.com.luiz.smktsystem.app.model.Product;
import br.com.luiz.smktsystem.service.dao.ProductDAO;
import br.com.luiz.smktsystem.service.dto.ProductRegisterDTO;
import br.com.luiz.smktsystem.service.mapper.ProductMapper;

public class ProductService {

    private ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO; 
    }

    public void registerProduct(ProductRegisterDTO registerDTO) {
        Product product = ProductMapper.INSTANCE.registerToEntity(registerDTO);
        productDAO.createProduct(product);
    }

    public void deleteProduct(UUID productId) {
        productDAO.deleteProduct(productId);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public void updateProduct(UUID productId, ProductRegisterDTO updatedProductDTO) {
        Product existingProduct = productDAO.findProductById(productId);
        
        if (existingProduct != null) {
            existingProduct.setProductName(updatedProductDTO.getProductName());
            existingProduct.setProductPrice(updatedProductDTO.getProductPrice());
            existingProduct.setProductQuantity(updatedProductDTO.getProductQuantity());
            existingProduct.setCategory(updatedProductDTO.getCategory());
            productDAO.updateProduct(existingProduct);
        }
    }
}

