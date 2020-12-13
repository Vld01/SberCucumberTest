package ru.appline.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import ru.appline.framework.managers.InitManager;
import ru.appline.framework.utils.MyAllureListener;

import static io.qameta.allure.Allure.getLifecycle;

public class Hooks {

    @Before
    public void beforeEach() {
        InitManager.initFramework();
    }

    @After
    public void afterEach(Scenario scenario) {
        if (scenario.isFailed()) {
            getLifecycle().addAttachment("Скриншот","image/jpeg",null, MyAllureListener.addScreenshot());
        }
        InitManager.quitFramework();
    }
}
