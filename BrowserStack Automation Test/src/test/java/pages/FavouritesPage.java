package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FavouritesPage {
    WebDriver browser;
    String ExpectedURL = "https://bstackdemo.com/favourites";
    String ActualURL;

    By ProductId1 = By.xpath("//button[@class=\"MuiButtonBase-root MuiIconButton-root Button clicked \"]");

    public FavouritesPage(WebDriver browser) {
        this.browser = browser;
    }

    public String getExpectedURL() {
        return ExpectedURL;
    }

    public String getActualURL() {
        ActualURL = browser.getCurrentUrl();
        return ActualURL;
    }

    public boolean ElementIsDisplay() {
        return browser.findElement(ProductId1).isDisplayed();
    }

}
