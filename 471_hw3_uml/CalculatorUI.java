import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class CalculatorUI {

	public static int NUM_BITS = 8;
	private boolean hasPushedEnter = false;
	private JFrame frmBinaryRpnCalculator;
	private JTextField textFieldBottomDisplay, textFieldTopDisplay;
	private JButton buttonBackspace, buttonClear, buttonEnter;
	private JButton buttonAdd, buttonSubtract, buttonDivide, buttonMultiply;
	private JButton button0, button1;
	private StringBuilder builder;
	private RPNStack stack;
	private JLabel warningLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculatorUI window = new CalculatorUI();
					window.frmBinaryRpnCalculator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CalculatorUI() {
		builder = new StringBuilder();
		stack = new RPNStack();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBinaryRpnCalculator = new JFrame();
		frmBinaryRpnCalculator.setTitle("Binary RPN Calculator");
		frmBinaryRpnCalculator.setResizable(false);
		frmBinaryRpnCalculator.setBounds(100, 100, 249, 269);
		frmBinaryRpnCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBinaryRpnCalculator.getContentPane().setLayout(null);

		textFieldTopDisplay = new JTextField();
		textFieldTopDisplay.setHorizontalAlignment(SwingConstants.TRAILING);
		textFieldTopDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField textField = (JTextField) e.getSource();
				String text = textField.getText();
				if (text.length() > NUM_BITS) {
					String validExpression = text.substring(0, NUM_BITS);
					stack.push(validExpression);
					textFieldBottomDisplay.setText(validExpression);
				} else {
					System.out.println("ENTERED A VALUE");
					stack.push(text);
					textFieldBottomDisplay.setText(text);
				}
			}
		});
		textFieldTopDisplay.setToolTipText("Enter expression to evaluate (up to 8 characters)");
		textFieldTopDisplay.setBounds(10, 11, 221, 20);
		frmBinaryRpnCalculator.getContentPane().add(textFieldTopDisplay);
		textFieldTopDisplay.setColumns(10);

		textFieldBottomDisplay = new JTextField();
		textFieldBottomDisplay.setText("00000000");
		textFieldBottomDisplay.setHorizontalAlignment(SwingConstants.TRAILING);
		textFieldBottomDisplay.setEditable(false);
		textFieldBottomDisplay.setColumns(10);
		textFieldBottomDisplay.setBounds(10, 42, 221, 20);
		frmBinaryRpnCalculator.getContentPane().add(textFieldBottomDisplay);

		buttonBackspace = new JButton("DELETE");
		buttonBackspace.setToolTipText("Delete the rightmost character (if any) from the display");
		buttonBackspace.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String fieldExpression = textFieldTopDisplay.getText();
				if (fieldExpression.length() == 0) {
					textFieldTopDisplay.setText("");
					textFieldBottomDisplay.setText("00000000");
				} else {
					fieldExpression = fieldExpression.substring(0, fieldExpression.length() - 1);
					textFieldTopDisplay.setText(fieldExpression);
				}
			}
		});
		buttonBackspace.setBounds(142, 79, 89, 23);
		frmBinaryRpnCalculator.getContentPane().add(buttonBackspace);

		buttonClear = new JButton("CLEAR");
		buttonClear.setToolTipText("Clear the display");
		buttonClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textFieldTopDisplay.setText("");
				builder = new StringBuilder();
			}
		});
		buttonClear.setBounds(10, 79, 89, 23);
		frmBinaryRpnCalculator.getContentPane().add(buttonClear);

		buttonEnter = new JButton("ENTER");
		buttonEnter.setToolTipText("Evaulate the expression");
		buttonEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hasPushedEnter = true;
				String textFieldExpression = textFieldTopDisplay.getText();
				System.out.println("THIS");
				if (textFieldExpression.length() != 0) {
					stack.push(textFieldExpression);
					textFieldBottomDisplay.setText(textFieldExpression);
					textFieldTopDisplay.setText("");
				}
			}
		});
		buttonEnter.setBounds(142, 213, 89, 23);
		frmBinaryRpnCalculator.getContentPane().add(buttonEnter);

		buttonSubtract = new JButton("-");
		buttonSubtract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textFiledExpression = textFieldBottomDisplay.getText();
				if (textFiledExpression.length() != 0) {
					textFieldTopDisplay.setText(stack.peek() + " - " + textFieldTopDisplay.getText());
				}
			}
		});
		buttonSubtract.setToolTipText("Perform binary subtraction");
		buttonSubtract.setBounds(142, 113, 89, 23);
		frmBinaryRpnCalculator.getContentPane().add(buttonSubtract);

		buttonDivide = new JButton("/");
		buttonDivide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textFiledExpression = textFieldBottomDisplay.getText();
				if (textFiledExpression.length() != 0) {
					textFieldTopDisplay.setText(stack.peek() + " / " + textFieldTopDisplay.getText());
				}
			}
		});
		buttonDivide.setToolTipText("Perform binary division");
		buttonDivide.setBounds(142, 147, 89, 23);
		frmBinaryRpnCalculator.getContentPane().add(buttonDivide);

		buttonAdd = new JButton("+");
		buttonAdd.setToolTipText("Perform binary addtion");
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String text = textFieldTopDisplay.getText();
					if (text.length() != 0) {
						textFieldTopDisplay.setText(stack.peek() + " + " + text);
						stack.push(text);
					}
					System.out.println("top of stack before: " + stack);
					stack.addition();
					System.out.println("top of stack after: " + stack);
				} catch (EmptyStackException empty) {
					showEmptyStackDialog();
				}
			}
		});
		buttonAdd.setBounds(10, 113, 89, 23);
		frmBinaryRpnCalculator.getContentPane().add(buttonAdd);

		buttonMultiply = new JButton("x");
		buttonMultiply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textFiledExpression = textFieldBottomDisplay.getText();
				if (textFiledExpression.length() != 0) {
					textFieldTopDisplay.setText(stack.peek() + " x " + textFieldTopDisplay.getText());
				}
			}
		});
		buttonMultiply.setToolTipText("Perform binary multiplication");
		buttonMultiply.setBounds(10, 147, 89, 23);
		frmBinaryRpnCalculator.getContentPane().add(buttonMultiply);

		button0 = new JButton("0");
		button0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (hasPushedEnter) {
					builder = new StringBuilder();
				}
				builder.append("0");
				textFieldTopDisplay.setText(builder.toString());
				hasPushedEnter = false;
			}
		});
		button0.setBounds(10, 213, 49, 23);
		frmBinaryRpnCalculator.getContentPane().add(button0);

		button1 = new JButton("1");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (hasPushedEnter) {
					builder = new StringBuilder();
				}
				builder.append("1");
				textFieldTopDisplay.setText(builder.toString());
				hasPushedEnter = false;
			}
		});
		button1.setBounds(62, 213, 49, 23);
		frmBinaryRpnCalculator.getContentPane().add(button1);

		warningLabel = new JLabel("Inputs exceeding 8-bits will be truncated");
		warningLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		warningLabel.setEnabled(false);
		warningLabel.setBounds(10, 181, 221, 14);
		frmBinaryRpnCalculator.getContentPane().add(warningLabel);
	}

	private void showEmptyStackDialog() {
		JOptionPane.showMessageDialog(frmBinaryRpnCalculator,
				"Please provide at least two operands followed by an operator", "Empty stack",
				JOptionPane.ERROR_MESSAGE);
	}
}
