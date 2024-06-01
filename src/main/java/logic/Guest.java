/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;
import domain.*;
import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author lenovo
 */
public class Guest implements Comparable<Guest>{
  private Stack<Stay> stays;
  private Stack<Reservation> reservations;
  private String email;
  private String name;
  private String address;
  private String phoneNumber;
  private UUID id;
  private RoomAndGuestManag manag;
  private List<Reservation> cancelledReservations;
  
  public Guest(String name, String email, String phone, String address){
    this.name=name;
    this.email=email;
    this.phoneNumber=phone;
    this.address=address;
    this.stays=new Stack<>();
    this.reservations=new Stack<>();
    this.id=UUID.randomUUID();
    this.manag = new RoomAndGuestManag();
    this.cancelledReservations=new ArrayList<>();
  }
  
  public void setManag(RoomAndGuestManag manag1){
    this.manag=manag1;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 97 * hash + Objects.hashCode(this.name);
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
    final Guest other = (Guest) obj;
    return Objects.equals(this.name, other.name);
  }
  
  public List<Reservation> getCancelledReservationList(){
    return this.cancelledReservations;
  }

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public UUID getId() {
    return id;
  }
  
  public Stack<Reservation> getGuestReservations(){
    return this.reservations;
  }
  
  public boolean makeReservation(LocalDate start, LocalDate end, int numberOfGuests, RoomType roomType, int beds){
    Room room = this.manag.getRoomForTheseCriteria(start, end, numberOfGuests, roomType, beds);
    return room!= null;
  }
  
  public void completeReservationProcess(LocalDate start, LocalDate end, int numberOfGuests, RoomType roomType, int beds){
    if(this.makeReservation(start, end, numberOfGuests, roomType, beds)){
      Room room = this.manag.getRoomForTheseCriteria(start, end, numberOfGuests, roomType, beds);
      Reservation reservation = new Reservation(room, start, end, numberOfGuests, this);
      this.reservations.add(reservation);
      room.addGuestToWaitlist(this);
      RoomReservation roomReserv = new RoomReservation(start,end);
      room.addRoomReservation(roomReserv);
    }
  }
  
 public boolean canReservationBeCancelled(LocalDate startDate){
   LocalDate currentDate = LocalDate.now();
   return currentDate.isBefore(startDate.minusDays(1));
 }
 
 public void addRservationToCancelList(Reservation reserv){
   this.cancelledReservations.add(reserv);
 }
 
 public void cancelReservation(String bookingReference){
   if(this.isValidBookingReference(bookingReference) && !this.reservations.isEmpty()){
     Iterator<Reservation> itr = this.reservations.iterator();
     while(itr.hasNext()){
       Reservation reservation = itr.next();
       if(reservation.getBookingReference().equals(bookingReference)){
         if(this.canReservationBeCancelled(reservation.getStartDate())){
           reservation.getRoom().removeGuestFromWaitlist(this);
           for(RoomReservation reserv:reservation.getRoom().getReservations()){
             if(reserv.getStart().compareTo(reservation.getStartDate())==0){
               reservation.getRoom().removeRoomReservation(reserv);
             }
           }
           this.addRservationToCancelList(reservation);
           
         }
       }
     }
   }
 }
 
 public Reservation getReservationForBookingReference(String reference){
   for(Reservation reserv:this.reservations){
     if(reserv.getBookingReference().equals(reference) && this.isValidBookingReference(reference)){
       return reserv;
     }
   }
   return null;
 } 
 
 public boolean isReservationCancelled(String bookingReference){
    if(this.isValidBookingReference(bookingReference)){
      return this.cancelledReservations.contains(this.getReservationForBookingReference(bookingReference));
    }
    return false;
 }
 
 public int getValidatedRservations(){
   return this.reservations.size() - this.cancelledReservations.size();
 }
  
 
 public  boolean isValidBookingReference(String bookingReference) {
        // Check if the reference number is not null and has the correct length
        if (bookingReference == null || bookingReference.length() != 10) {
            return false;
        }

        // Check if the reference number contains only uppercase letters and digits
        for (char c : bookingReference.toCharArray()) {
            if (!Character.isUpperCase(c) && !Character.isDigit(c)) {
                return false;
            }
        }

        // If all conditions are met, the reference number is considered valid
        return true;
    }

  
  
  public void viewReservations(){
    Stack<Reservation> backup = new Stack<>();
    while(!this.reservations.isEmpty()){
      Reservation reservation = this.reservations.pop();
      backup.push(reservation);
      System.out.println(reservation);
    }
    while(!backup.isEmpty()){
      Reservation reservation = backup.pop();
      this.reservations.push(reservation);
    }
    if(this.reservations.isEmpty()){
      System.out.println("You haven't made any reservations yet!");
    }
  }
  
  public void viewStays(){
    Stack<Stay> backup = new Stack<>();
    while(!this.stays.isEmpty()){
      Stay stay = this.stays.pop();
      backup.push(stay);
      System.out.println(stay);
    }
    while(!backup.isEmpty()){
      Stay stay = backup.pop();
      this.stays.push(stay);
    }
    
    if(this.stays.isEmpty()){
      System.out.println("You haven't had any staying period in the hotel!");
    }
  }
  
  public void viewLastReservation(){
    System.out.println(this.reservations.peek());
    if(this.reservations.isEmpty()){
      System.out.println("You haven't made any reservations yet!");
    }
  }
  
  public void viewLastStay(){
    System.out.println(this.stays.peek());
    if(this.stays.isEmpty()){
      System.out.println("You haven't had any staying period in the hotel!");
    }
  }
  
  public Stack<Stay> getGuestStays(){
    return this.stays;
  }
  
  @Override
  public int compareTo(Guest guest){
    return guest.getValidatedRservations() - this.getValidatedRservations();
  }
  
  public List<Reservation> getValidatedReservationList(){
    List<Reservation> list = new ArrayList<>(this.reservations);
    list.removeAll(this.cancelledReservations);
    return list;
  }
  
  public Stay getStayForTheDate(LocalDate start){
    Iterator<Stay> itr = this.stays.iterator();
    while(itr.hasNext()){
      Stay stay = itr.next();
      if(stay.getStartDate().compareTo(start)==0){
        return stay;
      }
    }
    return null;
  }
 
}
  
  
