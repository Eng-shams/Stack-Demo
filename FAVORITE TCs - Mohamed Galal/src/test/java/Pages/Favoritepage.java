package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Favoritepage {
    WebDriver driver;


    public Favoritepage(WebDriver driver){

        this.driver=driver;

    }
    By products = By.className("shelf-item");

    By removeButton = By.cssSelector(".shelf-item__del");

    By addCart = By.cssSelector(".buy-btn");

    public int getProductsCount(){

        return driver.findElements(products).size();

    }

    public void removeProduct(){

        driver.findElement(removeButton).click();

    }

    public void addToCart(){

        driver.findElement(addCart).click();

    }

}
