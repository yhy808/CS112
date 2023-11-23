/*
* ComputerPlayer.java
* by Hongyi Yu (yuhy@bu.edu)
* 2/20/2022
*
* A blueprint for objects that represent a computer player.
*
*/

import java.util.*;

public class ComputerPlayer extends Player {

    // a constructor which take the name of the player as a parameter and call the constructor of the superclass 
    // to do the actual work of initializing the inherited fields.
    public ComputerPlayer(String name) {
        super(name);
    }

    // a displayHand method that overrides the inherited version of that method. 
    // This version of the method should simply print the number of cards in the ComputerPlayer‘s hand.
    public void displayHand() {
        System.out.println(this.getName() +"'s hand:");
        if (this.getNumCards() == 1) {
            System.out.println("  " + this.getNumCards() + " card");
        } else {
            System.out.println("  " + this.getNumCards() + " cards");
        }
    }

    // a getPlay method that overrides the inherited version of that method. 
    // This version of the method should figure out if the computer has a card that matches the card at the top of the discard pile.
    public int getPlay(Scanner console, Card topDiscard) {
        int index = -1;
        int maxValue = -1;
        for (int i = 0; i < this.getNumCards(); i++) {
            if (this.getCard(i).matches(topDiscard) && this.getCard(i).getValue() > maxValue) {
                index = i;
                maxValue = this.getCard(i).getValue();
            }
        }
        return index;
    }
}
