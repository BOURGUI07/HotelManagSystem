/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.time.LocalDate;

/**
 *
 * @author lenovo
 */
public class RoomOccupied {
  private LocalDate start;
  private LocalDate end;
  
  public RoomOccupied() {
    this.start = LocalDate.now();
    this.end = LocalDate.now();
  }

  public LocalDate getStart() {
    return start;
  }

  public LocalDate getEnd() {
    return end;
  }

  public void setStart(LocalDate start) {
    this.start = start;
  }

  public void setEnd(LocalDate end) {
    this.end = end;
  }
  
  
}
