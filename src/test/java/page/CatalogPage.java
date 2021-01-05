package page;

import model.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CatalogPage extends Page {
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(className = "title")
    private WebElement catalogName;

    @FindBy(className = "jq-selectbox__select")
    private WebElement sortingTypeBox;

    @FindBy(className = "jq-selectbox__select-text")
    private WebElement sortingTypeText;


    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    public String getCatalogName(){
        return  catalogName.getText();
    }

    public CatalogPage changeSorting(Category category){
        sortingTypeBox.click();
        new WebDriverWait(driver, TIMEOUT)
                .until(jQueryAJAXCompleted());
        WebElement categoryToChoose = driver.findElement(By
                .xpath("//li[text()='"+category.getSorting()+"']"));
        categoryToChoose.click();
        logger.info("Category was changed to ["+category.getSorting()+"]");
        new WebDriverWait(driver, TIMEOUT)
                .until(jQueryAJAXCompleted());
        return this;
    }

    public String getSortingType(){
        return sortingTypeText.getText();
    }
}
