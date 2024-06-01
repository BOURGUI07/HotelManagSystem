/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author lenovo
 */
public class RoomReservation {
  private LocalDate start;
  private LocalDate end;
  
  public RoomReservation(LocalDate start, LocalDate end){
    this.start=start;
    this.end=end;
  }
  
  public LocalDate getStart() {
    return start;
  }

  public LocalDate getEnd() {
    return end;
  }
  
  @Override
  public int hashCode() {
    int hash = 7;
    hash = 97 * hash + Objects.hashCode(this.start);
    hash = 97 * hash + Objects.hashCode(this.end);
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
    final RoomReservation other = (RoomReservation) obj;
    if (!Objects.equals(this.start, other.start)) {
      return false;
    }
    return Objects.equals(this.end, other.end);
  }
  
  
}
