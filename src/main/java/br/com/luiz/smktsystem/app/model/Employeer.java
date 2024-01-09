package br.com.luiz.smktsystem.app.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.com.luiz.smktsystem.app.enums.Role;

@Entity
public class Employeer {

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;
  private String name;
  private String email;
  private String cpf;
  private String password;
  private Role role;

  public Employeer() {}

  public Employeer(String name, String email, String cpf, String password) {
    this.name = name;
    this.email = email;
    this.cpf = cpf;
    this.password = password;
    this.role = Role.EMPLOYEE;
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
