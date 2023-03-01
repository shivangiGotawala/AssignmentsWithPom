package com.automation.test;

import com.automation.framework.core.BaseTest;
import com.automation.framework.core.DataProviderArgument;
import com.automation.framework.core.DataProviderUtils;
import com.automation.framework.utils.JsonUtils;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @DataProviderArgument(value = "loginData=username,password")
    @Test(dataProviderClass = DataProviderUtils.class, dataProvider = "jsonData")
    public void validateLogin(String username, String password) throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homepage = loginPage.launchApplication("url")
                .enterUsername("loginpage_tbxUsername", username)
                .enterPassword("loginpage_tbxPassword", password)
                .clickOnSignInBtn("loginpage_btnLogin")
                .checkIfTitleDisplay("homepage_dashboardTitle");
    }

//With Json file without datProvider
//    @Test
//    public void validateNegativeCredential() throws IOException {
//        LoginPage loginPage = new LoginPage(driver);
//        JSONObject jObject = JsonUtils.getObjFromJsonObj(JsonUtils.jsonObject, "loginPage");
//        String invalidUsername = (String) jObject.get("invalidUsername");
//        String invalidPassword = (String) jObject.get("invalidPassword");
//
//        loginPage.launchApplication("url")
//                .enterPassword("loginpage_tbxUsername", invalidUsername)
//                .enterPassword("loginpage_tbxPassword", invalidPassword)
//                .clickOnSignInBtnWithInvalidCred("loginpage_btnLogin", "loginpage_errMsg");
//
//    }

}
