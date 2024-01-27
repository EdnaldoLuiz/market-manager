package br.com.luiz.smktsystem.app.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.com.luiz.smktsystem.app.enums.Role;
import br.com.luiz.smktsystem.service.dto.EmployeerRegisterDTO;

@Entity
public class Employeer {

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;
  private String name;
  private String email;
  private String cpf;
  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;

  public Employeer() {}

  public Employeer(EmployeerRegisterDTO dto) {
    this.name = dto.getName();
    this.email = dto.getEmail();
    this.cpf = dto.getCpf();
    this.password = dto.getPassword();
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}
