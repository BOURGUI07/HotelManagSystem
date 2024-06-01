/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.Objects;

/**
 *
 * @author lenovo
 */
public class Admin {
  private String username;
  private String password;
  private String email;
  
  public Admin(String name, String code, String email){
    this.email=email;
    this.password=code;
    this.username=name;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 71 * hash + Objects.hashCode(this.username);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Admin other = (Admin) obj;
    return Objects.equals(this.username, other.username);
     
  }
  
  
}
