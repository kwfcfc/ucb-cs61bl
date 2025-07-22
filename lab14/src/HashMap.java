import java.util.Iterator;
import java.util.LinkedList;

public class HashMap<K, V> implements Map61BL<K, V> {
    private static int INITIAL_CAPACITY = 16;
    private static double DEFAULT_LOAD_FACTOR = 0.75;

    private int capacity;
    private int size;
    private final double loadFactor;

    /* TODO: Instance variables here */
    private LinkedList<Entry<K, V>>[] buckets;

    /* TODO: Constructors here */
    public HashMap() {
        capacity = INITIAL_CAPACITY;
        size = 0;
        loadFactor = DEFAULT_LOAD_FACTOR;
        buckets = (LinkedList<Entry<K, V>>[]) new LinkedList[INITIAL_CAPACITY];
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    public HashMap(int initialCapacity) {
        capacity = initialCapacity;
        size = 0;
        loadFactor = DEFAULT_LOAD_FACTOR;
        buckets = (LinkedList<Entry<K, V>>[]) new LinkedList[INITIAL_CAPACITY];
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    public HashMap(int initialCapacity, double loadFactor) {
        capacity = initialCapacity;
        size = 0;
        this.loadFactor = loadFactor;
        buckets = (LinkedList<Entry<K, V>>[]) new LinkedList[INITIAL_CAPACITY];
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    /* TODO: Interface methods here */
    @Override
    public void clear() {
        capacity = INITIAL_CAPACITY;
        size = 0;
        buckets = (LinkedList<Entry<K, V>>[]) new LinkedList[INITIAL_CAPACITY];
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        int index = key.hashCode() & (capacity - 1);
        LinkedList<Entry<K, V>> bucket = buckets[index];
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        int index = key.hashCode() & (capacity - 1);
        for (Entry<K, V> entry : buckets[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        Entry<K, V> entry = new Entry<>(key, value);
        buckets[index].addLast(entry);
        size++;
    }

    @Override
    public V remove(K key) {
        int index =  key.hashCode() & (capacity - 1);
        Iterator<Entry<K, V>> iterator = buckets[index].iterator();
        int i = 0;
        while (iterator.hasNext()) {
            if (iterator.next().key.equals(key)) {
                Entry<K,V> result = buckets[index].remove(i);
                size--;
                return result.value;
            }
            i++;
        }
        return null;
    }

    @Override
    public boolean remove(K key, V value) {
        return remove(key) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public int capacity() {
        return capacity;
    }
    private static class Entry<K, V> {

        private K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /* Returns true if this key matches with the OTHER's key. */
        public boolean keyEquals(Entry other) {
            return key.equals(other.key);
        }

        /* Returns true if both the KEY and the VALUE match. */
        @Override
        public boolean equals(Object other) {
            return (other instanceof Entry
                    && key.equals(((Entry) other).key)
                    && value.equals(((Entry) other).value));
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }
}
