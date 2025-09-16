package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    
    // sign up Locators
    private By signupNameInput = By.name("name");
    private By signupEmailInput = By.xpath("//input[@data-qa='signup-email']");
    private By signupButton = By.xpath("//button[contains(text(),'Signup')]");
    private By newUserSignupHeading = By.xpath("//h2[contains(text(),'New User Signup')]");
    
     // 🔹 Login Locators
    private By loginHeading = By.xpath("//h2[contains(text(),'Login to your account')]");
    private By loginEmailInput = By.xpath("//input[@data-qa='login-email']");
    private By loginPasswordInput = By.xpath("//input[@data-qa='login-password']");
    private By loginButton = By.xpath("//button[@data-qa='login-button']");
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public boolean isNewUserSignupVisible() {
        return driver.findElement(newUserSignupHeading).isDisplayed();
    }
    
     // ✅ Verify "Login to your account" visible
    public boolean isLoginSectionVisible() {
        return driver.findElement(loginHeading).isDisplayed();
    }
    
    public void enterSignupDetails(String name, String email) {
        driver.findElement(signupNameInput).sendKeys(name);
        driver.findElement(signupEmailInput).sendKeys(email);
    }
    
    public void clickSignupButton() {
        driver.findElement(signupButton).click();
    }
    
    // ✅ Enter login details
    public void enterLoginDetails(String email, String password) {
        driver.findElement(loginEmailInput).sendKeys(email);
        driver.findElement(loginPasswordInput).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}