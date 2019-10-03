// This program takes a user validated decimal number and converts it to its respective octal representation.

import java.util.Scanner;

public class decimalToOctal {
         public static void main( String args[] ) {
			Scanner get = new Scanner( System.in );
            int userRequest = -1;							// Variable for user input 
        	inputHelpers helpers = new inputHelpers();		// Helper class to validate input
			
			while(userRequest < 0 || userRequest > 2097151) // Within this range or prompt again
			{
				System.out.print ("\nPlease enter a number from 0 to 2097151 to convert: ");
				userRequest = helpers.getIntInput();	
			}
		
			System.out.printf  ("\033[3m\n  The decimal number you entered, %d, is \033[0m", userRequest); 
			convertToOctal(userRequest);					// Concatenate string with result of method
			System.out.print("\033[3m converted to octal.\n\n\033[0m");
		}
		public static void convertToOctal (int userRequest)   // take user number and convert it to octal
		{
			int i = 0;
			int[] octalNumber = new int[90];				// Arbitrary length array for octal digits
			
			while (userRequest != 0)						// Continue decrementing until zero
			{
				octalNumber[i] = userRequest % 8;			// save remainder
				userRequest = userRequest / 8;				// divide without remainder
				i++;
			} 
			
			for (int j = i - 1; j >= 0; j--) 
			{
				System.out.print(octalNumber[j]);			// Value is stored backwards, so print backwards
			}	 
            
         }
}
