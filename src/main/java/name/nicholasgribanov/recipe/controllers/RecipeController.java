package name.nicholasgribanov.recipe.controllers;


import name.nicholasgribanov.recipe.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {
    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @RequestMapping({"/recipes", "recipes/index"})
    public String getListOfRecipe(Model model){
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipeIndex";
    }
}
