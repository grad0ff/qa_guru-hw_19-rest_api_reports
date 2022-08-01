package demowebshop_tests.test_config;

import config.RemoteWebDriverConfig;
import config.TestEnvironmentConfig;
import org.aeonbits.owner.ConfigFactory;

/**
 * Абстрактный класс конфигуратора тестов
 */
abstract class TestConfigurator {

    static TestEnvironmentConfig testEnvironmentConfig; // конфигурация тестового окружения
    static RemoteWebDriverConfig remoteWebDriverConfig; // конфигурация удаленного хоста

    TestConfigurator() {
        testEnvironmentConfig = ConfigFactory.create(TestEnvironmentConfig.class);
        remoteWebDriverConfig = ConfigFactory.create(RemoteWebDriverConfig.class);
    }

    /**
     * Абстрактный метод настройки конкретной конфигурации
     */
    abstract void configure();
}