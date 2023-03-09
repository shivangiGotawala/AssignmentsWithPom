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
import pages.RegistrationPage;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @DataProviderArgument(value = "loginData=username,password")
    @Test(dataProviderClass = DataProviderUtils.class, dataProvider = "jsonData")
    public void validateLogin(String username, String password) throws IOException {
        LandingPage landingPage = new LandingPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        landingPage.launchApplication("url")
                .clickOnSignIn("landingpage_btnSignin");

        HomePage homePage = loginPage.enterUsername("loginpage_tbxUsername", username)
                .enterPassword("loginpage_tbxPassword", password)
                .clickOnSignInBtn("loginpage_btnSubmit")
                .checkIfTitleDisplay("homepage_txtProfilename")
                .clickTshirtBtn("homepage_txtTshirt")
                .clickOnAddToCart("homepage_prodImg", "homepage_btnAddToCart")
                .checkIfTitleDisplay("homepage_dialogProdAddedToCart")
                .clickOnProceedToCheckoutBtn("homepage_btnProceedToCheckout")
                .getCartTotalPrice("homepage_txtCartTotalPrice")
                .clickOnProceedToCheckoutBtn("homepage_btnProToCheckout")
                .checkIfShippingCostNoMoreThan7("homepage_txtShippingCost")
                .clickOnProceedToCheckoutBtn("homepage_btnCheckout")
                .clickOnProceedToCheckoutBtn("homepage_btnProCheckout")
                .selectTermsAndCondition("homepage_chboxTermsCondition")
                .clickOnProceedToCheckoutBtn("homepage_btnPrCheckout")
                .clickBankWirePaymentOption("homepage_btnBankWire")
                .clickConfirmOrder("homepage_btnConfirmOrder")
                .checkIfOrderConfirmationMsgDisplay("homepage_txtConfirmOrderMsg")
                .fetchBankwireInfo();

    }

}
