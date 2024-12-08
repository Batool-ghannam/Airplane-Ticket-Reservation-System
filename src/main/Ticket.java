package edu.uqu.cs;

import java.util.*;

/**
 * The Ticket class represents a flight ticket and provides methods to get and
 * set the ticket information, calculate the price, determine the flight class,
 * determine seat number and cancel reservation.
 */
public class Ticket {

    /**
     * ticketNumber represents the unique identification number of the ticket.
     */
    private final int ticketNumber;

    /**
     * The seat number of the ticket.
     */
    private String seatNumber;

    /**
     * The type of ticket (economy or first-class, etc).
     */
    private String ticketType;

    /**
     * pricePerPassenger used to calculate the price per passenger for the
     * ticket.
     */
    private int pricePerPassenger;

    /**
     * The flight associated with the ticket.
     */
    private Flight flight;

    /**
     * The passenger associated with the ticket.
     */
    private Passenger passenger;

    /**
     * The number of tickets that have been created.
     */
    public static int ticketCount = 0;

    /**
     * A list of all tickets that have been created.
     */
    public static ArrayList<Ticket> tickets = new ArrayList<Ticket>();

    /**
     * Constructs a new Ticket object without any parameters.
     */
    public Ticket() {

        seatNumber = "No seat number yet";
        ticketType = "No ticket type yet";
        pricePerPassenger = 0;
        flight = new Flight();
        passenger = new Passenger();
        ticketNumber = ++ticketCount;
    }

    /**
     * Constructs a new Ticket object with ticket type ,flight and passenger as
     * parameters, and to automatically assign the ticket number, seat and price
     * to the passenger.
     *
     * @param type the ticket type as a String
     * @param flight the flight that was booked
     * @param passenger the passenger who booked the flight
     */
    public Ticket(String type, Flight flight, Passenger passenger) {
        this.ticketNumber = ++ticketCount;
        setTicketType(type);
        this.flight = flight;
        this.passenger = passenger;
        determineSeatNumber();
        calculatePricePerPassenger();
        tickets.add(this);
    }

    /**
     * Returns the ticket number of the ticket.
     *
     * @return the ticket number as an int
     */
    public int getTicketNumber() {
        return ticketNumber;
    }

    /**
     * Returns the seat number of the ticket.
     *
     * @return the seat number as an int
     */
    public String getSeatNumber() {
        return seatNumber;
    }

    /**
     * Returns the ticket type of the ticket.
     *
     * @return the ticket type as a String
     */
    public String getTicketType() {
        return ticketType;
    }

    /**
     * Sets the ticket type of the ticket.
     *
     * @param ticketType the ticket type as a String
     */
    public void setTicketType(String ticketType) {

        if (ticketType.equalsIgnoreCase("First class")) {
            this.ticketType = "First class";
        } else if (ticketType.equalsIgnoreCase("Economy class")) {
            this.ticketType = "Economy class";
        } else {
            System.out.println("There is no class on the plane with this name");
        } // end of else if statements
    } // end of setTicketType body

    /**
     * Returns the price of the ticket.
     *
     * @return the price of the ticket as a double
     */
    public int getPricePerPassenger() {
        return pricePerPassenger;
    }

    /**
     * Sets the price of the ticket.
     *
     * @param price the price of the ticket as a double
     */
    public void setPricePerPassenger(int price) {
        this.pricePerPassenger = price;
    }

    /**
     * Sets the flight booked on this ticket
     *
     * @param flight the flight that was booked
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /**
     * Gets the flight booked on this ticket
     *
     * @return the flight that was booked
     */
    public Flight getFlight() {
        return this.flight;
    }

    /**
     * Sets the passenger holding this ticket
     *
     * @param passenger the passenger holding this ticket
     */
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    /**
     * Gets the passenger holding this ticket
     *
     * @return the passenger holding this ticket
     */
    public Passenger getPassenger() {
        return this.passenger;
    }

    /**
     * To select the seat number based on the type of ticket. and check if there
     * are vacant seats and store the seat number e.g. 2A so that it consists of
     * a letter and a number.The number is the row number but the letter to
     * identify the column so that the first column carries the letter A, the
     * second column the letter B, the third column the letter C, and the fourth
     * column the letter D.
     *
     * @return true if the seat is reserved successfully, otherwise false.
     */
    public boolean determineSeatNumber() {

        Scanner input = new Scanner(System.in);

        boolean check = false;
        String[][] seats = flight.getSeats();
        if (!passenger.getAgeGroup().equalsIgnoreCase("Infant")) {
            while (!check) {
                if (ticketType.equalsIgnoreCase("First Class")) {
                    Loop:
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (seats[i][j].equalsIgnoreCase("A")) {
                                seats[i][j] = (i + 1) + seats[i][j];
                                this.seatNumber = seats[i][j];
                                check = true;
                                break Loop;
                            } else if (seats[i][j].equalsIgnoreCase("B")) {
                                seats[i][j] = (i + 1) + seats[i][j];
                                this.seatNumber = seats[i][j];
                                check = true;
                                break Loop;
                            } else if (seats[i][j].equalsIgnoreCase("C")) {
                                seats[i][j] = (i + 1) + seats[i][j];
                                this.seatNumber = seats[i][j];
                                check = true;
                                break Loop;
                            } else if (seats[i][j].equalsIgnoreCase("D")) {
                                seats[i][j] = (i + 1) + seats[i][j];
                                this.seatNumber = seats[i][j];
                                check = true;
                                break Loop;
                            } // end of else if
                        } // end of inner for loop
                    } // end of outer for loop
                    if (check == false) {
                        System.out.println("Sorry, there are no first class seats on this flight.");
                        System.out.println("Do you want to reserve Economy Class seats? (Yes/No)");
                        String select = input.next();
                        if (select.equalsIgnoreCase("Yes")) {
                            this.ticketType = "Economy Class";
                        } // end of if
                    } // end of if
                } else if (ticketType.equalsIgnoreCase("Economy Class")) {
                    Loop:
                    for (int i = 3; i < 10; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (seats[i][j].equalsIgnoreCase("A")) {
                                seats[i][j] = (i + 1) + seats[i][j];
                                this.seatNumber = seats[i][j];
                                check = true;
                                break Loop;
                            } else if (seats[i][j].equalsIgnoreCase("B")) {
                                seats[i][j] = (i + 1) + seats[i][j];
                                this.seatNumber = seats[i][j];
                                check = true;
                                break Loop;
                            } else if (seats[i][j].equalsIgnoreCase("C")) {
                                seats[i][j] = (i + 1) + seats[i][j];
                                this.seatNumber = seats[i][j];
                                check = true;
                                break Loop;
                            } else if (seats[i][j].equalsIgnoreCase("D")) {
                                seats[i][j] = (i + 1) + seats[i][j];
                                this.seatNumber = seats[i][j];
                                check = true;
                                break Loop;
                            } // end of inner else if
                        } // end of inner for loop
                    } // end of outer for loop
                } // end of outer else if
                if (check == false) {
                    System.out.println("Sorry, there are no Economy Class seats on this flight.");
                    System.out.println("Do you want to reserve First Class seats? (Yes/No)");
                    String select = input.next();
                    if (select.equalsIgnoreCase("Yes")) {
                        this.ticketType = "First Class";
                    } // end of if
                } // end of if
            } // end of while loop
        } else {
            System.out.println("Attention: No special seat will be allocated for infants");
        } // end of outer if else
        return check;
    } // end of determineSeatNumber body

    /**
     * Calculate the flight price based on the type of ticket, and there will be
     * no charge for infants.
     */
    public void calculatePricePerPassenger() {

        if (passenger.getAgeGroup().equalsIgnoreCase("Infant")) {
            this.pricePerPassenger = 0;
        } else {
            // If the ticket type is "First Class", the price per passenger will be the default price multiplied by 3.
            if (ticketType.equalsIgnoreCase("First class")) {
                this.pricePerPassenger = flight.getDefultPrice() * 3;
            } // If ticket type is "Economy class", the price per passenger will be the default price.
            else if (ticketType.equalsIgnoreCase("Economy class")) {
                this.pricePerPassenger = flight.getDefultPrice();
            } // end of inner if else        
        } // end of if else
    } // end of calculatePricePerPassenger body

    /**
     * This method cancels a reservation by the ticket number.
     *
     * @param ticketNumber The number of the ticket to be cancelled.
     *
     * @return Returns true if the reservation was successfully cancelled, false
     * otherwise.
     */
    public static boolean cancelReservation(int ticketNumber) {
        boolean isCancelled = false;
        for (Ticket ticket : tickets) {
            if (ticket.getTicketNumber() == ticketNumber) {
                ticket.flight.removePassenger(ticket.passenger);
                tickets.remove(ticket);
                isCancelled = true;
                break;
            } // end of if
        } // end of for loop
        if (isCancelled) {
            System.out.println("Your reservation has been successfully cancelled");
            return true;
        } else {
            System.out.println("Ticket number: " + ticketNumber + " does not exist");
            return false;
        } // end of if else
    } // end of cancelReservation body

    /**
     * Returns a formatted String with the ticket information.
     *
     * @return a formatted String with the ticket information
     */
    @Override
    public String toString() {
        if (passenger.getAgeGroup().equalsIgnoreCase("Infant")) {
            return "\n----------------------AIRPLANE TICKET------------------------\n"
                    + " NAME OF PASSENGER: " + passenger.getLastName() + "/" + passenger.getFirstName() + "   TICKET NUMBER:  " + ticketNumber + "\n"
                    + " FROM: " + Flight.DepartureCity + "     TO: " + flight.getArrivalCity() + "      AGE GROUP: " + passenger.getAgeGroup() + "\n"
                    + " FLIGHT NO.: " + flight.getFlightNumber() + "       DATE: " + flight.getFlightDate() + "\n"
                    + " CLASS: " + ticketType + "     PRICE: " + pricePerPassenger + " SAR\n"
                    + " DEPARTURE TIME: " + flight.getDepartureTime() + "     ARRIVAL TIME: " + flight.getArrivalTime() + "\n"
                    + "---------------------------------------------------------------";
        } // end of if
        return "\n----------------------AIRPLANE TICKET------------------------\n"
                + " NAME OF PASSENGER: " + passenger.getLastName() + "/" + passenger.getFirstName() + "   TICKET NUMBER:  " + ticketNumber + "\n"
                + " FROM: " + Flight.DepartureCity + "     TO: " + flight.getArrivalCity() + "      AGE GROUP: " + passenger.getAgeGroup() + "\n"
                + " FLIGHT NO.: " + flight.getFlightNumber() + "       DATE: " + flight.getFlightDate() + "\n"
                + " CLASS: " + ticketType + "      SEAT: " + getSeatNumber() + "    PRICE: " + pricePerPassenger + " SAR\n"
                + " DEPARTURE TIME: " + flight.getDepartureTime() + "     ARRIVAL TIME: " + flight.getArrivalTime() + "\n"
                + "---------------------------------------------------------------";
    } // end of toString body
} // end of Ticket body
