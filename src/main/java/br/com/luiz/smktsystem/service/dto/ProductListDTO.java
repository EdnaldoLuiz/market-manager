package br.com.luiz.smktsystem.service.dto;

import java.math.BigDecimal;

public class ProductListDTO {
    
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal totalPrice;

    public ProductListDTO() {}
    
    public ProductListDTO(String name, BigDecimal price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = price.multiply(new BigDecimal(quantity));
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

    public BigDecimal getTotalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }    
}
