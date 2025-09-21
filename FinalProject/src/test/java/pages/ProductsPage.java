package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import framework.SeleniumFrameWork;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductsPage {

    private WebDriver driver;
    private SeleniumFrameWork sf;
    private WebDriverWait wait;

    // ====== Locators ======
    private By allProductsHeader = By.xpath("//h2[contains(text(),'All Products')]");
    private By productsList = By.cssSelector(".features_items .product-image-wrapper");
    private By firstProductViewButton = By.xpath("/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[2]/ul/li/a");

    // Searching
    private By searchInput = By.id("search_product");
    private By searchButton = By.id("submit_search");
    private By searchedProductsHeader = By.xpath("//h2[text()='Searched Products']");

    // Product Detail Page Verification
    private By productDetailInfo = By.cssSelector(".product-information");

    // Add to cart
    private By firstProductCard = By.xpath("(//div[@class='product-image-wrapper'])[1]");
    private By secondProductCard = By.xpath("(//div[@class='product-image-wrapper'])[2]");
    private By continueShoppingButton = By.xpath("//button[text()='Continue Shopping']");
    private By viewCartButton = By.xpath("//u[text()='View Cart']");
    private By addToCartButtonDetail = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/span/button");

    // Modal window (appears after adding to cart)
    private By successModal = By.cssSelector(".modal-content");

    // Quantity Handling
    private By quantityInput = By.id("quantity");
    
    // Review section locators
private By reviewNameInput = By.id("name");
private By reviewEmailInput = By.id("email");
private By reviewTextArea = By.id("review");
private By submitReviewButton = By.id("button-review");
private By reviewSuccessMessage = By.cssSelector(".alert-success");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.sf = new SeleniumFrameWork(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ====== Page Visibility ======
    public boolean isAllProductsPageVisible() {
        return driver.findElement(allProductsHeader).isDisplayed();
    }

    public boolean isProductDetailVisible() {
        boolean visible = driver.findElement(productDetailInfo).isDisplayed();
        System.out.println("✅ Product detail page visible: " + visible);
        return visible;
    }

    public boolean isProductsListVisible() {
        return driver.findElements(productsList).size() > 0;
    }

    public void clickFirstProductViewButton() {
        sf.Edges_explicitWait(firstProductViewButton, 3);
        WebElement element = driver.findElement(firstProductViewButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        System.out.println("✅ Clicked on first product 'View Product' button using JS.");
    }

    // ====== Searching ======
    public void searchForProduct(String productName) {
        sf.Edges_explicitWait(searchInput, 3);
        driver.findElement(searchInput).clear();
        driver.findElement(searchInput).sendKeys(productName);
        driver.findElement(searchButton).click();
        System.out.println("🔍 Searched for product: " + productName);
    }

    public boolean isSearchedProductsVisible() {
        boolean headerVisible = driver.findElement(searchedProductsHeader).isDisplayed();
        List<WebElement> results = driver.findElements(productsList);
        return headerVisible && results.size() > 0;
    }

   public boolean areAllSearchedProductsRelevant(String searchKeyword) {
    List<WebElement> products = driver.findElements(productsList);
    boolean allRelevant = true;
    String lowerKeyword = searchKeyword.toLowerCase().trim();

    for (WebElement product : products) {
        String productName = "";
        try {
            // Try getting product name from <p> first
            productName = product.findElement(By.cssSelector(".productinfo p")).getText().toLowerCase().trim();
            
            // If empty, try <h2>
            if (productName.isEmpty()) {
                productName = product.findElement(By.cssSelector(".productinfo h2")).getText().toLowerCase().trim();
            }

            if (!productName.contains(lowerKeyword)) {
                System.out.println("⚠️ Unrelated product found: " + productName);
                allRelevant = false;
            }
        } catch (Exception e) {
            System.out.println("⚠️ Could not read product name for one product");
            allRelevant = false;
        }
    }
    return allRelevant;
}




    // ====== Add to cart helpers ======
    private void waitForModalToDisappear() {
        try {
            WebDriverWait modalWait = new WebDriverWait(driver, Duration.ofSeconds(20));
            modalWait.pollingEvery(Duration.ofMillis(500))
                     .until(ExpectedConditions.invisibilityOfElementLocated(successModal));
            System.out.println("✅ Modal disappeared.");
        } catch (Exception e) {
            System.out.println("⚠️ Modal did not disappear in time, continuing anyway.");
        }
    }

    private void addProductToCart(WebElement card, WebElement button, String productName) {
        // Scroll and hover
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'}); window.scrollBy(0, -100);", card);
        new Actions(driver).moveToElement(card).pause(Duration.ofMillis(500)).perform();

        // Click add to cart using JS
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);

        // Wait for modal
        wait.until(ExpectedConditions.visibilityOfElementLocated(successModal));
        System.out.println("✅ " + productName + " added to cart.");
    }

    public void addFirstProductToCart() {
        WebElement card = driver.findElement(firstProductCard);
        WebElement button = card.findElement(By.cssSelector("a[data-product-id]"));
        addProductToCart(card, button, "First product");
    }

    public void addSecondProductToCart() {
        waitForModalToDisappear(); // Wait for first modal to disappear
        WebElement card = driver.findElement(secondProductCard);
        WebElement button = card.findElement(By.cssSelector("a[data-product-id]"));
        addProductToCart(card, button, "Second product");
    }

    // ====== Continue Shopping & View Cart ======
    public void clickContinueShopping() {
        WebElement button = driver.findElement(continueShoppingButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        waitForModalToDisappear();
        System.out.println("✅ Clicked 'Continue Shopping'");
    }

    public void clickViewCart() {
        WebElement button = driver.findElement(viewCartButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("🛒 Clicked 'View Cart'");
    }

    // ====== Quantity Handling ======
    public void setQuantity(int quantity) {
        WebElement qty = driver.findElement(quantityInput);
        qty.clear();
        qty.sendKeys(String.valueOf(quantity));
        System.out.println("✅ Set product quantity to " + quantity);
    }

    public int getQuantityFromCart() {
        By quantityLocator = By.xpath("//table[@id='cart_info_table']//tbody/tr/td[4]/button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(quantityLocator));
        String qtyText = driver.findElement(quantityLocator).getText().trim();
        int quantity = Integer.parseInt(qtyText);
        System.out.println("✅ Product quantity in cart: " + quantity);
        return quantity;
    }

    public void clickAddToCartFromDetail() {
        WebElement button = driver.findElement(addToCartButtonDetail);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        wait.until(ExpectedConditions.visibilityOfElementLocated(successModal));
        System.out.println("✅ Clicked 'Add to cart' from product detail page");
    }
    
    public void enterReview(String name, String email, String review) {
    driver.findElement(reviewNameInput).sendKeys(name);
    driver.findElement(reviewEmailInput).sendKeys(email);
    driver.findElement(reviewTextArea).sendKeys(review);
    System.out.println("✅ Filled review form");
}

public void submitReview() {
    driver.findElement(submitReviewButton).click();
    System.out.println("✅ Clicked 'Submit Review'");
}

public boolean isReviewSubmittedMessageVisible() {
    return driver.findElement(reviewSuccessMessage).isDisplayed();
}
}
