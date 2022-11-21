package tests.shoppingUI;

import org.testng.annotations.Test;
import tests.base.BaseTest;

import static service.Values.Urls.SITE_START_PAGE;

public class СartCheckerTest extends BaseTest {

    @Test
    public void cartCounterCheck(){
        abstractPage.open(SITE_START_PAGE);
        loginPage.enterToSite("standard_user","secret_sauce");
        mainPage.cartBargeEmptinessCheck();
        mainPage.itemsClicker();
        mainPage.cartBargeCapacityCheck();
        mainPage.goToCart();
        cartPage.clearOut();
        mainPage.cartBargeEmptinessCheck();
    }
}
