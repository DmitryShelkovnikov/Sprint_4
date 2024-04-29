package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver webDriver;
    private By createOrderButtonLocator = By.xpath("//div[contains(@class,'Header')]/button[text()='Заказать']");
    private By createOrderAnotherButtonLocator = By.xpath("//div[contains(@class,'Home')]/button[text()='Заказать']");

    private By cookiesButtonLocator = By.id("rcc-confirm-button");
    private final String questionLocator = "accordion__heading-%s";
    private final String answerLocator = "//div[contains(@id, 'accordion__panel')][.='%s']";

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void clickCreateOrder() { // первая кнопка "Заказать"
        WebElement createOrderButton = webDriver.findElement(createOrderButtonLocator);
        createOrderButton.click();
    }

    public void clickCreateAnotherButtonOrder() { // вторая кнопка заказать
        WebElement createOrderButton = webDriver.findElement(createOrderAnotherButtonLocator);
        createOrderButton.click();
    }


    public void closeCookiesWindow() {
        webDriver.findElement(cookiesButtonLocator).click();
    }

    public void expandQuestion(int index) {
        WebElement element = webDriver.findElement(By.id(String.format(questionLocator, index)));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(webDriver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public boolean answerIsDisplayed(String expectedAnswer) {
        WebElement element = webDriver.findElement(By.xpath(String.format(answerLocator, expectedAnswer)));
        return element.isDisplayed();
    }


}

