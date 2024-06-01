/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package domain;

/**
 *
 * @author lenovo
 */
public enum RoomType {
  SUITE("Suite"), SINGLE("Single"), DOUBLE("Double"), QUEEN("Queen"), KING("King"), FAMILY("Family");
  private final String string;
  RoomType(String string){
    this.string=string;
  }
  @Override
  public String toString(){
    return this.string;
  }
}
