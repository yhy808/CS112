public class Problem1 {
    public static void main(String[] args) {
        int[] arr1 = {2, 5, 7, 4, 1};
        printReverse(arr1, 0);
    }

    public static void print(int[] arr, int start) {
        if (arr == null) {
            throw new IllegalArgumentException();
        }
        if (start < 0) {
            throw new IllegalArgumentException();
        }
        if (start >= arr.length) {
            return;
        } else {
            System.out.println(arr[start]);
            print(arr, start+1);
        }
    }

    public static void printReverse(int[] arr, int i) {   // i = 0
        if (arr == null) {
            throw new IllegalArgumentException();
        }
        if (i == arr.length) {
            return;
        } else {
            printReverse(arr, i+1);
            System.out.println(arr[i]);
        }
    } 
}