package qa.automation.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    @Step
    public static LoginPage open() {
        return Selenide.open("/", LoginPage.class);
    }

    @Step
    private SelenideElement loginField() {
        return $(By.name("username"));
    }

    @Step
    private SelenideElement passwordField() {
        return $(By.name("password"));
    }

    @Step
    private SelenideElement loginButton() {
        return $(By.id("login-button"));
    }

    @Step
    private SelenideElement otpField() {
        return $(By.id("otp-code"));
    }

    @Step
    private SelenideElement loginOtpButton() {
        return $(By.id("login-otp-button"));
    }

    @Step
    public SelenideElement restoreAccessButton() {
        return $(By.className("chevron"));
    }

    @Step
    public SelenideElement restoreAccessModalWindow() {
        return $(By.className("modal-content"));
    }

    @Step
    public SelenideElement exceptionWhenWrongCredentials() {
        return $(By.xpath("//div[contains(@class,'alert alert-error')]"));
    }

    @Step
    public SelenideElement resendCode() {
        return $(By.xpath("//span[contains(@class,'resend-text')]"));
    }

    @Step
    public LoginPage enterLoginAndPassword(String name, String password) {
        loginField().setValue(name);
        passwordField().setValue(password);
        loginButton().click();
        return this;
    }

    @Step
    public LoginPage enterOtpAndEnter(String otp) {
        otpField().setValue(otp);
        loginOtpButton().click();
        return this;
    }

    @Step
    public LoginPage changeLanguage() {
        $(By.xpath("//a[@class='chevron locale inline-block']")).click();
        return this;
    }
}
