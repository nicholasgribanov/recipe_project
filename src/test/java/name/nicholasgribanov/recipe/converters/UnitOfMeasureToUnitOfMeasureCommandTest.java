package name.nicholasgribanov.recipe.converters;

import name.nicholasgribanov.recipe.commands.UnitOfMeasureCommand;
import name.nicholasgribanov.recipe.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {
    private UnitOfMeasureToUnitOfMeasureCommand converter;
    private static final Long ID = 1L;
    private static final String DESCRIPTION = "description";

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void convert() {
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(ID);
        uom.setDescription(DESCRIPTION);

        UnitOfMeasureCommand uomCommand = converter.convert(uom);

        assertEquals(ID, uomCommand.getId());
        assertEquals(DESCRIPTION, uomCommand.getDescription());
    }

    @Test
    public void emptyObject() {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    public void nullObject() {
        assertNull(converter.convert(null));
    }
}