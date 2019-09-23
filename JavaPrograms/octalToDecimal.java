// This program takes user input in the form of an octal number and converts it to decimal.
    
import java.util.Scanner;

public class octalToDecimal {
    public static void main ( String args[] )
    {
        int userRequest = 0;								// Integer for user input
        Scanner get = new Scanner (System.in);				// New scanner
		
		while(userRequest < 1 || userRequest > 10000000)          // Only accept within this range
        {
            System.out.print("\nPlease enter a number from 1 to 9999999 to convert: ");

            if (get.hasNextInt()) 
			{             						// Get input and validate that it is within constraints
                userRequest = get.nextInt();
            } else 
			{
                get.next();
            }
        }

        // Final print statement with a call to the legwork function
       	System.out.printf  ("\033[3m\n  The octal number you entered, %d, is %d converted to decimal.\n\n\033[0m", userRequest, convertToDecimal(userRequest));
    
    }
    // Function to convert the octal value into decimal and return the result 
    public static int convertToDecimal (int userRequest){
         int number = userRequest;
         int remainder = 0;
         int count = 0;							// Variables needed for computation
         int decimal = 0;

    	while(number > 0) {						// Number variable moves closer to zero as its evaluated
        	remainder = number % 10;
        	decimal = (int)(decimal + remainder * Math.pow(8, count));
        	number = number / 10;
			count++;
    	}
    	return decimal;
    }
}
