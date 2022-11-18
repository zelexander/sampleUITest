package tests.base;

import base.BaseMethods;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import shopsite.pages.AbstractPage;
import shopsite.pages.CartPage;
import shopsite.pages.LoginPage;
import shopsite.pages.MainPage;

import static base.Config.KILL_BROWSER;

public class BaseTest {
    protected WebDriver driver = BaseMethods.createDriver();
    protected AbstractPage abstractPage = new AbstractPage(driver);
    protected LoginPage loginPage = new LoginPage(driver);
    protected MainPage mainPage = new MainPage(driver);
    protected CartPage cartPage = new CartPage(driver);

    @AfterTest(alwaysRun = true)
    public void close(){
        if (KILL_BROWSER) {
            driver.quit();
        }
    }
}
