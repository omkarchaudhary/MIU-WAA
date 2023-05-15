package nopcommercetest;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class RegistrationTest {

    private static RegistrationPage registrationPage;
    private static WelcomePage welcomePage;

    @Before
    public void createWebDriver() {
        System.setProperty("webdriver.chrome.driver", "D:\\MIU\\Assignments\\WAA\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        registrationPage = new RegistrationPage(driver);
        registrationPage.open("https://demo.nopcommerce.com/register");
    }

    @AfterClass
    public static void closeTheBrowser() {
        registrationPage.close();
    }

    @Test
    public void registerUserTest() {
        registrationPage.selectGender(RegistrationPage.GENDER.Male);
        registrationPage.enterName("Omkar", "User");
        registrationPage.enterDateOfBirth(1, "January", 2000);
        registrationPage.enterEmail(createUniqueEmail("omkar"));
        registrationPage.enterCompanyDetails("MIU");
        registrationPage.selectNewsLetter(true);
        registrationPage.enterPassword("Admin@123", "Admin@123");
        welcomePage = registrationPage.clickRegister();
        welcomePage.verifyRegistrationStatus("Your registration completed");
    }

    private String createUniqueEmail(String emailName) {
        String email = "@gmail.com";
        String name = emailName + createRandomNumber();
        return name + email;
    }

    private int createRandomNumber() {
        return (int) (Math.random() * 5000 + 1);
    }

}
