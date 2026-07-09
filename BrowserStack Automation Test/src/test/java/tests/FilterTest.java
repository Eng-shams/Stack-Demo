package tests;

import baseTests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class FilterTest extends BaseTest {
    SoftAssert softAssert = new SoftAssert();
    @Test(priority = 1)
    public void ProductRelatedToCattegory() {
    homeobj.clickAppleFilter();
        LoopTest("iPhone");
    }
    @Test(priority = 2)
    public void FilterUpdatesProductPageDynamically() {
        homeobj.clickAppleFilter();
        Assert.assertTrue(homeobj.getActualText().contains(homeobj.getExpectedText()));
    }
    @Test(priority = 3)
    public void ValidCounter(){
        homeobj.clickAppleFilter();
        Assert.assertTrue(homeobj.getNumberOfProdutsInPage().contains(String.valueOf(homeobj.allProducts().size())));
    }
    @Test(priority = 4)
    public void ProductsInPageRelatedToAnyCattegory() {
        homeobj.clickAppleFilter();
        homeobj.clickSamsungFilter();
        LoopTest("iPhone","Galaxy");
        }
    @Test(priority = 5)
    public void AllProductAreDisableInHomePage(){
        AllProductAreDisplay();
    }
    @Test(priority = 6)
    public void AllProductAreDisableInHomePageWithSelectAllCategory(){
        homeobj.clickAppleFilter();
        homeobj.clickGoogleFilter();
        homeobj.clickOneplusFilter();
        homeobj.clickSamsungFilter();
        AllProductAreDisplay();
    }
    @Test(priority = 7)
    public void ClearCategory(){
        homeobj.clickAppleFilter();
        homeobj.clickGoogleFilter();
        homeobj.clickAppleFilter();
        homeobj.clickGoogleFilter();
        AllProductAreDisplay();
    }
    @Test(priority = 8)
    public void HandelDoubleClick(){
        homeobj.clickAppleFilter();
        homeobj.clickAppleFilter();
        AllProductAreDisplay();
    }
    @Test(priority = 9)
    public void RetainSelectedCategoriesAfterPageReload() {
        homeobj.clickAppleFilter();
        browser.navigate().refresh();
        LoopTest("iPhone");
    }
    @Test(priority = 10)
    public void ProductRelatedToCattegoryWithSignin() throws InterruptedException {
        login();
       homeobj.clickAppleFilter();
        LoopTest("iPhone");
        logout();
    }
    @Test(priority = 11)
    public void FilterUpdatesProductPageDynamicallyWithSignin()throws InterruptedException {
        login();
        homeobj.clickAppleFilter();
        Assert.assertTrue(homeobj.getActualText().contains(homeobj.getExpectedText()));
        logout();
    }
    @Test(priority =12)
    public void ValidCounterWithSignin()throws InterruptedException{
        login();
        homeobj.clickAppleFilter();
        Thread.sleep(1500);
        Assert.assertTrue(homeobj.getNumberOfProdutsInPage().contains(String.valueOf(homeobj.allProducts().size())));
        logout();
    }
    @Test(priority =13)
    public void ProductsInPageRelatedToAnyCattegoryWithSignin()throws InterruptedException {
        login();
        homeobj.clickAppleFilter();
        homeobj.clickSamsungFilter();
        LoopTest("iPhone","Galaxy");
        logout();
    }
    @Test(priority = 14)
    public void AllProductAreDisableInHomePageWithSignin()throws InterruptedException{
        login();
        AllProductAreDisplay();
        logout();
    }
    @Test(priority = 15)
    public void AllProductAreDisableInHomePageWithSelectAllCategoryWithSignin()throws InterruptedException{
        login();
        homeobj.clickAppleFilter();
        homeobj.clickGoogleFilter();
        homeobj.clickOneplusFilter();
        homeobj.clickSamsungFilter();
        AllProductAreDisplay();
        logout();
    }
    @Test(priority = 16)
    public void ClearCategoryWithSignin()throws InterruptedException{
        login();
        homeobj.clickAppleFilter();
        homeobj.clickGoogleFilter();
        homeobj.clickAppleFilter();
        homeobj.clickGoogleFilter();
        AllProductAreDisplay();
        logout();
    }
    @Test(priority = 17)
    public void HandelDoubleClickWithSignin()throws InterruptedException{
        login();
        homeobj.clickAppleFilter();
        homeobj.clickAppleFilter();
        AllProductAreDisplay();
        logout();
    }
    @Test(priority = 18)
    public void RetainSelectedCategoriesAfterPageReloadWithSignin()throws InterruptedException {
        login();
        homeobj.clickAppleFilter();
        browser.navigate().refresh();
        LoopTest("iPhone");
        logout();
    }
    @Test(priority = 19)
    public void RetainSelectedCategoriesAfterNavigateToFavouritesPageWithSignin()throws InterruptedException {
        logout();
        login();
        homeobj.clickAppleFilter();
        favouritesobj= homeobj.clickFavourites();
        Thread.sleep(1500);
        Assert.assertTrue(favouritesobj.getActualURL().contains(favouritesobj.getExpectedURL()));
        browser.navigate().back();
        LoopTest("iPhone");
        logout();
    }
    @Test(priority = 20)
    public void RetainSelectedCategoriesAfterAddProductToCartWithSignin()throws InterruptedException {
        login();
        homeobj.clickAppleFilter();
        homeobj.AddProductInCart();
        homeobj.CloseCart();
        LoopTest("iPhone");
        logout();
    }
    @Test(priority = 21)
    public void Applyingcategoryfilterwithsorting()throws InterruptedException {
        login();
        homeobj.clickSortLowerPrice();
        homeobj.clickAppleFilter();
        homeobj.clickSamsungFilter();
        LoopTest("iPhone","Galaxy");
        logout();
    }
    @Test(priority = 22)
    public void RetainSelectedProductInFavouritPageAfterAddSelectedCategoryWithSignin()throws InterruptedException {
        login();
        homeobj.SelectFavourit();
        favouritesobj = homeobj.clickFavourites();
        Assert.assertTrue(favouritesobj.getActualURL().contains(favouritesobj.getExpectedURL()));
        Assert.assertTrue(favouritesobj.ElementIsDisplay());
        browser.navigate().back();
        homeobj.clickSamsungFilter();
        favouritesobj = homeobj.clickFavourites();
        Assert.assertTrue(favouritesobj.ElementIsDisplay());
        logout();
    }
}