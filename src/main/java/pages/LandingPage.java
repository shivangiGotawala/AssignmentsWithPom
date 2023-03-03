package pages;

import com.automation.framework.core.BasePage;
import org.openqa.selenium.WebDriver;

public class LandingPage extends BasePage {
    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public LandingPage launchApplication(String url) {
        launchURL(url);
        return this;
    }

    public RegistrationPage clickOnSignIn(String locator){
        clickOn(locator);
        return new RegistrationPage(driver);
    }
}
