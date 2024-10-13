package org.example;

public class User {
    private String name;
    private String role; // Buyer or Vendor

    public User(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User: " + name + ", Role: " + role;
    }
}
