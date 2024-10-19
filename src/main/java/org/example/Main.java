package org.example;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ApartmentService apartmentService = new ApartmentService();

    public static void main(String[] args) {
        System.out.println("Welcome to the Real Estate Management System!");

        while (true) {
            System.out.println("Are you a vendor or a buyer? (type 'vendor' or 'buyer')");
            String userType = scanner.nextLine().toLowerCase();

            if (userType.equals("vendor")) {
                vendorMenu();
            } else if (userType.equals("buyer")) {
                buyerMenu();
            } else {
                System.out.println("Invalid user type! Please enter either 'vendor' or 'buyer'.");
            }
        }
    }

    private static void vendorMenu() {
        boolean exit = false;

        while (!exit) {

            System.out.println("\nVendor Menu:");
            System.out.println("1. Add New Apartment");
            System.out.println("2. View My Apartments");
            System.out.println("3. Update Apartment");
            System.out.println("4. Delete Apartment");
            System.out.println("5. View Buyer Feedback");
            System.out.println("6. Exit");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addApartment();
                    break;
                case 2:
                    viewApartments();
                    break;
                case 3:
                    updateApartment();
                    break;
                case 4:
                    deleteApartment();
                    break;
                case 5:
                    viewFeedback();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


    private static void buyerMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nBuyer Menu:");
            System.out.println("1. View All Listed Apartments");
            System.out.println("2. Check Apartment Details");
            System.out.println("3. Buy Property");
            System.out.println("4. Rent Property");
            System.out.println("5. Leave Feedback");
            System.out.println("6. Exit");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    viewAllListedApartments();
                    break;
                case 2:
                    checkApartmentDetails();
                    break;
                case 3:
                    buyProperty();
                    break;
                case 4:
                    rentProperty();
                    break;
                case 5:
                    leaveFeedback();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addApartment() {
        System.out.println("Enter the apartment address:");
        String address = scanner.nextLine();

        System.out.println("Enter the apartment price:");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.println("Enter the apartment size (in square meters):");
        int size = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter the apartment status (Available/Sold/Rented):");
        String status = scanner.nextLine();

        Apartment apartment = new Apartment(address, price, size, status);
        apartmentService.addApartment(apartment);
    }

    private static void viewApartments() {
        apartmentService.viewAllApartments();
    }

    private static void viewAllListedApartments() {
        apartmentService.viewAvailableApartments();
    }

    private static void viewFeedback() {
        System.out.println("Enter the ID of the apartment you want to see feedback for:");
        int id = Integer.parseInt(scanner.nextLine());

        apartmentService.viewFeedback(id);
    }

    private static void checkApartmentDetails() {
        System.out.print("Enter the ID of the apartment you want to check: ");
        int id = Integer.parseInt(scanner.nextLine());
        apartmentService.viewApartmentDetails(id);
    }

    private static void buyProperty() {
        System.out.print("Enter the ID of the apartment you want to buy: ");
        int id = Integer.parseInt(scanner.nextLine());
        apartmentService.buyApartment(id);
    }

    private static void rentProperty() {
        System.out.print("Enter the ID of the apartment you want to rent: ");
        int id = Integer.parseInt(scanner.nextLine());
        apartmentService.rentApartment(id);
    }

    private static void leaveFeedback() {
        System.out.print("Enter your buyer ID: ");
        int buyerId = Integer.parseInt(scanner.nextLine()); // Get the buyer ID

        System.out.print("Enter the ID of the apartment you want to leave feedback for: ");
        int apartmentId = Integer.parseInt(scanner.nextLine()); // Get the apartment ID

        System.out.print("Enter your feedback: ");
        String feedback = scanner.nextLine(); // Get the feedback text

        apartmentService.leaveFeedback(buyerId, apartmentId, feedback);
    }

    private static void updateApartment() {
        System.out.println("Enter the ID of the apartment you want to update:");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter the new address:");
        String address = scanner.nextLine();

        System.out.println("Enter the new price:");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.println("Enter the new size (in square meters):");
        int size = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter the new status (Available/Sold/Rented):");
        String status = scanner.nextLine();

        apartmentService.updateApartment(id, address, price, size, status);
    }

    private static void deleteApartment() {
        System.out.println("Enter the ID of the apartment you want to delete:");
        int id = Integer.parseInt(scanner.nextLine());

        apartmentService.deleteApartment(id);
    }
}

