// --== CS400 Project One File Header ==--
// Name: Pratham Patel
// CSL Username: ppatel
// Email: ppatel74@wisc.edu
// Lecture #: 004
// Notes to Grader: none

/**
 * This class creates the constructor for the data to be stored in and also implements getters for
 * each element of the data set
 * 
 * @author pathup
 *
 */
public class CarInventoryDW implements CarInventoryInterfaceDW {


  private String model;
  private String price;
  private String brand;
  private String year;

  /**
   * Instantiation of new post objects requires the following data:
   * 
   * @param name  describing the cars model and year
   * @param price of the car
   * @param brand of the car
   */
  public CarInventoryDW(String brand, String model, String year, String price) {

    this.model = model;
    this.price = price;
    this.brand = brand;
    this.year = year;
  }

  @Override
  public String getModel() {
    // TODO Auto-generated method stub
    return model;
  }

  @Override
  public String getPrice() {
    // TODO Auto-generated method stub
    return price;
  }

  @Override
  public String getBrand() {
    // TODO Auto-generated method stub
    return brand;
  }


  @Override
  public String getYear() {
    // TODO Auto-generated method stub
    return year;
  }

  @Override
  public String toString() {
    return brand + " " + model + " " + year + " " + price;
  }


}


