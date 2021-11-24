package dsaa.lab12;

import java.util.Scanner;

public class LinesReader {
    String concatLines(int howMany, Scanner scanner) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < howMany; i++)
            sb.append(scanner.next());
        return sb.toString();
    }

}