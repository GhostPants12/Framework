package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class ProductPage extends Page {

    private static final String CART_URL = "https://belita-shop.by/personal/cart/";

    @FindBy(className = "btn-wrap")
    WebElement addToCartButton;

    @FindBy(className = "cart-links__icon")
    WebElement goToCartButton;

    @FindBy(xpath = "//div[@class = 'price-wrap']/input")
    WebElement quantityInput;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage buyProduct(){
        addToCartButton.click();
        return this;
    }

    public ProductPage buyProduct(int quantity){
        if(quantity > 1)
        {
            quantityInput.clear();
            quantityInput.sendKeys(String.valueOf(quantity));
        }

        return this.buyProduct();
    }

    public boolean isInCart()
    {
        new WebDriverWait(driver, TIMEOUT)
                .until(jQueryAJAXCompleted());
        return driver.findElements(By.linkText("Уже в корзине")).size() > 0;
    }

    public ProductPage openUrl(String url)
    {
            this.driver.get(url);
            new WebDriverWait(driver, TIMEOUT)
                    .until(jQueryAJAXCompleted());
            return this;
    }

    public CartPage goToCart(){
        driver.get(CART_URL);
        return new CartPage(driver);
    }
}
