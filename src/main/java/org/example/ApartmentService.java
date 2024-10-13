package org.example;

import java.util.ArrayList;
import java.util.List;

public class ApartmentService {
    private List<Apartment> apartments = new ArrayList<>();

    // Create - Add a new apartment
    public void addApartment(Apartment apartment) {
        apartments.add(apartment);
        System.out.println("Apartment added successfully!");
    }

    // Read - View all apartments
    public List<Apartment> viewAllApartments() {
        return apartments;
    }

    // Read - View available apartments
    public List<Apartment> viewAvailableApartments() {
        List<Apartment> availableApartments = new ArrayList<>();
        for (Apartment apartment : apartments) {
            if ("Available".equalsIgnoreCase(apartment.getStatus())) {
                availableApartments.add(apartment);
            }
        }
        return availableApartments;
    }

    // Update - Update apartment details
    public boolean updateApartment(int id, String address, double price, int size, String status) {
        for (Apartment apartment : apartments) {
            if (apartment.getId() == id) {
                apartment.setAddress(address);
                apartment.setPrice(price);
                apartment.setSize(size);
                apartment.setStatus(status);
                System.out.println("Apartment updated successfully!");
                return true;
            }
        }
        System.out.println("Apartment not found.");
        return false;
    }

    // Delete - Delete an apartment
    public boolean deleteApartment(int id) {
        for (Apartment apartment : apartments) {
            if (apartment.getId() == id) {
                apartments.remove(apartment);
                System.out.println("Apartment deleted successfully!");
                return true;
            }
        }
        System.out.println("Apartment not found.");
        return false;
    }
}
