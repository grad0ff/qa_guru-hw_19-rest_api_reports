package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/TestEnvironmentConfig.properties")
public interface TestEnvironmentConfig extends Config {

    @Key("baseUrl")
    String getBaseUrl();

    @Key("browser")
    @DefaultValue("chrome")
    String getBrowser();

    @Key("browserVersion")
    @DefaultValue("103")
    String getBrowserVersion();

    @Key("windowSize")
    @DefaultValue("1366x768")
    String getWindowSize();
}
