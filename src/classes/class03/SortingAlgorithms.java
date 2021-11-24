package classes.class03;

public class SortingAlgorithms {

    public static void insertionSort(int[] arr) {
        showArray(arr);
        for (int i = arr.length-2; i >= 0; i--) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] > arr[j-1]) swap(arr, j, j-1);
            }
            System.out.println();
            showArray(arr);
        }
        if (arr.length > 0) System.out.println();
    }

    public static void selectSort(int[] arr) {
        showArray(arr);
        for (int i = arr.length-1; i > 0; i--) {
            int min = Integer.MAX_VALUE, ind = 0;
            for (int j = 0; j <= i; j++) {
                if (arr[j] < min) {
                    min = arr[j]; ind = j;
                }
            }
            swap(arr, i, ind);
            System.out.println();
            showArray(arr);
        }
        if (arr.length > 0) System.out.println();
    }

    public static void bubbleSort(int [] arr) {
        showArray(arr);
        for (int i = 1; i < arr.length; i++) {
            for (int j = arr.length-1; j >= i; j--) {
                if (arr[j] > arr[j-1]) swap(arr, j, j-1);
            }
            System.out.println();
            showArray(arr);
        }
        if (arr.length > 0) System.out.println();
    }

    public static void showArray(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            if (i < arr.length-1) System.out.print(arr[i] + ", ");
            else System.out.print(arr[i]);
    }

    public static void swap(int[] arr, int idx1, int idx2) {
        int help = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = help;
    }
}
