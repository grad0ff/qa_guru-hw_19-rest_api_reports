package demowebshop_tests.test_config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

public class ApiTestConfigurator extends TestConfigurator {

    @Override
    public void configure() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        setRequestSpecification();
        setResponseSpecification();
    }


    private void setRequestSpecification() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(environmentConfig.getBaseUrl())
                .build();
    }

    private void setResponseSpecification() {
    }
}
