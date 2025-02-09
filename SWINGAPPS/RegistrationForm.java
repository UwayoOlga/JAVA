import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationForm {
    public static void main(String[] args) {
        // JFrame instance
        JFrame empForm = new JFrame("Employee Registration Form");
        empForm.setLayout(null);
        empForm.setSize(500, 800);  
        empForm.getContentPane().setBackground(new java.awt.Color(255, 182, 193));

        // Creating Components
        JLabel lblID = new JLabel("Employee ID");
        lblID.setBounds(100, 50, 150, 30);
        JLabel lblFirstName = new JLabel("First Name");
        lblFirstName.setBounds(100, 90, 150, 30);
        JLabel lblLastName = new JLabel("Last Name");
        lblLastName.setBounds(100, 130, 150, 30);  
        JLabel lblAge = new JLabel("Age");
        lblAge.setBounds(100, 170, 150, 30);  
        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(100, 210, 150, 30);  
        JLabel lblSalary = new JLabel("Salary");
        lblSalary.setBounds(100, 250, 150, 30);  
        JLabel lblHobbies = new JLabel("Hobbies");
        lblHobbies.setBounds(100, 290, 150, 30);  
        JLabel lblDepartment = new JLabel("Department");
        lblDepartment.setBounds(100, 370, 150, 30);  


        // Text fields
        JTextField txtID = new JTextField();
        txtID.setBounds(260, 50, 150, 30);
        JTextField txtFirstName = new JTextField();
        txtFirstName.setBounds(260, 90, 150, 30);
        JTextField txtLastName = new JTextField();
        txtLastName.setBounds(260, 130, 150, 30);  
        JTextField txtAge = new JTextField();
        txtAge.setBounds(260, 170, 150, 30);  
        JTextField txtSalary = new JTextField();
        txtSalary.setBounds(260, 250, 150, 30);  


        JRadioButton rbFemale = new JRadioButton("Female");
        rbFemale.setBounds(260, 210, 80, 30);
        rbFemale.setBackground(new java.awt.Color(255, 105, 180));   
        JRadioButton rbMale = new JRadioButton("Male");
        rbMale.setBounds(340, 210, 80, 30); 
        rbMale.setBackground(new java.awt.Color(255, 105, 180));     

        // Grouping radio buttons to avoid selection of multiple choices 
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbFemale);
        genderGroup.add(rbMale);



        String[] departmentList = {"HR", "Finance", "IT", "Accounting"};
        JComboBox<String> cmbDepartment = new JComboBox<>(departmentList);
        cmbDepartment.setBounds(260, 370, 150, 30);  

        JCheckBox chkFootball = new JCheckBox("Football");
        chkFootball.setBounds(260, 330, 150, 30); 
        chkFootball.setBackground(new java.awt.Color(255, 105, 180));   
        JCheckBox chkVolleyball = new JCheckBox("Volleyball");
        chkVolleyball.setBounds(260, 370, 150, 30); 
        chkVolleyball.setBackground(new java.awt.Color(255, 105, 180));  
        JCheckBox chkCricket = new JCheckBox("Cricket");
        chkCricket.setBounds(260, 410, 150, 30); 
        chkCricket.setBackground(new java.awt.Color(255, 105, 180));    
        JCheckBox chkSwimming = new JCheckBox("Swimming");
        chkSwimming.setBounds(260, 450, 150, 30); 
        chkSwimming.setBackground(new java.awt.Color(255, 105, 180));   
        JCheckBox chkTerms = new JCheckBox("Accept terms & conditions");
        chkTerms.setBounds(100, 500, 200, 30);  
        chkTerms.setBackground(new java.awt.Color(255, 105, 180)); 
        JButton btnSave = new JButton("Save");
        btnSave.setBounds(240, 540, 150, 30);  
        btnSave.setBackground(new java.awt.Color(255, 105, 180)); 


        // Adding components to the frame
        empForm.add(lblID);
        empForm.add(txtID);
        empForm.add(lblFirstName);
        empForm.add(txtFirstName);
        empForm.add(lblLastName);
        empForm.add(txtLastName);
        empForm.add(lblAge);
        empForm.add(txtAge);
        empForm.add(lblGender);
        empForm.add(rbFemale);
        empForm.add(rbMale);
        empForm.add(lblSalary);
        empForm.add(txtSalary);

        empForm.add(lblHobbies);
        empForm.add(chkFootball);
        empForm.add(chkVolleyball);
        empForm.add(chkCricket);
        empForm.add(chkSwimming); 
        empForm.add(lblDepartment);
        empForm.add(cmbDepartment);
        empForm.add(chkTerms);
        empForm.add(btnSave);


        empForm.setVisible(true);

// add an action listener on the save button
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                String ID = txtID.getText();
                String FirstName = txtFirstName.getText();
                String LastName = txtLastName.getText();
                int age = Integer.parseInt(txtAge.getText());
                String Gender = "";
                if(rbFemale.isSelected()){
                    Gender = "Female";
                }
                if(rbMale.isSelected()){
                    Gender = "Male";
                }
                Long Salary = Long.parseLong(txtSalary.getText());
                String Department = cmbDepartment.getSelectedItem().toString();
                JOptionPane.showMessageDialog(empForm, "Form submitted!");
                String Hobbies = "";
                if(chkFootball.isSelected()){
                    Hobbies = Hobbies + "FootBall";
                }
                if(chkVolleyball.isSelected()){
                    Hobbies = Hobbies + "VolleyBall";
                }
                if(chkCricket.isSelected()){
                    Hobbies = Hobbies + "Cricket";
                }
                if(chkSwimming.isSelected()){
                    Hobbies = Hobbies + "Swimming";
                }

                boolean Terms = false;
                if(chkTerms.isSelected()){
                    Terms = true;
                }
                // Display entered info 
                System.out.println("Employee ID:" +ID);
                System.out.println("First Name:" +FirstName);
                System.out.println("Last Name:" +LastName);
                System.out.println("Age:" +age);
                System.out.println("Gender:" +Gender);
                System.out.println("Salary:" +Salary);
                System.out.println("Department:" +Department);
                System.out.println("Hobbies:" +Hobbies);
                System.out.println("Confirmation:" +Terms);


            }
        });
    }
}
