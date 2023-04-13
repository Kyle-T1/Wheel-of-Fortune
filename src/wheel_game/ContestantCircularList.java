package wheel_game;

// Programmer : Kyle Williams
// Date : April 10, 2023
// Contestant Class

import java.util.Scanner;

public class ContestantCircularList {
    private ContestantCircularNode head;
    private ContestantCircularNode tail;
    private int sizeOfList;

    // Default Constructor
    public ContestantCircularList(){
        head = null;
        tail = null;
    }

    // Utility Method
    public boolean isEmpty(){
        if (head == null && tail == null){
            return true;
        }
        return false;
    }

    // Solution Methods
    public void addContestantToCircularList(Contestant contestant){
        ContestantCircularNode newNode = new ContestantCircularNode();
        if(newNode != null){
            newNode.setContestantInfo(contestant);
            if (isEmpty()){
                head = newNode;
                tail = newNode;
                tail.setNextNode(head);
            } else{
                tail.setNextNode(newNode);
                tail = newNode;
                tail.setNextNode(head);
            }
            sizeOfList++;
        } else{
            System.err.println("Error. List is full, it can not add a new node. ");
        }
    }

    public void takeTurn(){
        // is the list empty?
        if (isEmpty()){
            System.err.println("list is empty");
            return;
        }

        // Initialization
        int player = 1;
        boolean nextPlayer = false;
        ContestantCircularNode current = head;
        Scanner read = new Scanner(System.in);
        Wheel wheel = new Wheel();
        wheel.addAllCards();    // game processing
        Round round = new Round();

        // Solution
        try{
            do{
                System.out.println("Player " + player + " Spin the wheel? type yes or no. Make sure your answer is spelled correctly.");
                if (read.hasNext("yes") || read.hasNext("Yes")){
                    wheel.spinWheel();
                    wheel.actionAfterLandsOn(current);
                    if(wheel.landsOn().getCardInfo().getType() == "Loose A Turn") {
                        current.setRoundTotal(0);
                        player++;
                        nextPlayer = true;
                        current = current.getNextNode();
                    }
                    read.reset();
                }

                if (nextPlayer == false) {  // loose a turn skip to the other player
                    System.out.println("Have a letter in mind? If yes then type the guess if not the type and enter no.");
                    char value = (char) read.nextByte();

                    if (read.hasNext("no") || read.hasNext("No")) {
                        read.reset();
                    } else {
                        int intFromScanner;
                        do {
                            round.tryToGuess(value, Manager.getCategory());
                            System.out.println("You still have your turn, you can (1) choose to spin again, (2) buy a vowel, or " +
                                    "(3) solve the puzzle. \nPlease type and enter the number corresponding to what you choose.");
                            intFromScanner = read.nextInt();

                            // gameplay for spin again
                            if (intFromScanner == 1) {
                                wheel.spinWheel();
                                wheel.actionAfterLandsOn(current);
                            }

                            // gameplay for buy a vowel
                            else if (intFromScanner == 2) {
                                System.out.println("What vowel do you choose?");
                                if (round.buyVowel((char) read.nextByte()) == false) {
                                    System.out.println("That was an incorrect guess, next player.");
                                    // skip to next player
                                    current = current.getNextNode();
                                }
                            }

                            // gameplay for solve the puzzle
                            else if (intFromScanner == 3) {
                                System.out.println("Type and enter to solve the puzzle, be sure to include any " +
                                        "apostrophes, comma’s, etc.");
                                if (round.solvePuzzle(read.next(), current, Manager.getCategory()) == false) {
                                    System.out.println("That was an incorrect guess, next player.");
                                    current.setRoundTotal(0);
                                    // skip to next player
                                    current = current.getNextNode();
                                } else {
                                    // keep money for that round
                                    System.out.println("You won this round " + current.getContestantInfo().
                                            getRoundTotal() + "was added to your grand total.");
                                    current.setGrandTotal(current.getContestantInfo().getRoundTotal());
                                }
                            }
                        } while (intFromScanner == 1); // player will continue spining if choice is 1
                        player++;
                    }
                }
            } while (current != head); // list traversal only once

        } catch(Exception general){
            System.err.println("Something went wrong");
        }
    }

    public void findWinner(){
        if (isEmpty()) {
            System.out.println("Linked list is empty");
            return;
        }
        ContestantCircularNode current = head;
        ContestantCircularNode current2 = head;
        float highestGrandTotal = 0;
        float temp = 0;
        do {
            temp = current.getContestantInfo().getGrandTotal();
            do{
                if (current2.getContestantInfo().getGrandTotal() > temp){
                    highestGrandTotal = current2.getContestantInfo().getGrandTotal();
                }
                current2 = current2.getNextNode();
            } while (current2 != head);

            current = current.getNextNode();
        } while (current != head);

        current = head;
        do {
            if (highestGrandTotal == current.getContestantInfo().getGrandTotal()){
                break;
            }
            current = current.getNextNode();
        } while (current != head);
        System.out.println("Player " + current.getContestantInfo().getPlayerNum() + " IS THE GAME WINNER!!!");
    }

}
