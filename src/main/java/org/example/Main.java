package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ApartmentService apartmentService = new ApartmentService();

        System.out.println("Welcome to the Real Estate Management System!");

        // User setup
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Are you a Buyer or Vendor? ");
        String role = scanner.nextLine();
        User user = new User(name, role);

        int choice = -1;
        while (choice != 0) {
            // Display menu based on user role
            if ("Vendor".equalsIgnoreCase(user.getRole())) {
                System.out.println("\n--- Vendor Menu ---");
                System.out.println("1. Add New Apartment");
                System.out.println("2. Update Apartment");
                System.out.println("3. Delete Apartment");
                System.out.println("4. View My Apartments");
                System.out.println("0. Exit");

            } else if ("Buyer".equalsIgnoreCase(user.getRole())) {
                System.out.println("\n--- Buyer Menu ---");
                System.out.println("1. View Available Apartments");
                System.out.println("0. Exit");

            } else {
                System.out.println("Invalid role! Exiting...");
                break;
            }

            // Get user's choice
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    if ("Vendor".equalsIgnoreCase(user.getRole())) {
                        // Vendor adds an apartment
                        System.out.print("Enter Apartment ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter Address: ");
                        String address = scanner.nextLine();
                        System.out.print("Enter Price: ");
                        double price = scanner.nextDouble();
                        System.out.print("Enter Size (m²): ");
                        int size = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter Status (Available/Sold): ");
                        String status = scanner.nextLine();

                        Apartment apartment = new Apartment(id, address, price, size, status);
                        apartmentService.addApartment(apartment);

                    } else if ("Buyer".equalsIgnoreCase(user.getRole())) {
                        // Buyer views available apartments
                        System.out.println("\n--- Available Apartments ---");
                        for (Apartment apartment : apartmentService.viewAvailableApartments()) {
                            System.out.println(apartment);
                        }
                    }
                    break;

                case 2:
                    if ("Vendor".equalsIgnoreCase(user.getRole())) {
                        // Vendor updates an apartment
                        System.out.print("Enter Apartment ID to update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter New Address: ");
                        String newAddress = scanner.nextLine();
                        System.out.print("Enter New Price: ");
                        double newPrice = scanner.nextDouble();
                        System.out.print("Enter New Size (m²): ");
                        int newSize = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter New Status (Available/Sold): ");
                        String newStatus = scanner.nextLine();

                        apartmentService.updateApartment(updateId, newAddress, newPrice, newSize, newStatus);
                    }
                    break;

                case 3:
                    if ("Vendor".equalsIgnoreCase(user.getRole())) {
                        // Vendor deletes an apartment
                        System.out.print("Enter Apartment ID to delete: ");
                        int deleteId = scanner.nextInt();
                        apartmentService.deleteApartment(deleteId);
                    }
                    break;

                case 4:
                    if ("Vendor".equalsIgnoreCase(user.getRole())) {
                        // Vendor views all apartments
                        System.out.println("\n--- My Apartments ---");
                        for (Apartment apartment : apartmentService.viewAllApartments()) {
                            System.out.println(apartment);
                        }
                    }
                    break;

                case 0:
                    System.out.println("Exiting the system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }
}
