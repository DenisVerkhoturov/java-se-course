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
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
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
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
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
