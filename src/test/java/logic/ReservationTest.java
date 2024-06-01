/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package logic;

import domain.Room;
import domain.RoomType;
import domain.RoomView;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author lenovo
 */
public class ReservationTest {
  private Room room;
  private LocalDate startDate;
  private LocalDate endDate;
  private int numberOfGuests;
  private Guest guest;
  private Reservation reserv;
  
  public ReservationTest() {
  }
  
  @BeforeEach
  public void setUp() {
    room = new Room("2A31", RoomType.SINGLE, RoomView.CITY_VIEW, 22.5, 1);
    guest = new Guest("youness", "email", "0678", "marrakech");
    startDate = LocalDate.of(2024, Month.MARCH, 15);
    endDate = LocalDate.of(2024, Month.MARCH, 19);
    numberOfGuests = 1;
    reserv = new Reservation(room, startDate, endDate, numberOfGuests, guest);
  }
  
  @Test
  void test1(){
    assertNotNull(reserv.getId());
  }
  
  @Test
  void test2(){
    Room room1= new Room("2A31", RoomType.SINGLE, RoomView.CITY_VIEW, 22.5, 5);
    assertEquals(room1, reserv.getRoom());
  }
  
  @Test
  void test3(){
    assertEquals(new Guest("youness", "email", "0678", "marrakech"), reserv.getGuest());
  }
  
  @Test
  void test4(){
    assertEquals(1, reserv.getNumberOfGuests());
  }
  
  @Test
  void test5(){
    assertNotNull(reserv.getBookingReference());
  }
  
  @Test
  void test6(){
    assertEquals(90.0, reserv.totalPrice());
  }
  
  @Test
  void test7(){
    guest.getGuestReservations().add(reserv);
    assertEquals(reserv, guest.getGuestReservations().peek());
  }
  
  @Test
  void test8(){
    guest.getGuestReservations().add(reserv);
    assertEquals(1, guest.getGuestReservations().size());
  }
  
  @BeforeAll
  public static void setUpClass() {
  }
  
  @AfterAll
  public static void tearDownClass() {
  }
  
  
  
  @AfterEach
  public void tearDown() {
  }

  // TODO add test methods here.
  // The methods must be annotated with annotation @Test. For example:
  //
  // @Test
  // public void hello() {}
}
