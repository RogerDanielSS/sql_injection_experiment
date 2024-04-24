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

    /**
    * search user by name and password and return the username if it exists on database
    * if not, returns an informing message 
    *
    * @param username request data
    * @param password request data
    *
    * @return username or message informing that user was not found
    *  
    * @throws SQLException method throw this Exception if occurs any trouble with database
    */
    public String login(String username, String password) {
        Connection connection = null;
        try {
            // Connect to the database
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Example: Select data from a table
            String selectQuery = 
                "SELECT * FROM Users where username = '" + username + "' and password = '" + password + "'";
            PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
            ResultSet resultSet = selectStmt.executeQuery();
            while (resultSet.next()) {
                // Process each row
                String column1Value = resultSet.getString("username");
                // Get other columns similarly
                return column1Value;
            }

            return "user not found";
        } catch (SQLException e) {

            e.printStackTrace();           
            return "Error";

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