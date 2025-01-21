package com.tkt;

import java.util.*;

class Train {
    int trainNumber;
    String trainName;
    String source;
    String destination;
    int availableSeats;
    double fare;

    Train(int trainNumber, String trainName, String source, String destination, int availableSeats, double fare) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.fare = fare;
    }

    void displayDetails() {
        System.out.printf("Train No: %d | Name: %s | From: %s | To: %s | Seats: %d | Fare: %.2f\n",
                trainNumber, trainName, source, destination, availableSeats, fare);
    }
}

class Booking {
    int bookingId;
    String passengerName;
    int trainNumber;
    int seatsBooked;
    double totalFare;

    Booking(int bookingId, String passengerName, int trainNumber, int seatsBooked, double totalFare) {
        this.bookingId = bookingId;
        this.passengerName = passengerName;
        this.trainNumber = trainNumber;
        this.seatsBooked = seatsBooked;
        this.totalFare = totalFare;
    }

    void displayDetails() {
        System.out.printf("Booking ID: %d | Passenger: %s | Train No: %d | Seats: %d | Total Fare: %.2f\n",
                bookingId, passengerName, trainNumber, seatsBooked, totalFare);
    }
}

public class TrainTicketBookingSystem {
    private static Map<Integer, Train> trains = new HashMap<>();
    private static List<Booking> bookings = new ArrayList<>();
    private static int bookingCounter = 1;

    public static void main(String[] args) {
        initializeTrains();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Train Ticket Booking System ---");
            System.out.println("1. View Trains");
            System.out.println("2. Book Ticket");
            System.out.println("3. View Bookings");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. Print Ticket");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
//                case 1 -> viewTrains();
                case 2 -> bookTicket(scanner);
                case 3 -> viewBookings();
                case 4 -> cancelTicket(scanner);
                case 5 -> printTicket(scanner);
                case 6 -> {
                    System.out.println("Thank you for using the system. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeTrains() {
        trains.put(101, new Train(101, "Express A", "City X", "City Y", 50, 200.00));
        trains.put(102, new Train(102, "Express B", "City X", "City Z", 40, 250.00));
        trains.put(103, new Train(103, "Express C", "City Y", "City Z", 30, 150.00));
    }

    private static void viewTrains() {
        System.out.println("\nAvailable Trains:");
        for (Train train : trains.values()) {
            train.displayDetails();
        }
    }

    private static void bookTicket(Scanner scanner) {
        System.out.print("\nEnter Train Number: ");
        int trainNumber = scanner.nextInt();

        Train train = trains.get(trainNumber);
        if (train == null) {
            System.out.println("Invalid Train Number. Please try again.");
            return;
        }

        System.out.print("Enter Passenger Name: ");
        scanner.nextLine(); // Consume newline
        String passengerName = scanner.nextLine();

        System.out.print("Enter Number of Seats to Book: ");
        int seatsToBook = scanner.nextInt();

        if (seatsToBook <= 0 || seatsToBook > train.availableSeats) {
            System.out.println("Invalid number of seats. Please try again.");
            return;
        }

        train.availableSeats -= seatsToBook;
        double totalFare = seatsToBook * train.fare;

        Booking booking = new Booking(bookingCounter++, passengerName, train.trainNumber, seatsToBook, totalFare);
        bookings.add(booking);

        System.out.println("Ticket Booked Successfully!");
        booking.displayDetails();
    }

    private static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("\nNo bookings found.");
        } else {
            System.out.println("\nBooking Details:");
            for (Booking booking : bookings) {
                booking.displayDetails();
            }
        }
    }

    private static void cancelTicket(Scanner scanner) {
        System.out.print("\nEnter Booking ID to Cancel: ");
        int bookingId = scanner.nextInt();

        Booking bookingToCancel = null;
        for (Booking booking : bookings) {
            if (booking.bookingId == bookingId) {
                bookingToCancel = booking;
                break;
            }
        }

        if (bookingToCancel == null) {
            System.out.println("Invalid Booking ID. Please try again.");
            return;
        }

        Train train = trains.get(bookingToCancel.trainNumber);
        if (train != null) {
            train.availableSeats += bookingToCancel.seatsBooked;
        }

        bookings.remove(bookingToCancel);
        System.out.println("Booking Cancelled Successfully!");
    }

    private static void printTicket(Scanner scanner) {
        System.out.print("\nEnter Booking ID to Print: ");
        int bookingId = scanner.nextInt();

        Booking bookingToPrint = null;
        for (Booking booking : bookings) {
            if (booking.bookingId == bookingId) {
                bookingToPrint = booking;
                break;
            }
        }

        if (bookingToPrint == null) {
            System.out.println("Invalid Booking ID. Please try again.");
            return;
        }

        System.out.println("\n--- Ticket Details ---");
        bookingToPrint.displayDetails();
        System.out.println("-------");
    }
}