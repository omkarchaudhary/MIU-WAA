package ReactCalculator;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RCalculatorTest {
    private static RCalculatorPage page;

    @Before
    public void createWebDriver() {
        // set path to chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "D:\\MIU\\Assignments\\WAA\\chromedriver.exe");
        page = PageFactory.initElements(new ChromeDriver(), RCalculatorPage.class);
        page.open("http://localhost:3000");
    }

    @AfterClass
    public static void closeWebDriver() {
        page.close();
    }

    @Test
    public void add() {
        page.getFirstNumber().sendKeys("10");
        Select dropDown = new Select(page.webDriver.findElement(By.name("operator")));
        dropDown.selectByVisibleText("+");
        WebElement selection = dropDown.getFirstSelectedOption();
        selection.click();
        page.getSecondNumber().sendKeys("5");
        page.clickCalculate();
        assertThat(page.getResult(), is("15"));
    }

    @Test
    public void sub() {
        page.getFirstNumber().sendKeys("10");
        Select dropDown = new Select(page.webDriver.findElement(By.name("operator")));
        dropDown.selectByVisibleText("-");
        WebElement selection = dropDown.getFirstSelectedOption();
        selection.click();
        page.getSecondNumber().sendKeys("5");
        page.clickCalculate();
        assertThat(page.getResult(), is("5"));
    }

    @Test
    public void mul() {
        page.getFirstNumber().sendKeys("10");
        Select dropDown = new Select(page.webDriver.findElement(By.name("operator")));
        dropDown.selectByVisibleText("*");
        WebElement selection = dropDown.getFirstSelectedOption();
        selection.click();
        page.getSecondNumber().sendKeys("5");
        page.clickCalculate();
        assertThat(page.getResult(), is("50"));
    }
}
