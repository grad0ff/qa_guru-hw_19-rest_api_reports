package demowebshop_tests.testbase;

import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    private static final UiTestConfigurator uiTestConfigurator = new UiTestConfigurator();
    private static final ApiTestConfigurator apiTestConfigurator = new ApiTestConfigurator();

    @BeforeAll
    protected static void init() {
        uiTestConfigurator.configure();
        apiTestConfigurator.configure();
    }


}
