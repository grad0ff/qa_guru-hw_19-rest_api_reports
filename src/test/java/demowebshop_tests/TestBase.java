package demowebshop_tests;

import com.codeborne.selenide.Configuration;
import config.Host;
import config.HostConfig;
import config.TestEnvironmentConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class TestBase {

    private static final HostConfig hostConfig = ConfigFactory.create(HostConfig.class);
    private static final TestEnvironmentConfig envConfig = ConfigFactory.create(TestEnvironmentConfig.class);
    private static String host;

    @BeforeAll
    protected static void init() {
        configTestEnvironment();
        switch (System.getProperty("host", "not_found")) {
            case Host.LOCAL.name():
            case Host.REMOTE.name():
                configHost();
        }
    }

    private static void configTestEnvironment() {
        Configuration.baseUrl = envConfig.getBaseUrl();
        Configuration.browser = envConfig.getBrowser();
        Configuration.browserVersion = envConfig.getBrowserVersion();
        Configuration.browserSize = envConfig.getWindowSize();
    }

    private static void configHost() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = String.format("http://%s:%s@%s:4444/wd/hub",
                hostConfig.getLogin(),
                hostConfig.getPassword(),
                hostConfig.getRemoteUri());
    }
}
