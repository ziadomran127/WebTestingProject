package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebElement;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // ====== Locators ======
    private By cartButton = By.xpath("//a[@href='/view_cart']");
    private By proceedToCheckoutButton = By.xpath("//a[text()='Proceed To Checkout']");
    private By registerLoginButton = By.xpath("/html/body/section/div/section/div[2]/div/div/div[2]/p[2]/a/u");
    private By cartPageHeader = By.xpath("//section[@id='cart_items']//h2[contains(text(),'Shopping Cart')]");
    private By addressDetailsSection = By.cssSelector("/html/body/section/div/div[2]/h2"); // update with actual locator
    private By reviewOrderSection = By.cssSelector("/html/body/section/div/div[4]/h2");       // update with actual locator
    private By commentTextArea = By.name("message"); // usually the comment box has name="message"
    private By placeOrderButton = By.xpath("//a[text()='Place Order']"); // adjust if button text is different
    
    // ====== Remove product locators ======
    // Assuming the first product's remove 'X' button has this locator
    private By firstProductRemoveButton = By.xpath("/html/body/section/div/div[2]/table/tbody/tr[1]/td[6]/a");
    private By firstProductRow = By.xpath("//tr[1]");
     private By cartFirstProductName = By.xpath("//table[@id='cart_info_table']//td[@class='cart_description']/h4/a");
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Click Cart button from any page
    public void clickCartButton() {
        driver.findElement(cartButton).click();
        System.out.println("🛒 Clicked on Cart button");
    }

    // Verify cart page is visible
    public boolean isCartPageVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartPageHeader));
        boolean visible = driver.findElement(cartPageHeader).isDisplayed();
        System.out.println("✅ Cart page visible: " + visible);
        return visible;
    }

    // Click Proceed To Checkout
    public void clickProceedToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton));
        driver.findElement(proceedToCheckoutButton).click();
        System.out.println("➡️ Clicked 'Proceed To Checkout'");
    }

  public void clickRegisterOrLogin() throws InterruptedException {
    wait.until(ExpectedConditions.visibilityOfElementLocated(registerLoginButton));
    wait.until(ExpectedConditions.elementToBeClickable(registerLoginButton));
    
    Thread.sleep(500); // optional pause
    
    WebElement btn = driver.findElement(registerLoginButton);
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'}); arguments[0].click();", btn);

    System.out.println("📝 Clicked 'Register / Login'");
}

    // Scroll to footer (optional)
    public void scrollToFooter() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        System.out.println("📜 Scrolled to footer on Cart page");
    }
    
    // Verify Address Details section
   public boolean isAddressDetailsVisible() {
    By addressDetails = By.xpath("//h2[text()='Address Details']");
    wait.until(ExpectedConditions.visibilityOfElementLocated(addressDetails));
    return driver.findElement(addressDetails).isDisplayed();
}

public boolean isReviewOrderVisible() {
    By reviewOrder = By.xpath("//h2[text()='Review Your Order']");
    wait.until(ExpectedConditions.visibilityOfElementLocated(reviewOrder));
    return driver.findElement(reviewOrder).isDisplayed();
}

// Method to enter comment
public void enterOrderComment(String comment) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(commentTextArea));
    driver.findElement(commentTextArea).sendKeys(comment);
    System.out.println("📝 Entered order comment: " + comment);
}

// Method to click Place Order
public void clickPlaceOrder() {
    wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
    driver.findElement(placeOrderButton).click();
    System.out.println("✅ Clicked 'Place Order' button");
}


 // Remove first product from cart
    public void removeFirstProductFromCart() {
        wait.until(ExpectedConditions.elementToBeClickable(firstProductRemoveButton));
        WebElement btn = driver.findElement(firstProductRemoveButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", btn);
        System.out.println("🗑️ Clicked 'X' to remove first product from cart");
    }

    public boolean isFirstProductInCartVisible() {
    try {
        // Wait a few seconds to allow product removal
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        shortWait.until(ExpectedConditions.invisibilityOfElementLocated(firstProductRemoveButton));
        return driver.findElement(firstProductRemoveButton).isDisplayed();
    } catch (Exception e) {
        // Element not found or invisible -> product removed
        return false;
    }
}
    public String getFirstProductNameInCart() {
        return driver.findElement(cartFirstProductName).getText().trim();
    }
  public boolean isProductInCart(String expectedName) {
    List<WebElement> products = driver.findElements(By.xpath("//table[@id='cart_info_table']//td[@class='cart_description']/h4/a"));
    for (WebElement product : products) {
        if (product.getText().trim().contains(expectedName)) {
            return true;
        }
    }
    return false;
}


}