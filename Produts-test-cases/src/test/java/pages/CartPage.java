package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cartPanel = By.cssSelector(".float-cart__content");
    private final By cartItem = By.cssSelector(".float-cart__shelf-container .shelf-item");
    private final By itemTitle = By.cssSelector(".shelf-item__title, p.title");
    private final By itemImage = By.cssSelector("img");
    private final By checkoutButton = By.cssSelector(".buy-btn");

    public CartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void waitUntilOpen() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartPanel));
    }

    public WebElement getFirstItem() {
        waitUntilOpen();
        return driver.findElements(cartItem).get(0);
    }

    public String getTitle(WebElement item) {
        return item.findElement(itemTitle).getText().trim();
    }

    public String getImageSrc(WebElement item) {
        return item.findElement(itemImage).getAttribute("src");
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
}
