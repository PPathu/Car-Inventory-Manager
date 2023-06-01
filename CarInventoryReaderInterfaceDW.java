// --== CS400 Project One File Header ==--
// Name: Pratham Patel
// CSL Username: ppatel
// Email: ppatel74@wisc.edu
// Lecture #: 004
// Notes to Grader: none
import java.io.FileNotFoundException;
import java.util.List;


/**
 * interface for CarInventoryReaderDW
 * 
 * @author pathup
 *
 */
public interface CarInventoryReaderInterfaceDW {

  public List<CarInventoryInterfaceDW> readPostsFromFile(String filename)
      throws FileNotFoundException;
}

