package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.ProductsPage;
import pages.CartPage;

import java.time.Duration;

public class TestAddRecommendedItemsToCart {

    private WebDriver driver;
    private HomePage homePage;
    private ProductsPage products;
    private CartPage cart;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("http://automationexercise.com");
        homePage = new HomePage(driver);
        products = new ProductsPage(driver);
        cart = new CartPage(driver);

        Assert.assertTrue(homePage.isHomePageVisible(), "❌ Home page not visible");
        System.out.println("✅ Home page loaded");
    }

    @Test
    public void testAddRecommendedItemToCart() throws InterruptedException {
        // Scroll to footer and lazy load Recommended Items
        homePage.scrollToFooter();
        Thread.sleep(1000);

        homePage.scrollToRecommendedItems();

        // Verify Recommended Items section
        Assert.assertTrue(homePage.isRecommendedItemsVisible(), "❌ Recommended Items not visible");
        System.out.println("✅ Recommended Items section is visible");

        // Add first recommended item and capture its name
        //String productName = homePage.addFirstRecommendedItemToCart();

        String expectedProductName = homePage.addFirstRecommendedItemToCart();
        // Click View Cart
        products.clickViewCart();

         // Verify product in cart
    Assert.assertTrue(cart.isProductInCart(expectedProductName),
            "❌ Recommended product not found in cart: expected [" + expectedProductName + "]");
    System.out.println("✅ Recommended product is displayed in cart");

        System.out.println("✅ Test complete: Added first recommended item to cart successfully");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("✅ Browser closed");
        }
    }
}
