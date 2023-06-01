// --== CS400 P2W3 ==--
// Name: Shivam Ratnani
// CSL Username: ratnani
// Email: ratnani@wisc.edu
// Lecture #: (3:30 - 4:20) 4
// Notes to Grader: N/A

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class CarInventoryFD implements CarInventoryFDInterface {

  Scanner scanner = new Scanner(System.in);

  private BackendDeveloper backend;

  private boolean sortByYear = false;

  public CarInventoryFD(BackendDeveloper backend, Scanner scanner) {
    this.backend = backend;
    this.scanner = scanner;
  }

  /**
   * This method displays a wide line of dashes to the console.
   */
  private void line() {
    System.out
        .println("-------------------------------------------------------------------------------");
  }

  @Override
  public void runOperationLoop() {
    line(); // display welcome message
    System.out.println("Welcome to the Car Inventory App.");
    line();
    char command = '\0';

    while (command != '0') { // Continue operation loop until user chooses to exit
      command = this.mainMenuPrompt();
      switch (command) {
        case '1': // System.out.println(" Load data from file");
          loadDataOperation();
          break;
        case '2': // System.out.println(" Add car to inventory");
          addCarToInventory();
          break;
        case '3': // System.out.println(" Search by make");
          searchByMake();
          break;
        case '4': // System.out.println(" Search by price range");
          searchByPriceRange();
          break;
        case '5': // System.out.println(" Search by year");
          searchByYear();

          break;
        case '6': // System.out.println(" Show all cars by price");
          if (sortByYear) {
            sortByYear = false;
            resort();
          }

          showAllCars();

          break;
        case '7': // System.out.println(" Show all cars by year");
          if (!sortByYear) {
            sortByYear = true;
            resort();
          }
          showAllCars();

          break;
        case '8': // System.out.println(" Display statistics");
          displayStatistics();

          break;
        case '0': // System.out.println(" Quit");
          // do nothing, containing loop condition will fail
          break;
        case 'a': // CarInventoryNotAdded!!!
          // do nothing, containing loop condition will fail
          break;
        default:
          System.out.println(
              "Didn't recognize that command.  Please type one of the numbers presented to identify the command you would like to choose.");
          break;
      }
    }
    line();
    System.out.println("Thank you for using the Car Inventory App.");
    line();
  }

  @Override
  public char mainMenuPrompt() {
    // Print menu of choices
    System.out.println("Please choose one of the following commands:");

    System.out.println("[1] Load data from file");
    System.out.println("[2] Add car to inventory");
    System.out.println("[3] Search by make");
    System.out.println("[4] Search within price range");
    System.out.println("[5] Search by year");
    System.out.println("[6] Show all cars by price");
    System.out.println("[7] Show all cars by year");
    System.out.println("[8] Display statistics");
    System.out.println("[0] Quit");
    System.out.print("Enter your command number: ");
    String input = scanner.nextLine().trim();
    if (input.length() == 0) // if user's choice is blank then return null
      return '\0';
    if (backend.tree.size <= 0
        && !(input.charAt(0) == '1' || input.charAt(0) == '0' || input.charAt(0) == '2')) {
      System.out.println("You need to load a car inventory first!");
      return 'a';
    } else {
      return input.charAt(0);
    }
  }

  @Override
  public void loadDataOperation() {
    System.out.print("Enter the name of the file to load: ");
    String filename = scanner.nextLine().trim();
    try {
      backend.loadData(filename);
      System.out.println("Data loaded from file.");
    } catch (FileNotFoundException e) {
      System.out.println("Error: Could not find or load file: " + filename);
    }
  }

  @Override
  public void addCarToInventory() {
    System.out.println("Enter the following information about the car to add to the inventory:");
    System.out.print("Make & model of car: ");
    String name = scanner.nextLine().trim();
    String[] nameSplit = name.split(" ");
    nameSplit[0] = nameSplit[0].toUpperCase();
    name = nameSplit[0] + " " + nameSplit[1];
    if (name.length() == 0) {
      System.out.println("Error: Make & model cannot be blank.");
      return;
    }
    System.out.print("Year of car: ");
    int year = Integer.parseInt(scanner.nextLine().trim());
    if (year < 1885 || year > 2024) {
      System.out.println("Error: Year must be between 1885 and 2024.");
      return;
    }
    System.out.print("Price of car: ");
    int price = Integer.parseInt(scanner.nextLine().trim());
    if (price < 0) {
      System.out.println("Error: Price must be greater than 0.");
      return;
    }
    ItemInterface car = new Item(name, price, "" + year);
    backend.addCar(car);
    System.out.println("Car added to inventory.");
    line();
  }

  @Override
  public void searchByMake() {
    System.out.println("Enter the make and model of the car to search for:");
    String name = scanner.nextLine().trim();
    if (name.length() == 0) {
      System.out.println("Error: Make & model cannot be blank.");
      return;
    }
    System.out.println(backend.findCarsThisMake(name));
  }

  @Override
  public void searchByPriceRange() {
    System.out.println("Enter the minimum price:");
    int min = Integer.parseInt(scanner.nextLine().trim());
    if (min < backend.priceMin) {
      System.out.println("Error: Price must be greater than $" + backend.priceMin + ".");
      return;
    }
    System.out.println("Enter the maximum price:");
    int max = Integer.parseInt(scanner.nextLine().trim());
    if (max < min) {
      System.out.println("Error: Maximum price must be greater than minimum price.");
      return;
    }
    if (max > backend.priceMax) {
      System.out.println("Error: Price must be less than $" + backend.priceMax + ".");
      return;
    }
    String rtn = "";
    List<String> arr = backend.findCarsInRange(min, max);
    for (String str : arr) {
      rtn += str + "\n";
    }
    rtn = rtn.substring(0, rtn.length());

    System.out.println(rtn.trim());
  }

  @Override
  public void searchByYear() {
    System.out.println("Enter the year to search for:");
    int year = Integer.parseInt(scanner.nextLine().trim());
    if (year < backend.yearMin || year > backend.yearMax) {
      System.out
          .println("Error: Year must be between " + backend.yearMin + " and " + backend.yearMax);
      return;
    }
    System.out.println(backend.findCarsThisYear(year));
  }

  @Override
  public void showAllCars() {
    System.out.println(backend.showAllCars());
  }

  @Override
  public void resort() {
    backend.resort();
  }

  @Override
  public void displayStatistics() {
    System.out.println(backend.getStats());
  }
}
