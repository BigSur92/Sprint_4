package test;
import org.example.FAQ;
import org.example.OrderPage;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.is;
@RunWith(Parameterized.class)
public class testQuestions {
    WebDriver driver;
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

    private static final By COOKIES_ACCEPT_BUTTON = By.id("rcc-confirm-button");
    //Локатор для хедер
    private static final By HEADER = By.className("Header_Header__214zg");
    @Before //Запускае страницу в браузере, и принимаем куки
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(); //включаем хром
        //driver = new FirefoxDriver(); //включаем мозилу
        driver.get(PAGE_URL);
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.presenceOfElementLocated(HEADER));
        driver.findElement(COOKIES_ACCEPT_BUTTON).click();
    }
    //обьявлем переменные для параметров
    private final int number;
    private final String Answer;

    public testQuestions(int number, String Answer){
        this.number = number;
        this.Answer = Answer;
    }

    @Parameterized.Parameters //параметаризируем данные вопросы/ответы
    public static Object[][] answers() {
        return new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},

        };
    }

    @Test //тест проверки текста ответов на вопросы
    public void checkAllQuestions() {
        FAQ page = new FAQ(driver);
        page.scrollToQuestions();
        driver.findElement(By.id("accordion__heading-" + this.number )).click();
        String Answer_Text =  driver.findElement(By.id("accordion__panel-" + this.number )).getText();
        MatcherAssert.assertThat(Answer_Text, is(Answer));

    }

    @After
    public void cleanUp(){
        driver.quit();
    }
}