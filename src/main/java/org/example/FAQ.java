package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FAQ {
    //Локатор на вопросы о важном
    private static final By FAQ_PAGE = By.className("Home_FourPart__1uthg");
    private final WebDriver driver;

    public FAQ(WebDriver driver) {
        this.driver = driver;
    }

    //Скрол до вопросов/ответов
    public void scrollToQuestions() {
        WebElement element = driver.findElement(FAQ_PAGE);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

}
