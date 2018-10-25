package name.nicholasgribanov.recipe.controllers;

import name.nicholasgribanov.recipe.domain.Recipe;
import name.nicholasgribanov.recipe.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.*;

public class IndexControllerTest {
    @Mock
    private RecipeService recipeService;
    @Mock
    private Model model;

    private IndexController indexController;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void getIndexPage() {
        String indexPage = indexController.getIndexPage(model);

        assertEquals("index", indexPage);

        verify(model, times(1)).addAttribute(eq("recipes"), anySet());
        verify(recipeService,times(1)).getRecipes();
    }
}