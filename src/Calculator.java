import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Calculator implements ActionListener {

    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] funcButtons = new JButton[9];
    /**
     * all function buttons.
     */
    JButton addButton, subButton, mulButton, divButton, decButton, equButton, delButton, clrButton, negButton;
    /**
     * the panel of all number buttons and some function buttons.
     */
    JPanel panel;

    Font myFont = new Font("Arial Hebrew", Font.CENTER_BASELINE, 25);

    /**
     * the numbers the user puts in.
     */
    double number1 = 0, number2 = 0, result = 0;
    /**
     * the function the user chooses.
     */
    char operator;


    Calculator() {

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 530);
        //explicitly opting NOT to use any layout manager for the container.
        //let only the set bounds method determine the size and positions.
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(myFont);
        textField.setEditable(false);


        /**
         * set the function buttons.
         */
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Delete");
        clrButton = new JButton("Clear");
        negButton = new JButton("( - )");
        /**
         * put the func buttons in the array
         */
        funcButtons[0] = addButton;
        funcButtons[1] = subButton;
        funcButtons[2] = mulButton;
        funcButtons[3] = divButton;
        funcButtons[4] = decButton;
        funcButtons[5] = equButton;
        funcButtons[6] = delButton;
        funcButtons[7] = clrButton;
        funcButtons[8] = negButton;

/**
 * set the action buttons
 */
        for (int i = 0; i < 9; i++) {
            //what will be written on the button.
            funcButtons[i].addActionListener(this);
            //the font that the button will have.
            funcButtons[i].setFont(myFont);
            funcButtons[i].setFocusable(false);
        }
        /**
         * set the number buttons
         */
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        /**
         * set the Delete, Clear, negative buttons because they arnt on the panel.
         */
        negButton.setBounds(40, 430, 110, 45);
        delButton.setBounds(150, 430, 110, 45);
        clrButton.setBounds(260, 430, 110, 45);

        panel = new JPanel();
        panel.setBounds(40, 100, 330, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        //panel adding.
        /**
         * adding the buttons to the panel.
         */
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        //frame adding.
        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textField);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        Calculator myCalc = new Calculator();
    }


    /**
     * makes the buttons actually work.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            //check if the text field has the" Put Numbers First " message and delete it when putting new number.
            if (!textField.getText().isEmpty()) {
                if ((textField.getText()).equals("Put Numbers First") )
                    textField.setText("");
            }

            for (int i = 0; i < 10; i++) {
                if (e.getSource() == numberButtons[i]) {
                    textField.setText(textField.getText().concat(String.valueOf(i)));
                }
            }
            //check that there is no more than one " . " in the text field
            if (!textField.getText().isEmpty()) {
                if ((textField.getText().substring(textField.getText().length()-1)).equals(".") )
                    textField.setText(textField.getText().substring(0,textField.getText().length()-1));
            }

            if (e.getSource() == decButton) {
                textField.setText(textField.getText().concat("."));
            }
            if (e.getSource() == addButton) {
                number1 = Double.parseDouble(textField.getText());
                operator = '+';
                textField.setText("");
            }
            if (e.getSource() == subButton) {
                number1 = Double.parseDouble(textField.getText());
                operator = '-';
                textField.setText("");
            }
            if (e.getSource() == mulButton) {
                number1 = Double.parseDouble(textField.getText());
                operator = '*';
                textField.setText("");
            }
            if (e.getSource() == divButton) {
                number1 = Double.parseDouble(textField.getText());
                operator = '/';
                textField.setText("");
            }
            if (e.getSource() == negButton) {
                //making sure that will work if starting with minus and won't get exception.
                if (textField.getText().isEmpty())
                    textField.setText("-");
                else {
                    double temp = Double.parseDouble(textField.getText());
                    temp *= -1;
                    textField.setText(String.valueOf(temp));
                }
            }
            if (e.getSource() == equButton) {
                boolean isOk = false;
                number2 = Double.parseDouble(textField.getText());

                switch (operator) {
                    case '+':
                        result = number1 + number2;
                        break;
                    case '-':
                        result = number1 - number2;
                        break;
                    case '*':
                        result = number1 * number2;
                        break;
                    case '/':
                        //if the user trying to / in 0;
                        if (number2 == 0) {
                            isOk = true;
                            break;
                        }
                        result = number1 / number2;
                        break;
                }
                if (isOk) {
                    textField.setText("ERROR");
                    number1 = 0;
                    number2 = 0;
                } else {
                    if (result - (int) result == 0) {
                        textField.setText(String.valueOf((int) result));
                    } else {
                        textField.setText(String.valueOf(result));
                    }
                    number1 = result;
                }
            }
            if (e.getSource() == clrButton) {
                textField.setText("");
            }
            if (e.getSource() == delButton) {
                String str = textField.getText();
                textField.setText("");
                //make sure that index will not go out of bounds.
                if (str.length() == 0)
                    textField.setText("");
                else
                    textField.setText(str.substring(0, str.length() - 1));
            }

            //make another text box and not to real value
        } catch (NumberFormatException e1) {
            textField.setText("Put Numbers First");
        }
    }
}
