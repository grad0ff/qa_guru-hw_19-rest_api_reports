package demowebshop_tests;

import com.codeborne.selenide.WebDriverRunner;
import demowebshop_tests.test_config.ApiTestConfigurator;
import demowebshop_tests.test_config.UiTestConfigurator;
import helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

public class TestBase {

    private static final UiTestConfigurator uiTestConfigurator = new UiTestConfigurator();
    private static final ApiTestConfigurator apiTestConfigurator = new ApiTestConfigurator();

    @BeforeAll
    @DisplayName("Подготовка тестов")
    protected static void initTestRun() {
        uiTestConfigurator.configure();
        apiTestConfigurator.configure();
        WebDriverRunner.driver().clearCookies();
        WebDriverRunner.clearBrowserCache();
    }

    @AfterEach
    void addAttaches() {
        Attach.addConsoleLog();
        Attach.addScreenshot();
        Attach.addPageSource();
        Attach.addVideo();

    }
}
