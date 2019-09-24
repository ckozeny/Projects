// This program tests for a "perfect" number, where all of it's divisors add
// up to the number itself. Ex: (28) 1 + 2 + 4 + 7 + 14 = 28

import java.util.Scanner;
public class ckozeny_Perfect
{
    public static void main(String [] args)
    {
        Scanner input = new Scanner(System.in);
        int maybe;
        int num, count = 0;
        do { System.out.print("How many numbers would you like to test?:");
            maybe = input.nextInt();
        }
        while(maybe < 1);
        do
        {
            System.out.print("Enter a possible perfect number: ");
            num = input.nextInt();
            testPerfect(num,maybe);
            count++;
            printFactors(num);  
        } while (count < maybe);
     }
    public static boolean testPerfect(int num, int maybe)
    {
        int sum = 0;                                 
        for(int value = 1; value < maybe ; value++){
            if(num % value == 0)
            {
                sum += value;
            }
        }
        if(sum == num)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static void printFactors(int num)
    {
        int divide;
        int sum = 0;

        for(int factor = num - 1; factor > 0; factor--)
        {
            divide = num % factor;

            if( divide == 0)
            {
                sum = sum+factor;
            }
        }
        if(sum != num)
        {
            System.out.printf("%d:NOT PERFECT\n",num);
        }
        if(sum == num)
        {
            System.out.printf("%d: ",num);

            for(int factor = 1; factor < num; factor++)
            {
                divide = num % factor;
                if(divide == 0)
                {
                    System.out.printf("%d \n",factor);
                }
            }
        }
        sum = 0;
    }
}
 
