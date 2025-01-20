package com.tkt;

import java.util.Scanner;

class Ticket {
    private String passengerName;
    private int ticketId;

    public Ticket(String passengerName, int ticketId) {
        this.passengerName = passengerName;
        this.ticketId = ticketId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public int getTicketId() {
        return ticketId;
    }
}

class TrainTicketBooking {
    private static int availableTickets = 10;
    private static Ticket[] tickets = new Ticket[10];
    private static int ticketCount = 0;

    // Booking Ticket
    public static void bookTicket() {
        if (availableTickets > 0) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter passenger name: ");
            String name = sc.nextLine();
            Ticket ticket = new Ticket(name, ticketCount + 1);
            tickets[ticketCount] = ticket;
            ticketCount++;
            availableTickets--;
            System.out.println("Ticket booked successfully! Ticket ID: " + ticket.getTicketId());
        } else {
            System.out.println("Sorry, no tickets available.");
        }
    }

    // View booked tickets
    public static void viewTickets() {
        if (ticketCount == 0) {
            System.out.println("No tickets booked yet.");
            return;
        }
        System.out.println("\nBooked Tickets:");
        for (int i = 0; i < ticketCount; i++) {
            System.out.println("Ticket ID: " + tickets[i].getTicketId() + ", Passenger: " + tickets[i].getPassengerName());
        }
    }

    // Main menu for user interaction
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("\nTrain Ticket Booking System");
            System.out.println("1. Book Ticket");
            System.out.println("2. View Booked Tickets");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    bookTicket();
                    break;
                case 2:
                    viewTickets();
                    break;
                case 3:
                    System.out.println("Thank you for using the system!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 3);
        
        sc.close();
    }
}