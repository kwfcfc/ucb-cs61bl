import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HashMap<K, V> implements Map61BL<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

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

    private void resizeUp() {
        LinkedList<Entry<K, V>>[] oldBuckets = buckets;

        capacity = capacity << 1;
        buckets = (LinkedList<Entry<K, V>>[]) new LinkedList[capacity];

        // new buckets
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }

        for (LinkedList<Entry<K, V>> bucket : oldBuckets) {
            for (Entry<K, V> entry : bucket) {
                int index = entry.key.hashCode() & (capacity - 1);
                buckets[index].addLast(entry);
            }
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
        size++;
        if ((double) size / capacity > loadFactor) {
            resizeUp();
            index = key.hashCode() & (capacity - 1);
        }
        Entry<K, V> entry = new Entry<>(key, value);
        buckets[index].addLast(entry);
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
        return new HashMapIterator();
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

    private class HashMapIterator implements Iterator<K> {
        private int bucketIndex = 0;
        private Iterator<Entry<K, V>> bucketIterator = buckets[bucketIndex].iterator();

        @Override
        public boolean hasNext() {
            if (bucketIterator.hasNext()) {
                return true;
            }
            for (int i = bucketIndex + 1; i < capacity; i++) {
                if (!buckets[i].isEmpty()) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public K next() {
            if (bucketIterator.hasNext()) {
                return bucketIterator.next().key;
            }

            for (bucketIndex += 1; bucketIndex < capacity; bucketIndex++) {
                if (!buckets[bucketIndex].isEmpty()) {
                    bucketIterator = buckets[bucketIndex].iterator();
                    return bucketIterator.next().key;
                }
            }
            throw new NoSuchElementException();
        }
    }
}
