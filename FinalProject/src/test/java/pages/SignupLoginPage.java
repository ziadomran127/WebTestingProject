/*package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupLoginPage {

    private WebDriver driver;

    // 🔹 Locators
    private By newUserSignupHeading = By.xpath("//h2[text()='New User Signup!']");
    private By nameInput = By.xpath("//form[@action='/signup']/input[@name='name']");
    private By emailInput = By.xpath("//form[@action='/signup']/input[@name='email']");
    private By signupButton = By.xpath("//form[@action='/signup']//button");

    // 🏗️ Constructor
    public SignupLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // ✅ Verify "New User Signup!" is visible
    public boolean isNewUserSignupVisible() {
        return driver.findElement(newUserSignupHeading).isDisplayed();
    }

    // ✍️ Enter name and email
    public void enterNameAndEmail(String name, String email) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
    }

    // ⚡ Click signup button
    public void clickSignupButton() {
        driver.findElement(signupButton).click();
    }
}
*/