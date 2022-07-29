package demowebshop_tests.test_data;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class EditUserAccountTestData {
    public static final String authorizationCookieName;
    public static final String requestVerificationTokenName;
    public static final String requestVerificationTokenValue;
    public static final String requestVerificationTokenFormParamValue;
    public static final SelenideElement checkingWebElement;
    public static final String endPoint;

    static {
        endPoint = "/customer/info";
        authorizationCookieName = "NOPCOMMERCE.AUTH";
        requestVerificationTokenName = "__RequestVerificationToken";
        requestVerificationTokenValue = "9Wnd6z58xWE8_-8XnBfK87iugdtBHONq_57BimEkFBG0jGFKL521jp1e_8nt7ckaWYMOxLbY_hR" +
                "980crin40sOw5fEl7xIJDkHO-_D2DLgk1";
        requestVerificationTokenFormParamValue = "tlOdIbeueY0SwJP51AGJjrC4OZZaYLWX3-g8TyjSZuX5Pr8rdt9kO8RrvqQE-Ne48khy6To" +
                "CNTsXSrYHOxtpVU9oLE3WIhW7pIEcF3X6HzpKpazA9mGKGcM0D5J53jK90";

        checkingWebElement = $("#FirstName");
    }
}