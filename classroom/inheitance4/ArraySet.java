package inheitance4;

import java.util.Iterator;

public class ArraySet<T> implements Iterable<T>{
    private int size;
    private T[] items;

    public ArraySet() {
        this.size = 0 ;
        this.items = (T[]) new Object[100];
    }

    public void add(T x){
        if (contains(x)) {
            return;
        }
        items[size] = x;
        size += 1;
    }

    public boolean contains(T x){
        for (int i = 0; i < size; i++) {
            if (items[i].equals(x)) {
                return true;
            }
        }
        return false;
    }

    // Return a Iterator

    private class ArraySetIterator<T> implements Iterator<T>{

        private int Pos;

        public ArraySetIterator(){
            Pos = 0;
        }
        @Override
        public boolean hasNext() {
            return Pos < size;
        }

        @Override
        public T next() {
            T returnItem = (T) items[Pos];
            Pos += 1;
            return returnItem;
        }
    }

    public Iterator<T> iterator(){
        return new ArraySetIterator<>();
    }

    // toString
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

    // equals
    @Override
    public boolean equals(Object o){
        if(o instanceof ArraySet otherArrayset){
            if(this.size != otherArrayset.size){
                return false;
            }

            for(T i : this){
                if(! otherArrayset.contains(i)){
                    return false;
                }
            }

            return true;


        }
        return false; // o not a arrayset
    }


    public static void main(String[] args) {
        //test

        ArraySet<Integer> S = new ArraySet<>();
        S.add(3);
        S.add(32);
        S.add(88);

//        System.out.println(S.contains(32));
//        System.out.println(S.contains(30));

        // Iterator
//        Iterator<Integer> seer = S.iterator();
//        while(seer.hasNext()){
//            int x = seer.next();
//            System.out.println(x);
//        }


        // Nice Iterator

//        for (int i : S) {
//            System.out.println(i);
//        }



        //toString

//        System.out.println(S);


        // equals.

        ArraySet<Integer> S2 = new ArraySet<>();
        S2.add(3);
        S2.add(32);
        S2.add(88);

        System.out.println(S == S2);
        System.out.println(S.equals(S2));

    }

}


