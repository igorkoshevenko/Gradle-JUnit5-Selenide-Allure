package qa.automation;

import Constants.Environments;
import Settings.Settings;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import qa.automation.pages.LoginPage;

import static com.codeborne.selenide.Selenide.page;

public class BaseTestClass {

    @Step
    public <T> T thenAt(Class<T> tClass) {
        return page(tClass);
    }

    LoginPage loginPage;

    @BeforeAll
    public static void oneTimeSetUp() {
        Configuration.baseUrl = Settings.getInstance().getEnv(Environments.DEMO).getEnvUrl();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @BeforeEach
    public void setUp() {
        loginPage = LoginPage.open().changeLanguage();
    }

    @AfterEach
    public void tearDown() {
        Selenide.close();
    }
}
