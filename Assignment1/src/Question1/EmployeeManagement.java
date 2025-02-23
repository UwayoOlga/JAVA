package Question1;  
import config.ConfigLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EmployeeManagement {
    public static void main(String[] args) {
        // Load credentials from .env file
        String dbUrl = ConfigLoader.getProperty("DB_URL");
        String dbUsername = ConfigLoader.getProperty("DB_USERNAME");
        String dbPassword = ConfigLoader.getProperty("DB_PASSWORD");

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            System.out.println("Connected to MySQL successfully!");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
