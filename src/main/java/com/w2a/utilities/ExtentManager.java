package com.w2a.utilities;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;

public class ExtentManager {

    private static ExtentReports reports;

    public static ExtentReports getInstance(){

        if (reports == null){

            reports = new ExtentReports(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\extent.html",true, DisplayOrder.OLDEST_FIRST);
            reports.loadConfig(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\extentConfig\\ReportConfig.xml"));

        }
        return reports;
    }
}
