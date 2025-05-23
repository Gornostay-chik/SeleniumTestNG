package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import pages.WebForm;
import utils.Log;

public class LoginTest extends BaseTest {

   @Test
   public void checkMessage () {

       WebForm webForm = new WebForm(driver);

       webForm.enterText("Selenium");

       webForm.clickButton();

       Log.info("Button click is done");


       Assert.assertEquals(driver.getTitle(),"Web form - target page");



   }

}
