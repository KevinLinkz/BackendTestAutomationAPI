package com.automationTest.mainThread;

import com.automationTest.utilites.API.API;
import com.automationTest.utilites.IO.IOService;
import com.automationTest.utilites.globaldata.MyConfig;
import com.automationTest.utilites.datatable.ReadData;
import com.automationTest.utilites.reports.ExtentReportService;
import com.automationTest.utilites.reports.LogsService;
import com.automationTest.mainGUI.MainAutomationEngine;
import com.aventstack.extentreports.Status;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;
import java.util.Map;

public class MainEngine implements Runnable {
    ExtentReportService extentReportService;
    ReadData readData = new ReadData();
    //Scenario as like Excel put on Resources
    //This is just example
    String strFileName = "Datatable/API Testing 2.xlsm";
    String strPathDatatable = "";


    @Override
    public void run() {

        //Before Test
        initAutomationTest();
        readData.testScenario(extentReportService,strPathDatatable);
        //After Test
        afterTest();


    }

    private void initAutomationTest() {
        //Init Folder Result
        IOService.initFolder();

        //Init extendReport Functions
        extentReportService = new ExtentReportService("APITesting");

        //Load Datatable
        strPathDatatable = IOService.getPathFromResource(strFileName);


    }

    private void afterTest() {
        LogsService.appendLogOnOutput("Report Folder : " + MyConfig.strPathFolderResultTesting + "\n");
        LogsService.appendLogOnOutput("Finished the automation testing");
        MainAutomationEngine.btnStart.setEnabled(true);
    }


}
