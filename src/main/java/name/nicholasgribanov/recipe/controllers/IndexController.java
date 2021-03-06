package name.nicholasgribanov.recipe.controllers;

import lombok.extern.slf4j.Slf4j;
import name.nicholasgribanov.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
public class IndexController {
    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"","/","/index"})
    public String getIndexPage(Model model){
        log.debug("Get Index page");
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
