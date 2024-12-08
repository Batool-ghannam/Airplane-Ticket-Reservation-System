[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-f4981d0f882b2a3f0472912d15f9806d57e124e0fc890972558857b51b24a6f9.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=10537513)

<h1 align="center"> CS1312 - Computer Programming 2 - Course Project</h1>
<p align="center">A repository for Java Project</p>

# 🖥 Airplane Ticket Reservation System

# 🗂 Team Members
list all team members
* Arwa Fadaaq
* Sumiah Saed
* Albatool Ghannam
* Jana Ghazi
* Joud Alharthi
* Layan Almehmadi
   

# ⌨ Project Description

For this project, we will be making a Domestic Airplane Ticket Reservation System in Saudi Arabia.  This project will be done on GitHub using Java.  The system will allow customers to search for available departure flights from Makkah to Riyadh, Dammam, Tabuk and Abha, select their preferred travel dates, and know the economy and business class fare and other details.

Initially, our project consist of the following classes:

Flight.java: This class has properties: ArrayList from the passenger class, ArrayList for flight data ,array of objects for seats number, flight number, flight date ,departure time, arrival time, departure city, arrival city . It also has methods to add and remove passengers, save all flights,checks if a flight is on the list ,show all available flights for the passenger. 

User.java: This class has properties: first name, last name, and phone number. It also has methods that takes the user's name and phone number and verifies the validity of the entered data.

Passenger.java: This class is derived from User class. It has properties: passport number, date of passport expiration and age group . It also has  methods that will check the validity of the passenger’s information.

Employee.java: This class is derived from User class. It has properties: employee ID and job . It also has methods that will allow the employees to add, cancel , and edit flight information.

Ticket.java: This class has properties: ticketNumber, seatNumber, ticketType, pricePerPassenger, flight ,passenger, ticketCount and tickets. It also has methods for determine seat number , calculate price , cancel reservation.

Main.java: This class has the main method, which will be used in creating and instantiating objects along with calling methods.
# 🗺 How To Use

To use the airplane ticket reservation system, choose between reservation or for employees and enter a valid number.

For reservation, choose a flight, class, and enter personal information to generate tickets. After that, choose to make another reservation or cancel. For canceling a reservation, enter the correct ticket number.

For employees, enter a valid ID and choose to cancel, add, or edit flights:
If you choose to cancel a flight, choose from a list of available flights.
If you choose to add a flight, enter arrival city, flight date, departure and arrival times, and the program will create a new flight object and save it to a text file.
If you choose to edit a flight, select the flight and detail you want to edit, enter new information, and the program will save the updated flight information in a text file.

After completing any task, the program will ask if you want to do anything else. If not, the program will end your session.
