import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInCheck {
    public static void main(String[] args) {
        // Step 1: Create the JFrame for the login form
        JFrame loginFrame = new JFrame("Login Form");
        loginFrame.setSize(300, 200);
        loginFrame.setLayout(null); 

        // Step 2: Add labels and text fields for username and password
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(20, 20, 80, 25);
        loginFrame.add(lblUsername);

        JTextField txtUsername = new JTextField();
        txtUsername.setBounds(100, 20, 150, 25);
        loginFrame.add(txtUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(20, 60, 80, 25);
        loginFrame.add(lblPassword);

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBounds(100, 60, 150, 25);
        loginFrame.add(txtPassword);

        // Step 3: Add buttons for Login and Clear
        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(50, 100, 80, 30);
        btnLogin.setBackground(new java.awt.Color(255, 105, 180));  
        loginFrame.add(btnLogin);

        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(150, 100, 80, 30);
        btnClear.setBackground(new java.awt.Color(255, 105, 180));  
        loginFrame.add(btnClear);

        // Step 4: Add functionality to the Login button
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();  
                String password = new String(txtPassword.getPassword());  
 
                if (username.equals("java") && password.equals("class@123")) {
                    loginFrame.dispose(); // Close the login frame
                    showHomePage();
                } else { 
                    JOptionPane.showMessageDialog(loginFrame, "Incorrect username/password. Try again.");
                }
            }
        });

        // Step 5: Add functionality to the Clear button
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtUsername.setText("");  
                txtPassword.setText("");  
            }
        });
        loginFrame.getContentPane().setBackground(new java.awt.Color(255, 182, 193));  

        loginFrame.setVisible(true);
    }

    // Method to display the home page
    private static void showHomePage() {
        JFrame homeFrame = new JFrame("Home Page");
        homeFrame.setSize(400, 300);
        homeFrame.setLayout(null);

        JLabel lblWelcome = new JLabel("Welcome to the Home Page!");
        lblWelcome.setBounds(100, 100, 200, 25);
        homeFrame.getContentPane().setBackground(new java.awt.Color(255, 182, 193));  

        homeFrame.add(lblWelcome);

        homeFrame.setVisible(true);
    }
}
