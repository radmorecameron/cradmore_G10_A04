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

public class RomanNumeral {
	// INSTANCE VARIABLES
	private String romanNumeral;
	private int decimalNumber;
	private char prevChar=' ';
	
	// CONSTRUCTORS
	
	public RomanNumeral() {
		romanNumeral = "";
		decimalNumber = 0;
	}// RomanNumeral()

	public RomanNumeral(String num) {
		romanNumeral = num;
		decimalNumber = convertToDecimal(num);
	}// RomanNumeral(String)

	public RomanNumeral(int num) {
		decimalNumber = num;
		romanNumeral = convertToRomanNumeral(num);
	}// RomanNumeral(int)

	// ACCESSORS

	public String getRomanNumeral() {
		return romanNumeral;
	}// getRomanNumeral():String

	public int getDecimalNumber() {
		return decimalNumber;
	}// getDecimalNumber():int

	// MUTATORS

	public void setRomanNumeral(String newRom) {
		romanNumeral = newRom;
		decimalNumber = convertToDecimal(newRom);
	}// setRomanNumeral(String)

	public void setDecimalNumber(int num) {
		romanNumeral = convertToRomanNumeral(num);
		decimalNumber = num;
	}// setDecimalNumber():int

	private String convertToRomanNumeral(int num) {
		String romNum = "";
		if (num <= 0) {
			return "Error";
		}
		for (int i = 0; i < num / 1000; i++) 
			romNum = romNum + "M";
		num %= 1000;
		for (int i = 0; i < num / 500; i++) 
			romNum = romNum + "D";
		num %= 500;
		for (int i = 0; i < num / 100; i++) 
			romNum = romNum + "C";
		num %= 100;
		for (int i = 0; i < num / 50; i++) 
			romNum = romNum + "L";
		num %= 50;
		for (int i = 0; i < num / 10; i++) 
			romNum = romNum + "X";
		num %= 10;
		for (int i = 0; i < num / 5; i++) 
			romNum = romNum + "V";
		num %= 5;
		for (int i = 0; i < num / 1; i++) 
			romNum = romNum + "I";
		return romNum;
	}// convertToRomanNumeral():String
	
	private boolean checkIfAdd(char character) { //check if roman numeral is additive
		boolean value=false;
		if (prevChar=='M') {
			value=true;
		}
		if (prevChar=='D') {
			if (character!='M')
				value=true;
		}
		else if (prevChar=='C') {
			if (character!='M' && character!='D' )
				value=true;
		}
		else if (prevChar=='L') {
			if (character!='M' && character!='D' && character!='C' )
				value=true;
		}
		else if (prevChar=='X') {
			if (character!='M' && character!='D' && character!='C' && character!='L' )
				value=true;
		}
		else if (prevChar=='V') {
			if (character!='M' && character!='D' && character!='C' && character!='L' && character!='X' )
				value=true;
		}
		else if (prevChar=='I') {
			if (character=='I')
				value=true;
		}
		else if (prevChar==' ') {
			prevChar=character;
			value = true;
		}
		return value;
	}//checkIfAdd():Character
	
	private int convertToDecimal(String romNum) {
		int num = 0;
		int count = 0;
		while (count< romNum.length()) {
			if (checkIfAdd(romNum.charAt(count))) {
				if (romNum.charAt(count) == 'M') {
					num += 1000;
				} else if (romNum.charAt(count) == 'D') {
					num += 500;
				} else if (romNum.charAt(count) == 'C') {
					num += 100;
				} else if (romNum.charAt(count) == 'L') {
					num += 50;
				} else if (romNum.charAt(count) == 'X') {
					num += 10;
				} else if (romNum.charAt(count) == 'V') {
					num += 5;
				} else if (romNum.charAt(count) == 'I') {
					num += 1;
				} else {
					return -1;
			}
		}
			else {
				romanNumeral="Error:NotInOrder";
				count=romNum.length();
			}
			count++;
		}
		return num;
	}// convertToDecimal():int

}//RomanNumeral
