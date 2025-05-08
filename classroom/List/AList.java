package List;

public class AList<Item> implements List61B<Item>{
    private Item[] items;
    private int size;

    @SuppressWarnings("unchecked")
    public AList(){
        items = (Item[]) new Object[100];
    }

    private void resize(int capacity){
        @SuppressWarnings("unchecked")
        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(items,0,a,0,size);
        items = a;
    }


    @Override
    public void addFirst(Item x) {

    }

    public void addLast(Item x){
        if(size == items.length){
            resize(size * 2);
        }
        items[size] = x;
        size += 1;
    }

    @Override
    public Item getFirst(Item x) {
        return null;
    }



    public Item getLast(){
        return items[size - 1];
    }

    public Item get(int i){
        return items[i];
    }

    public int size(){
        return size;
    }

    public Item removeLast(){
        if(size <= items.length / 4){
            resize(items.length / 2);
        }
        Item x = items[size -1];
        items[size -1 ] = null;
        size -= 1;
        return x;
    }
}