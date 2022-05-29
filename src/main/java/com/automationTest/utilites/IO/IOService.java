package com.automationTest.utilites.IO;

import com.automationTest.utilites.globaldata.MyConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IOService {
    public static boolean createTextFile(String strPath, String strBody) {
        try {
            File file = new File(strPath);
            if (!file.exists())
                file.createNewFile();

            FileWriter fileWriter = new FileWriter(strPath, false);
            fileWriter.write(strBody);
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void initFolder() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyHHmm");
        MyConfig.strPathFolderResultTesting = System.getProperty("user.dir") + "\\Results\\" + simpleDateFormat.format(new Date());
        MyConfig.strPathFolderResultCap = MyConfig.strPathFolderResultTesting + "\\CAP\\";

        File file = new File(MyConfig.strPathFolderResultTesting);
        if (!file.exists()) {
            file.mkdirs();
            System.out.println("Directory Result is created!");
        }

        file = new File(MyConfig.strPathFolderResultCap);
        if (!file.exists()) {
            file.mkdirs();
            System.out.println("Directory CAP is created!");
        }
    }

    public static String getPathFromResource(String strFileName) {
        String strTemp = "";
        try {
            strTemp = IOService.class.getClassLoader().getResource(strFileName).getPath();

            strTemp = URLDecoder.decode(strTemp, "utf-8");
            strTemp = new File(strTemp).getPath();

        } catch (UnsupportedEncodingException | NullPointerException e) {
            e.printStackTrace();
        }
        return strTemp;
    }
}
