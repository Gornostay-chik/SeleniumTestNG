package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebForm {

    private WebDriver driver;

    @FindBy(name="my-text")
    WebElement textBox;
    @FindBy(css="button")
    WebElement submitButton;


  //  private By textBox = By.name("my-text");
  //  private By submitButton = By.cssSelector("button");

    public WebForm (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void enterText (String text) {


     //   driver.findElement(textBox).sendKeys(text);

        textBox.sendKeys(text);
    }

    public void clickButton() {
     //    driver.findElement(submitButton).click();
        submitButton.click() ;

    }

}
