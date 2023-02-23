package com.automation.test;

import com.automation.framework.core.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.awt.*;
import java.io.IOException;

public class HomeTest extends BaseTest {

    @Test
    public void searchForDirectoryOption() throws IOException, InterruptedException, AWTException {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.launchApplication("url")
                .enterUsername("loginpage_tbxUsername", "loginPage_validUsername")
                .enterPassword("loginpage_tbxPassword", "loginPage_validPassword")
                .clickOnSignInBtn("loginpage_btnLogin")
                .checkIfTitleDisplay("homepage_dashboardTitle");

        //Assignment 4 -> Scenario 1

        homePage.findDirectoryFromSidePanel("homepage_sidePanel", "homepage_sidePanelFindLiForDirectory")
                .checkIfTitleDisplay("hoempage_titleDirectory")
                .jobTitleFromDropDown("directoryPage_jobTitleSelect")
                .locationFromDropDown("directoryPage_locationSelect")
                .employeeFromDropDown("directoryPage_employeeSelect")
                .btnSearchclick("directoryPage_btnSearch")
                .checkIfTitleDisplay("directoryPage_searchRecordForSelectedEmployee");

        //Assignment 4 -> Scenario 2

        homePage.findDirectoryFromSidePanel("homepage_sidePanel", "homepage_sidePanelFindLiForMyInfo")
                .checkIfTitleDisplay("homepage_myInfoTitle")
                .checkIfUsernameIsShowingCorrect("myinfoPage_txtFirstname", "myinfoPage_txtLastname")
                .logout("directoryPage_userOptions", "directoryPage_userLogout")
                .checkIfTitleDisplay("loginpage_btnLogin");
    }
}
