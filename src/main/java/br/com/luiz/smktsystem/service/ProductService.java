package br.com.luiz.smktsystem.service;

import java.util.List;

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

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }
}

