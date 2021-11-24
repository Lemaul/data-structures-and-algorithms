package dsaa.lab03;

import java.util.Scanner;

public class Document{
    public String name;
    public TwoWayUnorderedListWithHeadAndTail<Link> link;
    public Document(String name, Scanner scan) {
        this.name=name;
        link=new TwoWayUnorderedListWithHeadAndTail<Link>();
        load(scan);
    }

    public void load(Scanner scan) {
        boolean run = true;
        while (run) {
            String[] line = scan.nextLine().split(" ");
            for (String l : line) {
                if (correctLink(l))
                    link.add(new Link(l.substring(5).toLowerCase()));
                else if (l.equalsIgnoreCase("eod")) {
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
        if (link.size == 0)
            return "Document: " + name;
        return "Document: " + name + "\n" + link.toString();
    }

    public String toStringReverse() {
        if (link.size == 0)
            return "Document: " + name;
        return "Document: " + name + "\n" + link.toStringReverse();
    }

}