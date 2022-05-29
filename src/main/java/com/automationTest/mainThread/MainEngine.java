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

    //Scenario as like Excel put on Resources
    //This is just example
    String strFileName = "Datatable/API Testing 2.xlsm";
    String strPathDatatable = "";


    @Override
    public void run() {

        //Before Test
        initAutomationTest();

        testScenario();

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

    private void testScenario() {
        //Create Report Scenario
        extentReportService.createNewTest("Init Datatable", "Anonymous", "Testing API", "Api platform");

        //init Read Data Functions & API Functions
        ReadData readData = new ReadData();
        API apiTest = new API();

        //Lopping Data Excel
        try {
            Sheet sheetDataInfo = readData.readDataTestCase("Datatable", strPathDatatable);
            LogsService.appendLog(Status.INFO.toString(), "Load Datatable at : " + strPathDatatable, extentReportService, "");

            int rowCountDataInfo = sheetDataInfo.getLastRowNum() - sheetDataInfo.getFirstRowNum();
            LogsService.appendLog(Status.INFO.toString(), "Load Data API", extentReportService, "");
            for (int i = 1; i <= rowCountDataInfo ; i++) {

                //We can put URL, Header, Token,dst. For Configuration
                //Get all Parameters
                Map<String, Object> mapInfoTesting = readData.getData(i, sheetDataInfo);

                Sheet sheetParameterBody = readData.readDataTestCase(mapInfoTesting.get("Parameter Body").toString(), strPathDatatable);
                Sheet sheetExpectedBody = readData.readDataTestCase(mapInfoTesting.get("Expected Body").toString(), strPathDatatable);
                Sheet sheetExpectedResponse = readData.readDataTestCase(mapInfoTesting.get("Expected Response").toString(), strPathDatatable);

                Map<String, Object> mapParametersBody = readData.getData(i, sheetParameterBody);
                Map<String, Object> mapExpectedResponse = readData.getData(i, sheetExpectedResponse);
                Map<String, Object> mapExpectedBody = readData.getData(i, sheetExpectedBody);

                //Create test scenario
                extentReportService.createNewTest(mapInfoTesting.get("Test Scenario").toString(), "Anonymous", "Testing API", "Api platform");
                LogsService.appendLog(Status.INFO.toString(), "Start Data-" + i, extentReportService, "");

                // I make this condition just for the test
                if (i==1){
                    apiTest.hitAPIProcessOrder(mapInfoTesting, mapParametersBody, mapExpectedResponse, mapExpectedBody, extentReportService);

                }else{
                    //Choose Method
                    if(((String) mapInfoTesting.get("Method")).equalsIgnoreCase("POST"))
                        apiTest.hitAPIPost(mapInfoTesting, mapParametersBody, mapExpectedResponse, mapExpectedBody, extentReportService);
                    else
                        apiTest.hitAPIGet(mapInfoTesting, mapExpectedResponse, mapExpectedBody, extentReportService);
                }




            }
        }
        catch (IOException e) {
            LogsService.appendLog(Status.FAIL.toString(), "Failed get path ", extentReportService, "");
            e.printStackTrace();
        }
    }


    private void afterTest() {
        LogsService.appendLogOnOutput("Report Folder : " + MyConfig.strPathFolderResultTesting + "\n");
        LogsService.appendLogOnOutput("Finished the automation testing");
        MainAutomationEngine.btnStart.setEnabled(true);
    }


}
