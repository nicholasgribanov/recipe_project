package name.nicholasgribanov.recipe.converters;

import name.nicholasgribanov.recipe.commands.CategoryCommand;
import name.nicholasgribanov.recipe.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

    private static final Long ID = 1L;
    private static final String DESCRIPTION = "description";
    private CategoryToCategoryCommand converter;


    @Before
    public void setUp() throws Exception {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void convert() {
        Category category = new Category();
        category.setId(ID);
        category.setDescription(DESCRIPTION);

        CategoryCommand categoryCommand = converter.convert(category);

        assertEquals(ID, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getDescription());
    }

    @Test
    public void emptyObject(){
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void nullObject(){
        assertNull(converter.convert(null));
    }
}