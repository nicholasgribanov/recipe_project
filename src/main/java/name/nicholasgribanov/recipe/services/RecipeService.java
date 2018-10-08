package name.nicholasgribanov.recipe.services;

import name.nicholasgribanov.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
}
