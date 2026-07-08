package tests;

import baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest {

//TC-01

    @Test(priority = 1)
    public void validLogin_s_1() throws InterruptedException {
        loginPage.loginByNameAndPass("demouser", "testingisfun99");
        Assert.assertTrue(loginPage.getCurrentUrl().contains("signin=true"));

    }

    @Test(priority = 2)
    public void validLogin_s_2() throws InterruptedException {
        loginPage.loginByNameAndPass("image_not_loading_user", "testingisfun99");
        Assert.assertTrue(loginPage.getCurrentUrl().contains("signin=true"));

    }

    @Test(priority = 3)
    public void validLogin_s_3() throws InterruptedException {
        loginPage.loginByNameAndPass("existing_orders_user", "testingisfun99");
        Assert.assertTrue(loginPage.getCurrentUrl().contains("signin=true"));

    }

    @Test(priority = 4)
    public void validLogin_s_4() throws InterruptedException {
        loginPage.loginByNameAndPass("fav_user", "testingisfun99");
        Assert.assertTrue(loginPage.getCurrentUrl().contains("signin=true"));

    }


    @Test(priority = 5)
    public void invalidLogin() throws InterruptedException {
        loginPage.loginByNameAndPass("locked_user", "testingisfun99");
        System.out.println(loginPage.getActualMsg());
        Assert.assertTrue(loginPage.getActualMsg().contains("Your account has been locked."));

    }


//TC-02
    @Test(priority = 6)
    public void emptyLogin_S_1() throws InterruptedException {
        loginPage.loginByEmptyInputs();
        Assert.assertTrue(loginPage.getActualMsg().contains("Invalid Username"));

    }

    @Test(priority = 7)
    public void emptyLogin_S_2() throws InterruptedException {
        loginPage.loginByEmptyName("testingisfun99");
        Assert.assertTrue(loginPage.getActualMsg().contains("Invalid Username"));

    }

    @Test(priority = 8)
    public void emptyLogin_s_3_one() throws InterruptedException {
        loginPage.loginByEmptyPass("demouser");
        Assert.assertTrue(loginPage.getActualMsg().contains("Invalid Password"));

    }

    @Test(priority = 9)
    public void emptyLogin_s_3_two() throws InterruptedException {
        loginPage.loginByEmptyPass("image_not_loading_user");
        Assert.assertTrue(loginPage.getActualMsg().contains("Invalid Password"));

    }

    @Test(priority = 10)
    public void emptyLogin_s_3_three() throws InterruptedException {
        loginPage.loginByEmptyPass("existing_orders_user");
        Assert.assertTrue(loginPage.getActualMsg().contains("Invalid Password"));

    }

    @Test(priority = 11)
    public void emptyLogin_s_3_four() throws InterruptedException {
        loginPage.loginByEmptyPass("fav_user");
        Assert.assertTrue(loginPage.getActualMsg().contains("Invalid Password"));

    }

    //TC-03

    @Test(priority = 12)
    public  void maskedPass_s_1() throws InterruptedException {
        loginPage.selectPass("testingisfun99");
        Assert.assertEquals(loginPage.getPass(),"**************");

    }

//TC-04
//  manual


//    TC-05
    @Test(priority = 12)
    public void stepBack_S_1() throws InterruptedException {
        loginPage.stepBack("demouser", "testingisfun99");
        Assert.assertFalse(loginPage.getCurrentUrl().contains("signin"));

    }

    @Test(priority = 13)
    public void stepBack_S_2() throws InterruptedException {
        loginPage.stepBack("image_not_loading_user", "testingisfun99");
        Assert.assertFalse(loginPage.getCurrentUrl().contains("signin"));

    }

    @Test(priority = 14)
    public void stepBack_S_3() throws InterruptedException {
        loginPage.stepBack("existing_orders_user", "testingisfun99");
        Assert.assertFalse(loginPage.getCurrentUrl().contains("signin"));

    }

    @Test(priority = 14)
    public void stepBack_S_4() throws InterruptedException {
        loginPage.stepBack("fav_user", "testingisfun99");
        Assert.assertFalse(loginPage.getCurrentUrl().contains("signin"));

    }

    //TC-06
    @Test(priority = 15)
    public void restrictedPage_s_1() throws InterruptedException {
        loginPage.goToRestrictedPages("favourites");
        Assert.assertTrue(loginPage.getCurrentUrl().contains("signin"));

    }

    @Test(priority = 16)
    public void restrictedPage_s_2() throws InterruptedException {
        loginPage.goToRestrictedPages("orders");
        Assert.assertTrue(loginPage.getCurrentUrl().contains("signin"));

    }

    @Test(priority = 17)
    public void restrictedPage_s_3() throws InterruptedException {
        loginPage.goToRestrictedPages("offers");
        Assert.assertTrue(loginPage.getCurrentUrl().contains("signin"));

    }

    //TC-07/TC-08
   @Test(priority = 18)
    public void favBtn_s_1() throws InterruptedException {
        loginPage.addToFav();
        Assert.assertTrue(loginPage.getCurrentUrl().contains("signin"));
    }

    @Test(priority = 19)
    public void checkoutBtn_s_1() throws InterruptedException {
        loginPage.checkoutBtn();
        Assert.assertTrue(loginPage.getCurrentUrl().contains("signin"));
    }


}


