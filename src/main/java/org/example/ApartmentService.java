package org.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class ApartmentService {

    public void addApartment(Apartment apartment) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            String query = "INSERT INTO apartments (address, price, size, status) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, apartment.getAddress());
                preparedStatement.setDouble(2, apartment.getPrice());
                preparedStatement.setInt(3, apartment.getSize());
                preparedStatement.setString(4, apartment.getStatus());
                preparedStatement.executeUpdate();
                System.out.println("Apartment added successfully!");
            } catch (SQLException e) {
                System.out.println("Failed to add apartment: " + e.getMessage());
            }
        }
    }

    public void viewAllApartments() {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            String query = "SELECT * FROM apartments";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    displayApartment(resultSet);
                }
            } catch (SQLException e) {
                System.out.println("Failed to retrieve apartments: " + e.getMessage());
            }
        }
    }

    public void viewAvailableApartments() {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            String query = "SELECT * FROM apartments WHERE status = 'Available'";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    displayApartment(resultSet);
                }
            } catch (SQLException e) {
                System.out.println("Failed to retrieve available apartments: " + e.getMessage());
            }
        }
    }

    public void viewApartmentDetails(int id) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            String query = "SELECT * FROM apartments WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    displayApartment(resultSet);
                } else {
                    System.out.println("No apartment found with the given ID.");
                }
            } catch (SQLException e) {
                System.out.println("Failed to retrieve apartment details: " + e.getMessage());
            }
        }
    }

    public void updateApartment(int id, String address, double price, int size, String status) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            String query = "UPDATE apartments SET address = ?, price = ?, size = ?, status = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, address);
                preparedStatement.setDouble(2, price);
                preparedStatement.setInt(3, size);
                preparedStatement.setString(4, status);
                preparedStatement.setInt(5, id);
                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Apartment updated successfully!");
                } else {
                    System.out.println("No apartment found with the given ID.");
                }
            } catch (SQLException e) {
                System.out.println("Failed to update apartment: " + e.getMessage());
            }
        }
    }

    public void deleteApartment(int id) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            String query = "DELETE FROM apartments WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                int rowsDeleted = preparedStatement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Apartment deleted successfully!");
                } else {
                    System.out.println("No apartment found with the given ID.");
                }
            } catch (SQLException e) {
                System.out.println("Failed to delete apartment: " + e.getMessage());
            }
        }
    }

    public void viewFeedback(int apartmentId) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            String query = "SELECT * FROM apartment_feedback WHERE apartment_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, apartmentId);
                ResultSet resultSet = preparedStatement.executeQuery();

                boolean hasFeedback = false;
                while (resultSet.next()) {
                    hasFeedback = true;
                    System.out.println("Feedback ID: " + resultSet.getInt("id"));
                    System.out.println("Feedback: " + resultSet.getString("feedback"));
                    System.out.println("-----------------------------------");
                }

                if (!hasFeedback) {
                    System.out.println("No feedback found for this apartment.");
                }
            } catch (SQLException e) {
                System.out.println("Failed to retrieve feedback: " + e.getMessage());
            }
        }
    }



    public void buyApartment(int id) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            String query = "UPDATE apartments SET status = 'Sold' WHERE id = ? AND status = 'Available'";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Congratulations! You have bought the apartment.");
                } else {
                    System.out.println("The apartment is either sold or not available.");
                }
            } catch (SQLException e) {
                System.out.println("Failed to buy apartment: " + e.getMessage());
            }
        }
    }

    public void rentApartment(int id) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            String query = "UPDATE apartments SET status = 'Rented' WHERE id = ? AND status = 'Available'";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Congratulations! You have rented the apartment.");
                } else {
                    System.out.println("The apartment is either rented or not available.");
                }
            } catch (SQLException e) {
                System.out.println("Failed to rent apartment: " + e.getMessage());
            }
        }
    }


    public void leaveFeedback(int apartmentId, int buyerId, String feedback) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            String query = "INSERT INTO apartment_feedback (apartment_id, buyer_id, feedback) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, apartmentId);
                preparedStatement.setInt(2, buyerId); //
                preparedStatement.setString(3, feedback);
                preparedStatement.executeUpdate();
                System.out.println("Feedback submitted successfully.");
            } catch (SQLException e) {
                System.out.println("Failed to submit feedback: " + e.getMessage());
            }
        }
    }



    // Helper method to display apartment information
    private void displayApartment(ResultSet resultSet) throws SQLException {
        System.out.println("ID: " + resultSet.getInt("id"));
        System.out.println("Address: " + resultSet.getString("address"));
        System.out.println("Price: " + resultSet.getDouble("price"));
        System.out.println("Size: " + resultSet.getInt("size") + " sqm");
        System.out.println("Status: " + resultSet.getString("status"));
        System.out.println("-----------------------------------");
    }
}
