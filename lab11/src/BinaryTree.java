import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T> {

    // Do not modify the TreeNode class.
    static class TreeNode<T> {
        T item;
        TreeNode<T> left;
        TreeNode<T> right;

        public TreeNode(T item) {
            this.item = item;
            left = right = null;
        }

        public TreeNode(T item, TreeNode<T> left, TreeNode<T> right) {
            this.item = item;
            this.left = left;
            this.right = right;
        }

        public T getItem() {
            return item;
        }

        public TreeNode<T> getLeft() {
            return left;
        }

        public TreeNode<T> getRight() {
            return right;
        }
    }

    // protected gives subclasses the ability to access this instance variable,
    // but not other classes.
    TreeNode<T> root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(TreeNode<T> t) {
        root = t;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    /** Optional constructor, see optional exercise in lab (or last week's theoretical lab). */
    public BinaryTree(ArrayList<T> pre, ArrayList<T> in) {
        root = TreeNodeHelper(pre, in);
    }

    private TreeNode<T> TreeNodeHelper(List<T> pre, List<T> in) {
        if (pre.isEmpty() || in.isEmpty()) {
            return null;
        }

        T rootItem = pre.getFirst();
        int inorderRoot = in.indexOf(rootItem);

        TreeNode<T> root = new TreeNode<>(rootItem);

        List<T> leftPre = pre.subList(1, inorderRoot + 1);
        List<T> rightPre = pre.subList(inorderRoot + 1, pre.size());
        List<T> leftIn = in.subList(0, inorderRoot);
        List<T> rightIn = in.subList(inorderRoot + 1, in.size());

        root.left = TreeNodeHelper(leftPre, leftIn);
        root.right = TreeNodeHelper(rightPre, rightIn);
        return root;
    }

    /* Print the values in the tree in preorder. */
    public void printPreorder() {
        if (root == null) {
            System.out.println("(empty tree)");
        } else {
            printPreorderHelper(root);
            System.out.println();
        }
    }

    private void printPreorderHelper(TreeNode<T> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.item + " ");
        printPreorderHelper(node.left);
        printPreorderHelper(node.right);
    }

    /* Print the values in the tree in inorder: values in the left subtree
       first (in inorder), then the root value, then values in the first
       subtree (in inorder). */
    public void printInorder() {
        if (root == null) {
            System.out.println("(empty tree)");
        } else {
            printInorderHelper(root);
            System.out.println();
        }
    }

    /* Prints the nodes of the BinaryTree in inorder. Used for your testing. */
    private void printInorderHelper(TreeNode<T> node) {
        if (node == null) {
            return;
        }
        printInorderHelper(node.left);
        System.out.print(node.item + " ");
        printInorderHelper(node.right);
    }

    /* Prints out the contents of a BinaryTree with a description in both
       preorder and inorder. */
    static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }

    /* Fills this BinaryTree with values a, b, and c. DO NOT MODIFY. */
    public static BinaryTree<String> sampleTree1() {
        TreeNode<String> root = new TreeNode("a",
                new TreeNode("b"),
                new TreeNode("c"));
        return new BinaryTree<>(root);
    }

    /* Fills this BinaryTree with values a, b, and c, d, e, f. DO NOT MODIFY. */
    public static BinaryTree<String> sampleTree2() {
        TreeNode root = new TreeNode("a",
                new TreeNode("b",
                        new TreeNode("d",
                                new TreeNode("e"),
                                new TreeNode("f")),
                        null),
                new TreeNode("c"));
        return new BinaryTree<>(root);
    }

    /* Fills this BinaryTree with the values a, b, c, d, e, f. DO NOT MODIFY. */
    public static BinaryTree<String> sampleTree3() {
        TreeNode<String> root = new TreeNode("a",
                new TreeNode("b"),
                new TreeNode("c",
                        new TreeNode("d",
                                new TreeNode("e"),
                                new TreeNode("f")),
                        null));
        return new BinaryTree<>(root);
    }

    /* Fills this BinaryTree with the same leaf TreeNode. DO NOT MODIFY. */
    public static BinaryTree<String> sampleTree4() {
        TreeNode<String> leafNode = new TreeNode("c");
        TreeNode<String> root = new TreeNode("a", new TreeNode("b", leafNode, leafNode),
                new TreeNode("d", leafNode, leafNode));
        return new BinaryTree<>(root);
    }

    /* Creates two BinaryTrees and prints them out in inorder. */
    public static void main(String[] args) {
        BinaryTree<String> t = new BinaryTree<>();
        print(t, "the empty tree");
        t = BinaryTree.sampleTree1();
        print(t, "sample tree 1");
        t = BinaryTree.sampleTree2();
        print(t, "sample tree 2");
        t = BinaryTree.sampleTree3();
        print(t, "sample tree 3");
        t = BinaryTree.sampleTree4();
        print(t, "sample tree 4");
    }

    /* Returns the height of the tree. */
    public int height() {
        if (root == null) {
            return 0;
        }
        return heightHelper(root);
    }

    private int heightHelper(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(heightHelper(node.left), heightHelper(node.right));
    }

    /* Returns true if the tree's left and right children are the same height
       and are themselves completely balanced. */
    public boolean isCompletelyBalanced() {
        return isBalancedHelper(root);
    }

    private boolean isBalancedHelper(TreeNode<T> node) {
        return node == null || heightHelper(node.getLeft()) == heightHelper(node.getRight())
                && isBalancedHelper(node.getLeft()) && isBalancedHelper(node.getRight());
    }

    /* Returns a BinaryTree representing the Fibonacci calculation for N. */
    public static BinaryTree<Integer> fibTree(int N) {
        BinaryTree<Integer> result = new BinaryTree<Integer>();

        if (N < 0) {
            return result;
        } else if (N == 0) {
            result.root = new TreeNode<>(0);
            return result;
        } else if (N == 1) {
            result.root = new TreeNode<>(1);
            return result;
        }

        TreeNode<Integer> helper = new TreeNode<>(0);
        TreeNode<Integer> head = new TreeNode<>(1);

        for (int i = 1; i < N; i++) {
            int newVal = helper.getItem() + head.getItem();
            TreeNode<Integer> newNode = new TreeNode<>(newVal);
            newNode.left = head;
            newNode.right = helper;
            helper = head;
            head = newNode;
        }
        result.root = head;
        return result;
    }
}
