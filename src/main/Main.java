package edu.uqu.cs;

/*
 * Project Name: Airplane Ticket Reservation System
 * Team Members: Arwa Fadaaq,Sumiah Saed,Albatool Ghannam,Jana Ghazi,Joud Alharthi and Layan Almehmadi
 * Section Number: 7
 * CS 1312
 */
import java.util.*;

/**
 * This is the main class for our project. It provides the entry point for the
 * program and initializes any necessary resources.
 */
public class Main {

    /**
     * This is the main method for the Airplane Ticket Reservation System
     * project. It contains code to execute , call other methods and create
     * objects from other classes.
     *
     * @param args the command line arguments passed to the program
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("\n************************************************************");
        System.out.println("     WELCOME TO OUR AIRPLANE TICKET RESERVATION SYSTEM      ");
        System.out.println("************************************************************\n");
        System.out.print(
                "Please enter your choice from below (1-2):\n\n1.Reservation\n\n2.For employees\n\nEnter your choice: ");
        int select = 0;
        boolean flag1 = true;
        while (flag1) {
            try {

                select = input.nextInt();
                if (select == 1 || select == 2) {
                    flag1 = false;
                } else {
                    System.out.print("Please enter a valid number (1-2): ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Please enter a valid number (1-2): ");
                input.next();
            } // end of catch block
        } // end of while loop

        // creating flight objects and saving it to a text file
        Flight flight1 = new Flight("RUH", "2023-06-04", "05:00", "06:40");
        Flight flight2 = new Flight("DMM", "2023-06-03", "07:55", "10:00");
        Flight flight3 = new Flight("AHB", "2023-06-05", "12:10", "13:35");
        Flight flight4 = new Flight("TUU", "2023-06-04", "20:05", "21:35");
        Flight flight5 = new Flight("RUH", "2023-06-07", "14:00", "15:50");
        Flight.saveFlightsInTextFile();

        // arraylist to store the employees' information
        Employee[] employeeList = new Employee[6];
        employeeList[0] = new Employee("Jana", "Ghazi", "0548116076", "Editior", 1);
        employeeList[1] = new Employee("Arwa", "Fadaaq", "0505521799", "Editior", 2);
        employeeList[2] = new Employee("Albatool", "Ghannam", "0594611966", "Editior", 3);
        employeeList[3] = new Employee("Sumiah", "Saeed", "0577275990", "Editior", 4);
        employeeList[4] = new Employee("Layan", "Ahmed", "0561953356", "Editior", 5);
        employeeList[5] = new Employee("Joud", "AlHarthi", "0570291425", "Editior", 6);

        Loop:
        switch (select) {
            case 1: // reservation
                boolean toBook = true;
                int countPassenger = 1;
                // while loop to make sure the flight exist
                while (toBook) {
                    Flight.displayAllFlights();
                    System.out.print("Please enter the flight number you want to book: ");
                    int flightNumber = input.nextInt();
                    while (!Flight.containsFlightNumber(flightNumber)) {
                        flightNumber = input.nextInt();
                    }

                    int chooseType = 0;
                    boolean flag2 = true;

                    // To select the type of class to be booked
                    System.out.println(
                            "Enter the corresponding number for the class you want to book\n1.First Class\n2.Economy Class");
                    while (flag2) {
                        try {
                            chooseType = input.nextInt();
                            if (chooseType == 1 || chooseType == 2) {
                                flag2 = false;
                            } else {
                                System.out.print("Please enter a valid number (1-2): ");
                            }
                        } catch (InputMismatchException e) {
                            System.out.print("Please enter a valid number (1-2): ");
                            input.next();
                        } // end of catch block
                    } // end of while loop
                    String ticketType;
                    if (chooseType == 1) {
                        ticketType = "First Class";
                    } else {
                        ticketType = "Economy Class";
                    } // end of if else

                    System.out.println("Would you like to confirm your reservation on this flight? (yes/no)");
                    String extra2 = input.nextLine(); // consume extra line
                    String choice4 = input.nextLine();
                    if (choice4.equalsIgnoreCase("yes")) {
                        // creating a passenger object after confirming the reservation

                        Passenger p = new Passenger();
                        System.out.print("\nEnter your first name : ");
                        p.setFirstName(input.next());

                        System.out.print("Enter your last name : ");
                        p.setLastName(input.next());

                        p.setPhoneNamber(p.enterPhoneNumber());
                        System.out.print(
                                "Please enter the passport number\nHint:The passport number It consists a minimum of 7 characters and a maximum of 10 characters\nand contains both capital letters and numbers:");
                        p.setPassportNumber(input.next());

                        System.out.print(
                                "Please enter the expiry date of the passport\nHint: it should be written in this format(yyyy-MM-dd) :");
                        p.setExpirationDate(input.next());
                        if (countPassenger == 1) {
                            p.setAgeGroup("adult");
                        } else {
                            p.setAgeGroup(p.selectAgeGroup());
                        } // end of if else

                        // creating a ticket object
                        for (Flight flight : Flight.flights) {
                            if (flight.getFlightNumber() == flightNumber) {
                                if (flight.addPassenger(p)) {
                                    Ticket ticket = new Ticket(ticketType, flight, p);
                                    System.out.println(ticket);
                                    countPassenger++;
                                } // end of inner if
                            } // end of if
                        } // end of for loop

                        System.out.println("Do you need anything else? (yes/no)");
                        String extra3 = input.nextLine(); // consume extra line
                        String choice6 = input.nextLine();
                        if (choice6.equalsIgnoreCase("no")) {
                            toBook = false;
                            break;
                        } else if (choice6.equalsIgnoreCase("yes")) {
                            System.out.print(
                                    "Please enter your choice from below (1-2):\n\n1.Reservation\n\n2.Cancel reservation\n\nEnter your choice: ");
                            int select2 = input.nextInt();
                            while (select2 != 1 && select2 != 2) {
                                System.out.print("Please enter a valid number (1-2): ");
                                select2 = input.nextInt();
                            } // end of while
                            if (select2 == 2) {
                                System.out.println("Please enter your ticket number to cancel your reservation.");
                                int ticketNumber = input.nextInt();
                                while (!Ticket.cancelReservation(ticketNumber)) {
                                    System.out.println("Please enter a valid ticket number");
                                    ticketNumber = input.nextInt();
                                } // end of while loop
                                toBook = false;
                                break Loop;
                            } // end of if
                        } // end of else if

                    } else if (choice4.equalsIgnoreCase("no")) {
                        System.out.println("Do you want to book another flight? (yes/no)");
                        String choice5 = input.nextLine();
                        if (choice5.equalsIgnoreCase("no")) {
                            toBook = false;
                            break;
                        } // end of if
                    } // end of else if
                } // end of while(toBook) loop

                break; // end of reservation case

            case 2: // the employee case
                boolean isIDValid = false;
                int id;
                // do while to validate employeeID

                do {
                    System.out.println("Please enter your employeeID to continue");
                    id = (input.nextInt()) - 1;
                    if (id >= 0 && id <= 5) {
                        isIDValid = true;
                    } else {
                        System.out.println("That is an invalid employee ID number. Please try again");
                    } // end of if else statements
                } while (!isIDValid); // end of do while loop

                System.out.println("WELCOME " + employeeList[id].getFirstName().toUpperCase() + "!");
                boolean flag = false;
                // while loop to preform different tasks
                while (!flag) {
                    System.out.println("Please enter your choice from below (1-3):\n1.Cancel Flights \n2.Add Flights \n3.Edit Flights");
                    int choice1 = input.nextInt();
                    input.nextLine();
                    while (choice1 != 1 && choice1 != 2 && choice1 != 3) {
                        System.out.print("Please enter a valid number (1 to 3): ");
                        choice1 = input.nextInt();
                    }
                    // switch cases to choose different tasks
                    switch (choice1) {

                        case 1: // Cancel Flights Case
                            Flight.displayAllFlights();
                            System.out.println("Please choose the flight you would like to cancel by Its Flight Number ");
                            int choice2 = input.nextInt();
                            while (!Flight.containsFlightNumber(choice2)) {
                                choice2 = input.nextInt();
                            }
                            input.nextLine();
                            for (int i = 0; i < Flight.flights.size(); i++) {
                                if (choice2 == Flight.flights.get(i).getFlightNumber()) {
                                    employeeList[id].cancelFlight(Flight.flights.get(i));
                                    System.out.println("The Flight " + choice2 + " has been cancelled!");
                                    break;
                                } // end of if
                            } // end of for loop
                            break; // end of case 1

                        case 2: // Add Flights Case
                            employeeList[id].addFlight();
                            Flight.displayAllFlights();
                            System.out.println("The Flight has been added!");
                            break; // end of case 2

                        case 3: // Edit Flight Case
                            Flight.displayAllFlights();
                            System.out.println("Please Choose The Flight You Want To Edit");
                            int choice3 = input.nextInt();
                            while (!Flight.containsFlightNumber(choice3)) {
                                choice3 = input.nextInt();
                            }
                            for (int i = 0; i < Flight.flights.size(); i++) {
                                if (choice3 == Flight.flights.get(i).getFlightNumber()) {
                                    employeeList[id].editFlight(Flight.flights.get(i));
                                    System.out.println("The Flight " + choice3 + " Has Been Edited!");
                                    Flight.displayAllFlights();
                                    break; // end of case 3
                                } // end of if else statement
                            } // end of for loop
                            input.nextLine();
                    } // end of switch

                    System.out.println("Would you like to do any additional tasks? (yes/no)");
                    String choice3 = input.nextLine();
                    if (choice3.equalsIgnoreCase("no")) {
                        System.out.println("Your Session Has Ended.");
                        flag = true;
                    } // end of if
                } // end of while loop
        } // end of switch cases (reservation and employee)
        System.out.println("\nTHANK YOU FOR VISITING US, WE WISH YOU A NICE DAY " + '\u263A');
    } // end of main method
}// end of main class body
