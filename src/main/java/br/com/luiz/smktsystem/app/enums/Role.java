package br.com.luiz.smktsystem.app.enums;

public enum Role {
    
    EMPLOYEE("Funcionário"),
    ADMIN("Admin");

    private String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Role fromDescription(String description) {
        for (Role role : Role.values()) {
            if (role.getDescription().equals(description)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No enum constant " + Role.class.getCanonicalName() + " with description " + description);
    }
}