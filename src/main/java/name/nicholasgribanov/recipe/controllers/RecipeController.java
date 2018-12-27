package name.nicholasgribanov.recipe.controllers;


import lombok.extern.slf4j.Slf4j;
import name.nicholasgribanov.recipe.NotFoundException;
import name.nicholasgribanov.recipe.commands.RecipeCommand;
import name.nicholasgribanov.recipe.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
public class RecipeController {
    public static final String RECIPE_RECIPEFORM = "recipe/recipeform";
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

        return RECIPE_RECIPEFORM;
    }

    @PostMapping("recipe")
    public String savedOrUpdateRecipe(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult result) {

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> {
                log.debug(error.toString());
            });

            return RECIPE_RECIPEFORM;
        }

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipeById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findRecipeCommandById(new Long(id)));
        return RECIPE_RECIPEFORM;
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipeById(@PathVariable String id) {
        recipeService.deleteById(new Long(id));
        log.debug("Deleted id = {} ", id);
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView notFoundHandling(Exception e) {
        log.error("Page not found");
        log.error(e.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", e);

        return modelAndView;
    }

}
