package test;

import model.Category;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import page.MainPage;
import service.CategoryCreator;
import util.TestRunner;

@RunWith(TestRunner.class)
public class CategoryTest extends CommonConditions{
    @Test
    public void chooseCategoryTest(){
        String categoryName = "Кремы для лица";
        Assert.assertEquals(categoryName, new MainPage(driver)
                .openURL()
                .chooseCategory(new CategoryCreator().withNoSorting())
                .getCatalogName());
    }

    @Test
    public void sortTest(){
        Category category = new CategoryCreator().withSorting();
        Assert.assertEquals("Сначала хиты", new MainPage(driver)
                .openURL()
                .chooseCategory(category)
                .changeSorting(category)
                .getSortingType());
    }

}
