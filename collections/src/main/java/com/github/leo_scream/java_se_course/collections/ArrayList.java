package com.github.leo_scream.java_se_course.collections;

import java.util.*;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class ArrayList<T> implements List<T> {
    private int size;
    private Object[] data;

    public ArrayList() {
        this(16);
    }

    public ArrayList(int startingCapacity) {
        this.data = new Object[startingCapacity];
        this.size = 0;
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
    public boolean contains(Object o) {
        boolean isFound = false;
        if (o == null) {
            for (int i = 0; i < size; i++) {
                isFound = data[i] == null;
                if (isFound) break;
            }
        } else {
            for (int i = 0; i < size; i++) {
                isFound = o.equals(data[i]);
                if (isFound) break;
            }
        }
        return isFound;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int current = 0;

            @Override
            public boolean hasNext() {
                return current != size;
            }

            @Override
            public T next() {
                return (T) data[current++];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R> R[] toArray(R[] array) {
        Objects.requireNonNull(array);
        if (array.length < size) {
            array = (R[]) Arrays.copyOf(data, size, array.getClass());
        } else {
            System.arraycopy(data, 0, array, 0, size);
        }
        return array;
    }

    @Override
    public boolean add(T element) {
        ensureCapacity(size + 1);
        data[size] = element;
        size += 1;
        return true;
    }

    @Override
    public boolean remove(Object element) {
        boolean isDeleted = false;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (data[i] == null) {
                    fastRemove(i);
                    isDeleted = true;
                    break;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(data[i])) {
                    fastRemove(i);
                    isDeleted = true;
                    break;
                }
            }
        }
        return isDeleted;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return c.stream().allMatch(this::contains);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        c.forEach(this::add);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isChanged = false;
        for (int index = 0; index < size; index++) {
            if (c.contains(data[index])) {
                remove(index);
                isChanged = true;
            }
        }
        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        data = new Object[0];
        size = 0;
    }

    @Override
    public T get(int index) {
        checkBounds(index);
        return (T) data[index];
    }

    @Override
    public T set(int index, T element) {
        checkBounds(index);
        T oldValue = (T) data[index];
        data[index] = element;
        return oldValue;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        checkBounds(index);
        T oldValue = (T) data[index];
        fastRemove(index);
        return oldValue;
    }

    @Override
    public int indexOf(Object needle) {
        boolean isFound = false;
        int index = 0;
        if (needle == null) {
            while (index < size) {
                isFound = data[index] == null;
                if (isFound) break;
                index += 1;
            }
        } else {
            while (index < size) {
                isFound = needle.equals(data[index]);
                if (isFound) break;
                index += 1;
            }
        }
        return isFound ? index : -1;
    }

    @Override
    public int lastIndexOf(Object needle) {
        boolean isFound = false;
        int index = size - 1;
        if (needle == null) {
            while (index >= 0) {
                isFound = data[index] == null;
                if (isFound) break;
                index -= 1;
            }
        } else {
            while (index < size) {
                isFound = needle.equals(data[index]);
                if (isFound) break;
                index -= 1;
            }
        }
        return isFound ? index : -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    private void ensureCapacity(int requiredCapacity) {
        if (data.length < requiredCapacity) {
            int newCapacity = Math.max(requiredCapacity, (data.length * 3) / 2 + 1);
            data = Arrays.copyOf(data, newCapacity);
        }
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void fastRemove(int index) {
        if (size > 1) {
            System.arraycopy(data, index + 1, data, index, size - index - 1);
        } else {
            data = new Object[0];
        }
        size -= 1;
    }
}
