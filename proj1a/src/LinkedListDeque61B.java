import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private ListNode sentinel;
    private int size;

    private class ListNode {
        private T item;
        private ListNode next;
        private ListNode prev;

        public ListNode(T item, ListNode next, ListNode prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

        public ListNode() {}

        @Override
        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }
            if (o instanceof LinkedListDeque61B<?>.ListNode other) {
                return this.item.equals(other.item);
            }
            return false;
        }

        @Override
        public String toString() {
            return this.item == null ? "" : this.item.toString();
        }
    }

    public LinkedListDeque61B() {
        this.sentinel = new ListNode();
        this.sentinel.next = sentinel;
        this.sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        ListNode newNode = new ListNode(x, sentinel.next, sentinel);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        ListNode newNode = new ListNode(x, sentinel, sentinel.prev);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> result = new ArrayList<>();
        ListNode iterator = sentinel.next;
        while (iterator != sentinel) {
            result.add(iterator.item);
            iterator = iterator.next;
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }

    @Override
    public T removeFirst() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeFirst'");
    }

    @Override
    public T removeLast() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeLast'");
    }

    @Override
    public T get(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public T getRecursive(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRecursive'");
    }

}
