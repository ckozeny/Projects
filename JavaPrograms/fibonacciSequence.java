// This is a program that prompts the user for which number of the fibonacci sequence they would like, and
// prints the sequence up to that point.
//
// Run the program by first compiling with "javac fibonacciSequence" followed by "java fibonacciSequence"

import java.util.Scanner;

public class fibonacciSequence
{
    public static void main(String[]args) 
	{
        Scanner get = new Scanner(System.in);				// Create new scanner			
        int userRequest = 0;								// Integer for user input
		inputHelpers helpers = new inputHelpers();      	// Helper class to validate input	
        
		while(userRequest < 1 || userRequest > 70) 			// Only accept within this range
		{
            System.out.print("\nPlease choose a Fibonacci number you would like to know (1-70): ");
 			userRequest = helpers.getIntInput();       	
        }
		
		// Final print statement with a call to the legwork function
        System.out.printf("\n\n\tFibonacci sequence number %d is: %.0f\n\n", userRequest, fibonacciCalculate((userRequest - 1)));
    
	}

	// Function that calculates and prints the fibonacci sequence requested by the user
    public static double fibonacciCalculate(int userRequest)
    {
        double num1 = 0, num2 = 1, num0;
		
		System.out.print("\n0 "); 			// Print base number of Fibonacci
		
		if (userRequest == 0)
		{	
			return num1;					// Base case, return first number
		}
        	
		for(int fibo = 2; fibo <= userRequest; fibo++)
		{			
			System.out.printf("%.0f ", num2);			// Print each number before operations
			num0 = num1 + num2;
        	num1 = num2;								// Fibonacci algorithm
            num2 = num0;
		
		}

		System.out.printf("%.0f", num2);				// Print final number before returning
		return num2;									// Return final Fibonacci number
    }
}
 
