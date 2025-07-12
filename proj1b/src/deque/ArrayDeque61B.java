package deque;

import java.util.ArrayList;
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
        tail = head;
        size = 0;
        capacity = INITIAL_CAPACITY;
        array = (T[]) new Object[INITIAL_CAPACITY];
    }

    private void resizeUp() {
    }

    @Override
    public void addFirst(T x) {
        if (size >= capacity) {
            resizeUp();
        }
        array[head] = x;
        head = (head - 1) & (capacity - 1); // this is the bitwise floor mod and works with -1 too.
        size++;
    }

    @Override
    public void addLast(T x) {
        if (size >= capacity) {
            resizeUp();
        }
        tail =  (tail + 1) & (capacity - 1);
        array[tail] = x;
        size++;
    }

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
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

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
        return null;
    }
}
