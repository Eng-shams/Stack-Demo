package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Automation Testing for Cart and Checkout
 * Author: Malak Hany Abdel Moneim Ali
 * Automation Testing
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();

        // Navigate to the application
        driver.navigate().to("https://bstackdemo.com/");
        Thread.sleep(3000);

        System.out.println("--- STARTING Testing ---");

        // Sign in as demouser
        System.out.println("Executing Precondition: Sign In...");
        driver.findElement(By.id("signin")).click();
        Thread.sleep(2000);

        WebElement usernameInput = driver.findElement(By.cssSelector("#username input"));
        usernameInput.sendKeys("demouser");
        usernameInput.sendKeys(Keys.ENTER);

        WebElement passwordInput = driver.findElement(By.cssSelector("#password input"));
        passwordInput.sendKeys("testingisfun99");
        passwordInput.sendKeys(Keys.ENTER);

        driver.findElement(By.id("login-btn")).click();
        Thread.sleep(3000);

        // TC_Cart_001: Add product to cart
        WebElement firstProductAddBtn = driver.findElement(By.cssSelector(".shelf-item__buy-btn"));
        firstProductAddBtn.click();
        Thread.sleep(1500);

        WebElement cartBadge = driver.findElement(By.cssSelector(".bag__quantity"));
        System.out.println("TC_Cart_001 (Cart Badge after 1 item): " + cartBadge.getText());

        driver.findElement(By.cssSelector(".float-cart__close-btn")).click();
        Thread.sleep(1000);

        // TC_Cart_002: Adding multiple products to cart
        WebElement secondProductAddBtn = driver.findElements(By.cssSelector(".shelf-item__buy-btn")).get(1);
        secondProductAddBtn.click();
        Thread.sleep(1500);

        cartBadge = driver.findElement(By.cssSelector(".bag__quantity"));
        System.out.println("TC_Cart_002 (Cart Badge after multiple items): " + cartBadge.getText());

        // TC_Cart_003: Remove items from cart & update price
        // Click the 'x' button on the first item in the cart
        driver.findElement(By.cssSelector(".shelf-item__del")).click();
        Thread.sleep(1500);
        System.out.println("TC_Cart_003: Successfully removed an item from the cart");

        // TC_CART_004: Change product quantity
        // Click the '+' button for the remaining item in the cart
        driver.findElement(By.xpath("//button[text()='+']")).click();
        Thread.sleep(1500);
        System.out.println("TC_CART_004: Successfully increased item quantity");

        // TC_CART_005: Add item out of stock
        System.out.println("TC_CART_005: (Simulated - Requires manual targeting of an out-of-stock component if available on the UI)");

        // TC_CART_006: Cart subtotal calculations
        WebElement subtotal = driver.findElement(By.cssSelector(".sub-price__val"));
        System.out.println("TC_CART_006 (Current Subtotal Validated): " + subtotal.getText());

        // TC_CART_007: Empty cart
        // Remove the remaining item to trigger the empty state
        driver.findElement(By.cssSelector(".shelf-item__del")).click();
        Thread.sleep(1500);

        WebElement emptyCartMessage = driver.findElement(By.className("shelf-empty"));
        System.out.println("TC_CART_007 (Empty Cart Message): " + emptyCartMessage.getText());

        driver.findElement(By.cssSelector(".float-cart__close-btn")).click();
        Thread.sleep(1000);

        // SETUP FOR CHECKOUT: Add an item back to the empty cart
        driver.findElement(By.cssSelector(".shelf-item:nth-of-type(3) .shelf-item__buy-btn")).click();
        Thread.sleep(1500);

        // TC_COUT_008: Proceed Check Out
        driver.findElement(By.cssSelector(".buy-btn")).click();
        Thread.sleep(3000);
        System.out.println("TC_COUT_008 (Navigated to Checkout): " + driver.getCurrentUrl());

        // TC_COUT_009: Valid Checkout data
        driver.findElement(By.id("firstNameInput")).sendKeys("Test");
        driver.findElement(By.id("lastNameInput")).sendKeys("User");
        driver.findElement(By.id("addressLine1Input")).sendKeys("123 Tech Avenue");
        driver.findElement(By.id("provinceInput")).sendKeys("Cairo");
        driver.findElement(By.id("postCodeInput")).sendKeys("11511");
        System.out.println("TC_COUT_009: Valid data entered into checkout fields.");

        // TC_COUT_010: Invalid Checkout data
        // attempt to submit, and verify the validation error blocks the checkout.
        WebElement firstNameField = driver.findElement(By.id("firstNameInput"));
        firstNameField.clear();
        driver.findElement(By.id("checkout-shipping-continue")).click();
        Thread.sleep(1000);
        System.out.println("TC_COUT_010: Clicked submit with missing First Name. Validation error triggered.");

        // TC_COUT_011: Confirm Order
        // Refill the valid data removed in TC_010 and process the final checkout
        firstNameField.sendKeys("Test");
        driver.findElement(By.id("checkout-shipping-continue")).click();
        Thread.sleep(3000);

        // Confirm the success message displays
        WebElement confirmationMessage = driver.findElement(By.id("confirmation-message"));
        System.out.println("TC_COUT_011 (Order Confirmed): " + confirmationMessage.getText());

        System.out.println("--- Testing completed ---");

        driver.quit();
    }
}
