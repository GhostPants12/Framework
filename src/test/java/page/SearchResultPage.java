package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage extends Page {
    protected SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public boolean containsResult(String result){
        new WebDriverWait(driver, TIMEOUT)
                .until(jQueryAJAXCompleted());
        return driver.findElements(By.linkText(result)).size() > 0;
    }
}
