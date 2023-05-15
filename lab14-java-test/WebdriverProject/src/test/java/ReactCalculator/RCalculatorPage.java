package ReactCalculator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RCalculatorPage {
    protected WebDriver webDriver;

    public RCalculatorPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @FindBy(name = "num1")
    private WebElement num1;
    @FindBy(name = "num2")
    private WebElement num2;
    @FindBy(name = "operator")
    private WebElement operator;
    @FindBy(name = "calculate")
    private WebElement calculate;

    @FindBy(id = "result")
    private WebElement resultField;

    public void open(String url) {
        webDriver.get(url);
    }

    public void close() {
        webDriver.close();
    }

    public WebElement getFirstNumber() {
        return num1;
    }

    public WebElement getSecondNumber() {
        return num2;
    }

    public WebElement getOperator() {
        return operator;
    }

    public void clickCalculate() {
        calculate.click();
    }

    public String getResult() {
        return resultField.getText();
    }
}
