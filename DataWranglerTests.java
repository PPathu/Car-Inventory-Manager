// --== CS400 Project One File Header ==--
// Name: Pratham Patel
// CSL Username: ppatel
// Email: ppatel74@wisc.edu
// Lecture #: 004
// Notes to Grader: none
import static org.junit.Assert.assertEquals;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * This class test the functionality of retrieving the data from the data set and ensuring that all
 * elements are retrevable via the getters
 * 
 * @author pathup
 *
 */
public class DataWranglerTests {

  @Test
  // test the getYear for the first 5 sets of car data
  public void Tester1() throws FileNotFoundException {

    CarReaderDW reader = new CarReaderDW();
    List<CarInventoryInterfaceDW> inventory = reader.readPostsFromFile("CarInventory.txt");

    // junit test
    Assertions.assertEquals("2010", inventory.get(0).getYear());
    Assertions.assertEquals("2011", inventory.get(1).getYear());
    Assertions.assertEquals("2006", inventory.get(2).getYear());
    Assertions.assertEquals("2011", inventory.get(3).getYear());
    Assertions.assertEquals("2014", inventory.get(4).getYear());
  }

  @Test
  // test the getPrice for the first 5 sets of car data
  public void Tester2() throws FileNotFoundException {

    CarReaderDW reader = new CarReaderDW();
    List<CarInventoryInterfaceDW> inventory = reader.readPostsFromFile("CarInventory.txt");

    // junit test
    Assertions.assertEquals("13328", inventory.get(0).getPrice());
    Assertions.assertEquals("16621", inventory.get(1).getPrice());
    Assertions.assertEquals("8467", inventory.get(2).getPrice());
    Assertions.assertEquals("3607", inventory.get(3).getPrice());
    Assertions.assertEquals("11726", inventory.get(4).getPrice());
  }

  @Test
  // test the getModel for the first 5 sets of car data
  public void Tester3() throws FileNotFoundException {

    CarReaderDW reader = new CarReaderDW();
    List<CarInventoryInterfaceDW> inventory = reader.readPostsFromFile("CarInventory.txt");

    // junit test
    Assertions.assertEquals("RX 450", inventory.get(0).getModel());
    Assertions.assertEquals("Equinox", inventory.get(1).getModel());
    Assertions.assertEquals("FIT", inventory.get(2).getModel());
    Assertions.assertEquals("Escape", inventory.get(3).getModel());
    Assertions.assertEquals("FIT", inventory.get(4).getModel());
  }

  @Test
  // test the getPrice and getBrand for the first 5 sets of car data
  public void Tester4() throws FileNotFoundException {

    CarReaderDW reader = new CarReaderDW();
    List<CarInventoryInterfaceDW> inventory = reader.readPostsFromFile("CarInventory.txt");

    // junit test
    Assertions.assertEquals("CHEVROLET", inventory.get(1).getBrand().toString());

  }

  @Test
  // test the toString for the sets of car data
  public void Tester5() throws FileNotFoundException {

    CarReaderDW reader = new CarReaderDW();
    List<CarInventoryInterfaceDW> inventory = reader.readPostsFromFile("CarInventory.txt");

    // junit test
    Assertions.assertEquals("CHEVROLET Equinox 2011 16621", inventory.get(1).toString());
    Assertions.assertEquals("HONDA FIT 2006 8467", inventory.get(2).toString());
    Assertions.assertEquals("FORD Escape 2011 3607", inventory.get(3).toString());
    Assertions.assertEquals("HONDA FIT 2014 11726", inventory.get(4).toString());
    Assertions.assertEquals("TOYOTA Prius 2010 1803", inventory.get(6).toString());

  }

  @Test
  public void IntegrationTest1() {
    BackendDeveloper backend = new BackendDeveloper(new ItemTree(), new CarReaderDW());

    // Testing empty tree case
    if (!backend.getStats().equals("No Cars currently")) {
      Assertions.fail();
    }

    // Testing with Cars added into the tree
    backend.addCar(new Item("Nissan GTR", 25000, "2010"));
    backend.addCar(new Item("Audi A8", 80000, "2007"));
    backend.addCar(new Item("Ford F150", 50000, "2022"));

    String str = backend.getStats();

    if (!str.contains("3")) {
      Assertions.fail();
    }


  }

  // Test findCarsThisYear method For BD
  @Test
  public void IntegrationTest2() {
    BackendDeveloper backend = new BackendDeveloper(new ItemTree(), new CarReaderDW());

    // 1. Testing empty tree case
    if (backend.findCarsThisYear(2004) != null) {
      Assertions.fail();
    }

    // 2. Testing with multiple cars in tree
    backend.addCar(new Item("Nissan GTR", 25000, "2010"));
    backend.addCar(new Item("Audi A8", 80000, "2007"));
    backend.addCar(new Item("Ford F150", 50000, "2022"));


    // testing trying to find a year of a car which is currently not in the inventory
    String str = backend.findCarsThisYear(2004);
    if (!str.equals("")) {
      Assertions.fail();
    }

    // testing year of the car in the inventory
    str = backend.findCarsThisYear(2007);
    if (!str.contains("Nissan") && !str.contains("Audi")) {
      Assertions.fail();
    }

    str = backend.findCarsThisYear(2022);
    if (!str.contains("Ford")) {
      Assertions.fail();
    }

  }

  // Teset the AE implementation for findCarsThisMake
  @Test
  public void CodeReviewOfAlgorithmEngineer() {
    // testing findCarsThisMake method

    ItemTree tree2 = new ItemTree();
    tree2.insert(new Item("Audi A8", 1, "2018"));
    tree2.insert(new Item("Nissan Rogue", 2, "2089"));
    tree2.insert(new Item("Ford F150", 3, "2005"));
    tree2.insert(new Item("TOYOTA Prius", 4, "2025"));
    tree2.insert(new Item("Nissan GTR", 5, "2000"));

    List<String> validCars2 = new ArrayList<String>();
    validCars2.add("Audi A8 1 2018");

    assertEquals(validCars2, tree2.findCarsThisMake("Audi"));
  }

  // Test total revenue method
  @Test
  public void CodeReviewOfAlgorithmEngineer2() {
    ItemTree tree = new ItemTree();
    tree.insert(new Item("Audi A8", 2, "2018"));
    tree.insert(new Item("Nissan Rogue", 4, "2089"));
    tree.insert(new Item("Ford F150", 6, "2005"));
    tree.insert(new Item("TOYOTA Prius", 8, "2025"));
    tree.insert(new Item("Nissan GTR", 10, "2000"));
    assertEquals(30, tree.getTotalRevenue());
  }



  public static void main(String[] args) {

  }



}


