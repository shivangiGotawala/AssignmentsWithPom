package com.automation.framework.utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentReportTestManager {

    static ExtentReports reports = ExtentReportManager.getReporter();
    static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    static ThreadLocal<Integer> testID = new ThreadLocal<>();
    static Map<Integer, ExtentTest> testMap = new HashMap<>();

    public synchronized static ExtentTest getTest() {
        return testMap.get((int) Thread.currentThread().getId());
    }

    public synchronized static ExtentTest startTest(String testName, String description) {
        ExtentTest test = reports.startTest(testName);
        extentTest.set(test);
        testMap.put((int) Thread.currentThread().getId(), extentTest.get());
        return test;
    }

    public synchronized static void stopTest() {
        reports.endTest((ExtentTest) testMap.get((int) Thread.currentThread().getId()));
    }
}
