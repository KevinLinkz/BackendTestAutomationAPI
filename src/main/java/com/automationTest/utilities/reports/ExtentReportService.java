package com.automationTest.utilities.reports;

import com.automationTest.utilities.globaldata.MyConfig;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Locale;

public class ExtentReportService {
    ExtentReports extentReports = null;
    ExtentSparkReporter spark = null;
    ExtentTest extentTest = null;

    public ExtentReportService() {
    }

    //Init ExtentReport
    public ExtentReportService(String strFileNameReport) {
        extentReports = new ExtentReports();
        spark = new ExtentSparkReporter(MyConfig.strPathFolderResultTesting + "\\" + strFileNameReport + ".html");
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle(strFileNameReport);
        spark.config().setReportName(strFileNameReport);


        extentReports.attachReporter(spark);
        extentReports.flush();

    }

    //Create New Test
    public void createNewTest(String strTestName, String strAuthor, String strCategory, String strDevice) {
        extentTest = extentReports.createTest(strTestName)
                .assignAuthor(strAuthor)
                .assignCategory(strCategory)
                .assignDevice(strDevice);
    }


    //Set Status Test
    public void setStatusTest(String strStatusTest, String strLog, String strPathScreenShot) {
        Status stsLog = null;
        switch (strStatusTest.toUpperCase(Locale.ROOT)) {
            case "PASS":
                stsLog = Status.PASS;
                break;
            case "FAIL":
                stsLog = Status.FAIL;
                break;
            case "SKIP":
                stsLog = Status.SKIP;
                break;
            case "INFO":
                stsLog = Status.INFO;
                break;
        }

        if (strPathScreenShot != "") {
            String encodedString = changeImageToBase64(strPathScreenShot);
            extentTest.log(stsLog, strLog, MediaEntityBuilder.createScreenCaptureFromBase64String(encodedString).build());

        } else {
            extentTest.log(stsLog, strLog);
        }
        extentReports.flush();
    }

    //Change To Base64
    private String changeImageToBase64(String strPathScreenShot) {
        String encodedString = "";
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(new File(strPathScreenShot));
            encodedString = Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodedString;
    }




}
