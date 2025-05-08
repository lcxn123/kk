package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    @Override
    public void put(K key, V value) {
        if ((double) size / Capacity >= loadFactor){
            resize();
        }

        int bucketNum = Math.floorMod(key.hashCode(),Capacity);


        if (this.containsKey(key)) {
            for (Node node : buckets[bucketNum]){
                if (node.key.equals(key)){
                    node.value = value;
                }
            }
            return;
        }

        Node putNode = new Node(key,value);
        buckets[bucketNum].add(putNode);
        size++;
    }

    private void resize(){
        Capacity *= 2;
        Collection<Node>[] newBuckets = new Collection[Capacity];

        for (int i = 0; i < Capacity; i++) {
            newBuckets[i] = createBucket();
        }

        for(Collection<Node> bucket:buckets){
            for(Node n:bucket){
                int bucketNum = Math.floorMod(n.key.hashCode(),Capacity);
                newBuckets[bucketNum].add(n);
            }
        }

        buckets = newBuckets;

    }

    @Override
    public V get(K key) {
        if (buckets == null &&!this.containsKey(key)){return null;}

        int bucketNum = Math.floorMod(key.hashCode(),Capacity);
        V value = null;
        for (Node node : buckets[bucketNum]){
            if (node.key.equals(key)){
                value = node.value;
            }
        }
        return value;
    }

    @Override
    public boolean containsKey(K key) {
        int bucketNum = Math.floorMod(key.hashCode(),Capacity);
        for (Node node : buckets[bucketNum]){
            if (node.key.equals(key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        buckets = new Collection[Capacity];
        for (int i = 0; i < Capacity; i++) {
            buckets[i] = createBucket();
        }
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keyset = new TreeSet<>();
        for (int i = 0; i < Capacity ; i++) {
            for (Node node : buckets[i]){
                keyset.add(node.key);
            }
        }

        return keyset;
    }

    @Override
    public V remove(K key) {
        if (!this.containsKey(key)){return null;}

        int bucketNum = Math.floorMod(key.hashCode(),Capacity);
        V value = null;
        for (Node node : buckets[bucketNum]){
            if (node.key.equals(key)){
                value = node.value;
                buckets[bucketNum].remove(node);
            }
        }
        size--;
        return value;
    }

    @Override
    public Iterator<K> iterator() {
        return new HashMapIterator();
    }

    private class HashMapIterator<K> implements Iterator<K>{

        private int currentBucket;
        private Iterator<Node> currentIterator;
        private K nextK;

        public HashMapIterator(){
            currentBucket = 0;
            nextK = null;

            advance();
        }

        private void advance(){
            if (currentIterator != null && currentIterator.hasNext()){
                nextK = (K) currentIterator.next().key;
                return;
            }

            while (currentBucket < buckets.length){
                if (buckets[currentBucket] != null && !buckets[currentBucket].isEmpty()){
                    currentIterator = buckets[currentBucket].iterator();
                    if (currentIterator.hasNext()){
                        nextK = (K) currentIterator.next().key;
                        return;
                    }
                }
                currentBucket++;

            }
            nextK = null;
        }
        @Override
        public boolean hasNext() {
            return nextK != null;
        }

        @Override
        public K next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }

            K result = nextK;
            advance();
            return result;
        }
    }







    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private double loadFactor = 0.75;
    private int size = 0;
    private int Capacity = 16;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        buckets = new Collection[Capacity];

        for (int i = 0; i < 16; i++) {
            buckets[i] = createBucket();
        }
    }

    public MyHashMap(int initialCapacity) {
        Capacity = initialCapacity;
        buckets = new Collection[Capacity];

        for (int i = 0; i < initialCapacity; i++) {
            buckets[i] = createBucket();
        }

    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        this.loadFactor = loadFactor;
        Capacity = initialCapacity;

        buckets = new Collection[Capacity];

        for (int i = 0; i < initialCapacity; i++) {
            buckets[i] = createBucket();
        }


    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        // TODO: Fill in this method.
        return new LinkedList<>();
    }

}
