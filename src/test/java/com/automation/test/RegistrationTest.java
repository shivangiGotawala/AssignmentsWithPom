package com.automation.test;

import com.automation.framework.core.BaseTest;
import com.automation.framework.core.DataProviderArgument;
import com.automation.framework.core.DataProviderUtils;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.RegistrationPage;

public class RegistrationTest extends BaseTest {
    @DataProviderArgument(value = "registrationData=email,firstname,lastname,password,dob")
    @Test(dataProviderClass = DataProviderUtils.class, dataProvider = "registrationJson")
    public void createAccount(String email, String firstname, String lastname, String pwd, String dob) {
        String[] birthday = dob.split("-");
        LandingPage landingPage = new LandingPage(driver);

        RegistrationPage registrationPage = landingPage.launchApplication("url")
                .clickOnSignIn("landingpage_btnSignin")
                .enterEmail("registrationPage_tbxEmail", email)
                .clickOnCreateAccount("registrationPage_btnCreateAccount")
                .selectTitle("registrationPage_btnTitle")
                .enterFirstname("registrationPage_tbxFirstname", firstname)
                .enterLastname("registrationPage_tbxLastname", lastname)
                .enterPassword("registrationPage_tbxPassword", pwd)
                .enterDay("registrationPage_selectDobDay", "value", birthday[0])
                .enterMonth("registrationPage_selectDobMonth", "value", birthday[1])
                .enterYear("registrationPage_selectDobYear", "value", birthday[2])
                .clickOnRegistration("registrationPage_btnRegister");

    }

}
