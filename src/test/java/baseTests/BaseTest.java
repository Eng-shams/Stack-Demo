package baseTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.FavouritesPage;
import pages.HomePage;
import pages.Signin;

public class BaseTest {
    protected WebDriver browser;

    protected HomePage homeobj;
    protected Signin signinobj;
    protected FavouritesPage favouritesobj;

    @BeforeSuite
    public void SetUpSuite(){
        System.out.println("DB Connection");
    }
    @BeforeTest
    public void SetUpTest(){
        System.out.println("Test Started");
    }

    @BeforeClass
    public void SetUp(){
        browser = new EdgeDriver();
        browser.manage().window().maximize();
        homeobj = new HomePage(browser);
    }
    @BeforeMethod
    public void gotoHomePage (){
        browser.get("https://bstackdemo.com/");
        Assert.assertTrue(homeobj.getActualURL().contains(homeobj.getExpectedURL()));

    }

    public void login()throws InterruptedException{
        signinobj= homeobj.clickSignin();
        Thread.sleep(1500);
        Assert.assertTrue(signinobj.getActualURL().contains(signinobj.getExpectedURL()));
        homeobj=signinobj.clickLogin();
        Assert.assertTrue(homeobj.getActualURL().contains(homeobj.getExpectedURL()));
    }
    public void logout(){
        homeobj.clickLogout();
    }
    public void LoopTest(String Category) {
        for (int i = 0; i < homeobj.allProducts().size(); i++) {
            Assert.assertTrue(homeobj.ListNameForAllProducts(i).getText().contains(Category));
        }
    }

    public void LoopTest(String Category1,String Category2) {
        for (int i = 0; i < homeobj.allProducts().size(); i++) {
            Assert.assertTrue(
                    homeobj.ListNameForAllProducts(i).getText().contains(Category1)
                            ||
                            homeobj.ListNameForAllProducts(i).getText().contains(Category2));
        }
    }

    public void AllProductAreDisplay() {
        for (int i = 0; i < homeobj.allProducts().size(); i++) {
            Assert.assertTrue(homeobj.ListNameForAllProducts(i).isDisplayed());
        }
    }
    public void CheakLowerSorting(){
        int lowerPrice = Integer.parseInt(homeobj.ListPriceForAllOfProduct(0).getText());
        for(int i=0; i<homeobj.allPrice().size();i++){
            if(lowerPrice>=Integer.parseInt(homeobj.ListPriceForAllOfProduct(i).getText())){
                lowerPrice = Integer.parseInt(homeobj.ListPriceForAllOfProduct(i).getText());
            }
        }
        Assert.assertEquals(Integer.parseInt(homeobj.ListPriceForAllOfProduct(0).getText()),lowerPrice);
    }
    public void CheakHigherSorting(){
        int highPrice = Integer.parseInt(homeobj.ListPriceForAllOfProduct(0).getText());
        for(int i=0; i<homeobj.allPrice().size();i++){
            if(highPrice<=Integer.parseInt(homeobj.ListPriceForAllOfProduct(i).getText())){
                highPrice = Integer.parseInt(homeobj.ListPriceForAllOfProduct(i).getText());
            }
        }
        Assert.assertEquals(Integer.parseInt(homeobj.ListPriceForAllOfProduct(0).getText()),highPrice);
    }
    @AfterClass
    public void WindowDown(){
        browser.quit();
    }

    @AfterTest
    public void tearDownTest(){
        System.out.println("Test Finished");
    }
    @AfterSuite
    public void tearDownSuite(){
        System.out.println("DB DisConnection");
    }


}
