package dsaa.lab08;

import java.util.HashSet;
import java.util.LinkedList;

public class HashTable{
    LinkedList arr[];
    private HashSet<String> names = new HashSet<>();
    private final static int defaultInitSize=8;
    private final static double defaultMaxLoadFactor=0.7;
    private int size;
    private final double maxLoadFactor;

    public HashTable() {
        this(defaultInitSize);
    }

    public HashTable(int size) {
        this(size,defaultMaxLoadFactor);
    }

    public HashTable(int initCapacity, double maxLF) {
        if(initCapacity<2)
            initCapacity=2;
        arr=new LinkedList[initCapacity];
        size = initCapacity;
        maxLoadFactor=maxLF;
    }


    public boolean add(Object elem) {
        Document doc = (Document)elem;

        if (names.contains(doc.getName())) return false;

        int idx = elem.hashCode() % size;
        if (arr[idx] == null) arr[idx] = new LinkedList();
        arr[idx].add(elem);
        names.add(doc.getName());

        if ( ((double)names.size()/(double)size) > maxLoadFactor)
            doubleArray();

        return true;
    }

    private void doubleArray() {
        LinkedList newArr[] = new LinkedList[size*2];

        names.clear();

        for (int i = 0; i < size; i++) {
            if (arr[i] == null) continue;
            for (Object o : arr[i]) {
                if (newArr[o.hashCode() % (size*2)] == null) newArr[o.hashCode() % (size*2)] = new LinkedList();
                newArr[o.hashCode() % (size*2)].add(o);
                names.add(((Document)o).getName());
            }
        }

        arr = newArr;
        size *= 2;
    }

    @Override
    public String toString() {
        String ht = "";
        for (int i = 0; i < size; i++) {
            ht += i + ":";
            if (arr[i] != null) {
                ht += " ";
                for (int j = 0; j < arr[i].size(); j++) {
                    if (j < arr[i].size()-1) ht += ((Document)arr[i].get(j)).getName() + ", ";
                    else ht += ((Document)arr[i].get(j)).getName();
                }
            }
            ht += "\n";
        }
        return ht;
    }

    public Object get(Object toFind) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == null) continue;
            for (Object o : arr[i]) {
                if (((Document)o).equals((Document)toFind)) return o;
            }
        }
        return null;
    }

}
