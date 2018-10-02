package name.nicholasgribanov.recipe.repositories;

import name.nicholasgribanov.recipe.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
