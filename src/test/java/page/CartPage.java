package page;

import model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CartPage extends Page {
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(className = "basket-coupon-block-total-price-current")
    WebElement price;

    @FindBy(className = "basket-item-amount-filed")
    List<WebElement> quantities;

    @FindBy(xpath = "//table[@class = 'basket-items-list-table']/tbody/tr")
    List<WebElement> products;

    @FindBy(className = "basket-item-info-name-link")
    List<WebElement> productsNames;

    @FindBy(xpath = "//input[@data-entity='basket-coupon-input']")
    WebElement promoInputField;

    @FindBy(className = "basket-coupon-block-coupon-btn")
    WebElement promoButton;


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean containsProduct(Product prod){
        for (int i = 0; i < productsNames.size(); i++) {
            if(productsNames.get(i).getText().equals(prod.getName())){
                if(Integer.parseInt(quantities.get(i).getAttribute("value"))==prod.getQuantity()){
                    return true;
                }

                return false;
            }
        }

        return false;
    }

    public boolean containsProducts(List<Product> products){
        for (Product prod: products
             ) {
            if(!this.containsProduct(prod)){
                return false;
            }
        }

        return true;
    }

    public CartPage deleteProduct(Product prod){
        for (int i = 0; i < productsNames.size(); i++) {
            if(productsNames.get(i).getText().equals(prod.getName())){
                Actions actions = new Actions(driver);
                actions.moveToElement(products
                        .get(i)
                        .findElement(By.xpath("//td[@class='basket-items-list-item-remove hidden-xs']"))).perform();
                products.get(i).findElement(By.className("basket-item-block-actions")).click();
                logger.info("Deleted product with name: ["+prod.getName()+"]");
                break;
            }
        }

        new WebDriverWait(driver, TIMEOUT).until(jQueryAJAXCompleted());
        return this;
    }

    public CartPage restoreProduct(){
        this.retryingFindClick(By.linkText("Восстановить"));
        logger.info("Product was restored.");
        return this;
    }

    public String getPrice(){
        return price.getText();
    }

    public CartPage setPromo(String promo){
        promoInputField.sendKeys(promo);
        promoButton.click();
        return this;
    }

    public boolean isPromoCorrect(){
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        return driver.findElements(By.className("basket-coupon-text")).size() == 0;
    }
}
