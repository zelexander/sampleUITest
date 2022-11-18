package shopsite.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class MainPage extends AbstractPage{

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private final By cartBadge = By.xpath("//span[@class='shopping_cart_badge']");
    private final By itemsList = By.xpath("//div[@class='inventory_item']");
    private final By addToCartBtn = By.xpath("//button[contains(text(), 'Add to cart')]");

    private boolean cartBadgeEmpty = driver.findElements(cartBadge).size() == 0;

    private int itemsCounter(){
        return driver.findElements(itemsList).size();
    }

    /**
     * Считаем рандомное значение из емкости листа
     * и кликаем/ кладем в корзину товар под этим номером
     */
    public void itemsClicker(){
        int maxValue = itemsCounter()-2;
        double randomD = (Math.random() * maxValue);
        int randomInt = (int) randomD;
        try {
            driver.findElements(addToCartBtn).get(randomInt).click();
            System.out.println("===== Отправили в корзину товар под номером " + randomInt);
        } catch (Exception e) {
            Assert.fail("===== Не удалось отправить товар в корзину!");
        }
    }

    /**
     * Проверяем что иконка корзины пустая
     */
    public void cartBargeEmptinessCheck(){
       if (cartBadgeEmpty){
           System.out.println("===== Проверили, что счетчик корзин пустой");
       } else {
           Assert.fail("===== Счетчик корзины оказался не пустым!");
       }
    }

    /**
     * Проверяем что иконка корзины пустая
     */
    public void cartBargeCapacityCheck(){
        String actualCapacity = driver.findElement(cartBadge).getText();
        Assert.assertEquals(actualCapacity,"1","Емкость корзины некорректна. ");
        System.out.println("===== Количество товаров в корзине: " + actualCapacity);
    }

    /**
     * Проверяем что иконка корзины пустая
     */
    public void goToCart(){
        driver.findElement(cartBadge).click();
        System.out.println("===== Выполнили переход в корзину");
    }


}
