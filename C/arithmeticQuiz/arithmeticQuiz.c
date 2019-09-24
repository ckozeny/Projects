// This program asks the user for the amount of questions desired and the difficulty rating
// and creates a randomly generated equation per question and prints the final score.

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
int questionCounter = 1;
int amountCorrect;

// This method creates a question with random numbers within range and
// a random operator is chosen.

int genQuestion (int questionNumber, int difficultyNumber) {
    int num1, num2, op1;
    int answer = 0;

    srand(time(NULL));

    if (difficultyNumber == 1) {
        num1 = rand() % 10 + 1;
        num2 = rand() % 10 + 1;
    } else if (difficultyNumber == 2) {
        num1 = rand() % 50 + 1;
        num2 = rand() % 50 + 1;
    } else if (difficultyNumber == 3) {
        num1 = rand() % 100 + 1;
        num2 = rand() % 100 + 1;
    } else {
        num1 = (rand() % 200 + 1) - 100;
        num2 = (rand() % 200 + 1) - 100;
    }
    
    op1 = rand() % 4 + 1;       // Used to choose a random operator out of the 4 options
    if (op1 == 1) {
        printf("Question %d: %d + %d =\n", questionCounter, num1, num2);
        answer = num1 + num2;
    } else if (op1 == 2) {
        printf("Question %d: %d - %d =\n", questionCounter, num1, num2);
        answer = num1 - num2;
    } else if (op1 == 3) {
        printf("Question %d: %d * %d =\n", questionCounter, num1, num2);
        answer = num1 * num2;
    } else {
        if (num2 == 0) {
            int hold;       // If denominator of zero, flip the fraction before continuing
            hold = num1;
            num1 = num2;
            num2 = hold;
        }
        printf("Question %d: %d / %d =\n", questionCounter, num1, num2);
        answer = num1 / num2;
    }                       // end operation if/else statements        
    questionCounter++;
    return(answer);
}

// This method is used to accept the user input answer and compares it
// to the actual answer.

int answerQuestion (int answer) {
    int userAnswer;
    
    printf("Enter Answer: ");
    scanf("%d", &userAnswer);    
    
    if (userAnswer == answer) {         // Test correctness of input
        return(1);
    } else
        return(0);
}

// This method is used to print out a random response based on if the question
// was answered correctly.

void response (int tfValue) {
    int response;
    
    response = rand() % 3 + 1;          // Used to pick a random statement out of the 3 options
    if (tfValue) {
        if (response == 1) {
            printf("Way to go!\n");
        } else if (response == 2) {
            printf("Nice!\n");
        } else if (response == 3) {
            printf("Good job!\n");
        }
        amountCorrect++;
    } else {
        if (response == 1) {
            printf("Wrong answer!\n");
        } else if (response == 2) {
            printf("Sorry!\n");
        } else if (response == 3) {
            printf("Incorrect!\n");
        }
    }
}

// The main function drives the main operations and prompts of the program

int main () {
    int questions;
    int difficulty = 1;
    
    do {
        printf("How many questions for this test (1-20)? ");
        scanf("%d", &questions);
    } while (questions > 20 || questions < 1);
    
    do { 
        printf("Select Difficulty (1-4): ");
        scanf("%d", &difficulty);
    } while (difficulty > 4 || difficulty < 1);
    
    do {
        response(answerQuestion(genQuestion(questions, difficulty)));
    } while (questionCounter <= questions);

    if (questionCounter == questions + 1) {             // Print score if total amount of questions has been reached
        printf("Your score was %d/%d\n", amountCorrect, questions);
    }
    return(0);
}
