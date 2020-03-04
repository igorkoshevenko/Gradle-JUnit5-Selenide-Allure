package qa.automation.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class OverviewPage {

    @Step
    private SelenideElement loginField() {
        return $(By.name("username"));
    }
}
