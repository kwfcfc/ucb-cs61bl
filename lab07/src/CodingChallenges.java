import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class CodingChallenges {

    /**
     * Return the missing number from an array of length N containing all the
     * values from 0 to N except for one missing number.
     */
    public static int missingNumber(int[] values) {
        boolean found[] = new boolean[values.length + 1];
        Arrays.fill(found, true);
        for (int num: values) {
            found[num] = false;
        }
        for (int i = 0; i < found.length; i++) {
            if (found[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns true if and only if s1 is a permutation of s2. s1 is a
     * permutation of s2 if it has the same number of each character as s2.
     */
    public static boolean isPermutation(String s1, String s2) {
        if (s1 == null ^ s2 == null) {
            return false;
        }
        if (s1.length() != s2.length()) {
            return false;
        }
        HashMap<Character, Integer> count = new HashMap<>();
        for (char c: s1.toCharArray()) {
            if (count.get(c) == null) {
                count.put(c, 1);
            } else {
                count.put(c, count.get(c) + 1);
            }
        }
        // now use the count and the s2 char array.
        for (char c: s2.toCharArray()) {
            if (count.get(c) == null) {
                return false;
            } else if (count.get(c) == 1) {
                count.remove(c);
            } else {
                count.put(c, count.get(c) - 1);
            }
        }
        return count.isEmpty();
    }
}
