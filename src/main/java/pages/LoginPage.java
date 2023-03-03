package pages;

import com.automation.framework.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;

public class LoginPage extends BasePage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    public LoginPage enterUsername(String locatorName, String userNamePropertyName) {
        typeInto(locatorName, userNamePropertyName);
        return this;
    }

    public LoginPage enterPassword(String locatorName, String pwdPropertyName) {
        typeInto(locatorName, pwdPropertyName);
        return this;
    }

    public HomePage clickOnSignInBtn(String locatorName) throws IOException {
        clickOn(locatorName);
        return new HomePage(driver);
    }

    public LoginPage clickOnSignInBtnWithInvalidCred(String btnLocator, String errMsgLocator) {
        clickOn(btnLocator);
        Assert.assertTrue(getElement(errMsgLocator).isDisplayed());
        return this;
    }


}
