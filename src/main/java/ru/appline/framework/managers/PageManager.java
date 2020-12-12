package ru.appline.framework.managers;

import ru.appline.framework.pages.MortgagePage;
import ru.appline.framework.pages.StartPage;

/**
 * Класс для управления страницами
 */
public class PageManager {

    /**
     * Менеджер страниц
     */
    private static PageManager pageManager;

    /**
     * Стартовая страница
     */
    StartPage startPage;

    /**
     * Страница ипотечного кредитования
     */
    MortgagePage mortgagePage;


    /**
     * Конструктор специально сделал приватным (синглтон)
     * @see PageManager#getPageManager()
     */
    private PageManager() {
    }

    /**
     * Ленивая инициализация ManagerPages
     *
     * @return ManagerPages
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    /**
     * Ленивая инициализация {@link ru.appline.framework.pages.StartPage}
     *
     * @return StartPage
     */
    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    /**
     * Ленивая инициализация {@link ru.appline.framework.pages.MortgagePage}
     *
     * @return MortgagePage
     */
    public MortgagePage getMortgagePage() {
        if (mortgagePage == null) {
            mortgagePage = new MortgagePage();
        }
        return mortgagePage;
    }
}
