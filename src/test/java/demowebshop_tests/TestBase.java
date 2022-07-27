package demowebshop_tests;

import com.codeborne.selenide.Configuration;
import config.RemoteWebDriverConfig;
import config.TestEnvironmentConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class TestBase {

    private static final RemoteWebDriverConfig remoteConfig = ConfigFactory.create(RemoteWebDriverConfig.class);
    private static final TestEnvironmentConfig envConfig = ConfigFactory.create(TestEnvironmentConfig.class);

    @BeforeAll
    protected static void init() {
        Configuration.baseUrl = envConfig.getBaseUrl();
        Configuration.browser = envConfig.getBrowser();
        Configuration.browserVersion = envConfig.getBrowserVersion();
        Configuration.browserSize = envConfig.getWindowSize();
        if (System.getProperty("host", "local").equals("remote")) {
            Configuration.browserCapabilities = getCapabilities();
            Configuration.remote = getRemoteUrl();
        }
    }

    private static DesiredCapabilities getCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true));
        return capabilities;
    }

    private static String getRemoteUrl() {
        return String.format("http://%s:%s@%s:4444/wd/hub",
                remoteConfig.getLogin(),
                remoteConfig.getPassword(),
                remoteConfig.getRemoteUri());
    }
}
