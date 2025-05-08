package two_;

class IntWrapper {
    int x;
    public IntWrapper(int value) {
        x = value;
    }
}
public class SwapPrimitives {
    public static void main(String[] args) {
        int a = 6;
        int b = 7;
        //TODO 下面填写 T
        IntWrapper x = new IntWrapper(a);
        IntWrapper y = new IntWrapper(b);
        swap(x, y);
        a = x.x;
        b = y.x;
    }
    public static void swap(IntWrapper x ,IntWrapper y) {
        //TODO 下面填写 T
        int temp = x.x;
        x.x = y.x;
        y.x = temp;
    }
}