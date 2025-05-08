import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
//floorMod

public class ArrayDeque61B <T> implements Deque61B<T>{
    private int size;

    private T[] items;

    private int first;

    private int rear;

    private int capacity;

    public ArrayDeque61B() {
        capacity = 8;
        size = 0;
        first = 0;
        rear = 0;
        items = (T[]) new Object[8];
    }

    public void resize(){
        int newCapacity = capacity * 2;
        T[] newL = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newL[i] = items[Math.floorMod(first + i,capacity)];
        }
        capacity = newCapacity;
        items = newL;
        first = 0;
        rear = size;
    }

    @Override
    public void addFirst(T x) {
        if (size == capacity) {
            this.resize();
        }
        items[first] = x;
        first = Math.floorMod(first - 1,capacity);
        size++;
    }

    @Override
    public void addLast(T x) {
        if (size == capacity) {
            this.resize();
        }
        items[rear] = x;
        rear = Math.floorMod(rear + 1, capacity);
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> L = new ArrayList<>(capacity);
        for (T item : this.items) {
            L.add(item);
        }
        return L;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        T returnItem = items[first];
        size--;
        first = Math.floorMod(first + 1,capacity);
        return returnItem;
    }

    @Override
    public T removeLast() {
        T returnItem = items[rear];
        size--;
        rear = Math.floorMod(rear - 1,capacity);
        return returnItem;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        return this.items[index - 1];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
