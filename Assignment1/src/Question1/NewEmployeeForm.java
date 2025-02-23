package Question1;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class NewEmployeeForm extends JFrame {

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
        JTextField employeeIdField = new JTextField();
        employeeIdField.setBounds(250, 50, 200, 25);
        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(250, 80, 200, 25);
        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(250, 110, 200, 25);
        JTextField ageField = new JTextField();
        ageField.setBounds(250, 140, 200, 25);
        JTextField mobilePhoneField = new JTextField();
        mobilePhoneField.setBounds(250, 170, 200, 25);
        JTextField emailField = new JTextField();
        emailField.setBounds(250, 200, 200, 25);
        JTextField socialSecurityField = new JTextField();
        socialSecurityField.setBounds(250, 230, 200, 25);
        JTextField basicSalaryField = new JTextField();
        basicSalaryField.setBounds(250, 260, 200, 25);

        // Radio Buttons for Gender
        JRadioButton maleRadioButton = new JRadioButton("Male");
        JRadioButton femaleRadioButton = new JRadioButton("Female");
        maleRadioButton.setBounds(250, 290, 80, 25);
        femaleRadioButton.setBounds(330, 290, 80, 25);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        // Combo Box for Department
        JComboBox<String> departmentComboBox = new JComboBox<>(new String[]{"IT", "Finance", "Accounting"});
        departmentComboBox.setBounds(250, 320, 200, 25);

        // Checkboxes for Hobbies
        JCheckBox footballCheckBox = new JCheckBox("Football");
        footballCheckBox.setBounds(250, 350, 80, 25);
        JCheckBox volleyballCheckBox = new JCheckBox("Volleyball");
        volleyballCheckBox.setBounds(330, 350, 80, 25);
        JCheckBox cricketCheckBox = new JCheckBox("Cricket");
        cricketCheckBox.setBounds(250, 400, 80, 25);
        JCheckBox swimmingCheckBox = new JCheckBox("Swimming");
        swimmingCheckBox.setBounds(330, 400, 100, 25);

        // Save Button
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(250, 450, 100, 30);
        saveButton.addActionListener(e -> saveEmployeeDetails(employeeIdField, firstNameField, lastNameField, ageField,
                mobilePhoneField, emailField, socialSecurityField,
                basicSalaryField, maleRadioButton, femaleRadioButton,
                departmentComboBox, footballCheckBox,
                volleyballCheckBox, cricketCheckBox, swimmingCheckBox));

        // Add components to the frame
        add(employeeIdLabel); add(employeeIdField);
        add(firstNameLabel); add(firstNameField);
        add(lastNameLabel); add(lastNameField);
        add(ageLabel); add(ageField);
        add(mobilePhoneLabel); add(mobilePhoneField);
        add(emailLabel); add(emailField);
        add(socialSecurityLabel); add(socialSecurityField);
        add(basicSalaryLabel); add(basicSalaryField);
        add(genderLabel); add(maleRadioButton); add(femaleRadioButton);
        add(departmentLabel); add(departmentComboBox);
        add(hobbiesLabel); add(footballCheckBox); add(volleyballCheckBox);
        add(cricketCheckBox); add(swimmingCheckBox); add(saveButton);
    }

    private void saveEmployeeDetails(JTextField employeeIdField, JTextField firstNameField, JTextField lastNameField,
                                     JTextField ageField, JTextField mobilePhoneField, JTextField emailField,
                                     JTextField socialSecurityField, JTextField basicSalaryField,
                                     JRadioButton maleRadioButton, JRadioButton femaleRadioButton,
                                     JComboBox<String> departmentComboBox, JCheckBox footballCheckBox,
                                     JCheckBox volleyballCheckBox, JCheckBox cricketCheckBox,
                                     JCheckBox swimmingCheckBox) {
        String hobbies = "";
        if (footballCheckBox.isSelected()) hobbies += "Football, ";
        if (volleyballCheckBox.isSelected()) hobbies += "Volleyball, ";
        if (cricketCheckBox.isSelected()) hobbies += "Cricket, ";
        if (swimmingCheckBox.isSelected()) hobbies += "Swimming, ";
        if (!hobbies.isEmpty()) hobbies = hobbies.substring(0, hobbies.length() - 2);

        // Get basic salary
        double basicSalary = Double.parseDouble(basicSalaryField.getText());

        // Calculate allowance based on department
        double allowance = 0;
        String department = (String) departmentComboBox.getSelectedItem();
        if ("IT".equals(department)) {
            allowance = 100000; // For IT Staff
        }

        // Calculate deductions (3% of basic salary)
        double deductions = 0.03 * basicSalary;

        // Calculate gross salary
        double grossSalary = basicSalary + allowance;

        // Calculate net salary
        double netSalary = grossSalary - deductions;

        // Save to the database
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            conn.setAutoCommit(false);

            // Save personal details to employee table
            PreparedStatement personalStmt = conn.prepareStatement("INSERT INTO employee (employee_id, first_name, last_name, age, mobile_phone, email, social_sec_no, gender, department, hobbies) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            personalStmt.setString(1, employeeIdField.getText());
            personalStmt.setString(2, firstNameField.getText());
            personalStmt.setString(3, lastNameField.getText());
            personalStmt.setInt(4, Integer.parseInt(ageField.getText()));
            personalStmt.setString(5, mobilePhoneField.getText());
            personalStmt.setString(6, emailField.getText());
            personalStmt.setString(7, socialSecurityField.getText());
            personalStmt.setString(8, maleRadioButton.isSelected() ? "Male" : "Female");
            personalStmt.setString(9, department);
            personalStmt.setString(10, hobbies);
            personalStmt.executeUpdate();

            // Save salary details to salary_data table
            PreparedStatement salaryStmt = conn.prepareStatement("INSERT INTO salary_data (employee_id, basic_salary, allowance, deductions, gross_salary, net_salary) VALUES (?, ?, ?, ?, ?, ?)");
            salaryStmt.setString(1, employeeIdField.getText());
            salaryStmt.setDouble(2, basicSalary);
            salaryStmt.setDouble(3, allowance);
            salaryStmt.setDouble(4, deductions);
            salaryStmt.setDouble(5, grossSalary);
            salaryStmt.setDouble(6, netSalary);
            salaryStmt.executeUpdate();

            conn.commit();
            JOptionPane.showMessageDialog(this, "Employee details and salary saved successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NewEmployeeForm().setVisible(true));
    }
}
