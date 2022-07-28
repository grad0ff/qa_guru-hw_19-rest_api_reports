package demowebshop_tests;

import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import demowebshop_tests.data.EditUserAccountTestData;
import demowebshop_tests.data.RegistrationTestData;
import demowebshop_tests.testbase.TestBase;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static demowebshop_tests.data.DefaultUser.*;
import static io.restassured.RestAssured.given;

public class UserAccountTests extends TestBase {

    Faker faker = new Faker();

    @Test
    @Description("Проверяется регистрация пользователя после заполнения обязательных полей формы регистрации")
    @DisplayName("Регистрация пользователя")
    void registerUser() {
        String newLocationUrl = given()
                .cookie(
                        RegistrationTestData.requestVerificationTokenName,
                        RegistrationTestData.requestVerificationTokenValue
                )
                .formParam(
                        RegistrationTestData.requestVerificationTokenName,
                        RegistrationTestData.requestVerificationTokenFormParamValue
                )
                .formParam("FirstName", firstName)
                .formParam("LastName", lastName)
                .formParam("Email", faker.internet().emailAddress())
                .formParam("Password", password)
                .formParam("ConfirmPassword", password)
                .when()
                .post(RegistrationTestData.endPoint)
                .then()
                .statusCode(302)
                .cookie("NOPCOMMERCE.AUTH")
                .extract().header("Location");

        open(newLocationUrl);
        RegistrationTestData.checkingWebElement.shouldHave(text(RegistrationTestData.assertionText));
    }

    @Test
    @Description("Проверяется возможность изменения имени пользователя в настройках профиля")
    @DisplayName("Редактирование профиля пользователя")
    void changeUserFirstName() {
        String newFirstName = faker.name().firstName();

        String authorizationCookieValue = given()
                .formParam("Email", email)
                .formParam("Password", password)
                .when()
                .post("/login")
                .then()
                .statusCode(302)
                .extract().cookie(EditUserAccountTestData.authorizationCookieName);

        String newLocationUrl = given()
                .cookie(
                        EditUserAccountTestData.authorizationCookieName,
                        authorizationCookieValue
                )
                .cookie(
                        EditUserAccountTestData.requestVerificationTokenName,
                        EditUserAccountTestData.requestVerificationTokenValue
                )
                .formParam(
                        EditUserAccountTestData.requestVerificationTokenName,
                        EditUserAccountTestData.requestVerificationTokenFormParamValue
                )
                .formParam("FirstName", newFirstName)
                .formParam("LastName", password)
                .formParam("Email", email)
                .when()
                .post(EditUserAccountTestData.endPoint)
                .then()
                .statusCode(302)
                .extract().header("Location");

        open("http://demowebshop.tricentis.com/Themes/DefaultClean/Content/images/bullet-right.gif");
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie(
                EditUserAccountTestData.authorizationCookieName,
                authorizationCookieValue
        ));
        open(newLocationUrl);
        EditUserAccountTestData.checkingWebElement.shouldHave(attribute("value", newFirstName));
    }
}
