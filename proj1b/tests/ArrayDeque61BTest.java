import deque.ArrayDeque61B;

import deque.Deque61B;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {
    @Test
    @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
    void noNonTrivialFields() {
        List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                .toList();

        assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
    }

    @Test
    @DisplayName("ArrayDeque61B has constant time add")
    void addFirstAndAddLast() {
        Deque61B<Integer> test = new ArrayDeque61B();
        test.addLast(1); // [1]
        assertWithMessage("List size not correct").that(test.size()).isEqualTo(1);

        test.addFirst(7); // [7,1]
        test.addLast(8); // [7,1,8]
        test.addLast(9); // [7,1,8,9]
        test.addFirst(-2); // [-2,7,1,8,9]

        assertWithMessage("List of Deque not correct").that(test.toList()).containsExactly(-2, 7, 1, 8, 9).inOrder();
    }

    @Test
    @DisplayName("ArrayDeque61B has constant time get")
    void getTest1() {
        Deque61B<String> test = new ArrayDeque61B();
        test.addLast("m"); // [m]

        test.addFirst("a"); // [a,m]
        test.addLast("g"); // [a,m,g]
        test.addLast("e"); // [a,m,g,e]
        test.addFirst("E"); // [E,a,m,g,e]

        assertWithMessage("List size not correct").that(test.size()).isEqualTo(5);
        assertWithMessage("Get element 0 not correct").that(test.get(0)).isEqualTo("E");
        assertWithMessage("Get element 3 not correct").that(test.get(3)).isEqualTo("g");
        assertWithMessage("Get negative index should be null").that(test.get(-3)).isNull();
        assertWithMessage("Get out of bound index should be null").that(test.get(7)).isNull();
    }

    @Test
    @DisplayName("ArrayDeque61B has constant time remove first and remove last")
    void removeFirstAndRemoveLast() {
        Deque61B<String> test = new ArrayDeque61B();
        test.addLast("m"); // [m]

        test.addFirst("a"); // [a,m]
        test.addLast("g"); // [a,m,g]
        test.addLast("e"); // [a,m,g,e]
        test.addFirst("E"); // [E,a,m,g,e]

        assertWithMessage("List size not correct").that(test.size()).isEqualTo(5);
        assertWithMessage("remove first not correct").that(test.removeFirst()).isEqualTo("E");
        assertWithMessage("remove first size not correct").that(test.size()).isEqualTo(4);
        test.removeFirst();
        test.removeFirst();
        assertWithMessage("remove last not correct").that(test.removeLast()).isEqualTo("e");
        assertWithMessage("remove last size not correct").that(test.size()).isEqualTo(1);
        assertWithMessage("list should be not empty").that(test.isEmpty()).isFalse();
        test.removeLast();
        assertWithMessage("list should be empty").that(test.isEmpty()).isTrue();
        assertWithMessage("remove last empty list not correct").that(test.removeLast()).isNull();
        assertWithMessage("remove first empty list not correct").that(test.removeLast()).isNull();
    }
}
