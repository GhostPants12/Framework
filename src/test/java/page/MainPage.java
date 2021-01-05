package page;

import model.Category;
import model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends Page {

    private static final String MAIN_PAGE_URL = "https://belita-shop.by/";

    @FindBy(className = "item-catalog")
    private WebElement catalogButton;

    @FindBy(className = "form-control")
    private WebElement searchField;

    @FindBy(className = "submit")
    private WebElement searchSubmit;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage openURL()
    {
        this.driver.get(MAIN_PAGE_URL);
        new WebDriverWait(driver, TIMEOUT)
                .until(jQueryAJAXCompleted());
        return this;
    }

    public CatalogPage chooseCategory(Category category){
        catalogButton.click();
        new WebDriverWait(driver, TIMEOUT)
                .until(jQueryAJAXCompleted());
        driver.findElement(By.linkText(category.getName())).click();
        return new CatalogPage(driver);
    }

    public SearchResultPage searchForProduct(Product product){
        searchField.sendKeys(product.getName());
        searchSubmit.click();
        return new SearchResultPage(driver);
    }
}
