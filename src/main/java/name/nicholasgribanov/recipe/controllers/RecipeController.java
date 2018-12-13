package name.nicholasgribanov.recipe.controllers;


import lombok.extern.slf4j.Slf4j;
import name.nicholasgribanov.recipe.commands.RecipeCommand;
import name.nicholasgribanov.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"/recipes", "recipes/index"})
    public String getListOfRecipe(Model model) {
        model.addAttribute("recipes", recipeService.getRecipes());
        return "recipeIndex";
    }

    @GetMapping({"/recipe/{id}/show"})
    public String getRecipeById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeById(new Long(id)));
        return "recipe/show";
    }

    @GetMapping("recipe/recipeform")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String savedOrUpdateRecipe(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipeById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findRecipeCommandById(new Long(id)));
        return "recipe/recipeform";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipeById(@PathVariable String id) {
        recipeService.deleteById(new Long(id));
        log.debug("Deleted id = {} ", id);
        return "redirect:/";
    }
}
