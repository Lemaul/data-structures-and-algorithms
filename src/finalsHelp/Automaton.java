package finalsHelp;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.HashMap;

public class Automaton {

    int n;
    int q;
    int M;
    TreeSet<Character> alphabet;
    int[][] delta;


    public LinkedList<Integer> validShifts(String pattern, String text) {
        LinkedList<Integer> result = new LinkedList<>();
        this.n = text.length();
        this.q = 0; // the initial state
        M = pattern.length();
        alphabet = new TreeSet<>();
        for (int i = 0; i < M; i++)
            alphabet.add(pattern.charAt(i));

        Character[] letters = new Character[alphabet.size()];
        int iter = 0;
        for (Character s : alphabet) letters[iter++] = s;

        // Mapping char to int
        HashMap<Character, Integer> charToInt = new HashMap<>();
        for (int i = 0; i < alphabet.size(); i++)
            charToInt.put(letters[i], i);

        delta = new int[pattern.length() + 1][alphabet.size()];
        getDelta(delta, pattern, alphabet, charToInt);
        toStringDelta(delta);

        // Checking for patterns
        for (int i = 0; i < text.length(); i++) {
            q = alphabet.contains(text.charAt(i)) ? delta[q][charToInt.get(text.charAt(i))] : 0;
            if (q == M) result.add(i - M + 1);
        }

        return result;
    }

    private void getDelta(int[][] delta, String pattern, TreeSet<Character> alphabet, HashMap<Character, Integer> charToInt) {
        int m = pattern.length();
        int alphaLength = alphabet.size();
        for (int i = 0; i <= m; i++) {
            for (Character s : alphabet) {
                int nextState = 0;
                if (i < m && s == pattern.charAt(i))
                    nextState = i + 1;
                else {
                    int k;
                    for (int j = i; j > 0; j--) {
                        if (pattern.charAt(j - 1) == s) {
                            for (k = 0; k < j - 1; k++)
                                if (pattern.charAt(k) != pattern.charAt(i - j + 1 + k))
                                    break;
                            if (k == j - 1) {
                                nextState = j;
                                break;
                            }
                        }
                    }
                }
                delta[i][charToInt.get(s)] = nextState;
            }
        }
    }

    public void toStringDelta(int[][] delta) {
        for (int[] ints : delta) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
