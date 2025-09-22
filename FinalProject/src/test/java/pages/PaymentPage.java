package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class PaymentPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // ───── Locators ─────
    private By nameOnCardInput = By.name("name_on_card");
    private By cardNumberInput = By.name("card_number");
    private By cvcInput = By.name("cvc");
    private By expiryMonthInput = By.name("expiry_month");
    private By expiryYearInput = By.name("expiry_year");
    private By payAndConfirmButton = By.id("submit"); // Adjust if needed
     private By orderPlacedMsg = By.xpath("//p[text()='Congratulations! Your order has been confirmed!']");
     // Invoice locators
    private By downloadInvoiceButton = By.xpath("//a[text()='Download Invoice']");
    private By continueButton = By.xpath("//a[@data-qa='continue-button']");

    // ───── Constructor ─────
    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ───── Step 16: Fill payment details ─────
    public void fillPaymentDetails(String name, String cardNumber, String cvc, String month, String year) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameOnCardInput));
        driver.findElement(nameOnCardInput).sendKeys(name);
        driver.findElement(cardNumberInput).sendKeys(cardNumber);
        driver.findElement(cvcInput).sendKeys(cvc);
        driver.findElement(expiryMonthInput).sendKeys(month);
        driver.findElement(expiryYearInput).sendKeys(year);
        System.out.println("💳 Payment details entered");
    }

    // ───── Step 17: Click Pay and Confirm Order ─────
    public void clickPayAndConfirm() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(payAndConfirmButton));
        WebElement btn = driver.findElement(payAndConfirmButton);

        // Scroll into view and click using JavaScript
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'}); arguments[0].click();", btn);

        System.out.println("✅ Clicked 'Pay and Confirm Order' safely via JS");
    }

   // STEP 18: Verify order placed success message
    public boolean isOrderPlacedSuccessVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderPlacedMsg));
        boolean visible = driver.findElement(orderPlacedMsg).isDisplayed();
        System.out.println("🎉 Order placed successfully: " + visible);
        return visible;
    }
    
     public void clickDownloadInvoice() {
        wait.until(ExpectedConditions.elementToBeClickable(downloadInvoiceButton));
        driver.findElement(downloadInvoiceButton).click();
        System.out.println("📥 Clicked 'Download Invoice'");
    }
     
      public boolean isInvoiceButtonVisible() {
        return driver.findElement(downloadInvoiceButton).isDisplayed();
    }
      
       public void clickContinueAfterInvoice() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        driver.findElement(continueButton).click();
        System.out.println("➡️ Clicked 'Continue' after downloading invoice");
    }
}
