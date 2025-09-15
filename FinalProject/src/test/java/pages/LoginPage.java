package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    
    // Locators
    private By signupNameInput = By.name("name");
    private By signupEmailInput = By.xpath("//input[@data-qa='signup-email']");
    private By signupButton = By.xpath("//button[contains(text(),'Signup')]");
    private By newUserSignupHeading = By.xpath("//h2[contains(text(),'New User Signup')]");
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public boolean isNewUserSignupVisible() {
        return driver.findElement(newUserSignupHeading).isDisplayed();
    }
    
    public void enterSignupDetails(String name, String email) {
        driver.findElement(signupNameInput).sendKeys(name);
        driver.findElement(signupEmailInput).sendKeys(email);
    }
    
    public void clickSignupButton() {
        driver.findElement(signupButton).click();
    }
}