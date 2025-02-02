import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class SimpleAdditionForm {
    public static void main(String[] args) {

     // Create a window using JFrame
     JFrame frame = new JFrame("Simple Addition Form"); 
     frame.setSize(400, 400);  
     frame.setResizable(true);
     frame.setVisible(true); // making the frame visible

     // Creating Labels, textfields, buttons...(components instances)
     JLabel Num1 = new JLabel("First Number");
     JLabel Num2 = new JLabel("Second Number");
     JLabel Sum = new JLabel("The Sum");
     JLabel ResultDisplay = new JLabel();
     JTextField txtNum1 = new JTextField();
     JTextField txtNum2 = new JTextField();
     JButton btnAdd = new JButton("Add");
     Num1.setBounds(100, 50, 150, 30);
     txtNum1.setBounds(270, 50, 150, 30);
     Num2.setBounds(100, 100, 150, 30);
     txtNum2.setBounds(270, 100, 150, 30);
     Sum.setBounds(100, 150, 150, 30);
     ResultDisplay.setBounds(270, 150, 150, 30);
     btnAdd.setBounds(100, 200, 150, 30);

      // Adding components to the frame
     frame.setLayout(null);
     frame.add(Num1);
     frame.add(txtNum1);
     frame.add(Num2);
     frame.add(txtNum2);
     frame.add(Sum);
     frame.add(ResultDisplay);
     frame.add(btnAdd);

     // Adding an event listener to handle button click
     btnAdd.addActionListener(new ActionListener() { // A new instance of an ActionListener 
        public void actionPerformed(ActionEvent e) {  // this method is overridden from the actionlistener
            int num1 = Integer.parseInt(txtNum1.getText());
            int num2 = Integer.parseInt(txtNum2.getText());
            int Sum = num1 + num2;
            ResultDisplay.setText(Sum+"");
    }
});
    }
}
