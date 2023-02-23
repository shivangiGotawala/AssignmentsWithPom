package com.automation.framework.utils;

import com.relevantcodes.extentreports.LogStatus;

public class Reporter {

    public static void testPassed(String message) {
        ExtentReportTestManager.getTest().log(LogStatus.PASS, message);
    }

    public static void testFailed(String message) {
        ExtentReportTestManager.getTest().log(LogStatus.FAIL, message);
    }
}
