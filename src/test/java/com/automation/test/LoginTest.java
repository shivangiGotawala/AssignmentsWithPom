package com.automation.test;

import com.automation.framework.core.BaseTest;
import com.automation.framework.core.DataProviderArgument;
import com.automation.framework.core.DataProviderUtils;
import com.automation.framework.utils.JsonUtils;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LandingPage;
import pages.LoginPage;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @DataProviderArgument(value = "loginData=username,password")
    @Test(dataProviderClass = DataProviderUtils.class, dataProvider = "jsonData")
    public void validateLogin(String username, String password) throws IOException {
        LoginPage loginPage = new LoginPage(driver);

        HomePage homePage =
                loginPage.enterUsername("loginpage_tbxUsername", username)
                        .enterPassword("loginpage_tbxPassword", password)
                        .clickOnSignInBtn("loginpage_btnSubmit")
                        .checkIfTitleDisplay("homepage_txtProfilename");
    }

}
