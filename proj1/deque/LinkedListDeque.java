package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class DequeNode {
        private T item;
        private DequeNode next;
        private DequeNode prev;
        DequeNode(T x) {
            item = x;
        }
    }

    private DequeNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new DequeNode(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        // new node
        DequeNode newNode = new DequeNode(item);
        newNode.next = sentinel.next;
        newNode.prev = sentinel;
        // previous first node
        sentinel.next.prev = newNode;
        // sentinel node
        sentinel.next = newNode;
        // size
        size += 1;
    }

    public void addLast(T item) {
        // new node
        DequeNode newNode = new DequeNode(item);
        newNode.prev = sentinel.prev;
        newNode.next = sentinel;
        // previous last node
        sentinel.prev.next = newNode;
        // sentinal
        sentinel.prev = newNode;
        // size
        size += 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        StringBuilder str = new StringBuilder();
        DequeNode curr = sentinel.next;
        while (curr != null && curr.next != sentinel) {
            str.append(curr.item);
            str.append(' ');
            curr = curr.next;
        }
        if (curr != null && curr.item != null) {
            str.append(curr.item);
        }
        System.out.println(str);
        System.out.println();
    }

    private void removeNode(DequeNode n) {
        DequeNode prevNode = n.prev;
        DequeNode nextNode = n.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        size -= 1;
    }

    public T removeFirst() {
        DequeNode nodeToRemove = sentinel.next;
        if (nodeToRemove == sentinel) {
            return null;
        }
        removeNode(nodeToRemove);
        return nodeToRemove.item;
    }

    public T removeLast() {
        DequeNode NodeToRemove = sentinel.prev;
        if (NodeToRemove == sentinel) {
            return null;
        }
        removeNode(NodeToRemove);
        return NodeToRemove.item;
    }

    public T get(int index) {
        DequeNode curr = sentinel.next;
        while (index != 0) {
            if (curr == null) {
                return null;
            }
            curr = curr.next;
            index -= 1;
        }
        return curr.item;
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private DequeNode curr = sentinel.next;
        public boolean hasNext() {
            return curr != null;
        }
        public T next() {
            DequeNode returnedNode = curr;
            curr = curr.next;
            return returnedNode.item;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        LinkedListDeque other = (LinkedListDeque<T>) o;
        if (this.size() != other.size()) {
            return false;
        }
        Iterator<T> thisIterator = new LinkedListDequeIterator();
        Iterator<T> otherIterator = other.iterator();
        while (thisIterator.hasNext()) {
            T currItem = thisIterator.next();
            T otherItem = otherIterator.next();
            if (currItem != otherItem) {
                return false;
            }
        }
        return true;
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(DequeNode currNode, int index) {
        if (index == 0 && currNode != null) {
            return currNode.item;
        }
        if (currNode == null) {
            return null;
        }
        return getRecursiveHelper(currNode.next, index - 1);
    }


}
