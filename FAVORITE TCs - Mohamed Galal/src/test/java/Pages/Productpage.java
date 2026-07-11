package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class Productpage {
    WebDriver driver;


    public Productpage(WebDriver driver){
        this.driver = driver;
    }

    By heartIcon = By.xpath("//button[@aria-label='delete']");

    By favoriteIcon = By.id("favourites");

    public void addToFavorite(){
        driver.findElement(heartIcon).click();
    }
    public void openFavorites(){
        driver.findElement(favoriteIcon).click();
    }

    public boolean isHeartRed(){
        String color = driver.findElement(heartIcon).getCssValue("color");
        return color.contains("red");
    }

}
