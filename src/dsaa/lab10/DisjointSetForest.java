package dsaa.lab10;

import java.util.HashSet;
import java.util.Stack;

public class DisjointSetForest implements DisjointSetDataStructure {

    private class Element{
        int rank;
        int parent;

        public Element(int rank, int parent) {
            this.rank = rank;
            this.parent = parent;
        }
    }

    Element[] arr;

    public DisjointSetForest(int size) {
        arr = new Element[size];
        for (int i = 0; i < size; i++)
            makeSet(i);
    }

    @Override
    public void makeSet(int item) {
        if (item < arr.length && item >=0)
            arr[item] = new Element(1, item);
    }

    @Override
    public int findSet(int item) {
        if (item < 0 || item > arr.length-1) return -1;
        Stack<Element> s = new Stack<>();
        s.add(arr[item]);
        int current = arr[item].parent;
        while (current != arr[current].parent) {
            current = arr[current].parent;
            s.add(arr[current]);
        }
        while (!s.empty())
            s.pop().parent = current;
        return current;
    }

    @Override
    public boolean union(int itemA, int itemB) {
        // if any of items is out of range
        if (itemA >= arr.length || itemB >= arr.length || itemA < 0 || itemB < 0)
            return false;
        // they belong to the same set
        if (findSet(itemA) == findSet(itemB))
            return false;
        Link(findSet(itemA), findSet(itemB));
        return true;
    }

    private void Link(int x, int y) {
        if (arr[x].rank > arr[y].rank)
            arr[y].parent = x;
        else {
            arr[x].parent = y;
            if (arr[x].rank == arr[y].rank)
                arr[y].rank++;
        }
    }

    @Override
    public int countSets() {
        HashSet<Integer> hs = new HashSet<>();
        for (int i = 0; i < arr.length; i++)
            hs.add(findSet(i));
        return hs.size();
    }

    @Override
    public String toString() {
        String forest = "Disjoint sets as forest:\n";
        for (int i = 0; i < arr.length; i++)
            forest += i + " -> " + arr[i].parent + "\n";
        return forest.substring(0, forest.length()-1);
    }
}