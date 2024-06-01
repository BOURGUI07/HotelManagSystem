/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package domain;

/**
 *
 * @author lenovo
 */
public enum SpecialRequest {
  NON_SMOKING_ROOM("Non smoking room"),
  HIGH_FLOOR("High floor"),
  LOW_FLOOR("Low floor"),
  QUIET("Quiet room"),
  AIRPORT_SHUTTLE_SERVICE("Airport shuttle service"),
  CAR_RENTAL_ARRANGEMENTS("Car rental arrangement"),
  IN_ROOM_DINING_OPTION("In-room dining option"),
  PET_FRIENDLY_ROOM("Pet-friendly room"),
  HIGH_SPEED_INTERNET_ACCESS("High speed internet access");
  
  private final String string;
  SpecialRequest(String string){
    this.string=string;
  }
  
  @Override
  public String toString(){
    return this.string;
  }
}
