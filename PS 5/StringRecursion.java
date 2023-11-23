/*
 * StringRecursion.java
 * 
 * Problem 6: Recursion and strings
 * 
 * created by: Hongyi Yu; yuhy@bu.edu
 */


public class StringRecursion {

    // use recursion to print the individual characters in the string str, separated by spaces.
    public static void printWithSpaces(String str) {
        if (str == null || str.equals("")) {     // base case if str is null or an empty string
            System.out.println();
            return;
        } else {                                 // recursive case
            System.out.print(str.charAt(0) + " ");
            printWithSpaces(str.substring(1));
        } 
    }

    // take a string str and use recursion to create and return a “reflected” version of the string in which 
    // the original string is followed by the characters of the string in reverse order.
    public static String reflect(String str) {
        if (str == null || str.equals("")) {     // base case if str is null or an empty string
            return "";
        } 
        else {                                   // recursive case
            String rest = reflect(str.substring(1));
            return str.charAt(0) + rest + str.charAt(0);
        }
    }

    // take two strings str1 and str2 and use recursion to determine and return the number of differences between the two strings.
    public static int numDiff(String str1, String str2) {
        if (str1.equals("")) {                   // base case if the length of str2 is greater or equal to str1
            return str2.length();
        }
        if (str2.equals("")) {                   // base case if the length of str1 is greater or equal to str2
            return str1.length();
        } else {                                 // recursive case
            int rest = numDiff(str1.substring(1), str2.substring(1));
            if (str1.charAt(0) != str2.charAt(0)) {
                return 1 + rest;
            } else {
                return rest;
            }
        }
    }

    // use recursion to find and return the index of the first occurrence of the character ch in the string str, or -1 
    // if ch does not occur in str.
    public static int indexOf(char ch, String str) {
        if (str == null || str.equals("")) {      // special case if str is null or an empty string
            return -1;
        } 
        if (ch == str.charAt(0)) {                // base case
            return 0;
        } else {                                  // recursive case
            int rest = indexOf(ch, str.substring(1));     
            if (rest == -1) {
                return -1;
            } else {
                return 1 + rest;
            }
        }
    }

    // Testing the code 
    public static void main(String[] args) {
        printWithSpaces("space");
        System.out.println(reflect("method"));
        System.out.println(reflect("abc"));
        System.out.println(numDiff("alien", "allen"));
        System.out.println(numDiff("alien", "alone"));
        System.out.println(numDiff("same", "same"));
        System.out.println(numDiff("same", "sameness"));
        System.out.println(numDiff("some", "sameness"));
        System.out.println(numDiff("abc", ""));
        System.out.println(indexOf('b', "Rabbit"));
        System.out.println(indexOf('P', "Rabbit"));
    }
}
