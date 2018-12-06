package name.nicholasgribanov.recipe.controllers;

import lombok.extern.slf4j.Slf4j;
import name.nicholasgribanov.recipe.commands.IngredientCommand;
import name.nicholasgribanov.recipe.services.IngredientService;
import name.nicholasgribanov.recipe.services.RecipeService;
import name.nicholasgribanov.recipe.services.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IngredientController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String getIngredientsById(@PathVariable String recipeId, Model model) {
        log.debug("Getting ingredients for recipeId = {}", recipeId);
        model.addAttribute("recipe", recipeService.findRecipeCommandById(new Long(recipeId)));
        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
        return "recipe/ingredient/show";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId),
                Long.valueOf(ingredientId)));
        model.addAttribute("uomList", unitOfMeasureService.findAllUom());
        return "recipe/ingredient/ingredientform";
    }

    @PostMapping
    @RequestMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command) {
        IngredientCommand ingredientSaved = ingredientService.saveIngredientCommand(command);

        log.debug("saved recipe id = {}", ingredientSaved.getRecipeId());
        log.debug("saved ingredient id = {}", ingredientSaved.getId());

        return "redirect:/recipe/" + ingredientSaved.getRecipeId() + "/ingredient/" + ingredientSaved.getId() + "/show";
    }
}
