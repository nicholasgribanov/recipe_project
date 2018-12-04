package name.nicholasgribanov.recipe.controllers;

import lombok.extern.slf4j.Slf4j;
import name.nicholasgribanov.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IngredientController {
    private final RecipeService recipeService;

    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String getIngredientsById(@PathVariable String recipeId, Model model) {
        log.debug("Getting ingredients for recipeId = {}", recipeId);
        model.addAttribute("recipe", recipeService.findRecipeCommandById(new Long(recipeId)));
        return "recipe/ingredient/list";
    }
}
