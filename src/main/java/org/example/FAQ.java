package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class FAQ {
    //Локатор на вопросы о важном
    private static final By FAQ_PAGE = By.className("Home_FourPart__1uthg");
    public FAQ(WebDriver driver){
        this.driver = driver;
    }
    private WebDriver driver;
    //Скрол до вопросов/ответов
    public void scrollToQuestions(){
        WebElement element = driver.findElement(FAQ_PAGE);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

}
