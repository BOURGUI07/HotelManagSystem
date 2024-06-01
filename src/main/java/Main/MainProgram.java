/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;
import ui.UserInterface;
import java.util.Scanner;


/**
 *
 * @author lenovo
 */
public class MainProgram {
  public static void main(String[] args) throws NoSuchMethodException{
    Scanner scan = new Scanner(System.in);
    UserInterface userI = new UserInterface(scan);
    userI.welcomePage();
  }
}
