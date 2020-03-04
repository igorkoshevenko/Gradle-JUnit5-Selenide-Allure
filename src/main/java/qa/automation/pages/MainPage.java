package qa.automation.pages;

import Constants.NavigationBarMenu;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    private String menuItem = "//a[@id='{0}']";

    @Step
    public SelenideElement pageHeader() {
        return $(By.id("header"));
    }

    @Step
    public SelenideElement greeting() {
        return $(By.id("user-greeting"));
    }

    @Step
    public SelenideElement clientName() {
        return $(By.xpath("//span[@class='filter-option pull-left']"));
    }

    @Step
    public MainPage openMenuFromNavigationVar(NavigationBarMenu menu)
    {
        $(By.id(String.format(menuItem, menu.toString()))).click();
        return this;
    }


}
