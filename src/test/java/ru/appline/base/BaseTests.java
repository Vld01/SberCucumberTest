package ru.appline.base;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.appline.framework.managers.InitManager;
import ru.appline.framework.managers.PageManager;
import ru.appline.framework.utils.MyTestWatcher;

public class BaseTests {

    /**
     * Менеджер страниц
     * @see PageManager#getPageManager()
     */
    protected PageManager app = PageManager.getPageManager();

    @BeforeEach
    public void beforeEach() {
        InitManager.initFramework();
    }

    @ExtendWith(MyTestWatcher.class)
    @AfterEach
    public void afterEach() {
        InitManager.quitFramework();
    }
}
