package com.automationTest.mainThread;

import com.automationTest.utilities.IO.IOService;
import com.automationTest.utilities.globaldata.MyConfig;
import com.automationTest.utilities.datatable.ReadData;
import com.automationTest.utilities.reports.ExtentReportService;
import com.automationTest.utilities.reports.LogsService;
import com.automationTest.mainGUI.MainAutomationEngine;

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
