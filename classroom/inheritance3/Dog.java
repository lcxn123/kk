package inheritance3;

public class Dog implements Comparable<Dog>{
    public String name;
    private int size;
    public Dog(String n,int s){
        name = n;
        size = s;
    }
    public void bark(){
        System.out.println(name + " says: bark");
    }

    @Override
    public int compareTo(Dog otherDog) {
        return this.size - otherDog.size;
    }

//    public static class NameComparator implements Comparable<Dog>{
//
//        @Override
//        public int compare(Dog d1,Dog d2) {
//            return d1.name.compareTo(d2.name);
//        }
//    }
}
