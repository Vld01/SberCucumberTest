package ru.appline.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import ru.appline.framework.managers.PageManager;


public class Steps {
    /**
     * Менеджер страниц
     * @see PageManager#getPageManager()
     */
    private PageManager app = PageManager.getPageManager();

    @Когда("^Загружена стартовая страница$")
    public void getInitialPage(){
        app.getStartPage();
    }

    @Когда("^Переход в меню Ипотека и В подменю выбираем Ипотека на готовое жильё$")
    public void selectMenu() {app.getStartPage().choiceMortgageCompleteHouse();}

    @Когда("^Заполняем форму поле/значение$")
    public void fillField(DataTable dataTable) {
        dataTable.cells().forEach(
                raw -> {
                    app.getMortgagePage().fillField(raw.get(0), Integer.parseInt(raw.get(1)));
                });
    }

    @Когда("^Устанавливаем переключатели$")
    public void switchButton(DataTable dataTable) {
        dataTable.cells().forEach(
                raw -> {
                    app.getMortgagePage().fillCheckbox(raw.get(0), raw.get(1));
                });
    }

    @Тогда("^Сравниваем значения$")
    public void checkFieldValue(DataTable dataTable) {
        dataTable.cells().forEach(
                raw -> {
                    app.getMortgagePage().checkFieldValue(raw.get(0), raw.get(1));
                });
    }
}
