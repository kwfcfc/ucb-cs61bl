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
            return this.item.toString();
        }
    }

    public LinkedListDeque61B() {
        this.sentinel = new ListNode(null, sentinel, sentinel);
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addFirst'");
    }

    @Override
    public void addLast(T x) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addLast'");
    }

    @Override
    public List<T> toList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toList'");
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
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
