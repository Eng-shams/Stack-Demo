package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Handles the "Sign In" flow. Username/password are React-Select dropdowns,
 * not plain <input> fields, so we type into them and hit ENTER to pick the
 * highlighted match instead of clicking a specific option index (that index
 * changes depending on which users exist, so typing is more reliable).
 *
 * NOTE: Locators below are based on the site's known structure. If the site
 * markup has changed, open dev tools on bstackdemo.com and update the
 * By.xxx values here - that's the only place they live.
 */
public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By signInLink = By.id("signin");
    private final By usernameDropdown = By.cssSelector("#username input");
    private final By passwordDropdown = By.cssSelector("#password input");
    private final By loginButton = By.id("login-btn");
    private final By usernameLabel = By.id("username-value");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openSignInForm() {
        wait.until(ExpectedConditions.elementToBeClickable(signInLink)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameDropdown));
    }

    public void login(String username, String password) {
        openSignInForm();

        driver.findElement(usernameDropdown).sendKeys(username);
        driver.findElement(usernameDropdown).sendKeys(Keys.ENTER);

        driver.findElement(passwordDropdown).sendKeys(password);
        driver.findElement(passwordDropdown).sendKeys(Keys.ENTER);

        driver.findElement(loginButton).click();

    }
}
