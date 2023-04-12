package wheel_game;

// Programmer : Kyle Williams
// Date : April 12, 2023
// Guess Class
public class GuessQueueNode {
    private char letter;
    private GuessQueueNode next;

    public GuessQueueNode(){
        letter = ' ';
        next = null;
    }

    public GuessQueueNode(char letter, GuessQueueNode next) {
        this.letter = letter;
        this.next = next;
    }

    public GuessQueueNode(char data) {
        this.letter = data;
    }

    public GuessQueueNode(GuessQueueNode next){
        this.next = next;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public GuessQueueNode getNext() {
        return next;
    }

    public void setNext(GuessQueueNode next) {
        this.next = next;
    }
}
