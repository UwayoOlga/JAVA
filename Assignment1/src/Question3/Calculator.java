package Question3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Calculator {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Simple Calculator");
        frame.setSize(500, 300);
        frame.setLayout(null);  // Using absolute positioning
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Label and text field for Number 1
        JLabel labelNum1 = new JLabel("Number 1:");
        labelNum1.setBounds(50, 30, 150, 30);
        frame.add(labelNum1);

        JTextField txtNum1 = new JTextField();
        txtNum1.setBounds(220, 30, 200, 30);
        frame.add(txtNum1);

        // Label and text field for Number 2
        JLabel labelNum2 = new JLabel("Number 2:");
        labelNum2.setBounds(50, 70, 150, 30);
        frame.add(labelNum2);

        JTextField txtNum2 = new JTextField();
        txtNum2.setBounds(220, 70, 200, 30);
        frame.add(txtNum2);

        // Label and dropdown (JComboBox) for selecting the operation
        JLabel labelOperation = new JLabel("Operation:");
        labelOperation.setBounds(50, 110, 150, 30);
        frame.add(labelOperation);

        String[] operations = { "+", "-", "*", "/" };
        JComboBox<String> comboOperation = new JComboBox<>(operations);
        comboOperation.setBounds(220, 110, 200, 30);
        frame.add(comboOperation);

        // Calculate button without custom color settings
        JButton btnCalculate = new JButton("Calculate");
        btnCalculate.setBounds(50, 160, 150, 40);
        frame.add(btnCalculate);

        // Label to display the result
        JLabel labelResult = new JLabel("Result:");
        labelResult.setBounds(220, 160, 200, 40);
        labelResult.setFont(new Font("SansSerif", Font.BOLD, 16));
        frame.add(labelResult);

        // Action listener for the Calculate button
        btnCalculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double num1 = Double.parseDouble(txtNum1.getText().trim());
                    double num2 = Double.parseDouble(txtNum2.getText().trim());
                    String op = (String) comboOperation.getSelectedItem();
                    double result = 0.0;

                    // Use a switch statement to perform the selected operation
                    switch (op) {
                        case "+":
                            result = num1 + num2;
                            break;
                        case "-":
                            result = num1 - num2;
                            break;
                        case "*":
                            result = num1 * num2;
                            break;
                        case "/":
                            if (num2 == 0) {
                                JOptionPane.showMessageDialog(frame, "Division by zero is not allowed.", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            result = num1 / num2;
                            break;
                        default:
                            JOptionPane.showMessageDialog(frame, "Unknown operation.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                    }
                    labelResult.setText("Result: " + result);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }
}
