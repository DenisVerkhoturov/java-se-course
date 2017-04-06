package com.github.leo_scream.java_se_course.collections;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class HashMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private int size;
    private Bucket<K, V>[] buckets;

    public HashMap() {
        this(DEFAULT_CAPACITY);
    }

    public HashMap(int initialCapacity) {
        buckets = new Bucket[initialCapacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean containsKey(Object key) {
        boolean isFound = false;
        final K needle = (K) key;
        for (Bucket<K, V> bucket : buckets) {
            if (bucket != null) {
                isFound = bucket.containsKey(needle);
                if (isFound) break;
            }
        }
        return isFound;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean containsValue(Object value) {
        boolean isFound = false;
        final V needle = (V) value;
        for (Bucket<K, V> bucket : buckets) {
            if (bucket != null) {
                isFound = bucket.containsValue(needle);
                if (isFound) break;
            }
        }
        return isFound;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        final int index = Objects.hashCode(key) % buckets.length;
        Bucket<K, V> bucket = buckets[index];
        if (bucket == null) {
            bucket = new Bucket<>();
            buckets[index] = bucket;
        }
        return bucket.put(key, value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public V remove(Object key) {
        final int index = Objects.hashCode(key) % buckets.length;
        Bucket<K, V> bucket = buckets[index];
        return bucket.remove((K) key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }

    private class Bucket<K, V> {
        private Entry<K, V> head;

        boolean containsKey(K key) {
            boolean isFound = false;
            for (Entry<K, V> entry = head; entry != null; entry = entry.next) {
                isFound = Objects.equals(entry.key, key);
                if (isFound) break;
            }
            return isFound;
        }

        boolean containsValue(V value) {
            boolean isFound = false;
            for (Entry<K, V> entry = head; entry != null; entry = entry.next) {
                isFound = Objects.equals(entry.value, value);
                if (isFound) break;
            }
            return isFound;
        }

        V put(K key, V value) {
            V oldValue = null;
            if (head == null) {
                head = new Entry<>(key, value);
            } else {
                for (Entry<K, V> entry = head; entry != null; entry = entry.next) {
                    if (Objects.equals(entry.key, key)) {
                        oldValue = entry.value;
                        entry.value = value;
                        break;
                    } else if (entry.next == null) {
                        entry.next = new Entry<>(key, value);
                        break;
                    }
                }
            }
            size += 1;
            return oldValue;
        }

        V remove(K key) {
            V oldValue = null;
            if (head != null) {
                Entry<K, V> previous = head;
                for (Entry<K, V> entry = head; entry != null; entry = entry.next) {
                    if (Objects.equals(entry.key, key)) {
                        oldValue = entry.value;
                        if (head == entry) {
                            head = null;
                        } else {
                            previous.next = entry.next;
                        }
                        size -= 1;
                        break;
                    }
                    previous = entry;
                }
            }
            return oldValue;
        }
    }

    private class Entry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public final boolean equals(Object o) {
            if (o == this) return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
                if (Objects.equals(key, e.getKey()) &&
                    Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }
}
