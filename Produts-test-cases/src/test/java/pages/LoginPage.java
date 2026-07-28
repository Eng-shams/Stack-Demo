package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
