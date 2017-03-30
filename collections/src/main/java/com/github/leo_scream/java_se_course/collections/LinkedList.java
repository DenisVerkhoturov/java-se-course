package com.github.leo_scream.java_se_course.collections;

import java.util.*;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class LinkedList<T> implements List<T> {

    private Node head;
    private Node tail;
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
        boolean found = false;
        if (needle == null) {
            for (Node node = head; node != null; node = node.next) {
                found = node.value == null;
                if (found) break;
            }
        } else {
            for (Node node = head; node != null; node = node.next) {
                found = needle.equals(node.value);
                if (found) break;
            }
        }
        return found;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int current = 0;

            @Override
            public boolean hasNext() {
                return current != size;
            }

            @Override
            public T next() {
                return nodeBy(current++).value;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        for (Node node = head; node != null; node = node.next, index++) array[index] = node.value;
        return array;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R> R[] toArray(R[] array) {
        Objects.requireNonNull(array);
        if (array.length < size) {
            array = (R[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), size);
        }

        int index = 0;
        for (Node node = head; node != null; node = node.next, index++) {
            array[index] = (R) node.value;
        }

        return array;
    }

    @Override
    public boolean add(T value) {
        final Node tail = this.tail;
        final Node node = new Node(tail, value, null);
        this.tail = node;

        if (tail == null) head = node;
        else tail.next = node;

        size += 1;

        return true;
    }

    @Override
    public boolean remove(Object value) {
        boolean isDeleted = false;
        if (value == null) {
            for (Node node = head; node != null; node = node.next) {
                if (node.value == null) {
                    remove(node);
                    isDeleted = true;
                }
            }
        } else {
            for (Node node = head; node != null; node = node.next) {
                if (value.equals(node.value)) {
                    remove(node);
                    isDeleted = true;
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
        checkBounds(index);
        final Node next = nodeBy(index);
        final Node previous = next.previous;
        Node node;

        for (T value : c) {
            node = new Node(previous, value, next);
            if (previous == null) head = node;
            else previous.next = node;
        }

        size += c.size();

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isChanged = false;
        for (Node node = head; node != null; node = node.next) {
            if (c.contains(node.value)) {
                remove(node);
                isChanged = true;
            }
        }
        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isChanged = false;
        for (Node node = head; node != null; node = node.next) {
            if (!c.contains(node.value)) {
                remove(node);
                isChanged = true;
            }
        }
        return isChanged;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public T get(int index) {
        checkBounds(index);
        return nodeBy(index).value;
    }

    @Override
    public T set(int index, T value) {
        checkBounds(index);
        Node node = nodeBy(index);
        T oldValue = node.value;
        nodeBy(index).value = value;
        return oldValue;
    }

    @Override
    public void add(int index, T value) {
        checkBounds(index);
        final Node next = nodeBy(index);
        final Node previous = next.previous;
        final Node node = new Node(previous, value, next);

        if (previous == null) head = node;
        else previous.next = node;

        size += 1;
    }

    @Override
    public T remove(int index) {
        checkBounds(index);
        return remove(nodeBy(index));
    }

    @Override
    public int indexOf(Object needle) {
        int index = 0;
        boolean isFound = false;
        if (needle == null) {
            for (Node node = head; node != null; node = node.next, index++) {
                isFound = node.value == null;
                if (isFound) break;
            }
        } else {
            for (Node node = head; node != null; node = node.next, index++) {
                isFound = needle.equals(node.value);
                if (isFound) break;
            }
        }

        return isFound ? index : -1;
    }

    @Override
    public int lastIndexOf(Object needle) {
        int index = size - 1;
        boolean isFound = false;
        if (needle == null) {
            for (Node node = tail; node != null; node = node.previous, index--) {
                isFound = node.value == null;
                if (isFound) break;
            }
        } else {
            for (Node node = tail; node != null; node = node.previous, index--) {
                isFound = needle.equals(node.value);
                if (isFound) break;
            }
        }

        return isFound ? index : -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new ListIterator<T>() {
            int current = index;

            @Override
            public boolean hasNext() {
                return current != size;
            }

            @Override
            public T next() {
                return nodeBy(current++).value;
            }

            @Override
            public boolean hasPrevious() {
                return current != 0;
            }

            @Override
            public T previous() {
                return nodeBy(current - 1).value;
            }

            @Override
            public int nextIndex() {
                return current + 1;
            }

            @Override
            public int previousIndex() {
                return current - 1;
            }

            @Override
            public void remove() {
                LinkedList.this.remove(nodeBy(current--));
            }

            @Override
            public void set(T t) {
                nodeBy(index).value = t;
            }

            @Override
            public void add(T t) {
                LinkedList.this.add(current++, t);
            }
        };
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        checkBounds(fromIndex);
        checkBounds(toIndex);
        checkBounds(toIndex - fromIndex);
        final LinkedList<T> list = new LinkedList<>();
        int elementsRemained = toIndex - fromIndex;
        for (Node node = nodeBy(fromIndex); elementsRemained > 0; elementsRemained--) {
            list.add(node);
        }
        return list;
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
    }

    private Node nodeBy(int index) {
        return index < (size / 2)
            ? head.forward(index)
            : tail.backward(size - index - 1);
    }

    private void add(Node node) {
        final Node tail = this.tail;
        this.tail = node;

        if (tail == null) head = node;
        else tail.next = node;

        size += 1;
    }

    private T remove(Node node) {
        final Node previous = node.previous;
        final Node next = node.next;

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

    private class Node {
        private Node next;
        private Node previous;
        private T value;

        Node(T value) {
            this.value = value;
        }

        Node(Node previous, T value, Node next) {
            this(value);
            this.previous = previous;
            this.next = next;
        }

        Node forward(int offset) {
            Node node = this;
            while (offset > 0) {
                node = node.next;
                offset -= 1;
            }
            return node;
        }

        Node backward(int offset) {
            Node node = this;
            while (offset > 0) {
                node = node.previous;
                offset -= 1;
            }
            return node;
        }
    }
}
