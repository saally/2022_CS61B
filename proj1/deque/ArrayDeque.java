package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        int copyLengthFirstToArrayEnd = Math.min(items.length - nextFirst - 1, size);
        System.arraycopy(items, nextFirst + 1, newItems, 0, copyLengthFirstToArrayEnd);
        System.arraycopy(
                items, 0,
                newItems, copyLengthFirstToArrayEnd,
                size - copyLengthFirstToArrayEnd
        );
        nextFirst = newItems.length - 1;
        nextLast = size;
        items = newItems;
    }
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size += 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size; i++) {
            str.append(get(i));
            if (i != size - 1) {
                str.append(' ');
            }
        }
        System.out.println(str);
        System.out.println();
    }
    private void downsize() {
        if (size() < items.length / 4) {
            resize(items.length / 2);
        }
    }
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T returnedItem = get(0);
        nextFirst = (nextFirst + 1) % items.length;
        size -= 1;
        downsize();
        return returnedItem;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T returnedItem = get(size - 1);
        nextLast = (nextLast - 1 + items.length) % items.length;
        size -= 1;
        downsize();
        return returnedItem;
    }

    public T get(int index) {
        int arrayIndex = (index + nextFirst + 1) % items.length;
        return items[arrayIndex];
    }
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        int currIndex = (nextFirst + 1) % items.length;
        public boolean hasNext() {
            return currIndex != nextLast;
        }
        public T next() {
            if (!hasNext()) {
                return null;
            }
            T returnedItem = get(currIndex);
            currIndex = (currIndex + 1) % items.length;
            return returnedItem;
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque other = (Deque<T>) o;
        if (this.size() != other.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            if (!this.get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }

}
