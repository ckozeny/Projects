// Name :Christopher Kozeny 
// Class : 1400-002
// Program # : 7
// Due Date : February , 2015
//
// Honor Pledge: On my honor as a student of the University
// of Nebraska at Omaha, I have neither given nor received
// unauthorized help on this homework assignment.
//
// NAME:Christopher Kozeny
// EMAIL: ckozeny@unomaha.edu
// NUID: 178
// Colleagues: None.

import java.util.Scanner;

public class ckozeny_OctalConversion {
         public static void main( String args[] ) {
			Scanner get = new Scanner( System.in );
            int userRequest = -1;
        	
			while(userRequest < 0 || userRequest > 2097151) 
			{
				System.out.print ("\n\tPlease enter a number from 0 to 2097151 to convert:");
            	
				if (get.hasNextInt())
            	{                                   // Get input and validate that it is within constraints
                	userRequest = get.nextInt();
            	} else
            	{
                	get.next();
            	}
			}
		
			System.out.printf  ("\n\tThe decimal number you entered, %d, is ", userRequest); 
			convertToOctal(userRequest);
			System.out.print(" converted to octal.\n");
		}
		public static void convertToOctal (int userRequest) 
		{
			int i = 0;
			int[] octalNumber = new int[90];
			
			while (userRequest != 0)
			{
				octalNumber[i] = userRequest % 8;
				userRequest = userRequest / 8;
				i++;
			} 
			
			for (int j = i - 1; j >= 0; j--) 
			{
				System.out.print(octalNumber[j]);
			}	 
            
         }
}
