package dsaa.lab02;

import java.util.Scanner;

public class Document{
    public String name;
    public OneWayLinkedList<Link> links = new OneWayLinkedList<>();
    public Document(String name, Scanner scan) {
        this.name = name;
        load(scan);
    }
    public void load(Scanner scan) {
        boolean run = true;
        while (run) {
            String[] line = scan.nextLine().split(" ");
            for (String link : line) {
                if (correctLink(link))
                    links.add(new Link(link.substring(5).toLowerCase())); // remove toLowerCase
                else if (link.equalsIgnoreCase("eod")) {
                    run = false;
                    break;
                }
            }
        }
    }
    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
    private static boolean correctLink(String link) {
        if (link.length() <= 5) return false;
        if (!link.substring(0, 5).equalsIgnoreCase("link=")) return false;
        return Character.isAlphabetic(link.charAt(5));
    }

    @Override
    public String toString() {
        String elems = "";
        String newLine = "";
        if (links.size() > 0){
            elems = links.printableLines().substring(0, links.printableLines().length()-1);
            newLine = "\n";
        }
        return "Document: " + name + newLine + elems;
    }

}