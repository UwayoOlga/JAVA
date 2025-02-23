package Question1;

import config.ConfigLoader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, clearButton;

    public LoginForm() {
        setTitle("Login Form");
        setLayout(null);
        setSize(700, 350);
        getContentPane().setBackground(new Color(255, 182, 193));  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Labels
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 16));
        lblUsername.setBounds(150, 100, 150, 30);
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 16));
        lblPassword.setBounds(150, 130, 150, 30);

        // Text Fields
        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameField.setBounds(300, 100, 150, 30);
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setBounds(300, 130, 150, 30);

        // Buttons
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 16));
        loginButton.setBounds(250, 200, 100, 30);
        loginButton.setBackground(new Color(255, 105, 180));
        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.PLAIN, 16));
        clearButton.setBounds(370, 200, 100, 30);
        clearButton.setBackground(new Color(255, 105, 180));

        add(lblUsername);
        add(usernameField);
        add(lblPassword);
        add(passwordField);
        add(loginButton);
        add(clearButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                usernameField.setText("");
                passwordField.setText("");
            }
        });
    }

    private void loginUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
 
        String dbUrl = ConfigLoader.getProperty("DB_URL");
        String dbUsername = ConfigLoader.getProperty("DB_USERNAME");
        String dbPassword = ConfigLoader.getProperty("DB_PASSWORD");

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            String sql = "SELECT STATUS FROM USERS WHERE USER_NAME = ? AND USER_PASSWORD = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String status = rs.getString("STATUS");
                if ("E".equals(status)) {
                    JOptionPane.showMessageDialog(this, "Welcome to the Employee System!");
                    this.dispose();
                    new NewEmployeeForm().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Your account is disabled!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Wrong username/password. Try again.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm().setVisible(true));
    }
}
