package inheritance3;


public class Maximizer {

    public static Comparable max(Comparable[] items){
        int maxDex = 0;
        for (int i = 0; i < items.length; i++) {
            int cmp = items[i].compareTo(items[maxDex]);
            if (cmp > 0) {
                maxDex = i;
            }
        }
        return items[maxDex];
    }

    public static void main(String[] args) {
        Dog d1 = new Dog("lll",12);
        Dog d2 = new Dog("ccc",13);
        Dog d3 = new Dog("xxx",14);

        Dog[] dogs = new Dog[]{d1,d2,d3};
        Dog largest = (Dog) Maximizer.max(dogs);
        largest.bark();

//        Dog.NameComparator nc = new Dog.NameComparator();
//        int cmp = nc.compare(d1,d2);
    }
}
