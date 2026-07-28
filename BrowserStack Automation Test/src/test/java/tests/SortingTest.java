package tests;

import baseTests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SortingTest extends BaseTest {

    @Test(priority = 1)
    public void VlaidLowerSort(){
        homeobj.clickSortLowerPrice();
        CheakLowerSorting();
    }
    @Test(priority = 2)
    public void VlaidHigherSort(){
        homeobj.clickSortHighPrice();
        CheakHigherSorting();
    }
    @Test(priority = 3)
    public void RetainSelectedProductInFavouritPageAfterSortingWithSign()throws InterruptedException {
        login();
        homeobj.SelectFavourit();
        favouritesobj = homeobj.clickFavourites();
        Assert.assertTrue(favouritesobj.getActualURL().contains(favouritesobj.getExpectedURL()));
        Assert.assertTrue(favouritesobj.ElementIsDisplay());
        browser.navigate().back();
        homeobj.clickSortHighPrice();
        favouritesobj = homeobj.clickFavourites();
        Assert.assertTrue(favouritesobj.ElementIsDisplay());
        logout();
    }
    @Test(priority = 4)
    public void VlaidLowerSortWithFilter()throws InterruptedException{
        homeobj.clickSortLowerPrice();
        homeobj.clickSamsungFilter();
        Thread.sleep(1500);
        CheakLowerSorting();
    }
    @Test(priority = 5)
    public void VlaidFilterWithLowerSort()throws InterruptedException{
        homeobj.clickSamsungFilter();
        homeobj.clickSortLowerPrice();
        Thread.sleep(1500);
        CheakLowerSorting();
    }
    @Test(priority = 6)
    public void RetainSortingApplyAfterNavigatToFavouritPageWithSign()throws InterruptedException {
        logout();
        login();
        homeobj.clickSortLowerPrice();
        favouritesobj = homeobj.clickFavourites();
        Thread.sleep(1500);
        Assert.assertTrue(favouritesobj.getActualURL().contains(favouritesobj.getExpectedURL()));
        browser.navigate().back();
        CheakLowerSorting();
        logout();
    }
    @Test(priority = 7)
    public void SortingOptionClaearly(){
        homeobj.OrderOptionAreDisplay();
    }
    @Test(priority = 8)
    public void RetainSortingApplyAfterReloadPageWithSign()throws InterruptedException {
        login();
        homeobj.clickSortLowerPrice();
        browser.navigate().refresh();
        CheakLowerSorting();
        logout();
    }
    @Test(priority = 9)
    public void CheckSortingApplyPerformanceWithSign()throws InterruptedException {
        logout();
        login();
        homeobj.clickSortLowerPrice();
        homeobj.clickSortHighPrice();
        Thread.sleep(1500);
        CheakHigherSorting();
        logout();
    }
}
