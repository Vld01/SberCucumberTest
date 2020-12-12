package ru.appline.framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static ru.appline.framework.managers.DriverManager.getDriver;


public class MortgagePage extends BasePage {
    // флаг переключения фрейма
    private boolean frameFlag = false;

    @FindBy(xpath = "//div[(@data-label=\"Стоимость недвижимости\")]/input")
    WebElement priceHouseElement;

    @FindBy(xpath = "//div[(@data-label=\"Первоначальный взнос\")]/input")
    WebElement firstPaymentElement;

    @FindBy(xpath = "//div[(@data-label=\"Срок кредита\")]/input")
    WebElement loanPeriodElement;

    @FindBy(id ="iFrameResizer0")
    WebElement iFrame;

    @FindBy(xpath = "//div[contains(@class, 'kit-col_xs_12')]//h1[contains(@class, 'header')]")
    WebElement mortgageHeaderElement;

    @FindBy(xpath = "//span[text()='Есть зарплатная карта СберБанка']/..//input")
    private WebElement salaryCardCheckBox;

    @FindBy(xpath = "//span[text()='Использовать материнский капитал']/..//input")
    private WebElement matherCapitalCheckBox;

    @FindBy(xpath = "//span[text()= \"Скидка 0,3% при покупке квартиры на ДомКлик\"]/../..//input")
    private WebElement discountHouseCheckBox;

    @FindBy(xpath = "//span[text()= \"Страхование жизни\"]/../..//input")
    private WebElement lifeInsuranceCheckBox;

    @FindBy (xpath = "//span[text()= \"Молодая семья\"]/../..//input")
    private WebElement youngFamilyCheckBox;

    @FindBy (xpath = "//span[text()= \"Электронная регистрация сделки\"]/../..//input")
    private WebElement electronRegCheckBox;

    @FindBy(xpath = "//span[text()= \"Сумма кредита\"]/../span[contains(@data-e2e-id, \"medium-result-credit-sum\")]")
    private WebElement creditAmountElement;

    @FindBy(xpath = "//span[text()= \"Ежемесячный платеж\"]/../span[contains(@data-e2e-id, \"medium-result-monthly-payment\")]")
    private WebElement monthlyPaymentElement;

    @FindBy(xpath = "//span[text()= \"Необходимый доход\"]/../span[contains(@data-e2e-id, \"medium-result-required-income\")]")
    private WebElement requiredIncomeElement;

    @FindBy(xpath = "//span[text()= \"Процентная ставка\"]/../span[contains(@data-e2e-id, \"medium-result-credit-rate\")]")
    private WebElement mortgageRateElement;

    /**
     * Метод переключения между фреймами
     */
    public void switchToFrame() {
        if(!frameFlag) {
            frameFlag = true;
            //scrollToElementJs("iFrameResizer0");
            getDriver().switchTo().frame("iFrameResizer0");
        }
    }

    /**
     * Метод проверки перехода на страницу
     *
     * @return MortgagePage - т.е. остаемся на этой странице
     */
    @Step("Проверяем переход на страницу")
    public MortgagePage checkActualPage() {
        elementToBeVisible(mortgageHeaderElement);
        assertEquals("Ипотека от 7,3%* на готовые квартиры", mortgageHeaderElement.getText(),
                "Заголовок отсутствует/не соответствует требуемому");
        return this;
    }

    /**
     * Метод заполнения полей
     *
     * @param nameField - имя веб элемента, поля ввода
     * @param value     - значение вводимое в поле
     * @return MortgagePage - т.е. остаемся на этой странице
     */
    @Step("Заполняем поле '{nameField}' значение '{value}'")
    public MortgagePage fillField(String nameField, int value) {
        switchToFrame();
        WebElement element = null;
        switch (nameField) {
            case "Стоимость недвижимости":
                fillInputField(priceHouseElement, value);
                element = priceHouseElement;
                break;
            case "Первоначальный взнос":
                fillInputField(firstPaymentElement, value);
                element = firstPaymentElement;
                break;
            case "Срок кредита":
                fillInputField(loanPeriodElement, value);
                element = loanPeriodElement;
                break;
            default:
                fail("Поле с наименованием '" + nameField + "' отсутствует на странице " +
                        "'Ипотека на готовое жилье'");

        }
        assertEquals(value, Integer.parseInt(element.getAttribute("value").replaceAll(" ", "")), "Поле '" + nameField + "' было заполнено некорректно");
        return this;
    }

    /**
     * Метод заполнения чекбоксов
     *
     * @param nameField - имя веб элемента, поля ввода
     * @param value     - значение вводимое в поле "true" или "false"
     * @return MortgagePage - т.е. остаемся на этой странице
     */
    @Step("Заполняем поле '{nameField}' значение '{value}'")
    public MortgagePage fillCheckbox(String nameField, String value) {
        WebElement element = null;
        switch (nameField) {
            case "Есть зарплатная карта СберБанка":
                fillInputCheckbox(salaryCardCheckBox, value);
                element = salaryCardCheckBox;
                break;
            case "Использовать материнский капитал":
                fillInputCheckbox(matherCapitalCheckBox, value);
                element = matherCapitalCheckBox;
                break;
            case "Скидка 0,3% при покупке квартиры на ДомКлик":
                fillInputCheckbox(discountHouseCheckBox, value);
                element = discountHouseCheckBox;
                break;
            case "Страхование жизни":
                fillInputCheckbox(lifeInsuranceCheckBox, value);
                element = lifeInsuranceCheckBox;
                break;
            case "Молодая семья":
                fillInputCheckbox(youngFamilyCheckBox, value);
                element = youngFamilyCheckBox;
                break;
            case "Электронная регистрация сделки":
                fillInputCheckbox(electronRegCheckBox, value);
                element = electronRegCheckBox;
                break;
            default:
                fail("Поле с наименованием '" + nameField + "' отсутствует на странице " +
                        "'Ипотека на готовое жилье'");

        }
        assertEquals(value, element.getAttribute("aria-checked"), "Поле '" + nameField + "' было заполнено некорректно");
        return this;
    }

    /**
     * Метод проверки результирующих значений
     *
     * @param nameElement - имя веб элемента, поля ввода
     * @param value     - значение актуальное
     * @return MortgagePage - т.е. остаемся на этой странице
     */
    @Step("Сравниваем элемент '{nameElement}' значение '{value}'")
    public MortgagePage checkFieldValue(String nameElement, String value) {
        switch (nameElement) {
            case "Сумма кредита":
                checkValueElement(creditAmountElement, value, nameElement);
                break;
            case "Ежемесячный платеж":
                checkValueElement(monthlyPaymentElement, value, nameElement);
                break;
            case "Необходимый доход":
                checkValueElement(requiredIncomeElement, value, nameElement);
                break;
            case "Процентная ставка":
                checkValueElement(mortgageRateElement, value, nameElement);
                break;
            default:
                fail("Поле с наименованием '" + nameElement + "' отсутствует на странице.");
                break;
        }
        return this;
    }
}
