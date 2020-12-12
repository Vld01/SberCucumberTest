package ru.appline.framework.utils;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import static io.qameta.allure.Allure.getLifecycle;

public class MyTestWatcher implements TestWatcher {
    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
         getLifecycle().addAttachment("Скриншот","image/jpeg",null, MyAllureListener.addScreenshot());
    }
}
