package List;

public interface List61B<Item> {

    /* add item x in the front of first */
    public void addFirst(Item x);

    public void addLast(Item x);
    public Item getFirst(Item x);
    public Item getLast();
    public Item get(int x);
    public int size();

    public Item removeLast();

    default public void print(){
        for (int i = 0; i < size(); i++) {
            System.out.print(get(i) + " ");
        }
    }
}
