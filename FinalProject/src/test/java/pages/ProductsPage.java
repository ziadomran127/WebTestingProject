package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import framework.SeleniumFrameWork;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
public class ProductsPage {
    private WebDriver driver;
    private SeleniumFrameWork sf;

    private By allProductsHeader = By.xpath("//h2[contains(text(),'All Products')]");
    private By productsList = By.cssSelector(".features_items .product-image-wrapper");
    private By firstProductViewButton = By.xpath("/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[2]/ul/li/a");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.sf = new SeleniumFrameWork(driver);
    }

    public boolean isAllProductsPageVisible() {
        return driver.findElement(allProductsHeader).isDisplayed();
    }

    public boolean isProductsListVisible() {
        return driver.findElements(productsList).size() > 0;
    }

   public void clickFirstProductViewButton() {
    sf.Edges_explicitWait(firstProductViewButton, 3);

    WebElement element = driver.findElement(firstProductViewButton);

    // Scroll للعنصر عشان يظهر في الشاشة
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

    // استخدام JavaScript click عشان نتجنب الاعلان اللي بيغطيه
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

    System.out.println("✅ Clicked on first product 'View Product' button using JS.");
}

}
