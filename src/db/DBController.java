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

    public void sample() {
        Connection connection = null;
        try {
            // Connect to the database
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Example: Select data from a table
            String selectQuery = "SELECT * FROM your_table_name";
            PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
            ResultSet resultSet = selectStmt.executeQuery();
            while (resultSet.next()) {
                // Process each row
                String column1Value = resultSet.getString("column1");
                // Get other columns similarly
                System.out.println(column1Value);
            }

            // Example: Insert data into a table
            String insertQuery = "INSERT INTO your_table_name (column1, column2) VALUES (?, ?)";
            PreparedStatement insertStmt = connection.prepareStatement(insertQuery);
            insertStmt.setString(1, "value1");
            insertStmt.setString(2, "value2");
            insertStmt.executeUpdate();

            // Other operations (update, delete) can be performed similarly

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