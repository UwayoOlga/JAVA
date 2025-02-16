import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {      public static void main(String[] args) {
        String url = "jdbc:postgresql://[localhost]:[5432]/[Studentdatabase]";  
        String user = "system";
        String password = "123";
        //Register / load JDBC Driver
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("JDBC Driver loaded Successfully!!");
            // establish JDBC Connection
            Connection connection = DriverManager.getConnection(url, user, password);
        
            System.out.println("Connection established Successfully!!");
            // create statement 
            Statement stm = connection.createStatement();
            // execute the queries 
            String queryname = "SELECT ID, USER_NAME, USER_PASSWORD, STATUS FROM USERS";
            ResultSet rs = stm.executeQuery(queryname);
            // process query results
            System.out.println("Students Records::::");
            while (rs.next()) {
                int id = rs.getInt("ID");
                String username = rs.getString("USER_NAME");
                String password = rs.getString("USER_PASSWORD");
                String status = rs.getString("STATUS");
                System.out.println("ID: " + id + ", Username: " + username + ", Password: " + password + ", Status: " + status);
            }
        }
        catch(ClassNotFoundException e){ 
            System.out.println("Failed to register JDBC Driver ::::" + e.getMessage());
        } catch(SQLException e){
            System.out.println("Database error::::" +e.getMessage());

    } 
}
}