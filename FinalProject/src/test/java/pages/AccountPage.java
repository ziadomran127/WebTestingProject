package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // ====== Locators ======
    private By deleteAccountButton = By.xpath("//a[contains(text(),'Delete Account')]");
    private By accountDeletedMsg = By.xpath("//b[text()='Account Deleted!']");
    private By continueButton = By.xpath("//a[@data-qa='continue-button']");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // STEP 19: Click 'Delete Account'
    public void clickDeleteAccount() {
    try {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(deleteAccountButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", btn);
        System.out.println("❌ Clicked 'Delete Account'");
    } catch (Exception e) {
        System.out.println("⚠️ Delete Account button not found or clickable: " + e.getMessage());
        throw e;
    }
}


    // STEP 20: Verify account deleted and click continue
    public boolean isAccountDeletedVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountDeletedMsg));
        boolean visible = driver.findElement(accountDeletedMsg).isDisplayed();
        System.out.println("✅ Account deleted message visible: " + visible);
        return visible;
    }

    public void clickContinueAfterDeletion() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        driver.findElement(continueButton).click();
        System.out.println("➡️ Clicked 'Continue' after account deletion");
    }
}
