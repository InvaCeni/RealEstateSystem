package org.example.service;
import java.util.HashMap;
import java.util.Map;

    public class UserService {
        private Map<String, String> users = new HashMap<>(); // Store username and password pairs

        public void registerUser(String username, String password) {
            if (users.containsKey(username)) {
                System.out.println("Username already taken!");
            } else {
                users.put(username, password);
                System.out.println("User registered successfully!");
            }
        }

        public boolean loginUser(String username, String password) {
            if (users.containsKey(username) && users.get(username).equals(password)) {
                System.out.println("Login successful!");
                return true;
            }
            System.out.println("Invalid username or password.");
            return false;
        }
}
