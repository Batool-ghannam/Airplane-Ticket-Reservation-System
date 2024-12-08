package edu.uqu.cs;

import java.util.*;
import java.time.LocalDate;

import java.util.Scanner;

/**
 * This class is a child class from the User class the purpose of this class is
 * Taking passenger data and verifying its validity
 */
public class Passenger extends User {

    /**
     * passportNumber store the Passenger's passport Number
     */
    private String passportNumber;
    /**
     * expirationDate store the Passenger's passport expiry number
     */
    private String expirationDate;
    /**
     * ageGroup store the Passenger's age Group
     */
    private String ageGroup;

    /**
     * Passenger default constructor
     */
    public Passenger() {
        super();
        passportNumber = "";
        expirationDate = "";
        ageGroup = "";
    }

    /**
     * Passenger constructor used to give the value of the Passenger's first
     * name, last name and phone number , String ageGroup, String
     * passportNumber, String expirationDate
     *
     * @param first the Passenger's first name
     * @param last the Passenger's last name
     * @param ageGroup the Passenger's age Group
     * @param passportNumber the Passenger's passport number
     * @param expirationDate the Passenger's passport expiration date
     */
    public Passenger(String first, String last, String ageGroup, String passportNumber, String expirationDate) {
        super(first, last);
        setAgeGroup(ageGroup);
        setPassportNumber(passportNumber);

        setExpirationDate(expirationDate);

    }

    /**
     * get passenger's Passport Number
     *
     * @return passportNumber
     */
    public String getPassportNumber() {
        return passportNumber;
    }

    /**
     * set passenger's Passport Number and validates it.
     *
     * @param passportNumber the Passenger's passport number
     */
    public void setPassportNumber(String passportNumber) {

        Scanner scn = new Scanner(System.in);

        if (isValidPassportNumber(passportNumber) == true) {
            this.passportNumber = passportNumber;

            // Allow other attempts if the entered number is not valid
        } else {
            while (isValidPassportNumber(passportNumber) == false) {
                System.out.println("invalid passport Number! try again");
                passportNumber = scn.nextLine();
                isValidPassportNumber(passportNumber);
            } // end of while loop
            this.passportNumber = passportNumber;
        } // end of if else
    } // end of setPassportNumber body

    /**
     * get passenger's passport expiration date
     *
     * @return expirationDate
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * set passenger's passport expiration date and validates then stores it.
     *
     * @param expirationDate the Passenger's passport expiration date
     */
    public void setExpirationDate(String expirationDate) {

        Scanner scn = new Scanner(System.in);
        boolean validDate = false;
        boolean validPassport = false;
        // Ensure that the expiry date entered is written in the required format and is
        // not expired
        while (!validDate || !validPassport) {
            if (isDateValid(expirationDate)) {
                validDate = true;
                if (checkPassportExpiration(expirationDate)) {
                    validPassport = true;
                    this.expirationDate = expirationDate;
                } else {
                    System.out.println("Passport has expired.Please enter a valid expiration date:");
                    expirationDate = scn.nextLine();
                }
            } else {
                System.out.println("Invalid date format.Please enter a valid expiration date:");
                expirationDate = scn.nextLine();
            } // end of if else
        } // end of while loop
    } // end of setExpirationDate body

    /**
     * get passenger's age group
     *
     * @return ageGroup
     */
    public String getAgeGroup() {
        return ageGroup;
    }

    /**
     * set passenger's age group
     *
     * @param ageGroup passenger's age group
     */
    public void setAgeGroup(String ageGroup) {

        if (ageGroup.equalsIgnoreCase("infant") || ageGroup.equalsIgnoreCase("child")
                || ageGroup.equalsIgnoreCase("adult")) {
            this.ageGroup = ageGroup;
        } else {
            this.ageGroup = selectAgeGroup();
        }
    }

    /**
     * This method verifies that the entered date is written in the required
     * format
     *
     * @param expirationDate the Passenger's passport expiration date
     * @return true if the date is valid , otherwise false
     */
    public boolean isDateValid(String expirationDate) {
        try {
            LocalDate.parse(expirationDate);
            return true;
            // If the user enters a date in an inappropriate format, an exception will be
            // thrown
        } catch (Exception e) {
            return false;
        } // end of catch block
    } // end of isDateValid body

    /**
     * This method makes sure that the date entered is not expired
     *
     * @param expirationDate the Passenger's passport expiration date
     * @return true if the date is not expired,otherwise false
     */
    public boolean checkPassportExpiration(String expirationDate) {
        LocalDate currentDate = LocalDate.now();
        LocalDate expDate = LocalDate.parse(expirationDate);
        if (expDate.compareTo(currentDate) <= 0) {
            return false;
        } else {
            return true;
        } // end of if else statement
    } // end of checkPassportExpiration body

    /**
     * This method makes sure that the passport number is valid as it meets the
     * conditions(The passport number is not empty, its length is from 7 to 10,
     * it contains numbers and capital letters together)
     *
     * @param passportNumber the Passenger's passport number
     * @return true Passport Number is valid ,otherwise false
     */
    public boolean isValidPassportNumber(String passportNumber) {
        // make sure that the number is not empty.
        if (passportNumber == null || passportNumber.isEmpty()) {
            return false;
        }
        /*
         * make sure that passport number consists of a minimum of 7 characters
         * and a maximum of 10 characters
         */
        int length = passportNumber.length();
        if (length < 7 || length > 10) {
            return false;
        } // end of if statement

        // Make sure it contains both letters and numbers
        boolean hasLetter = false;
        boolean hasDigit = false;
        for (int i = 0; i < length; i++) {
            char c = passportNumber.charAt(i);
            if (Character.isLetter(c) && Character.isUpperCase(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else {
                return false; // contains invalid character
            } // end of if else statements
        } // end of for loop
        return hasLetter && hasDigit;

    } // end of isValidPassportNumber body

    /**
     * This method allows choosing the age group of the passenger, where he/she
     * has to choose the number of his age group (1,2 or 3), and if he/she
     * enters an incorrect entry, it is allowed to choose again.
     *
     * @return AgeGroup
     */
    public String selectAgeGroup() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the corresponding number for your age group.");
        System.out.println("1. Infant (less than two years)");
        System.out.println("2. Child (Between 2 - 11 years old)");
        System.out.println("3. Adult (12 years and over)");

        String ageGroup;
        while (true) {
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        ageGroup = "infant";
                        break;
                    case 2:
                        ageGroup = "child";
                        break;
                    case 3:
                        ageGroup = "adult";
                        break;
                    default:
                        System.out.println("Please enter a valid number (1 to 3):");
                        continue;
                } // end of switch cases
                break; // break out of while loop
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number (1 to 3):");
                scanner.next();
            } // end of catch block
        } // end of while loop
        return ageGroup;
    } // end of selectAgeGroup body

    @Override
    public String toString() {

        return super.toString()
                + "\npassportNumber: " + passportNumber
                + "\nexpirationDate: " + expirationDate
                + "\nageGroup: " + ageGroup;
    } // end of toString body
} // end of class body
