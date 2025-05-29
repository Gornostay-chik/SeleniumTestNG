package tests;

import base.BaseTest;
import io.opentelemetry.sdk.common.CompletableResultCode;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.WebForm;
import utils.ExtentReportManager;

public class SendTest extends BaseTest {

   @Parameters ({"sendkeys"})
   @Test
   public void checkMessage (String sendkeys) {

       WebForm webForm = new WebForm(driver);

       test = ExtentReportManager.createTest("Send Keys from Param " + sendkeys);

       webForm.enterText(sendkeys);

       webForm.clickButton();

       test.info("Button click is done");


       try {
           Assert.assertEquals(driver.getTitle(), "Web form - target fff page");
       } catch (AssertionError e) {
           test.fail("Assertion failed: " + e.getMessage());
           throw e; // важно перекинуть исключение, чтобы тест завершился с ошибкой
       }

       test.pass("Success!!");



   }

}
