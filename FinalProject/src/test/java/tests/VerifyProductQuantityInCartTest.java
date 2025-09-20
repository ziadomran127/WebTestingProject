package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.HomePage;
import pages.ProductsPage;

import java.time.Duration;

public class VerifyProductQuantityInCartTest {

    private WebDriver driver;
    private HomePage homePage;
    private ProductsPage productsPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        homePage = new HomePage(driver);
        productsPage = new ProductsPage(driver);
    }

    @Test
    public void testProductQuantityInCart() throws InterruptedException {
        System.out.println("🚀 Starting Test Case 13: Verify Product Quantity in Cart");

        // Step 3: Verify home page
        Assert.assertTrue(homePage.isHomePageVisible(), "❌ Home page is not visible!");

        // Step 4: Click 'View Product' for first product
        productsPage.clickFirstProductViewButton();

        // Step 5: Verify product detail page is opened
        Assert.assertTrue(productsPage.isProductDetailVisible(), "❌ Product detail page is not visible!");

        // Step 6: Increase quantity to 4
        productsPage.setQuantity(4);

        // Step 7: Click 'Add to cart'
        productsPage.clickAddToCartFromDetail();

        // Step 8: Click 'View Cart' button
        productsPage.clickViewCart();

        // Step 9: Verify that product quantity in cart is 4
        int quantityInCart = productsPage.getQuantityFromCart();
        Assert.assertEquals(quantityInCart, 4, "❌ Product quantity in cart is not 4!");
        System.out.println("✅ Product quantity in cart verified successfully: " + quantityInCart);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
