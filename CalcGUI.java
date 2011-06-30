package balancedTernary;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/**
 * @author Tianyi Du
 * 
 *         Creates GUI for the BalancedTernary Calculator.
 */
@SuppressWarnings("serial")
public class CalcGUI extends JFrame {
	// Declare primitives
	String prevOperation;
	String prevOperand;
	String currentOperand;
	boolean needRefresh;
	boolean operatorSet;

	// Declare buttons and panel
	JButton multiplyButton;
	JButton divideButton;
	JButton addButton;
	JButton subtractButton;
	JButton negButton;
	JButton equalsButton;
	JButton oneButton;
	JButton nButton;
	JButton zeroButton;
	JButton clearButton;
	JButton allClearButton;
	JButton decButton;
	JTextField screen;
	JPanel buttonPanel;

	/**
	 * Instantiates all primitives, buttons, and panels.
	 */
	public CalcGUI() {
		// instantiate primitives
		prevOperation = "";
		prevOperand = "";
		currentOperand = "0";
		needRefresh = false;
		operatorSet = false;

		// Instantiate buttons and panel
		multiplyButton = new JButton("x");
		divideButton = new JButton("÷");
		addButton = new JButton("+");
		subtractButton = new JButton("-");
		negButton = new JButton("±");
		equalsButton = new JButton("=");
		oneButton = new JButton("1");
		nButton = new JButton("  N  ");
		zeroButton = new JButton("  0  ");
		clearButton = new JButton("C");
		allClearButton = new JButton("AC");
		decButton = new JButton("dec");
		screen = new JTextField("0");
		buttonPanel = new JPanel();
	}

	public static void main(String[] args) {
		new CalcGUI().createAndDisplayGUI();
	}

	/**
	 * Creates panel layout and sets up layout design.
	 */
	public void createAndDisplayGUI() {
		// Create panel and set layout
		this.add(buttonPanel);
		buttonPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		// Display screen
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 4;
		screen.setEnabled(false);
		screen.setHorizontalAlignment(JTextField.RIGHT);
		screen.setDisabledTextColor(Color.BLACK);
		buttonPanel.add(screen, constraints);

		// Top row buttons
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		buttonPanel.add(decButton, constraints);
		constraints.gridx = 1;
		constraints.gridy = 1;
		buttonPanel.add(allClearButton, constraints);
		constraints.gridx = 2;
		constraints.gridy = 1;
		buttonPanel.add(clearButton, constraints);
		constraints.gridx = 3;
		constraints.gridy = 1;
		buttonPanel.add(addButton, constraints);

		// Number buttons
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridheight = 3;
		buttonPanel.add(oneButton, constraints);
		constraints.gridx = 1;
		constraints.gridy = 2;
		buttonPanel.add(nButton, constraints);
		constraints.gridx = 2;
		constraints.gridy = 2;
		buttonPanel.add(zeroButton, constraints);

		// Right-hand column buttons
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 3;
		constraints.gridy = 2;
		constraints.gridheight = 1;
		buttonPanel.add(subtractButton, constraints);
		constraints.gridx = 3;
		constraints.gridy = 3;
		buttonPanel.add(multiplyButton, constraints);
		constraints.gridx = 3;
		constraints.gridy = 4;
		buttonPanel.add(divideButton, constraints);

		// Last row buttons
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 3;
		buttonPanel.add(equalsButton, constraints);
		constraints.gridx = 3;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		buttonPanel.add(negButton, constraints);

		// Display components and attach listeners
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.attachListeners();
		this.pack();
		this.setVisible(true);
	}

	/**
	 * Attaches appropriate listeners to buttons. Dec function converts current
	 * BT to an integer.
	 */
	public void attachListeners() {
		// Convert and clear buttons
		decButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (!currentOperand.equals("ERROR"))
					screen.setText(Integer.toString(BTOperations
							.btToInt(currentOperand)));
			}
		});
		allClearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				allClear();
			}
		});
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				clear();
			}
		});

		// Center trit buttons
		oneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				checkFirst();
				screen.setText(currentOperand += "1");
			}
		});
		nButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				checkFirst();
				screen.setText(currentOperand += "N");
			}
		});
		zeroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				checkFirst();
				screen.setText(currentOperand += "0");
			}
		});

		// Operator buttons
		multiplyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				multiply();
			}
		});
		divideButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				divide();
			}
		});
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				add();
			}
		});
		subtractButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				subtract();
			}
		});

		// Negate and equals buttons
		negButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				negate();
			}
		});
		equalsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				equal();
			}
		});
	}

	/**
	 * Resets the calculator.
	 */
	public void allClear() {
		prevOperation = "";
		prevOperand = "";
		needRefresh = false;
		operatorSet = false;
		clear();
	}

	/**
	 * Clears the current operand to 0.
	 */
	public void clear() {
		screen.setText(currentOperand = "0");
	}

	/**
	 * Multiplies the current operand by the current.
	 */
	public void multiply() {
		if (checkOperator())
			prevOperation = "x";
	}

	/**
	 * Divides the previous operand by the current.
	 */
	public void divide() {
		if (checkOperator())
			prevOperation = "÷";
	}

	/**
	 * Adds current operand to the previous.
	 */
	public void add() {
		if (checkOperator())
			prevOperation = "+";
	}

	/**
	 * Subtracts current operand from the previous.
	 */
	public void subtract() {
		if (checkOperator())
			prevOperation = "-";
	}

	/**
	 * If previous operation did not end in error, negates current operand.
	 */
	public void negate() {
		if (!currentOperand.equals("ERROR")) {
			currentOperand = BTOperations.negate(currentOperand);
			screen.setText(currentOperand);
		}
	}

	/**
	 * If previous operand was set, refreshes calculator screen for new trit
	 * sequence.
	 */
	public void checkFirst() {
		if ((currentOperand.equals("0")) || needRefresh) {
			screen.setText(currentOperand = "");
		}
		if (needRefresh) {
			needRefresh = false;
		}
	}

	/**
	 * If previous operation did not result in an ERROR, tries to execute
	 * previous operation.
	 * 
	 * @return True if previous operation did not result in error.
	 */
	public boolean checkOperator() {
		if (!currentOperand.equals("ERROR")) {
			equal();
			prevOperand = currentOperand;
			needRefresh = true;
			return operatorSet = true;
		}
		return false;
	}

	/**
	 * If an operator has been set, executes the last operation set. Throws
	 * error if division by zero. Clears operator if an operation is executed.
	 */
	public void equal() {
		if (operatorSet) {
			
			// Parse and evaluate previous operation entered
			if (prevOperation.equals("x")) {
				currentOperand = BTOperations.multiply(prevOperand,
						currentOperand);
			} else if (prevOperation.equals("÷")) {
				try {
					currentOperand = BTOperations.divide(prevOperand,
							currentOperand);
				} catch (java.lang.ArithmeticException e) {
					// Catch divide by 0 error
					currentOperand = "ERROR";
					prevOperation = "";
					operatorSet = false;
					needRefresh = true;
				}
			} else if (prevOperation.equals("+")) {
				currentOperand = BTOperations.add(prevOperand, currentOperand);
			} else if (prevOperation.equals("-")) {
				currentOperand = BTOperations.subtract(prevOperand,
						currentOperand);
			}

			// Update text field with result
			screen.setText(currentOperand);
			prevOperation = "";
			operatorSet = false;
		}
	}
}