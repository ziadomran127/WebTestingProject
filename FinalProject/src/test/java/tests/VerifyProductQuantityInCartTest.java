package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.HomePage;
import pages.ProductDetailPage;

import java.time.Duration;

public class VerifyProductQuantityTest {

    private WebDriver driver;
    private HomePage homePage;
    private ProductDetailPage productDetailPage;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");

        homePage = new HomePage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testProductQuantityInCart() {
        System.out.println("🚀 Starting Test Case 13: Verify Product Quantity in Cart");

        // Step 3: Verify home page is visible
        Assert.assertTrue(homePage.isHomePageVisible(), "❌ Home page is not visible!");

        // Step 4: Click 'View Product' for first product
        WebElement firstProductViewButton = driver.findElement(By.xpath("(//a[text()='View Product'])[1]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", firstProductViewButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstProductViewButton);

        // Step 5: Verify product detail page opened
        productDetailPage = new ProductDetailPage(driver);
        Assert.assertTrue(productDetailPage.areProductDetailsVisible(), "❌ Product details are not visible!");

        // Step 6: Increase quantity to 4
        productDetailPage.setQuantity(4);

        // Step 7: Click 'Add to cart'
        productDetailPage.clickAddToCart();

        // Step 8: Click 'View Cart' button from modal
        WebElement viewCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//u[text()='View Cart']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewCartButton);

        // Step 9: Verify product is displayed in cart with exact quantity
        WebElement cartRow = driver.findElement(By.xpath("//table[@id='cart_info_table']//tbody/tr[1]"));
        String cartProductName = cartRow.findElement(By.xpath("./td[2]/h4/a")).getText().trim();
        String cartQuantity = cartRow.findElement(By.xpath("./td[4]/button")).getText().trim();

        Assert.assertEquals(cartProductName, productDetailPage.getProductName(), "❌ Product name in cart is incorrect!");
        Assert.assertEquals(cartQuantity, "4", "❌ Product quantity in cart is incorrect!");

        System.out.printf("✅ Product '%s' is in cart with quantity %s%n", cartProductName, cartQuantity);
        System.out.println("✅ Test Case 13 Passed Successfully!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
