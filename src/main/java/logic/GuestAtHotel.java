/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import domain.Room;
import domain.RoomReservation;
import domain.SpecialRequest;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author lenovo
 */
public class GuestAtHotel {
  private Guest guest;
  private Reservation reservation;
  private LocalDate arrivalDate;
  private LocalDate departureDate;
  
  public GuestAtHotel(Guest guest, Reservation reservation, LocalDate arrivalDate){
    if(guest.getValidatedReservationList().contains(reservation) && reservation.getStartDate().compareTo(arrivalDate)==0){
      this.guest=guest;
      this.reservation=reservation;
      this.arrivalDate=arrivalDate;
    }
    this.departureDate=null;
  }
  
  public void setDepartureDate(LocalDate departure){
    this.departureDate=departure;
  }
  
  public void makeArrivalChanges(){
    Room room = this.reservation.getRoom();
    this.removeReservationFromRoom();
    room.removeGuestFromWaitlist(this.guest);
    room.occupy();
    room.getRoomOccupied().setStart(this.reservation.getStartDate());
    room.getRoomOccupied().setEnd(this.reservation.getEndDate());
  }
  
  public void makeDepartureChanges(){
    Room room = this.reservation.getRoom();
    room.leaveRoom();
    Stay stay = new Stay(room, this.arrivalDate, this.departureDate);
    this.guest.getGuestStays().push(stay);
  }
  
  public void removeReservationFromRoom(){
    for(RoomReservation reserv:this.reservation.getRoom().getReservations()){
      if(reserv.getStart().compareTo(arrivalDate)==0){
        this.reservation.getRoom().getReservations().remove(reserv);
      }
    }
  }
  
  
}
