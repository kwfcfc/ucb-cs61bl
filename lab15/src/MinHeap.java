import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

/* A MinHeap class of Comparable elements backed by an ArrayList. */
public class MinHeap<E extends Comparable<E>> {

    /* An ArrayList that stores the elements in this MinHeap. */
    private ArrayList<E> contents;
    private int size;
    private HashMap<E, Integer> table;

    /* Initializes an empty MinHeap. */
    public MinHeap() {
        contents = new ArrayList<>();
        contents.add(null);
        size = 0;
        table = new HashMap<>();
    }

    /* Returns the element at index INDEX, and null if it is out of bounds. */
    private E getElement(int index) {
        if (index >= contents.size()) {
            return null;
        } else {
            return contents.get(index);
        }
    }

    /* Sets the element at index INDEX to ELEMENT. If the ArrayList is not big
       enough, add elements until it is the right size. */
    private void setElement(int index, E element) {
        while (index >= contents.size()) {
            contents.add(null);
        }
        contents.set(index, element);
        table.put(element, index);
    }

    /* Swaps the elements at the two indices. */
    private void swap(int index1, int index2) {
        E element1 = getElement(index1);
        E element2 = getElement(index2);
        setElement(index2, element1);
        setElement(index1, element2);
    }

    /* Prints out the underlying heap sideways. Use for debugging. */
    @Override
    public String toString() {
        return toStringHelper(1, "");
    }

    /* Recursive helper method for toString. */
    private String toStringHelper(int index, String soFar) {
        if (getElement(index) == null) {
            return "";
        } else {
            String toReturn = "";
            int rightChild = getRightOf(index);
            toReturn += toStringHelper(rightChild, "        " + soFar);
            if (getElement(rightChild) != null) {
                toReturn += soFar + "    /";
            }
            toReturn += "\n" + soFar + getElement(index) + "\n";
            int leftChild = getLeftOf(index);
            if (getElement(leftChild) != null) {
                toReturn += soFar + "    \\";
            }
            toReturn += toStringHelper(leftChild, "        " + soFar);
            return toReturn;
        }
    }

    /* Returns the index of the left child of the element at index INDEX. */
    private int getLeftOf(int index) {
        return 2 * index;
    }

    /* Returns the index of the right child of the element at index INDEX. */
    private int getRightOf(int index) {
        return 2 * index + 1;
    }

    /* Returns the index of the parent of the element at index INDEX. */
    private int getParentOf(int index) {
        return index / 2;
    }

    /* Returns the index of the smaller element. At least one index has a
       non-null element. If the elements are equal, return either index. */
    private int min(int index1, int index2) {
        if (getElement(index1) == null) {
            return index2;
        }
        if (getElement(index2) == null) {
            return index1;
        }

        int compare = getElement(index1).compareTo(getElement(index2));
        if (compare > 0) {
            return index2;
        } else {
            return index1;
        }
    }

    /* Returns but does not remove the smallest element in the MinHeap. */
    public E findMin() {
        return getElement(1);
    }

    /* Bubbles up the element currently at index INDEX. */
    private void bubbleUp(int index) {
        int iterator = index;
        int parent = getParentOf(index);
        while (iterator > 1 && getElement(iterator).compareTo(getElement(parent)) < 0) {
            swap(iterator, parent);
            table.replace(getElement(parent), iterator);
            iterator = parent;
            parent = getParentOf(iterator);
        }
        if (iterator != index) {
            table.replace(getElement(iterator), iterator);
        }
    }

    /* Bubbles down the element currently at index INDEX. */
    private void bubbleDown(int index) {
        int iterator = index;
        int smaller = min(getLeftOf(index), getRightOf(index));
        while (getElement(smaller) != null && getElement(iterator).compareTo(getElement(smaller)) > 0) {
            swap(iterator, smaller);
            table.replace(getElement(smaller),  iterator); // update the index hashtable for smaller child
            iterator = smaller;
            smaller = min(getLeftOf(iterator), getRightOf(iterator));
        }
        if (index != iterator) {
            table.replace(getElement(iterator), iterator);
        }
    }

    /* Returns the number of elements in the MinHeap. */
    public int size() {
        return size;
    }

    /* Inserts ELEMENT into the MinHeap. If ELEMENT is already in the MinHeap,
       throw an IllegalArgumentException.*/
    public void insert(E element) {
        size++;
        setElement(size, element);
        bubbleUp(size);
    }

    /* Returns and removes the smallest element in the MinHeap, or null if there are none. */
    public E removeMin() {
        E result = getElement(1);
        if (result == null) {
            return null;
        }
        swap(1, size);
        contents.remove(size);
        size--;
        bubbleDown(1);
        table.remove(result);
        return result;
    }

    /* Replaces and updates the position of ELEMENT inside the MinHeap, which
       may have been mutated since the initial insert. If a copy of ELEMENT does
       not exist in the MinHeap, throw a NoSuchElementException. Item equality
       should be checked using .equals(), not ==. */
    public void update(E element) {
        if (!table.containsKey(element)) {
            throw new NoSuchElementException();
        }
        int index = table.get(element);
        setElement(index, element);

        int parent = getParentOf(index);
        if (index > 1 && index == min(index, parent)) { // root should not have parent
            bubbleUp(index);
            return;
        }

        int minChild = min(getLeftOf(index), getRightOf(index));
        if (index != min(index, minChild)) {
            bubbleDown(index);
        }
    }

    /* Returns true if ELEMENT is contained in the MinHeap. Item equality should
       be checked using .equals(), not ==. */
    public boolean contains(E element) {
        return table.containsKey(element);
    }
}
