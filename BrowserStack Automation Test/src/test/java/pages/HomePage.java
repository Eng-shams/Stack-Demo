package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {
    WebDriver browser;
    String ExpectedURL = "https://bstackdemo.com/";
    String ActualURL;
    String ExpectedText = "Products";
    By ActualTextby = By.xpath("//div[text()=\"Products\"]");

    By signinButton = By.xpath("//span[@class=\"Navbar_link__3Blki logout-link mt-2\"]");
    By lohoutby = By.xpath("//span[@class=\"Navbar_link__3Blki logout-link mt-2\"]");
    By Favouritesby = By.xpath("//strong[text()=\"Favourites\"]");

    By AppleFilterby = By.xpath("//span[text()=\"Apple\"]");
    By SamsungFilterby = By.xpath("//span[text()=\"Samsung\"]");
    By GoogleFilterby = By.xpath("//span[text()=\"Google\"]");
    By OneplusFilterby = By.xpath("//span[text()=\"OnePlus\"]");

    By Sortinglistby = By.xpath("//div/select");
    By DefultSortingby = By.xpath("//option[text()=\"Select\"]");
    By SortLowerPriceby = By.xpath("//option[@value=\"lowestprice\"]");
    By SortHighPriceby = By.xpath("//option[@value=\"highestprice\"]");

    By AllproductsNameby = By.xpath("//p[@class=\"shelf-item__title\"]");
    By AllproductPriceby = By.xpath("//div[@class=\"val\"]/b");
    By NumberOfProdutsInPageby = By.xpath("//div//h3");

    By ProductId1FavouritIconby = By.xpath("//div[@id=\"1\"]//button[@class=\"MuiButtonBase-root MuiIconButton-root Button \"]");

    By AddToCartButtonForFirstProductby = By.xpath("//div[@id=\"1\"]//div[@class=\"shelf-item__buy-btn\"]");
    By CloseCartButtonby = By.xpath("//div[@class=\"float-cart__close-btn\"]");

    WebDriverWait wait;

    public HomePage(WebDriver browser) {
        this.browser = browser;
        wait = new WebDriverWait(browser, Duration.ofSeconds(10));
    }

    public String getExpectedURL() {
        return ExpectedURL;
    }
    public String getActualURL() {
        ActualURL =browser.getCurrentUrl();
        return ActualURL;
    }

    public String getExpectedText() {
        return ExpectedText;
    }
    public String getActualText(){
       return wait.until(ExpectedConditions.visibilityOfElementLocated(ActualTextby)).getText();
        //return browser.findElement(ActualTextby).getText();
    }

    public String getNumberOfProdutsInPage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(NumberOfProdutsInPageby)).getText();
    }

    public WebElement ListNameForAllProducts(int number) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(AllproductsNameby));
        List<WebElement> allelements = browser.findElements(AllproductsNameby);
        return allelements.get(number);
    }

    public void clickAppleFilter(){
    wait.until(ExpectedConditions.visibilityOfElementLocated(AppleFilterby)).click();
    }
    public void clickSamsungFilter(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(SamsungFilterby)).click();
    }
    public void clickGoogleFilter(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(GoogleFilterby)).click();
    }
    public void clickOneplusFilter(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(OneplusFilterby)).click();
    }

    public WebElement OrderNumber(int number) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Sortinglistby));
        List<WebElement> allelements = browser.findElements(Sortinglistby);
        return allelements.get(number);
    }
    public boolean OrderOptionAreDisplay(){
        boolean state = false;
        wait.until(ExpectedConditions.elementToBeClickable(Sortinglistby)).click();
        state = wait.until(ExpectedConditions.visibilityOfElementLocated(DefultSortingby)).isDisplayed();
        if(state){
            state =wait.until(ExpectedConditions.visibilityOfElementLocated(SortLowerPriceby)).isDisplayed();
            if(state){
                state =wait.until(ExpectedConditions.visibilityOfElementLocated(SortHighPriceby)).isDisplayed();
            }else{
                return state;
            }
        }else{
            return state;
        }
        return state;
    }
    public void clickSortLowerPrice(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(SortLowerPriceby)).click();
    }
    public boolean LowerSoertIsSelected(){
        return browser.findElement(SortLowerPriceby).isSelected();
    }
    public void clickSortHighPrice(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(SortHighPriceby)).click();
    }
    public List<WebElement> allPrice() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(AllproductPriceby));
        return browser.findElements(AllproductPriceby);
    }
    public WebElement ListPriceForAllOfProduct(int number) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(AllproductPriceby));
        List<WebElement> allelements = browser.findElements(AllproductPriceby);
        return allelements.get(number);
    }

    public List<WebElement> allProducts() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(AllproductsNameby));
        return browser.findElements(AllproductsNameby);
    }

    public Signin clickSignin(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(signinButton)).click();
        return new Signin(browser);
    }
    public void clickLogout(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(lohoutby)).click();
    }
    public void SelectFavourit(){
        wait.until(ExpectedConditions.elementToBeClickable(ProductId1FavouritIconby)).click();
    }
    public FavouritesPage clickFavourites(){
        wait.until(ExpectedConditions.elementToBeClickable(Favouritesby)).click();
        return new FavouritesPage(browser);
    }
    public void AddProductInCart(){
        wait.until(ExpectedConditions.elementToBeClickable(AddToCartButtonForFirstProductby)).click();
    }
    public void CloseCart(){
        wait.until(ExpectedConditions.elementToBeClickable(CloseCartButtonby)).click();
    }
}
