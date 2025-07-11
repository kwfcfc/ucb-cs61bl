import deque.ArrayDeque61B;

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
        ArrayDeque61B<Integer> test = new ArrayDeque61B();
        test.addLast(1); // [1]

        test.addFirst(7); // [7,1]
        test.addLast(8); // [7,1,8]
        test.addLast(9); // [7,1,8,9]
        test.addFirst(-2); // [-2,7,1,8,9]

        assertWithMessage("List of Deque not correct").that(test.toList()).containsExactly(-2, 7, 1, 8, 9).inOrder();
    }
}
