package classes.class03;

import java.util.LinkedList;
import java.util.Queue;

public class List3 {

    public static int theKillingProblem(int[] arr, int k) {
        Queue<Integer> q = new LinkedList<>();

        for (int n : arr)
            q.add(n);

        int ind = 1;
        while (q.size() > 1) {
            if (ind % k == 0) q.remove();
            else q.add(q.remove());
            ind++;
        }

        return q.remove();
    }

}
