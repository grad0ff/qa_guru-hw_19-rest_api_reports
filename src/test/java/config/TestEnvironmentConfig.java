package config;

import org.aeonbits.owner.Config;

public interface TestEnvironmentConfig extends Config {

    @DefaultValue("http://demowebshop.tricentis.com/")
    String getBaseUrl();

    @DefaultValue("chrome")
    String getBrowser();

    @DefaultValue("100")
    String getBrowserVersion();

    @DefaultValue("1366x768")
    String getWindowSize();
}
