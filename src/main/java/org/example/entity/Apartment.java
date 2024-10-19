package org.example.entity;

public class Apartment {
    private String address;
    private double price;
    private int size;
    private String status;

    public Apartment(String address, double price, int size, String status) {
        this.address = address;
        this.price = price;
        this.size = size;
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public double getPrice() {
        return price;
    }

    public int getSize() {
        return size;
    }

    public String getStatus() {
        return status;
    }
}
