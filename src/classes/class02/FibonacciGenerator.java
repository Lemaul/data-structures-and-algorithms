package classes.class02;

import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Integer;

public class FibonacciGenerator<Integer> {

    int n;
    ArrayList<java.lang.Integer> data;

    public FibonacciGenerator(int n) {
        this.n = n;
        this.data = new ArrayList<java.lang.Integer>();
    }

    public void generate() {
        if (n <= 0) return;
        if (n == 1) { data.add(1); return; }
        if (n == 2) { data.add(1); data.add(1); return; }

        data.add(1);
        data.add(1);
        Iterator<Integer> fib = iterator();
        Integer secLast = fib.next();
        Integer last = fib.next();
        while (fib.hasNext()) {
            data.add((int) last + (int) secLast);
            last = secLast;
            secLast = (Integer) data.get(data.size()-1);
        }
    }

    public class FibonacciIterator<E> implements Iterator<E> {

        private int index;

        public FibonacciIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < n;
        }

        @Override
        public E next() {
            return (E) data.get(index++);
        }
    }

    public Iterator<Integer> iterator() {
        return new FibonacciIterator<>();
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
