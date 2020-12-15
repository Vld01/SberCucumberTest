package ru.appline.framework.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.appline.framework.managers.PageManager;

import static org.junit.Assert.assertEquals;
import static ru.appline.framework.managers.DriverManager.getDriver;

/**
 * Базовый класс всех страничек
 */
public class BasePage {

    /**
     * Менеджер страниц
     *
     * @see PageManager
     */
    protected PageManager app = PageManager.getPageManager();

    /**
     * Объект для имитации реального поведения мыши или клавиатуры
     *
     * @see Actions
     */
    protected Actions action = new Actions(getDriver());

    /**
     * Объект для выполнения любого js кода
     *
     * @see JavascriptExecutor
     */
    protected JavascriptExecutor js = (JavascriptExecutor) getDriver();

    /**
     * Объект явного ожидания
     * При применении будет ожидать задонного состояния 10 секунд с интервалом в 1 секунду
     *
     * @see WebDriverWait
     */
    protected WebDriverWait wait = new WebDriverWait(getDriver(), 10, 1000);

    /**
     * Конструктор позволяющий инициализировать все странички и их эелементы помеченные анотацией {@link FindBy}
     * Подробнее можно просмотреть в класс {@link PageFactory}
     *
     * @see FindBy
     * @see PageFactory
     * @see PageFactory#initElements(WebDriver, Object)
     */
    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }

    /**
     * Функция позволяющая скролить до любого элемента с помощью js
     *
     * @param element - веб-элемент странички
     * @see JavascriptExecutor
     */
    protected void scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Явное ожидание состояния кликабельности элемента
     *
     * @param element - веб-элемент который требует проверки на кликабельность
     * @return WebElement - возвращаем тот же веб элемент что был передан в функцию
     * @see WebDriverWait
     * @see org.openqa.selenium.support.ui.FluentWait
     * @see org.openqa.selenium.support.ui.Wait
     * @see ExpectedConditions
     */
    protected WebElement elementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Явное ожидание состояния видимости элемента
     *
     * @param element - веб-элемент который требует проверки на видимость
     * @return WebElement - возвращаем тот же веб элемент что был передан в функцию
     * @see WebDriverWait
     * @see org.openqa.selenium.support.ui.FluentWait
     * @see org.openqa.selenium.support.ui.Wait
     * @see ExpectedConditions
     */
    public WebElement elementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Общий метод по заполнения полей ввода
     *
     * @param field - веб-елемент поле ввода
     * @param value - значение вводимое в поле
     */
    public void fillInputField(WebElement field, int value) {
        scrollToElementJs(field);
        while (!field.getAttribute("value").isEmpty()) {
            field.sendKeys(Keys.BACK_SPACE);
        }
        field.sendKeys("" + value);
    }

    /**
     * Общий метод явного ожидания
     *
     * @param millis - время ожидания в милисекундах
     */
    public void explicitWait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Общий метод по заполнению чекбоксов
     *
     * @param field - веб-елемент поле ввода
     * @param value - значение вводимое в поле
     */
    public void fillInputCheckbox(WebElement field, String value) {
        if (!field.getAttribute("aria-checked").equals(value)) {
            explicitWait(200);
            field.click();
        }
    }

    /**
     * Общий метод проверки соответствия полей
     *
     * @param element - веб-елемент поле ввода
     * @param value - значение вводимое в поле
     * @param nameElement - имя элемента вводимого в поле
     */
    public void checkValueElement(WebElement element, String value, String nameElement){
        scrollToElementJs(element);
        int expectedValue = Integer.parseInt(value.replaceAll("[^0-9]",""));
        int actualValue = Integer.parseInt(element.getText().replaceAll("[^0-9]",""));

        if (expectedValue != actualValue) {
            explicitWait(1000);
            actualValue = Integer.parseInt(element.getText().replaceAll("[^0-9]",""));
        }
        assertEquals("В поле '" + nameElement + "' значения не соответствует ожидаемому", expectedValue, actualValue);
    }

    /**
     * Общий метод по заполнению полей с датой
     *
     * @param field - веб-елемент поле с датой
     * @param value - значение вводимое в поле с датой
     */
    public void fillDateField(WebElement field, String value) {
        scrollToElementJs(field);
        field.sendKeys(value);
    }
}
