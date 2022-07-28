package demowebshop_tests;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import demowebshop_tests.testbase.TestBase;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;

public class UserRegistrationTests extends TestBase {

    Faker faker = new Faker();

    @Test
    @Description("Проверяется факт регистрации пользователя после заполнения обязательных полей формы регистрации")
    @DisplayName("Регистрация пользователя")
    void checkLinkToRegistrationPage() {
        SelenideElement checkedWebElement = $(".registration-result-page .result");
        String assertionText = "Your registration completed";
        String tokenName = "__RequestVerificationToken";
        String cookieValue = "FY_J7Ayh-kdFygHBCLoWz0YDezdiyUVR7siO6_-yRDVIwPwXVIf7CCbsOysv1vNOxr_3VlP9OYBWcTunXO8lp2N" +
                "-pBAkYw_n2A1ECDlgnOY1;";
        String paramValue =
                "6dv-IKT_u6YjXxMc7kSvETHSAGkomJ7SBooCzfQYDs0xMtSfb8wW11gyUCB84famGYuYi" +
                        "-xO8INeHZavB7yqhm0e2KoZoJIO3Nr9m44kbHc1";
        String newLocationUrl = given()
                .cookie(tokenName, cookieValue)
                .formParam(tokenName, paramValue)
                .formParam("FirstName", "fakeName")
                .formParam("LastName", "fakeLastName")
                .formParam("Email", faker.internet().emailAddress())
                .formParam("Password", "fakepassword")
                .formParam("ConfirmPassword", "fakepassword")
                .formParam("register-button", "Register")
                .when()
                .post("/register")
                .then()
                .assertThat()
                .cookie("NOPCOMMERCE.AUTH")
                .statusCode(302)
                .extract().header("Location");
        open(newLocationUrl);
        checkedWebElement.shouldHave(text(assertionText));
    }

    @Test
    void changeUserFirstName() {
        String newFirstName = faker.name().firstName();

        String cookieName = "NOPCOMMERCE.AUTH";
        String cookieValue = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("Email", "fake@email.ru")
                .formParam("Password", "fakepassword")
                .when()
                .post("/login")
                .then()
                .statusCode(302)
                .cookie(cookieName)
                .extract().cookie(cookieName);

        given()
                .contentType("application/x-www-form-urlencoded")
                .cookie(cookieName, cookieValue)
                .formParam("FirstName", newFirstName)
                .formParam("LastName", "fakepassword")
                .formParam("Email", "fake@email.ru")
                .when()
                .post("/customer/info")
                .then()
                .statusCode(302)
                .log().all()
                .extract().header("Location");
        open("http://demowebshop.tricentis.com/Themes/DefaultClean/Content/images/star-x-inactive.png");
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie(cookieName, cookieValue));
        open("/customer/info");
        sleep(3000);
    }
}
