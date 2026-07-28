package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Product card locators
    private final By productCards = By.cssSelector(".shelf-item");
    private final By cardTitle = By.cssSelector(".shelf-item__title");
    private final By cardPrice = By.cssSelector(".shelf-item__price");
    private final By cardImage = By.cssSelector(".shelf-item__thumb img");
    private final By cardAddToCartBtn = By.cssSelector(".shelf-item__buy-btn");
    private final By cardFavoriteBtn = By.cssSelector(".shelf-stopper button");

    // Search bar locators
    private final By searchIcon = By.cssSelector(".search__icon, #search");
    private final By searchInput = By.cssSelector("#search_input, input.search-field__input");
    private final By searchButton = By.cssSelector(".search-field__button, button[type='submit']");
    private final By noResultsMessage = By.xpath("//*[contains(text(),'No results') or contains(text(),'no results')]");

    public ProductsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public List<WebElement> getProductCards() {
        wait.until(ExpectedConditions.presenceOfElementLocated(productCards));
        return driver.findElements(productCards);
    }

    public String getTitle(WebElement card) {
        return card.findElement(cardTitle).getText().trim();
    }

    public String getPriceText(WebElement card) {
        return card.findElement(cardPrice).getText().trim();
    }

    public WebElement getImage(WebElement card) {
        return card.findElement(cardImage);
    }

    public String getImageSrc(WebElement card) {
        return getImage(card).getAttribute("src");
    }

    public String getImageAlt(WebElement card) {
        return getImage(card).getAttribute("alt");
    }

    /** True when the browser actually rendered pixels for the image (not a broken icon). */
    public boolean isImageLoaded(WebElement card) {
        WebElement img = getImage(card);
        Long naturalWidth = (Long) ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("return arguments[0].naturalWidth;", img);
        return naturalWidth != null && naturalWidth > 0;
    }

    public void addToCart(WebElement card) {
        card.findElement(cardAddToCartBtn).click();
    }

    public void addToFavorites(WebElement card) {
        card.findElement(cardFavoriteBtn).click();
        System.out.print("hi : )");
    }
}
