package name.nicholasgribanov.recipe.controllers;

import name.nicholasgribanov.recipe.commands.RecipeCommand;
import name.nicholasgribanov.recipe.services.ImageService;
import name.nicholasgribanov.recipe.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ImageControllerTest {

    @Mock
    ImageService imageService;
    @Mock
    RecipeService recipeService;

    ImageController imageController;
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        imageController = new ImageController(recipeService, imageService);
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).setControllerAdvice(new ControllerExceptionHandling()).build();
    }

    @Test
    public void showUploadForm() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeService.findRecipeCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/1/image"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"));
        verify(recipeService, times(1)).findRecipeCommandById(anyLong());
    }

    @Test
    public void handleImagePost() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt",
                "text/plain", "Nicholas Recipies".getBytes());

        mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/recipe/1/show"));

        verify(imageService, times(1)).saveImageFile(anyLong(), any());
    }

    @Test
    public void getImageFromDB() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        String text = "Example of image";
        Byte [] textBytes = new Byte[text.getBytes().length];
        int i=0;
        for(byte b:text.getBytes()){
            textBytes[i++]=b;
        }
        recipeCommand.setImage(textBytes);

        when(recipeService.findRecipeCommandById(anyLong())).thenReturn(recipeCommand);

        MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeimage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        byte [] responseByte = response.getContentAsByteArray();

        assertEquals(responseByte.length, text.getBytes().length);
    }

    @Test
    public void badNumberFormatException() throws Exception {
        mockMvc.perform(get("/recipe/sadasd/recipeimage"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }
}