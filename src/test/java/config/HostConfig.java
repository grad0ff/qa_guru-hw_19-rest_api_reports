package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/host/$().properties"})
public interface HostConfig extends Config {

    @Key("login")
    String getLogin();

    @Key("password")
    String getPassword();

    @Key("remoteUri")
    String getRemoteUri();
}
