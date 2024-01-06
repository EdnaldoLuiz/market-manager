package br.com.luiz.smktsystem.app.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import br.com.luiz.smktsystem.app.enums.Category;

@Entity
public class Product {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String productName;
    private Double productPrice;
    private Integer productQuantity;
    private Category category;

    @Column(length = 100000, columnDefinition = "TEXT")
    private String image;

    public Product() {}

    public Product(String productName, Double productPrice, Integer productQuantity, Category category, String image) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.category = category;
        this.image = image;
    }

    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
