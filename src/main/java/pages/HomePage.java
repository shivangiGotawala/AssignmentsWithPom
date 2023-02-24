package pages;

import com.automation.framework.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class HomePage extends BasePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public HomePage checkIfTitleDisplay(String locator) {
        Assert.assertTrue(getElement(locator).isDisplayed());
        return this;
    }

    public HomePage findDirectoryFromSidePanel(String locator, String locatorFindFromList) {
        getSingleElementFromList(locator, locatorFindFromList).click();
        return this;
    }

    public HomePage btnSearchclick(String locator) {
        clickOn(locator);
        return this;
    }

    public HomePage jobTitleFromDropDown(String locator) {
        WebElement ele = getElement(locator);
        ele.click();
        ele.sendKeys("c");
        ele.sendKeys(Keys.ARROW_DOWN);
        ele.sendKeys(Keys.ARROW_DOWN);
        ele.sendKeys(Keys.ENTER);
        return this;
    }

    public HomePage locationFromDropDown(String locator) {
        WebElement ele = getElement(locator);
        ele.click();
        ele.sendKeys("h");
        ele.sendKeys(Keys.ESCAPE);
        return this;
    }

    public HomePage employeeFromDropDown(String locator, String textToType) throws InterruptedException, AWTException {
        WebElement ele = getElement(locator);
        ele.click();
        ele.sendKeys(textToType);
        ele.sendKeys(Keys.SPACE);
        Thread.sleep(5000);
        ele.sendKeys(Keys.ARROW_DOWN);
        ele.sendKeys(Keys.ESCAPE);
        System.out.println("employee :" + ele.getAttribute("value"));
        Assert.assertTrue(ele.getAttribute("value").equalsIgnoreCase("odis  adalwin"));
        return this;
    }

    public HomePage logout(String locator, String locatorOptionFromList) {
        clickOn("directoryPage_userProfile");
        getSingleElementFromList(locator, locatorOptionFromList).click();
        return this;
    }

    public HomePage checkIfUsernameIsShowingCorrect(String locator, String name) {
        System.out.println("checkIfUsernameIsShowingCorrect : " + name);
        WebElement ele = getElement(locator);
        String value = ele.getAttribute("value");
        System.out.println("value:" + value);
        Assert.assertTrue(value.equalsIgnoreCase(name));
        return this;
    }


}
