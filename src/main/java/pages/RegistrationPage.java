package pages;

import com.automation.framework.core.BasePage;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {

    WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public RegistrationPage clickOnRegistration(String locator) {
        clickOn(locator);
        return this;
    }

    public RegistrationPage enterEmail(String locator, String text) {
        typeInto(locator, text);
        return this;
    }

    public RegistrationPage enterFirstname(String locator, String text) {
        typeInto(locator, text);
        return this;
    }

    public RegistrationPage enterLastname(String locator, String text) {
        typeInto(locator, text);
        return this;
    }

    public RegistrationPage enterPassword(String locator, String text) {
        typeInto(locator, text);
        return this;
    }


    public RegistrationPage enterDay(String locator, String by, String text) {
        selectBy(locator, by, text);
        return this;
    }

    public RegistrationPage enterMonth(String locator, String by, String text) {
        System.out.println(text);
        selectBy(locator, by, text);
        return this;
    }

    public RegistrationPage enterYear(String locator, String by, String text) {
        selectBy(locator, by, text);
        return this;
    }

    public RegistrationPage clickOnCreateAccount(String locator) {
        clickOn(locator);
        return this;
    }

    public RegistrationPage selectTitle(String locator) {
        clickOn(locator);
        return this;
    }
}
