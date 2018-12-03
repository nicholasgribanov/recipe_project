package name.nicholasgribanov.recipe.services;

import name.nicholasgribanov.recipe.commands.RecipeCommand;
import name.nicholasgribanov.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
    Recipe getRecipeById(Long id);
    RecipeCommand findRecipeCommandById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
}
