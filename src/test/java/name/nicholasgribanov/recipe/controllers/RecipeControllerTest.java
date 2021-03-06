package name.nicholasgribanov.recipe.controllers;

import name.nicholasgribanov.recipe.NotFoundException;
import name.nicholasgribanov.recipe.commands.RecipeCommand;
import name.nicholasgribanov.recipe.domain.Recipe;
import name.nicholasgribanov.recipe.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyByte;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    RecipeController recipeController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).setControllerAdvice(new ControllerExceptionHandling()).build();
    }

    @Test
    public void getRecipeById() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        when(recipeService.getRecipeById(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void recipeNotFound() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        when(recipeService.getRecipeById(anyLong())).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));

    }

    @Test
    public void numberFormatException() throws Exception {

        mockMvc.perform(get("/recipe/sdsd/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));

    }

    @Test
    public void savedOrUpdateTest() throws Exception {
        RecipeCommand recipe = new RecipeCommand();
        recipe.setId(2L);

        when(recipeService.saveRecipeCommand(any())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/2/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"));
    }

    @Test
    public void deleteById() throws Exception {

        mockMvc.perform(get("/recipe/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(recipeService, times(1)).deleteById(anyLong());
    }

    @Test
    public void testPostNewRecipeFormValidationFail() throws Exception{
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);

        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id",""))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recipe/recipeform"));

    }

}