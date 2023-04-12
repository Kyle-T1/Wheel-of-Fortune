package wheel_game;

// Programmer : Kyle Williams
// Date : April 12, 2023
// Guess Class

public class GuessQueue {
    // Attributes
    private GuessQueueNode front;
    private GuessQueueNode rear;
    int size;

    // Default Constructor
    GuessQueue(){
        this.front = null;
        this.rear = null;
        size = 0;
    }

    // Getters
    public GuessQueueNode getFront() {
        return front;
    }

    public GuessQueueNode getRear() {
        return rear;
    }

    public int getSize() {
        return size;
    }


    // Setters
    public void setFront(GuessQueueNode front) {
        this.front = front;
    }

    public void setRear(GuessQueueNode rear) {
        this.rear = rear;
    }

    public void setSize(int size) {
        this.size = size;
    }


    // Solution Methods
    public void enqueueLetter(char data) {
        GuessQueueNode newNode = new GuessQueueNode();
        newNode.setLetter(data);

        if (rear == null) {
            front = newNode;
        } else {
            rear.setNext(newNode);
        }
        rear = newNode;
        size++;
    }

    public void displayAllLetters() {
        if (front == null) {
            System.out.println("Queue is empty");
            return;
        }
        GuessQueueNode current = front;

        System.out.print("Letters guessed so far: ");

        while (current != null) {
            System.out.print(current.getLetter() + " ");
            current = current.getNext();
        }
        System.out.println();
    }

    public boolean searchForLetter(char data) {
        if (front == null) {
            return false;
        }
        GuessQueueNode current = front;

        while (current != null) {
            if (current.getLetter() == data) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
}
