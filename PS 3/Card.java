/*
 * Card.java
 * 
 * A blueprint class for objects that represent a single playing card 
 * for a game in which cards have both colors and numeric values.
 * 
 * starter code: CS 112 Staff (cs112-staff@cs.bu.edu)
 * completed by: Hongyi Yu
 */

public class Card {
    /* The smallest possible value that a Card can have. */
    public static final int MIN_VALUE = 0;
    
    /* The possible colors that a Card can have. */
    public static final String[] COLORS = {"blue", "green", "red", "yellow"};

    /* The possible values that a Card can have. */
    public static final int MAX_VALUE = 9;

    /* Put the rest of your class definition below. */
    public static boolean isValidColor(String color) {     // take the name of a color as a parameter, and it should return true if the specified color is valid
        boolean valid = false;
        for (int i = 0; i < COLORS.length; i++) {
            if (COLORS[i] == color) {
                valid = true;
            }
        }
        return valid;
    }

    // Define the fields
    private String color;
    private int value;

    // Implement mutators for the fields
    public void setColor(String otherColor) {     // takes a String representing a color and sets the value of the Card object’s color field to the specified color
        if (isValidColor(otherColor) == false) {
            throw new IllegalArgumentException();
        }
        this.color = otherColor;
    }

    public void setValue(int otherValue) {      // takes an integer and sets the value of the Card object’s value field to the specified number
        if (otherValue > MAX_VALUE || otherValue < MIN_VALUE) {
            throw new IllegalArgumentException();
        }
        this.value = otherValue;
    }

    // Implement a constructor
    public Card(String c, int v) {     // add a constructor that takes a string for the Card‘s color and an integer for the Card‘s value (in that order), and initializes the values of the fields
        this.setColor(c);
        this.setValue(v);
    }

    // Implement accessors for the fields
    public String getColor() {     // returns the string representing the Card object’s color
        return this.color;
    }

    public int getValue() {     // returns the integer representing the Card object’s value
        return this.value;
    }

    // Define the toString method
    public String toString() {     // returns a String representation of the Card object that can be used when printing it or concatenating it to a String
        return this.color + " " + this.value;
    }

    // Define methods for comparing Card objects
    public boolean matches(Card other) {     // takes another Card object as a parameter and returns true if the called Card object matches the color and/or value of the other Card object, and false if it doesn’t match either the color or the value
        return (other != null && (this.color == other.color || this.value == other.value));
    }

    public boolean equals(Card other) {      // takes another Card object as a parameter and determines if it is equivalent to the called object, returning true if it is equivalent and false if it is not equivalent
        return(other != null && this.color == other.color && this.value == other.value);
    }

}
    
