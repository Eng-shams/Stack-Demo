package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage { WebDriver driver;


    public LoginPage(WebDriver driver){
        this.driver = driver;
    }



    By loginButton = By.id("signin");



    By submit = By.id("login-btn");



    public void login(String user, String pass) throws InterruptedException {

        driver.findElement(loginButton).click();
        Thread.sleep(1000);

        driver.findElement(By.id("username")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[text()='"+user+"']")).click();

        driver.findElement(By.id("password")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[text()='"+pass+"']")).click();

        driver.findElement(submit).click();
        Thread.sleep(1000);

    }
}
