/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;
import domain.*;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author lenovo
 */
public class RoomAndGuestManag {
  private List<Room> rooms;
  private List<Guest> guests;
  private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
  
  
  public RoomAndGuestManag(){
    this.rooms=new ArrayList<>();
    this.guests=new ArrayList<>();
  }
  
  public void addRoom(String number, RoomType type, RoomView view, double price, int beds){
    Room room = new Room(number, type, view, price ,beds);
    if(!this.rooms.contains(room)){
      this.rooms.add(room);
    }
  }
  
  public void maintainRoom(String roomNumber){
    this.getRoomForNumber(roomNumber).maintain();
  }
  
  public void addAmenitiesToRoom(String roomNumber, Amenity amenity){
    this.getRoomForNumber(roomNumber).addAmenities(amenity);
  }
  
  public void updateRoomPrice(String roomNumber, double price){
    this.getRoomForNumber(roomNumber).changeRoomPrice(price);
  }
  
  public void updateRoomType(String roomNumber, RoomType type){
    this.getRoomForNumber(roomNumber).changeRoomType(type);
  }
  
  public boolean doesRoomExists(String roomNumber){
    return this.rooms.contains(this.getRoomForNumber(roomNumber));
  }
  
  public boolean doesGuestExists(String guestName){
    return this.guests.contains(this.getGuestForName(guestName));
  }
  
  public Room getRoomForNumber(String number){
    for(Room room:this.rooms){
      if(room.getNumber().equals(number)){
        return room;
      }
    }
    return null;
  }
  
  public  boolean isValidUUID(String uuidString) {
        String uuidPattern = 
            "^\\h*\\b[0-9a-fA-F]{8}\\b-\\b[0-9a-fA-F]{4}\\b-\\b[0-9a-fA-F]{4}\\b-\\b[0-9a-fA-F]{4}\\b-\\b[0-9a-fA-F]{12}\\b\\h*$";
        return Pattern.matches(uuidPattern, uuidString);
    }

  public List<Room> getRooms() {
    return rooms;
  }
  
  public List<Guest> getGuests(){
    return this.guests;
  }
  
  public void browseRooms(){
    for(Room room:this.rooms){
      System.out.println(room);
    }
  }
  
  public void browseAvailableRooms(){
    this.rooms.stream().filter(room -> room.isAvailable()).forEach(room -> System.out.println(room));
  }
  
  public void viewRoomsUnderMaintenance(){
    this.rooms.stream().filter(room -> room.isUnderMaintenance()).forEach(room -> System.out.println(room));
  }
  
  public void viewOccupiedRooms(){
    this.rooms.stream().filter(room -> room.isOccupied()).forEach(room -> System.out.println(room));
  }
  
  public void viewReservedRooms(){
    this.rooms.stream().filter(room -> !room.getReservations().isEmpty()).forEach(room -> System.out.println(room));
  }
  
  public Room getRoomForTheseCriteria(LocalDate start, LocalDate end, int numberOfGuests, RoomType roomType, int beds){
    List<Room> checkedRooms = new ArrayList<>();
    this.rooms.stream().filter(room -> room.occupancyLimit()>=numberOfGuests && room.getType()==roomType && room.getBeds()>=beds).forEach(room -> checkedRooms.add(room));
    for(Room room:checkedRooms){
      if(room.isAvailable() && room.getReservations().isEmpty()){
        return room;
      }else if(room.isOccupied() && room.getReservations().isEmpty() && !this.isOverlap(start, end, room.getRoomOccupied().getStart(), room.getRoomOccupied().getEnd())){
        return room;
      }else if(room.isAvailable() && !room.getReservations().isEmpty() && !this.isOverlapWithExisting(room.getReservations(), start, end)){
        return room;
      }else if(!this.isOverlap(start, end, room.getRoomOccupied().getStart(), room.getRoomOccupied().getEnd()) && !this.isOverlapWithExisting(room.getReservations(), start, end)){
        return room;
      }
    }
    return null;
  }
  
  public  boolean isOverlap(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
        return start1.isBefore(end2) && end1.isAfter(start2);
    }
  
  public  boolean isOverlapWithExisting(List<RoomReservation> existingRanges, LocalDate start, LocalDate end) {
        for (RoomReservation range : existingRanges) {
            if (isOverlap(range.getStart(), range.getEnd(), start, end)) {
                return true;
            }
        }
        return false;
    }
  
  public void sortRoomsBasedOnReservationNumber(){
    Collections.sort(this.rooms);
    System.out.println("Room Number\tRoom Number Of Reservations");
    for(Room room:this.rooms){
      System.out.println(room.getNumber() + ":\t" + room.getReservations().size());
    }
  }
  
  public void sortGuestsBasedOnReservationNumber(){
    Collections.sort(this.guests);
    System.out.println("Guest ID\t\tGuest Name\t Guest email\t\t\tNumber of Reservations");
    for(Guest guest:this.guests){
      System.out.println(guest.getId() + ":\t\t" + guest.getName() + "\t" + guest.getEmail() + "\t\t\t" + guest.getValidatedRservations());
    }
  }
  
  public void filterRoomsByPricePerNight(double minPrice, double maxPrice){
    this.rooms.stream().filter(r -> r.getPrice()>=minPrice && r.getPrice()<=maxPrice).forEach(r -> System.out.println(r));
  }
  
  public void filterRoomsByTheseAmenities(List<Amenity> list){
    this.rooms.stream().filter(r -> r.getAmenities().containsAll(list)).forEach(r -> System.out.println(r));
  }
  
  public void filterRoomsByType(RoomType type){
    this.rooms.stream().filter(r -> r.getType()==type).forEach(r -> System.out.println(r));
  }
  
  public void filterRoomsByView(RoomView view){
    this.rooms.stream().filter(r -> r.getView()==view).forEach(r -> System.out.println(r));
  }
  
  public Guest getGuestForName(String guestName){
      for(Guest guest:this.guests){
        if(guest.getName().equals(guestName)){
          return guest;
        }
      }
    return null;
  }
 
  
  public void registerGuest(String name, String email, String phone, String address){
    if(this.isValidEmail(email)){
      Guest guest = new Guest(name, email, phone, address);
      this.guests.add(guest);
    }
  }
  
  public boolean isValidEmail(String email){
      Pattern pattern = Pattern.compile(EMAIL_PATTERN);
      Matcher matcher = pattern.matcher(email);
      return matcher.matches();
  }

}
