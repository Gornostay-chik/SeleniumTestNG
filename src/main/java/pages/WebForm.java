package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebForm {

    private WebDriver driver;

    private By textBox = By.name("my-text");
    private By submitButton = By.cssSelector("button");

    public WebForm (WebDriver driver) {
        this.driver = driver;
    }

    public void enterText (String text) {
         driver.findElement(textBox).sendKeys(text);
    }

    public void clickButton() {
         driver.findElement(submitButton).click();
    }

}
