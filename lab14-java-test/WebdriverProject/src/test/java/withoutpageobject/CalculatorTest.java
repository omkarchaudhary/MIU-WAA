package withoutpageobject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {
	private WebDriver driver;
	private String URL = "http://www.rekenmachine-calculator.nl/";

	@Before
	public void createWebDriver() {
		// set path to chromedriver.exe
		System.setProperty("webdriver.chrome.driver", "D:\\MIU\\Assignments\\WAA\\chromedriver.exe");
		// create chrome instance
		driver = new ChromeDriver();
	}

	@After
	public void closeWebDriver() {
		driver.close();
	}

	@Test
	public void oneAndFour() {
		driver.navigate().to(URL);

		WebElement button = driver.findElement(By.name("one"));
		button.click();
		button = driver.findElement(By.name("add"));
		button.click();
		button = driver.findElement(By.name("four"));
		button.click();
		assertThat(driver.findElement(By.name("txt")).getAttribute("value"), is("1+4"));
		button = driver.findElement(By.name("equal"));
		button.click();
		assertThat(driver.findElement(By.name("txt")).getAttribute("value"), is("5"));
	}

	@Test
	public void addTwoAndFour(){
		driver.navigate().to(URL);
		WebElement button = driver.findElement(By.name("two"));
		button.click();
		button = driver.findElement(By.name("add"));
		button.click();
		button = driver.findElement(By.name("four"));
		button.click();
		assertThat(driver.findElement(By.name("txt")).getAttribute("value"),is("2+4"));
		button = driver.findElement(By.name("equal"));
		button.click();
		assertThat(driver.findElement(By.name("txt")).getAttribute("value"), is("6"));

	}
}
