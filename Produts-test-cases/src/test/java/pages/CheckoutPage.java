package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Order review step shown after clicking "Checkout" in the cart.
 * Requires the user to be logged in first (see LoginPage).
 */
public class CheckoutPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By orderItem = By.cssSelector(".product");
    private final By itemTitle = By.cssSelector(".product-title");
    private final By itemPrice = By.cssSelector(".product-price");
    private final By itemImage = By.cssSelector("img");

    public CheckoutPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public WebElement getFirstOrderItem() {
        wait.until(ExpectedConditions.presenceOfElementLocated(orderItem));
        return driver.findElements(orderItem).get(0);
    }

    public String getTitle(WebElement item) {
        return item.findElement(itemTitle).getText().trim();
    }

    public String getPriceText(WebElement item) {
        return item.findElement(itemPrice).getText().trim();
    }

    public String getImageSrc(WebElement item) {
        return item.findElement(itemImage).getAttribute("src");
    }
}
