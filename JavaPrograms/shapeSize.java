// This program calculates the area and volumes of the below shapes based on the
// input from the user. The last prompt outputs the largest and smallest of the two. 

import java.lang.*;
import java.util.Scanner;

public class shapeSize {
    public static void main (String[] args){ 
        String questions[][] = new String[4][3];
		double answers[][] = new double[4][3];

		questions[0][0] = "Please enter the length of a side of your square: ";
		questions[0][1] = "Area of your square is:";
		questions[1][0] = "Please enter the length of a side of your cube: ";	
		questions[1][1] = "Volume of your cube is:"; 
		questions[1][2] = "Surface area of your cube is:";
		questions[2][0] = "Please enter the radius of your circle: ";
		questions[2][1] = "Area of your circle is:";
		questions[3][0] = "Please enter two numbers separated by spaces: ";
		questions[3][1] = "Larger of your two numbers is:";
		questions[3][2] = "Smaller of your two numbers is:";
		 
		System.out.print("\n");	
		for (int i = 0; i < 4; i++) {
			System.out.printf("%s", questions[i][0]);
			
			switch (i) {
				case 0:
					answers[0][0] = getValue();
					answers[0][0]  = Math.pow(answers[0][0], 2);
					break;
				case 1:
					answers[1][0] = getValue();	
					answers[1][1] = 6.0 * Math.pow(answers[1][0], 2);
					answers[1][0] = Math.pow(answers[1][0], 3);
					break;
				case 2:
					answers[2][0] = getValue();
					answers[2][0] = Math.PI * Math.pow(answers[2][0], 2);
					break;
				case 3:
					double[] parsedDbls = getString();

					answers[3][0] = parsedDbls[0];	
					answers[3][1] = parsedDbls[1];

					if (answers[3][0] == Math.min(answers[3][0], answers[3][1])) {
						answers[3][0] = answers[3][0] + answers[3][1];
						answers[3][1] = answers[3][0] - answers[3][1];					// Swap the variables
						answers[3][0] = answers[3][0] - answers[3][1];		
					}
					break;
			}
			
			for (int n = 1; n < 3; n++) {
				if (questions[i][n] != null) {
					System.out.printf("\t%s", questions[i][n]);
				}
				if (answers[i][n-1] != 0.0) {
					System.out.printf("\t%.3f\n", answers[i][n-1]);
				} 	
			}
			System.out.print("\n");
		} 
	}

	public static double getValue () {
		Scanner get = new Scanner (System.in);
		double userInput = -1;

		while (userInput < 1 || userInput > 1000) {	
			if (get.hasNextDouble())
				userInput = get.nextDouble();
			else
				get.next();
		}
		return userInput;
	}

	public static double[] getString () {
		Scanner get = new Scanner (System.in);	
		String numbers[] = new String[2];
		numbers[0] = numbers[1] = "0";

		while (true) {
			String userInput = get.nextLine();
		
			try {	
				numbers = userInput.split(" ");
				double parsedDbls[] = new double[numbers.length];
				for(int c = 0; c < numbers.length; c++) parsedDbls[c] = Double.parseDouble(numbers[c]);		
				return parsedDbls;
			} catch (Exception e) {	
				System.out.print("Incorrect input format. Please try again: ");
			}
		}
		
	}
}
