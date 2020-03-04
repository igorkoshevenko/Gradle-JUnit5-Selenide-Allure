package qa.automation;

import Constants.Users;
import Settings.Settings;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.sun.tools.javac.util.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import qa.automation.pages.MainPage;

import static Constants.Constants.*;
import static Utilities.Utilities.generateRandomString;

@Tag("smoke")
public class LoginTest extends BaseTestClass {

    String login = Settings.getInstance().getUser(Users.DEMO_USER).getUserLogin();
    String password = Settings.getInstance().getUser(Users.DEMO_USER).getUserPassword();

    @Test
    @DisplayName("Test EX-0001: User enter valid credentials")
    @Order(1)
    public void testCanLoginWithValidCredentials() {

        loginPage.enterLoginAndPassword(login, password).enterOtpAndEnter(VALID_OTP);

        MainPage mainPage = thenAt(MainPage.class);
        mainPage.pageHeader().shouldBe(Condition.visible);
        mainPage.greeting().shouldHave(Condition.exactText("Hello World!"));
        Assert.check(WebDriverRunner.url().contains("welcome"));
    }

    @Test
    @DisplayName("Test EX-0002: User login with invalid credentials")
    @Order(2)
    public void testLoginWithInvalidCredentials() {

        loginPage.enterLoginAndPassword(login, generateRandomString(4)).
                exceptionWhenWrongCredentials().shouldHave(Condition.text("Invalid login credentials"));

        loginPage.enterLoginAndPassword(generateRandomString(4), password).
                exceptionWhenWrongCredentials().shouldHave(Condition.text("Invalid login credentials"));
    }

    @Test
    @DisplayName("Test EX-0003: User login with valid credentials and enter invalid Otp")
    @Order(3)
    public void testLoginWitInvalidOtp() {

        loginPage.enterLoginAndPassword(login, password).enterOtpAndEnter(generateRandomString(4));
        loginPage.exceptionWhenWrongCredentials().shouldHave(Condition.text("Invalid code"));
        loginPage.resendCode().shouldHave(Condition.text("Resend"));
    }

    @Test
    @DisplayName("Test EX-0004: User restore access")
    @Order(4)
    public void testRestoreAccess() {

        loginPage.restoreAccessButton().click();;
        loginPage.restoreAccessModalWindow().shouldHave(Condition.text(FORGOT_USERNAME_OR_PASSWORD));
    }

//    @ParameterizedTest
//    @ValueSource(strings = { "cali", "bali", "dani" })
}
