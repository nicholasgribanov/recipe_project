package name.nicholasgribanov.recipe.services;

import lombok.extern.slf4j.Slf4j;
import name.nicholasgribanov.recipe.domain.Recipe;
import name.nicholasgribanov.recipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {
        try {
            Recipe recipe = recipeRepository.findById(recipeId).get();

            Byte[] bytes = new Byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                bytes[i++] = b;
            }
            recipe.setImage(bytes);

            recipeRepository.save(recipe);
        } catch (Exception e) {
            log.error("Somthing brake into save image" + e.getLocalizedMessage());
        }
    }
}
