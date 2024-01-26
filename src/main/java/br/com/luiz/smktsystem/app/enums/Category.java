package br.com.luiz.smktsystem.app.enums;

public enum Category {
        
        FOOD("Alimentos"),
        CLEANING("Limpeza"),
        OFFICE("Escritório");
    
        private String description;
    
        Category(String description) {
            this.description = description;
        }
    
        public String getDescription() {
            return description;
        }

        public static Category fromDescription(String description) {
            for (Category category : Category.values()) {
                if (category.getDescription().equals(description)) {
                    return category;
                }
            }
            throw new IllegalArgumentException("No enum constant " + Category.class.getCanonicalName() + " with description " + description);
        }
}
