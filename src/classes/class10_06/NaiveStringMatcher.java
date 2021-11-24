package classes.class10_06;

public class NaiveStringMatcher {

    public static void printComparisons(String pattern, String text) {
        int n = text.length();
        int m = pattern.length();
        int cnt = 0;
        for (int i = 0; i < n - m; i++) {
            System.out.println("i = " + i);
            for (int j = i; j < i + m; j++) {
                String equality = text.charAt(j) == pattern.charAt(j - i) ? " == " : " != ";
                System.out.print(text.charAt(j) + equality + pattern.charAt(j-i) + "\t");
                cnt++;
                if (text.charAt(j) != pattern.charAt(j - i)) break;
            }
            System.out.println();
        }
        System.out.println("Total number of comparisons: " + cnt);
    }

    public static void main(String[] args) {
        String pattern = "0001";
        String text = "000010001010001";
        printComparisons(pattern, text);
    }
}
