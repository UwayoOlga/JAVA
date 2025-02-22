package Question1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, clearButton;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/employee_management";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "California123!";

    public LoginForm() {
        setTitle("Login Form");
        setLayout(null);
        setSize(700, 350);
        getContentPane().setBackground(new Color(255, 182, 193)); // Light Pink Background
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
        usernameField.setBounds(300, 100, 150, 30); // Touch label
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setBounds(300, 130, 150, 30); // Touch label


        // Buttons
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 16));
        loginButton.setBounds(250, 200, 100, 30); // Adjusted, centered
        loginButton.setBackground(new Color(255, 105, 180));
        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.PLAIN, 16));
        clearButton.setBounds(370, 200, 100, 30); // Adjusted, centered
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

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT STATUS FROM USERS WHERE USER_NAME = ? AND USER_PASSWORD = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String status = rs.getString("STATUS");
                if ("E".equals(status)) {
                    JOptionPane.showMessageDialog(this, "Welcome to the Employee System!");
                } else {
                    JOptionPane.showMessageDialog(this, "Your account is disabled!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Wrong username/ password. Try again");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm().setVisible(true));
    }
}
