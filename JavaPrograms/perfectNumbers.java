// This program tests for a "perfect" number, where all of it's divisors add
// up to the number itself. Ex: (28) 1 + 2 + 4 + 7 + 14 = 28

import java.util.Scanner;
public class perfectNumbers
{
    public static void main(String [] args)
    {
        int userRequest, perfectNumber, numDone;							// Initialize input and counter variables
		userRequest = numDone = 0;
		perfectNumber = -1;
		inputHelpers helpers = new inputHelpers();							// Helper to validate input
	
		System.out.println("\nHello! This program takes your input and confirms"
							+ "\nwhether it is a perfect number or not. A perfect"
							+ "\nnumber exists when all of its multiples add up"
							+ "\nto the number itself. (ex: 1 + 2 + 3 = 6)\n");

        while (userRequest < 1 || userRequest > 25) 						// Input is within bounds
		{ 
			System.out.print("\tHow many numbers would you like to try (1-25)?: ");
			userRequest = helpers.getIntInput();										// Validate input with helper function
		}
		
		while (numDone < userRequest)										// Loop as many times as the user requests
        {	
			perfectNumber = -1;
			while (perfectNumber < 0 || perfectNumber > 10000)				// Input is within bounds
			{
				System.out.print("\n\tEnter the number you would like to try (1-10000): ");
        		perfectNumber = helpers.getIntInput();									// Validate input with helper function
			}

			if (testPerfect(perfectNumber, userRequest))					// Test for perfection
            	printFactors(perfectNumber, true);  						// If perfect, print factors
			else
				printFactors(perfectNumber, false);							// If not perfect, print sorry message
			
			numDone++;           											// Iterate until count is exhausted
    	}
    } 

    public static boolean testPerfect(int perfectNumber, int userRequest)
    {
        int sum = 0;                                 
        for(int value = 1; value <= (perfectNumber / 2); value++)			// Lowest divisor will be 2, only half iterations needed
		{
            if(perfectNumber % value == 0) 									// Test that all divisors add up to total
                sum += value;
        }

        if(sum == perfectNumber)
            return true;													// Perfect number
        else
            return false;													// Not perfect
    }

    public static void printFactors(int perfectNumber, boolean perfect)
    {  
		if(!perfect)
            System.out.printf("\n\t\tSorry, but %d is not a perfect number.\n\n", perfectNumber);

        if(perfect)										
        {
            System.out.printf("\n\t\tSuccess! %d is a perfect number.\n\t\tThe factors are:\n\t\t\t ", perfectNumber);

            for(int factor = 1; factor <= (perfectNumber / 2); factor++)
            {
                if (perfectNumber % factor == 0)							// If its a clean multiple, print it
                {    
					System.out.printf("%d", factor);
				
					if (perfectNumber / 2 == factor)						// The last possible factor
						System.out.printf(" = %d\n", perfectNumber);
					else
						System.out.print(" + "); 							// Between factors for output
            	}
			}
        }
    }
}
 
