package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
    private Comparator<T> comparator;
    public MaxArrayDeque(Comparator<T> c){
        this.comparator = c;
    }

    public T max(){
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }
        return max(comparator);

    }


    public T max(Comparator<T> c){
        if(isEmpty()){
            return null;
        }
        T maxItem = get(0);
        for(T item: this) {
            if (c.compare(item, maxItem) > 0) {
                maxItem = item;
            }
        }
        return maxItem;
    }

}
