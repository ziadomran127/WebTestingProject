package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import framework.SeleniumFrameWork;

public class LoginPage {
    private WebDriver driver;
    private SeleniumFrameWork sf;
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
    private By loggedInAs = By.xpath("/html/body/header/div/div/div/div[2]/div/ul/li[10]/a");
     private By deleteAccountButton = By.xpath("//a[text()=' Delete Account']");
    private By accountDeletedMessage = By.xpath("//b[text()='Account Deleted!']");
    private By continueAfterDeleteButton = By.xpath("//a[text()='Continue']");
    public LoginPage(WebDriver driver) {
        this.driver = driver;
         this.sf = new SeleniumFrameWork(driver);
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
    
   public boolean isLoggedInAsVisible() {
    sf.Edges_explicitWait(loggedInAs, 10);
    return driver.findElement(loggedInAs).isDisplayed();
}

    public void clickDeleteAccountButton() {
        driver.findElement(deleteAccountButton).click();
    }

    public boolean isAccountDeletedVisible() {
        return driver.findElement(accountDeletedMessage).isDisplayed();
    }

    public void clickContinueAfterDelete() {
        driver.findElement(continueAfterDeleteButton).click();
    }
    
    
}