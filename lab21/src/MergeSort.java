import java.util.Arrays;

public class MergeSort {


    /**
     * @param arr Input array
     *
     * Sort the array arr using merge sort.
     * The merge sort algorithm is as follows:
     * 1. Split the collection to be sorted in half.
     * 2. Recursively call merge sort on each half.
     * 3. Merge the sorted half-lists.
     *
     */
    public static int[] sort(int[] arr) {
        if (arr.length <= 1)
            return arr;

        int middle = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);
        left = sort(left);
        right = sort(right);
        return merge(left, right);
    }

    /**
     * @param a Left merge array
     * @param b Right merge array
     *
     * Merge the sorted half-lists.
     *
     * Suggested helper method that will make it easier for you to implement merge sort.
     */
    private static int[] merge(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int left_i = 0;
        int right_i = 0;
        int i = 0;

        while (left_i < a.length && right_i < b.length) {
            if (a[left_i] <= b[right_i]) {
                c[i] = a[left_i];
                left_i++;
            } else {
                c[i] = b[right_i];
                right_i++;
            }
            i++;
        }

        if (left_i < a.length) {
            System.arraycopy(a, left_i, c, i, a.length - left_i);
        } else if (right_i < b.length) {
            System.arraycopy(b, right_i, c, i, b.length - right_i);
        }
        return c;
    }
}

