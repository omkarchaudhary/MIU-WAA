package nopcommercetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class WelcomePage {

    protected WebDriver driver;

    public WelcomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "/html/body/div[6]/div[3]/div/div/div/div[2]/div[1]")
    private WebElement result;

    public WebElement getResult() {
        return result;
    }

    public void verifyRegistrationStatus(String statusText) {
        assertThat(getResult().getText(), is(statusText));
    }

    public void close() {
        driver.close();
    }
}
