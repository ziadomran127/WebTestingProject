package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignupPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // ───── Locators ─────
    // Step 6
    private By nameInput = By.name("name");
    private By emailInput = By.xpath("//input[@data-qa='signup-email']");
    private By signupButton = By.xpath("//button[@data-qa='signup-button']");

    // Step 8
    private By enterAccountInfoHeading = By.xpath("//b[text()='Enter Account Information']");

    // Step 9
    private By titleMrRadio = By.id("id_gender1");
    private By titleMrsRadio = By.id("id_gender2");
    private By passwordInput = By.id("password");
    private By dayDropdown = By.id("days");
    private By monthDropdown = By.id("months");
    private By yearDropdown = By.id("years");

    // Step 10 & 11
    private By newsletterCheckbox = By.id("newsletter");
    private By offersCheckbox = By.id("optin");

    // Step 12 (Address info)
    private By firstNameInput = By.id("first_name");
    private By lastNameInput = By.id("last_name");
    private By companyInput = By.id("company");
    private By address1Input = By.id("address1");
    private By address2Input = By.id("address2");
    private By countryDropdown = By.id("country");
    private By stateInput = By.id("state");
    private By cityInput = By.id("city");
    private By zipcodeInput = By.id("zipcode");
    private By mobileNumberInput = By.id("mobile_number");

    // Step 13
    private By createAccountButton = By.xpath("//button[@data-qa='create-account']");

    // Step 14
    private By accountCreatedMsg = By.xpath("//b[text()='Account Created!']");
    private By continueButton = By.xpath("//a[@data-qa='continue-button']");

    // Step 15
    private By loggedInAs = By.xpath("//a[contains(text(),'Logged in as')]");

    // ───── Constructor ─────
    public SignupPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ───── Step 6 ─────
    public void enterNameAndEmail(String name, String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
    }

    // ───── Step 7 ─────
    public void clickSignupButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signupButton));
        driver.findElement(signupButton).click();
    }

    // ───── Step 8 ─────
    public boolean isEnterAccountInformationVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(enterAccountInfoHeading));
        return driver.findElement(enterAccountInfoHeading).isDisplayed();
    }

    // ───── Step 9 ─────
    public void fillAccountInformation(String title, String password, String day, String month, String year) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(titleMrRadio));
        
        if (title.equalsIgnoreCase("Mr")) {
            driver.findElement(titleMrRadio).click();
        } else {
            driver.findElement(titleMrsRadio).click();
        }
        
        driver.findElement(passwordInput).sendKeys(password);
        
        // Use helper method for dropdowns
        selectDropdownOption(dayDropdown, day);
        selectDropdownOption(monthDropdown, month);
        selectDropdownOption(yearDropdown, year);
    }

    // Helper method for dropdown selection
    private void selectDropdownOption(By locator, String value) {
        Select dropdown = new Select(driver.findElement(locator));
        dropdown.selectByValue(value);
    }

    // ───── Step 10 ─────
    public void selectNewsletterCheckbox() {
        driver.findElement(newsletterCheckbox).click();
    }

    // ───── Step 11 ─────
    public void selectOffersCheckbox() {
        driver.findElement(offersCheckbox).click();
    }

    // ───── Step 12 ─────
    public void fillAddressInformation(
            String firstName, String lastName, String company, String address1, String address2,
            String country, String state, String city, String zipcode, String mobileNumber
    ) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(companyInput).sendKeys(company);
        driver.findElement(address1Input).sendKeys(address1);
        driver.findElement(address2Input).sendKeys(address2);
        
        // For country dropdown
        Select countrySelect = new Select(driver.findElement(countryDropdown));
        countrySelect.selectByVisibleText(country);
        
        driver.findElement(stateInput).sendKeys(state);
        driver.findElement(cityInput).sendKeys(city);
        driver.findElement(zipcodeInput).sendKeys(zipcode);
        driver.findElement(mobileNumberInput).sendKeys(mobileNumber);
    }

    // ───── Step 13 ─────
    public void clickCreateAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createAccountButton));
        driver.findElement(createAccountButton).click();
    }

    // ───── Step 14 ─────
    public boolean isAccountCreatedVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountCreatedMsg));
        return driver.findElement(accountCreatedMsg).isDisplayed();
    }

    public void clickContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        driver.findElement(continueButton).click();
    }

    // ───── Step 15 ─────
    public boolean isLoggedInAsVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loggedInAs));
        return driver.findElement(loggedInAs).isDisplayed();
    }
}