package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.ExtentReportManager;
import utils.Log;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setupReport () {

        extent = ExtentReportManager.getExtentReportInstance();
    }

    @AfterSuite
    public void tearDownReport () {

        extent.flush();
    }


    @BeforeMethod
    public void setUp () {
        Log.info("Starting Firefox driver");
        FirefoxOptions options = new FirefoxOptions();
        options.setPlatformName("linux");
        options.addArguments("-headless"); // For run in docker
        driver = new FirefoxDriver(options);
        // Установка неявного ожидания на 10 секунд
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        //driver.manage().window().maximize();

        Log.info("Navigating to page");
      driver.get("https://www.selenium.dev/selenium/web/web-form.html");

    }

    @AfterMethod
    public void tearDown(ITestResult result) {



       // if (result.isSuccess()) {

            String screenShotPath = ExtentReportManager.captureScreenshot(driver, "Success");
            test.pass("All good", MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());
       // }

        if (driver != null){
            driver.quit();
            Log.info("Close Firefox driver");
        }


    }

}
