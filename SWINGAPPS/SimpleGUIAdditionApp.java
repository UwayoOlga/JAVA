// exercise 1 ; adding two nubes 
import javax.swing.JOptionPane;

public class SimpleGUIAdditionApp {
    public static void main(String[] args){
        int num1, num2, sum;
        String number1, number2;  
        number1 = JOptionPane.showInputDialog("Enter the first number");
        number2 = JOptionPane.showInputDialog("Enter the Second number");
        num1 = Integer.parseInt(number1);
        num2 = Integer.parseInt(number2);
        sum = num1 + num2;
        JOptionPane.showMessageDialog(null, "The sum is " + sum, "the result", JOptionPane.PLAIN_MESSAGE);
    }
}