import org.junit.Test;

import static com.google.common.truth.Truth.*;

public class MinHeapTest {

    @Test
    public void findMinTest() {
        MinHeap<Integer> minHeap = new MinHeap<>();

        assertThat(minHeap.findMin()).isNull();
        assertThat(minHeap.size()).isEqualTo(0);

        minHeap.insert(8);
        assertThat(minHeap.findMin()).isEqualTo(8);
        assertThat(minHeap.size()).isEqualTo(1);

        minHeap.insert(9);
        minHeap.insert(6);
        minHeap.insert(4);

        assertThat(minHeap.findMin()).isEqualTo(4);
        assertThat(minHeap.size()).isEqualTo(4);
    }

    @Test
    public void insertAndRemoveTest() {
        MinHeap<Integer> minHeap = new MinHeap<>();

        assertThat(minHeap.findMin()).isNull();
        assertThat(minHeap.size()).isEqualTo(0);

        minHeap.insert(8);
        minHeap.insert(9);
        minHeap.insert(6);

        assertThat(minHeap.findMin()).isEqualTo(6);
        assertThat(minHeap.size()).isEqualTo(3);

        // remove to empty
        minHeap.removeMin();
        minHeap.removeMin();
        minHeap.removeMin();

        assertThat(minHeap.findMin()).isNull();
        assertThat(minHeap.size()).isEqualTo(0);

        // add again
        minHeap.insert(4);
        minHeap.insert(1);
        minHeap.insert(9);

        assertThat(minHeap.findMin()).isEqualTo(1);
        assertThat(minHeap.size()).isEqualTo(3);
    }
}
