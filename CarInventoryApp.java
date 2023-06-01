// --== CS400 P2W3 ==--
// Name: Shivam Ratnani
// CSL Username: ratnani
// Email: ratnani@wisc.edu
// Lecture #: (3:30 - 4:20) 4
// Notes to Grader: N/A

import java.util.*;

public class CarInventoryApp {

  public static void main(String[] args) {
    // Use data wrangler's code to load post data
    // PostReaderDW postLoader = new PostReaderDW();
    // Use algorithm engineer's code to store and search for data
    // HashtableWithDuplicateKeysAE<String, PostInterface> hashtable;
    // hashtable = new HashtableWithDuplicateKeysAE<>();
    // Use the backend developer's code to manage all app specific processing
    BackendDeveloper backend = new BackendDeveloper(new ItemTree(), new CarReaderDW());
    // Use the frontend developer's code to drive the text-base user interface
    Scanner scanner = new Scanner(System.in);
    CarInventoryFDInterface frontend = new CarInventoryFD(backend, scanner);
    frontend.runOperationLoop();
  }


}
