package br.com.luiz.smktsystem.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.luiz.smktsystem.app.model.Employeer;
import br.com.luiz.smktsystem.app.model.Product;
import br.com.luiz.smktsystem.service.dao.ProductDAO;
import br.com.luiz.smktsystem.service.dto.EmployeerListDTO;
import br.com.luiz.smktsystem.service.dto.ProductListDTO;
import br.com.luiz.smktsystem.service.dto.ProductRegisterDTO;
import br.com.luiz.smktsystem.service.mapper.EmployeerMapper;
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

    public void deleteProduct(UUID productId) {
        productDAO.deleteProduct(productId);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public void updateProduct(UUID productId, ProductRegisterDTO updatedProductDTO) {
        Product existingProduct = productDAO.findProductById(productId);
        
        if (existingProduct != null) {
            existingProduct.setName(updatedProductDTO.getName());
            existingProduct.setPrice(updatedProductDTO.getPrice());
            existingProduct.setQuantity(updatedProductDTO.getQuantity());
            existingProduct.setCategory(updatedProductDTO.getCategory());
            productDAO.updateProduct(existingProduct);
        }
    }

    public List<ProductListDTO> listProducts() {
        List<Product> products = productDAO.getAllProducts();
        return products.stream()
                .map(employeer -> ProductMapper.INSTANCE.entityToListDTO(employeer))
                .collect(Collectors.toList());
    }
}

