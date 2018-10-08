package name.nicholasgribanov.recipe.bootstrap;

import name.nicholasgribanov.recipe.domain.*;
import name.nicholasgribanov.recipe.repositories.CategoryRepository;
import name.nicholasgribanov.recipe.repositories.RecipeRepository;
import name.nicholasgribanov.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void run(String... args) throws Exception {



        Recipe spicyGrilledChickenTacos = new Recipe();
        spicyGrilledChickenTacos.setDescription("Look for ancho chile powder with the Mexican ingredients" +
                " at your grocery store, on buy it online.");
        spicyGrilledChickenTacos.setCookTime(15);
        spicyGrilledChickenTacos.setPrepCook(20);
        spicyGrilledChickenTacos.setDifficulty(Difficulty.EAYSY);


        Set<Category> categoriesForSGCT = new HashSet<>();
        categoriesForSGCT.add(categoryRepository.findByDescription("Dinner").get());
        categoriesForSGCT.add(categoryRepository.findByDescription("Grill").get());
        categoriesForSGCT.add(categoryRepository.findByDescription("Quick and easy").get());
        categoriesForSGCT.add(categoryRepository.findByDescription("Chicken").get());
        spicyGrilledChickenTacos.setCategories(categoriesForSGCT);

        Set<Ingredient> ingredientsForSGCT = new HashSet<>();
        Ingredient ingredient = new Ingredient();

        ingredient.setAmount(BigDecimal.valueOf(2));
        ingredient.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").get());
        ingredient.setDescription("ancho chili powder");
        ingredient.setRecipe(spicyGrilledChickenTacos);
        ingredientsForSGCT.add(ingredient);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setAmount(BigDecimal.valueOf(1));
        ingredient1.setUom(unitOfMeasureRepository.findByDescription("Teaspoon").get());
        ingredient1.setDescription("dried oregano");
        ingredient1.setRecipe(spicyGrilledChickenTacos);
        ingredientsForSGCT.add(ingredient1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setAmount(BigDecimal.valueOf(1));
        ingredient2.setUom(unitOfMeasureRepository.findByDescription("Teaspoon").get());
        ingredient2.setDescription("dried cumin");
        ingredient2.setRecipe(spicyGrilledChickenTacos);
        ingredientsForSGCT.add(ingredient2);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setAmount(BigDecimal.valueOf(1));
        ingredient3.setUom(unitOfMeasureRepository.findByDescription("Teaspoon").get());
        ingredient3.setDescription("sugar");
        ingredient3.setRecipe(spicyGrilledChickenTacos);
        ingredientsForSGCT.add(ingredient3);

        Ingredient ingredient4 = new Ingredient();
        ingredient4.setAmount(BigDecimal.valueOf(0.5));
        ingredient4.setUom(unitOfMeasureRepository.findByDescription("Teaspoon").get());
        ingredient4.setDescription("salt");
        ingredient4.setRecipe(spicyGrilledChickenTacos);
        ingredientsForSGCT.add(ingredient4);

        Ingredient ingredient5 = new Ingredient();
        ingredient5.setAmount(BigDecimal.valueOf(1));
        ingredient5.setUom(unitOfMeasureRepository.findByDescription("Clove").get());
        ingredient5.setDescription("garlic, finely chopped");
        ingredient5.setRecipe(spicyGrilledChickenTacos);
        ingredientsForSGCT.add(ingredient5);

        Ingredient ingredient6 = new Ingredient();
        ingredient6.setAmount(BigDecimal.valueOf(1));
        ingredient6.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").get());
        ingredient6.setDescription("finely grated orange zest");
        ingredient6.setRecipe(spicyGrilledChickenTacos);
        ingredientsForSGCT.add(ingredient6);

        Ingredient ingredient7 = new Ingredient();
        ingredient7.setAmount(BigDecimal.valueOf(3));
        ingredient7.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").get());
        ingredient7.setDescription("fresh-squeezed orange juice");
        ingredient7.setRecipe(spicyGrilledChickenTacos);
        ingredientsForSGCT.add(ingredient7);

        Ingredient ingredient8 = new Ingredient();
        ingredient8.setAmount(BigDecimal.valueOf(2));
        ingredient8.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").get());
        ingredient8.setDescription("olive oil");
        ingredient8.setRecipe(spicyGrilledChickenTacos);
        ingredientsForSGCT.add(ingredient8);

        Ingredient ingredient9 = new Ingredient();
        ingredient9.setAmount(BigDecimal.valueOf(0.25));
        ingredient9.setUom(unitOfMeasureRepository.findByDescription("Pounds").get());
        ingredient9.setDescription("skinless, boneless chicken thighs ");
        ingredient9.setRecipe(spicyGrilledChickenTacos);
        ingredientsForSGCT.add(ingredient9);

        Notes notes = new Notes();
        notes.setRecipe(spicyGrilledChickenTacos);
        notes.setRecipeNotes("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "Spicy Grilled Chicken Tacos\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");

        spicyGrilledChickenTacos.setNotes(notes);
        spicyGrilledChickenTacos.setIngredients(ingredientsForSGCT);
        recipeRepository.save(spicyGrilledChickenTacos);











    }
}
