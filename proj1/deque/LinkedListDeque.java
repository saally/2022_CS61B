package deque;

public class LinkedListDeque<T> implements Iterable<T> {
    private class DequeNode {
        public T item;
        public DequeNode next;
        public DequeNode prev;
        public DequeNode(T x) {
            item = x;
        }
    }

    private DequeNode sentinel;
    private int size;

    public LinkedListDeque(T item) {
        sentinel = new DequeNode(null);
        DequeNode newItem = new DequeNode(item);
        sentinel.prev = newItem;
        sentinel.next = newItem;
        size = 1;
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

    public boolean isEmpty() {
        return sentinel.next == null;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        StringBuilder str = new StringBuilder();
        DequeNode curr = sentinel.next;
        while (curr != null && curr.next != null) {
            str.append(curr.item.toString() + ' ');
            curr = curr.next;
        }
        str.append(curr.item.toString());
        System.out.println(str);
        System.out.println();
    }

    private T removeNode(DequeNode n) {
        DequeNode prevNode = n.prev;
        DequeNode nextNode = n.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        size -= 1;
    }

    public T removeFirst() {
        DequeNode NodeToRemove = sentinel.next;
        if (NodeToRemove == null) {
            return null;
        }
        removeNode(NodeToRemove);
        return NodeToRemove.item;
    }

    public T removeLast() {
        DequeNode NodeToRemove = sentinel.prev;
        if (NodeToRemove == null) {
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

    }

    public boolean equals(Object o) {
        if (!(o instanceof LinkedListDeque)) {
            return false;
        }
        if (this.size != o.size) {
            return false;
        }

    }


}
