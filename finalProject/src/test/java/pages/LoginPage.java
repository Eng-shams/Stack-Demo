package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage  {

    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver=driver;
    }



    public String getActualMsg(){
       return driver.findElement(By.className("api-error")).getText();
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public String getPass(){
        return driver.findElement(By.id("password")).getText();
    }

    public void  selectPass  (String pass) throws InterruptedException{
        driver.findElement(By.id("password")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[text()='"+pass+"']")).click();

    }



    public void loginByNameAndPass (String name , String pass) throws InterruptedException{

         driver.findElement(By.id("username")).click();
         Thread.sleep(2000);
         driver.findElement(By.xpath("//div[text()='"+name+"']")).click();

        driver.findElement(By.id("password")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[text()='"+pass+"']")).click();

        driver.findElement(By.id("login-btn")).click();
        Thread.sleep(2000);

    }

    public void loginByEmptyInputs () throws InterruptedException{

        driver.findElement(By.id("login-btn")).click();
        Thread.sleep(2000);

    }

    public void loginByEmptyName ( String pass)throws InterruptedException{

        driver.findElement(By.id("password")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[text()='"+pass+"']")).click();

        driver.findElement(By.id("login-btn")).click();
        Thread.sleep(2000);
    }

    public void loginByEmptyPass (String name)throws InterruptedException {
        driver.findElement(By.id("username")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[text()='"+name+"']")).click();

        driver.findElement(By.id("login-btn")).click();
        Thread.sleep(5000);

    }

    public void stepBack (String name , String pass) throws InterruptedException{

        driver.findElement(By.id("username")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[text()='"+name+"']")).click();

        driver.findElement(By.id("password")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[text()='"+pass+"']")).click();

        driver.findElement(By.id("login-btn")).click();
        Thread.sleep(2000);

        driver.navigate().back();

    }

    public void goToRestrictedPages(String page) throws InterruptedException{
        driver.navigate().to("https://bstackdemo.com/");
        driver.findElement(By.id(page)).click();
        Thread.sleep(5000);

    }

    public void addToFav()throws InterruptedException{
        driver.navigate().to("https://bstackdemo.com/");
        driver.findElement(By.className("MuiIconButton-label")).click();
        Thread.sleep(2000);
    }

    public void checkoutBtn()throws InterruptedException{
        driver.navigate().to("https://bstackdemo.com/");
        driver.findElement(By.className("shelf-item__buy-btn")).click();
        Thread.sleep(2000);
        driver.findElement(By.className("buy-btn")).click();
        Thread.sleep(2000);
    }









}
