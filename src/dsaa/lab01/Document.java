package dsaa.lab01;

import java.util.Scanner;

public class Document {

    public static void loadDocument(String name, Scanner scan) {
        boolean run = true;
        while (run) {
            String[] links = scan.nextLine().split(" ");
            for (String link : links) {
                if (correctLink(link))
                    System.out.println(link.substring(5, link.length()).toLowerCase());
                else if (link.equalsIgnoreCase("eod"))
                    run = false;
            }
        }
    }

    // accepted only small letters, capital letter, digits nad '_' (but not on the begin)
    public static boolean correctLink(String link) {
        if (link.length() <= 5) return false;
        if (!link.substring(0, 5).equalsIgnoreCase("link=")) return false;
        return Character.isAlphabetic(link.charAt(5));
    }

}