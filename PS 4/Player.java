/*
* Player.java
* by Hongyi Yu (yuhy@bu.edu)
* 2/19/2022
*
* A blueprint for objects that represent a single CardMatch player.
*
*/

import java.util.*;

public class Player {
    private String name;     // keep track of the player’s name 
    private Card[] hand;     // an array to hold the cards in the player’s hand
    private int numCards;       // keep track of how many cards are currently in the player’s hand

    // a constructor that takes a single parameter for the name of the player
    public Player(String name) {
        this.name = name;
        this.hand = new Card[CardMatch.MAX_CARDS];
        this.numCards = 0;
    }

    // an accessor that returns the player’s name.
    public String getName() {
        return this.name;
    }

    // an accessor that returns the current number of cards in the player’s hand.
    public int getNumCards() {
        return this.numCards;
    }

    // a toString method that just returns the player’s name
    public String toString() {
        return this.name;
    }

    // a mutator that takes a Card object as a parameter and adds the specified card to the player’s hand, filling the array from left to right.
    public boolean addCard(Card newCard) {
        if (newCard == null || this.numCards == this.hand.length) {
            throw new IllegalArgumentException();
        } else {
            this.hand[this.numCards] = newCard;
            this.numCards++;
            return true;
        }
    }

    // an accessor that takes an integer index as a parameter and returns the Card at the specified position in the player’s hand.
    public Card getCard(int position) {
        if (this.hand[position] == null) {
            throw new IllegalArgumentException();
        } else {
            return this.hand[position];
        }
    }

    // an accessor method that computes and returns the total value of the player’s current hand.
    public int getHandValue() {
        int value = 0;
        for(int i = 0; i < this.numCards; i++) {
            value += this.hand[i].getValue();
        }
        if (this.numCards == CardMatch.MAX_CARDS) {
            value += CardMatch.MAX_CARDS_PENALTY;
        }
        return value;
    }

    // an accessor method that prints the current contents of the player’s hand, preceded by a heading that includes the player’s name.
    public void displayHand() {
        System.out.println(this.name + "'s hand:");
        for (int i = 0; i < this.numCards; i++) {
            System.out.println("  " + i + ": " + this.hand[i]);
        }
    }

    // a mutator method that takes an integer index as a parameter and both removes and returns the Card at that position of the player’s hand.
    public Card removeCard(int position) {
        if (position >= this.numCards || position < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            Card remove = this.hand[position];
            this.hand[position] = this.hand[this.numCards-1];
            this.hand[this.numCards-1] = null;    
            this.numCards--;
            return remove;  
        }
    }

    // an accessor method that determines and returns the number corresponding to the player’s next play: 
    // either -1 if the player wants to draw a card, or the number/index of the card that the player wants to discard from his/her hand. 
    public int getPlay(Scanner console, Card topDiscard) {
        int value;
        do {
            System.out.print(this.name + ": number of card to play (-1 to draw)? ");
            value = console.nextInt();
        } while (value < -1 || value >= this.numCards);
        return value; 
    }
}
