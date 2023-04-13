package wheel_game;

// Programmer : Nathalea Evans & Kyle Williams
// Group Members:
//                  Nathalea Evans - 2101707
//                  Lamar Smith -
//                  Kyle Williams - 2111227
// Start Date : Mar 25, 2023
// Due Date : Apr 14, 2023
// Project : Data Structure Group Project - Wheel of Fortune

// Imports

import java.util.Scanner;

public class GameplayDriver { // Start Program Execution
    public static void main(String[] args){

        // Initialization
        Manager management = new Manager(); // Create an instance of the game
        ContestantCircularList playersTakeTurns = new ContestantCircularList();
        Wheel theWheel = new Wheel();

        int choice;
        int rounds = 0;
        boolean exit_game = false;
        Scanner scan = new Scanner(System.in);

        // Game processing
        mainMenu();
        if (scan.nextInt() == 0){
            exit_game = true;       // end game before it starts

        } else{     //continue to game initialization
            System.out.println("How many players will play this game? ");
            int players = scan.nextInt();
            int counter = 1;
            while (counter != players) {       // add players until we reach the number we want to add
                System.out.println("Player " + counter + ", enter your name: ");
                Contestant contestant = new Contestant(counter, 0, scan.next(), 0);
                playersTakeTurns.addContestantToCircularList(contestant);
            }
        }


        // Start of game
        while(!exit_game){

            playersTakeTurns.takeTurn();

            // Play again or terminate game
            if(rounds == 3) {
                System.out.println("Do you want to 1-play again or 2-exit the game? Type and enter 1 or 2.");
                if (scan.nextInt() == 1) {
                    rounds = 0;     // restart rounds as the counter
                } else {
                    exit_game = true;
                }
            }
            rounds++;
        }
    }

    public static void mainMenu(){
        System.out.println("\t--------------------------------------------------------------------");
        System.out.println("\t|                   GAME SHOW NETWORK PRESENTS                     |");
        System.out.println("\t|                        WHEEL OF FORTUNE                          |");
        System.out.println("\t--------------------------------------------------------------------\n");
        System.out.println("\t--------------------------------------------------------------------");
        System.out.println("\t|                       >>>__Main Menu__<<<                        |");
        System.out.println("\t--------------------------------------------------------------------\n\n");
        System.out.println("|\t\tEnter 1 to start game.");
        System.out.println("|\t\tEnter 0 to exit.\n");
        System.out.println("|\t\tYour Choice: ");
    }
}
