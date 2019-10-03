// This drives the programs below and acts as a menu, before calling them to manage their own logic.

import java.util.Scanner;
import java.lang.*;
import java.awt.Toolkit;

public class mainMenu {
	public static void main(String args[]) {
		octalToDecimal otd = new octalToDecimal();			
		decimalToOctal dto = new decimalToOctal();				// New instances of child programs
		birthdayPercentage bp = new birthdayPercentage();
		fibonacciSequence fs = new fibonacciSequence();
		shapeSize ss = new shapeSize();
		multiples ms = new multiples();
		perfectNumbers pn = new perfectNumbers();
		inputHelpers helpers = new inputHelpers();				// Helpers to validate input

		int userRequest, YorN;									// Request from user and a Yes or No value for menu
        userRequest = YorN = -1;
		
		System.out.print("\n  Hi there! Here are a sample of some small programs i've written.\n");
        
		while(userRequest < 1 || userRequest > 3)
        {
			printMenu(1);										// Print main menu	
			userRequest = helpers.getIntInput();						// Test that input is an integer
			
			if (userRequest == 1) {								// Math conversion option chosen
				userRequest = -1;
				while (userRequest < 1 || userRequest > 5) {
					printMenu(11);								// Print sub-menu of Math conversions
					userRequest = helpers.getIntInput();				// Test input is an integer
				}
					
				switch (userRequest) {
					case 1:
						otd.main(null);							// octalToDecimal.java
						break;
					case 2:
						dto.main(null);							// decimalToOctal.java
						break;
					case 3:
						ss.main(null);							// shapeSize.java
						break;
					case 4:
						ms.main(null);							// multiples.java
						break;
					case 5:
						userRequest = -1;						// Go back a menu
						continue;
				}				

			} else if (userRequest == 2) {						// Other programs section chosen
					userRequest = -1;
					while (userRequest < 1 || userRequest > 4) {
						printMenu(12);							// Print sub-menu of other programs
						userRequest = helpers.getIntInput();			// Test for integer input
					}
				
					switch (userRequest) {
						case 1:
							bp.main(null);						// birthdayPercentages.java
							break;
						case 2:
							fs.main(null);						// fibonacciSequence.java
							break;
						case 3:
							pn.main(null);						// perfectNumbers.java
							break;
						case 4:
							userRequest = -1;					// Go back a menu
							continue;
					}		
			
			} else if (userRequest == 3) {
				exit();
			} else {
				userRequest = -1;
				continue;
			}
	
			while (YorN != 0 || YorN != 1) {
				Toolkit.getDefaultToolkit().beep();
				System.out.print("\033[1mWould you like to go back to the main menu (y/n)? \033[0m");	
				YorN = helpers.getYorN();							// String validator to ensure a y or n
			
				if (YorN == 0) {								// Answer was yes
					userRequest = -1;
					break;
				} else if (YorN == 1) {							// Answer was no
					exit();
				} else
					continue;									// Wrong input
			}
		}
	}
	
	public static void printMenu (int menuChoice) {				// Menus method to reduce clutter in main()
		if (menuChoice == 1) {	
			System.out.print("\n\tYour options are listed below. Use numbers to navigate:"
								+ "\n\t-------------------------------------------------------"
								+ "\n\n\t1. Math Programs"
								+ "\n\t2. Other Programs"
								+ "\n\t3. Exit"
								+ "\n\n\t\tYour Choice: ");
		} else if (menuChoice == 11) {
			System.out.print("\n\n\tMath Programs:"
								+ "\n\t--------------"
								+ "\n\n\t1. Octal to Decimal Conversion"
								+ "\n\t2. Decimal to Octal Conversion"
								+ "\n\t3. Geometry Calculator"
								+ "\n\t4. Multiple Calculator"
								+ "\n\t5. Go Back"
								+ "\n\n\t\tYour Choice: ");
		} else if (menuChoice == 12) {	
			System.out.print("\n\n\tOther Available Programs:"
								+ "\n\t-------------------------"
								+ "\n\n\t1. Birthday Probabilities"
								+ "\n\t2. Fibonacci Calculator"
								+ "\n\t3. Perfect Numbers"
								+ "\n\t4. Go Back"
								+ "\n\n\t\tYour Choice: ");
		}
	}
           	
	public static void exit () {
		System.out.print("\033[3m\n\tThanks for trying my programs!\n\n\033[0m");	
		System.exit(0);											// Exit program
	}
}
