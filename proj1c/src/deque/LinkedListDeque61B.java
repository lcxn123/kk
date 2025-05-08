package deque;

import java.util.ArrayList; // import the ArrayList class
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T>{

    // Node
    private static class Node<T>{
        public T item;
        public Node<T> next;

        public Node<T> prev;

        public Node(T item, Node<T> next, Node<T> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    // sentinel size tail

    private Node<T> sentinel;
    private int size;
    private Node<T> tail;


    // 构造函数
    public LinkedListDeque61B(){
        sentinel = new Node<>(null,null,null);
        tail = sentinel;
        size = 0;
    }



    @Override
    public void addFirst(T x) {
        Node<T> content = sentinel.next;
        sentinel.next = new Node<>(x,content,sentinel);
        if (content == null) {
            tail = sentinel.next;
        }
        size += 1;
    }

    @Override
    public void addLast(T x) {
        Node<T> p = new Node<>(x,null,tail);
        tail.next = p;
        tail = p;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node<T> p = sentinel;

        while(p.next != null){
            p = p.next;
            returnList.add(p.item);
        }
        return returnList;
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
        //当列表为空
        if(this.size == 0){
            return null;
        }

        Node<T> p = sentinel.next;
        //当列表只有一个元素
        if (this.size == 1) {
            tail = sentinel;
            sentinel.next = null;
            size = 0;
        }

        // 当列表有多个元素
        if (this.size > 1) {
            sentinel.next = p.next;
            p.next.prev = sentinel;
            size -= 1;
        }
        return p.item;
    }

    @Override
    public T removeLast() {
        //当列表为空
        if(this.size == 0){
            return null;
        }

        Node<T> p = tail;
        if (this.size >= 1) {
            tail = p.prev;
            tail.next = null;
            size -= 1;
        }
        return p.item;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        Node<T> p = sentinel;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    @Override
    public T getRecursive(int index) {
        if (  index < 0 || index > size) {
            return null;
        }
        return getFromHead(sentinel,index);
    }

    private T getFromHead(Node<T> current, int remainingSteps) {
        // 终止条件：剩余步数为0时返回当前节点值
        if (remainingSteps == 0) {
            return current.item;
        }
        // 递归：移动到下一个节点，步数减1
        return getFromHead(current.next, remainingSteps - 1);
    }


    private class ArraySetIterator<T> implements Iterator<T>{

        private int count;
        private Node<T> currnet;

        public ArraySetIterator(){
            count = 0;
            currnet = (Node<T>) sentinel;
        }
        @Override
        public boolean hasNext() {
            return currnet.next != null;
        }

        @Override
        public T next() {
            currnet = currnet.next;
            T returnItem = currnet.item;
            return returnItem;
        }
    }

    public Iterator<T> iterator(){
        return new ArraySetIterator<>();
    }



    @Override
    public boolean equals(Object o){
        if(o instanceof LinkedListDeque61B otherLink){
            if (this.size != otherLink.size) {
                return false;
            }
            Iterator<T> seer1 = ((LinkedListDeque61B<T>) o).iterator();
            Iterator<T> seer2 = otherLink.iterator();
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

        for(T i : this){
            x.append(i.toString());
            x.append("-->");
        }
        x.append(")");
        return x.toString();
    }


    public static void main(String[] args) {
        LinkedListDeque61B<Integer> L = new LinkedListDeque61B<>();
        L.addFirst(1);
        L.addLast(2);
        L.addLast(3);
        System.out.println(L.toList());

        for (int i : L){
            System.out.println(i);
        }
    }
}
