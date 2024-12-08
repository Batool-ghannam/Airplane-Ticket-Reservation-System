package edu.uqu.cs;

import java.util.*;
import java.io.*;

/**
 * The purpose of this class is display all flight data "flight number
 * ,departure time, arrival city and flight date". Reserve a seat for the
 * passenger on the plane save flights and display it to the user add or delete
 * a passenger.
 */
public class Flight {

    // implementing encapsulation
    /**
     * FlightNumber to store the flight number for each flight
     */
    private final int FlightNumber;
    /**
     * ArrivalCity to display the arrival city
     */
    private String ArrivalCity;
    /**
     * flightDate to display the date of the flight
     */
    private String flightDate;
    /**
     * DepartureTime to display the departure time of the aircraft
     */
    private String DepartureTime;
    /**
     * ArrivalTime to display the arrival time to the city
     */
    private String ArrivalTime;
    /**
     * Array of passenger to create a passenger on the plane
     */
    private ArrayList<Passenger> passengers = new ArrayList<Passenger>(40);
    /**
     * Array of seats to create the seats for the passengers on the plane
     */
    private String[][] seats = new String[10][4];
    /**
     * defultPrice to give the defult price for each flight
     */
    private int defultPrice;
    /**
     * DepartureCity to display the departure city
     */
    public static final String DepartureCity = "MAK";
    /**
     * ArrayList of type Flight to store the trips
     */
    public static ArrayList<Flight> flights = new ArrayList<Flight>();
    /**
     * fileName A file containing flight information
     */
    public static final String fileName = "/workspaces/cs1312-course-project-airplane_arwa_fadaaq_07/src/main/java/edu/uqu/cs/Flights.txt";
    /**
     * giveFlightNumber to give each flight a number
     */
    public static int giveFlightNumber = 100;

    /**
     * Flight default constructor
     */
    public Flight() {
        this.FlightNumber = ++giveFlightNumber;
        this.ArrivalCity = " No arrival city yet";
        this.flightDate = "No flight Date yet";
        this.DepartureTime = "No departure time";
        this.ArrivalTime = "No arrival time";
        this.defultPrice = 0;
        for (int i = 0; i < seats.length; i++) {
            seats[i][0] = "A";
            seats[i][1] = "B";
            seats[i][2] = "C";
            seats[i][3] = "D";

        }
    }

    /**
     * Flight constructor used to give the value of the ArrivalCity, flightDate,
     * DepartureTime and ArrivalTime, and to automatically assign the flight
     * number and default price.
     *
     * @param ArrivalCity Arrival city for the flight
     * @param flightDate Flight departure date
     * @param DepartureTime Flight departure time
     * @param ArrivalTime Flight arrival time
     */
    public Flight(String ArrivalCity, String flightDate, String DepartureTime, String ArrivalTime) {

        this.FlightNumber = ++giveFlightNumber;
        this.ArrivalCity = ArrivalCity;
        this.flightDate = flightDate;
        this.DepartureTime = DepartureTime;
        this.ArrivalTime = ArrivalTime;
        flights.add(this);
        this.setDefultPrice();
        for (int i = 0; i < seats.length; i++) {
            seats[i][0] = "A";
            seats[i][1] = "B";
            seats[i][2] = "C";
            seats[i][3] = "D";

        }
    }

    /**
     * Set the default price based on the city of arrival
     */
    public void setDefultPrice() {

        if (ArrivalCity.equalsIgnoreCase("AHB")) {
            this.defultPrice = 200;
        } else if (ArrivalCity.equalsIgnoreCase("RUH")) {
            this.defultPrice = 400;
        } else if (ArrivalCity.equalsIgnoreCase("TUU")) {
            this.defultPrice = 600;
        } else if (ArrivalCity.equalsIgnoreCase("DMM")) {
            this.defultPrice = 500;
        }

    }

    /**
     * gets the DefultPrice
     *
     * @return the DefultPrice
     */
    public int getDefultPrice() {
        return defultPrice;
    }

    /**
     * gets the FlightNumber
     *
     * @return the FlightNumber
     */
    public int getFlightNumber() {
        return FlightNumber;
    }

    /**
     * gets the ArrivalCity
     *
     * @return the ArrivalCity
     */
    public String getArrivalCity() {
        return ArrivalCity;
    }

    /**
     * sets the ArrivalCity
     *
     * @param ArrivalCity Arrival city for the flight
     */
    public void setArrivalCity(String ArrivalCity) {
        if (ArrivalCity.equalsIgnoreCase("AHB")) {
            this.ArrivalCity = "AHB";
        } else if (ArrivalCity.equalsIgnoreCase("RUH")) {
            this.ArrivalCity = "RUH";
        } else if (ArrivalCity.equalsIgnoreCase("TUU")) {
            this.ArrivalCity = "TUU";
        } else if (ArrivalCity.equalsIgnoreCase("DMM")) {
            this.ArrivalCity = "DMM";
        }
    }

    /**
     * gets the Seats
     *
     * @return the Seats
     */
    public String[][] getSeats() {
        return seats;
    }

    /**
     * gets the Array list of passenger
     *
     * @return the passengers
     */
    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    /**
     * gets the FlightDate
     *
     * @return FlightDate
     */
    public String getFlightDate() {
        return flightDate;
    }

    /**
     * sets the FlightDate
     *
     * @param flightDate Flight departure date
     */
    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    /**
     * gets the DepartureTime
     *
     * @return DepartureTime
     */
    public String getDepartureTime() {
        return DepartureTime;
    }

    /**
     * sets the DepartureTime
     *
     * @param DepartureTime Flight departure time
     */
    public void setDepartureTime(String DepartureTime) {
        this.DepartureTime = DepartureTime;
    }

    /**
     * gets the ArrivalTime
     *
     * @return ArrivalTime
     */
    public String getArrivalTime() {
        return ArrivalTime;
    }

    /**
     * sets the ArrivalTime
     *
     * @param ArrivalTime Flight arrival time
     */
    public void setArrivalTime(String ArrivalTime) {
        this.ArrivalTime = ArrivalTime;
    }

    /**
     * saveFlightsInTextFile to save the ArrayList of flight in file "
     * Flights.txt"
     */
    public static void saveFlightsInTextFile() {

        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the file " + fileName);
            System.exit(0);
        } // end of catch block
        outputStream.println();
        outputStream.println(
                "-------------------------------------------------------FLIGHT INFORMATION-------------------------------------------------------------");
        for (int i = 0; i < flights.size(); i++) {
            outputStream.println(flights.get(i));
        } // end of for loop
        outputStream.println(
                "--------------------------------------------------------------------------------------------------------------------------------------");
        outputStream.close();

    } // end of saveFlightsInTextFile body

    /**
     * displayAllFlights to display all flights information
     */
    public static void displayAllFlights() {

        Scanner inputStream = null;
        try {
            inputStream = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the file " + fileName);
            System.exit(0);
        } // end of catch block
        while (inputStream.hasNextLine()) {
            String line = inputStream.nextLine();
            System.out.println(line);
        } // end of while loop
        inputStream.close();

    } // end of displayAllFlights body

    /**
     * addPassenger to add a new passenger to the flight
     *
     * @param pass The passenger we want to add to the flight
     * @return true if the passenger was added successfully, otherwise it is
     * false
     */
    public boolean addPassenger(Passenger pass) {
        boolean check = true;
        if (passengers.size() < 40) {
            for (Passenger p : passengers) {
                if (pass.getPassportNumber().equalsIgnoreCase(p.getPassportNumber())) {
                    System.out.println("Error: This passport number already exists");
                    check = false;
                    break;
                } // end of if
            } // end of for loop
        } else {
            System.out.println("Sorry, this flight is  fully booked.");
            check = false;
        } // end of if else
        if (check) {
            passengers.add(pass);
            System.out.println("The flight has been booked successfully");
        } // end of if
        return check;
    } // end of addPassenger body

    /**
     * removePassenger to remove a passenger from the flight
     *
     * @param passenger The passenger we want to remove from the flight
     * @return true if the passenger was removed successfully, otherwise it is
     * false
     */
    public boolean removePassenger(Passenger passenger) {
        for (int i = 0; i < passengers.size(); i++) {
            if (passenger.getPassportNumber().equals(passengers.get(i).getPassportNumber())) {
                passengers.remove(passengers.get(i));
                return true;
            } // end of if
        } // end of for loop
        System.out.println("Passenger with passport number: " + passenger.getPassportNumber() + " does not exist");
        return false;

    } // end of removePassenger body

    /**
     * containsFlightNumber to Check if the flights list contains a flight with
     * the same flight number.
     *
     * @param flightNumber the flight number we want to search for
     * @return true if the flight exists otherwise false
     */
    public static boolean containsFlightNumber(int flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber() == flightNumber) {
                return true;
            } // end of if
        } // end of for loop
        System.out.println("There is no flight with this number " + flightNumber + " Please enter a valid number");
        return false;
    } // end of containsFlightNumber body

    /**
     * return a formatted string containing the flight information
     *
     * @return a formatted string containing the flight information
     */
    @Override
    public String toString() {
        return "| Flight No.: " + FlightNumber + " | From: " + DepartureCity + " | To:" + ArrivalCity
                + " | Flight date: " + flightDate + " | Departure time: " + DepartureTime
                + " | Arrival time: " + ArrivalTime + " | Price starts from " + defultPrice + " |";
    } // end of toString body

} // end of Flight body
