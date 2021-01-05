package test;

import model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import page.MainPage;
import service.ProductCreator;
import util.TestRunner;

@RunWith(TestRunner.class)
public class SearchTest extends CommonConditions {
    @Test
    public void findByProductNameTest()
    {
        Product productToSearch = new ProductCreator().withSingleQuantity("one");
        Assert.assertTrue(new MainPage(driver)
                .openURL()
                .searchForProduct(productToSearch)
                .containsResult(productToSearch.getName()));
    }
}
