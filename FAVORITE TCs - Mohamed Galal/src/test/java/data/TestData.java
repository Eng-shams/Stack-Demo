package data;

import org.testng.annotations.DataProvider;

public class TestData {@DataProvider(name="allUsers")

public Object[][] allUsers(){

    return new Object[][]{

            {"demouser","testingisfun99"},
            {"fav_user","testingisfun99"},
            {"image_not_loading_user","testingisfun99"},
            {"existing_orders_user","testingisfun99"},
            {"locked_user","testingisfun99"}
    };
}

}
