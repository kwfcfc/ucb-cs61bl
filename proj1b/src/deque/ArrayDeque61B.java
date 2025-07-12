package deque;

import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private static final int INITIAL_CAPACITY = 8;
    private static final int USAGE_FACTOR = 4;
    private static final int RESIZING_THRESHOLD = 16;

    private int head;
    private int tail;
    private int capacity;
    // if we only set capacity to the power of 2, it is easier to implement the floor mod in bitwise operation
    private int size;
    private T[] array;

    public ArrayDeque61B() {
        // set the head to the half of the array, so it assumes the user will have relatively same amount of addFirst
        // and addLast operations.
        head = (INITIAL_CAPACITY >> 1) - 1;
        // tail is the position of the next element that add Last.
        tail = head + 1;
        size = 0;
        capacity = INITIAL_CAPACITY;
        array = (T[]) new Object[INITIAL_CAPACITY];
    }

    private void resize(boolean up) {
        int oldCapacity = capacity;
        T[] oldArray = array;
        int oldHead = head;

        capacity = up? capacity << 1: capacity >> 1;
        array = (T[]) new Object[capacity];
        head = (oldCapacity >> 1) - 1;

        for (int i = 0; i < size; i++) {
            int oldIndex = (oldHead + 1 + i) & (oldCapacity - 1);
            int index = (head + 1 + i) & (capacity - 1);
            array[index] = oldArray[oldIndex];
        }
    }

    private void resizeUp() { resize(true); }

    private void resizeDown() { resize(false); }

    private void add(T x, boolean front) {
        if (size >= capacity) {
            resizeUp();
        }
        int pointer = front? this.head : this.tail;
        array[pointer] = x;
        if (front) {
            head = (pointer - 1) & (capacity - 1); // this is the bitwise floor mod and works with -1 too.
        } else {
            tail = (pointer + 1) & (capacity - 1);
        }
        size++;
    }

    private T remove(boolean front) {
        if (size == 0) {
            return null;
        }
        if (capacity >= RESIZING_THRESHOLD && size * USAGE_FACTOR <= capacity) {
            resizeDown();
        }
        int pointer = front? this.head + 1 : this.tail - 1;
        pointer = pointer & (capacity - 1);
        if (front) {
            head = pointer;
        } else {
            tail = pointer;
        }
        size--;
        return array[pointer];
    }

    @Override
    public void addFirst(T x) { add(x, true); }

    @Override
    public void addLast(T x) { add(x, false); }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            int index = (head + 1 + i) & (capacity - 1);
            list.add(array[index]);
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() { return remove(true); }

    @Override
    public T removeLast() { return remove(false); }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int arrayIndex = (head + 1 + index) & (capacity - 1);
        return array[arrayIndex];
    }

    @Override
    public T getRecursive(int index) {
        return getRecursiveHelper(new ArrayIterator(),  index);
    }

    private T getRecursiveHelper(Iterator<T> iterator, int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        if (index == 0) {
            return iterator.next();
        } else {
            iterator.next();
            return getRecursiveHelper(iterator, index - 1);
        }
    }

    private class ArrayIterator implements Iterator<T> {
        private int index = head;

        public boolean hasNext() {
            int nextIndex = (index + 1) & (capacity - 1);
            return nextIndex != tail;
        }

        public T next() {
            index = (index + 1) & (capacity - 1);
            return array[index];
        }
    }

    @Override
    @NonNull
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Deque61B<?> otherDeque) {
            return dequeEquals(otherDeque);
        }
        return false;
    }

    @Override
    public String toString() {
        return dequeToString();
    }
}
