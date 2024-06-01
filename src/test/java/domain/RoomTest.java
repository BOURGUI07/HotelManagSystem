/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package domain;

import java.util.HashSet;
import java.util.Set;
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
public class RoomTest {
  
  private static Room room;
  
  public RoomTest() {
  }
  
  @BeforeAll
  public static void setUpClass() {
    room = new Room("2A31", RoomType.SINGLE, RoomView.CITY_VIEW, 22.5, 1);
  }
  
  @Test
  void testTheGetters(){
    
    assertNotNull(room);
    assertEquals("2A31", room.getNumber());
    assertEquals(RoomType.FAMILY, room.getType());
    assertTrue(room.getAmenities().isEmpty());
    assertTrue(room.getBeds()==1);
    assertTrue(room.getPrice()==1);
    assertTrue(room.getReservations().isEmpty());
    assertTrue(room.getView()==RoomView.CITY_VIEW);
    assertTrue(room.getWaitlist().isEmpty());
  }
  
  @Test
  void testTheEqualsMethod(){
    Room room1 = new Room("2A31", RoomType.SINGLE, RoomView.CITY_VIEW, 22.5, 1);
    Room room2 = new Room("2A31", RoomType.DOUBLE, RoomView.CITY_VIEW, 1.0, 2);
    Set<Room> set = new HashSet<>();
    set.add(room2);
    set.add(room1);
    assertTrue(set.size()==1);
    assertEquals(room1, room2);
  }
  
  @Test
  void testTheMaintainMethod(){
    room.maintain();
    assertEquals(true, room.isUnderMaintenance());
    assertFalse(room.isUnderMaintenance()==false);
    assertEquals(false, room.isAvailable());
    room.unmaintain();
    assertEquals(false, room.isUnderMaintenance());
  }
  
  @Test
  void testTheOccupyMethod(){
    room.occupy();
    assertFalse(room.isOccupied()==false);
    assertTrue(room.isAvailable()==false);
    room.leaveRoom();
    assertTrue(room.isOccupied()==false);
    assertTrue(room.isAvailable()==true);
  }
  
  @Test
  void testTheChangeType(){
    room.changeRoomType(RoomType.SUITE);
    assertEquals(RoomType.SUITE, room.getType());
  }
  
  @Test
  void testTheChangePrice(){
    room.changeRoomPrice(1);
    assertFalse(room.getPrice()==0);
  }
  
  @Test
  void testTheOccupancyLimit(){
    assertEquals(1, room.occupancyLimit());
    room.changeRoomType(RoomType.DOUBLE);
    assertNotEquals(1, room.occupancyLimit());
    room.changeRoomType(RoomType.FAMILY);
    assertEquals(6, room.occupancyLimit());
  }
  
  @Test
  void testAmenitiesMethods(){
    assertEquals(0, room.getAmenities().size());
    room.addAmenities(Amenity.WIFI);
    assertEquals(1, room.getAmenities().size());
    assertTrue(room.getAmenities().contains(Amenity.WIFI));
    room.removeAmenities(Amenity.WIFI);
    assertTrue(room.getAmenities().isEmpty());
  }
  
  @Test
  void testIsAvailable(){
    room.occupy();
    assertTrue(room.isAvailable()==false);
    room.maintain();
    room.leaveRoom();
    assertEquals(false, room.isAvailable());
    room.unmaintain();
    assertEquals(true, room.isAvailable());
  }
  
  @Test
  void testOtherLists(){
    assertTrue(room.getReservations().isEmpty());
    assertTrue(room.getAmenities().isEmpty());
    
  }
  
  
  
  @AfterAll
  public static void tearDownClass() {
  }
  
  @BeforeEach
  public void setUp() {
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
