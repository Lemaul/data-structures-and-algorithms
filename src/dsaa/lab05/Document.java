package dsaa.lab05;

import java.util.Scanner;

public class Document{
    public String name;
    public TwoWayCycledOrderedListWithSentinel<Link> link;

    public Document(String name, Scanner scan) {
        this.name=name.toLowerCase();
        link=new TwoWayCycledOrderedListWithSentinel<Link>();
        load(scan);
    }

    public void load(Scanner scan) {
        boolean run = true;
        while (run) {
            String[] line = scan.nextLine().split(" ");
            for (String l : line) {
                if (isCorrectLink(l))
                    link.add(createLink(l));
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

    public static Link createLinkAdd(String link) {
        if (isCorrectAdd(link) == 0) return null;
        if (isCorrectAdd(link) == 1) return new Link(link);
        int open = link.indexOf('('), close = link.indexOf(')');
        int weight = Integer.parseInt(link.substring(open+1, close));
        return new Link(link.substring(0, open), weight);
    }

    // accepted only small letters, capital letter, digits nad '_' (but not on the begin)
    public static Link createLink(String link) {
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
        String retStr="Document: "+name;
        if (link.size() == 0) return retStr;
        return retStr + "\n" + link.toString();
    }

    public String toStringReverse() {
        String retStr="Document: "+name;
        if (link.size() == 0) return retStr;
        return retStr + "\n" + link.toStringReverse();
    }

    public int[] getWeights() {
        return link.getWeights();
    }

    public static void showArray(int[] arr) {
        for (int i=0; i<arr.length; i++) {
            if (i < arr.length-1)
                System.out.print(arr[i] + " ");
            else
                System.out.print(arr[i]);
        }
    }

    void bubbleSort(int[] arr) {
        showArray(arr);
        for (int i = 1; i < arr.length; i++) {
            for (int j = arr.length-1; j >= i; j--) {
                if (arr[j] < arr[j-1]) swap(arr, j, j-1);
            }
            System.out.println();
            showArray(arr);
        }
        if (arr.length > 0) System.out.println();
    }

    public void insertSort(int[] arr) {
        showArray(arr);
        for (int i = arr.length-2; i >= 0; i--) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] < arr[j-1]) swap(arr, j, j-1);
            }
            System.out.println();
            showArray(arr);
        }
        if (arr.length > 0) System.out.println();
    }

    public void selectSort(int[] arr) {
        showArray(arr);
        for (int i = arr.length-1; i > 0; i--) {
            int max = Integer.MIN_VALUE, ind = 0;
            for (int j = 0; j <= i; j++) {
                if (arr[j] > max) {
                    max = arr[j]; ind = j;
                }
            }
            swap(arr, i, ind);
            System.out.println();
            showArray(arr);
        }
        if (arr.length > 0) System.out.println();
    }

    public void selectSortFromLeft(int[] arr) {
        showArray(arr);
        for (int i = 0; i < arr.length; i++) {
            int min = Integer.MAX_VALUE, ind = 0;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j]; ind = j;
                }
            }
            swap(arr, i, ind);
            System.out.println();
            showArray(arr);
        }
        if (arr.length > 0) System.out.println();
    }

    public void mergeSort(int[] arr) {
        showArray(arr);
        int pow = 1;
        for (int i = 0; i < Math.ceil(Math.log(arr.length)); i++) {
            for (int j = 0; j < arr.length-1; j += 2*pow) {
                merge(arr, j, Math.min(j + 2*pow - 1, arr.length-1));
            }
            System.out.println();
            showArray(arr);
            pow *= 2;
        }
    }

    public void merge(int arr[], int l, int r) {
        int middle = (l+r)/2;
        int len1 = middle - l + 1;
        int len2 = r - middle;

        int L[] = new int[len1];
        int R[] = new int[len2];

        for (int i = l; i < l+middle; i++)
            L[i-l] = arr[i];
        for (int i = middle+1; i < r; i++)
            R[i-middle-1] = arr[i];

        int indexL = 0, indexR = 0;
        while (indexL < len1 && indexR < len2) {
            if (L[indexL] <= R[indexR])
                arr[l] = L[indexL++];
            else
                arr[l] = R[indexR++];
            l++;
        }
        while (indexL < len1)
            arr[l++] = L[indexL++];
        while (indexR < len2)
            arr[l++] = R[indexR++];

    }



    private void swap(int[] arr, int idx1, int idx2) {
        int hjelp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = hjelp;
    }

}