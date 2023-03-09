package com.automation.framework.core;

import com.automation.framework.utils.PropertiesLoader;
import com.automation.framework.utils.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BasePage {

    private Properties ORProperty;
    private static PropertiesLoader propertiesLoader;
    private static WebDriver driver;


    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public static WebDriver launchBrowser(String browserTypeProperty) throws IOException {
        propertiesLoader = new PropertiesLoader();
        String bType = null;
        try {
            propertiesLoader.loadProperties("\\src\\main\\resources\\config.properties");
            bType = propertiesLoader.getPropertyValue(browserTypeProperty);
            if (bType.equalsIgnoreCase("Chrome")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\binaries\\chromedriver.exe");
                driver = new ChromeDriver();
            } else if (bType.equalsIgnoreCase("edge")) {
                System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\binaries\\msedgedriver.exe");
                driver = new EdgeDriver();
            } else if (bType.equalsIgnoreCase("ie")) {
                System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\binaries\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
            } else {
                throw new UnsupportedOperationException("Given browser type is not supported. Please enter the supported browser type");
            }
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            Reporter.testPassed("browser [" + bType + "] has been launch successfully ");
        } catch (Exception e) {
            Reporter.testFailed("Unable to launch the browser [" + bType + "]");
        }
        return driver;
    }

    public By findElement(String locator) {
        By by = null;
        try {
            ORProperty = propertiesLoader.loadProperties("\\src\\main\\resources\\OR.properties");
            String locatorName = propertiesLoader.getPropertyValue(locator).split("#", 2)[0];
            String locatorValue = propertiesLoader.getPropertyValue(locator).split("#", 2)[1];

            if (locatorName.equalsIgnoreCase("id")) {
                by = By.id(locatorValue);
            } else if (locatorName.equalsIgnoreCase("name")) {
                by = By.name(locatorValue);
            } else if (locatorName.equalsIgnoreCase("xpath")) {
                by = By.xpath(locatorValue);
            } else if (locatorName.equalsIgnoreCase("css")) {
                by = By.cssSelector(locatorValue);
            } else if (locatorName.equalsIgnoreCase("lt")) {
                by = By.linkText(locatorValue);
            } else if (locatorName.equalsIgnoreCase("pt")) {
                by = By.partialLinkText(locatorValue);
            } else {
                throw new UnsupportedEncodingException("locator type not supported...");
            }
            Reporter.testPassed("Method findElement by locator property [" + locator + "] found successfully");
        } catch (Exception e) {
            Reporter.testFailed("Unable to find the locator by  [" + locator + "] property");
        }
        return by;
    }

    public WebElement getElement(String locator) {
        By by = null;
        WebElement ele = null;
        try {
            by = findElement(locator);
            ele = new WebDriverWait(driver, Long.parseLong(propertiesLoader.getPropertyValue("waitTime")))
                    .until(ExpectedConditions.presenceOfElementLocated(by));

            ele = new WebDriverWait(driver, Long.parseLong(propertiesLoader.getPropertyValue("waitTime")))
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
            Reporter.testPassed("Method getElement by locator property [" + locator + "] found successfully");
        } catch (Exception e) {
            Reporter.testFailed("Unable to find the element by [" + locator + "] property");
        }
        return ele;
    }

    public Actions getAction() {
        return new Actions(driver);
    }

    public List<WebElement> getElements(String locator) {
        By by = null;
        List<WebElement> eleList = null;
        try {
            by = findElement(locator);
            eleList = new WebDriverWait(driver, Long.parseLong(propertiesLoader.getPropertyValue("waitTime")))
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));

            System.out.println("eleList :" + eleList);

            eleList = new WebDriverWait(driver, Long.parseLong(propertiesLoader.getPropertyValue("waitTime")))
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
            Reporter.testPassed("Method getElements by locator property [" + locator + "] are found successfully");
        } catch (Exception e) {
            Reporter.testFailed("Unable to finds the list of elements by [" + locator + "] property");
        }
        return eleList;
    }

    public WebElement getSingleElementFromList(String locator, String locatorFindFromList) {
        List<WebElement> listOfSideMenus = getElements(locator);
        System.out.println("list :" + listOfSideMenus);
        WebElement element = null;
        for (int i = 0; i < listOfSideMenus.size(); i++) {
            element = listOfSideMenus.get(i).findElement(findElement(locatorFindFromList));
            break;
        }
        return element;
    }

    public void clickOn(String locator) {
        try {
            getElement(locator).click();
            Reporter.testPassed("Method  clickOn by locator [" + locator + "] performed successfully");
        } catch (Exception e) {
            Reporter.testPassed("Unable to click on the [" + locator + "] property");
        }
    }

    public void typeInto(String locator, String contentToType) {
        try {
            getElement(locator).sendKeys(contentToType);
            Reporter.testPassed("Method typeInto by locator [" + locator + "] and text [" + contentToType + "] is performed successfully");
        } catch (Exception e) {
            Reporter.testFailed("Unable to type into [" + locator + "] property ");
        }
    }

    public void selectBy(String locator, String by, String value) {
        Select select = new Select(getElement(locator));
        if (by.equalsIgnoreCase("value")) {
            select.selectByValue(value);
        }
        if (by.equalsIgnoreCase("text")) {
            select.selectByVisibleText(convertNumericalMonthToTextFormat(value));
        }
    }

    public String convertNumericalMonthToTextFormat(String monthByNo) {
        String month = "";
        if (monthByNo.equalsIgnoreCase("01")) {
            month = "January";
        }
        if (monthByNo.equalsIgnoreCase("02")) {
            month = "February";
        }
        if (monthByNo.equalsIgnoreCase("03")) {
            month = "March";
        }
        if (monthByNo.equalsIgnoreCase("04")) {
            month = "April";
        }
        if (monthByNo.equalsIgnoreCase("05")) {
            month = "May";
        }
        if (monthByNo.equalsIgnoreCase("06")) {
            month = "June";
        }
        if (monthByNo.equalsIgnoreCase("07")) {
            month = "July";
        }
        if (monthByNo.equalsIgnoreCase("08")) {
            month = "August";
        }
        if (monthByNo.equalsIgnoreCase("09")) {
            month = "September";
        }
        if (monthByNo.equalsIgnoreCase("10")) {
            month = "October";
        }
        if (monthByNo.equalsIgnoreCase("11")) {
            month = "November";
        }
        if (monthByNo.equalsIgnoreCase("12")) {
            month = "December";
        }
        return month;
    }

    public String getValueFromTestDataPropertyFile(String propertyName) {
        try {
            propertiesLoader.loadProperties("\\src\\main\\resources\\testdata.properties");
            Reporter.testPassed(" Found the testdata by [" + propertyName + "] property from Testdata.properties file");
        } catch (Exception e) {
            Reporter.testPassed("Unable to find the property [" + propertyName + "] from Testdata.properties file");
        }
        return propertiesLoader.getPropertyValue(propertyName);
    }

    public void waitTime(long miliseconds) {
        driver.manage().timeouts().implicitlyWait(miliseconds, TimeUnit.MILLISECONDS);
    }

    public void launchURL(String url) {
        try {
            String propURL = propertiesLoader.getPropertyValue(url);
            driver.get(propURL);
            Reporter.testPassed(" Launched  [" + url + "] successfully");
        } catch (Exception e) {
            Reporter.testPassed(" Unable to launched the  [" + url + "]");
        }
    }

    public static void closeBrowser() {
        try {
            driver.close();
            Reporter.testPassed(" Closed browser successfully");
        } catch (Exception e) {
            Reporter.testPassed(" Unable to closed the browser");
        }
    }

    public static void quitBrowser() {
        try {
            driver.quit();
            Reporter.testPassed(" Quit browser successfully");
        } catch (Exception e) {
            Reporter.testPassed(" Unable to quit the browser");
        }
    }


}
