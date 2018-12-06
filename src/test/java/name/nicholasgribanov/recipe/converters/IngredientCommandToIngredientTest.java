package name.nicholasgribanov.recipe.converters;

import name.nicholasgribanov.recipe.commands.IngredientCommand;
import name.nicholasgribanov.recipe.commands.UnitOfMeasureCommand;
import name.nicholasgribanov.recipe.domain.Ingredient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    private static final Long ID = 1L;
    private static final String DESCRIPTION = "description";
    private static final BigDecimal AMOUNT = new BigDecimal(1);
    private UnitOfMeasureCommandToUnitOfMeasure uomConverter;
    private IngredientCommandToIngredient converter;

    @Before
    public void setUp() throws Exception {
        uomConverter = new UnitOfMeasureCommandToUnitOfMeasure();
        converter = new IngredientCommandToIngredient(uomConverter);
    }

    @Test
    public void convert() {
        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(ID);
        uomCommand.setDescription(DESCRIPTION);
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);
        ingredientCommand.setUom(uomCommand);

        Ingredient ingredient = converter.convert(ingredientCommand);

        assertEquals(ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(ID,ingredient.getUom().getId());
        assertEquals(DESCRIPTION, ingredient.getUom().getDescription());
    }

    @Test
    public void nullObjcet(){
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObject(){
        assertNotNull(converter.convert(new IngredientCommand()));
    }
}