package two_;

public class QuikMaths {
    public static void multiplyBy3(int[] A){
        for (int i = 0; i < A.length; i += 1) {
            int x = A[i];
            x = x * 3;
        }
    }

    public static void multiplyBy2(int[] A) {
        int[] B = A;
        for (int i = 0; i < B.length; i+= 1) {
            B[i] *= 2;
        }
    }

    public static void swap(int A, int B) {
        int temp = B;
        B = A;
        A = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 3, 4};
        multiplyBy3(arr); // Value of arr: {2,3,3,4}
        arr = new int[]{2, 3, 3, 4};
        multiplyBy2(arr); // Value of arr: {4,6,6,8}
        int a = 6;
        int b = 7;
        /* Value of a: 7 Value of b: 6 */
        //TODO error
        //TODO answer a:6 b:7 Java按值传递，但只是传递了值的副本
        swap(a, b);
    }



}
