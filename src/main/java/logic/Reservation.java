/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;
import domain.*;
import java.security.SecureRandom;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author lenovo
 */
public class Reservation {
  private final Room room;
  private final LocalDate startDate;
  private final LocalDate endDate;
  private final int numberOfGuests;
  private final UUID id;
  private final Guest guest;
  private  final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  private  final SecureRandom RANDOM = new SecureRandom();
  private final String bookingReference;
  
  public Reservation(Room room, LocalDate start, LocalDate end, int guests, Guest guest){
    this.bookingReference = this.generateBookingReference(10);
    this.id = UUID.randomUUID();
    this.room=room;
    this.endDate=end;
    this.startDate=start;
    this.numberOfGuests=guests;
    this.guest=guest;
  }

  private  String generateBookingReference(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
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

  public int getNumberOfGuests() {
    return numberOfGuests;
  }

  public UUID getId() {
    return id;
  }

  public Guest getGuest() {
    return guest;
  }

  public String getBookingReference() {
    return bookingReference;
  }
  
  public double totalPrice(){
    int days = (int) ChronoUnit.DAYS.between(this.startDate, this.endDate);
    return this.room.getPrice()*days;
  }
  
  @Override
  public String toString(){
    String a = "Reservation Details:\n";
    String b = "";
    for(int i=0;i<=19;i++){
      b+="-";
    }
    String c = "\nReservation ID: " + this.id + "\n";
    String d = "Guest name: " + this.guest.getName() + "\n";
    String e = "Check-in Date: " + this.startDate + "\n";
    String f = "Check-out Date: " + this.endDate + "\n";
    String g = "Room type: " + this.room.getType() + "\n";
    String h = "Price per night: $"+ this.room.getPrice() + "\n";
    String i = "Total Price: $" + this.totalPrice() + "\n";
    String j = "Booking reference number: " + this.bookingReference;
    return a+b+c+d+e+f+g+h+i+j;
  }
  
   @Override
  public int hashCode() {
    int hash = 5;
    hash = 71 * hash + Objects.hashCode(this.id);
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
    final Reservation other = (Reservation) obj;
    return Objects.equals(this.id, other.id);
  }
}
