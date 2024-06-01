/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;
import domain.Room;
import domain.SpecialRequest;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 *
 * @author lenovo
 */
public class Stay {

  
  private Room room;
  private LocalDate startDate;
  private LocalDate endDate;
  private List<SpecialRequest> specialRequests;
  
  public Stay(Room room, LocalDate start, LocalDate end){
    this.room=room;
    this.endDate=end;
    this.startDate=start;
    this.specialRequests=new ArrayList<>();
  }
  
  public int getNumberOfSatyingDays(){
    return (int) ChronoUnit.DAYS.between(this.startDate, this.endDate);
  }
  
  public void addSpecialRequest(SpecialRequest request){
    this.specialRequests.add(request);
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 71 * hash + Objects.hashCode(this.startDate);
    hash = 71 * hash + Objects.hashCode(this.endDate);
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
    final Stay other = (Stay) obj;
    if (!Objects.equals(this.startDate, other.startDate)) {
      return false;
    }
    return Objects.equals(this.endDate, other.endDate);
  }
  
  
  
  @Override
  public String toString(){
    String a = "The guest stayed for " + this.getNumberOfSatyingDays() + " days in Room whose number is: " + this.room.getNumber()+"\n";
    String b = "From date: " + this.startDate + " to: " + this.endDate + ".\n";
    String c = "During his staying period, the guest made the following requests: \n";
    String d = "";
    for(SpecialRequest request:this.specialRequests){
      d+= request + "\n";
    }
    return a+b+c+d;
  }
  
  public Room getRoom() {
    return room;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public List<SpecialRequest> getSpecialRequests() {
    return specialRequests;
  }
}
