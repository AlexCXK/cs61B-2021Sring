package deque;

import java.util.Iterator;

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
        T data =node.data;
        node.data = null;
        this.size -= 1;
        return data;
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
        T data =node.data;
        node.data = null;
        this.size -= 1;
        return data;
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

    private class LinkedListDequeIterator implements Iterator<T>{
        private Node current = sentinel.next;

        @Override
        public boolean hasNext() {
            return current != sentinel;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("no more elements");
            }
            T o = current.data;
            current = current.next;
            return o;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object o){
        if (o == this){return true;}
        if (o instanceof Deque<?> otherSet){
            if(this.size != otherSet.size()){
                return false;
            }
            Iterator<T> iterator = this.iterator();
            Iterator<?> otherIterator = otherSet.iterator();
            while (iterator.hasNext()){
                T a = iterator.next();
                Object b = otherIterator.next();
                if(!((a == b) || (a != null && a.equals(b)))){
                    return false;
                }
            }
            return true;
        }

        return false;
    }
}
