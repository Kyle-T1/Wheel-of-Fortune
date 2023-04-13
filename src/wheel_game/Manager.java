package wheel_game;

// Programmer : Nathalea Evans
// Contribution: Kyle Williams
// Date : Mar 28, 2023
// Manager Class

// Imports
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.*;

public class Manager {
    // Attribute
    private int round_num = 0;
    private int puzzles = 0;
    private int card =  0;
    private static String category;



    // Getters
    public int getRound_num() {
        return round_num;
    }

    // Setters
    public void setRound_num(int round_num) {
        this.round_num = round_num;
    }


    // Other Methods
    public void setCategory(){

        // we need the manager to input the category
        System.out.println("Catergory: ");
        Scanner read = new Scanner(System.in);
        category = read.next();



    }

    public static String getCategory(){
        return category;
    }
}
