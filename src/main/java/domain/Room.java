/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;
import logic.Guest;
import java.util.*;
/**
 *
 * @author lenovo
 */
public class Room implements Comparable<Room>{

  
  private String number;
  private RoomType type;
  private RoomView view;
  private double pricePerNight;
  private List<Amenity> amenities;
  private boolean occupied;
  private boolean underMaintenance;
  private List<Guest> waitlist;
  private int numberOfBeds;
  private List<RoomReservation> reservations;
  private RoomOccupied roomOccupied;
 
  public Room(String number, RoomType type, RoomView view, double price, int beds){
    if(number==null){
      throw new IllegalArgumentException();
    }
    if(type==null){
      throw new IllegalArgumentException();
    }
    if(view==null){
      throw new IllegalArgumentException();
    }
    if(price<=0){
      throw new IllegalArgumentException();
    }
    
    this.number=number;
    this.type=type;
    this.view=view;
    this.pricePerNight=price;
    this.amenities=new ArrayList<>();
    this.waitlist=new ArrayList<>();
    this.numberOfBeds=beds;
    this.reservations=new ArrayList<>();
    this.roomOccupied=new RoomOccupied();
  }
  
  public List<Guest> getWaitlist() {
    return waitlist;
  }

  public List<RoomReservation> getReservations() {
    return reservations;
  }

  public RoomOccupied getRoomOccupied() {
    return roomOccupied;
  }
  
  public void addRoomReservation(RoomReservation reservation){
    this.reservations.add(reservation);
  }
  
  public void removeRoomReservation(RoomReservation reservation){
    this.reservations.remove(reservation);
  }
  
  public void addAmenities(Amenity amenity){
    this.amenities.add(amenity);
  }
  
  public void removeAmenities(Amenity amenity){
    this.amenities.remove(amenity);
  }
  
  public void addGuestToWaitlist(Guest guest){
    this.waitlist.add(guest);
  }
  
  public void removeGuestFromWaitlist(Guest guest){
    this.waitlist.remove(guest);
  }
  
  public void changeRoomType(RoomType newType){
    this.type=newType;
  }
  
  public void changeRoomPrice(double price){
    if(price<=0){
      throw new IllegalArgumentException();
    }
    this.pricePerNight=price;
  }
  
  public int occupancyLimit(){
    if(this.type==RoomType.SINGLE){
      return 1;
    }else if(this.type==RoomType.DOUBLE){
      return 2;
    }else if(this.type==RoomType.SUITE){
      return 4;
    }else if(this.type==RoomType.QUEEN){
      return 2;
    }else if(this.type==RoomType.KING){
      return 2;
    }else{
      return 6;
    }
  }
  
  public String getNumber(){
    return this.number;
  }
  
  public RoomView getView(){
    return this.view;
  }
  
  public RoomType getType(){
    return this.type;
  }
  
  public double getPrice(){
    return this.pricePerNight;
  }
  
  public int getBeds(){
    return this.numberOfBeds;
  }
  
  public List<Amenity> getAmenities(){
    return this.amenities;
  }
  
 
  
  public boolean isOccupied(){
    return this.occupied;
  }
  
  public boolean isUnderMaintenance(){
    return this.underMaintenance;
  }
  
  public void occupy(){
    this.occupied=true;
  }
  
  public void leaveRoom(){
    this.occupied=false;
  }
  
  public void maintain(){
    this.underMaintenance=true;
  }
  
  public void unmaintain(){
    this.underMaintenance=false;
  }
  
  public boolean isAvailable(){
    return (!this.occupied) && (!this.underMaintenance);
  }
  
  @Override
  public boolean equals(Object obj){
    if(this==obj){
      return true;
    }
    if(!(obj instanceof Room)){
      return false;
    }
    Room room = (Room) obj;
    return this.number.equals(room.getNumber());
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 53 * hash + Objects.hashCode(this.number);
    return hash;
  }
  
  @Override
  public String toString(){
    String a = "This room of number: " + this.number + "\n";
    String b = "Type: " + this.type + "\n";
    String c = "View: " + this.view + "\n";
    String d="""
             Its amenities are: 
             """;
    for(Amenity amenity:this.amenities){
      d+= amenity + "\n";
    }
    String e;
    if(this.isAvailable()){
      e = "Availability Status: Available\n";
    }else{
      e= "Availability Status: Unavailable\n";
    }
    return a+b+e+c+d;
  }
 
  @Override
  public int compareTo(Room room){
    return room.getReservations().size() - this.reservations.size();
  }
  
  public void viewRoomAmenities(){
    if(this.amenities.isEmpty()){
      System.out.println("The room doesn't have any amenity right now!");
    }
    this.amenities.stream().forEach(am -> System.out.println(am));
  }
  
}
