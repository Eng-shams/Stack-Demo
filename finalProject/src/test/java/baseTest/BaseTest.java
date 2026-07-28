package baseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;

public class BaseTest {



    WebDriver driver;

    protected LoginPage loginPage;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
         loginPage =new LoginPage(driver);
        driver.navigate().to("https://bstackdemo.com/signin");
    }


    @AfterMethod
    public void quitHomePage(){
        driver.quit();
    }




}
