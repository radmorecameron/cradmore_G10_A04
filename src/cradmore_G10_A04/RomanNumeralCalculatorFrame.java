/**
 * 
 */
package cradmore_G10_A04;

/**
 * @author 1923445
 * Cameron Radmore
 * December 2nd 2019
 * 420-G10
 * A03
 *The purpose of this program is to perform arithmetic on roman numerals
 **/
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class RomanNumeralCalculatorFrame extends JFrame implements ActionListener {

	/**
	 * @param args
	 */
	// Instance Variables

	private JLabel lblNumber = new JLabel("Enter filename (if not using romanNumerals.txt):");
	private JPanel inputPanel = new JPanel();
	private JPanel panel2Panel = new JPanel();

	private JTextField fldNumber = new JTextField(10);
	private JTextArea areaDisplay = new JTextArea(5, 10);
	private JButton btnCalculate = new JButton("Calculate Now");
	private RomanNumeral myRomanNumeral;
	// Constructor

	public RomanNumeralCalculatorFrame() {
		setTitle("Roman Numeral Calculator");
		getContentPane().add(inputPanel);
		inputPanel.add(lblNumber);
		inputPanel.add(fldNumber);
		panel2Panel.add(btnCalculate);
		areaDisplay.setFont(new Font("Monospaced", Font.PLAIN, 12));
		getContentPane().add(inputPanel, BorderLayout.NORTH);
		getContentPane().add(panel2Panel, BorderLayout.CENTER);
		getContentPane().add(areaDisplay, BorderLayout.SOUTH);
		btnCalculate.addActionListener(this);

	}// RomanNumeralCalculatorFrame()

	public static void main(String[] args) {
		RomanNumeralCalculatorFrame frame = new RomanNumeralCalculatorFrame();
		frame.setSize(500, 220);
		frame.setLocation(300, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}// Main

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCalculate) {
			String fileName;
			if (fldNumber.getText().isEmpty()) {
				fileName = "romanNumerals.txt";

			} else {
				fileName = fldNumber.getText();
			}
			readFile(fileName);
		}
	}// actionPerformed(ActionEvent):void

	public void readFile(String fileName) {
		areaDisplay.setText("");
		File myFile;
		Scanner inFile = null;
		try {
			myFile = new File(fileName);
			inFile = new Scanner(myFile);
			while (inFile.hasNextLine()) {
				String nxt = inFile.nextLine();
				String num1 = nxt.substring(0, nxt.indexOf(" "));
				String operand = nxt.substring(nxt.indexOf(' ') + 1, nxt.indexOf(' ') + 2);
				String num2 = nxt.substring(nxt.lastIndexOf(' ') + 1);
				myRomanNumeral = new RomanNumeral(num1);
				int intNum = myRomanNumeral.getDecimalNumber();
				if (myRomanNumeral.getRomanNumeral()=="Error:NotInOrder") {
					JOptionPane.showMessageDialog(this,num1 + " is not additive", "Invalid Roman Numeral",JOptionPane.ERROR_MESSAGE);
					inFile.close();
					return;//Exit method on error
				}
				if (intNum == -1) {
					num1="Invalid Roman Numeral";
				}
				areaDisplay.append(num1 + " (" + intNum + ") ");
				areaDisplay.append(operand);
				myRomanNumeral = new RomanNumeral(num2);
				int intNum2 = myRomanNumeral.getDecimalNumber();
				if (myRomanNumeral.getRomanNumeral()=="Error:NotInOrder") {
					JOptionPane.showMessageDialog(this,num2 + " is not additive", "Invalid Roman Numeral",JOptionPane.ERROR_MESSAGE);
					inFile.close();
					return;//Exit method on error
				}
				if (intNum2 == -1) {
					num2="Invalid Roman Numeral";
				}
				
				areaDisplay.append(" " + num2 + " (" + intNum2 + ") = ");
				if (intNum != -1 && intNum2 !=-1) {
					if (operand.equals("+")) {
						myRomanNumeral.setDecimalNumber(intNum + intNum2);
						if (myRomanNumeral.getRomanNumeral().equals("Error")) {
							JOptionPane.showMessageDialog(this,"Arithmetic yields a number that is less than or equal to 0", "Out of bounds",JOptionPane.ERROR_MESSAGE);
							areaDisplay.append(" (0)");
							inFile.close();
							return;//Exit method on error
						}
						areaDisplay.append(myRomanNumeral.getRomanNumeral() + " (" + (intNum + intNum2) + ")");
					}//end if 
				
					else if (operand.equals("/")) {
						myRomanNumeral.setDecimalNumber(intNum / intNum2);
						if (myRomanNumeral.getRomanNumeral().equals("Error")) {
							JOptionPane.showMessageDialog(this,"Arithmetic yields a number that is less than or equal to 0", "Out of bounds",JOptionPane.ERROR_MESSAGE);
							inFile.close();
							return;//Exit method on error
						}
						areaDisplay.append(myRomanNumeral.getRomanNumeral() + " (" + (intNum / intNum2) + ")");
					}//end else if 
					else if (operand.equals("*")) {
						myRomanNumeral.setDecimalNumber(intNum * intNum2);
						if (myRomanNumeral.getRomanNumeral().equals("Error")) {
							JOptionPane.showMessageDialog(this,"Arithmetic yields a number that is less than or equal to 0", "Out of bounds",JOptionPane.ERROR_MESSAGE);
							inFile.close();
							return; //Exit method on error
						}
						areaDisplay.append(myRomanNumeral.getRomanNumeral() + " (" + (intNum * intNum2) + ")");
					}//end else if 
					else if (operand.equals("%")) {
						myRomanNumeral.setDecimalNumber(intNum % intNum2);
						if (myRomanNumeral.getRomanNumeral().equals("Error")) {
							JOptionPane.showMessageDialog(this,"Arithmetic yields a number that is less than or equal to 0", "Out of bounds",JOptionPane.ERROR_MESSAGE);
							inFile.close();
							return; //Exit method on error
						} else {
							areaDisplay.append(myRomanNumeral.getRomanNumeral() + " (" + (intNum % intNum2) + ")");
						}
					}//end else if
					else if (operand.equals("-")) {
						if (myRomanNumeral.getRomanNumeral().equals("Error")) {
							JOptionPane.showMessageDialog(this,"Arithmetic yields a number that is less than or equal to 0", "Out of bounds",JOptionPane.ERROR_MESSAGE);
							inFile.close();
							return; //Exit method on error
						} else {
							myRomanNumeral.setDecimalNumber(intNum - intNum2);
							areaDisplay.append(myRomanNumeral.getRomanNumeral() + " (" + (intNum - intNum2) + ")");
						}
					}//end else if
				}
				else {
					areaDisplay.append("Invalid Roman Numeral (-1)");
				}
				areaDisplay.append("\n");
			}//end while
			inFile.close();

		}//try
		
		catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "\"" + fileName + "\" could not be found.", "File not found",
					JOptionPane.ERROR_MESSAGE);
			return; //Exit method on error
		} // catch (FileNotFoundException e)
		
		catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "I/O error.", JOptionPane.ERROR_MESSAGE);
			return; //Exit method on error
		} // catch (IOException)

	}// readFile(String):void
}// RomanNumeralCalculatorFrame
