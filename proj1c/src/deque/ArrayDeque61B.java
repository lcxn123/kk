package deque;

import java.util.*;
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
        items = (T[]) new Object[capacity];
    }

    public ArrayDeque61B(int c , T defaultValue) {
        capacity = c;
        size = c;
        first = 0;
        rear = 0;
        items = (T[]) new Object[capacity];
        if (defaultValue != null) {
            Arrays.fill(items, defaultValue); // 填充默认值
        }
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
        items[first] = x;
        first = Math.floorMod(first - 1,capacity);
        size++;
    }

    @Override
    public void addLast(T x) {
        items[rear] = x;
        rear = Math.floorMod(rear + 1, capacity);
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> L = new ArrayList<>(capacity);
        for (int i = 0; i < size; i++) {
            int index = Math.floorMod(first + i,capacity);
            L.add(items[index]);
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
        Iterator<T> iterator = iterator();
        T item = null;
        for (int i = 0; i <= index; i++) {
            item = iterator.next(); // 移动到目标索引
        }
        return item;
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    //iterator

    private class ArrayIterator<T> implements Iterator<T>{
        private int Pos;
        private int count;
        public ArrayIterator(){Pos = first;count = 0;}
        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T returnItem = (T) items[Pos];
            Pos = (Pos + 1) % capacity;
            count++;
            return returnItem;
        }
    }


    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<>();
    }


    @Override
    public boolean equals(Object o){
        if(o instanceof ArrayDeque61B otherArray){
            if (this.size != otherArray.size) {
                return false;
            }
           Iterator<T> seer1 = ((ArrayDeque61B<T>) o).iterator();
           Iterator<T> seer2 = otherArray.iterator();
           while (seer1.hasNext() && seer2.hasNext()){
               T item1 = seer1.next();
               T item2 = seer2.next();
               if (item1 != item2){
                   return false;
               }
           }
           return true;
        }
        return false;
    }

    @Override
    public String toString(){
        StringBuilder x = new StringBuilder();
        x.append("(");

        for (T i : this) {
            x.append(i.toString());
            x.append(" ");
        }
        x.append(")");
        return x.toString();
    }

    public static void main(String[] args) {
        ArrayDeque61B<Integer> a = new ArrayDeque61B<>();
        a.addFirst(3);
        a.addFirst(2);
        a.addFirst(1);
        a.addLast(4);
        for(int item : a){
            System.out.println(item);
        }

        ArrayDeque61B<Double> b = new ArrayDeque61B<>(10,0.0);
        Double value = b.get(0);
    }
}

