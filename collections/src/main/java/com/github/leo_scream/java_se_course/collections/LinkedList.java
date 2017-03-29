package com.github.leo_scream.java_se_course.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class LinkedList<T> implements List<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedList() {
        size = 0;
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
    public boolean contains(Object needle) {
        if (needle == null) {
            for (Node<T> node = head; node != null; node = node.next) {
                if (node.value == null) {
                    return true;
                }
            }
        } else {
            for (Node<T> node = head; node != null; node = node.next) {
                if (needle.equals(node.value)) {
                    return true;
                }
            }
        }
        return false;
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
    public <R> R[] toArray(R[] a) {
        return null;
    }

    @Override
    public boolean add(T value) {
        final Node<T> tail = this.tail;
        final Node<T> node = new Node<>(tail, value, null);
        this.tail = node;

        if (tail == null) head = node;
        else tail.next = node;

        size += 1;

        return true;
    }

    @Override
    public boolean remove(Object value) {
        if (value == null) {
            for (Node<T> node = head; node != null; node = node.next) {
                if (node.value == null) {
                    remove(node);
                    return true;
                }
            }
        } else {
            for (Node<T> node = head; node != null; node = node.next) {
                if (value.equals(node.value)) {
                    remove(node);
                    return true;
                }
            }
        }
        return false;
    }

    private T remove(Node<T> node) {
        final Node<T> previous = node.previous;
        final Node<T> next = node.next;

        if (previous == null) {
            head = next;
        } else {
            previous.next = next;
            node.previous = null;
        }

        if (next == null) {
            tail = previous;
        } else {
            next.previous = previous;
            node.next = null;
        }

        size -= 1;

        return node.value;
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
        checkBounds(index);
        T element = null;

        if (index < (size / 2)) {
            for (Node<T> node = head; index >= 0; node = node.next, index--) {
                element = node.value;
            }
        } else {
            for (Node<T> node = tail; index >= 0; node = node.previous, index--) {
                element = node.value;
            }
        }

        return element;
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
        checkBounds(index);
        Node<T> node;

        if (index < (size / 2)) {
            node = head;
            while (index > 0) {
                node = node.next;
                index -= 1;
            }
        } else {
            node = tail;
            while (index > 0) {
                node = node.previous;
                index -= 1;
            }
        }

        return remove(node);
    }

    @Override
    public int indexOf(Object needle) {
        int index = 0;
        boolean isFound = false;
        if (needle == null) {
            for (Node<T> node = head; node != null; node = node.next, index++) {
                isFound = node.value == null;
                if (isFound) break;
            }
        } else {
            for (Node<T> node = tail; node != null; node = node.previous, index++) {
                isFound = needle.equals(node.value);
                if (isFound) break;
            }
        }

        return isFound ? index : -1;
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

    private void checkBounds(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
    }

    private class Node<T> {
        private Node<T> next;
        private Node<T> previous;
        private T value;

        Node(T value) {
            this.value = value;
        }

        Node(Node<T> previous, T value, Node<T> next) {
            this(value);
            this.previous = previous;
            this.next = next;
        }
    }
}
