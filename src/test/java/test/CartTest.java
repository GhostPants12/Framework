package test;

import model.Cart;
import model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import page.CartPage;
import page.ProductPage;
import service.CartCreator;
import service.ProductCreator;
import util.TestRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(TestRunner.class)
public class CartTest extends CommonConditions{
    @Test
    public void productAlreadyInCartTest(){
        Assert.assertTrue(new ProductPage(driver)
                .openUrl(new ProductCreator().withSingleQuantity("one").getUrl())
                .buyProduct()
                .isInCart());
    }

    @Test
    public void tooManyProductsTest()
    {
        Assert.assertFalse(new ProductPage(driver)
                .openUrl(new ProductCreator().withSingleQuantity("one").getUrl())
                .buyProduct(100)
                .isInCart());
    }

    @Test
    public void addProductsToCartTest(){
        Product prod = new ProductCreator().withConfigQuantity("one");

        Assert.assertTrue(new ProductPage(driver)
                .openUrl(prod.getUrl())
                .buyProduct(prod.getQuantity())
                .goToCart()
                .containsProduct(prod));
    }

    @Test
    public void deleteProductFromCartTest(){
        Product prod = new ProductCreator().withConfigQuantity("one");
        CartPage cartPage = new ProductPage(driver)
                .openUrl(prod.getUrl())
                .buyProduct(prod.getQuantity())
                .goToCart();
        String priceAfterBuying = cartPage.getPrice();

        Assert.assertTrue(
                cartPage
                        .containsProduct(prod) != cartPage
                        .deleteProduct(prod)
                        .containsProduct(prod));
        Assert.assertTrue(priceAfterBuying.equals(cartPage.getPrice()));
    }

    @Test
    public void restoreProductInCartTest(){
        Product prod = new ProductCreator().withConfigQuantity("one");
        CartPage cartPage = new ProductPage(driver)
                .openUrl(prod.getUrl())
                .buyProduct(prod.getQuantity())
                .goToCart();
        Assert.assertTrue(cartPage.containsProduct(prod) == cartPage
                .deleteProduct(prod)
                .restoreProduct()
                .containsProduct(prod));
    }

    @Test
    public void addDifferentProductsToCartTest(){
        List<Product> products = new ArrayList<Product>();
        products.add(new ProductCreator().withConfigQuantity("one"));
        products.add(new ProductCreator().withConfigQuantity("two"));
        Cart cart = CartCreator.withProducts(products);

        Assert.assertTrue(new ProductPage(driver)
                .openUrl(cart.getProduct(0).getUrl())
                .buyProduct(cart.getProduct(0).getQuantity())
                .openUrl(cart.getProduct(1).getUrl())
                .buyProduct(cart.getProduct(1).getQuantity())
                .goToCart()
                .containsProducts(cart.getProductList()));
    }

    @Test
    public void incorrectPromoTest()
    {
        Cart cart = CartCreator.withNoProducts();
        Product prod = new ProductCreator().withConfigQuantity("one");

        Assert.assertFalse(new ProductPage(driver)
                .openUrl(prod.getUrl())
                .buyProduct(prod.getQuantity())
                .goToCart()
                .setPromo(cart.getPromo())
                .isPromoCorrect());
    }
}
