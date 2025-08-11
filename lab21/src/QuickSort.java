public class QuickSort {

    /**
     * @param arr
     *
     * Sort the array arr using quicksort with the 3-scan partition algorithm.
     * The quicksort algorithm is as follows:
     * 1. Select a pivot, partition array in place around the pivot.
     * 2. Recursively call quicksort on each subsection of the modified array.
     */
    public static int[] sort(int[] arr) {
        quickSort(arr, 0, arr.length);
        return arr;
    }

    /**
     * @param arr
     * @param start
     * @param end
     *
     * Helper method for sort: runs quicksort algorithm on array from [start:end)
     */
    private static void quickSort(int[] arr, int start, int end) {
        if (end <= start + 1)
            return;

        int[] slice = partition(arr, start, end);
        quickSort(arr, start, slice[0]);
        quickSort(arr, slice[1], end);
    }

    // set the start of the arr as the pivot from [start:end)
    private static void setPivot(int[] arr, int start, int end) {
        if (end - start <= 2)
            return;
        int middle = start + (end - start) / 2;
        int a = arr[start], b = arr[middle], c = arr[end - 1];
        if ((a <= b && b <= c) || (c <= b && b <= a))
            swap(arr, start, middle);
        else if ((a <= c && c <= b) || (b <= c && c <= a))
            swap(arr, start, end - 1);
    }

    /**
     * @param arr
     * @param start
     * @param end
     *
     * Partition the array in-place following the 3-scan partitioning scheme.
     * You may assume that first item is always selected as the pivot.
     * 
     * Returns a length-2 int array of indices:
     * [end index of "less than" section, start index of "greater than" section]
     *
     * Most of the code for quicksort is in this function
     */
    private static int[] partition(int[] arr, int start, int end) {
        if (end <= start + 1)
            return new int[] { start, end };
        setPivot(arr, start, end);
        int pivot = arr[start];

        int iter = start + 1;
        int left = start;
        int right = end;

        // 3-scan partition
        while (iter < right) {
            // left part is partitioned
            if (arr[iter] < pivot) {
                swap(arr, iter, left);
                ++left;
                ++iter;
            } else if (arr[iter] > pivot) {
                // right part is not yet partitioned, do not increment i and 
                // compare the new element 
                --right;
                swap(arr, iter, right);
            } else {
                // equal to pivot, ignore the part
                ++iter;
            }
        }

        return new int[] { left, right };
    }

    /**
     * @param arr
     * @param i
     * @param j
     *
     * Swap the elements at indices i and j in the array arr.
     * A helper method you can use in your implementation of sort.
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}   
