import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {
    private JTextField textField;
    private JButton[] numberButtons;
    private JButton[] operatorButtons;
    private JButton equalsButton;
    private JButton clearButton;
    
    private String currentInput = "";
    private double firstOperand = 0;
    private char selectedOperator = ' ';
    
    public CalculatorGUI() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        textField = new JTextField();
        numberButtons = new JButton[10];
        operatorButtons = new JButton[4];
        equalsButton = new JButton("=");
        clearButton = new JButton("C");
        
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
        }
        
        operatorButtons[0] = new JButton("+");
        operatorButtons[1] = new JButton("-");
        operatorButtons[2] = new JButton("*");
        operatorButtons[3] = new JButton("/");
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));
        
        buttonPanel.add(numberButtons[7]);
        buttonPanel.add(numberButtons[8]);
        buttonPanel.add(numberButtons[9]);
        buttonPanel.add(operatorButtons[0]);
        
        buttonPanel.add(numberButtons[4]);
        buttonPanel.add(numberButtons[5]);
        buttonPanel.add(numberButtons[6]);
        buttonPanel.add(operatorButtons[1]);
        
        buttonPanel.add(numberButtons[1]);
        buttonPanel.add(numberButtons[2]);
        buttonPanel.add(numberButtons[3]);
        buttonPanel.add(operatorButtons[2]);
        
        buttonPanel.add(clearButton);
        buttonPanel.add(numberButtons[0]);
        buttonPanel.add(equalsButton);
        buttonPanel.add(operatorButtons[3]);
        
        textField.setPreferredSize(new Dimension(300, 75));
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        
        add(textField, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        
        for (int i = 0; i < 10; i++) {
            numberButtons[i].addActionListener(this);
        }
        
        for (int i = 0; i < 4; i++) {
            operatorButtons[i].addActionListener(this);
        }
        
        equalsButton.addActionListener(this);
        clearButton.addActionListener(this);
        
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        for (int i = 0; i < 10; i++) {
            if (source == numberButtons[i]) {
                currentInput += i;
                textField.setText(currentInput);
                return;
            }
        }
        
        if (source == operatorButtons[0]) {
            performOperation('+');
        } else if (source == operatorButtons[1]) {
            performOperation('-');
        } else if (source == operatorButtons[2]) {
            performOperation('*');
        } else if (source == operatorButtons[3]) {
            performOperation('/');
        }
        
        if (source == equalsButton) {
            if (!currentInput.isEmpty()) {
                double secondOperand = Double.parseDouble(currentInput);
                double result = calculateResult(firstOperand, selectedOperator, secondOperand);
                textField.setText(Double.toString(result));
                currentInput = Double.toString(result);
            }
        }
        
        if (source == clearButton) {
            currentInput = "";
            firstOperand = 0;
            selectedOperator = ' ';
            textField.setText("");
        }
    }
    
    private void performOperation(char operator) {
        if (!currentInput.isEmpty()) {
            firstOperand = Double.parseDouble(currentInput);
            selectedOperator = operator;
            currentInput = "";
        }
    }
    
    private double calculateResult(double operand1, char operator, double operand2) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid...", "Error", JOptionPane.ERROR_MESSAGE);
                    return 0;
                }
            default:
                return 0;
        }
    }
    
    public static void main(String[] args) {
        new CalculatorGUI();
    }
}
