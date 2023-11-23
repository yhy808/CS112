/*
 * Wordle.java 
 * 
 * An console-based implementation of a popular word-guessing game
 * 
 * starter code: Computer Science 112 staff (cs112-staff@cs.bu.edu)
 *
 * completed by: Hongyi Yu
 */

import java.util.*;

public class Wordle {
    // the name of a file containing a collection of English words, one word per line
    public static final String WORD_FILE = "words.txt";

    /*
     * printWelcome - prints the message that greets the user at the beginning of the game
     */  
    public static void printWelcome() {
        System.out.println();   
        System.out.println("Welcome to Wordle!");
        System.out.println("The mystery word is a 5-letter English word.");
        System.out.println("You have 6 chances to guess it.");
        System.out.println();
    }
    
    /*
     * initWordList - creates the WordList object that will be used to select
     * the mystery work. Takes the array of strings passed into main(),
     * since that array may contain a random seed specified by the user 
     * from the command line.
     */
    public static WordList initWordList(String[] args) {
        int seed = -1;
        if (args.length > 0) {
            seed = Integer.parseInt(args[0]);
        }

        return new WordList(WORD_FILE, seed);
    }

    /*
     * readGuess - reads a single guess from the user and returns it
     * inputs:
     *   guessNum - the number of the guess (1, 2, ..., 6) that is being read
     *   console - the Scanner object that will be used to get the user's inputs
     */
    public static String readGuess(int guessNum, Scanner console) {
        String guess;
        do {
            System.out.print("guess " + guessNum + ": ");
            guess = console.next();
        } while (! isValidGuess(guess));

        return guess.toLowerCase();
    }

    /**** ADD YOUR METHODS FOR TASK 1 HERE ****/
    public static boolean includes(String s, char c) {
        boolean including = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                including = true;
            }
        }
        return including;
    }

    public static boolean isAlpha(String s) {
        boolean Alphabet = true;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isAlphabetic(s.charAt(i)) == false) {
                Alphabet = false;
            }
        }
        return Alphabet;
    }

    public static int numOccur(char c, String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }

    public static int numInSamePosn(char c, String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == c && s2.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }

    /*
     * TASK 2: Implement this method
     * 
     * isValidGuess -  takes an arbitrary string guess and returns true
     * if it is a valid guess for Wordle, and false otherwise
     */
    public static boolean isValidGuess(String guess) {
        boolean Valid = true;
        if (guess.length() != 5) {
            Valid = false;
            System.out.println("Your guess must be 5 letters long.");
        } else if (isAlpha(guess) == false) {
            Valid = false;
            System.out.println("Your guess must only contain letters of the alphabet.");
        }
        return Valid;
    }

    /**** ADD YOUR METHOD FOR TASKS 3 and 5 HERE. ****/
    public static boolean processGuess(String guess, String mystery) {
        boolean isCorrect = true;
        System.out.print("  ");
        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == mystery.charAt(i)) {
                System.out.print(guess.charAt(i) + " ");   
            } else if (includes(mystery, guess.charAt(i)) == true) {
                if (numInSamePosn(guess.charAt(i), guess, mystery) > 0) {  
                    if (numOccur(guess.charAt(i), guess) > numOccur(guess.charAt(i), mystery)) {
                        System.out.print("_" + " ");
                    } else {
                        System.out.print("[" + guess.charAt(i) + "]" + " ");
                    }
                } else {
                    if (numOccur(guess.charAt(i), guess.substring(0,i + 1)) <= numOccur(guess.charAt(i), mystery)) {
                        System.out.print("[" + guess.charAt(i) + "]" + " ");
                    } else {
                        System.out.print("_" + " ");
                    }
                }
                isCorrect = false;
            } else {
                System.out.print("_" + " ");
                isCorrect = false;
            }
        }
        System.out.println();
        return isCorrect;
    }

    
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        
        printWelcome();

        // Create the WordList object for the collection of possible words.
        WordList words= initWordList(args);

        // Choose one of the words as the mystery word.
        String mystery = words.getRandomWord();
        
        /*** TASK 4: Implement the rest of the main method below. ***/
        boolean gotItRight = false;
        for (int i = 1; i <= 6; i++) {
            String guess = readGuess(i, console);
            if (processGuess(guess, mystery) == true) {
                System.out.println("Congrats! You guessed it!");
                gotItRight = true;
                break;   
            }
        }
        if (gotItRight == false) {
            System.out.println("Sorry! Better luck next time!");
            System.out.println("The word was " + mystery + ".");
        }
        console.close();
    }
}