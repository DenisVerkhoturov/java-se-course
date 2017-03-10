package com.github.leo_scream.java_se_course.unit_01.task_06;

import java.util.Arrays;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Notebook {

    private Note[] notes;
    private int size;

    public Notebook() {
        this.notes = new Note[0];
        this.size = 0;
    }

    /**
     * @return Number of elements contained in notebook
     */
    public int getSize() {
        return size;
    }

    /**
     * @param i Check if {@code i} in range of size
     * @throws IndexOutOfBoundsException If {@code i} negative or bigger that {@code size}
     */
    private void rangeCheck(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * @param note Check if {@code note} is null
     * @throws IllegalArgumentException If {@code note} is null
     */
    private void nullCheck(Note note) {
        if (note == null) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * @param capacity Required capacity
     */
    private void ensureCapacity(int capacity) {
        if (notes.length < capacity) {
            int newCapacity = Math.max(capacity, (notes.length * 3) / 2 + 1);
            notes = Arrays.copyOf(notes, newCapacity);
        }
    }

    private void smartRemove(int i) {
        if (size > 1) {
            System.arraycopy(notes, i + 1, notes, i, size - 1);
        } else {
            notes = new Note[0];
        }
        size -= 1;
    }

    /**
     * Adds {@code note} to notebook.
     *
     * @param note Note to be added to notebook
     * @return Index of newly added {@code note}
     * @throws IllegalArgumentException If {@code note} is {@code null}
     */
    public int add(Note note) {
        nullCheck(note);
        ensureCapacity(size + 1);
        notes[size] = note;
        size += 1;
        return size - 1;
    }

    /**
     * Removes note under index {@code i}.
     *
     * @param i Index of note to delete
     * @throws IndexOutOfBoundsException If {@code i} negative or bigger that {@code size}
     */
    public void remove(int i) {
        rangeCheck(i);
        smartRemove(i);
    }

    /**
     * Removes {@code note} from notebook.
     *
     * @param note Note which will be removed
     * @return Index of removed note or {@code -1} if note is not found
     * @throws IllegalArgumentException If {@code note} is {@code null}
     */
    public int remove(Note note) {
        nullCheck(note);

        for (int i = 0; i < size; i++) {
            if (notes[i].equals(note)) {
                smartRemove(i);
                return i;
            }
        }
        return -1;
    }

    /**
     * Update note under index {@code i}.
     *
     * @param i Index of {@code note} which will be updated
     * @param note {@code note} to insert
     * @throws IndexOutOfBoundsException If {@code i} negative or bigger that {@code size}
     * @throws IllegalArgumentException If {@code note} is {@code null}
     */
    public void update(int i, Note note) {
        nullCheck(note);
        rangeCheck(i);
        notes[i] = note;
    }

    /**
     * @param i Index of note to get
     * @return Note under index {@code i}
     * @throws IndexOutOfBoundsException If {@code i} negative or bigger that {@code size}
     */
    public Note get(int i) {
        rangeCheck(i);
        return notes[i];
    }
}
