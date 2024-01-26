package br.com.luiz.smktsystem.service.dto;

import br.com.luiz.smktsystem.app.enums.Role;

public class EmployeerListDTO {

    private String name;
    private String email;
    private String cpf;
    private Role role;

    public EmployeerListDTO() {}

    public EmployeerListDTO(String name, String email, String cpf) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
