package name.nicholasgribanov.recipe.converters;

import name.nicholasgribanov.recipe.commands.UnitOfMeasureCommand;
import name.nicholasgribanov.recipe.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {
    private UnitOfMeasureCommandToUnitOfMeasure converter;
    private static final Long ID = 1L;
    private static final String DESCRIPTION = "description";

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void convert() {
        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(ID);
        uomCommand.setDescription(DESCRIPTION);

        UnitOfMeasure uom = converter.convert(uomCommand);

        assertEquals(ID, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());
    }

    @Test
    public void emptyObject() {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void nullObject() {
        assertNull(converter.convert(null));
    }
}