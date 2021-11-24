package dsaa.lab02;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OneWayLinkedList<E> implements IList<E>{

    private class Element{
        E object;
        Element next=null;

        public Element(E e) {
            this.object=e;
        }

        public E getValue() {
            return object;
        }

        public void setValue(E value) {
            this.object = value;
        }

        public Element getNext() {
            return next;
        }

        public void setNext(Element next) {
            this.next = next;
        }
    }

    Element sentinel;

    private class InnerIterator implements Iterator<E>{

        Element current;

        public InnerIterator() {}

        @Override
        public boolean hasNext() {
            return current.getNext() != null;
        }

        @Override
        public E next() {
            E value = current.getValue();
            current = current.getNext();
            return value;
        }

    }

    public OneWayLinkedList() {
        sentinel = new Element(null);
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E e) {
        Element newE = new Element(e);
        if (isEmpty()) {
            sentinel.next = newE;
            return true;
        }
        Element current = sentinel;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(newE);
        return true;
    }

    @Override
    public void add(int index, E element) throws NoSuchElementException {
        if (index == size()) {
            add(element);
            return;
        }
        if (index >= size() || index < 0) throw new NoSuchElementException();

        Element newE = new Element(element);

        if (index == 0) {
            newE.setNext(sentinel.getNext());
            sentinel.setNext(newE);
            return;
        }

        int ind = 0;
        Element current = sentinel.getNext();
        while (ind < index-1) {
            current = current.getNext();
            ind++;
        }

        newE.setNext(current.getNext());
        current.setNext(newE);
    }

    @Override
    public void clear() {
        sentinel.setNext(null);
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    @Override
    public E get(int index) throws NoSuchElementException {
        if (index >= size() || index < 0) throw new NoSuchElementException();

        int ind = 0;
        Element current = sentinel.getNext();
        while (ind < index) {
            current = current.getNext();
            ind++;
        }
        return current.getValue();
    }

    @Override
    public E set(int index, E element) throws NoSuchElementException {
        if (index >= size() || index < 0) throw new NoSuchElementException();

        int ind = 0;
        Element current = sentinel.getNext();
        while (ind < index) {
            current = current.getNext();
            ind++;
        }
        E old = current.getValue();
        current.setValue(element);
        return old;
    }

    @Override
    public int indexOf(E element) {
        if (isEmpty()) return -1;

        int index = 0;
        Element current = sentinel.getNext();
        while (current.getNext() != null) {
            if (((E) current.getValue()).equals((E) element))
                return index;
            current = current.getNext();
            index++;
        }
        if (((E) current.getValue()).equals((E) element))
            return index;
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.getNext() == null;
    }

    @Override
    public E remove(int index) throws NoSuchElementException {
        if (index >= size() || index < 0) throw new NoSuchElementException();

        Element current = sentinel.getNext();

        if (index == 0) {
            sentinel.setNext(sentinel.getNext().getNext());
            return current.getValue();
        }

        int ind = 0;
        while (ind < index-1) {
            current = current.getNext();
            ind++;
        }

        E old = current.getNext().getValue();
        current.setNext(current.getNext().getNext());
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
        if (isEmpty()) return 0;
        Element elem = sentinel.getNext();
        int size = 1;
        while (elem.getNext() != null) {
            elem = elem.getNext();
            size++;
        }
        return size;
    }

    @Override
    public String toString() {
        String list = "[";

        Element elem = sentinel.getNext();

        if (size() == 0) return list + "]";
        if (size() == 1)
            return list + elem.getValue() + "]";
        else if (size() == 2)
            return list + elem.getValue() + ", " + elem.getNext().getValue() + "]";

        while(elem.getNext().getNext() != null) {
            list += elem.getValue() + ", ";
            elem = elem.getNext();
        }
        list += elem.getValue() + ", ";
        list += elem.getNext().getValue() + "]";
        return list;
    }

    public String printableLines() {
        String list = "";
        if (size() == 0) return list;

        Element elem = sentinel.getNext();
        for (int i=0; i<size(); i++) {
            list += elem.getValue()+"\n";
            elem = elem.getNext();
        }
        return list;
    }
}
