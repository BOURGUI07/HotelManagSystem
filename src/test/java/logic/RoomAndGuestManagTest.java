/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package logic;

import domain.Amenity;
import domain.Room;
import domain.RoomReservation;
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
public class RoomAndGuestManagTest {
  private RoomAndGuestManag manag;
  
  public RoomAndGuestManagTest() {
  }
  
  @BeforeAll
  public static void setUpClass() {
  }
  
  @AfterAll
  public static void tearDownClass() {
  }
  
  @BeforeEach
  public void setUp() {
    manag = new RoomAndGuestManag();
  }
  
  @Test
  void test0(){
    assertTrue(manag.getRooms().isEmpty());
    assertTrue(manag.getGuests().isEmpty());
  }
  
  @Test
  void test1(){
    manag.addRoom("AA", RoomType.SUITE, RoomView.SEA_VIEW, 15, 2);
    assertTrue(manag.doesRoomExists("AA"));
  }
  
  @Test
  void test2(){
    manag.addRoom("AA", RoomType.SUITE, RoomView.SEA_VIEW, 15, 2);
    assertTrue(manag.getRooms().size()==1);
  }
  
  @Test
  void test3(){
    manag.addRoom("AA", RoomType.SUITE, RoomView.SEA_VIEW, 15, 2);
    Room room = new Room("AA", RoomType.SUITE, RoomView.SEA_VIEW, 15, 2);
    assertEquals(room, manag.getRoomForNumber("AA"));
  }
  
  @Test
  void test4(){
    manag.addRoom("AA", RoomType.SUITE, RoomView.SEA_VIEW, 15, 2);
    manag.addAmenitiesToRoom("AA", Amenity.WIFI);
    Room room = manag.getRoomForNumber("AA");
    assertTrue(room.getAmenities().contains(Amenity.WIFI));
  }
  
  @Test
  void test5(){
    manag.registerGuest("youness", "email", "phone", "address");
    assertTrue(manag.getGuests().isEmpty());
  }
  
  @Test
  void test6(){
    manag.registerGuest("youness", "younessbourgui07@gmail.com", "phone", "address");
    Guest guest = new Guest("youness", "email", "phone", "address");
    assertEquals(guest, manag.getGuestForName("youness"));
  }
  
  @Test
  void test7(){
    manag.registerGuest("youness", "younessbourgui07@gmail.com", "phone", "address");
    assertTrue(manag.doesGuestExists("youness"));
  }
  
  @Test
  void test8(){
    manag.addRoom("AA", RoomType.SUITE, RoomView.SEA_VIEW, 15, 2);
    manag.maintainRoom("AA");
    Room room = manag.getRoomForNumber("AA");
    assertTrue(room.isUnderMaintenance());
  }
  
  @Test
  void test9(){
    manag.addRoom("AA", RoomType.SUITE, RoomView.SEA_VIEW, 15, 2);
    Room room = manag.getRoomForNumber("AA");
    manag.updateRoomPrice("AA", 14.8);
    assertEquals(14.8, room.getPrice());
  }
  
  @Test
  void test10(){
    manag.addRoom("AA", RoomType.SUITE, RoomView.SEA_VIEW, 15, 2);
    Room room = manag.getRoomForNumber("AA");
    manag.updateRoomType("AA", RoomType.KING);
    assertEquals(RoomType.KING, room.getType());
  }
  
  @Test
  void test11(){
    manag.addRoom("AA", RoomType.SUITE, RoomView.SEA_VIEW, 15, 2);
    Room room = manag.getRoomForNumber("AA");
    RoomReservation roomreserv = new RoomReservation(LocalDate.of(2024, Month.MARCH, 14), LocalDate.of(2024, Month.MARCH, 21));
    room.addRoomReservation(roomreserv);
    Room room2 = manag.getRoomForTheseCriteria(LocalDate.of(2024, Month.MARCH, 25), LocalDate.of(2024, Month.MARCH, 29), 1, RoomType.SUITE, 1);
    assertEquals(room2, room);
  }
  
  @Test
  void test12(){
    manag.addRoom("AA", RoomType.SUITE, RoomView.SEA_VIEW, 15, 2);
    Room room = manag.getRoomForNumber("AA");
    RoomReservation roomreserv = new RoomReservation(LocalDate.of(2024, Month.MARCH, 14), LocalDate.of(2024, Month.MARCH, 21));
    room.addRoomReservation(roomreserv);
    manag.registerGuest("youness", "younessbourgui07@gmail.com", "phone", "address");
    Guest guest = manag.getGuestForName("youness");
    guest.setManag(manag);
    assertTrue(guest.makeReservation(LocalDate.of(2024, Month.MARCH, 25), LocalDate.of(2024, Month.MARCH, 29), 1, RoomType.SUITE, 1));
  }
  
  @Test
  void test13(){
    manag.addRoom("AA", RoomType.SUITE, RoomView.SEA_VIEW, 15, 2);
    Room room = manag.getRoomForNumber("AA");
    RoomReservation roomreserv = new RoomReservation(LocalDate.of(2024, Month.MARCH, 14), LocalDate.of(2024, Month.MARCH, 21));
    room.addRoomReservation(roomreserv);
    manag.registerGuest("youness", "younessbourgui07@gmail.com", "phone", "address");
    Guest guest = manag.getGuestForName("youness");
    guest.setManag(manag);
    guest.completeReservationProcess(LocalDate.of(2024, Month.MARCH, 25), LocalDate.of(2024, Month.MARCH, 29), 1, RoomType.SUITE, 1);
    assertFalse(guest.getGuestReservations().isEmpty());
  }
  
  @Test 
  void test14(){
    manag.addRoom("AA", RoomType.SUITE, RoomView.SEA_VIEW, 15, 2);
    Room room = manag.getRoomForNumber("AA");
    RoomReservation roomreserv = new RoomReservation(LocalDate.of(2024, Month.MARCH, 14), LocalDate.of(2024, Month.MARCH, 21));
    room.addRoomReservation(roomreserv);
    manag.registerGuest("youness", "younessbourgui07@gmail.com", "phone", "address");
    Guest guest = manag.getGuestForName("youness");
    guest.setManag(manag);
    guest.completeReservationProcess(LocalDate.of(2024, Month.MARCH, 25), LocalDate.of(2024, Month.MARCH, 29), 1, RoomType.SUITE, 1);
    assertFalse(room.getWaitlist().isEmpty());
  }
  
  @Test
  void test15(){
    manag.addRoom("AA", RoomType.SUITE, RoomView.SEA_VIEW, 15, 2);
    Room room = manag.getRoomForNumber("AA");
    RoomReservation roomreserv = new RoomReservation(LocalDate.of(2024, Month.MARCH, 14), LocalDate.of(2024, Month.MARCH, 21));
    room.addRoomReservation(roomreserv);
    manag.registerGuest("youness", "younessbourgui07@gmail.com", "phone", "address");
    Guest guest = manag.getGuestForName("youness");
    guest.setManag(manag);
    guest.completeReservationProcess(LocalDate.of(2024, Month.MARCH, 25), LocalDate.of(2024, Month.MARCH, 29), 1, RoomType.SUITE, 1);
    RoomReservation roomreserv1 = new RoomReservation(LocalDate.of(2024, Month.MARCH, 25), LocalDate.of(2024, Month.MARCH, 29));
    assertTrue(room.getReservations().contains(roomreserv1));
  }
  
  @Test
  void test16(){
    manag.addRoom("AA", RoomType.SUITE, RoomView.SEA_VIEW, 15, 2);
    Room room = manag.getRoomForNumber("AA");
    RoomReservation roomreserv = new RoomReservation(LocalDate.of(2024, Month.MARCH, 14), LocalDate.of(2024, Month.MARCH, 21));
    room.addRoomReservation(roomreserv);
    manag.registerGuest("youness", "younessbourgui07@gmail.com", "phone", "address");
    Guest guest = manag.getGuestForName("youness");
    guest.setManag(manag);
    guest.completeReservationProcess(LocalDate.of(2024, Month.MARCH, 25), LocalDate.of(2024, Month.MARCH, 29), 1, RoomType.SUITE, 1);
    assertFalse(guest.getValidatedReservationList().isEmpty());
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
