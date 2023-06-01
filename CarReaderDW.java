// --== CS400 Project One File Header ==--
// Name: Pratham Patel
// CSL Username: ppatel
// Email: ppatel74@wisc.edu
// Lecture #: 004
// Notes to Grader: none
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is responsible for reading in the data from the data set and returning it within a an
 * arraylist
 * 
 * @author pathup
 *
 */
public class CarReaderDW implements CarInventoryReaderInterfaceDW {
  ArrayList<CarInventoryInterfaceDW> inventory = new ArrayList<>();


  public List<CarInventoryInterfaceDW> readPostsFromFile(String filename)
      throws FileNotFoundException {
    // use scanner to read from the specified file, and store results in carDetails

    Scanner scnr = new Scanner(new File(filename));

    while (scnr.hasNextLine()) {
      // for each line in the file being read:
      String carDetails = scnr.nextLine();
      // split that line into parts around around the delimiter: ,
      String[] parts = carDetails.split(",");
      // most lines should have all four parts
      if (parts.length == 4)
        inventory.add(new CarInventoryDW(parts[0], parts[1], parts[2], parts[3]));
    }

    // then close the scanner before returning the list of posts
    scnr.close();
    return inventory;


  }



}
