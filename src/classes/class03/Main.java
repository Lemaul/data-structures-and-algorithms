package classes.class03;

public class Main {

    public static int[] copy(int[] arr) {
        int[] copy = new int[arr.length];
        System.arraycopy(arr, 0, copy, 0, arr.length);
        return copy;
    }

    public static void main(String[] args) {

        int[] array1 = {76, 71, 5, 57, 12, 50, 20, 93, 20, 55, 62, 3};
//        System.out.println("Insertion Sort:");
//        SortingAlgorithms.insertionSort(copy(array1));
        System.out.println("\nSelect Sort:");
        SortingAlgorithms.selectSort(copy(array1));
//        System.out.println("\nBubble Sort:");
//        SortingAlgorithms.bubbleSort(copy(array1));
//
//        int[] people = {1, 2, 3, 4, 5, 6, 7, 8};
//        System.out.println(List3.theKillingProblem(people, 3));
    }

}
