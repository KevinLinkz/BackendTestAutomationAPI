package com.automationTest.utilites.IO;

import com.automationTest.utilites.globaldata.MyConfig;

import java.io.File;
import java.io.IOException;

public class ScreenshotService {

    //Screenshot Notepad use AHK
    public static String screenShotNotepadAHK() {
        String strScreenshotFullPathName = "";
        String strPathCapture = IOService.getPathFromResource("AutoHotKey/pathNotepad.txt");
        strScreenshotFullPathName = MyConfig.strPathFolderResultCap + "ScreenShot_" +MyConfig.intCounterData + ".png";
        IOService.createTextFile(strPathCapture, strScreenshotFullPathName);
        MyConfig.intCounterData++;

        try {
            Runtime.getRuntime().exec(IOService.getPathFromResource("AutoHotKey/NotepadScreenShot.exe"), null, new File("C:\\"));
            Thread.sleep(2000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return strScreenshotFullPathName;

    }



}
