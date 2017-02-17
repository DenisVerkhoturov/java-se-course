package com.github.leo_scream.java_se_course.task06;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class NotebookTest
{
    private Notebook notebook;
    private Note[] notes;
    private Note specialNote;
    private Note markedNote;
    private int markedNoteIndex;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception
    {
        notebook = new Notebook();
        specialNote = new Note();
        notes = new Note[]{
            new Note(), new Note(), new Note(), new Note(), new Note()
        };
        markedNoteIndex = 2;
        markedNote = notes[markedNoteIndex];
    }

    @Test
    public void getSize() throws Exception
    {
        for (Note note : notes) notebook.add(note);

        assertEquals(notes.length, notebook.getSize());

        for (int i = 0; i < notes.length; i++) {
            assertEquals(notes.length - i, notebook.getSize());
            notebook.remove(0);
        }

        assertEquals(0, notebook.getSize());
    }

    @Test
    public void add() throws Exception
    {
        for (Note note : notes) notebook.add(note);

        for (Note note : notes) {
            boolean noteIsPresented = false;

            for(int i = 0; i < notebook.getSize(); i++) {
                final Note notebookNote = notebook.get(i);

                if (note.equals(notebookNote)) {
                    noteIsPresented = true;
                    break;
                }
            }

            assertTrue(noteIsPresented);
        }

        thrown.expect(IllegalArgumentException.class);
        notebook.add(null);
    }

    @Test
    public void removeByIndex() throws Exception
    {
        for (Note note : notes) notebook.add(note);

        notebook.remove(markedNoteIndex);

        for (int i = 0; i < notebook.getSize(); i++) {
            final Note notebookNote = notebook.get(i);
            boolean noteIsPresented = false;

            assertFalse(notebookNote.equals(markedNote));

            for (Note note : notes) {
                if (note.equals(notebookNote)) {
                    noteIsPresented = true;
                    break;
                }
            }

            assertTrue(noteIsPresented);
        }

        thrown.expect(IndexOutOfBoundsException.class);
        notebook.remove(-1);
        notebook.remove(notebook.getSize());
    }

    @Test
    public void removeByObject() throws Exception
    {
        for (Note note : notes) notebook.add(note);

        assertEquals(markedNoteIndex, notebook.remove(markedNote));
        assertEquals(-1, notebook.remove(specialNote));

        for (int i = 0; i < notebook.getSize(); i++) {
            final Note notebookNote = notebook.get(i);
            boolean noteIsPresented = false;

            assertFalse(notebookNote.equals(markedNote));

            for (Note note : notes) {
                if (note.equals(notebookNote)) {
                    noteIsPresented = true;
                    break;
                }
            }

            assertTrue(noteIsPresented);
        }

        thrown.expect(IllegalArgumentException.class);
        notebook.remove(null);
    }

    @Test
    public void update() throws Exception
    {
        for (Note note : notes) notebook.add(note);

        notebook.update(markedNoteIndex, specialNote);
        assertEquals(specialNote, notebook.get(markedNoteIndex));

        for (Note note : notes) {
            if (note.equals(markedNote)) continue;

            boolean noteIsPresented = false;

            for(int i = 0; i < notebook.getSize(); i++) {
                final Note notebookNote = notebook.get(i);

                if (note.equals(notebookNote)) {
                    noteIsPresented = true;
                    break;
                }
            }

            assertTrue(noteIsPresented);
        }

        thrown.expect(IllegalArgumentException.class);
        notebook.update(markedNoteIndex, null);

        thrown.expect(IndexOutOfBoundsException.class);
        notebook.update(notes.length, specialNote);
    }

    @Test
    public void get() throws Exception
    {
        for (Note note : notes) notebook.add(note);

        for (int i = 0; i < notebook.getSize(); i++) {
            Note notebookNote = notebook.get(i);
            boolean noteIsPresent = false;
            for (Note note : notes) {
                if (notebookNote.equals(note)) {
                    noteIsPresent = true;
                    break;
                }
            }
            assertTrue(noteIsPresent);
        }

        thrown.expect(IndexOutOfBoundsException.class);
        notebook.get(notes.length);
    }
}
