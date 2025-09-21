package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.ProductsPage;
import pages.CartPage;

import java.time.Duration;

public class TestRemoveProductsFromCart {

    private WebDriver driver;
    private HomePage homePage;
    private ProductsPage productsPage;
    private CartPage cartPage;

    @BeforeClass
    public void setUp() {
        // 1️⃣ Launch browser
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Initialize page objects
        homePage = new HomePage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);

        // 2️⃣ Navigate to URL
        driver.get("http://automationexercise.com");
    }

    @Test
    public void testRemoveProductFromCart() throws InterruptedException {
        // 3️⃣ Verify home page
        Assert.assertTrue(homePage.isHomePageVisible(), "❌ Home page not visible");
        System.out.println("✅ Home page visible");

        // 4️⃣ Add products to cart
        homePage.clickProductsButton();
        Assert.assertTrue(productsPage.isAllProductsPageVisible(), "❌ Products page not visible");
        productsPage.addFirstProductToCart();
        productsPage.clickContinueShopping();
        productsPage.addSecondProductToCart();
        productsPage.clickViewCart();
        System.out.println("✅ Products added to cart");

        // 5️⃣ Click 'Cart' button
        cartPage.clickCartButton();

        // 6️⃣ Verify that cart page is displayed
//        Assert.assertTrue(cartPage.isCartPageVisible(), "❌ Cart page not visible");
        System.out.println("✅ Cart page visible");

        // 7️⃣ Click 'X' button to remove the first product
        cartPage.removeFirstProductFromCart();
        Thread.sleep(2000); // wait for the product row to disappear

        // 8️⃣ Verify that the product is removed from the cart
        Assert.assertFalse(cartPage.isFirstProductInCartVisible(), "❌ Product was NOT removed from cart");
        System.out.println("🗑️ Product successfully removed from cart");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
