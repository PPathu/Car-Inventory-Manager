// --== CS400 Spring 2023 File Header Information ==--
// Name: Amogh Parvate
// Email: parvate@wisc.edu
// Team: DR
// TA: Yuye Jiang
// Lecturer: Florian Heimerl
// Notes to Grader: Have a good day!

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BackendDeveloper implements BackendDeveloperInterface {

  ItemTree tree;
  CarInventoryReaderInterfaceDW reader;
  int priceMin;
  int priceMax;
  int yearMin;
  int yearMax;

  public BackendDeveloper(ItemTree tree, CarInventoryReaderInterfaceDW reader) {
    this.tree = (ItemTree) tree;
    this.reader = reader;
    priceMin = -1;
    priceMax = -1;
    yearMin = -1;
    yearMax = -1;
  }

  @Override
  public void loadData(String filename) throws FileNotFoundException {
    List<CarInventoryInterfaceDW> loadList = null;
    try {
      loadList = reader.readPostsFromFile(filename);
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("Error: File not found!");
    }
    for (CarInventoryInterfaceDW a : loadList) {
      this.addCar(
          new Item(a.getBrand() + " " + a.getModel(), Integer.parseInt(a.getPrice()), a.getYear()));
    }
  }

  @Override
  public List<String> findCarsInRange(int min, int max) {
    ArrayList<Item> parseList = new ArrayList<Item>();
    ArrayList<String> returnValue = new ArrayList<String>();
    if (this.tree.isEmpty())
      return returnValue;

    ArrayList<ItemTree.Node<Item>> nodeList = new ArrayList<ItemTree.Node<Item>>();
    ItemTree.Node<Item> current = tree.root;
    while (!nodeList.isEmpty() || current != null) {
      if (current == null) {
        RedBlackTree.Node<Item> popped = nodeList.remove(0);
        parseList.add(popped.data);
        current = popped.context[2];
      } else {
        nodeList.add(current);
        current = current.context[1];
      }
    }

    for (Item car : parseList) {
      if (car.getPrice() >= min && car.getPrice() <= max) {
        returnValue.add(car.toString());
      }
    }

    return returnValue;
  }

  @Override
  public String getStats() {

    if (tree.isEmpty()) {
      return "No Cars currently";
    }

    if (tree.getSortedBy()) {
      return "This tree currently contains " + tree.size() + " cars."
          + "\nThe lowest price for a car is $" + this.priceMin + ", while the highest price is $"
          + this.priceMax + ".";
    } else {
      return "This tree currently contains " + tree.size() + " cars."
          + "\nThe oldest car was made in " + this.yearMin + ", while the newest car was made in "
          + this.yearMax + ".";
    }
  }

  @Override
  public void addCar(ItemInterface car) {
    tree.insert((Item) car);
    if (car.getPrice() < this.priceMin || priceMin == -1) {
      priceMin = car.getPrice();
    }
    if (car.getPrice() > this.priceMax || priceMax == -1) {
      priceMax = car.getPrice();
    }
    if (Integer.parseInt(car.getYear()) < this.yearMin || yearMin == -1) {
      yearMin = Integer.parseInt(car.getYear());
    }
    if (Integer.parseInt(car.getYear()) > this.yearMax || yearMax == -1) {
      yearMax = Integer.parseInt(car.getYear());
    }
  }

  @Override
  public String findCarsThisYear(int year) {

    if (tree.isEmpty()) {
      return null;
    }

    String returnValue = "";
    for (String a : tree.findCarsThisYear(year)) {
      returnValue = returnValue + "\n" + a;
    }
    return returnValue.trim();
  }

  @Override
  public String findCarsThisMake(String make) {
    ArrayList<String> listString = (ArrayList<String>) tree.findCarsThisMake(make);
    String returnString = "";
    for (String a : listString) {
      returnString = returnString + a + "\n";
    }
    return returnString.substring(0, returnString.length() - 2);
  }

  @Override
  public void resort() {
    tree.reSort();
  }

  @Override
  public String showAllCars() {
    return tree.toInOrderString().replace(", ", "\n").replace("[ ", "").replace(" ]", "");
  }

  @Helper
  private ItemTree.Node<Item> findMin() {
    ItemTree.Node<Item> firstNode = this.tree.root;
    ItemTree.Node<Item> returnNode = firstNode;
    while (firstNode.context[1] != null) {
      firstNode = firstNode.context[1];
      returnNode = firstNode;
    }
    return returnNode;
  }

  @Helper
  private ItemTree.Node<Item> findMax() {
    ItemTree.Node<Item> firstNode = this.tree.root;
    ItemTree.Node<Item> returnNode = firstNode;
    while (firstNode.context[2] != null) {
      firstNode = firstNode.context[2];
      returnNode = firstNode;
    }
    return returnNode;
  }

}
