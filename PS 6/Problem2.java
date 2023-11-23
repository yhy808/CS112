public class Problem2 {
    public static int numUnique(int[] arr) {
        Sort.mergeSort(arr);
        int count = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i-1]) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr1 = {1,3,6,2,3,4,6,2,9,9};
        System.out.println(numUnique(arr1));
    }
}