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
        this.sentinel = new Node(null, null, null);
        this.sentinel.next = sentinel;
        this.sentinel.prev = sentinel;
        this.size = 0;
    }

    public LinkedListDeque(T data) {
        this();
        addFirst(data);

    }

    @Override
    public void addFirst(T item) {
        Node node = new Node(item, this.sentinel, this.sentinel.next);;
        this.sentinel.next.prev = node;
        this.sentinel.next = node;
        this.size +=1;
    }

    @Override
    public void addLast(T item) {
        Node node = new Node(item, this.sentinel.prev, this.sentinel);
        this.sentinel.prev.next = node;
        this.sentinel.prev = node;
        this.size +=1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        Node p = this.sentinel.next;
        for (int i = 0; i < this.size; i++) {
            System.out.print(p.data);
            if (i < size - 1) {
                System.out.print(" ");
            }
            p = p.next;
        }
        System.out.println();

    }

    @Override
    public T removeFirst() {
        if (isEmpty()){
            return null;
        }
        Node node = this.sentinel.next;
        this.sentinel.next = node.next;
        node.next.prev = this.sentinel;
        node.next =null;
        node.prev = null;
        this.size -= 1;
        return node.data;
    }

    @Override
    public T removeLast() {
        if (isEmpty()){
            return null;
        }
        Node node = this.sentinel.prev;
        node.prev.next = this.sentinel;
        this.sentinel.prev = node.prev;
        node.prev = null;
        node.next = null;
        this.size -= 1;
        return node.data;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1){
            return null;
        }
        Node p =  this.sentinel.next;
        while (index > 0){
            p = p.next;
            index -= 1;
        }
        return p.data;
    }
}
