package br.com.luiz.smktsystem.service.dto;

public class EmployeerLoginDTO {
    
    private String email;
    private String password;

    public EmployeerLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
