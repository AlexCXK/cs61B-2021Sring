package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>{
    private T[] items;
    private int size;
    private int capacity;
    private int nextFirstIndex;
    private int nextLastIndex;
//    private int usageFactor;
    public ArrayDeque() {
        capacity = 8;
        items = (T[]) new Object[capacity];
        size = 0;
        nextFirstIndex = 4;
        nextLastIndex = 5;

    }
    @Override
    public void addFirst(T item) {
        if(isFull()){
            resize(this.capacity *2);
        }
        items[nextFirstIndex] = item;
        nextFirstIndex = (nextFirstIndex - 1 +capacity) %capacity;
        this.size++;
    }

    @Override
    public void addLast(T item) {
        if(isFull()){
            resize(this.capacity *2);
        }
        items[nextLastIndex] = item;
        nextLastIndex = (nextLastIndex + 1 +capacity) %capacity;
        this.size++;

    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < this.size; i++) {
            System.out.print(get(i));
            if (i < size - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if(isEmpty()){ return null; }
        int physicalIndex = (nextFirstIndex  + 1 +capacity ) % capacity;
        T item = items[physicalIndex];
        items[physicalIndex] = null;
        nextFirstIndex = physicalIndex;
        this.size -=1;
        if(isLowUsage()){
            resize(this.capacity / 2);
        }

        return item;
    }

    @Override
    public T removeLast() {
        if(isEmpty()){ return null; }

        int physicalIndex = (nextLastIndex  - 1 +capacity ) % capacity;
        T item = items[physicalIndex];
        items[physicalIndex] = null;
        nextLastIndex = physicalIndex;
        this.size -=1;
        if(isLowUsage()){
            resize(this.capacity / 2);
        }

        return item;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) { return null; }
        int physicalIndex = (nextFirstIndex  + 1 + index) % capacity;
        return items[physicalIndex];
    }

    private class ArrayDequeIterator implements Iterator<T>{
        private int curr = 0;
        @Override
        public boolean hasNext() {

            return curr < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException(); //
            }
            T item = get(curr);
            curr++;
            return item;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    public  boolean isFull(){
        return this.size == this.capacity;
    }


    private void resize(int capacity) {
        T[] temps = (T[]) new Object[capacity];
        int curr = (this.nextFirstIndex + 1) % items.length;
        for(int i = 0; i < size; i++){
            temps[i] = items[curr];
            curr = (curr + 1) % items.length;
        }
        items = temps;
        this.nextFirstIndex = capacity - 1;
        this.nextLastIndex = this.size;
        this.capacity = capacity;

    }

    public boolean isLowUsage(){
        if(this.capacity <16){
            return false;
        }
        if (4 *this.size <this.capacity ){
            return true;
        }
        return false;
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
