package com.automation.test;

import com.automation.framework.core.BaseTest;
import com.automation.framework.utils.JsonUtils;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test
    public void validatePositiveCredential() throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        JSONObject jObject = JsonUtils.getObjFromJsonObj(JsonUtils.jsonObject,"loginPage");
        String validUsername = (String) jObject.get("validUsername");
        String validPassword = (String) jObject.get("validPassword");


        HomePage homepage = loginPage.launchApplication("url")
                .enterUsername("loginpage_tbxUsername", validUsername)
                .enterPassword("loginpage_tbxPassword", validPassword)
                .clickOnSignInBtn("loginpage_btnLogin")
                .checkIfTitleDisplay("homepage_dashboardTitle");
    }


    @Test
    public void validateNegativeCredential() throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        JSONObject jObject = JsonUtils.getObjFromJsonObj(JsonUtils.jsonObject,"loginPage");
        String invalidUsername = (String) jObject.get("invalidUsername");
        String invalidPassword = (String) jObject.get("invalidPassword");

        loginPage.launchApplication("url")
                .enterPassword("loginpage_tbxUsername", invalidUsername)
                .enterPassword("loginpage_tbxPassword", invalidPassword)
                .clickOnSignInBtnWithInvalidCred("loginpage_btnLogin", "loginpage_errMsg");

    }

}
