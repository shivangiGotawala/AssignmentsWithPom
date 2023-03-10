package com.automation.framework.core;

import com.automation.framework.utils.ExtentReportManager;
import com.automation.framework.utils.ExtentReportTestManager;
import com.relevantcodes.extentreports.ExtentReports;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;

public class BaseTest {

    public WebDriver driver;

    @BeforeSuite
    public void setUp(){
        ExtentReportManager.getReporter();
    }

    @BeforeMethod
    public void loadBrowser(Method methodName) throws IOException {
        ExtentReportTestManager.startTest(methodName.getName(),"");
        this.driver = BasePage.launchBrowser("browserType");
    }

    @AfterMethod
    public void tearDown() {
//        BasePage.closeBrowser();
        ExtentReportTestManager.stopTest();
    }

    @AfterSuite
    public void tearDownConfig() {
//        BasePage.quitBrowser();
        driver = null;
        ExtentReportManager.getReporter().flush();
        ExtentReportManager.getReporter().close();
    }
}
