package classes.class07;

import java.util.ArrayList;
import java.util.Scanner;

public class MagnificentMeatballs {

    private static ArrayList<int[]> inputData() {
        ArrayList<int[]> input = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            if (sc.hasNextLine()) {
                String[] current = sc.next().split(" ");
                if (current[0].equals("0")) break;
                int currentSize = Integer.parseInt(current[0]);
                input.add(new int[currentSize]);
                for (int i = 0; i < currentSize; i++) {
                    input.get(input.size()-1)[i] = Integer.parseInt(current[i+1]);
                }
            }
        }

        return input;
    }

    public static void calculateResults() {
        ArrayList<int[]> input = inputData();
        for (int[] current : input) {
            int S = 0, E = current.length-1;
            int SCount = 0, ECount = 0;
            while (E != S) {
                if (SCount <= ECount) {
                    SCount += current[S++];
                }
                else {
                    ECount += current[E--];
                }
            }
            if (SCount == ECount) System.out.printf("%d%n", S);
        }
    }
}
