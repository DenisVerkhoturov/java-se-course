package com.github.leo_scream.java_se_course.task06;

import java.util.Arrays;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Notebook
{
    private Note[] notes;
    private int size;

    public Notebook()
    {
        this.notes = new Note[0];
        this.size = 0;
    }

    public int getSize()
    {
        return size;
    }

    /**
     * @param capacity Required capacity
     */
    private void ensureCapacity(int capacity)
    {
        if (notes.length < capacity) {
            int newCapacity = Math.max(capacity, (notes.length * 3) / 2 + 1);
            notes = Arrays.copyOf(notes, newCapacity);
        }
    }

    public void add(Note note) {
        ensureCapacity(size + 1);
        notes[size] = note;
        size += 1;
    }

    public void remove(int i) {}

    public void remove(Note note) {}

    public void update(int i) {}

    public void get(int i) {}
}
