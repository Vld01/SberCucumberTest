package ru.appline.framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;


public class StartPage extends BasePage {
    @FindBy(xpath = "//a[@aria-label='Ипотека']")
    WebElement mortgageElement;

    @FindBy(xpath = "//a[text()='Ипотека на готовое жильё' and contains(@class, 'link_second')]")
    WebElement mortgageCompleteHouseElement;

    @FindBy(xpath = "//div[(@class = 'kitt-top-menu__pointer kitt-top-menu__pointer_icons ' and contains(@style, 'opacity: 1'))]")
    List<WebElement> mortgageCheckSumMenuElement;


    /**
     * Функция выбора из подменю ипотеки на готовое жильё
     *
     * @return SearchPage - т.е. переходим на страницу {@link ru.appline.framework.pages.MortgagePage}
     */
    @Step("Выбираем подменю с ипотекой")
    public MortgagePage choiceMortgageCompleteHouse(){
        elementToBeClickable(mortgageElement).click();
        assertFalse(mortgageCheckSumMenuElement.isEmpty(), "Подменю не отобразилось");
        elementToBeClickable(mortgageCompleteHouseElement).click();
        return app.getMortgagePage();
    }
}
