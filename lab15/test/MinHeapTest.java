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
        assertThat(minHeap.removeMin()).isEqualTo(6);
        assertThat(minHeap.removeMin()).isEqualTo(8);
        assertThat(minHeap.removeMin()).isEqualTo(9);

        assertThat(minHeap.findMin()).isNull();
        assertThat(minHeap.size()).isEqualTo(0);

        // add again
        minHeap.insert(4);
        minHeap.insert(1);
        minHeap.insert(9);

        assertThat(minHeap.findMin()).isEqualTo(1);
        assertThat(minHeap.size()).isEqualTo(3);
    }

    @Test
    public void containTest() {
        MinHeap<Integer> minHeap = new MinHeap<>();

        minHeap.insert(8);
        minHeap.insert(9);
        minHeap.insert(6);

        assertThat(minHeap.size()).isEqualTo(3);
        assertThat(minHeap.contains(8)).isTrue();

        // remove to empty
        assertThat(minHeap.removeMin()).isEqualTo(6);
        assertThat(minHeap.removeMin()).isEqualTo(8);

        assertThat(minHeap.contains(8)).isFalse();
        assertThat(minHeap.contains(9)).isTrue();

        assertThat(minHeap.removeMin()).isEqualTo(9);

        assertThat(minHeap.findMin()).isNull();
        assertThat(minHeap.size()).isEqualTo(0);

        // add again
        minHeap.insert(4);
        minHeap.insert(1);
        minHeap.insert(9);

        assertThat(minHeap.contains(9)).isTrue();
        assertThat(minHeap.contains(6)).isFalse();
        assertThat(minHeap.contains(4)).isTrue();
    }

    public class testPair<K extends Comparable<K>, V> implements Comparable<testPair<K, V>> {
        K key;
        V value;

        public testPair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() { return key.toString(); }

        @Override
        public int compareTo(testPair<K, V> o) {
            return key.compareTo(o.getKey());
        }
    }

    @Test
    public void updateTest() {
        MinHeap<testPair<Integer, String>> minHeap = new MinHeap<>();

        testPair<Integer, String> pairMonth = new testPair<>(6, "June");
        testPair<Integer, String> pairDay = new testPair<>(4, "four");
        testPair<Integer, String> pairWeek = new testPair<>(3, "three");
        testPair<Integer, String> pairYear = new testPair<>(1, "1998");

        minHeap.insert(pairMonth);
        minHeap.insert(pairDay);
        minHeap.insert(pairWeek);
        minHeap.insert(pairYear);

        assertThat(minHeap.findMin().getValue()).isEqualTo("1998");
        assertThat(minHeap.size()).isEqualTo(4);

        pairDay.setValue("fourth");
        minHeap.update(pairDay);

        assertThat(minHeap.contains(pairDay)).isTrue();

        assertThat(minHeap.removeMin().getValue()).isEqualTo("1998");
        assertThat(minHeap.removeMin().getValue()).isEqualTo("three");
        assertThat(minHeap.findMin().getValue()).isEqualTo("fourth");
    }
}
