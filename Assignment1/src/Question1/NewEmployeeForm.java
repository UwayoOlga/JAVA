package Question1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class NewEmployeeForm extends JFrame {
    private JTextField employeeIdField, firstNameField, lastNameField, ageField, mobilePhoneField,
            emailField, socialSecurityField, basicSalaryField;
    private JComboBox<String> departmentComboBox;
    private JCheckBox footballCheckBox, volleyballCheckBox, cricketCheckBox, swimmingCheckBox;
    private JRadioButton maleRadioButton, femaleRadioButton;
    private JButton saveButton;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/employee_management";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "California123!";

    public NewEmployeeForm() {
        setTitle("New Employee Form");
        setLayout(null);
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Labels
        JLabel employeeIdLabel = new JLabel("Employee Id:");
        employeeIdLabel.setBounds(50, 50, 150, 25);
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(50, 80, 150, 25);
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(50, 110, 150, 25);
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(50, 140, 150, 25);
        JLabel mobilePhoneLabel = new JLabel("Mobile Phone:");
        mobilePhoneLabel.setBounds(50, 170, 150, 25);
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 200, 150, 25);
        JLabel socialSecurityLabel = new JLabel("Social Security Number:");
        socialSecurityLabel.setBounds(50, 230, 150, 25);
        JLabel basicSalaryLabel = new JLabel("Basic Salary:");
        basicSalaryLabel.setBounds(50, 260, 150, 25);
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(50, 290, 150, 25);
        JLabel departmentLabel = new JLabel("Department:");
        departmentLabel.setBounds(50, 320, 150, 25);
        JLabel hobbiesLabel = new JLabel("Hobbies:");
        hobbiesLabel.setBounds(50, 350, 150, 25);

        // Text Fields
        employeeIdField = new JTextField();
        employeeIdField.setBounds(250, 50, 200, 25);
        firstNameField = new JTextField();
        firstNameField.setBounds(250, 80, 200, 25);
        lastNameField = new JTextField();
        lastNameField.setBounds(250, 110, 200, 25);
        ageField = new JTextField();
        ageField.setBounds(250, 140, 200, 25);
        mobilePhoneField = new JTextField();
        mobilePhoneField.setBounds(250, 170, 200, 25);
        emailField = new JTextField();
        emailField.setBounds(250, 200, 200, 25);
        socialSecurityField = new JTextField();
        socialSecurityField.setBounds(250, 230, 200, 25);
        basicSalaryField = new JTextField();
        basicSalaryField.setBounds(250, 260, 200, 25);

        // Radio Buttons for Gender
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        maleRadioButton.setBounds(250, 290, 80, 25);
        femaleRadioButton.setBounds(330, 290, 80, 25);
        ButtonGroup genderGroup = new ButtonGroup(); // Group to ensure only one can be selected
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        // Combo Box for Department
        departmentComboBox = new JComboBox<>(new String[]{"IT", "Finance", "Accounting"});
        departmentComboBox.setBounds(250, 320, 200, 25);

        // Checkboxes for Hobbies
        footballCheckBox = new JCheckBox("Football");
        footballCheckBox.setBounds(250, 350, 80, 25);
        volleyballCheckBox = new JCheckBox("Volleyball");
        volleyballCheckBox.setBounds(330, 350, 80, 25);
        cricketCheckBox = new JCheckBox("Cricket");
        cricketCheckBox.setBounds(250, 400, 80, 25);
        swimmingCheckBox = new JCheckBox("Swimming");
        swimmingCheckBox.setBounds(330, 400, 100, 25);

        // Save Button
        saveButton = new JButton("Save");
        saveButton.setBounds(250, 450, 100, 30);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveEmployeeDetails();
            }
        });

        // Add components to the frame
        add(employeeIdLabel);
        add(employeeIdField);
        add(firstNameLabel);
        add(firstNameField);
        add(lastNameLabel);
        add(lastNameField);
        add(ageLabel);
        add(ageField);
        add(mobilePhoneLabel);
        add(mobilePhoneField);
        add(emailLabel);
        add(emailField);
        add(socialSecurityLabel);
        add(socialSecurityField);
        add(basicSalaryLabel);
        add(basicSalaryField);
        add(genderLabel);
        add(maleRadioButton);
        add(femaleRadioButton);
        add(departmentLabel);
        add(departmentComboBox);
        add(hobbiesLabel);
        add(footballCheckBox);
        add(volleyballCheckBox);
        add(cricketCheckBox);
        add(swimmingCheckBox);
        add(saveButton);
    }

    private void saveEmployeeDetails() {
        // Retrieve data from all fields
        String employeeId = employeeIdField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String age = ageField.getText();
        String mobilePhone = mobilePhoneField.getText();
        String email = emailField.getText();
        String socialSecurity = socialSecurityField.getText();
        String basicSalary = basicSalaryField.getText();

        // Validate mandatory fields
        if (employeeId.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || basicSalary.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields!");
            return;  // Stop further processing
        }

        try {
            int basic_salary = Integer.parseInt(basicSalary);  // Validate salary as an integer
            if (basic_salary <= 0) {
                JOptionPane.showMessageDialog(this, "Salary must be a positive number.");
                return;  // Stop further processing
            }

            // Calculate allowance, deductions, gross salary, and net salary
            int allowance = 0;
            if ("IT".equals(departmentComboBox.getSelectedItem())) {
                allowance = 100000;
            }

            double deductions = 0.03 * basic_salary;
            double grossSalary = basic_salary + allowance;
            double netSalary = grossSalary - deductions;

            // Save to database
            saveToDatabase(employeeId, firstName, lastName, age, mobilePhone, email, socialSecurity, allowance, deductions, grossSalary, netSalary, basic_salary);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for salary.");
        }
    }

    private void saveToDatabase(String employeeId, String firstName, String lastName, String age, String mobilePhone, String email, String socialSecurity, int allowance, double deductions, double grossSalary, double netSalary, int basic_salary) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            // Insert into employee_personal_details table
            String personalDetailsSql = "INSERT INTO employee (employee_id, first_name, last_name, age, mobile_phone, email, social_sec_no, gender, department) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement personalDetailsStmt = conn.prepareStatement(personalDetailsSql);
            personalDetailsStmt.setString(1, employeeId);
            personalDetailsStmt.setString(2, firstName);
            personalDetailsStmt.setString(3, lastName);
            personalDetailsStmt.setString(4, age);
            personalDetailsStmt.setString(5, mobilePhone);
            personalDetailsStmt.setString(6, email);
            personalDetailsStmt.setString(7, socialSecurity);
            personalDetailsStmt.setString(8, maleRadioButton.isSelected() ? "Male" : "Female");
            personalDetailsStmt.setString(9, (String) departmentComboBox.getSelectedItem());
            personalDetailsStmt.executeUpdate();

            // Insert into employee_salary_details table
            String salaryDetailsSql = "INSERT INTO salary_data (employee_id, basic_salary, allowance, deductions, gross_salary, net_salary) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement salaryDetailsStmt = conn.prepareStatement(salaryDetailsSql);
            salaryDetailsStmt.setString(1, employeeId);
            salaryDetailsStmt.setInt(2, basic_salary);
            salaryDetailsStmt.setInt(3, allowance);
            salaryDetailsStmt.setDouble(4, deductions);
            salaryDetailsStmt.setDouble(5, grossSalary);
            salaryDetailsStmt.setDouble(6, netSalary);
            salaryDetailsStmt.executeUpdate();

            // Insert hobbies into the database if they are selected
            insertHobbies(conn, employeeId);

            JOptionPane.showMessageDialog(this, "Employee details saved successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }
    }

    private void insertHobbies(Connection conn, String employeeId) throws SQLException {
        // Insert hobbies if selected
        String hobbiesSql = "INSERT INTO employee (employee_id, hobbies) VALUES (?, ?)";
        PreparedStatement hobbiesStmt = conn.prepareStatement(hobbiesSql);

        if (footballCheckBox.isSelected()) {
            hobbiesStmt.setString(1, employeeId);
            hobbiesStmt.setString(2, "Football");
            hobbiesStmt.executeUpdate();
        }
        if (volleyballCheckBox.isSelected()) {
            hobbiesStmt.setString(1, employeeId);
            hobbiesStmt.setString(2, "Volleyball");
            hobbiesStmt.executeUpdate();
        }
        if (cricketCheckBox.isSelected()) {
            hobbiesStmt.setString(1, employeeId);
            hobbiesStmt.setString(2, "Cricket");
            hobbiesStmt.executeUpdate();
        }
        if (swimmingCheckBox.isSelected()) {
            hobbiesStmt.setString(1, employeeId);
            hobbiesStmt.setString(2, "Swimming");
            hobbiesStmt.executeUpdate();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NewEmployeeForm().setVisible(true));
    }
}
