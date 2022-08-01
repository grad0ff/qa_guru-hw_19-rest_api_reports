package demowebshop_tests.test_data;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class RegistrationTestData {
    public static final String endPoint;
    public static final String requestVerificationTokenName;
    public static final String requestVerificationTokenValue;
    public static final String requestVerificationTokenFormParamValue;
    public static final SelenideElement checkingWebElement;
    public static final String assertionText;

    static {
        endPoint = "/register";
        requestVerificationTokenName = "__RequestVerificationToken";
        requestVerificationTokenValue = "FY_J7Ayh-kdFygHBCLoWz0YDezdiyUVR7siO6_-yRDVIwPwXVIf7CCbsOysv1vNOxr_3VlP9OYB" +
                "WcTunXO8lp2N-pBAkYw_n2A1ECDlgnOY1;";
        requestVerificationTokenFormParamValue = "6dv-IKT_u6YjXxMc7kSvETHSAGkomJ7SBooCzfQYDs0xMtSfb8wW11gyUCB84famGY" +
                "uYi-xO8INeHZavB7yqhm0e2KoZoJIO3Nr9m44kbHc1";
        checkingWebElement = $(".registration-result-page .result");
        assertionText = "Your registration completed";
    }
}