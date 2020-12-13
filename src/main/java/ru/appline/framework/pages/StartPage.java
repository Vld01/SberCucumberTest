package ru.appline.framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;


public class StartPage extends BasePage {
    @FindBy(xpath = "//button[@class=\"kitt-cookie-warning__close\"]")
    private WebElement cookieButton;

    @FindBy(xpath = "//ul[contains(@class, \"kitt-top-menu__list\")]//label")
    private List<WebElement> menuPanelList;

    @FindBy(xpath = "//li[contains(@class, \"item_opened\")]//a")
    private List<WebElement> subMenuList;


    /**
     * Функция выбора из подменю ипотеки на готовое жильё
     *
     * @return SearchPage - т.е. переходим на страницу {@link ru.appline.framework.pages.MortgagePage}
     */

    public StartPage selectMenu (String nameMenu) {
        findItem(menuPanelList, nameMenu).click();
        return this;
    }

    public MortgagePage selectSubMenu (String nameSubMenu) {
        findItem(subMenuList, nameSubMenu).click();
        return app.getMortgagePage();
    }
}
