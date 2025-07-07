import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

public class ArrayExercises {

    /** Returns an array [1, 2, 3, 4, 5, 6] */
    public static int[] makeDice() {
        int dice[] = { 1, 2, 3, 4, 5, 6 };
        return dice;
    }

    /**
     * Returns the positive difference between the maximum element and minimum
     * element of the given array.
     * Assumes array is nonempty.
     */
    public static int findMinMax(int[] array) {
        // Source about IntSummaryStatistics
        // https://stackoverflow.com/questions/1484347/finding-the-max-min-value-in-an-array-of-primitives-using-java
        IntSummaryStatistics stat = Arrays.stream(array).summaryStatistics();
        int min = stat.getMin();
        int max = stat.getMax();
        return max - min;
    }

}
