package List;

public class SpeedTest {
    public static void main(String[] args) {
        AList L = new AList();
        int i = 0;
        while (i < 100000){
            L.addLast(i);
            i += 1;
        }
    }
}
