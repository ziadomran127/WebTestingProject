package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CategoryPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Locators
    private By categoriesSection = By.xpath("//div[@class='left-sidebar']//h2[contains(text(),'Category')]");
    private By categoryTitle = By.xpath("//h2[@class='title text-center']");

    // Verify categories section is visible
    public boolean isCategoriesVisible() {
        WebElement categories = wait.until(ExpectedConditions.visibilityOfElementLocated(categoriesSection));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", categories);
        return categories.isDisplayed();
    }

    // Click any category by name (Women, Men, Kids, etc.)
    public void clickCategory(String categoryName) {
        By locator = By.xpath("//div[@class='left-sidebar']//a[contains(normalize-space(),'" + categoryName + "')]");
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
        System.out.println("✅ Clicked '" + categoryName + "' category");
    }

    // Click subcategory by name under a category (Dress, Tshirts, Tops & Shirts, etc.)
    public void clickSubCategory(String categoryName, String subCategoryName) {
        // First click the main category
        clickCategory(categoryName);

        // Then click the subcategory
        By locator = By.xpath("//div[@class='left-sidebar']//a[contains(normalize-space(),'" + subCategoryName + "')]");
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
        System.out.println("✅ Clicked '" + subCategoryName + "' sub-category under '" + categoryName + "'");
    }

    // Get text of a category (for verification)
    public String getCategoryText(String categoryName) {
        By locator = By.xpath("//div[@class='left-sidebar']//a[contains(normalize-space(),'" + categoryName + "')]");
        return driver.findElement(locator).getText();
    }

    // Get text of a subcategory (for verification)
    public String getSubCategoryText(String subCategoryName) {
        By locator = By.xpath("//div[@class='left-sidebar']//a[contains(normalize-space(),'" + subCategoryName + "')]");
        return driver.findElement(locator).getText();
    }

    // Get title of the current category page
    public String getCategoryPageTitle() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(categoryTitle));
        return title.getText();
    }
}
