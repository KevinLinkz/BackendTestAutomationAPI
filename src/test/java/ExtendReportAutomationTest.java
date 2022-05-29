import com.automationTest.utilites.reports.ExtentReportService;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class ExtendReportAutomationTest {

    @Test
    public void isFileExist(){
        ExtentReportService extendReportAutomation = new ExtentReportService("TestFile");
        File file = new File(System.getProperty("user.dir") + "\\Reports\\TestFile.html");

        Assert.assertTrue(file.isFile());

    }


}
