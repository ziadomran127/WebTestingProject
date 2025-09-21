package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrandsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locator for Brands section
    private By brandsSection = By.cssSelector(".brands_products");

    // Locator for category title on brand page
    private By brandTitle = By.xpath("//h2[@class='title text-center']");

    public BrandsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Verify Brands section is visible
    public boolean isBrandsVisible() {
        WebElement brands = wait.until(ExpectedConditions.visibilityOfElementLocated(brandsSection));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", brands);
        return brands.isDisplayed();
    }

    // Click on a brand by name
    public void clickBrand(String brandName) {
        By locator = By.xpath("//div[@class='brands_products']//a[contains(normalize-space(),'" + brandName + "')]");
        
        // Wait until clickable
        WebElement brandLink = wait.until(ExpectedConditions.elementToBeClickable(locator));
        
        // Scroll into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", brandLink);
        
        // Optional short pause for smooth click
        try { Thread.sleep(500); } catch (InterruptedException e) { }
        
        brandLink.click();
        System.out.println("✅ Clicked brand: " + brandName);
    }

    // Get the brand page title text (for verification)
    public String getBrandPageTitle() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(brandTitle));
        return title.getText().trim();
    }
}
