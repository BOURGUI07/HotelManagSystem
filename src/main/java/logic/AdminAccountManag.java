/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import domain.Admin;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author lenovo
 */
public class AdminAccountManag {
  private List<Admin> adminList;
  private Map<String, String> map;
  private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
  
  public AdminAccountManag(){
    this.adminList = new ArrayList<>();
    this.map = new HashMap<>();
  }
  
  public List<Admin> getAdminList() {
    return adminList;
  }

  public Map<String, String> getMap() {
    return map;
  }
  
  public void registerAdmin(Admin admin){
    if(!this.adminList.contains(admin)){
      this.adminList.add(admin);
      this.map.put(admin.getUsername(), admin.getPassword());
    }
  }
  
  public boolean isAdminRegistrated(Admin admin){
    return this.adminList.contains(admin);
  }
  
  public boolean doesUserNameAlreadyExists(String username){
    return this.map.containsKey(username);
  }
  
  public boolean doesPasswordAlreadyExists(String password){
    return this.map.containsValue(password);
  }
  
  public String getPasswordForName(String username){
    return this.map.get(username);
  }
  
  public String getEmailForUserName(String name){
    String email = null;
    for(Admin admin:this.adminList){
      if(admin.getUsername().equals(name)){
        email= admin.getEmail();
      }
    }
    return email;
  }
  
  public boolean isValidEmail(String email){
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
  }
  
  public boolean isSecure(String password) {
    if (password == null || password.isEmpty()) {
        return false;
    }
    
    // Define regex patterns
    String digitRegex = ".*\\d.*";
    String uppercaseRegex = ".*[A-Z].*";
    String lowercaseRegex = ".*[a-z].*";
    String specialCharRegex = ".*[@#$%^&+=].*";
    
    // Check password against each pattern
    boolean hasDigit = password.matches(digitRegex);
    boolean hasUppercase = password.matches(uppercaseRegex);
    boolean hasLowercase = password.matches(lowercaseRegex);
    boolean hasSpecialChar = password.matches(specialCharRegex);
    
    // Check password length
    boolean isLengthValid = password.length() >= 8;
    
    // Print debug information
    
    // Return true only if all conditions are met
    return hasDigit && hasUppercase && hasLowercase && hasSpecialChar && isLengthValid;
}



}
