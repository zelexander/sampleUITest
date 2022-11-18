package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

import static base.Config.ENVS;
import static service.Values.TimeValues.IMPLICIT_WAIT;

public class BaseMethods {

    public static WebDriver createDriver(){
        WebDriver driver = null;
        switch (ENVS){
            case "mac_chrome":
                System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver");
                driver = new ChromeDriver();
                break;
            default:
                Assert.fail("Не удалось запуститься в заданном конфиге");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        return driver;
    }
}