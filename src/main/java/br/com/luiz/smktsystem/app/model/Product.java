package br.com.luiz.smktsystem.app.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.Type;

import br.com.luiz.smktsystem.app.enums.Category;
import br.com.luiz.smktsystem.service.dto.ProductRegisterDTO;

@Entity
public class Product {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private Category category;
    private BigDecimal totalPrice;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] image;

    public Product() {}

    public Product(ProductRegisterDTO product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.category = product.getCategory();
        this.image = product.getImage();
        totalPrice = totalPrice();
    }

    private BigDecimal totalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
        this.totalPrice = totalPrice();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        this.totalPrice = totalPrice();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
