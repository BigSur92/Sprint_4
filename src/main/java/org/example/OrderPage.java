package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {

    //Кнопка "Заказать" в header
    private static final By HEADER_ORDER_BUTTON = By.cssSelector("button.Button_Button__ra12g");

    //Кнопка "Заказать" в середине страници
    private static final By MIDDLE_ORDER_BUTTON = By.xpath("//*[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //Локатор страници оформления заказа
    private static final By ORDER_FORM = By.className("Order_Form__17u6u");

    //Поле "Имя"
    private static final By NAME = By.xpath(".//input[@placeholder= '* Имя']");

    //Поле "Фамилия"
    private static final By SURNAME = By.xpath(".//input[@placeholder= '* Фамилия']");

    //Поле "Адрес"
    private static final By ADDRESS = By.xpath(".//input[@placeholder= '* Адрес: куда привезти заказ']");

    //Поле станции метро
    private static final By METRO = By.className("select-search__input");

    //кликаем на первую строчку станции из выпадающего списка
    private static final By METRO_STATION = By.xpath("//*[@class='select-search__row'and @data-index='0']");

    //Поле телефон
    private static final By PHONE = By.xpath(".//input[@placeholder= '* Телефон: на него позвонит курьер']");

    //Кнопка "Далее" в форме ввода данных
    private static final By ORDER_NEXT_BUTTON = By.xpath("//*[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // Поле "когда привезти самокат"
    private static final By DATA_RENT_START = By.xpath("//*[@placeholder= '* Когда привезти самокат']");

    //Поле "Срок Аренды"
    private static final By RENTAL_PERIOD = By.className("Dropdown-placeholder");

    //Выпадающий список с количеством суток выбираем 1
    private static final By ONE_DAY_RENT = By.xpath("//*[@class='Dropdown-option' and text()='сутки']");
    //Выпадающий список с количеством суток выбираем 7
    private static final By SEVEN_DAYS_RENT = By.xpath("//*[@class='Dropdown-option' and text()='семеро суток']");

    //Кликаем по чекбоксу серый
    private static final By GREY_COLOR = By.xpath("//*[@class='Checkbox_Input__14A2w' and @id='grey']");
    //Кликаем по чекбоксу черный
    private static final By BLACK_COLOR = By.xpath("//*[@class='Checkbox_Input__14A2w' and @id='black']");

    //Поля "Комментарий для курьера"
    private static final By DELIVERY_COMMENTS = By.xpath("//*[@placeholder= 'Комментарий для курьера']");

    //Кнопка "Заказать" в форме ввода данных
    private static final By BUTTON_SEND_ORDER = By.xpath("//*[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //Кнопка "Да" в форме ввода данных
    private static final By YES_SEND_ORDER = By.xpath("//*[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");

    //Кнопка Посмотреть статус заказа
    private static final By ORDER_PLACED = By.xpath("//*[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Посмотреть статус']");


    public OrderPage(WebDriver driver){
        this.driver = driver;
    }
    private WebDriver driver;


    public void clickOrderButton(){
        driver.findElement(HEADER_ORDER_BUTTON).click();

    }
    public void clickMiddleOrderButton(){
        driver.findElement(MIDDLE_ORDER_BUTTON).click();
    }

    public void enterOrderData(String customerName, String customerSurname, String customerAddress, String customerPhone, String customerMetro){
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.presenceOfElementLocated(ORDER_FORM));
        driver.findElement(NAME).sendKeys(customerName);
        driver.findElement(SURNAME).sendKeys(customerSurname);
        driver.findElement(ADDRESS).sendKeys(customerAddress);
        driver.findElement(METRO).click();
        driver.findElement(METRO).sendKeys(customerMetro);
        driver.findElement(METRO_STATION).click();
        driver.findElement(PHONE).sendKeys(customerPhone);
        driver.findElement(ORDER_NEXT_BUTTON).click();
    }
    public void enterOrderRentDataSevenDaysAndBlack(String dataRentStart, String deliveryComments){

        driver.findElement(DATA_RENT_START).sendKeys(dataRentStart);
        driver.findElement(DATA_RENT_START).sendKeys(Keys.ENTER);
        driver.findElement(RENTAL_PERIOD).click();
        driver.findElement(SEVEN_DAYS_RENT).click();
        driver.findElement(BLACK_COLOR).click();
        driver.findElement(DELIVERY_COMMENTS).sendKeys(deliveryComments);
        driver.findElement(BUTTON_SEND_ORDER).click();
    }

    public void enterOrderRentDataOneDayAndGrey(String dataRentStart, String deliveryComments){

        driver.findElement(DATA_RENT_START).sendKeys(dataRentStart);
        driver.findElement(DATA_RENT_START).sendKeys(Keys.ENTER);
        driver.findElement(RENTAL_PERIOD).click();
        driver.findElement(ONE_DAY_RENT).click();
        driver.findElement(GREY_COLOR).click();
        driver.findElement(DELIVERY_COMMENTS).sendKeys(deliveryComments);
        driver.findElement(BUTTON_SEND_ORDER).click();

    }

    public void confirmOrderSend(){
        driver.findElement(YES_SEND_ORDER).click();

    }
    public boolean isOrderHasBeenPlaced(){
       return driver.findElement(ORDER_PLACED).isDisplayed();
    }

}

