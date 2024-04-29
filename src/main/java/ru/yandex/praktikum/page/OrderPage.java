package ru.yandex.praktikum.page;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.*;

import static org.hamcrest.CoreMatchers.containsString;


public class OrderPage {

    private final WebDriver webDriver;

    private final By nameInputLocator = By.xpath("//input[@placeholder='* Имя']");
    private final By lastNameInputLocator = By.xpath("//input[@placeholder='* Фамилия']");
    private final By adressInputLocator = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By subwayInputLocator = By.xpath("//input[@placeholder='* Станция метро']");
    private final By phoneInputLocator = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButtonLocator = By.xpath("//button[text()='Далее']");
    private final String stationMenuItemLocator = "//div[text()='%s']";
    private final By orderDateLocator = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentPeriodLocator = By.xpath("//div[text()='* Срок аренды']");
    private final By rentPeriodMenuLocator = By.xpath("//div[text()='двое суток']");
    private final By createOrderButtonLocator = By.xpath("(//button[text()='Заказать'])[2]");
    private final By createOrderButtonConfirmLocator = By.xpath("//button[text()='Да']");
    private final By popupTextLocator = By.xpath("//div[text()='Заказ оформлен']");
    private String expectedText = ("Заказ оформлен");
    private final By firstPageOrderLocator = By.xpath("//div[text()='Для кого самокат']");
    private String expectedTextFirstPageOrder = ("Для кого самокат");

    public OrderPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void fillCustomerInfo(String name, String lastname, String adress, String subwayTitle, String phone) {
        WebElement nameInput = webDriver.findElement(nameInputLocator);
        nameInput.sendKeys(name);

        WebElement lastNameInput = webDriver.findElement(lastNameInputLocator);
        lastNameInput.sendKeys(lastname);

        WebElement adressInput = webDriver.findElement(adressInputLocator);
        adressInput.sendKeys(adress);

        WebElement phoneInput = webDriver.findElement(phoneInputLocator);
        phoneInput.sendKeys(phone);

        WebElement subwayInput = webDriver.findElement(subwayInputLocator);
        subwayInput.click();

        WebElement prazkayaStationMenu = webDriver
                .findElement(By.xpath(String.format(stationMenuItemLocator, subwayTitle)));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", prazkayaStationMenu);
        prazkayaStationMenu.click();
    }

    public void clickNextButton() { //Переход на следующую страницу на странице заказа самоката.

        WebElement nextButton = webDriver
                .findElement(nextButtonLocator);
        nextButton.click();
    }

    public void fillOrderInfo(String orderDate, String rentalPeriod) {

        WebElement orderDateInput = webDriver.findElement(orderDateLocator);
        orderDateInput.sendKeys(orderDate, Keys.ENTER); // Вводим дату начала аренды самоката и жмав интер.

        WebElement rentPeriodInput = webDriver.findElement(rentPeriodLocator);
        rentPeriodInput.click(); // жмав по выпадающему списку срока аренды

        WebElement rentPeriodMenuInput = webDriver.findElement(rentPeriodMenuLocator);
        rentPeriodMenuInput.click(); // выбираем из выпадающего списка срок аренды самоката.
    }

    public void orderButtonClick() {
        WebElement createOrderButton = webDriver
                .findElement(createOrderButtonLocator);
        createOrderButton.click(); // нажимаем на кнопку создания заказа.
    }

    public void orderButtonConfirmClick() {
        WebElement createOrderConfirmButton = webDriver
                .findElement(createOrderButtonConfirmLocator);
        createOrderConfirmButton.click(); // нажимаем на кнопку подтверждения создания заказа.

    }


    public String popupOrderCheckActualText() { // Получаем текст об успешном создании заказа.
        WebElement actualText = webDriver.findElement(popupTextLocator);
        return actualText.getText();
    }

    public void assertSucessOrderText() { // сравниваем ожидаемый текст с фактическим в модальном окне успешного заказа.
        MatcherAssert.assertThat(popupOrderCheckActualText(), containsString(expectedText));
    }

    public String getHeadingFirstPageOrder() { // Получаем заголовок первой страницы заказа
        WebElement actualText = webDriver.findElement(firstPageOrderLocator);
        return actualText.getText();
    }

    public void assertHeadingFirstPageOrder() {// сравниваем ожидаемый текст с фактическим на первой странице создания заказа.
        MatcherAssert.assertThat(getHeadingFirstPageOrder(), containsString(expectedTextFirstPageOrder));
    }

}





