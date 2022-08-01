package demowebshop_tests;

import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import demowebshop_tests.test_data.EditUserAccountTestData;
import demowebshop_tests.test_data.RegistrationTestData;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static demowebshop_tests.test_data.DefaultUser.*;
import static helpers.CustomApiListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

@Tag("account")
@DisplayName("Тесты аккаунта пользователя")
public class UserAccountTests extends TestBase {

    Faker faker = new Faker();

    @Test
    @Description("Проверяется успешная регистрация пользователя после заполнения обязательных полей формы регистрации")
    @DisplayName("Успешная регистрация")
    void registerUser() {
        step("Заполняем и отправляем форму регистрации через API. Получаем URL редиректа...",
                () -> {
                    String newLocationUrl = given()
                            .filter(withCustomTemplates())
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
                            .extract().header("Location");

                    step("...открываем ссылку в браузере",
                            () -> open(newLocationUrl));
                });

        step("Проверяем успешность регистрации",
                () -> RegistrationTestData.checkingWebElement.shouldHave(text(RegistrationTestData.assertionText)));
    }

    @Test
    @Description("Проверяется возможность изменения имени пользователя в настройках профиля")
    @DisplayName("Редактирование профиля")
    void changeUserFirstName() {
        String newFirstName = faker.name().firstName();

        step("Авторизуемся под default пользователем...",
                () -> {
                    String authorizationCookieValue = given()
                            .filter(withCustomTemplates())
                            .formParam("Email", email)
                            .formParam("Password", password)
                            .when()
                            .post("/login")
                            .then()
                            .statusCode(302)
                            .extract().cookie(EditUserAccountTestData.authorizationCookieName);

                    step("...меняем имя пользователя и сохраняем изменения через API. Получаем URL редиректа...",
                            () -> {
                                String newLocationUrl = given()
                                        .filter(withCustomTemplates())
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

                                step("...открываем ссылку в браузере",
                                        () -> open(newLocationUrl));
                            });

                    step("Проверяем успешность изменения имени пользователя",
                            () -> EditUserAccountTestData.checkingWebElement.shouldHave(attribute("value", newFirstName)));
                });
    }
}
