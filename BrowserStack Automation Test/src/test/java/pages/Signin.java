package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Signin {
    WebDriver browser;
    String ExpectedURL = "https://bstackdemo.com/signin";
    String ActualURL;

    By UserNameBoxby = By.id("username");
    By PasswordBoxby = By.id("password");
    By Loginbuttonby = By.id("login-btn");

    WebDriverWait wait;
    public Signin(WebDriver browser) {
        this.browser = browser;
        wait =  new WebDriverWait(browser, Duration.ofSeconds(10));
    }

    public String getExpectedURL() {
        return ExpectedURL;
    }

    public String getActualURL() {
        ActualURL = browser.getCurrentUrl();
        return ActualURL;
    }

//    public void SendUsername(String username){
//        browser.findElement(UserNameBoxby).sendKeys("demouser");
//    }
//    public void SendPassword(String password){
//        browser.findElement(PasswordBoxby).sendKeys("testingisfun99");
//    }
    public HomePage clickLogin(){
        wait.until(ExpectedConditions.elementToBeClickable(UserNameBoxby)).click();
        browser.findElement(By.xpath("//div[text()=\"demouser\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(PasswordBoxby)).click();
        browser.findElement(By.xpath("//div[text()=\"testingisfun99\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(Loginbuttonby));
        browser.findElement(Loginbuttonby).click();
        return new HomePage(browser);
    }
}
