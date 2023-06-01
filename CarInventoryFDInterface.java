// --== CS400 P2W3 ==--
// Name: Shivam Ratnani
// CSL Username: ratnani
// Email: ratnani@wisc.edu
// Lecture #: (3:30 - 4:20) 4
// Notes to Grader: N/A

import java.util.List;
import java.util.Scanner;

public interface CarInventoryFDInterface {
  // public CarInventoryFDInterface (Scanner scanner, BackendDeveloperInterface backend);

  public char mainMenuPrompt();

  public void runOperationLoop();

  public void loadDataOperation();

  public void addCarToInventory();

  public void searchByMake();

  public void searchByPriceRange();

  public void searchByYear();

  public void showAllCars();

  public void resort();

  public void displayStatistics();
}
