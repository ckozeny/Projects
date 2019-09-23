// This drives the below programs and acts as a menu, before calling them to do their own conversion.
// octalToDecimal.java, decimalToOctal.java
// fibonacciSequence.java, birthdayPercentage.java

import java.util.Scanner;
import java.lang.*;

public class programMainMenu {
	public static void main(String args[]) {
		octalToDecimal otd = new octalToDecimal();			
		decimalToOctal dto = new decimalToOctal();				// New instances of child programs
		birthdayPercentage bp = new birthdayPercentage();
		fibonacciSequence fs = new fibonacciSequence();

		int userRequest, YorN;							// Request from user and a Yes or No value for menu
        userRequest = YorN = -1;
		
		System.out.print("\n  Hi there! Here are a sample of some small programs i've written.");
        
		while(userRequest < 1 || userRequest > 2)
        {
			printMenu(1);								// Print main menu	
			userRequest = tryIntInput();				// Test that input is an integer
			
			if (userRequest == 1) {						// Math conversion option chosen
					userRequest = -1;
					while (userRequest < 1 || userRequest > 2) {
						printMenu(11);					// Print sub-menu of Math conversions
						userRequest = tryIntInput();	// Test input is an integer
					}
				
					if (userRequest == 1) {				// Octal to decimal
           				otd.main(null);
       				} else if (userRequest == 2) {		// Decimal to octal
           				dto.main(null);
					}

			} else if (userRequest == 2) {				// Other programs section chosen
					userRequest = -1;
					while (userRequest < 1 || userRequest > 2) {
						printMenu(12);					// Print sub-menu of other programs
						userRequest = tryIntInput();	// Test for integer input
					}
				
					if (userRequest == 1) {				// Birthday percentages
						bp.main(null);
					} else if (userRequest == 2) {		// Fibonacci sequence
						fs.main(null);
					}
			}
	
			while (YorN != 0 || YorN != 1) {
				System.out.print("\033[1mWould you like to go back to the main menu (y/n)? \033[0m");	
				YorN = validateYorN();					// String validator to ensure a y or n
			
				if (YorN == 0) {						// Answer was yes
					userRequest = -1;
					break;
				} else if (YorN == 1) {					// Answer was no
					System.out.print("\033[3m\n\tThanks for trying my programs!\n\n\033[0m");	
					System.exit(0);						// Exit program
				} else
					continue;							// Wrong input
			}
		}
	}
	
	public static void printMenu(int menuChoice) {		// Menus method to reduce clutter in main()
		if (menuChoice == 1) {	
			System.out.print("\n\n\tThe options are listed below:"
								+ "\n\n\t1. Math Conversions\n\t2. Other Programs"
								+ "\n\n\t\tYour Choice: ");
		} else if (menuChoice == 11) {
			System.out.print("\n\n\tMath Conversion Programs:"
								+ "\n\n\t1. Octal to Decimal Conversion"
								+ "\n\t2. Decimal to Octal Conversion"
								+ "\n\n\t\tYour Choice: ");
		} else if (menuChoice == 12) {	
			System.out.print("\n\n\tOther Available Programs:"
								+ "\n\n\t1. Birthday Probabilities"
								+ "\n\t2. Fibonacci Calculator"
								+ "\n\n\t\tYour Choice: ");
		}
	}
           
	public static int validateYorN() {					// Validate the user string is a y or n
		Scanner get = new Scanner(System.in);
		String menu = get.nextLine();					// Buffer for input
			
		if (menu.compareTo("y") == 0)					// Main menu
			return 0;
		else if (menu.compareTo("n") == 0) {			// End program
			return 1;
		} else											// Wrong input
			return -1;
	}
	public static int tryIntInput() {					// Test that input values are integers
			Scanner get = new Scanner(System.in);
			int userRequest = -1;
			
			if (get.hasNextInt()) { 
                userRequest = get.nextInt(); 			// Get input and validate that it is within constraints
            } else
                get.next();
           
			return userRequest;							// Return result or -1 for bad input
        }
}
