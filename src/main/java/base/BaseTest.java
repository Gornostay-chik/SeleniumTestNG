package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.Log;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp () {
        Log.info("Starting Firefox driver");
      driver = new FirefoxDriver();
      driver.manage().window().maximize();
        Log.info("Navigating to page");
      driver.get("https://www.selenium.dev/selenium/web/web-form.html");

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null){
            driver.quit();
            Log.info("Close Firefox driver");
        }


    }

}
