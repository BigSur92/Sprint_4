import org.example.OrderPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.is;
public class testOrder {
    WebDriver driver;
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

   //локатор кнопки принятия куков
    private static final By COOKIES_ACCEPT_BUTTON = By.id("rcc-confirm-button");

    //Локатор для хедер
    private static final By HEADER = By.className("Header_Header__214zg");
    @Before //Запускае страницу в браузере, и принимаем куки
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(); //включаем хром
        //driver = new FirefoxDriver(); //включаем мозилу
        driver.get(PAGE_URL);
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.presenceOfElementLocated(HEADER));
        driver.findElement(COOKIES_ACCEPT_BUTTON).click();

    }

    @Test //тест который начинается с кнопки "заказать" в Хедаре
    public void positiveTestHeaderOrderButton() {
        OrderPage page = new OrderPage(driver);
        page.clickOrderButton();
        page.enterOrderData("Алексей",
                "Сурков",
                "Зеленоград",
                "89162197784",
                "Сокол");
        page.enterOrderRentDataSevenDaysAndBlack("12.12.2012", "net");
        page.confirmOrderSend();
        Assert.assertTrue(page.isOrderHasBeenPlaced());
    }

    @Test //тест который начинается с кнопки "заказать" в середине страницы
    public void positiveTestMiddleOrderButton() {
        OrderPage page = new OrderPage(driver);
        page.clickMiddleOrderButton();
        page.enterOrderData("Аврам",
                "Линкольн",
                "Москва, Цветной больвар, д. 5 Корп. 4 квартира 45",
                "+13210987654",
                "Цветной бульвар");
        page.enterOrderRentDataOneDayAndGrey("31.12.2032", "Нужна запасная батарея");
        page.confirmOrderSend();
        Assert.assertTrue(page.isOrderHasBeenPlaced());
    }

    @After
    public void cleanUp(){
        driver.quit();
    }
}