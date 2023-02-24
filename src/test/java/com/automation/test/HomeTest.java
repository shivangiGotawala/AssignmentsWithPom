package com.automation.test;

import com.automation.framework.core.BaseTest;
import com.automation.framework.utils.JsonUtils;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.awt.*;
import java.io.IOException;

public class HomeTest extends BaseTest {

    @Test
    public void searchForDirectoryOption() throws IOException, InterruptedException, AWTException {
        LoginPage loginPage = new LoginPage(driver);
        JSONObject jObject = JsonUtils.getObjFromJsonObj(JsonUtils.jsonObject,"loginPage");
        String validUsername = (String) jObject.get("validUsername");
        String validPassword = (String) jObject.get("validPassword");

        JSONObject jObjectDir = JsonUtils.getObjFromJsonObj(JsonUtils.jsonObject,"directoryPage");
        String empName = (String) jObjectDir.get("empName");
        String firstname = (String) jObjectDir.get("firstname");
        String lastname = (String) jObjectDir.get("lastname");

        HomePage homePage = loginPage.launchApplication("url")
                .enterUsername("loginpage_tbxUsername", validUsername)
                .enterPassword("loginpage_tbxPassword", validPassword)
                .clickOnSignInBtn("loginpage_btnLogin")
                .checkIfTitleDisplay("homepage_dashboardTitle");

        //Assignment 4 -> Scenario 1

        homePage.findDirectoryFromSidePanel("homepage_sidePanel", "homepage_sidePanelFindLiForDirectory")
                .checkIfTitleDisplay("hoempage_titleDirectory")
                .jobTitleFromDropDown("directoryPage_jobTitleSelect")
                .locationFromDropDown("directoryPage_locationSelect")
                .employeeFromDropDown("directoryPage_employeeSelect",empName)
                .btnSearchclick("directoryPage_btnSearch")
                .checkIfTitleDisplay("directoryPage_searchRecordForSelectedEmployee");

        //Assignment 4 -> Scenario 2

        homePage.findDirectoryFromSidePanel("homepage_sidePanel", "homepage_sidePanelFindLiForMyInfo")
                .checkIfTitleDisplay("homepage_myInfoTitle")
                .checkIfUsernameIsShowingCorrect("myinfoPage_txtFirstname", firstname)
                .checkIfUsernameIsShowingCorrect("myinfoPage_txtFirstname", lastname)
                .logout("directoryPage_userOptions", "directoryPage_userLogout")
                .checkIfTitleDisplay("loginpage_btnLogin");
    }
}
