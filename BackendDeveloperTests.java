// --== CS400 Spring 2023 File Header Information ==--
// Name: Amogh Parvate
// Email: parvate@wisc.edu
// Team: DR
// TA: Yuye Jiang
// Lecturer: Florian Heimerl
// Notes to Grader: Have a good day!

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BackendDeveloperTests {

  @Test
  public void testConstructorAndLoad() {
    BackendDeveloper backendDeveloperClass =
        new BackendDeveloper(new ItemTree(), new CarReaderDW());
    Exception exception = Assertions.assertThrows(FileNotFoundException.class, () -> {
      backendDeveloperClass.loadData("WrongName");
    });

    String actualMessage = exception.getMessage();

    Assertions.assertEquals("Error: File not found!", actualMessage);

    try {
      backendDeveloperClass.loadData("CorrectFileName.txt");
    } catch (FileNotFoundException e) {
      Assertions.fail();
    }
    Assertions.assertEquals("[ Brand1 Model1 1 Year1, Brand2 Model2 2 2, Brand3 Model3 3 Year3 ]",
        backendDeveloperClass.tree.toInOrderString());
  }

  @Test
  public void testFindCarsInRange() {
    BackendDeveloper backendDeveloperClass =
        new BackendDeveloper(new ItemTree(), new CarReaderDW());
    Exception exception = Assertions.assertThrows(FileNotFoundException.class, () -> {
      backendDeveloperClass.loadData("WrongName");
    });

    String actualMessage = exception.getMessage();

    Assertions.assertEquals("Error: File not found!", actualMessage);

    try {
      backendDeveloperClass.loadData("CorrectFileName.txt");
    } catch (FileNotFoundException e) {
      Assertions.fail();
    }
    Assertions.assertEquals("[Brand1 Model1 1 Year1, Brand2 Model2 2 2]",
        backendDeveloperClass.findCarsInRange(1, 2).toString());
    Assertions.assertEquals("[Brand3 Model3 3 Year3, Brand2 Model2 2 2]",
        backendDeveloperClass.findCarsInRange(2, 4).toString());
  }

  @Test
  public void testFindCarsByMakeAndYear() {
    BackendDeveloper backendDeveloperClass =
        new BackendDeveloper(new ItemTree(), new CarReaderDW());
    Exception exception = Assertions.assertThrows(FileNotFoundException.class, () -> {
      backendDeveloperClass.loadData("WrongName");
    });

    String actualMessage = exception.getMessage();

    Assertions.assertEquals("Error: File not found!", actualMessage);

    try {
      backendDeveloperClass.loadData("CorrectFileName.txt");
    } catch (FileNotFoundException e) {
      Assertions.fail();
    }
    Assertions.assertEquals("CorrectMake",
        backendDeveloperClass.findCarsThisMake("Brand2").toString());
    Assertions.assertEquals("CorrectYear", backendDeveloperClass.findCarsThisYear(2).toString());
    Assertions.assertEquals("WrongMake",
        backendDeveloperClass.findCarsThisMake("Brand1").toString());
    Assertions.assertEquals("WrongYear", backendDeveloperClass.findCarsThisYear(1).toString());
  }

  @Test
  public void testGetStats() {
    BackendDeveloper backendDeveloperClass =
        new BackendDeveloper(new ItemTree(), new CarReaderDW());
    Exception exception = Assertions.assertThrows(FileNotFoundException.class, () -> {
      backendDeveloperClass.loadData("WrongName");
    });

    String actualMessage = exception.getMessage();

    Assertions.assertEquals("Error: File not found!", actualMessage);

    try {
      backendDeveloperClass.loadData("CorrectFileName.txt");
    } catch (FileNotFoundException e) {
      Assertions.fail();
    }
    Assertions.assertEquals(
        "This tree currently contains 3 cars.\nThe lowest price for a car is 1, while the highest price is 3",
        backendDeveloperClass.getStats());
  }

  @Test
  public void testShowAllCars() {
    BackendDeveloper backendDeveloperClass =
        new BackendDeveloper(new ItemTree(), new CarReaderDW());
    Exception exception = Assertions.assertThrows(FileNotFoundException.class, () -> {
      backendDeveloperClass.loadData("WrongName");
    });

    String actualMessage = exception.getMessage();

    Assertions.assertEquals("Error: File not found!", actualMessage);

    try {
      backendDeveloperClass.loadData("CorrectFileName.txt");
    } catch (FileNotFoundException e) {
      Assertions.fail();
    }
    Assertions.assertEquals("[ Brand1 Model1 1 Year1, Brand2 Model2 2 2, Brand3 Model3 3 Year3 ]",
        backendDeveloperClass.showAllCars());
  }
}
