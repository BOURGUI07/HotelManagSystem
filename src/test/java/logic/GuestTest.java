/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package logic;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
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
public class GuestTest {
  private static Guest guest;
  private static Guest guest1;
  private static Guest guest2;

  public GuestTest() {
  }
  
  @BeforeAll
  public static void setUpClass() {
    guest = new Guest("youness", "email", "0678", "marrakech");
    guest1 = new Guest("YOUNESS", "email", "0678", "marrakech");
    guest2 = new Guest("youness", "email", "0678", "medina");
  }
  
  @Test
  void testID(){
    assertNotNull(guest.getId());
    assertFalse(guest.getId().equals(UUID.randomUUID()));
  }
  
  @Test
  void testGetters(){
    assertEquals("youness", guest.getName());
    assertEquals("email", guest.getEmail());
    assertEquals("0678", guest.getPhoneNumber());
    assertEquals("marrakech", guest.getAddress());
  }
  
  @Test
  void listsEmptiness(){
    assertTrue(guest.getGuestReservations().isEmpty());
    assertTrue(guest.getGuestStays().isEmpty());
    assertTrue(guest.getValidatedReservationList().isEmpty());
    assertTrue(guest.getCancelledReservationList().isEmpty());
  }
  
  @Test
  void testEqualsMethod(){
    assertFalse(guest.equals(guest1));
    assertTrue(guest.equals(guest2));
    Set<Guest> set = new HashSet<>();
    set.add(guest);
    set.add(guest1);
    set.add(guest2);
    assertTrue(set.size()==2);
  }
  
  @Test
  void test0(){
    Guest guest = new Guest("youness", "email", "0678", "marrakech");
    LocalDate date = LocalDate.of(2024, Month.MAY, 4);
    assertTrue(guest.canReservationBeCancelled(date));
  }
  
  @Test
  void test1(){
    Guest guest = new Guest("youness", "email", "0678", "marrakech");
    LocalDate date = LocalDate.of(2024, Month.MAY, 2);
    assertFalse(guest.canReservationBeCancelled(date));
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
