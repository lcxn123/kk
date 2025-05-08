package List;

public class SLList<Item> implements List61B<Item> {

    private static class Node<Item> {
        public Item item;
        public Node<Item> next;

        public Node(Item f, Node<Item> r) {
            item = f;
            next = r;
        }
    }

    private Node<Item> sentinel;
    private int size;

    /* create a new SLList with one item X */
    public SLList(Item x) {
        sentinel = new Node<>(null, null);
        sentinel.next = new Node<>(x, null);
        size = 1;
    }

    /* create a empty SLList */
    public SLList(){
        sentinel = new Node<>(null, null);
        size = 0;
    }


    @Override
    public void addFirst(Item x) {
        sentinel.next = new Node<>(x, sentinel.next);
        size += 1;
    }


    /* add item x in the front of first */

    public Item getFirst(){
        return sentinel.next.item;
    }



    public void addLast(Item x){
        Node<Item> p = sentinel;
        size += 1;

        while(p.next != null){
            p = p.next;
        }

        p.next = new Node<>(x, null);
    }

    @Override
    public Item getFirst(Item x) {
        return null;
    }

    @Override
    public Item getLast() {
        return null;
    }

    @Override
    public Item get(int x) {
        return null;
    }


    public int size(){
        return size;
    }

    @Override
    public Item removeLast() {
        return null;
    }

    public static void main(String[] args) {
        // Test the SLList with different types of items
        SLList<Integer> intList = new SLList<>();
        intList.addFirst(1);
        intList.addLast(2);
        System.out.println("First item: " + intList.getFirst()); // Should print 1
        System.out.println("Size: " + intList.size()); // Should print 2

        SLList<String> stringList = new SLList<>();
        stringList.addFirst("apple");
        stringList.addLast("banana");
        System.out.println("First item: " + stringList.getFirst()); // Should print "apple"
        System.out.println("Size: " + stringList.size()); // Should print 2
    }
}