import java.util.Scanner;

public class inputHelpers {
	public static int getIntInput() {
		Scanner get = new Scanner(System.in);
        int userRequest = -1;

        if (get.hasNextInt())
            userRequest = get.nextInt();            // Get input and validate that it is within constraints
        else
            get.next();

        return userRequest;
	}

	public static double getDoubleInput() {
		Scanner get = new Scanner (System.in);
        double userInput = -1;

        if (get.hasNextDouble())
            userInput = get.nextDouble();
        else
            get.next();
    
        return userInput;
	}

	public static int getYorN () {
		Scanner get = new Scanner(System.in);
		String menu = get.nextLine();                           // Buffer for input

        if (menu.compareTo("y") == 0 || menu.compareTo("Y") == 0)                           // Main menu
            return 0;
        else if (menu.compareTo("n") == 0 || menu.compareTo("N") == 0)                    // End program
            return 1;
        else                                                  // Wrong input
            return -1;
	}
}
