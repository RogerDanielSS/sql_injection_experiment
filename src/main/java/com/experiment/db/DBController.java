package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBController {

    private static final String DB_URL = "jdbc:mariadb://localhost:3306/db";
    private static final String DB_USER = "username";
    private static final String DB_PASSWORD = "password";

    public void login() {
        Connection connection = null;
        try {
            // Connect to the database
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Example: Select data from a table
            String selectQuery = "SELECT * FROM Users";
            PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
            ResultSet resultSet = selectStmt.executeQuery();
            while (resultSet.next()) {
                // Process each row
                String column1Value = resultSet.getString("username");
                // Get other columns similarly
                System.out.println(column1Value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close connection in finally block
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}