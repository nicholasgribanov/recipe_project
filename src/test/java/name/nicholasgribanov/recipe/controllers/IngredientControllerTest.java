package name.nicholasgribanov.recipe.controllers;

import name.nicholasgribanov.recipe.commands.IngredientCommand;
import name.nicholasgribanov.recipe.commands.RecipeCommand;
import name.nicholasgribanov.recipe.domain.Ingredient;
import name.nicholasgribanov.recipe.domain.Recipe;
import name.nicholasgribanov.recipe.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IngredientControllerTest {
    @Mock
    private  RecipeService recipeService;

    private IngredientController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new IngredientController(recipeService);
    }

    @Test
    public void getIngredientsById() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        RecipeCommand recipe = new RecipeCommand();
        recipe.setId(1L);
        recipe.getIngredients().add(new IngredientCommand());

        when(recipeService.findRecipeCommandById(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"));
    }
}