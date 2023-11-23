import java.util.*;

public class test {
    public static int[] test5(int[] a1, int[] a2) {
        int[] temp = new int[a1.length + a2.length];
        Sort.mergeSort(a1);
        Sort.mergeSort(a2);
        
        int i = 0;
        int j = 0;
        int k = 0;

        if (a1[0] < a2[0]) {
            temp[0] = a1[0];
            i++; k++;
        } else {
            temp[0] = a2[0];
            j++; k++;
        }

        while (i < a1.length && j < a2.length) {
            if (a1[i] <= a2[j] && a1[i] != temp[k-1]) {
                temp[k] = a1[i];
                i++; k++;
            } else if (a2[j] < a1[i] && a2[j] != temp[k-1]) {
                temp[k] = a2[j];
                j++; k++;
            }
        }

        while (i < a1.length) {
            temp[k] = a1[i];
            i++; k++;
        }
        while (j < a2.length) {
            temp[k] = a2[j];
            j++; k++;
        }
        while (k < temp.length) {
            temp[k] = 0;
            k++;
        }

        return temp;
    }
    public static void main(String[] args) {
        int[] a1 = {10, 5, 7, 5, 9, 4};
        int[] a2 = {7, 5, 15, 7, 7, 9, 10};
        int[] result1 = test5(a1, a2);
        System.out.println("result1: " + Arrays.toString(result1));
    }
}
