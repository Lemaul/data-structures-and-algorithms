package classes.class01;


import java.util.Arrays;
import java.util.HashSet;

public class Main {

    public static int[] nextPascalLine(int[] line){
        int[] next = new int[line.length+1];
        next[0] = 1;
        next[line.length] = 1;

        for (int i=0; i<line.length-1; i++) {
            next[i+1] = line[i] + line[i + 1];
        }

        return next;
    }

    public static int getSecondSmallest(int[] arr) throws NoAnswerException {
        if (arr.length < 2)
            throw new NoAnswerException("The array length is too short");
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        int changedFirst = 0, changedSecond = 0;
        for (int n : arr) {
            if (n < first) {
                second = first;
                first = n;
                changedFirst++;
                changedSecond++;
            }
            else if (n < second && n != first){
                second = n;
                changedSecond++;
            }
        }
        if (changedFirst < 1 || changedSecond < 2)
            throw new NoAnswerException("The numbers are the same");
        return second;
    }


    public static void main(String[] args) throws NoAnswerException {
//        int[] line = {1, 3, 3, 1};
//        int[] nextLine = nextPascalLine(line);
//        int[] nextNextLine = nextPascalLine(nextLine);
//        System.out.println(Arrays.toString(nextNextLine));

        int[] test1 = {9, 3, 5, 4, 7, 1, 5, 9};
        int[] test2 = {1, 3, 4, 5, 7, 9};
        int[] test3 = {1, 1, 1, 3, 2};
        int[] test4 = {10, 12, 11};

        try {
            System.out.println(getSecondSmallest(test3));
        } catch (NoAnswerException e) {
            System.out.println(e);
        }

    }
}
