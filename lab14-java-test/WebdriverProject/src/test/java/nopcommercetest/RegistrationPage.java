package nopcommercetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage {

    public static enum GENDER {
        Male, Female
    }

    protected WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open(String url) {
        driver.get(url);
    }

    public void close() {
        driver.close();
    }

    // Personal Details
    @FindBy(id = "gender-male")
    private WebElement genderMaleRB;
    @FindBy(id = "gender-female")
    private WebElement genderFemaleRB;
    @FindBy(id = "FirstName")
    private WebElement firstNameTF;
    @FindBy(id = "LastName")
    private WebElement lastNameTF;

    @FindBy(name = "DateOfBirthDay")
    private WebElement dateOfBirthDaySB;
    @FindBy(name = "DateOfBirthMonth")
    private WebElement dateOfBirthMonthSB;
    @FindBy(name = "DateOfBirthYear")
    private WebElement dateOfBirthYearSB;

    @FindBy(id = "Email")
    private WebElement emailTF;

    @FindBy(id = "Company")
    private WebElement companyTF;

    // Options
    @FindBy(name = "Newsletter")
    private WebElement newsletterCB;

    // Password
    @FindBy(id = "Password")
    private WebElement passwordPF;
    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPasswordPF;

    @FindBy(id = "register-button")
    private WebElement registerButton;

    public WebElement getGenderMaleRB() {
        return genderMaleRB;
    }

    public WebElement getGenderFemaleRB() {
        return genderFemaleRB;
    }

    public WebElement getFirstNameTF() {
        return firstNameTF;
    }

    public WebElement getLastNameTF() {
        return lastNameTF;
    }

    public WebElement getDateOfBirthDaySB() {
        return dateOfBirthDaySB;
    }

    public WebElement getDateOfBirthMonthSB() {
        return dateOfBirthMonthSB;
    }

    public WebElement getDateOfBirthYearSB() {
        return dateOfBirthYearSB;
    }

    public WebElement getEmailTF() {
        return emailTF;
    }

    public WebElement getCompanyTF() {
        return companyTF;
    }

    public WebElement getNewsletterCB() {
        return newsletterCB;
    }

    public WebElement getPasswordPF() {
        return passwordPF;
    }

    public WebElement getConfirmPasswordPF() {
        return confirmPasswordPF;
    }

    public WebElement getRegisterButton() {
        return registerButton;
    }

    public void selectGender(GENDER gender) {
        if (gender.equals(GENDER.Male)) {
            genderMaleRB.click();
        } else {
            genderFemaleRB.click();
        }
    }

    public void enterName(String firstName, String lastName) {
        firstNameTF.clear();
        firstNameTF.sendKeys(firstName);
        lastNameTF.clear();
        lastNameTF.sendKeys(lastName);
    }

    public void enterDateOfBirth(int day, String month, int year) {
        Select selectDay = new Select(dateOfBirthDaySB);
        Select selectMonth = new Select(dateOfBirthMonthSB);
        Select selectYear = new Select(dateOfBirthYearSB);
        selectDay.selectByVisibleText(String.valueOf(day));
        selectMonth.selectByVisibleText(month);
        selectYear.selectByVisibleText(String.valueOf(year));
    }

    public void enterEmail(String email) {
        emailTF.clear();
        emailTF.sendKeys(email);
    }

    public void enterCompanyDetails(String companyName) {
        companyTF.clear();
        companyTF.sendKeys(companyName);
    }

    public void selectNewsLetter(boolean checkNewsLetter) {
        if (newsletterCB.isSelected() && checkNewsLetter || !newsletterCB.isSelected() && !checkNewsLetter) {
            return;
        } else {
            newsletterCB.click();
        }
    }


    public void enterPassword(String password, String confirmPassword) {
        passwordPF.clear();
        confirmPasswordPF.clear();
        passwordPF.sendKeys(password);
        confirmPasswordPF.sendKeys(confirmPassword);
    }

    public WelcomePage clickRegister() {
        registerButton.submit();
        return new WelcomePage(driver);
    }
}
