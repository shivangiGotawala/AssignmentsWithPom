package com.automation.framework.utils;

import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    private static ExtentReports extentReports;

    public static synchronized ExtentReports getReporter() {
        String extentReportPath = null;
        try {
            if (extentReports == null) {
                extentReportPath = System.getProperty("user.dir") + "/src/test/resources/extent-reports";
                File file = new File(extentReportPath);
                if (!file.isDirectory()) {
                    file.mkdirs();
                }
                extentReports = new ExtentReports(extentReportPath + "//TestResults-" + new SimpleDateFormat("dd.mm.yy.hh.mm.ss").format(new Date()) + ".html");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return extentReports;
    }
}
