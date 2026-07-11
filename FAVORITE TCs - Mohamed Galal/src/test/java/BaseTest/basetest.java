package BaseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class basetest {protected WebDriver driver;


    @BeforeMethod
    public void setup(){

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://bstackdemo.com/");

    }



    @AfterMethod
    public void tearDown(){

        driver.quit();

    }
}
