/**
 * A data structure to represent a Linked List of Integers. Each IntList represents one node in the
 * overall Linked List.
 */

public class IntList {

    /** The integer stored by this node. */
    public int item;
    /** The next node in this IntList. */
    public IntList next;

    /** Constructs an IntList storing ITEM and next node NEXT. */
    public IntList(int item, IntList next) {
        this.item = item;
        this.next = next;
    }

    /** Constructs an IntList storing ITEM and no next node. */
    public IntList(int item) {
        this(item, null);
    }

    /**
     * Returns an IntList consisting of the elements in ITEMS. IntList L = IntList.list(1, 2, 3);
     * System.out.println(L.toString()) // Prints 1 2 3
     */
    public static IntList of(int... items) {
        /** Check for cases when we have no element given. */
        if (items.length == 0) {
            return null;
        }
        /** Create the first element. */
        IntList head = new IntList(items[0]);
        IntList last = head;
        /** Create rest of the list. */
        for (int i = 1; i < items.length; i++) {
            last.next = new IntList(items[i]);
            last = last.next;
        }
        return head;
    }

    /**
     * Returns [position]th item in this list. Throws IllegalArgumentException if index out of
     * bounds.
     *
     * @param position, the position of element.
     * @return The element at [position]
     */
    public int get(int position) {
        if (position < 0) {
            throw new IllegalArgumentException("position should be no less than zero.");
        }
        int value = this.item;
        IntList iterator = this.next;
        while (position > 0) {
            if (iterator == null) {
                throw new IllegalArgumentException("index out of bound");
            }
            value = iterator.item;
            iterator = iterator.next;
            position--;
        }
        return value;
    }

    /**
     * Returns the string representation of the list. For the list (1, 2, 3), returns "1 2 3".
     *
     * @return The String representation of the list.
     */
    public String toString() {
        IntList iterator = this.next;
        StringBuilder builder = new StringBuilder();
        builder.append(this.item);
        while (iterator != null) {
            builder.append(" ");
            builder.append(iterator.item);
            iterator = iterator.next;
        }
        return builder.toString();
    }

    /**
     * Returns whether this and the given list or object are equal.
     *
     * NOTE: A full implementation of equals requires checking if the object passed in is of the
     * correct type, as the parameter is of type Object. This also requires we convert the Object to
     * an IntList, if that is legal. The operation we use to do this is called casting, and it is
     * done by specifying the desired type in parentheses. An example of this is `IntList otherLst =
     * (IntList) obj;` We recommend reviewing the `instanceOf` keyword mentioned in the spec.
     *
     * @param obj, another list (object)
     * @return Whether the two lists are equal.
     */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof IntList otherList) {
            boolean itemSame = this.item == otherList.item;
            boolean bothNullOrEqualNext =
                    this.next == null ? otherList.next == null : this.next.equals(otherList.next);
            return itemSame && bothNullOrEqualNext;
        }
        return false;
    }

    /**
     * Adds the given value at the end of the list.
     *
     * @param value, the int to be added.
     */
    public void add(int value) {
        IntList newNode = new IntList(value);
        IntList iterator = this;
        while (iterator.next != null) {
            iterator = iterator.next;
        }
        iterator.next = newNode;
    }

    /**
     * Returns the smallest element in the list.
     *
     * @return smallest element in the list
     */
    public int smallest() {
        int min = this.item;
        IntList iterator = this.next;
        while (iterator != null) {
            if (iterator.item < min) {
                min = iterator.item;
            }
            iterator = iterator.next;
        }
        return min;
    }

    /**
     * Returns the sum of squares of all elements in the list.
     *
     * @return The sum of squares of all elements.
     */
    public int squaredSum() {
        int sum = this.item * this.item;
        IntList iterator = this.next;
        while (iterator != null) {
            sum += iterator.item * iterator.item;
            iterator = iterator.next;
        }
        return sum;
    }

    /**
     * Destructively squares each item of the list.
     *
     * @param L list to destructively square.
     */
    public static void dSquareList(IntList L) {
        while (L != null) {
            L.item = L.item * L.item;
            L = L.next;
        }
    }

    /**
     * Returns a list equal to L with all elements squared. Non-destructive.
     *
     * @param L list to non-destructively square.
     * @return the squared list.
     */
    public static IntList squareListIterative(IntList L) {
        if (L == null) {
            return null;
        }
        IntList res = new IntList(L.item * L.item, null);
        IntList ptr = res;
        L = L.next;
        while (L != null) {
            ptr.next = new IntList(L.item * L.item, null);
            L = L.next;
            ptr = ptr.next;
        }
        return res;
    }

    /**
     * Returns a list equal to L with all elements squared. Non-destructive.
     *
     * @param L list to non-destructively square.
     * @return the squared list.
     */
    public static IntList squareListRecursive(IntList L) {
        if (L == null) {
            return null;
        }
        return new IntList(L.item * L.item, squareListRecursive(L.next));
    }

    /**
     * Returns a new IntList consisting of A followed by B, non-destructively (no modifications to
     * A).
     *
     * @param A list to be on the front of the new list.
     * @param B list to be on the back of the new list.
     * @return new list with A followed by B.
     */
    public static IntList catenate(IntList A, IntList B) {
        if (A == null && B == null) {
            return null;
        }
        IntList result = A == null ? new IntList(B.item, B.next) : new IntList(A.item, A.next);
        IntList iterator = result;

        while (iterator.next != null) {
            IntList oldNode = iterator.next;
            iterator.next = new IntList(oldNode.item, oldNode.next);
            iterator = iterator.next;
        }

        // if the A is not null, it means we only traversed the B
        if (A == null) {
            return result;
        } else {
            iterator.next = B;
        }

        while (iterator.next != null) {
            IntList oldNode = iterator.next;
            iterator.next = new IntList(oldNode.item, oldNode.next);
            iterator = iterator.next;
        }

        return result;
    }

    /**
     * Returns a new IntList consisting of A followed by B, destructively (make modifications to A).
     *
     * @param A list to be on the front of the new list.
     * @param B list to be on the back of the new list.
     * @return new list with A followed by B.
     */
    public static IntList dcatenate(IntList A, IntList B) {
        if (A == null) {
            return B;
        }
        IntList iterator = A;
        while (iterator.next != null) {
            iterator = iterator.next;
        }
        iterator.next = B;
        return A;
    }
}
