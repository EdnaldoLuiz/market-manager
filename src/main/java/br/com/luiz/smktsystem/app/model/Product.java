package br.com.luiz.smktsystem.app.model;

import javax.persistence.Id;

import br.com.luiz.smktsystem.app.enums.Category;

public class Product {
    
    @Id
    private Long id;
    private String productName;
    private Double productPrice;
    private Integer productQuantity;
    private Category category;

    public Product() {}

    public Product(String productName, Double productPrice, Integer productQuantity, Category category) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.category = category;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
