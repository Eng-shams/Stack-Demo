package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * The "Favourites" page (URL: /favourites). Reuses the same card markup
 * as the main product listing.
 */
public class FavoritesPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By favoritesLink = By.id("favourites");
    private final By favoriteCards = By.cssSelector(".shelf-item");
    private final By itemTitle = By.cssSelector(".shelf-item__title");
    private final By itemPrice = By.cssSelector(".shelf-item__price");
    private final By itemImage = By.cssSelector(".shelf-item__thumb img");

    public FavoritesPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void open() {
        driver.findElement(favoritesLink).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(favoriteCards));
    }

    public WebElement getFirstFavorite() {
        return driver.findElements(favoriteCards).get(0);
    }

    public String getTitle(WebElement card) {
        return card.findElement(itemTitle).getText().trim();
    }

    public String getImageSrc(WebElement card) {
        return card.findElement(itemImage).getAttribute("src");
    }
}
