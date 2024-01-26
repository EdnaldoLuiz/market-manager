package br.com.luiz.smktsystem.service.dto;

import br.com.luiz.smktsystem.app.enums.Role;

public class EmployeerRegisterDTO {
    
    private String name;
    private String email;
    private String cpf;
    private String password;
    private Role role;

    public EmployeerRegisterDTO(String name, String email, String cpf, Role role) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
