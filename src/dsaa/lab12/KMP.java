package dsaa.lab12;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;

public class KMP implements IStringMatcher {

    @Override
    public LinkedList<Integer> validShifts(String pattern, String text) {
        LinkedList<Integer> result = new LinkedList<>();
        int n = text.length();
        int m = pattern.length();
        int[] pi = getPi(pattern);
        int q = 0;

        int i = 0;
        while (i < n) {
            if (pattern.charAt(q) == text.charAt(i)) {
                q++;
                i++;
            }
            if (q == m) {
                result.add(i - m);
                q = pi[q - 1];
            }
            else if (i < n && pattern.charAt(q) != text.charAt(i))
                if (q != 0)
                    q = pi[q - 1];
                else
                    i++;
        }

        return result;
    }

    public int[] getPi(String pattern) {
        int m = pattern.length();
        int[] pi = new int[m];
        pi[0] = 0;
        int k = 0;

        int i = 1;
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(k))
                pi[i++] = ++k;
            else
                if (k != 0)
                    k = pi[k-1];
                else
                    pi[i++] = k;
        }
        return pi;
    }
}