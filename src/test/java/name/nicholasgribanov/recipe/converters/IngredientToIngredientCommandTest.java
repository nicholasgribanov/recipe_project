package name.nicholasgribanov.recipe.converters;

import name.nicholasgribanov.recipe.commands.IngredientCommand;
import name.nicholasgribanov.recipe.domain.Ingredient;
import name.nicholasgribanov.recipe.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

    private static final Long ID = 1L;
    private static final String DESCRIPTION = "description";
    private static final BigDecimal AMOUNT = new BigDecimal(1);
    private UnitOfMeasureToUnitOfMeasureCommand uomConverter;
    private IngredientToIngredientCommand converter;

    @Before
    public void setUp() throws Exception {
        uomConverter = new UnitOfMeasureToUnitOfMeasureCommand();
        converter = new IngredientToIngredientCommand(uomConverter);
    }

    @Test
    public void convert() {
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(ID);
        uom.setDescription(DESCRIPTION);
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        ingredient.setUom(uom);

        IngredientCommand ingredientCommand = converter.convert(ingredient);

        assertEquals(ID, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(ID,ingredientCommand.getUom().getId());
        assertEquals(DESCRIPTION, ingredientCommand.getUom().getDescription());
    }

    @Test
    public void nullObjcet(){
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObject(){
        assertNotNull(converter.convert(new Ingredient()));
    }
}