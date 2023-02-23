package com.automation.test;

import com.automation.framework.core.BaseTest;
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
        HomePage homepage = loginPage.launchApplication("url")
                .enterUsername("loginpage_tbxUsername", "loginPage_validUsername")
                .enterPassword("loginpage_tbxPassword", "loginPage_validPassword")
                .clickOnSignInBtn("loginpage_btnLogin")
                .checkIfTitleDisplay("homepage_prodTItle");
    }


    @Test
    public void validateNegativeCredential() throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.launchApplication("url")
                .enterPassword("loginpage_tbxUsername", "loginPage_invalidUsername")
                .enterPassword("loginpage_tbxPassword", "loginPage_invalidPassword")
                .clickOnSignInBtnWithInvalidCred("loginpage_btnLogin", "loginpage_errMsg");

    }

}
