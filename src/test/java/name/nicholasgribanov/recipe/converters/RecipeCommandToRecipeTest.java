package name.nicholasgribanov.recipe.converters;

import name.nicholasgribanov.recipe.commands.CategoryCommand;
import name.nicholasgribanov.recipe.commands.IngredientCommand;
import name.nicholasgribanov.recipe.commands.NotesCommand;
import name.nicholasgribanov.recipe.commands.RecipeCommand;
import name.nicholasgribanov.recipe.domain.Difficulty;
import name.nicholasgribanov.recipe.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {
    private RecipeCommandToRecipe converter;
    private static final Long ID = 1L;
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final Integer PREP_COOK = 2;
    private static final Integer COOK_TIME = 3;
    private static final Integer SERVINGS = 4;
    private static final String SOURCE = "SOURCE";
    private static final String URL = "http://nicholasgrbanov.name";
    private static final String DIRECTIONS = "DIRECTIONS";
    private static final Long INGREDIENT_ID_1 = 1L;
    private static final Long INGREDIENT_ID_2 = 2L;
    private static final Difficulty DIFFICULTY = Difficulty.EAYSY;
    private static final Long NOTES_ID = 1L;
    private static final Long CATEGORY_ID_1 = 1L;
    private static final Long CATEGORY_ID_2 = 2L;


    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes(), new CategoryCommandToCategory());
    }

    @Test
    public void convert() {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setPrepCook(PREP_COOK);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setDifficulty(DIFFICULTY);

        IngredientCommand ingredientCommand1 = new IngredientCommand();
        IngredientCommand ingredientCommand2 = new IngredientCommand();
        CategoryCommand categoryCommand1 = new CategoryCommand();
        CategoryCommand categoryCommand2 = new CategoryCommand();
        NotesCommand notesCommand = new NotesCommand();
        ingredientCommand1.setId(INGREDIENT_ID_1);
        ingredientCommand2.setId(INGREDIENT_ID_2);
        categoryCommand1.setId(CATEGORY_ID_1);
        categoryCommand2.setId(CATEGORY_ID_2);
        notesCommand.setId(NOTES_ID);

        recipeCommand.getIngredients().add(ingredientCommand1);
        recipeCommand.getIngredients().add(ingredientCommand2);
        recipeCommand.getCategories().add(categoryCommand1);
        recipeCommand.getCategories().add(categoryCommand2);
        recipeCommand.setNotes(notesCommand);

        Recipe recipe = converter.convert(recipeCommand);

        assertEquals(ID, recipe.getId());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(PREP_COOK, recipe.getPrepCook());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getIngredients().size());
        assertEquals(2, recipe.getCategories().size());
    }

    @Test
    public void nullObject() {
        converter.convert(null);
    }

    @Test
    public void emptyObject() {
        converter.convert(new RecipeCommand());
    }
}