package name.nicholasgribanov.recipe.repositories;

import name.nicholasgribanov.recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
