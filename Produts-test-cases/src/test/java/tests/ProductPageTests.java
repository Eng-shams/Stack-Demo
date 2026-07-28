package tests;

import base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pages.*;
import utils.TestData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ProductPageTests extends BaseTest {

    // TC_PROD_01: product images are visible and not broken
    @Test
    @DisplayName("TC_PROD_01 - product images load and are not broken")
    void productImagesAreVisibleAndNotBroken() {
        ProductsPage products = new ProductsPage(driver, wait);

        LoginPage login = new LoginPage(driver, wait);
        login.login(TestData.USER_IMAGE_NOT_LOADING, TestData.PASSWORD);

        for (WebElement card : products.getProductCards()) {
            assertTrue(products.isImageLoaded(card),
                    "Image did not load for product: " + products.getTitle(card));
        }
    }

    // TC_PROD_04: product title is visible and not empty
    @Test
    @DisplayName("TC_PROD_04 - product title is visible and readable")
    void productTitleIsVisible() {
        ProductsPage products = new ProductsPage(driver, wait);

        for (WebElement card : products.getProductCards()) {
            assertFalse(products.getTitle(card).isEmpty(), "Product title was empty");
        }
    }

    // TC_PROD_05 / TC_PROD_06 / TC_PROD_07: cart item matches the listing page
    @Test
    @DisplayName("TC_PROD_05/06/07 - cart item title, image match the listing page")
    void cartItemMatchesListingPage() {
        ProductsPage products = new ProductsPage(driver, wait);
        WebElement listedCard = products.getProductCards().get(0);

        String expectedTitle = products.getTitle(listedCard);
        String expectedImageSrc = products.getImageSrc(listedCard);

        products.addToCart(listedCard);

        CartPage cart = new CartPage(driver, wait);
        WebElement cartItem = cart.getFirstItem();

        assertEquals(expectedTitle, cart.getTitle(cartItem), "Title mismatch between listing and cart");
        assertEquals(expectedImageSrc, cart.getImageSrc(cartItem), "Image mismatch between listing and cart");
    }

    // TC_PROD_08 / TC_PROD_09 / TC_PROD_10: checkout item matches the listing page
    @Test
    @DisplayName("TC_PROD_08/09/10 - checkout item title, image match the listing page")
    void checkoutItemMatchesListingPage() {
        LoginPage login = new LoginPage(driver, wait);
        login.login(TestData.USER_DEMO, TestData.PASSWORD);

        ProductsPage products = new ProductsPage(driver, wait);
        WebElement listedCard = products.getProductCards().get(0);
        String expectedPrice = products.getPriceText(listedCard);


        String expectedTitle = products.getTitle(listedCard);
        String expectedImageSrc = products.getImageSrc(listedCard);

        products.addToCart(listedCard);

        CartPage cart = new CartPage(driver, wait);
        cart.waitUntilOpen();
        cart.clickCheckout();

        CheckoutPage checkout = new CheckoutPage(driver, wait);
        WebElement orderItem = checkout.getFirstOrderItem();

        assertEquals(expectedTitle, checkout.getTitle(orderItem), "Title mismatch between listing and checkout");
        assertTrue(expectedPrice.contains(checkout.getPriceText(orderItem)) , "Price mismatch between listing and checkout");
        assertEquals(expectedImageSrc, checkout.getImageSrc(orderItem), "Image mismatch between listing and checkout");
    }


    // TC_PROD_12: favorited product matches the listing page
    @Test
    @DisplayName("TC_PROD_12 - favorited product details match the listing page")
    void favoritedProductMatchesListingPage() throws InterruptedException {
        LoginPage login = new LoginPage(driver, wait);
        login.login(TestData.USER_DEMO, TestData.PASSWORD);

        ProductsPage products = new ProductsPage(driver, wait);
        WebElement listedCard = products.getProductCards().get(0);


        String expectedTitle = products.getTitle(listedCard);
        String expectedImageSrc = products.getImageSrc(listedCard);


        products.addToFavorites(listedCard);

//        System.out.print("**expected :  " + listedCard.getText());

        FavoritesPage favorites = new FavoritesPage(driver, wait);
        favorites.open();



        WebElement favCard = favorites.getFirstFavorite();

//        System.out.println("**actual : " + favCard.getText());

        assertEquals(expectedTitle, favorites.getTitle(favCard), "Title mismatch between listing and favorites");
        assertEquals(expectedImageSrc, favorites.getImageSrc(favCard), "Image mismatch between listing and favorites");
    }

    // TC_PROD_13: image alt text is present and descriptive (matches the product title, not empty/generic)
    @Test
    @DisplayName("TC_PROD_13 - product images have a descriptive alt attribute")
    void productImagesHaveDescriptiveAltText() {
        ProductsPage products = new ProductsPage(driver, wait);

        for (WebElement card : products.getProductCards()) {
            String alt = products.getImageAlt(card);
            String title = products.getTitle(card);

            assertNotNull(alt, "alt attribute missing for: " + title);
            assertFalse(alt.trim().isEmpty(), "alt attribute empty for: " + title);
            assertFalse(alt.trim().equalsIgnoreCase("img"),
                    "alt attribute is a generic placeholder for: " + title);
        }
    }

    // TC_PROD_19: no duplicate products on the listing page
    @Test
    @DisplayName("TC_PROD_19 - no duplicate products on the listing page")
    void noDuplicateProducts() {
        ProductsPage products = new ProductsPage(driver, wait);
        List<WebElement> cards = products.getProductCards();

        Set<String> seenTitles = new HashSet<>();
        for (WebElement card : cards) {
            String title = products.getTitle(card);
            assertTrue(seenTitles.add(title), "Duplicate product found: " + title);
        }
    }
}
