package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ExtentTest test;
    public static String reportPath;

    public static ExtentReports getExtentReportInstance() {

        if(extent==null) {

            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            reportPath = "reports/ExtentReport_"+timestamp+".html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

            reporter.config().setDocumentTitle("Automation Test Report");
            reporter.config().setReportName("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }

        return extent;
    };

    public static ExtentTest createTest(String testName) {

        test = getExtentReportInstance().createTest(testName);

        return test;
    }

    public static String captureScreenshot (WebDriver driver,String screenShotName) {

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String path = "../screenshots/"+screenShotName + ".png";

        try {
            FileUtils.copyFile(src, new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return path;
    }

}
