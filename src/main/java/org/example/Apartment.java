package org.example;

public class Apartment {
    private int id;
    private String address;
    private double price;
    private int size;
    private String status; // Available or Sold

    public Apartment(int id, String address, double price, int size, String status) {
        this.id = id;
        this.address = address;
        this.price = price;
        this.size = size;
        this.status = status;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Apartment ID: " + id + ", Address: " + address + ", Price: " + price +
                ", Size: " + size + "m², Status: " + status;
    }
}