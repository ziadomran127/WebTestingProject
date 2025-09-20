package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDetailPage {
    private WebDriver driver;

    private By productName = By.cssSelector(".product-information h2");
    private By category = By.xpath("//p[contains(text(),'Category')]");
    private By price = By.xpath("//span[contains(text(),'Rs.')]");
    private By availability = By.xpath("//b[text()='Availability:']");
    private By condition = By.xpath("//b[text()='Condition:']");
    private By brand = By.xpath("//b[text()='Brand:']");
    private By quantityInput = By.id("quantity");
    private By addToCartButton = By.xpath("//button[text()='Add to cart']");

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
    }

    // ✅ Check if all product details are visible
    public boolean areProductDetailsVisible() {
        return driver.findElement(productName).isDisplayed() &&
               driver.findElement(category).isDisplayed() &&
               driver.findElement(price).isDisplayed() &&
               driver.findElement(availability).isDisplayed() &&
               driver.findElement(condition).isDisplayed() &&
               driver.findElement(brand).isDisplayed();
    }

    // ✅ Get product name
    public String getProductName() {
        return driver.findElement(productName).getText().trim();
    }

    // ✅ Get product price as double
    public double getProductPrice() {
        String priceText = driver.findElement(price).getText().replace("Rs.", "").trim();
        return Double.parseDouble(priceText);
    }

    // ✅ Get quantity input field value
    public int getQuantity() {
        String qty = driver.findElement(quantityInput).getAttribute("value").trim();
        return Integer.parseInt(qty);
    }

    // ✅ Set quantity
    public void setQuantity(int quantity) {
        WebElement qtyInput = driver.findElement(quantityInput);
        qtyInput.clear();
        qtyInput.sendKeys(String.valueOf(quantity));
    }

    // ✅ Click Add to Cart
   // ✅ Click Add to Cart (robust version)
public void clickAddToCart() {
    WebElement button = driver.findElement(addToCartButton);

    // Scroll button into view
    ((JavascriptExecutor) driver).executeScript(
        "arguments[0].scrollIntoView({block: 'center'}); window.scrollBy(0, -100);", button
    );

    // Small pause to ensure button is interactable
    try { Thread.sleep(300); } catch (InterruptedException e) { }

    // Click using JS to avoid overlay issues
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
}

}
