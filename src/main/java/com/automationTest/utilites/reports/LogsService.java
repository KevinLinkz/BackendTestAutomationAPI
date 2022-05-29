package com.automationTest.utilites.reports;

import com.automationTest.utilites.globaldata.MyConfig;
import com.automationTest.mainGUI.MainAutomationEngine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogsService {

    public static void appendLog(String strStatus, String strTextLog, ExtentReportService extentReportService, String strScreenShot) {
        String strPathLog = createLog();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        appendTxtLog(simpleDateFormat.format(new Date()), strStatus, strTextLog,strPathLog);
        appendLogOnOutput( simpleDateFormat.format(new Date()) + "   " + strStatus + "   " + strTextLog + "\n");
        extentReportService.setStatusTest(strStatus, strTextLog, strScreenShot);

    }

    private static void appendTxtLog(String strTimeStamp, String strStatus, String strTextLog, String strPathLog) {
        try {
            FileWriter fileWriter = new FileWriter(strPathLog, true);
            fileWriter.write(strTimeStamp + "   " + strStatus + "   " + strTextLog + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String createLog() {
        String strPathLog = MyConfig.strPathFolderResultTesting + "\\log.txt";
        try {
            File file = new File(strPathLog);
            if (!file.isFile())
                file.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return strPathLog;

    }

    public static void appendLogOnOutput(String strText){
        MainAutomationEngine.txtOutput.append(strText);
        MainAutomationEngine.txtOutput.setCaretPosition(MainAutomationEngine.txtOutput.getDocument().getLength());
    }
}
