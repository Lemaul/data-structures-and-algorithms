package dsaa.lab11;

import java.util.Scanner;
import java.util.*;

public class Document implements IWithName{
    public String name;
    public SortedMap<String, Link> link;

    public Document(String name) {
        this.name=name.toLowerCase();
        link=new TreeMap<>();
    }

    public Document(String name, Scanner scan) {
        this.name=name.toLowerCase();
        link=new TreeMap<>();
        load(scan);
    }
    public void load(Scanner scan) {
        boolean run = true;
        while (run) {
            String[] line = scan.nextLine().split(" ");
            for (String l : line) {
                if (isCorrectLink(l)) {
                    Link newLink = createLink(l);
                    assert newLink != null;
                    link.put(newLink.ref, newLink);
                }
                else if (l.equalsIgnoreCase("eod")) {
                    run = false;
                    break;
                }
            }
        }
    }

    public static boolean isCorrectLink(String id) {
        if (id.length() <= 5) return false;
        if (!id.substring(0, 5).equalsIgnoreCase("link=")) return false;
        if (!Character.isAlphabetic(id.charAt(5))) return false;
        int open = id.indexOf('('), close = id.indexOf(')');
        if (open == close) return true;
        if (open > close || close != id.length()-1) return false;
        try {
            int weight = Integer.parseInt(id.substring(open+1, close));
            return weight > 0;
        } catch (NumberFormatException e) { return false; }
    }

    public static boolean isCorrectId(String id) {
        return Character.isAlphabetic(id.charAt(0));
    }

    public static int isCorrectAdd(String link) {
        if (link.length() > 5 && link.substring(0, 5).equalsIgnoreCase("link=")) return 0;
        int open = link.indexOf('('), close = link.indexOf(')');
        if (open == close && Character.isAlphabetic(link.charAt(0))) return 1;
        if (open == 0 || close == 0) return 0;
        try {
            int weight = Integer.parseInt(link.substring(open+1, close));
            return 2;
        } catch (NumberFormatException e) { return 0; }
    }

    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
    public static Link createLinkAdd(String link) {
        if (isCorrectAdd(link) == 0) return null;
        if (isCorrectAdd(link) == 1) return new Link(link);
        int open = link.indexOf('('), close = link.indexOf(')');
        int weight = Integer.parseInt(link.substring(open+1, close));
        return new Link(link.substring(0, open), weight);
    }

    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
    static Link createLink(String link) {
        if (isCorrectAdd(link) > 0) return createLinkAdd(link);
        if (!isCorrectLink(link)) return null;
        int open = link.indexOf('('), close = link.indexOf(')');
        if (open == close) return new Link(link.substring(5, link.length()));
        String ref = link.substring(5, open).toLowerCase();
        int weight = Integer.parseInt(link.substring(open+1, close));
        return new Link(ref, weight);
    }

    @Override
    public String toString() {
        String retStr="Document: "+name+"\n";
        //TODO?
        retStr+=link;
        return retStr;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String getName() {
        return name;
    }
}