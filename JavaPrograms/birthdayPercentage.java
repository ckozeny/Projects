// This program creates and counts the different birthdays of the population of the CHI Health center.
// Each person is assigned a random birthday and the highest and lowest count are displayed at the end.

import java.util.Random;
public class birthdayPercentage { 
    public static void main ( String args[] ){
    
		Random randomNumbers = new Random();		// Random numbers being used for birthdays  
    	int[] countArray = new int[366];			// int array with space for each day in the year
    	
		int smallest = 1000;						// Start with arbitrarily high number
    	int largest = countArray[0];				// Start with the largest being the first element
    	
		int[] Birthdays = new int[18975];			// Each patron has their own birthday
		int largestPosition = 0, smallestPosition = 0;			// ints for the position of highest and lowest amounts
		double percentage = 0;
       
    	System.out.println ("\n\tThe CHI Health Center in Omaha, Nebraska has a capacity of 18,975 patrons." 
							+ "\n\tThe probability that each has a birthday on a specific day is listed below."
							+ "\n\tA list of occupants in the CHI Health Center and their birthdays:\n");
        
		for (int i = 0; i<Birthdays.length; i++)		// Loop through all of the 18,975 birthdays
		{	
            Birthdays[i]= randomNumbers.nextInt(366);}	// Assign each a birthday from 1-365

            for (int r : Birthdays)						// Loop through the birthdays and count them up
            	countArray[r]++;
            
            for (int x = 1; x < countArray.length; x++) // Loop through the 365 days and print the amounts
			{
                percentage = countArray[x]/18975.0;    	// Each loop store the otal percentage of the population
				if (x > 99)
				{
					System.out.print("\t");
					if (x > 199)						
					{									// Add tabs for each 100 days for readability
						System.out.print("\t");
						if (x > 299)
							System.out.print("\t");
					}
				}

				System.out.printf("Day %d: %d  people - %.4f%% of the total.\n", x, countArray[x], percentage);
			}
            
            for (int y=1; y<countArray.length; y++)		// Loop through each count of birthdays
			{
                if (countArray[y] > largest)			// Search for the largest count element and set
                {
				    largest = countArray[y];
					largestPosition = y;						// Set the position of the largest element found
                }
				if (countArray[y] < smallest)			// Search for the smallest count element and set
                {
				    smallest = countArray[y];
					smallestPosition = y;						// Set the position of the smallest element found
				}
            }
            // Print the final count results
            System.out.printf ("\n\tThe day that the most people have a birthday on is day %d with %d birthdays.\n", largestPosition, largest);
            System.out.printf ("\tThe day that the least amount of people have a birthday on is day %d with %d birthdays.\n\n", smallestPosition, smallest);
	}
}
