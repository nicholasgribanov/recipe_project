package name.nicholasgribanov.recipe.converters;

import name.nicholasgribanov.recipe.commands.RecipeCommand;
import name.nicholasgribanov.recipe.domain.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {
    private RecipeToRecipeCommand converter;
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
        converter = new RecipeToRecipeCommand(new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new NotesToNotesCommand(), new CategoryToCategoryCommand());
    }

    @Test
    public void convert() {
        Recipe recipe = new Recipe();
        recipe.setId(ID);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepCook(PREP_COOK);
        recipe.setCookTime(COOK_TIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setDirections(DIRECTIONS);
        recipe.setDifficulty(DIFFICULTY);

        Ingredient ingredient1 = new Ingredient();
        Ingredient ingredient2 = new Ingredient();
        Category category1 = new Category();
        Category category2 = new Category();
        Notes notes = new Notes();
        ingredient1.setId(INGREDIENT_ID_1);
        ingredient2.setId(INGREDIENT_ID_2);
        category1.setId(CATEGORY_ID_1);
        category2.setId(CATEGORY_ID_2);
        notes.setId(NOTES_ID);

        recipe.getIngredients().add(ingredient1);
        recipe.getIngredients().add(ingredient2);
        recipe.getCategories().add(category1);
        recipe.getCategories().add(category2);
        recipe.setNotes(notes);

        RecipeCommand recipeCommand = converter.convert(recipe);

        assertEquals(ID, recipeCommand.getId());
        assertEquals(DESCRIPTION, recipeCommand.getDescription());
        assertEquals(PREP_COOK, recipeCommand.getPrepCook());
        assertEquals(COOK_TIME, recipeCommand.getCookTime());
        assertEquals(SERVINGS, recipeCommand.getServings());
        assertEquals(SOURCE, recipeCommand.getSource());
        assertEquals(URL, recipeCommand.getUrl());
        assertEquals(DIRECTIONS, recipeCommand.getDirections());
        assertEquals(DIFFICULTY, recipeCommand.getDifficulty());
        assertEquals(NOTES_ID, recipeCommand.getNotes().getId());
        assertEquals(2, recipeCommand.getIngredients().size());
        assertEquals(2, recipeCommand.getCategories().size());
    }

    @Test
    public void nullObject() {
        converter.convert(null);
    }

    @Test
    public void emptyObject() {
        converter.convert(new Recipe());
    }
}