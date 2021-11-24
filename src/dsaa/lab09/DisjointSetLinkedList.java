package dsaa.lab09;

public class DisjointSetLinkedList implements DisjointSetDataStructure {

    private class Element {
        int representant;
        int next;
        int length;
        int last;

        public Element(int representant, int next, int length, int last) {
            this.representant = representant;
            this.next = next;
            this.length = length;
            this.last = last;
        }
    }

    private static final int NULL = -1;

    Element[] arr;

    public DisjointSetLinkedList(int size) {
        arr = new Element[size];
        for (int i = 0; i < size; i++)
            makeSet(i);
    }

    @Override
    public void makeSet(int item) {
        arr[item] = new Element(item, -1, 1, item);
    }

    @Override
    public int findSet(int item) {
        if (item < 0 || item >= arr.length) return -1;
        return arr[item].representant;
    }

    @Override
    public boolean union(int itemA, int itemB) {
        // if any of items is out of range
        if (itemA >= arr.length || itemB >= arr.length || itemA < 0 || itemB < 0) return false;
        // they belong to the same set
        if (findSet(itemA) == findSet(itemB)) return false;
        // I want to choose a bigger set's representative to be the representative of the union
        if (arr[findSet(itemA)].length < arr[findSet(itemB)].length)
            return union(itemB, itemA);

        arr[arr[itemA].last].next = findSet(itemB);
        int current = findSet(itemB);
        while (current != -1) {
            arr[current].representant = findSet(itemA);
            current = arr[current].next;
        }
        arr[itemA].last = arr[itemB].last;
        arr[findSet(itemA)].length += arr[findSet(itemB)].length;

        return true;
    }


    @Override
    public String toString() {
        boolean[] visited = new boolean[arr.length];
        String linkedList = "Disjoint sets as linked list:\n";
        for (int i = 0; i < arr.length; i++) {
            int current = arr[i].representant;
            if (visited[current]) continue;
            String currentSet = "";
            while (current != -1) {
                visited[current] = true;
                currentSet += current + ", ";
                current = arr[current].next;
            }
            linkedList += currentSet.substring(0, currentSet.length()-2) + "\n";
        }
        return linkedList.substring(0, linkedList.length()-1);
    }

}