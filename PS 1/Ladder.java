/*
 * Ladder.java
 * 
 * A program that allows a user to precompute the required length of the ladder
 * to avoid adjusting the ladder once it’s in the air.
 * 
 * Completed by: Hongyi Yu
 * Email: yuhy@bu.edu
 */

import java.util.*;

public class Ladder {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("What is the height? ");
        int height = scan.nextInt();
        System.out.print("What is the angle? ");
        int angle = scan.nextInt();

        double radians;
        radians = (double)angle * Math.PI/180;
        double length;
        length = (double)height/Math.sin(radians);

        System.out.println("The required length is:");
        System.out.println(length + " feet");
        double yards = length/3;
        System.out.println(yards + " yards");
        double remainingLength = length - (int)yards * 3;
        System.out.println((int)yards + " yards and " + remainingLength + " feet");
    }
}