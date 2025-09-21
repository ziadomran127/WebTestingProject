package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import framework.SeleniumFrameWork ;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    private SeleniumFrameWork sf;
   

    // 🔹 Locators
    private By logo = By.xpath("/html/body/header/div/div/div/div[1]/div/a/img");
    private By signupLoginLink = By.xpath("/html/body/header/div/div/div/div[2]/div/ul/li[4]/a");
    private By productsButton = By.xpath("//a[@href='/products']");
     private By testCasesButton = By.xpath("//a[@href='/test_cases']");
     private By subscriptionText = By.xpath("//h2[text()='Subscription']");
    private By subscriptionEmailInput = By.id("susbscribe_email");
    private By subscriptionArrowButton = By.id("subscribe");
    private By successMessage = By.xpath("/html/body/footer/div[1]/div/div/div[1]/div/div");
    // ===== Recommended Items =====
private By recommendedItemsSection = By.xpath("//*[contains(translate(text(),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'RECOMMENDED ITEMS')]");
private By firstRecommendedItem = By.xpath("(//div[@class='recommended_items']//div[@class='product-image-wrapper'])[1]");
private By addToCartButtonRecommended = By.xpath("(//div[@class='recommended_items']//a[@data-product-id])[1]");

    // 🏗️ Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.sf = new SeleniumFrameWork(driver);
    }

    // ✅ Verify home page by title
    public boolean isHomePageVisibleByTitle() {
        String expectedTitle = "Automation Exercise";
        return driver.getTitle().equals(expectedTitle);
    }

    // ✅ Verify home page by logo
    public boolean isHomePageVisibleByLogo() {
        return driver.findElement(logo).isDisplayed();
    }

    // ✅ Combined check
    public boolean isHomePageVisible() {
        return isHomePageVisibleByTitle() && isHomePageVisibleByLogo();
    }

    // 🖱️ Click on "Signup / Login"
    public void clickSignupLogin() {
        driver.findElement(signupLoginLink).click();
    }
    
    public void clickContactUs() {
    driver.findElement(By.xpath("//a[text()=' Contact us']")).click();
}
    
      public void clickTestCasesButton() {
        driver.findElement(testCasesButton).click();
    }
      
        public void clickProductsButton() {
        driver.findElement(productsButton).click();
    }
        
          public void scrollToFooter() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        System.out.println(" Scrolled to footer.");
    }

    public boolean isSubscriptionTextVisible() {
        return driver.findElement(subscriptionText).isDisplayed();
    }

    public void enterEmailAndSubscribe(String email) {
        driver.findElement(subscriptionEmailInput).clear();
        driver.findElement(subscriptionEmailInput).sendKeys(email);
        driver.findElement(subscriptionArrowButton).click();
        System.out.println(" Subscribed with email: " + email);
    }

    public boolean isSubscriptionSuccessVisible() {
         sf.Edges_explicitWait(successMessage, 3);
        return driver.findElement(successMessage).isDisplayed();
    }
    
  public String addFirstRecommendedItemToCart() {
    // Locate first recommended product name
    WebElement firstProductName = driver.findElement(By.xpath("//div[@id='recommended-item-carousel']//h2"));
    String productName = firstProductName.getText();

    // Click Add To Cart for first recommended product
    WebElement addToCartBtn = driver.findElement(By.xpath("(//div[@id='recommended-item-carousel']//a[contains(text(),'Add to cart')])[1]"));
    addToCartBtn.click();

    // Wait for modal & click Continue Shopping
    WebElement continueBtn = driver.findElement(By.xpath("//button[text()='Continue Shopping']"));
    continueBtn.click();

    return productName;
}




   public void scrollToRecommendedItems() {
    WebElement section = driver.findElement(recommendedItemsSection);
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", section);
}
public boolean isRecommendedItemsVisible() {
    try {
        WebElement section = driver.findElement(recommendedItemsSection);
        return section.isDisplayed();
    } catch (Exception e) {
        return false;
    }
}

}
