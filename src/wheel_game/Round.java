package wheel_game;

// Programmer : Kyle Williams
// Date : April 12, 2023
// Guess Class

import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Round {
    // Attributes
    private String category;
    private String[] puzzle;    // because in this case puzzle is an attribute, we did it as an array instead of singly linked list
    private GuessQueue lettersGuessed;

    // Default Constructor
    Round(){
        category = "";
        puzzle = new String[20];
        lettersGuessed = null;
    }

    // Primary Constructor
    public Round(String category, String[] puzzle) {
        this.category = category;
        this.puzzle = puzzle;
    }

    //Copy Constructor
    public Round(Round round){
        this.category = round.category;
        this.puzzle = round.puzzle;
    }

    // Getters
    public String getCategory() {
        return category;
    }

    public String[] getPuzzle() {
        return puzzle;
    }

    // Setters
    public void setCategory(String category) {
        this.category = category;
    }

    public void setPuzzle(String[] puzzle) {
        this.puzzle = puzzle;
    }

    // Solution Methods
    public Round readFromFile(String categorySearch){
        Round round = new Round();
        Scanner read = null;
        Scanner inFileStream = null;
        try {
            read = new Scanner(System.in);
            inFileStream = new Scanner(new File("RoundAttributes"));
            while (inFileStream.hasNext()) {
                round.category = inFileStream.next();
                int arrayTraverse = 0;
                while (inFileStream.next().contains(".")) {
                    round.puzzle[arrayTraverse] = String.valueOf(inFileStream);
                    inFileStream.next();
                    arrayTraverse++;
                }

                if(Objects.equals(round.category, categorySearch)) {
                    return round;
                }
            }

            System.out.println("Item was not found");
            round = new Round();
            category = "";
            puzzle = new  String[20];
        } catch (FileNotFoundException e) {
            System.err.println("File could not be found");
        } catch (Exception e) {
            System.err.println("Exception Occured: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (read != null) {
                read.close();
            }
            if (inFileStream != null) {
                inFileStream.close();
            }
        }

        return null;
    }

    public boolean createFile() {
        try (FileOutputStream fos = new FileOutputStream("RoundAttributes")) {
            System.out.println("File created successfully.");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean solvePuzzle(String answer, ContestantCircularNode contestant, String category){

        if (contestant.getContestantInfo().getRoundTotal() != 0 || contestant.getContestantInfo().getGrandTotal() != 0){
            Round storageFromFile = readFromFile(category);
            int traverseArray = 0;
            String stringFromArray = "";

            while (storageFromFile.puzzle[traverseArray] != null){  // convert the array to string
                stringFromArray += puzzle[traverseArray];
            }

            // compare user's guess with the puzzle stored on file
            System.out.println("Type your guess to solve the puzzle: ");
            if (stringFromArray.equals(answer)){
                return true;
            }

        } else{
            System.out.println("Contestant has no money to solve the puzzle.");
        }

            return false;
    }

    public boolean tryToGuess(char guess, String category){
        Round fileOutput = new Round();
        fileOutput = fileOutput.readFromFile(category);

        // search for letter
        boolean verifyReturn = false;
        int count = 0;
        int count2 = 0;
        for (String word : fileOutput.puzzle) {
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess) {
                    lettersGuessed.enqueueLetter(guess);
                    verifyReturn = true;
                    // reveal all occurances
                    System.out.println("The letter " + guess + " appears " + count + " time/s in the " + count2 + " word");
                    count++;
                }
            }
            count2++;
        }
        if (count > 0 ) {
            System.out.println("The letter " + guess + " appears " + count + " times in the puzzle");
        }

        return verifyReturn;
    }

    public boolean buyVowel(char vowel){
        Round fileOutput = new Round();
        fileOutput = fileOutput.readFromFile(category);

        // search for letter
        for (String word : fileOutput.puzzle) {
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == vowel) {
                    if (lettersGuessed.searchForLetter(vowel) == true){
                        System.out.println("Letter not available");
                    } else{
                        lettersGuessed.enqueueLetter(vowel);
                        return true;
                    }
                    // communication with trackGuesses class
                }
            }
        }

        return false;
    }

    public String toString(){
        return "Category: " + category + "\nPuzzle: " + Arrays.toString(puzzle);
    }
}
