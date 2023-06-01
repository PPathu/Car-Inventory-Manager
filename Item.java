/**
 * this class represents 1 item. In this case, every item will represent a car.
 */
public class Item implements ItemInterface, Comparable<Item> {

  private String name;// name includes make and model
  private int price;
  private String year;

  public Item(String name, int price, String year) {
    this.name = name;
    this.price = price;
    this.year = year;
  }

  public String getName() {
    return name;
  }


  public int getPrice() {

    return price;
  }

  public String getYear() {

    return year;
  }

  // compare prices of items. overridden reSort tree
  public int compareTo(Item other) {
    if (this.price > other.price)
      return 1;
    else if (this.price < other.price)
      return -1;
    else
      return 0;
  }

  @Override
  public String toString() {
    return name + " " + price + " " + year;
  }
}
