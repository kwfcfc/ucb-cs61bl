import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.*;


public class MinHeapPQTest {

    @Test
    public void testAddOneThing() {
        MinHeapPQ<String> pq = new MinHeapPQ<>();
        pq.insert("l", 2);
        String item = pq.poll();
        assertThat("l").isEqualTo(item);
    }

    @Test
    public void testAddThenRemove() {
        MinHeapPQ<String> pq = new MinHeapPQ<>();
        pq.insert("h", 100);
        pq.insert("i", 0);
        String item = pq.poll();
        assertThat("i").isEqualTo(item);
        assertThat("h").isEqualTo(pq.poll());
    }

    /**
     * Tests that a MinHeapPQ can add and remove a single element.
     */
    @Test
    public void testOneThing() {
        MinHeapPQ<String> pq = new MinHeapPQ<>();
        assertThat(pq.poll()).isNull();
        pq.insert("l", 2);
        assertThat(1).isEqualTo(pq.size());
        String item = pq.poll();
        assertThat("l").isEqualTo(item);
        assertThat(0).isEqualTo(pq.size());
    }

    // TODO: add some of your own tests here!

    @Test
    public void heapAlphabetTest() {
        MinHeapPQ<Character> pq = new MinHeapPQ<>();
        List<Character> alphabet = new ArrayList<>();

        for (char c = 'z'; c >= 'a'; c--) {
            pq.insert(c, c - 'a' + 1);
        }

        while (pq.size() > 0) {
            alphabet.add(pq.poll());
        }

        List<Character> expected = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            expected.add(c);
        }

        assertThat(alphabet).containsExactly(expected.toArray()).inOrder();
    }

    @Test
    public void changePriorityTest() {
        MinHeapPQ<String> pq = new MinHeapPQ<>();

        pq.insert("l", 2);
        pq.insert("i", 0);
        pq.insert("h", 18);
        pq.insert("a", 10);

        assertThat(pq.peek()).isEqualTo("i");

        pq.changePriority("i", 5);

        assertThat(pq.poll()).isEqualTo("l");
        assertThat(pq.poll()).isEqualTo("i");
        assertThat(pq.poll()).isEqualTo("a");
        assertThat(pq.poll()).isEqualTo("h");
    }
}
