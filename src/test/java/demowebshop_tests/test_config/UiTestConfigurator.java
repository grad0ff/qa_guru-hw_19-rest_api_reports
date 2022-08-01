package demowebshop_tests.test_config;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class UiTestConfigurator extends TestConfigurator {

    @Override
    public void configure() {
        Configuration.baseUrl = environmentConfig.getBaseUrl();
        Configuration.browser = environmentConfig.getBrowser();
        Configuration.browserVersion = environmentConfig.getBrowserVersion();
        Configuration.browserSize = environmentConfig.getWindowSize();
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
                webDriverConfig.getLogin(),
                webDriverConfig.getPassword(),
                webDriverConfig.getRemoteUrl());
    }
}