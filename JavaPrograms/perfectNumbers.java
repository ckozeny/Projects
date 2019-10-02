// This program tests for a "perfect" number, where all of it's divisors add
// up to the number itself. Ex: (28) 1 + 2 + 4 + 7 + 14 = 28

import java.util.Scanner;
public class perfectNumbers
{
    public static void main(String [] args)
    {
        Scanner input = new Scanner(System.in);
        int userRequest, perfectNumber, numDone;
		userRequest = numDone = 0;
		perfectNumber = -1;

        while (userRequest < 1 || userRequest > 25) 
		{ 
			System.out.print("How many numbers would you like to test?:");
			userRequest = getValue();
		}
		
		while (numDone < userRequest)
        {	
			perfectNumber = -1;
			while (perfectNumber < 0 || perfectNumber > 10000)
			{
				System.out.print("Enter a number you would like to test: ");
        		perfectNumber = getValue();
			}
			testPerfect(perfectNumber, userRequest);
            printFactors(perfectNumber);  
			numDone++;           
        }
     }

	public static int getValue () {
        Scanner get = new Scanner (System.in);
        int userInput = -1;

        if (get.hasNextInt())
            userInput = get.nextInt();
        else 
            get.next();

        return userInput;
    }

    public static boolean testPerfect(int perfectNumber, int userRequest)
    {
        int sum = 0;                                 
        for(int value = 1; value < userRequest; value++){
            if(perfectNumber % value == 0) 
                sum += value;
        }

        if(sum == perfectNumber)
            return true;
        else
            return false;
    }

    public static void printFactors(int perfectNumber)
    {
        int divide;
        int sum = 0;

        for(int factor = (perfectNumber - 1); factor > 0; factor--)
        {
            if(perfectNumber % factor == 0)
            	sum = sum + factor;
        }
        
		if(sum != perfectNumber)
            System.out.printf("\n\tSorry, but %d is not a perfect number.\n\n", perfectNumber);

        if(sum == perfectNumber)
        {
            System.out.printf("\n\tSuccess! %d is a perfect number.\n\tThe factors are:\n\t\t ", perfectNumber);

            for(int factor = 1; factor < perfectNumber; factor++)
            {
                if (perfectNumber % factor == 0)
                {    
					System.out.printf("%d", factor);
				
					if (perfectNumber / 2 == factor)					// The last possible factor
						System.out.printf(" = %d\n\n", perfectNumber);
					else
						System.out.print(" + "); 
            	}
			}
        }
    }
}
 
