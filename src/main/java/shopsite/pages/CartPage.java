package shopsite.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends AbstractPage{

    public CartPage(WebDriver driver) {
        super(driver);
    }

    private final By cartBadge = By.xpath("//button[contains(text(),'Remove')]");

    /**
     * Проверяем что иконка корзины пустая
     */
    public void clearOut(){
        driver.findElement(cartBadge).click();
        System.out.println("===== Выполнили очистку корзины");
    }
}
