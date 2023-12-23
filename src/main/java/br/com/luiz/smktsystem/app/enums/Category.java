package br.com.luiz.smktsystem.app.enums;

public enum Category {
        
        FOOD("Alimentos"),
        CLEANING("Limpeza"),
        HYGIENE("Higiene"),
        OFFICE("Escritório"),
        ELECTRONICS("Eletrônicos"),
        OTHERS("Outros");
    
        private String description;
    
        Category(String description) {
            this.description = description;
        }
    
        public String getDescription() {
            return description;
        }
    
}
