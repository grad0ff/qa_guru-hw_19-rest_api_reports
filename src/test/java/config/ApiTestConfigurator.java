package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

public class ApiTestConfigurator extends TestConfigurator {

    @Override
    public void configure() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        setRequestSpecification();
    }

    private void setRequestSpecification() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(testEnvironmentConfig.getBaseUrl())
                .build();
    }
}
