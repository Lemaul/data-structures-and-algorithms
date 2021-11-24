package dsaa.lab01;

public class Drawer {

    private static void drawLine(int n, char ch) {
        for (int i=0; i<n; i++){
            System.out.print(ch);
        }
    }

    public static void drawPyramid(int n) {
        int spaces = n-1;
        int current = 1;
        for (int i=0; i<n; i++){
            drawLine(spaces, ' ');
            drawLine(current, 'X');
            spaces--;
            current += 2;
            System.out.println();
        }
    }

    public static void drawPyramid(int n, int padding) {
        int spaces = n-1;
        int current = 1;
        for (int i=0; i<n; i++){
            drawLine(spaces+padding, ' ');
            drawLine(current, 'X');
            spaces--;
            current += 2;
            System.out.println();
        }
    }

    public static void drawChristmassTree(int n) {
        for (int i=1; i<=n; i++){
            drawPyramid(i, n-i);
        }
    }

}