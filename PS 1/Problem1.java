/*
* Problem1.java
*
* A program with lots of syntax errors!
*/
 
import java.util.*;
 
public class Problem1 {
   /*
    * This static method should take an integer x and return:
    *    - the opposite of x when x is negative
    *    - 10 more than x when x is non-negative and even
    *    - the unchanged value of x when x is non-negative and odd
    */
   public static int adjust(int x) {
       if (x < 0) {
           return -1 * x;
       } else if (x % 2 == 0) {
           return x += 10;
       } else {
           return x;
       }
   }
 
   public static void main(String[] args) {
       Scanner console = new Scanner(System.in);
 
       System.out.print("Enter an integer x: ");
       int x = console.nextInt();
 
       System.out.println("adjust(x) = " + adjust(x));
   }
}
