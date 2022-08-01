package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/RemoteWebDriverConfig.properties"})
public interface RemoteWebDriverConfig extends Config {

    @Key("login")
    String getLogin();

    @Key("password")
    String getPassword();

    @Key("remoteUrl")
    String getRemoteUrl();
}
