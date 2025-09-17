package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailPage {
    private WebDriver driver;

    private By productName = By.cssSelector(".product-information h2");
    private By category = By.xpath("//p[contains(text(),'Category')]");
    private By price = By.xpath("//span[contains(text(),'Rs.')]");
    private By availability = By.xpath("//b[text()='Availability:']");
    private By condition = By.xpath("//b[text()='Condition:']");
    private By brand = By.xpath("//b[text()='Brand:']");

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean areProductDetailsVisible() {
        return driver.findElement(productName).isDisplayed() &&
               driver.findElement(category).isDisplayed() &&
               driver.findElement(price).isDisplayed() &&
               driver.findElement(availability).isDisplayed() &&
               driver.findElement(condition).isDisplayed() &&
               driver.findElement(brand).isDisplayed();
    }
}
