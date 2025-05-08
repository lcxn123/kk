import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        // TODO: Fill in this function.
        if (L == null) {
            return 0;
        }
        int result = 0;
        for (int i : L) {
            result += i;
        }
        return result;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        // TODO: Fill in this function.
        List<Integer> lst = new ArrayList<>();
        for (int i : L) {
            if (i % 2 == 0) {
                lst.add(i);
            }
        }
        return lst;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Fill in this function.
        List<Integer> lst = new ArrayList<>();
        for(int i : L1){
            if (L2.contains(i)) {
                lst.add(i);
            }
        }
        return lst;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Fill in this function.
        int count = 0;
        for (String word : words) {
            for (int i = 0; i <word.length() ; i++) {
                if (word.charAt(i) == c){
                    count += 1;
                }
            }
        }
        return count;
    }
}
