package demowebshop_tests.test_config;

import config.RemoteWebDriverConfig;
import config.TestEnvironmentConfig;
import org.aeonbits.owner.ConfigFactory;

abstract class TestConfigurator {

    static TestEnvironmentConfig environmentConfig;
    static RemoteWebDriverConfig webDriverConfig;

    TestConfigurator() {
        environmentConfig = ConfigFactory.create(TestEnvironmentConfig.class);
        webDriverConfig = ConfigFactory.create(RemoteWebDriverConfig.class);
    }

    abstract void configure();
}
