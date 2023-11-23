/*
 * Problem5.java
 *
 * Problem 5: Finding the union of two arrays
 * 
 * Created by: Hongyi Yu; yuhy@bu.edu
 */

 import java.util.*;

 public class Problem5 {
    // A method takes two arrays of integers a1 and a2, and it should use an approach based on merging to create and 
    // return a new array containing the union of the values in a1 and a2.
    public static int[] union(int[] a1, int[] a2) {
        if (a1 == null || a2 == null) {
            throw new IllegalArgumentException();
        }

        int[] temp = new int[a1.length + a2.length];          // creating a new array
        Sort.mergeSort(a1);            // sort a1
        Sort.mergeSort(a2);            // sort a2

        int i = 0;         // index of a1
        int j = 0;         // index of a2
        int k = 0;         // index of temp


        while (i < a1.length && j < a2.length) {        // find the union
            if (a1[i] <= a2[j]) {
                if (k == 0 || a1[i] != temp[k-1]) {
                    temp[k] = a1[i];
                    i++; k++;
                } else {             // skips over duplicate values
                    i++;
                }
            } else {
                if (k == 0 || a2[j] != temp[k-1]) {
                    temp[k] = a2[j];
                    j++; k++;
                } else {             // skips over duplicate values
                    j++;
                }
            }
        }

        while (i < a1.length) {            // the rest of a1
            if (k == 0 || a1[i] != temp[k-1]) {
                temp[k] = a1[i];
                i++; k++;
            } else {                 // skips over duplicate values
                i++;
            }  
        }
        while (j < a2.length) {            // the rest of a2
            if (k == 0 || a2[j] != temp[k-1]) {
                temp[k] = a2[j];
                j++; k++;
            } else {                 // skips over duplicate values
                j++;
            }
        }

        return temp;
    }

    // test code for the union method.
    public static void main(String[] args) {
        int[] a1 = {10, 5, 7, 5, 9, 4};
        int[] a2 = {7, 5, 15, 7, 7, 9, 10};
        int[] result1 = union(a1, a2);
        System.out.println("result1: " + Arrays.toString(result1));

        int[] a3 = {0, 2, -4, 6, 10, 8};
        int[] a4 = {12, 0, -4, 8};
        int[] result2 = union(a3, a4);
        System.out.println("result2: " + Arrays.toString(result2));

        int[] a5 = {2, 6, 2, 2, 2, 5, 7};
        int[] a6 = {2, 6, 2};
        int[] result3 = union(a5, a6);
        System.out.println("result3:" + Arrays.toString(result3));

        int[] a7 = {3, 3, 0, 3};
        int[] a8 = {};
        int[] result4 = union(a7, a8);
        System.out.println("result4:" + Arrays.toString(result4));
    }
}
