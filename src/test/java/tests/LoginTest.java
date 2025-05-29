package tests;

import base.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import pages.WebForm;
import utils.ExelUtils;
import utils.ExtentReportManager;
import utils.Log;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @DataProvider(name = "SendKeys")
    public Object[][] getData () throws IOException {

        String filePath = "./data/TestData.xlsx";
        ExelUtils.loadExcel(filePath,"Sheet1");
        int rowCount = ExelUtils.returnCount();
        Object[][] data = new Object[rowCount-1][1];

        for (int i = 1; i < rowCount; i++) {

            data [i-1][0] = ExelUtils.getCellData(i,0);

        }

        ExelUtils.closeExcel();

        return data;
    }

   @Test (dataProvider = "SendKeys")
   public void checkMessage (String sendKeys) {

       WebForm webForm = new WebForm(driver);

       test = ExtentReportManager.createTest("Send Keys " + sendKeys);

       webForm.enterText(sendKeys);

       webForm.clickButton();

       test.info("Button click is done");


       try {
           Assert.assertEquals(driver.getTitle(), "Web form - target page");
       } catch (AssertionError e) {
           test.fail("Assertion failed: " + e.getMessage());
           throw e; // важно перекинуть исключение, чтобы тест завершился с ошибкой
       }


       test.pass("Success!!");



   }

}
