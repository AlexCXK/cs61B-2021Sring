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
            resize();
        }
        items[nextFirstIndex] = item;
        nextFirstIndex = (nextFirstIndex - 1 +capacity) %capacity;
    }

    @Override
    public void addLast(T item) {
        if(isFull()){
            resize();
        }
        items[nextLastIndex] = item;
        nextLastIndex = (nextLastIndex + 1 +capacity) %capacity;

    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {

    }

    @Override
    public T removeFirst() {
        int physicalIndex = (nextFirstIndex  + 1 ) % capacity;
        T item = items[physicalIndex];
        items[physicalIndex] = null;
        nextFirstIndex = physicalIndex;
        if(isLowUsage()){
            resize();
        }

        return item;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        int physicalIndex = (nextFirstIndex  - 1 + index) % capacity;
        return items[physicalIndex];
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    public  boolean isFull(){
        return this.nextFirstIndex == this.nextLastIndex;
    }

    public  void resize() {


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
}
