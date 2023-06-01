import org.junit.Test;
import static org.junit.Assert.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AlgorithmEngineerTests {

  @Test
  // testing get methods for one node
  public void test1() {
    ItemTree tree = new ItemTree();
    tree.insert(new Item("TOYOTA Prius", 20698, "2001"));

    assertEquals("TOYOTA Prius", tree.root.data.getName());
    assertEquals(20698, tree.root.data.getPrice());
    assertEquals("2001", tree.root.data.getYear());


  }

  @Test
  // testing toString for Item class
  public void test2() {
    ItemTree tree = new ItemTree();
    tree.insert(new Item("TOYOTA Prius", 20698, "2001"));

    assertEquals("TOYOTA Prius 20698 2001", tree.root.data.toString());

  }

  // test resort method and correct insertion of duplicate items
  @Test
  public void test3() {
    ItemTree tree = new ItemTree();
    tree.insert(new Item("TOYOTA Camry", 1, "2012"));
    tree.insert(new Item("TOYOTA Camry", 2, "2011"));
    tree.insert(new Item("TOYOTA Sinatra", 3, "2012"));
    tree.insert(new Item("TOYOTA Prius", 4, "2022"));
    tree.insert(new Item("TOYOTA Camry", 5, "2032"));

    // re-Sort tree by year
    tree.reSort();
    assertEquals("[ TOYOTA Camry 2 2011, TOYOTA Sinatra 3 2012, TOYOTA Camry 1 2012, "
        + "TOYOTA Prius 4 2022, TOYOTA Camry 5 2032 ]", tree.toInOrderString());
    // re-sort tree by price
    tree.reSort();
    assertEquals("[ TOYOTA Camry 1 2012, TOYOTA Camry 2 2011, TOYOTA Sinatra 3 2012, "
        + "TOYOTA Prius 4 2022, TOYOTA Camry 5 2032 ]", tree.toInOrderString());
  }

  // testing total revenue method
  @Test
  public void test4() {
    ItemTree tree = new ItemTree();
    tree.insert(new Item("TOYOTA Camry", 1, "2002"));
    tree.insert(new Item("TOYOTA Camry", 2, "2502"));
    tree.insert(new Item("TOYOTA Sinatra", 3, "2012"));
    tree.insert(new Item("TOYOTA Prius", 4, "2022"));
    tree.insert(new Item("TOYOTA Camry", 5, "2032"));
    assertEquals(15, tree.getTotalRevenue());
  }

  // testing search methods
  @Test
  public void test5() {
    // testing findCarsThisYear method
    ItemTree tree = new ItemTree();
    tree.insert(new Item("TOYOTA Camry", 1, "2012"));
    tree.insert(new Item("TOYOTA Camry", 2, "2012"));
    tree.insert(new Item("TOYOTA Sinatra", 3, "2012"));
    tree.insert(new Item("TOYOTA Prius", 4, "2022"));
    tree.insert(new Item("TOYOTA Camry", 5, "2032"));
    List<String> validCars = new ArrayList<String>();

    validCars.add("TOYOTA Camry 2 2012");
    validCars.add("TOYOTA Camry 1 2012");
    validCars.add("TOYOTA Sinatra 3 2012");


    assertEquals(validCars, tree.findCarsThisYear(2012));

    // testing findCarsThisMake method
    ItemTree tree2 = new ItemTree();
    tree2.insert(new Item("TOYOTA Camry", 1, "2012"));
    tree2.insert(new Item("Honda Camry", 2, "2052"));
    tree2.insert(new Item("Ford Sinatra", 3, "2002"));
    tree2.insert(new Item("TOYOTA Prius", 4, "2022"));
    tree2.insert(new Item("Hyundai Camry", 5, "2032"));
    List<String> validCars2 = new ArrayList<String>();
    validCars2.add("TOYOTA Camry 1 2012");
    validCars2.add("TOYOTA Prius 4 2022");

    assertEquals(validCars2, tree2.findCarsThisMake("TOYOTA"));
  }
  // integration of DW and AE

  public static String test6() throws FileNotFoundException {
    BackendDeveloper backendDeveloperClass =
        new BackendDeveloper(new ItemTree(), new CarReaderDW());
    List<Item> inventory = new ArrayList<>();

    try {
      backendDeveloperClass.loadData("CarInventory.txt");
    } catch (FileNotFoundException e) {
      fail();
    }
    Scanner scnr = new Scanner(new File("CarInventory.txt"));

    while (scnr.hasNextLine()) {
      // for each line in the file being read:
      String carDetails = scnr.nextLine();
      // split that line into parts around around the delimiter: ,
      String[] parts = carDetails.split(",");
      // lines should have all four parts
      List<String> inventoryInRange = backendDeveloperClass.findCarsInRange(30, 20000);

      // if (parts.length == 4&&Integer.parseInt(parts[3])>30&&Integer.parseInt(parts[3])<20000) {
      // if(!inventoryInRange.contains(new Item(parts[0]+"
      // "+parts[1],Integer.parseInt(parts[3]),parts[2]).toString()))
      // return Arrays.toString(parts);
      // }
      return backendDeveloperClass.findCarsThisMake("VOLKSWAGEN");

    }
    scnr.close();
    return "";

  }

  public static void CodeReviewOfFrontendDeveloper() {
    // testing if any commands accepts bad inputs and if it returns an error message
    /*
     * Tested the following inputs for the following commands: *.txt, afds.txt, afds, 2, 2.txt,
     */
    CarInventoryApp.main(null);

  }

  public static void main(String[] args) throws FileNotFoundException {
    CodeReviewOfFrontendDeveloper();
  }
}
