import deque.Deque61B;
import deque.LinkedListDeque61B;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {


    /**
     * In this test, we have three different assert statements that verify that addFirst works
     * correctly.
     */
    @Test
    public void addFirstTestBasic() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst("back"); // after this call we expect: ["back"]
        assertThat(lld1.toList()).containsExactly("back").inOrder();

        lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
        assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

        lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

        /*
         * Note: The first two assertThat statements aren't really necessary. For example, it's hard
         * to imagine a bug in your code that would lead to ["front"] and ["front", "middle"]
         * failing, but not ["front", "middle", "back"].
         */
    }


    /**
     * In this test, we use only one assertThat statement. IMO this test is just as good as
     * addFirstTestBasic. In other words, the tedious work of adding the extra assertThat statements
     * isn't worth it.
     */
    @Test
    public void addLastTestBasic() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
    }


    /** This test performs interspersed addFirst and addLast calls. */
    @Test
    public void addFirstAndAddLastTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        /*
         * I've decided to add in comments the state after each call for the convenience of the
         * person reading this test. Some programmers might consider this excessively verbose.
         */
        lld1.addLast(0); // [0]
        lld1.addLast(1); // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2); // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
    }

    @Test
    public void addFirstAfterRemoveToEmptyTest() {
        Deque61B<Double> lld1 = new LinkedListDeque61B<>();

        lld1.addLast(0.3); // [0.3]
        lld1.addLast(1.7); // [0.3, 1.7]
        lld1.addFirst(2.0); // [2.0, 0.3, 1.7]

        assertThat(lld1.size()).isEqualTo(3);

        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        // addfirst after remove
        lld1.addFirst(-8.3);
        lld1.addFirst(2.6);
        assertThat(lld1.toList()).containsExactly(2.6, -8.3).inOrder();
    }

    @Test
    public void addLastAfterRemoveToEmptyTest() {
        Deque61B<Double> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.toList().isEmpty()).isTrue();

        lld1.addLast(0.3); // [0.3]
        lld1.addLast(1.7); // [0.3, 1.7]
        lld1.addFirst(2.0); // [2.0, 0.3, 1.7]

        assertThat(lld1.size()).isEqualTo(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeFirst();

        // addfirst after remove
        lld1.addLast(8.4);
        lld1.addLast(-1.9);
        assertThat(lld1.toList()).containsExactly(8.4, -1.9).inOrder();
    }

    // Below, you'll write your own tests for LinkedListDeque61B.
    @Test
    public void isEmptyTest() {
        Deque61B<Boolean> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.isEmpty()).isTrue();

        lld1.addFirst(true);

        assertThat(lld1.isEmpty()).isFalse();
    }

    @Test
    public void sizeTest() {
        Deque61B<Double> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.size()).isEqualTo(0);

        lld1.addFirst(8.9);
        lld1.addLast(6.4);

        assertThat(lld1.size()).isEqualTo(2);
    }

    @Test
    public void getTest() {
        Deque61B<Double> lld1 = new LinkedListDeque61B<>();

        assertWithMessage("get 0 from empty list").that(lld1.get(0)).isNull();

        lld1.addLast(7.3);
        lld1.addFirst(23.4);
        lld1.addFirst(-2.4);

        assertWithMessage("get negative index should return null").that(lld1.get(-1)).isNull();
        assertWithMessage("get out of bound index should return null").that(lld1.get(8)).isNull();
        assertThat(lld1.get(2)).isEqualTo(7.3);
        assertThat(lld1.get(0)).isEqualTo(-2.4);
        assertThat(lld1.get(1)).isEqualTo(23.4);
    }

    @Test
    public void getRecursiveTest() {
        Deque61B<Double> lld1 = new LinkedListDeque61B<>();

        assertWithMessage("get 0 from empty list").that(lld1.getRecursive(0)).isNull();

        lld1.addLast(7.3);
        lld1.addFirst(23.4);
        lld1.addFirst(-2.4);

        assertWithMessage("get negative index should return null").that(lld1.getRecursive(-1)).isNull();
        assertWithMessage("get out of bound index should return null").that(lld1.getRecursive(8)).isNull();
        assertThat(lld1.getRecursive(2)).isEqualTo(7.3);
        assertThat(lld1.getRecursive(0)).isEqualTo(-2.4);
        assertThat(lld1.getRecursive(1)).isEqualTo(23.4);
    }

    @Test
    public void removeFirstAndRemoveLastTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(0);
        lld1.addLast(1);
        lld1.addFirst(-1);
        lld1.addLast(2);
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        assertThat(lld1.removeFirst()).isEqualTo(-2);
        assertThat(lld1.removeFirst()).isEqualTo(-1);
        assertThat(lld1.toList()).containsExactly(0, 1, 2).inOrder();
        assertThat(lld1.size()).isEqualTo(3);

        assertThat(lld1.removeLast()).isEqualTo(2);
        assertThat(lld1.toList()).containsExactly(0, 1).inOrder();
        assertThat(lld1.size()).isEqualTo(2);

        assertThat(lld1.removeLast()).isEqualTo(1);
        assertThat(lld1.removeLast()).isEqualTo(0);

        // now the list is empty
        assertThat(lld1.size()).isEqualTo(0);
        assertThat(lld1.removeFirst()).isNull();
        assertThat(lld1.removeLast()).isNull();
    }

    @Test
    public void removeFirstToEmptyTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(0);
        lld1.addLast(7);
        lld1.addFirst(-7);
        lld1.addLast(13);
        lld1.addFirst(-13); // [-13, -7, 0, 7, 13]
    }

    @Test
    @DisplayName("LinkedListDeque implements equal")
    void equalTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        Deque61B<Integer> lld2 = new LinkedListDeque61B<>();
        lld1.addLast(0);
        lld1.addLast(1);

        assertThat(lld1).isNotEqualTo(lld2);

        lld2.addFirst(1);
        lld2.addFirst(0);
        assertThat(lld1).isEqualTo(lld2);
        assertThat(lld1).isNotEqualTo(null);
        assertThat(lld1).isNotEqualTo(List.of(0, 1));
        assertThat(lld1).isEqualTo(lld1);
    }
}
