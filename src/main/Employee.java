package edu.uqu.cs;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * This class is a child class from the User class An Employee has a job title
 * and an employee ID. The class has methods to cancel a flight, add a flight,
 * and edit a flight's details.
 */
public class Employee extends User {

    /**
     * job to store the Employee's job
     */
    private String job;
    /**
     * employeeID to store the Employee's ID
     */
    private int employeeID;
    /**
     *
     * The date format used in the application. The format is "yyyy-M-d".
     */
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    /**
     * The time format used in the application. The format is "HH:MM".
     */
    private static final String TIME_FORMAT = "HH:mm";

    Scanner input = new Scanner(System.in);

    /**
     * Employee default constructor
     */
    public Employee() {
        super();
        job = "No Job Title yet";
        employeeID = 0;
    }

    /**
     * Employee constructor used to give the value of the Employee's first name,
     * last name, phone number, job title and employeeID
     *
     * @param first the employee's first name
     * @param last the employee's last name
     * @param employeePhone the employee's phone number
     * @param job the employee's job title
     * @param employeeID the employee's ID
     */
    public Employee(String first, String last, String employeePhone, String job, int employeeID) {
        super(first, last, employeePhone);
        this.job = job;
        this.employeeID = employeeID;
    }

    /**
     * Gets the Employee's job
     *
     * @return the Employee's job
     */
    public String getJob() {
        return job;
    }

    /**
     * Sets the Employee's job title
     *
     * @param job the Employee's job title
     */
    public void setJob(String job) {
        if (job.equals("editor")) {
            this.job = job;
        } else {
            System.out.println("That is not the right job title");
        }
    }

    /**
     * Gets the Employee's ID
     *
     * @return the Employee's ID
     */
    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * Sets the Employee's ID
     *
     * @param employeeID the Employee's ID
     */
    public void setEmployeeID(int employeeID) {
        if (employeeID >= 1 && employeeID <= 6) {
            this.employeeID = employeeID;
        } else {
            System.out.println("The employee ID must be between 1 and 6.");
        }
    }

    /**
     * This method cancels a specified flight by removing it from the list of
     * available flights and saving the updated list to a text file.
     *
     * @param cancelledFlight the flight object to be cancelled
     */
    public void cancelFlight(Flight cancelledFlight) {
        Flight.flights.remove(cancelledFlight);
        Flight.saveFlightsInTextFile();
    } // end of cancelFlight body

    /**
     * this method allows Employees Add Flights to the Flights ArrayList
     */
    public void addFlight() {

        LocalDate currentDate = LocalDate.now();
        String aCity;
        LocalTime depTime;
        LocalTime arrTime;

        System.out.println("Choose the Arrival City (RUH, DMM, AHB, TUU)");
        while (true) {
            aCity = input.nextLine();
            if (!(aCity.equalsIgnoreCase("RUH")
                    || aCity.equalsIgnoreCase("DMM")
                    || aCity.equalsIgnoreCase("AHB")
                    || aCity.equalsIgnoreCase("TUU"))) {

                System.out.println("Please enter the Correct City name from the list");
            } else {
                break;
            } // end of if else
        } // end of while loop
        boolean isDateValid = false;
        LocalDate fDate = LocalDate.now(); // inital value
        do {
            try {
                System.out.println("Enter the Flight Date in the format year-month-day (yyyy-MM-dd)");
                String date = input.nextLine().trim();
                fDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
                if (fDate.isBefore(currentDate)) {
                    System.out.println("Flight Date must be in the future");
                } else {
                    isDateValid = true;
                } // end of if else
            } catch (DateTimeParseException ex) {
                System.out.println("Invalid date format. Please enter a valid date.");
                isDateValid = false;
            } // end of catch block
        } while (!isDateValid); // end of do while loop

        do {
            try {
                System.out.println("Enter the Departure Time in the format hour:minute (HH:mm)");
                String depTimeString = input.nextLine();
                depTime = LocalTime.parse(depTimeString, DateTimeFormatter.ofPattern(TIME_FORMAT));
                System.out.println("Enter the Arrival Time in the format hour:minute (HH:mm)");
                String arrTimeString = input.nextLine();
                arrTime = LocalTime.parse(arrTimeString, DateTimeFormatter.ofPattern(TIME_FORMAT));
                if (arrTime.isBefore(depTime)) {
                    System.out.println("Arrival time must be after departure time.");
                    System.out.println("please re-enter both Departure and Arrival Time");
                } else {
                    break;
                }
            } catch (DateTimeParseException ex) {
                System.out.println("Invalid time format. Please enter a valid time.");
                System.out.println("please re-enter both Departure and Arrival Time");

            } // end of catch block
        } while (true); // end of do while

        Flight newFlight = new Flight(aCity.toUpperCase(), fDate.toString(), depTime.toString(), arrTime.toString());
        Flight.saveFlightsInTextFile();
    } // end of addFlight method

    /**
     * Allows the employee to edit the details of a given Flight object. The
     * user can choose which detail to edit (city, date, departure and arrival
     * time) and enter the new value. The method will continue to prompt the
     * user until all desired edits have been made.
     *
     * @param editedFlight the Flight object to be edited
     */
    public void editFlight(Flight editedFlight) {
        boolean flag = false;
        LocalTime depTime;
        LocalTime arrTime;

        do {
            System.out.println("Please Choose The detail you want to edit ");
            System.out.println("Select 1 for (City)"
                    + " 2 for (Date)"
                    + " 3 for (Departure Time and Arrival Time)");
            int choice = input.nextInt();
            String newExtra = input.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("You have chosen to edit the City ");
                    System.out.println("Choose the New Arrival City (RUH, DMM, AHB, TUU)");
                    while (true) {
                        String newCity = input.nextLine();

                        if (!(newCity.equalsIgnoreCase("RUH")
                                || newCity.equalsIgnoreCase("DMM")
                                || newCity.equalsIgnoreCase("AHB")
                                || newCity.equalsIgnoreCase("TUU"))) {

                            System.out.println("please enter the correct city name from the list");
                        } else {
                            editedFlight.setArrivalCity(newCity);
                            break;
                        } // end of if else statment
                    } // end of while loop            
                    break;
                // end of case 1

                case 2:
                    System.out.println("You have chosen to edit the Date ");
                    String newDate;
                    LocalDate fDate;
                    LocalDate currentDate = LocalDate.now();

                    do {
                        try {
                            System.out.println("Enter the Flight Date in the format year-month-day (yyyy-MM-dd)");
                            newDate = input.nextLine().trim();
                            fDate = LocalDate.parse(newDate, DateTimeFormatter.ofPattern(DATE_FORMAT));
                            if (fDate.isBefore(currentDate)) {
                                System.out.println("Flight Date must be in the future");
                            } else {
                                break;
                            }
                        } catch (DateTimeParseException ex) {
                            System.out.println("Invalid date format. Please enter a valid date.");
                        } // end of catch block
                    } while (true);  // end of loop
                    editedFlight.setFlightDate(newDate);
                    break;
                // end of case 2

                case 3:
                    System.out.println("You have chosen to edit the Departure and Arrival Time ");
                    String newDepartureTime;
                    String newArrivalTime;
                    do {
                        try {
                            System.out.println("Enter the Departure Time in the format hour:minute (HH:mm)");
                            newDepartureTime = input.nextLine();
                            depTime = LocalTime.parse(newDepartureTime, DateTimeFormatter.ofPattern(TIME_FORMAT));
                            System.out.println("Enter the Arrival Time in the format hour:minute (HH:mm)");
                            newArrivalTime = input.nextLine();
                            arrTime = LocalTime.parse(newArrivalTime, DateTimeFormatter.ofPattern(TIME_FORMAT));
                            if (arrTime.isBefore(depTime)) {
                                System.out.println("Arrival time must be after departure time.");
                                System.out.println("please re-enter both Departure and Arrival Time");
                            } else {
                                editedFlight.setDepartureTime(newDepartureTime);
                                editedFlight.setArrivalTime(newArrivalTime);
                                break;
                            }
                        } catch (DateTimeParseException ex) {
                            System.out.println("Invalid time format. Please enter a valid time.");
                            System.out.println("please re-enter both Departure and Arrival Time");

                        }
                    } while (true);
                // end of case 3

            } // end of switch

            System.out.println("Would you like to edit anything else? ");
            System.out.println("press 1 for yes // 2 for no");
            int choice6 = input.nextInt();
            if (choice6 == 2) {
                flag = false;
            } else if (choice6 == 1) {
                flag = true;
            }
        } while (flag); // end of do while loop

        Flight.saveFlightsInTextFile();

    }// end of editFlight body

    @Override
    public String toString() {

        return super.toString()
                + "\njob: " + job
                + "\nEmployee ID: " + employeeID;
    }// end of toString method

} // end of class body
