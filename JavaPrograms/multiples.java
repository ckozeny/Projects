// This program takes in two verified variables and compares them.
// The program outputs if they are multiples, and if they're odd or even.

import java.util.Scanner;

public class multiples {
 	public static void main( String args[] ) {
		int number[] = new int[2];				// To hold the 2 user input numbers		
 		number[0] = number[1] = -1;				// Default values

		System.out.println("");					// Main Menu
		System.out.println("Hello! Below, please input two numbers. This program"
							+ "\nwill determine if the second number is a multiple"
							+ "\nof the first number, and determine if the numbers"
							+ "\nprovided are odd or even.\n");
  
		while (number[0] < 0) {					// Base case to start loop
	    	for (int j = 0; j < 2; j++) {		// Loop and get 2 numbers from user
				String turn = " ";
				if (j == 1)						// If it's the second prompt, add to print statement
					turn = " second ";

				System.out.print ("\tPlease enter a" + turn + "number: ");
    			number[j] = tryIntInput();		// Get input and validate it
				
				if (number[0] == -1) {			// Bad value input for number[0]
					incorrectInput();	
					break;
				} else if (number[1] == -1 && j == 1) {		// Bad value input for number[1]
					j = 0;									// Loop will continue at j = 1 and ask again
					incorrectInput();					
					continue;
				}
			}
		}
		System.out.println("");

 		String isOrIsnt = " ";					// String to add to print statement if "not"
        if (number[0] % number[1] != 0 )
           	isOrIsnt = " not ";					// Numbers are not multiples
        
		System.out.printf ("\t%d is" + isOrIsnt + "a multiple of %d", number[0], number[1]);
		
		String evenOrOdd = "odd.";				// Base case of "odd" for output statement
		for (int i = 0; i < 2; i++) {
			if ( number[i] % 2 == 0) 
				evenOrOdd = "even.";			// If the number is even, change print output

		 	System.out.printf("\n\tThe number you entered, %d, is " + evenOrOdd, number[i]);
		}
	
		System.out.println("\n");
	}
	
	public static int tryIntInput() {                   // Test that input values are integers
            Scanner get = new Scanner(System.in);
            int userRequest = -1;

            if (get.hasNextInt()) {
                userRequest = get.nextInt();            // Get input and validate that it is within constraints
            } else
                get.next();

            return userRequest;                         // Return result or -1 for bad input
    }
	
	public static void incorrectInput() {				// Global function for input error message
		System.out.print("Incorrect input. Please try again.\n");
	}

}        
