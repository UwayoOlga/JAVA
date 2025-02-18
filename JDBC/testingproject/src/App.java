import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;  // ✅ Correct import for Statement

public class App {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "system";
        String password = "auca123";

        // Register / load JDBC Driver
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("JDBC Driver loaded Successfully!!");

            // Establish JDBC Connection
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established Successfully!!");

            // Create statement 
            Statement stm = connection.createStatement();

            // Execute the queries 
            String queryname = "SELECT ID, USER_NAME, USER_PASSWORD, STATUS FROM USERS";
            ResultSet rs = stm.executeQuery(queryname);

            // Process query results
            System.out.println("Users Records:");
            while (rs.next()) {
                int id = rs.getInt("ID");
                String username = rs.getString("USER_NAME");
                String userPassword = rs.getString("USER_PASSWORD");
                String status = rs.getString("STATUS");
                System.out.println("ID: " + id + ", Username: " + username + 
                                   ", Password: " + userPassword + ", Status: " + status);
            }

            // Close resources
            rs.close();
            stm.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to register JDBC Driver: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
