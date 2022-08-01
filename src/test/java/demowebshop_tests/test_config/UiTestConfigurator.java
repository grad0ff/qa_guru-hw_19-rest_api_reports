package demowebshop_tests.test_config;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class UiTestConfigurator extends TestConfigurator {

    @Override
    public void configure() {
        Configuration.baseUrl = testEnvironmentConfig.getBaseUrl();
        Configuration.browser = testEnvironmentConfig.getBrowser();
        Configuration.browserVersion = testEnvironmentConfig.getBrowserVersion();
        Configuration.browserSize = testEnvironmentConfig.getWindowSize();
        if (System.getProperty("host", "local").equals("remote")) {
            Configuration.browserCapabilities = getCapabilities();
            Configuration.remote = getRemoteUrl();
        }
    }

    private DesiredCapabilities getCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true));
        return capabilities;
    }

    private String getRemoteUrl() {
        return String.format("http://%s:%s@%s:4444/wd/hub",
                remoteWebDriverConfig.getLogin(),
                remoteWebDriverConfig.getPassword(),
                remoteWebDriverConfig.getRemoteUrl());
    }
}
