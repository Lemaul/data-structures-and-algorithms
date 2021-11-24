package dsaa.lab03;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class TwoWayUnorderedListWithHeadAndTail<E> implements IList<E>{

    private class Element{
        public Element(E e) {
            this.object = e;
        }
        E object;
        Element next=null;
        Element prev=null;
    }

    Element head;
    Element tail;
    int size;

    private class InnerIterator implements Iterator<E>{
        public InnerIterator() { }
        @Override
        public boolean hasNext() { return false; }
        @Override
        public E next() { return null; }
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    public TwoWayUnorderedListWithHeadAndTail() {
        head = new Element(null);
        tail = new Element(null);
    }

    @Override
    public boolean add(E e) {
        size++;
        Element newE = new Element(e);
        if (isEmpty()) {
            head.next = newE;
            tail.prev = newE;
            return true;
        }
        newE.prev = tail.prev;
        tail.prev.next = newE;
        tail.prev = newE;
        return true;
    }

    @Override
    public void add(int index, E element) throws NoSuchElementException {
        if (index == size) {
            add(element);
            return;
        }
        if (index > size || index < 0) throw new NoSuchElementException();

        Element newE = new Element(element);
        if (index == 0) {
            size++;
            newE.next = head.next;
            head.next.prev = newE;
            head.next = newE;
            return;
        }

        size++;
        int ind = 0;
        Element current = head.next;
        while (ind < index-1) {
            current = current.next;
            ind++;
         }
        newE.prev = current;
        newE.next = current.next;
        current.next.prev = newE;
        current.next = newE;
    }

    @Override
    public void clear() {
        head.next = null;
        tail.prev = null;
        size = 0;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    @Override
    public E get(int index) throws NoSuchElementException {
        if (index >= size || index < 0) throw new NoSuchElementException();

        int ind = 0;
        Element current = head.next;
        while (ind < index) {
            current = current.next;
            ind++;
        }
        return current.object;
    }

    @Override
    public E set(int index, E element) {
        if (index >= size || index < 0) throw new NoSuchElementException();

        int ind = 0;
        Element current = head.next;
        while (ind < index) {
            current = current.next;
            ind++;
        }
        E old = current.object;
        current.object = element;
        return old;
    }

    @Override
    public int indexOf(E element) {
        if (isEmpty()) return -1;

        int index = 0;
        Element current = head.next;
        while (current.next != null) {
            if (((E) current.object).equals((E) element))
                return index;
            current = current.next;
            index++;
        }
        if (((E) current.object).equals((E) element))
            return index;

        return -1;
    }

    @Override
    public boolean isEmpty() {
        return head.next == null;
    }

    @Override
    public E remove(int index) { // needs testing
        if (index >= size || index < 0) throw new NoSuchElementException();

        size--;
        if (size == 0) {
            E value = head.next.object;
            clear();
            return value;
        }
        Element current = head.next;
        if (index == 0) {
            head.next = current.next;
            current.next.prev = null;
            return current.object;
        }

        if (index == size) { // size has been decremented already
            E val = tail.prev.object;
            tail.prev.prev.next = null;
            tail.prev = tail.prev.prev;
            return val;
        }

        int ind = 0;
        while (ind < index-1) {
            current = current.next;
            ind++;
        }

        Element atIndex = current.next;
        E old = atIndex.object;
        atIndex.prev.next = atIndex.next;
        atIndex.next.prev = atIndex.prev;
        return old;
    }

    @Override
    public boolean remove(E e) {
        if (indexOf(e) == -1) return false;
        remove(indexOf(e));
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        String toPrint = "";
        if (size == 0) return toPrint;
        Element current = head.next;
        for (int i=0; i<size; i++) {
            toPrint += current.object + "\n";
            current = current.next;
        }
        return toPrint.substring(0, toPrint.length()-1);
    }

    public String toStringReverse() {
        String toPrint ="";
        if (size == 0) return toPrint;
        Element current = tail.prev;
        for (int i=0; i<size; i++) {
            toPrint += current.object + "\n";
            current = current.prev;
        }
        return toPrint.substring(0, toPrint.length()-1);
    }

    public void add(TwoWayUnorderedListWithHeadAndTail<E> other) {
        if (other == this) return;
        if (size == 0) {
            clear();
            this.head.next = other.head.next;
            this.tail.prev = other.tail.prev;
            size = other.size;
            other.clear();
            return;
        }
        if (other.size == 0) return;

        tail.prev.next = other.head.next;
        other.head.next.prev = tail.prev;
        tail.prev = other.tail.prev;
        size += other.size;
        other.clear();
    }
}
