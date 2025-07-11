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
    private int size;
    private T[] array;

    public ArrayDeque61B() {
        head = 0;
        tail = 1;
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
        head = Math.floorMod(head - 1, capacity);
        size++;
    }

    @Override
    public void addLast(T x) {
        if (size >= capacity) {
            resizeUp();
        }
        array[tail] = x;
        tail =  Math.floorMod(tail + 1, capacity);
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>(size);
        int arrayStart = Math.floorMod(head + 1, capacity);
        int arrayEnd = Math.floorMod(tail - 1, capacity);
        for (int i = arrayStart; i < size + arrayStart && i < capacity; i++) {
            list.add(array[i]);
        }
        for (int i = 0; i <= arrayEnd && i < arrayStart; i++) {
            list.add(array[i]);
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
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
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
