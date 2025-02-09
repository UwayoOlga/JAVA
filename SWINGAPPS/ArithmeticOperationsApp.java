import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArithmeticOperationsApp {
    public static void main(String[] args) {
        // Step 1: Create an instance of JFrame
        JFrame frame = new JFrame("Arithmetic Operations");
        frame.setSize(400, 400);
        frame.setLayout(null); // manual component positioning

        // Step 2: Create components
        JLabel lblNum1 = new JLabel("First Number");
        JLabel lblNum2 = new JLabel("Second Number");
        JLabel lblResult = new JLabel("Result");
        JTextField txtNum1 = new JTextField();
        JTextField txtNum2 = new JTextField();
        JLabel lblDisplayResult = new JLabel();

        JButton btnAdd = new JButton("+");
        JButton btnSubtract = new JButton("-");
        JButton btnMultiply = new JButton("*");
        JButton btnDivide = new JButton("/");

        // Set bounds for components
        lblNum1.setBounds(50, 50, 150, 30);
        txtNum1.setBounds(200, 50, 150, 30);
        lblNum2.setBounds(50, 100, 150, 30);
        txtNum2.setBounds(200, 100, 150, 30);
        lblResult.setBounds(50, 150, 150, 30);
        lblDisplayResult.setBounds(200, 150, 150, 30);

        btnAdd.setBounds(50, 200, 50, 30);
        btnSubtract.setBounds(120, 200, 50, 30);
        btnMultiply.setBounds(190, 200, 50, 30);
        btnDivide.setBounds(260, 200, 50, 30);

        // Step 3: Add components to the JFrame
        frame.add(lblNum1);
        frame.add(txtNum1);
        frame.add(lblNum2);
        frame.add(txtNum2);
        frame.add(lblResult);
        frame.add(lblDisplayResult);
        frame.add(btnAdd);
        frame.add(btnSubtract);
        frame.add(btnMultiply);
        frame.add(btnDivide);

        // Add ActionListeners to buttons
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int num1 = Integer.parseInt(txtNum1.getText());
                    int num2 = Integer.parseInt(txtNum2.getText());
                    int sum = num1 + num2;
                    lblDisplayResult.setText(String.valueOf(sum));
                } catch (NumberFormatException ex) {
                    lblDisplayResult.setText("Invalid Input");
                }
            }
        });

        btnSubtract.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int num1 = Integer.parseInt(txtNum1.getText());
                    int num2 = Integer.parseInt(txtNum2.getText());
                    int difference = num1 - num2;
                    lblDisplayResult.setText(String.valueOf(difference));
                } catch (NumberFormatException ex) {
                    lblDisplayResult.setText("Invalid Input");
                }
            }
        });

        btnMultiply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int num1 = Integer.parseInt(txtNum1.getText());
                    int num2 = Integer.parseInt(txtNum2.getText());
                    int product = num1 * num2;
                    lblDisplayResult.setText(String.valueOf(product));
                } catch (NumberFormatException ex) {
                    lblDisplayResult.setText("Invalid Input");
                }
            }
        });

        btnDivide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int num1 = Integer.parseInt(txtNum1.getText());
                    int num2 = Integer.parseInt(txtNum2.getText());
                    if (num2 == 0) {
                        lblDisplayResult.setText("Cannot Divide by Zero");
                    } else {
                        double quotient = (double) num1 / num2;
                        lblDisplayResult.setText(String.valueOf(quotient));
                    }
                } catch (NumberFormatException ex) {
                    lblDisplayResult.setText("Invalid Input");
                }
            }
        });
 
        frame.setVisible(true);
        frame.setResizable(false);

    }
}
