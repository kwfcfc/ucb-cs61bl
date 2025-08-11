import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class DistributionSortsTest {

    @Test
    public void testBasic() {
        int[] arr = { 1, 1, 3, 4, 5, 7, 8, 9, 9, 7, 8, 9, 0, 0, 2, 2, 4, 5 };
        int[] expected = { 0, 0, 1, 1, 2, 2, 3, 4, 4, 5, 5, 7, 7, 8, 8, 9, 9, 9 };

        DistributionSorts.countingSort(arr);
        assertThat(arr).isEqualTo(expected);
    }
}
