package demowebshop_tests;

import com.codeborne.selenide.WebDriverRunner;
import demowebshop_tests.test_config.ApiTestConfigurator;
import demowebshop_tests.test_config.UiTestConfigurator;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    private static final UiTestConfigurator uiTestConfigurator = new UiTestConfigurator();
    private static final ApiTestConfigurator apiTestConfigurator = new ApiTestConfigurator();

    @BeforeAll
    protected static void init() {
        uiTestConfigurator.configure();
        apiTestConfigurator.configure();
        WebDriverRunner.driver().clearCookies();
        WebDriverRunner.clearBrowserCache();
    }
}
