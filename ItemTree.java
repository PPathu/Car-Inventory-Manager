import java.util.List;
import java.util.ArrayList;


/**
 * Creates a RedBlackTree implementation of a car inventory. This class includes functions such as
 * searching by year, searching by make, re-sorting the tree by price/year. This class does not
 * support user input.
 */
public class ItemTree extends RedBlackTree<Item> implements ItemTreeInterface {

  private boolean sortedByPrice = true;// used to re-sort the tree

  /**
   * @return the total revenue of all the cars sold.
   */
  public int getTotalRevenue() {
    List<Node<Item>> allCars = allCars(root);
    int count = 0;
    for (Node<Item> car : allCars) {
      count += car.data.getPrice();
    }
    return count;
  }

  /**
   * @return a list of all the cars sold.
   */
  private List<Node<Item>> allCars(Node<Item> node) {
    List<Node<Item>> allCars = new ArrayList<Node<Item>>();
    if (node == null)
      return new ArrayList<Node<Item>>();
    allCars.add(node);
    for (Node<Item> nodes : allCars(node.context[1])) {
      allCars.add(nodes);
    }
    for (Node<Item> nodes : allCars(node.context[2])) {
      allCars.add(nodes);
    }
    return allCars;
  }

  /**
   * @param year represents the year of all the cars in the output
   * @return a list of all the cars sold in given year
   */
  public List<String> findCarsThisYear(int year) {
    List<Node<Item>> allCars = allCars(root);
    List<String> validCars = new ArrayList<String>();

    for (Node<Item> car : allCars) {
      if (Integer.parseInt(car.data.getYear()) == year)
        validCars.add(car.data.toString());
    }
    return validCars;
  }

  /**
   * @param make represents the make of all the cars in the output
   * @return a list of all the cars sold of given make
   */
  public List<String> findCarsThisMake(String make) {
    List<Node<Item>> allCars = allCars(root);
    List<String> validCars = new ArrayList<String>();

    for (Node<Item> car : allCars) {
      int index = car.data.getName().indexOf(" ");
      if (car.data.getName().substring(0, index).equals(make))
        validCars.add(car.data.toString());
    }
    return validCars;
  }

  /**
   * Performs a naive insertion into a binary search tree: adding the input data value to a new node
   * in a leaf position within the tree. After this insertion, no attempt is made to restructure or
   * balance the tree. This tree will not hold null references. Unlike RedBlackTree's
   * implementation, this class can hold duplicate items.
   * 
   * @param data to be added into this binary search tree
   * @return true if the value was inserted, false if not
   * @throws NullPointerException     when the provided data argument is null
   * @throws IllegalArgumentException when data is already contained in the tree
   */
  @Override
  public boolean insert(Item data) throws NullPointerException, IllegalArgumentException {
    // null references cannot be stored within this tree
    if (data == null)
      throw new NullPointerException("This RedBlackTree cannot store null references.");

    Node<Item> newNode = new Node<Item>(data);
    if (this.root == null) {
      // add first node to an empty tree
      root = newNode;
      size++;
      enforceRBTreePropertiesAfterInsert(newNode);
      return true;
    } else {
      // insert into subtree
      Node<Item> current = this.root;
      while (true) {
        int compare = newNode.data.compareTo(current.data);
        if (compare <= 0) {
          // insert in left subtree
          if (current.context[1] == null) {
            // empty space to insert into
            current.context[1] = newNode;
            newNode.context[0] = current;
            this.size++;
            enforceRBTreePropertiesAfterInsert(newNode);
            return true;
          } else {
            // no empty space, keep moving down the tree
            current = current.context[1];
          }
        } else {
          // insert in right subtree
          if (current.context[2] == null) {
            // empty space to insert into
            current.context[2] = newNode;
            newNode.context[0] = current;
            this.size++;
            enforceRBTreePropertiesAfterInsert(newNode);
            return true;
          } else {
            // no empty space, keep moving down the tree
            current = current.context[2];
          }
        }
      }
    }
  }

  /**
   * reSorts the red-black tree into a new RBT tree. if it is sorted by price, it re-sorts it by
   * year, and if sorted by year, it re-sorts it by price. Note that this method can also override
   * the compareTo method in the Item class. this is needed to re-sort the tree by year.
   */
  public void reSort() {
    class classA extends Item {

      public classA(String name, int price, String year) {
        super(name, price, year);
      }

      @Override
      public int compareTo(Item other) {
        int thisint = Integer.parseInt(this.getYear());
        int otherint = Integer.parseInt(other.getYear());
        if (thisint > otherint)
          return 1;
        else if (thisint < otherint)
          return -1;
        else
          return 0;
      }

    }
    RedBlackTree<Item> newTree = new ItemTree();
    List<Node<Item>> list = allCars(root);
    if (sortedByPrice) {
      for (Node<Item> node : list) {
        Item obj = new classA(node.data.getName(), node.data.getPrice(), node.data.getYear());
        newTree.insert(obj);
      }
    } else {
      for (Node<Item> node : list) {
        Item obj = new Item(node.data.getName(), node.data.getPrice(), node.data.getYear());
        newTree.insert(obj);
      }
    }
    this.root = newTree.root;// set original tree to newtree.
    sortedByPrice = !sortedByPrice;
  }

  public boolean getSortedBy() {
    return sortedByPrice;
  }

}
