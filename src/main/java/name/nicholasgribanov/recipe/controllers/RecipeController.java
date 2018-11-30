package name.nicholasgribanov.recipe.controllers;


import name.nicholasgribanov.recipe.commands.RecipeCommand;
import name.nicholasgribanov.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/recipes", "recipes/index"})
    public String getListOfRecipe(Model model) {
        model.addAttribute("recipes", recipeService.getRecipes());
        return "recipeIndex";
    }

    @RequestMapping({"/recipe/show/{id}"})
    public String getRecipeById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeById(new Long(id)));
        return "recipe/show";
    }

    @RequestMapping("recipe/recipeform")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping("recipe")
    public String savedOrUpdateRecipe(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/show/" + savedCommand.getId();
    }
}
