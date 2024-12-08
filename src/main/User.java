package edu.uqu.cs;

import java.util.*;

/**
 * The purpose of this class is to take the user's full name and phone number
 * and validate them.
 */
public class User {

    /**
     * firstName to store the user's first name
     */
    private String firstName;
    /**
     * lastName to store the user's last name
     */
    private String lastName;
    /**
     * phoneNumber to store the user's phone number
     */
    private String phoneNumber;

    /**
     * User default constructor
     */
    public User() {

        firstName = "No first name yet";
        lastName = "No last name yet";
        phoneNumber = "No phone number yet";

    }

    /**
     * User constructor used to give the value of the user's first name, last
     * name and phone number
     *
     * @param first the user's first name
     * @param last the user's last name
     */
    public User(String first, String last) {

        firstName = validateName(first);
        lastName = validateName(last);
        // Ensure that the number entered is correct
        phoneNumber = enterPhoneNumber();
    }

    /**
     * User constructor used to give the value of the user's first name, last
     * name and phone number
     *
     * @param first the user's first name
     * @param last the user's last name
     * @param userPhone the user's phone number
     */
    public User(String first, String last, String userPhone) {
        firstName = validateName(first);
        lastName = validateName(last);
        while (checkPhoneNumber(userPhone) == false) {
            userPhone = enterPhoneNumber();
        }
        phoneNumber = userPhone;
    }

    /**
     * Sets the user's first name
     *
     * @param first the user's first name
     */
    public void setFirstName(String first) {
        this.firstName = validateName(first);
    }

    /**
     * Sets the user's last name
     *
     * @param last the user's last name
     */
    public void setLastName(String last) {
        this.lastName = validateName(last);
    }

    /**
     * Gets the user's first name
     *
     * @return the user's first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Gets the user's last name
     *
     * @return the user's last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Gets the user's phone number
     *
     * @return the user's phone number
     */
    public String getPhoneNamber() {
        return phoneNumber;
    }

    /**
     * Sets the user's phone number
     *
     * @param phone the user's phone number
     */
    public void setPhoneNamber(String phone) {

        while (checkPhoneNumber(phone) == false) {
            phone = enterPhoneNumber();
        }
        phoneNumber = phone;
    }

    /**
     * This method checks whether the passed phone number is valid or not.
     *
     * @param phone the user's phone number
     * @return true if the phone number is valid, otherwise false
     */
    public boolean checkPhoneNumber(String phone) {

        boolean check = true;
        int count = 0;
        for (int i = 0; i < phone.length(); i++) {

            if (phone.charAt(1) == '0' || phone.charAt(1) != '5') {
                check = false;
                System.out.print("Hint : the phone number is supposed to start with 5 \n +966 ");
                break;
            } // end of if

            if (phone.charAt(i) >= '0' && phone.charAt(i) <= '9') {
                count++;
            } // end of if

        } // end of for loop
        if ((count != 10) && check) {
            check = false;
            System.out.print("Hint : The phone number must be 9 digits \n +966 ");
        } // end of if

        return check;
    } // end of checkPhoneNumber body

    /**
     * This method takes the phone number from the user and sends it to the
     * checkPhoneNumber(String phone) method to validate it and then return it
     *
     * @return the user's phone number as String
     */
    public String enterPhoneNumber() {

        Scanner input = new Scanner(System.in);
        System.out.print("Enter your phone number : \n +966 ");
        String copyPhone = "0";
        String phone = input.nextLine();
        copyPhone += phone;

        while (checkPhoneNumber(copyPhone) == false) {
            copyPhone = "0";
            phone = input.nextLine();
            copyPhone += phone;

        }
        return copyPhone;
    } // end of enterPhoneNumber body

    /**
     * This method checks if the username consists of letters only capitalizes
     * the first letter, and then returns username.
     *
     * @param name the username
     * @return the verified username
     */
    public static String validateName(String name) {
        Scanner input = new Scanner(System.in);

        // Check if the name is characters only
        while (!name.matches("[a-zA-Z]+")) {
            System.out.println("Invalid input (" + name + "), please enter a name consisting of letters only");
            name = input.next();
        } // end of while loop

        // Make the first character a capital letter
        char firstChar = Character.toUpperCase(name.charAt(0));
        String restOfName = name.substring(1);

        return firstChar + restOfName;
    } // end of validateName body

    @Override
    public String toString() {
        return "First name: " + firstName
                + "\nLast name: " + lastName
                + "\nPhone number: " + phoneNumber;
    } // end of toString body
} // end of User body
