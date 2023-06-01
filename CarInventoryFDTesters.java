// --== CS400 P2W3 ==--
// Name: Shivam Ratnani
// CSL Username: ratnani
// Email: ratnani@wisc.edu
// Lecture #: (3:30 - 4:20) 4
// Notes to Grader: N/A

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class CarInventoryFDTesters {
    /**
     * This method tests the loadDataOperation method in CarInventoryFD as well as the UI.
     */
    @Test
    public void frontendTest1() {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteStream);
        PrintStream previous = System.out;
        System.setOut(printStream);

        String testInput = "1\nfile.txt\n0\n";

        Scanner scan = new Scanner(testInput);
        CarInventoryBD backendTest = new CarInventoryBD();


        CarInventoryFD frontendTest = new CarInventoryFD(backendTest, scan);

        frontendTest.runOperationLoop();

        System.out.flush();
        System.setOut(previous);
        Assertions.assertEquals("-------------------------------------------------------------------------------\n" +
            "Welcome to the Car Inventory App.\n" +
            "-------------------------------------------------------------------------------\n" +
            "Please choose one of the following commands:\n" +
            "[1] Load data from file\n" +
            "[2] Add car to inventory\n" +
            "[3] Search by make\n" +
            "[4] Search within price range\n" +
            "[5] Search by year\n" +
            "[6] Show all cars by price\n" +
            "[7] Show all cars by year\n" +
            "[8] Display statistics\n" +
            "[0] Quit\n" +
            "Enter your command number: Enter the name of the file to load: Data loaded from file.\n" +
            "Please choose one of the following commands:\n" +
            "[1] Load data from file\n" +
            "[2] Add car to inventory\n" +
            "[3] Search by make\n" +
            "[4] Search within price range\n" +
            "[5] Search by year\n" +
            "[6] Show all cars by price\n" +
            "[7] Show all cars by year\n" +
            "[8] Display statistics\n" +
            "[0] Quit\n" +
            "Enter your command number: -------------------------------------------------------------------------------\n" +
            "Thank you for using the Car Inventory App.\n" +
            "-------------------------------------------------------------------------------\n", byteStream.toString());
    }

    /**
     * This method tests the addCarOperation method in CarInventoryFD as well as the UI.
     */
    @Test
    public void frontendTest2() {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteStream);
        PrintStream previous = System.out;
        System.setOut(printStream);

        String testInput = "2\nAudi A4\n2009\n15000\n0\n";

        Scanner scan = new Scanner(testInput);
        CarInventoryBD backendTest = new CarInventoryBD();


        CarInventoryFD frontendTest = new CarInventoryFD(backendTest, scan);

        frontendTest.runOperationLoop();

        System.out.flush();
        System.setOut(previous);
        Assertions.assertEquals("-------------------------------------------------------------------------------\n" +
            "Welcome to the Car Inventory App.\n" +
            "-------------------------------------------------------------------------------\n" +
            "Please choose one of the following commands:\n" +
            "[1] Load data from file\n" +
            "[2] Add car to inventory\n" +
            "[3] Search by make\n" +
            "[4] Search within price range\n" +
            "[5] Search by year\n" +
            "[6] Show all cars by price\n" +
            "[7] Show all cars by year\n" +
            "[8] Display statistics\n" +
            "[0] Quit\n" +
            "Enter your command number: Enter the following information about the car to add to the inventory:\n" +
            "Make & model of car: Year of car: Price of car: Car added to inventory.\n" +
            "-------------------------------------------------------------------------------\n" +
            "Please choose one of the following commands:\n" +
            "[1] Load data from file\n" +
            "[2] Add car to inventory\n" +
            "[3] Search by make\n" +
            "[4] Search within price range\n" +
            "[5] Search by year\n" +
            "[6] Show all cars by price\n" +
            "[7] Show all cars by year\n" +
            "[8] Display statistics\n" +
            "[0] Quit\n" +
            "Enter your command number: -------------------------------------------------------------------------------\n" +
            "Thank you for using the Car Inventory App.\n" +
            "-------------------------------------------------------------------------------\n", byteStream.toString());
    }

    /**
     * This method tests adding a car with invalid parameters,
     * which should rerun the command loop after displaying error message.
     */
    @Test
    public void frontendTest3() {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteStream);
        PrintStream previous = System.out;
        System.setOut(printStream);

        String testInput = "2\nAudi A4\n1500\n0\n";

        Scanner scan = new Scanner(testInput);
        CarInventoryBD backendTest = new CarInventoryBD();


        CarInventoryFD frontendTest = new CarInventoryFD(backendTest, scan);

        frontendTest.runOperationLoop();

        System.out.flush();
        System.setOut(previous);
        Assertions.assertEquals("-------------------------------------------------------------------------------\n" +
            "Welcome to the Car Inventory App.\n" +
            "-------------------------------------------------------------------------------\n" +
            "Please choose one of the following commands:\n" +
            "[1] Load data from file\n" +
            "[2] Add car to inventory\n" +
            "[3] Search by make\n" +
            "[4] Search within price range\n" +
            "[5] Search by year\n" +
            "[6] Show all cars by price\n" +
            "[7] Show all cars by year\n" +
            "[8] Display statistics\n" +
            "[0] Quit\n" +
            "Enter your command number: Enter the following information about the car to add to the inventory:\n" +
            "Make & model of car: Year of car: Error: Year must be between 1885 and 2024.\n" +
            "Please choose one of the following commands:\n" +
            "[1] Load data from file\n" +
            "[2] Add car to inventory\n" +
            "[3] Search by make\n" +
            "[4] Search within price range\n" +
            "[5] Search by year\n" +
            "[6] Show all cars by price\n" +
            "[7] Show all cars by year\n" +
            "[8] Display statistics\n" +
            "[0] Quit\n" +
            "Enter your command number: -------------------------------------------------------------------------------\n" +
            "Thank you for using the Car Inventory App.\n" +
            "-------------------------------------------------------------------------------\n", byteStream.toString());
    }

    /**
     * This method tests the searchByMake and searchByPriceRange methods
     * in CarInventoryFD with valid and invalid input as well as the UI.
     */
    @Test
    public void frontendTest4(){
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteStream);
        PrintStream previous = System.out;
        System.setOut(printStream);

        String testInput = "3\nVolkswagen Passat\n4\n5000\n4000\n0\n";

        Scanner scan = new Scanner(testInput);
        CarInventoryBD backendTest = new CarInventoryBD();


        CarInventoryFD frontendTest = new CarInventoryFD(backendTest, scan);

        frontendTest.runOperationLoop();

        System.out.flush();
        System.setOut(previous);
        Assertions.assertEquals("-------------------------------------------------------------------------------\n" +
            "Welcome to the Car Inventory App.\n" +
            "-------------------------------------------------------------------------------\n" +
            "Please choose one of the following commands:\n" +
            "[1] Load data from file\n" +
            "[2] Add car to inventory\n" +
            "[3] Search by make\n" +
            "[4] Search within price range\n" +
            "[5] Search by year\n" +
            "[6] Show all cars by price\n" +
            "[7] Show all cars by year\n" +
            "[8] Display statistics\n" +
            "[0] Quit\n" +
            "Enter your command number: Enter the make and model of the car to search for:\n" +
            "null\n" +
            "Please choose one of the following commands:\n" +
            "[1] Load data from file\n" +
            "[2] Add car to inventory\n" +
            "[3] Search by make\n" +
            "[4] Search within price range\n" +
            "[5] Search by year\n" +
            "[6] Show all cars by price\n" +
            "[7] Show all cars by year\n" +
            "[8] Display statistics\n" +
            "[0] Quit\n" +
            "Enter your command number: Enter the minimum price:\n" +
            "Enter the maximum price:\n" +
            "Error: Maximum price must be greater than minimum price.\n" +
            "Please choose one of the following commands:\n" +
            "[1] Load data from file\n" +
            "[2] Add car to inventory\n" +
            "[3] Search by make\n" +
            "[4] Search within price range\n" +
            "[5] Search by year\n" +
            "[6] Show all cars by price\n" +
            "[7] Show all cars by year\n" +
            "[8] Display statistics\n" +
            "[0] Quit\n" +
            "Enter your command number: -------------------------------------------------------------------------------\n" +
            "Thank you for using the Car Inventory App.\n" +
            "-------------------------------------------------------------------------------\n", byteStream.toString());
    }

    /**
     * This method tests the addCar method with valid/invalid inputs along with searchByMake and
     * searchByYear with valid and invalid inputs respectively, and it also tests the getStatistics method.
     */
    @Test
    public void frontendTest5(){
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteStream);
        PrintStream previous = System.out;
        System.setOut(printStream);

        String testInput = "2\nAudi S6\n2015\n32000\n2\nAudi A3\n1999\n-5\n3\nAudi A3\n5\n1500\n8\n0\n";

        Scanner scan = new Scanner(testInput);
        CarInventoryBD backendTest = new CarInventoryBD();


        CarInventoryFD frontendTest = new CarInventoryFD(backendTest, scan);

        frontendTest.runOperationLoop();

        System.out.flush();
        System.setOut(previous);
        Assertions.assertEquals("-------------------------------------------------------------------------------\n" +
            "Welcome to the Car Inventory App.\n" +
            "-------------------------------------------------------------------------------\n" +
            "Please choose one of the following commands:\n" +
            "[1] Load data from file\n" +
            "[2] Add car to inventory\n" +
            "[3] Search by make\n" +
            "[4] Search within price range\n" +
            "[5] Search by year\n" +
            "[6] Show all cars by price\n" +
            "[7] Show all cars by year\n" +
            "[8] Display statistics\n" +
            "[0] Quit\n" +
            "Enter your command number: Enter the following information about the car to add to the inventory:\n" +
            "Make & model of car: Year of car: Price of car: Car added to inventory.\n" +
            "-------------------------------------------------------------------------------\n" +
            "Please choose one of the following commands:\n" +
            "[1] Load data from file\n" +
            "[2] Add car to inventory\n" +
            "[3] Search by make\n" +
            "[4] Search within price range\n" +
            "[5] Search by year\n" +
            "[6] Show all cars by price\n" +
            "[7] Show all cars by year\n" +
            "[8] Display statistics\n" +
            "[0] Quit\n" +
            "Enter your command number: Enter the following information about the car to add to the inventory:\n" +
            "Make & model of car: Year of car: Price of car: Error: Price must be greater than 0.\n" +
            "Please choose one of the following commands:\n" +
            "[1] Load data from file\n" +
            "[2] Add car to inventory\n" +
            "[3] Search by make\n" +
            "[4] Search within price range\n" +
            "[5] Search by year\n" +
            "[6] Show all cars by price\n" +
            "[7] Show all cars by year\n" +
            "[8] Display statistics\n" +
            "[0] Quit\n" +
            "Enter your command number: Enter the make and model of the car to search for:\n" +
            "null\n" +
            "Please choose one of the following commands:\n" +
            "[1] Load data from file\n" +
            "[2] Add car to inventory\n" +
            "[3] Search by make\n" +
            "[4] Search within price range\n" +
            "[5] Search by year\n" +
            "[6] Show all cars by price\n" +
            "[7] Show all cars by year\n" +
            "[8] Display statistics\n" +
            "[0] Quit\n" +
            "Enter your command number: Enter the year to search for:\n" +
            "Error: Year must be between 1885 and 2024.\n" +
            "Please choose one of the following commands:\n" +
            "[1] Load data from file\n" +
            "[2] Add car to inventory\n" +
            "[3] Search by make\n" +
            "[4] Search within price range\n" +
            "[5] Search by year\n" +
            "[6] Show all cars by price\n" +
            "[7] Show all cars by year\n" +
            "[8] Display statistics\n" +
            "[0] Quit\n" +
            "Enter your command number: null\n" + //After backendIntegration this should return "There are currently no cars with an average price of $0.00"
            "Please choose one of the following commands:\n" +
            "[1] Load data from file\n" +
            "[2] Add car to inventory\n" +
            "[3] Search by make\n" +
            "[4] Search within price range\n" +
            "[5] Search by year\n" +
            "[6] Show all cars by price\n" +
            "[7] Show all cars by year\n" +
            "[8] Display statistics\n" +
            "[0] Quit\n" +
            "Enter your command number: -------------------------------------------------------------------------------\n" +
            "Thank you for using the Car Inventory App.\n" +
            "-------------------------------------------------------------------------------\n", byteStream.toString());
    }

    public static void main(String args[]) {
    }
}
