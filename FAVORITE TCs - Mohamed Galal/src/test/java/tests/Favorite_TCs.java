package tests;

import BaseTest.basetest;
import Pages.Favoritepage;
import Pages.LoginPage;
import Pages.Productpage;
import data.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Favorite_TCs extends basetest {
    @Test(dataProvider = "allUsers",dataProviderClass = TestData.class)
    public void TC_FAV_001_Add_Product_To_Favorites(String username, String password) throws InterruptedException {

        LoginPage login = new LoginPage(driver);
        login.login(username,password);

        Productpage product = new Productpage(driver);
        Favoritepage fav = new Favoritepage(driver);

        product.addToFavorite();
        Thread.sleep(1000);

        product.openFavorites();
        Thread.sleep(1000);

        Assert.assertTrue(fav.getProductsCount()==1, "Product not added to favorites");

    }





    @Test(dataProvider = "allUsers",dataProviderClass = TestData.class)
    public void TC_FAV_002_Favorite_Icon_Changes_Color(String username, String password) throws InterruptedException{
        LoginPage login = new LoginPage(driver);
        login.login(username,password);

        Productpage product = new Productpage(driver);

        product.addToFavorite();

        Assert.assertTrue(product.isHeartRed(), "Heart icon did not change color");

    }





    @Test
    public void TC_FAV_003_Product_Not_Duplicated(){
        Productpage product = new Productpage(driver);
        Favoritepage fav = new Favoritepage(driver);

        product.addToFavorite();
        product.addToFavorite();
        product.openFavorites();

        Assert.assertEquals(
                fav.getProductsCount(),
                1,
                "Duplicate product exists"
        );

    }

    @Test
    public void TC_FAV_004_Remove_Product_From_Favorites(){
        Productpage product = new Productpage(driver);


        Favoritepage fav = new Favoritepage(driver);

        product.addToFavorite();
        product.openFavorites();
        fav.removeProduct();
        Assert.assertEquals(fav.getProductsCount(), 0, "Product was not removed");


    }





    @Test
    public void TC_FAV_005_Add_Product_From_Favorites_To_Cart(){

        Productpage product = new Productpage(driver);

        Favoritepage fav = new Favoritepage(driver);

        product.addToFavorite();
        product.openFavorites();
        fav.addToCart();

        Assert.assertTrue(driver.getCurrentUrl().contains("cart"), "Cart page not opened");

    }



    @Test
    public void TC_FAV_006_Product_Remains_After_Refresh(){



        Productpage product = new Productpage(driver);

        Favoritepage fav = new Favoritepage(driver);

        product.addToFavorite();
        driver.navigate().refresh();
        product.openFavorites();

        Assert.assertTrue(fav.getProductsCount()>0, "Favorites lost after refresh");


    }





    @Test
    public void TC_FAV_007_Empty_Favorites_Page(){



        Productpage product = new Productpage(driver);


        Favoritepage fav = new Favoritepage(driver);
        product.openFavorites();


        Assert.assertEquals(fav.getProductsCount(), 0, "Favorites page is not empty");

    }


}
