package name.nicholasgribanov.recipe.converters;

import name.nicholasgribanov.recipe.commands.NotesCommand;
import name.nicholasgribanov.recipe.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {
    private static final Long ID = 1L;
    private static final String RECIPE_NOTES = "NOTES";
    private NotesCommandToNotes converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNotes();
    }

    @Test
    public void convert() {
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(ID);
        notesCommand.setRecipeNotes(RECIPE_NOTES);

        Notes notes = converter.convert(notesCommand);

        assertEquals(ID, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }

    @Test
    public void nullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObject() {
        assertNotNull(converter.convert(new NotesCommand()));
    }
}