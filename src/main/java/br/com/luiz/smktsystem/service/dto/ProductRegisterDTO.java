package br.com.luiz.smktsystem.service.dto;

import java.math.BigDecimal;

import br.com.luiz.smktsystem.app.enums.Category;

public class ProductRegisterDTO {

    private String name;
    private BigDecimal price;
    private Integer quantity;
    private Category category;

    public ProductRegisterDTO(String name, BigDecimal price, Integer quantity, Category category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
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
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
