/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package domain;

/**
 *
 * @author lenovo
 */
public enum RoomView {
  SEA_VIEW("See View"),
  CITY_VIEW("City View");
  
  private final String customString;
  
  RoomView(String string){
    this.customString=string;
  }
  
  @Override
  public String toString(){
    return this.customString;
  }
}
