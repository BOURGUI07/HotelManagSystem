/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package logic;

import domain.Admin;
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
public class AdminAccountManagTest {
  private static Admin admin1;
  private static AdminAccountManag manag;
  
  
  public AdminAccountManagTest() {
  }
  
  @BeforeAll
  public static void setUpClass() {
    
  }
  
  @Test
  void test1(){
    manag.registerAdmin(admin1);
    assertTrue(manag.getAdminList().size()==1);
    assertTrue(manag.getMap().size()==1);
  }
  
  @Test
  void testIsAdminRegistrated(){
    manag.registerAdmin(admin1);
    assertTrue(manag.isAdminRegistrated(admin1));
  }
  
  @Test 
  void testDoesNameAlreadyExists(){
    manag.registerAdmin(admin1);
    assertEquals(true, manag.doesUserNameAlreadyExists("admin1"));
  }
  
  @Test
  void testGetEmailForUsername(){
    manag.registerAdmin(admin1);
    assertEquals("email1", manag.getEmailForUserName("admin1"));
  }
  
  @AfterAll
  public static void tearDownClass() {
  }
  
  @BeforeEach
  public void setUp() {
    admin1 = new Admin("admin1", "code1", "email1");

    manag = new AdminAccountManag();
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
