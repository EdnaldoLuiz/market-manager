package br.com.luiz.smktsystem.model.enums;

public enum Payment {
    
    CREDIT_CARD("Cartão de Crédito"),
    DEBIT_CARD("Cartão de Débito"),
    MONEY("Dinheiro"),
    PIX("PIX");

    private String description;

    Payment(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
