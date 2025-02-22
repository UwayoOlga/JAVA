import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EmployeeManagement {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/employee_management";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "California123!";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            System.out.println("✅ Connected to MySQL successfully!");
            
            // Use the connection (e.g., execute a query)
            
        } catch (SQLException e) {
            System.err.println("❌ Database error: " + e.getMessage());
        }
    }
}
