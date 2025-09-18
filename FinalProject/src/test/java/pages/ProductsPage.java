package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import framework.SeleniumFrameWork;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import java.util.List; 


public class ProductsPage {
    private WebDriver driver;
    private SeleniumFrameWork sf;

    private By allProductsHeader = By.xpath("//h2[contains(text(),'All Products')]");
    private By productsList = By.cssSelector(".features_items .product-image-wrapper");
    private By firstProductViewButton = By.xpath("/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[2]/ul/li/a");

    // for Searching 
    private By searchInput = By.id("search_product");
    private By searchButton = By.id("submit_search");
     private By searchedProductsHeader = By.xpath("//h2[text()='Searched Products']");

    
    
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
   
   
    // searching for a product 
    public void searchForProduct(String productName) {
        sf.Edges_explicitWait(searchInput, 3);
        driver.findElement(searchInput).clear();
        driver.findElement(searchInput).sendKeys(productName);
        driver.findElement(searchButton).click();
        System.out.println("🔍 Searched for product: " + productName);
    }

// 🆕 التحقق من نتائج البحث
    public boolean isSearchedProductsVisible() {
        boolean headerVisible = driver.findElement(searchedProductsHeader).isDisplayed();
        List<WebElement> results = driver.findElements(productsList);
        return headerVisible && results.size() > 0;
    }
    
    public boolean areAllSearchedProductsRelevant(String searchKeyword) {
    List<WebElement> products = driver.findElements(productsList);
    if (products.isEmpty()) return false;

    String lowerKeyword = searchKeyword.toLowerCase();

    for (WebElement product : products) {
        try {
            WebElement nameElement = product.findElement(By.cssSelector(".productinfo p"));
            String productName = nameElement.getText().toLowerCase();
            if (!productName.contains(lowerKeyword)) {
                System.out.println("⚠️ Found unrelated product: " + productName);
                return false;
            }
        } catch (Exception e) {
            System.out.println("⚠️ Couldn't read product name for one product");
            return false;
        }
    }
    return true;
}

}