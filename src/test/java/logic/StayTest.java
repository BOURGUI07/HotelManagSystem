/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package logic;

import domain.Room;
import domain.RoomType;
import domain.RoomView;
import domain.SpecialRequest;
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
public class StayTest {
  private static Room room;
  private static Room room1;
  private static LocalDate start;
  private static LocalDate end;
  private static LocalDate start1;
  private static LocalDate end1;
  private static Stay stay;
  private static Stay stay1;
  private static Stay stay2;
  
  public StayTest() {
  }
  
  @BeforeAll
  public static void setUpClass() {
    room = new Room("2A31", RoomType.SINGLE, RoomView.CITY_VIEW, 22.5, 1);
    start = LocalDate.of(2024, Month.MARCH, 15);
    end = LocalDate.of(2024, Month.MARCH, 19);
    stay = new Stay(room, start, end);
    start1 = LocalDate.of(2024, Month.MARCH, 15);
    end1 = LocalDate.of(2024, Month.MARCH, 18);
    stay1 = new Stay(room, start1, end1);
    room1 = new Room("2A31", RoomType.SINGLE, RoomView.CITY_VIEW, 22.5, 1);
    stay2 = new Stay(room1, start, end);
  }
  
  @Test
  void testGetNumberOfSatyingDays(){
    assertEquals(4, stay.getNumberOfSatyingDays());
  }
  
  @Test
  void testGetRoom(){
    assertEquals(new Room("2A31", RoomType.SINGLE, RoomView.CITY_VIEW, 22.5, 4), stay.getRoom());
  }
  
  @Test 
  void testGetStartDate(){
    assertEquals(LocalDate.of(2024, Month.MARCH, 15), stay.getStartDate());
  }
  
  @Test 
  void testGetEndDate(){
    assertEquals(LocalDate.of(2024, Month.MARCH, 19), stay.getEndDate());
  }
  
  @Test
  void testSpecialRequests(){
    assertTrue(stay.getSpecialRequests().isEmpty());
    stay.addSpecialRequest(SpecialRequest.QUIET);
    assertTrue(stay.getSpecialRequests().size()==1);
  }
  
  @Test
  void testEqualsMethod(){
    assertTrue(stay.equals(stay2));
    assertFalse(stay.equals(stay1));
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
