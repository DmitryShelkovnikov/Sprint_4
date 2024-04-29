package ru.yandex.praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.page.MainPage;
import ru.yandex.praktikum.page.OrderPage;
import ru.yandex.praktikum.page.WebDriverFactory;


public class OrderTest {
    private static final String BROWSER = "chrome";
    private WebDriver webDriver;


    @Before
    public void setup() {
        webDriver = WebDriverFactory.getWebDriver(BROWSER);
        webDriver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void createOrder() { // Создаем заказ самоката через первую кнопку.
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickCreateOrder();
        OrderPage orderPage = new OrderPage(webDriver);
        orderPage.fillCustomerInfo("Имя", "Фамилия", "Адрес",
                "Пражская", "12312312312");// Указываем данные заказчика.
        orderPage.clickNextButton();
        orderPage.fillOrderInfo("01.01.2025", "двое суток");// Указываем дату заказа и срок аренды
        orderPage.orderButtonClick(); // Жмав на кнопку создания заказа.
        orderPage.orderButtonConfirmClick(); // Жмав на кнопку подтверждения заказа.
        orderPage.assertSucessOrderText(); // Проверка успешности отображения модального окна/текста в модальном окне.
    }

    @Test
    public void createOrderAnotherButton() { // Проверяем вторую кнопку создания заказа.
        MainPage mainPage = new MainPage(webDriver);
        mainPage.closeCookiesWindow();
        mainPage.clickCreateAnotherButtonOrder(); // Жмав на вторую кнопку заказа.
        OrderPage orderPage = new OrderPage(webDriver);
        orderPage.assertHeadingFirstPageOrder();// Проверяем, что открылась первая страница заказа самоката.

    }


    @After
    public void closeBrowser() {
        webDriver.quit();
    }
}


