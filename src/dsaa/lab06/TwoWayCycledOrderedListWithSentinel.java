package dsaa.lab06;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TwoWayCycledOrderedListWithSentinel<E> implements IList<E> {

    private class Element{
        public Element(E e) {
            this.object = e;
        }
        public Element(E e, Element next, Element prev) {
            this.object = e;
            this.next = next;
            this.prev = prev;
        }
        // add element e after this
        public void addAfter(Element elem) {
            elem.next = next;
            elem.prev = this;
            next.prev = elem;
            next = elem;
        }
        // assert it is NOT a sentinel
        public void remove() {
            if (object == null) return;
            next.prev = prev;
            prev.next = next;
        }

        E object;
        Element next=null;
        Element prev=null;
    }

    Element sentinel;
    int size;

    private class InnerIterator implements Iterator<E>{
        public InnerIterator() {
        }
        @Override
        public boolean hasNext() {
            return false;
        }
        @Override
        public E next() {
            return null;
        }
    }
    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }
    @Override
    public ListIterator<E> listIterator() {
        return null;
    }


    public TwoWayCycledOrderedListWithSentinel() {
        this.sentinel = new Element(null);
        this.size = 0;
    }

    //@SuppressWarnings("unchecked")
    @Override
    public boolean add(E e) {
        if (size() == 0) {
            sentinel.next = new Element(e);
            sentinel.next.next = sentinel.next;
            sentinel.next.prev = sentinel.next;
            size++;
            return true;
        }

        if (((Link)e).compareTo((Link)sentinel.next.object) < 0) {
            Element newE = new Element(e, sentinel.next, sentinel.next.prev);
            sentinel.next.prev.next = newE;
            sentinel.next.prev = newE;
            sentinel.next = newE;
            size++;
            return true;
        }

        Element current = sentinel.next.prev;
        for (int i=0; i<size(); i++) {
            if ( ((Link)e).compareTo((Link)current.object) >= 0 ) {
               Element newE = new Element(e);
                current.addAfter(newE);
                break;
            }
            current = current.prev;
        }
        size++;
        return true;
    }

    private Element getElement(int index) {
        if (index < 0 || index >= size()) return null;
        Element e = sentinel.next;
        for (int i=0; i < index; i++)
            e = e.next;
        return e;
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        sentinel.next = null;
        size = 0;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size()) throw new NoSuchElementException();
        Element e = sentinel.next;
        for (int i=0; i < index; i++)
            e = e.next;
        return e.object;
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(E element) {
        if (size() == 0) return -1;

        Element current = sentinel.next;
        Link seek = (Link) element;
        for (int i = 0; i < size(); i++) {
            if (current.object.equals(seek))
                return i;
            current = current.next;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size()) throw new NoSuchElementException();

        size--;

        Element current = sentinel.next;
        for (int i = 0; i < index; i++)
            current = current.next;

        if (index == 0)
            sentinel.next = current.next;

        E obj = current.object;
        current.remove();

        if (size() == 0) clear();

        return obj;
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

    //@SuppressWarnings("unchecked")
    public void add(TwoWayCycledOrderedListWithSentinel<E> other) {
        if (other.size() == 0) {
            other.clear();
            return;
        }
        if (size() == 0) {
            sentinel.next = other.sentinel.next;
            size = other.size();
            other.clear();
            return;
        }

        Element currentThis = sentinel.next.prev;
        Element currentOther = other.sentinel.next.prev;

        for (int i = 0; i < other.size(); i++) {
            boolean added = false;
            while ( ((Link)currentOther.object).compareTo((Link)currentThis.object) < 0 ) {
                if (((Link)currentOther.object).compareTo((Link)sentinel.next.object) <= 0) {
                    Element newE = new Element(currentOther.object, sentinel.next, sentinel.next.prev);
                    sentinel.next.prev.next = newE;
                    sentinel.next.prev = newE;
                    sentinel.next = newE;
                    added = true;
                    break;
                }
                currentThis = currentThis.prev;

            }
            if (!added)
                currentThis.addAfter(new Element(currentOther.object));
            currentOther = currentOther.prev;
        }

        size += other.size();
        other.clear();
    }

    //@SuppressWarnings({ "unchecked", "rawtypes" })
    public void removeAll(E e) {
        Link check = (Link) e;
        Element current = sentinel.next;
        int n = size();
        for (int i=0; i < n; i++) {
            if (current.object.equals(check)) {
                if (current.equals(sentinel.next)) {
                    sentinel.next = current.next;
                    current.remove();
                } else {
                    current.remove();
                }
                size--;
            }
            current = current.next;
        }
        if (size == 0) clear();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Element e = sentinel.next;
        for (int i = 0; i < size(); i++) {
            if (i == size()-1) s.append(e.object);
            else if ((i+1)%10 == 0) s.append(e.object).append("\n");
            else s.append(e.object).append(" ");
            e = e.next;
        }
        return s.toString();
    }

    public String toStringReverse() {
        StringBuilder s = new StringBuilder();
        Element e = sentinel.next.prev;
        for (int i = 0; i < size(); i++) {
            if (i == size()-1) s.append(e.object);
            else if ((i+1)%10 == 0) s.append(e.object).append("\n");
            else s.append(e.object).append(" ");
            e = e.prev;
        }
        return s.toString();
    }

    public int[] getWeights() {
        int[] arr = new int[size()];
        Element current = sentinel.next;
        for (int i=0; i<size(); i++) {
            arr[i] = ((Link)current.object).weight;
            current = current.next;
        }
        return arr;
    }

}

