/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import domain.Amenity;
import domain.Room;
import domain.RoomType;
import domain.RoomView;
import domain.SpecialRequest;
import domain.Admin;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import logic.AdminAccountManag;
import logic.Guest;
import logic.Reservation;
import logic.RoomAndGuestManag;
import logic.Stay;

/**
 *
 * @author lenovo
 */
public class UserInterface {
  private Scanner scanner;
  private RoomAndGuestManag manag;
  private AdminAccountManag usermanag;
  
  public UserInterface(Scanner scanner){
    this.scanner=scanner;
    this.manag = new RoomAndGuestManag();
    this.usermanag = new AdminAccountManag();
  }
  
  public void welcomePage() throws NoSuchMethodException{
    System.out.println("Welcome to our Hotel Page");
    while(true){
      System.out.println("[R]: Register");
      System.out.println("[L]: Login as Admin");
      System.out.println("[G]: Login as Guest");
      System.out.println("[X]: Quit");
      String answer = this.scanner.nextLine().toUpperCase();
      if(answer.equals("X")){
        break;
      }
      if(answer.equals("R")){
        this.registerPage();
      }else if(answer.equals("L")){
        this.adminLoginPage();
      }else{
        this.guestLoginPage();
      }
    }
  }
  
  private void guestLoginPage() throws NoSuchMethodException{
    System.out.println("Enter name: ");
    String name = this.scanner.nextLine();
    if(this.manag.doesGuestExists(name)){
      Guest guest = this.manag.getGuestForName(name);
      this.guestPanel(guest);
    }else{
      System.out.println("Either you entered the wrong name or you're not registrated! Try again!");
      this.guestLoginPage();
    }
  }
  
  private void adminLoginPage(){
    System.out.println("Enter username");
    String username = this.scanner.nextLine();
    while(!this.usermanag.doesUserNameAlreadyExists(username)){
      System.out.println("The username isn't found. Try again");
      username = this.scanner.nextLine();
    }
    System.out.println("Enter password");
    String password = this.scanner.nextLine();
    while(!this.usermanag.doesPasswordAlreadyExists(password)){
      System.out.println("The password is wrong! Forgot password?");
      System.out.println("[Y]: yes! help me!");
      System.out.println("[N]: No I will write it correctly nextTime");
      String answer = this.scanner.nextLine();
      if(answer.equals("Y")){
        System.out.println("Here's your password: " + this.usermanag.getPasswordForName(username));
        System.out.println("Enter the password again: ");
        password = this.scanner.nextLine();
      }else{
        System.out.println("Enter the password again: ");
        password = this.scanner.nextLine();
      }
    }
    Admin admin = new Admin(username, password, null);
    if(this.usermanag.isAdminRegistrated(admin)){
      this.adminPanelPage();
    }else{
      System.out.println("the login procedure has failed. Try again!");
      this.adminLoginPage();
    }
  }
  
  
  
  private void registerPage() throws NoSuchMethodException{
    System.out.println("Enter user name: ");
    String name = this.scanner.nextLine();
    System.out.println("Enter password: ");
    String password = this.scanner.nextLine();
    while(!this.usermanag.isSecure(password)){
      System.out.println("The password isn't secure. Try again");
      password = this.scanner.nextLine();
    }
    System.out.println("confirm password: ");
    String confirmedPassword = this.scanner.nextLine();
    while(!password.equals(confirmedPassword)){
      System.out.println("The confirmed password doesn't match the first one. Try again!");
      confirmedPassword = this.scanner.nextLine();
    }
    System.out.println("Enter email: ");
    String email = this.scanner.nextLine();
    while(!this.usermanag.isValidEmail(email)){
      System.out.println("The email isn't valid. Try again!");
      email=this.scanner.nextLine();
    }
    Admin admin = new Admin(name, confirmedPassword, email);
    this.usermanag.registerAdmin(admin);
    if(this.usermanag.isAdminRegistrated(admin)){
      System.out.println("Successfuly Registrated!");
      this.adminPanelPage();
    }else{
      System.out.println("The registration procedure has failed! Try again!");
      this.registerPage();
    }
  }
  
  private void adminPanelPage(){
    while(true){
      System.out.println("[A]: Add Room");
      System.out.println("[R]: Register Guest");
      System.out.println("[B]: Update Room");
      System.out.println("[C]: View Rooms");
      System.out.println("[D]: View Available Room");
      System.out.println("[E]: View Occupied Rooms");
      System.out.println("[F]: View Under-Maintenance Rooms");
      System.out.println("[G]: View Reserved Rooms");
      System.out.println("[H]: Sort Rooms");
      System.out.println("[J]: Sort Guests");
      System.out.println("[N]: Notifications");
      System.out.println("[X]: Quit");
      String answer = this.scanner.nextLine().toUpperCase();
      if("X".equals(answer)){
        break;
      }
      if(answer.equals("A")){
        this.addRoomPage();
      }else if(answer.equals("R")){
        this.registerGuestPage();
      }else if(answer.equals("B")){
        this.updateRoomPage();
      }else if(answer.equals("C")){
        this.viewRoomsPage();
      }else if(answer.equals("D")){
        this.viewAvailableRoomsPage();
      }else if(answer.equals("E")){
        this.viewOccupiedRoomsPage();
      }else if(answer.equals("F")){
        this.viewUnderMaintenanceRoomsPage();
      }else if(answer.equals("G")){
        this.viewReservedRoomsPage();
      }else if(answer.equals("H")){
        this.sortRoomsPage();
      }else if(answer.equals("J")){
        this.sortGuestsPage();
      }else{
        this.notificationsPage();
      }
    }
  }
  
  private void addRoomPage(){
    System.out.println("Enter the Room number: ");
    String number = this.scanner.nextLine();
    System.out.println("Enter the Room Type: ");
    System.out.println("[U]: Suite");
    System.out.println("[G]: Single");
    System.out.println("[F]: Family");
    System.out.println("[K]: King");
    System.out.println("[Q]: Queen");
    System.out.println("[D]: Double");
    String character = this.scanner.nextLine().toUpperCase();
    RoomType type = this.typeMap().get(character);
    System.out.println("Enter the Room View:");
    System.out.println("[S]: Sea View");
    System.out.println("[C]: City View");
    String answer = this.scanner.nextLine().toUpperCase();
    RoomView view = this.viewMap().get(answer);
    System.out.println("Enter the Room Price Per Night in Dollars: ");
    double price = Double.parseDouble(this.scanner.nextLine());
    System.out.println("Enter the number of beds: ");
    int beds = Integer.parseInt(this.scanner.nextLine());
    Room room = new Room(number, type, view, price, beds);
    this.manag.addRoom(number, type, view, price, beds);
    if(this.manag.doesRoomExists(number)){
      System.out.println("Room is successfully added to the system");
      System.out.println("Here are its information: ");
      System.out.println(room);
    }else{
      System.out.println("Room isn't added, maybe try again!");
      this.addRoomPage();
    }
  }
  
  private void updateRoomPage(){
    while(true){
      System.out.println("Enter the room number:");
      String roomNumber = this.scanner.nextLine();
      System.out.println("[A]: Maintain Room");
      System.out.println("[B]: Change Room Price per Night");
      System.out.println("[C]: Change Room Type");
      System.out.println("[E]: Add Amenities to Room");
      System.out.println("[X]: Return to Admin Panel");
      String answer = this.scanner.nextLine().toUpperCase();
      if(answer.equals("X")){
        break;
      }
      if(answer.equals("A")){
        this.manag.maintainRoom(roomNumber);
        Room room = this.manag.getRoomForNumber(roomNumber);
        if(room.isUnderMaintenance()){
          System.out.println("Room successfully put under maintenance!");
        }
      }else if(answer.equals("B")){
        System.out.println("The current price of the Room is: $" + this.manag.getRoomForNumber(answer).getPrice());
        System.out.println("Enter the new price in dollars: ");
        double price = Double.parseDouble(this.scanner.nextLine());
        this.manag.updateRoomPrice(roomNumber, price);
      }else if(answer.equals("C")){
        System.out.println("The current room type is: " + this.manag.getRoomForNumber(answer).getType());
        System.out.println("Enter the new Type: ");
        System.out.println("[U]: Suite");
        System.out.println("[G]: Single");
        System.out.println("[F]: Family");
        System.out.println("[K]: King");
        System.out.println("[Q]: Queen");
        System.out.println("[D]: Double");
        String character = this.scanner.nextLine().toUpperCase();
        this.manag.updateRoomType(roomNumber, this.typeMap().get(character));
      }else{
        System.out.println("The current amenities of the room is: ");
        this.manag.getRoomForNumber(answer).viewRoomAmenities();
        System.out.println("Add amenities: ");
        System.out.println("[W]: WiFi");
        System.out.println("[M]: Microwave");
        System.out.println("[H]: Hairdryer");
        System.out.println("[C]: Coffee-Maker");
        System.out.println("[T]: Heating");
        System.out.println("[F]: Mini-Fridge");
        System.out.println("[I]: Ironing Facilities");
        System.out.println("[A]: Air Conditioning");
        String character = this.scanner.nextLine().toUpperCase();
        while(!character.isBlank()){
          this.manag.addAmenitiesToRoom(roomNumber, this.amenityMap().get(character));
          character = this.scanner.nextLine().toUpperCase();
        }
      }
    }
  }
  
  private void viewOccupiedRoomsPage(){
    this.manag.viewOccupiedRooms();
  }
  
  private void viewUnderMaintenanceRoomsPage(){
    this.manag.viewRoomsUnderMaintenance();
  }
  
  private void viewReservedRoomsPage(){
    this.manag.viewReservedRooms();
  }
  
  private void sortRoomsPage(){
    this.manag.sortRoomsBasedOnReservationNumber();
  }
  
  private void sortGuestsPage(){
    this.manag.sortGuestsBasedOnReservationNumber();
  }
  
  private void notificationsPage(){
    
  }
  
  private void registerGuestPage(){
    System.out.println("Enter The Guest Name: ");
    String name = this.scanner.nextLine();
    System.out.println("Enter the Guest email: ");
    String email = this.scanner.nextLine();
    System.out.println("Enter The Guest Phone: ");
    String phone = this.scanner.nextLine();
    System.out.println("Enter the Guest Address: ");
    String address = this.scanner.nextLine();
    this.manag.registerGuest(name, email, phone, address);
    if(this.manag.doesGuestExists(name)){
      System.out.println("Guest successfully added!");
    }else{
      System.out.println("Guest was not added! Maybe the email address isn't valid. Try again!");
      this.registerGuestPage();
    }
  }
 
  
  private void guestPanel(Guest guest){
    System.out.println("Welcome " + guest.getName() + "!");
    while(true){
      System.out.println("[R]: View Rooms");
      System.out.println("[A]: View Available Rooms");
      System.out.println("[F]: Filter Rooms");
      System.out.println("[M]: Make Reservation");
      System.out.println("[C]: Cancel Reservation");
      System.out.println("[P]: View Reservations");
      System.out.println("[S]: View Past Hotel Stays");
      System.out.println("[Q]: Make Special Requests");
      System.out.println("[X]: Quit");
      String answer = this.scanner.nextLine().toUpperCase();
      if(answer.equals("X")){
        break;
      }
      if(answer.equals("R")){
        this.viewRoomsPage();
      }else if(answer.equals("A")){
        this.viewAvailableRoomsPage();
      }else if(answer.equals("F")){
        this.guestFiltersRooms();
      }else if(answer.equals("M")){
        this.makeReservationPage(guest);
      }else if(answer.equals("C")){
        this.cancelReservationPage(guest);
      }else if(answer.equals("P")){
        this.viewReservationPage(guest);
      }else if(answer.equals("S")){
        this.viewPastHotelStayPage(guest);
      }else{
        this.makeSpecialRequestsPage(guest);
      }
    }
  }
  
  private void viewRoomsPage(){
    this.manag.browseRooms();
  }
  
  private void viewAvailableRoomsPage(){
    this.manag.browseAvailableRooms();
  }
  
  private void makeReservationPage(Guest guest){
    System.out.println("Enter the start date: ");
    System.out.print("\tDay: ");
    int startDay = Integer.parseInt(this.scanner.nextLine());
    System.out.print("\tMonth: ");
    int startMonth = Integer.parseInt(this.scanner.nextLine());
    System.out.print("\tYear: ");
    int startYear = Integer.parseInt(this.scanner.nextLine());
    LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
    System.out.println("Enter the end date: ");
    System.out.print("\tDay: ");
    int endDay = Integer.parseInt(this.scanner.nextLine());
    System.out.print("\tMonth: ");
    int endMonth = Integer.parseInt(this.scanner.nextLine());
    System.out.print("\tYear: ");
    int endYear = Integer.parseInt(this.scanner.nextLine());
    LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);
    System.out.println("Enter the number of guests: ");
    int guests = Integer.parseInt(this.scanner.nextLine());
    System.out.println("Enter the Room Type: ");
    System.out.println("[U]: Suite");
    System.out.println("[G]: Single");
    System.out.println("[F]: Family");
    System.out.println("[K]: King");
    System.out.println("[Q]: Queen");
    System.out.println("[D]: Double");
    String character = this.scanner.nextLine().toUpperCase();
    RoomType type = this.typeMap().get(character);
    System.out.println("Enter the number of Beds: ");
    int beds = Integer.parseInt(this.scanner.nextLine());
    Boolean doesRoomExists = guest.makeReservation(startDate, endDate, guests, type, beds);
    if(doesRoomExists){
      System.out.println("The Room is Found!");
      Room room = this.manag.getRoomForTheseCriteria(startDate, endDate, guests, type, beds);
      Reservation reserv = new Reservation(room, startDate, endDate, guests, guest);
      System.out.println("Here are the details of your reservation: ");
      System.out.println(reserv);
      System.out.println("Do you want to confirm reservations?");
      System.out.println("[Yes]: Yes I want to confirm reservation");
      System.out.println("[No]: Not yet");
      String answer = this.scanner.nextLine().toUpperCase();
      if(answer.equals("YES")){
        guest.completeReservationProcess(startDate, endDate, guests, type, beds);
      }else{
        this.makeReservationPage(guest);
      }
    }else{
      System.out.println("The room isn't found!");
      System.out.println("Do you want to modify reservation inputs?");
      System.out.println("[Yes]: Yes I want to modify reservation");
      System.out.println("[No]: Not yet");
      String answer = this.scanner.nextLine().toUpperCase();
      if(answer.equals("YES")){
        this.makeReservationPage(guest);
      }else{
        this.guestPanel(guest);
      }
    }
  }
  
  private void cancelReservationPage(Guest guest){
    System.out.println("Enter the booking reference of reservation: ");
    String bookingReference = this.scanner.nextLine();
    guest.cancelReservation(bookingReference);
    if(guest.isReservationCancelled(bookingReference)){
      System.out.println("Reservation Suucessfully Cancelled");
    }else{
      System.out.println("Too late! You must cancel at least 24 hours before the reservation starting date");
    }
  }
  
  private void viewReservationPage(Guest guest){
    System.out.println("[L]: View The Last Reservation");
    System.out.println("[A]: view All Past Reservations");
    String answer = this.scanner.nextLine().toUpperCase();
    if(answer.equals("L")){
      guest.viewLastReservation();
    }else{
      guest.viewReservations();
    }
  }
  
  private void viewPastHotelStayPage(Guest guest){
    System.out.println("[L]: View The Last Stay");
    System.out.println("[A]: view All Past Stays");
    String answer = this.scanner.nextLine().toUpperCase();
    if(answer.equals("L")){
      guest.viewLastStay();
    }else{
      guest.viewStays();
    }
  }
  
  private void makeSpecialRequestsPage(Guest guest){
    System.out.println("Enter the reservation booking reference: ");
    String reference = this.scanner.nextLine();
    Reservation reserv = guest.getReservationForBookingReference(reference);
    if(guest.getValidatedReservationList().contains(reserv) && this.isDateBetween(reserv.getStartDate(), reserv.getEndDate())){
      this.specialRequestPrompt(guest, reserv.getStartDate());
    }else{
      System.out.println("Either no reservation found for the booking reference you entered or it's not the right time to make special requests. Wait until your arrive at the hotel. ");
    }
  }
  
  private void specialRequestPrompt(Guest guest, LocalDate date){
    Stay stay = guest.getStayForTheDate(date);
    System.out.println("Enter the letters corresponding of the special requests you'd like in your staying period");
    System.out.println("[N]: Non-smoking room");
    System.out.println("[H]: High-floor");
    System.out.println("[L]: Low-floor");
    System.out.println("[Q]: Quest Room");
    System.out.println("[A]: Airport Shuttle Service");
    System.out.println("[C]: Car Rental Arrangement");
    System.out.println("[D]: In-room Dining Option");
    System.out.println("[P]: Pet-friendly Room");
    System.out.println("[S]: High-Speed Internet Access");
    String letter = this.scanner.nextLine().toUpperCase();
    while(!letter.isBlank()){
      stay.addSpecialRequest(this.specialRequestMap().get(letter));
      letter = this.scanner.nextLine().toUpperCase();
    }
    
  }
  
  private Map<String, SpecialRequest> specialRequestMap(){
    Map<String, SpecialRequest> map = new HashMap<>();
    map.put("N", SpecialRequest.NON_SMOKING_ROOM);
    map.put("H", SpecialRequest.HIGH_FLOOR);
    map.put("L", SpecialRequest.LOW_FLOOR);
    map.put("Q", SpecialRequest.QUIET);
    map.put("A", SpecialRequest.AIRPORT_SHUTTLE_SERVICE);
    map.put("C", SpecialRequest.CAR_RENTAL_ARRANGEMENTS);
    map.put("D", SpecialRequest.IN_ROOM_DINING_OPTION);
    map.put("P", SpecialRequest.PET_FRIENDLY_ROOM);
    map.put("S", SpecialRequest.HIGH_SPEED_INTERNET_ACCESS);
    return map;
  }
  
  private boolean isDateBetween(LocalDate start, LocalDate end){
    LocalDate currentDate = LocalDate.now();
    return (currentDate.compareTo(start)==0 || currentDate.isAfter(start)) && currentDate.isBefore(end);
  }
    
  private void guestFiltersRooms(){
    while(true){
      System.out.println("[P]: Filter Rooms by Price");
      System.out.println("[A]: Filter Rooms by Amenities");
      System.out.println("[V]: Filter Rooms by View");
      System.out.println("[T]: Filter Rooms by Type");
      System.out.println("[X]: Return to The Guest Panel");
      String answer = this.scanner.nextLine().toUpperCase();
      if("X".equals(answer)){
        break;
      }
      if(answer.equals("P")){
        this.filterRoomsByPrice();
      }else if(answer.equals("A")){
        this.filterRoomsByAmenities();
      }else if(answer.equals("V")){
        this.filterRoomsByView();
      }else {
        this.filterRoomsByType();
      }
    }
  }
  
  private void filterRoomsByPrice(){
    System.out.println("Enter minimum Price in dollars: ");
    double minPrice = Double.parseDouble(this.scanner.nextLine());
    System.out.println("Enter maximum Price in dollars: ");
    double maxPrice = Double.parseDouble(this.scanner.nextLine());
    this.manag.filterRoomsByPricePerNight(minPrice, maxPrice);
  }
  
  private void filterRoomsByAmenities(){
    System.out.println("Here's the list of amenities available in our hotel rooms right now: ");
    System.out.println("Enter the letters corresponding to the amenities you'd like in a room");
    System.out.println("To end the list just press an empty line!");
    System.out.println("[W]: WiFi");
    System.out.println("[M]: Microwave");
    System.out.println("[H]: Hairdryer");
    System.out.println("[C]: Coffee-Maker");
    System.out.println("[T]: Heating");
    System.out.println("[F]: Mini-Fridge");
    System.out.println("[I]: Ironing Facilities");
    System.out.println("[A]: Air Conditioning");
    List<String> strings = new ArrayList<>();
    String letter = this.scanner.nextLine();
    while(!letter.isBlank()){
      strings.add(letter);
      letter = this.scanner.nextLine();
    }
    List<Amenity> amenities = new ArrayList<>();
    Iterator<String> itr = strings.iterator();
    while(itr.hasNext()){
      String character = itr.next().toUpperCase();
      amenities.add(this.amenityMap().get(character));
    }
    this.manag.filterRoomsByTheseAmenities(amenities);
   }
  
  private Map<String, Amenity> amenityMap(){
    Map<String, Amenity> map = new HashMap<>();
    map.put("W", Amenity.WIFI);
    map.put("M", Amenity.MICROWAVE);
    map.put("T", Amenity.HEATING);
    map.put("H", Amenity.HAIRDRYER);
    map.put("I", Amenity.IRONING_FACILITIES);
    map.put("A", Amenity.AIR_CONDITIONING);
    map.put("C", Amenity.COFFEE_MAKER);
    map.put("F", Amenity.MINI_FRIDGE);
    return map;
  }
  
  private void filterRoomsByView(){
    System.out.println("[S]: Sea View");
    System.out.println("[C]: City View");
    String answer = this.scanner.nextLine().toUpperCase();
    this.manag.filterRoomsByView(this.viewMap().get(answer));
  }
  
  private Map<String, RoomView> viewMap(){
    Map<String, RoomView> map = new HashMap<>();
    map.put("S", RoomView.SEA_VIEW);
    map.put("C", RoomView.CITY_VIEW);
    return map;
  }
  
  private void filterRoomsByType(){
    System.out.println("[U]: Suite");
    System.out.println("[G]: Single");
    System.out.println("[F]: Family");
    System.out.println("[K]: King");
    System.out.println("[Q]: Queen");
    System.out.println("[D]: Double");
    String character = this.scanner.nextLine().toUpperCase();
    this.manag.filterRoomsByType(this.typeMap().get(character));
  }
  
  private Map<String, RoomType> typeMap(){
    Map<String, RoomType> map = new HashMap<>();
    map.put("U", RoomType.SUITE);
    map.put("G", RoomType.SINGLE);
    map.put("K", RoomType.KING);
    map.put("Q", RoomType.QUEEN);
    map.put("D", RoomType.DOUBLE);
    map.put("F", RoomType.FAMILY);
    return map;
  }
}
