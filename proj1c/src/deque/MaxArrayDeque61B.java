package deque;

import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T>{

    private Comparator<T> defaultComparator;
    public MaxArrayDeque61B(Comparator<T> c){
        super();
        this.defaultComparator = c;
    }

    public T max(){
        if (isEmpty()) {return null;}
        T maxItem = get(0);
        for (T item : this){
            if(defaultComparator.compare(item,maxItem)>0){
                maxItem = item;
            }
        }
        return maxItem;
    }

    public T max(Comparator<T> c){
        if (this.isEmpty()) {return null;}
        T maxItem = get(0);
        for (T item : this){
            if(c.compare(item,maxItem)>0){
                maxItem = item;
            }
        }
        return maxItem;
    }
}
