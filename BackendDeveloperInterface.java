// --== CS400 Spring 2023 File Header Information ==--
// Name: Amogh Parvate
// Email: parvate@wisc.edu
// Team: DR
// TA: Yuye Jiang
// Lecturer: Florian Heimerl
// Notes to Grader: Have a good day!

import java.io.FileNotFoundException;
import java.util.List;

public interface BackendDeveloperInterface {

  // constructor
  // public BD(RedBlackTree<CarInterface> tree, DW reader);

  // This method will load the CSV file
  public void loadData(String filename) throws FileNotFoundException;


  // This method will search for car within a range of min and max
  public List<String> findCarsInRange(int min, int max);

  // This method will search for the first car in this year using AE’s method
  public String findCarsThisYear(int year);

  // This method will search for the first car with a specific make using AE’s method
  public String findCarsThisMake(String make);


  // gives overall stats regarding inventory
  public String getStats();

  // adds a car to the RedBlackTree
  public void addCar(ItemInterface car);

  // calls AE’s resort method
  public void resort();

  // shows all cars using InOrderTraversal using formatting
  public String showAllCars();

}
