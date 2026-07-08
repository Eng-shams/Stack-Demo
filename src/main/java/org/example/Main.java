package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.net.URL;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws  InterruptedException {
        // valid senario
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        Thread.sleep(2000);

        driver.navigate().to("https://bstackdemo.com/signin");



        WebElement userName = driver.findElement(By.id("username"));
        userName.click();
        WebElement userNameText = driver.findElement(By.xpath("//*[text()='demouser']"));
        Thread.sleep(2000);
        userNameText.click();


        WebElement pass = driver.findElement(By.id("password"));
        pass.click();
        WebElement passText = driver.findElement(By.xpath("//*[text()='testingisfun99']"));
        Thread.sleep(2000);
        passText.click();


        WebElement loginButton = driver.findElement(By.id("login-btn"));
        loginButton.click();

        Thread.sleep(5000);
        String currentUrl = driver.getCurrentUrl();

        if(currentUrl.contains("signin=true")){
            System.out.println("page open successfully");
        }
        else {
            System.out.println("page didn't open");
        }

        WebElement logoutButton = driver.findElement(By.id("signin"));
        logoutButton.click();
        Thread.sleep(1000);
        logoutButton.click();




















    }
}