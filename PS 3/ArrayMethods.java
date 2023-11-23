/*
 * ArrayMethods.java
 * Problem 4: Array-processing methods
 *
 * Created by: Hongyi Yu
 * 
 */

import java.util.*;

public class ArrayMethods {
    public static final String[] DAY_NAMES = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    public static int getDayNum(String day) {      // takes a reference to a string and returns the index of that string in the array referred to by the class constant DAY_NAMES
        int dayNum = -1;
        for (int i = 0; i < DAY_NAMES.length; i++) {
            if (day != null && day.equalsIgnoreCase(DAY_NAMES[i])) {
                dayNum = i;
            }
        }
        return dayNum;
    }

    public static void swapNeighbors(int[] values) {     // takes a reference to an array of integers values and swaps pairs of elements that are “neighbors” of each other
        if (values == null) {
            throw new IllegalArgumentException();
        }

        int[] other = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            other[i] = values[i];
        }

        for (int i = 0; i < values.length-1; i += 2) {
            values[i] = other[i+1];
        }
        for (int i = 1; i < values.length; i += 2) {
                values[i] = other[i-1];
        }  
    }

    public static int[] copyWithCeiling(int[] values, int ceiling) {    // takes a reference to an array of integers values and an integer ceiling, and that creates and returns a new array based on values in which all elements greater than ceiling are replaced by the value ceiling
        if (values == null) {
            throw new IllegalArgumentException();
        }

        int[] other = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            other[i] = values[i];
        }

        for (int i = 0; i < values.length; i++) {
            if (other[i] > ceiling){
                other[i] = ceiling;
            }
        }
        return other;
    }

    public static int mostOccur(int[] arr) {      // takes a reference to a sorted array of integers and returns the value that occurs most often in the array
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException();
        }

        int maxCount = 1;
        int count = 1;
        int maxNum = arr[0];
        int temp = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == temp) {
                count++;
            } else {
                if (count > maxCount) {
                    maxCount = count;
                    maxNum = arr[i-1];
                }
                count = 1;
                temp = arr[i];
            }
        }
        return maxNum;
    }

    public static int find(int[] arr1, int[] arr2) {      // takes two arrays containing sequences of integers and that returns the index of the first occurrence of the first sequence in the second sequence, or -1 if the first sequence does not appear in the second sequence
        if (arr1 == null || arr1.length == 0) {
            throw new IllegalArgumentException();
        } else if (arr2 == null || arr2.length == 0) {
            throw new IllegalArgumentException();
        }
                
        int index = -1;
        for (int i = arr2.length-1; i > 0; i--) {
            if (arr1[arr1.length-1] == arr2[i]) {
                for (int j = 1; j < arr1.length; j++) {
                    if (arr1[arr1.length - j] == arr2[i-(arr1.length-j)]) {
                        index = i-(arr1.length-1);    
                    }
                }
            }
        }
        return index;
    }



    public static void main(String[] args) {       // to test the code
        int[] list1 = {1, 3, 6};
        int[] list2 = {1, 3, 5, 8, 12, 1, 3, 17, 1, 3, 6, 9, 1, 3, 6};
        System.out.println(find(list1, list2));
    }
}
