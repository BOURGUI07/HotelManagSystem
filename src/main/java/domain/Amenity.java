/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package domain;

/**
 *
 * @author lenovo
 */
public enum Amenity {
  WIFI("Wi-Fi"),
  AIR_CONDITIONING("Air Conditioning"),
  HEATING("Heating"),
  MINI_FRIDGE("Mini-Fridge"),
  MICROWAVE("Microwave"),
  COFFEE_MAKER("Coffee Maker"),
  IRONING_FACILITIES("Ironing facilities"),
  HAIRDRYER("Hairdryer");
  
  private final String string;
  Amenity(String string){
    this.string=string;
  }
  @Override
  public String toString(){
    return this.string;
  }
}
