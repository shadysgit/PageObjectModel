package com.w2a.listeners;


import com.relevantcodes.extentreports.LogStatus;
import com.w2a.base.Page;
import com.w2a.utilities.TestUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.IOException;

public class CustomListeners extends Page implements ITestListener {

    public void onTestStart(ITestResult iTestResult) {

        test = rep.startTest(iTestResult.getName().toLowerCase());

    }



    public void onTestSuccess(ITestResult iTestResult) {

        test.log(LogStatus.PASS,iTestResult.getName().toUpperCase()+" PASS" );
        rep.endTest(test);
        rep.flush();

    }


    public void onTestFailure(ITestResult iTestResult) {

        System.setProperty("org.uncommons.reportng.escape-output","false");

        try {
            TestUtil.captureScreenshot();
        } catch (IOException e) {
            e.printStackTrace();
        }

        test.log(LogStatus.FAIL,iTestResult.getName().toUpperCase()+" Failed with Exception"+iTestResult.getThrowable());
        test.log(LogStatus.FAIL,test.addScreenCapture(TestUtil.screenShotName));

        Reporter.log("Capturing Screeenshot");
        Reporter.log("<a target=\"_blank\" href="+ TestUtil.screenShotName+" >ScreenShot</a>");
        Reporter.log("<br>");
        Reporter.log("<a href="+TestUtil.screenShotName+" target=\"_blank\">" +
                "<img src="+TestUtil.screenShotName+" height=200 width=200></img></a>");


        rep.endTest(test);
        rep.flush();
    }


    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {

    }
}
