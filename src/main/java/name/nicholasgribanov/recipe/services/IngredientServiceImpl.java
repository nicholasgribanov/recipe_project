package name.nicholasgribanov.recipe.services;

import lombok.extern.slf4j.Slf4j;
import name.nicholasgribanov.recipe.commands.IngredientCommand;
import name.nicholasgribanov.recipe.converters.IngredientToIngredientCommand;
import name.nicholasgribanov.recipe.domain.Recipe;
import name.nicholasgribanov.recipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (!recipeOptional.isPresent()) {
            //TODO impl error handle
            log.error("Recipe id {} not found", recipeId);
        }
        Recipe recipe = recipeOptional.get();
        Optional<IngredientCommand> ingredientCommand = recipe.getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredientToIngredientCommand::convert)
                .findFirst();

        if (!ingredientCommand.isPresent()) {
            //TODO impl error handle
            log.error("Ingredient id {} not found", ingredientId);
        }

        return ingredientCommand.get();

    }
}
