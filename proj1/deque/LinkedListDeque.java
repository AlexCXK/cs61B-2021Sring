package deque;

public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        public T data;
        public Node prev;
        public Node next;
        public Node(T data,Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }
    private int size;
    private Node sentinel;
    public LinkedListDeque() {

    }

    @Override
    public void addFirst(T item) {

    }

    @Override
    public void addLast(T item) {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void printDeque() {

    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }
}
