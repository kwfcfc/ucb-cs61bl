import org.junit.Test;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class BinarySearchTreeTest {

    @Test
    public void containsAndAddTest() {
        BinarySearchTree<Double> bst = new BinarySearchTree<>();

        assertThat(bst.contains(8.9)).isFalse();
        bst.add(6.4);

        assertThat(bst.contains(8.9)).isFalse();
        assertThat(bst.contains(6.4)).isTrue();

        bst.add(8.9);
        assertThat(bst.contains(8.9)).isTrue();

        bst.add(6.4);
        bst.delete(8.9);
        bst.delete(6.4);
        assertThat(bst.contains(8.9)).isFalse();
    }

    @Test
    public void containsTest() {
        BinarySearchTree<Integer> x = new BinarySearchTree<>();
        assertWithMessage("BST should not contain anything immediately after instantiation").that(x.contains(2)).isFalse();
        x.add(2);
        assertWithMessage("BST should contain 2 after adding 2").that(x.contains(2)).isTrue();
        x.add(3);
        assertWithMessage("BST should contain 3 after adding 3").that(x.contains(3)).isTrue();
        x.add(4);
        assertWithMessage("BST should contain 4 after adding 4").that(x.contains(4)).isTrue();
        x.add(2);
        x.delete(2);
        assertWithMessage("BST should not contain 2 after deleting 2").that(x.contains(2)).isFalse();
    }
}
