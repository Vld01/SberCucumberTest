package ru.appline.framework.utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static ru.appline.framework.managers.DriverManager.getDriver;

public class MyAllureListener{


//  Здесь должен быть переопределённые методы или метод который позволит прикреплять скриншоты к шагу аллюра
//  Если не получается переопредилить то скопировать библиотеку AllureJunit4 себе в пакет listener
//  И прописать в конкретном месте в скопированной библиотеке строчку Allure.addAttachment();
//  Или там же прописать Allure.getLifecycle().addAttachment();


    @Attachment(value = "screenshot", type = "image/png")
    public static byte[] addScreenshot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
