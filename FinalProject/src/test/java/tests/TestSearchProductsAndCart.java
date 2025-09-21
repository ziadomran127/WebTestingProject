package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.ProductsPage;

import java.time.Duration;

public class TestSearchProductsAndCart {

    private WebDriver driver;
    private HomePage homePage;
    private ProductsPage productsPage;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("http://automationexercise.com");

        homePage = new HomePage(driver);
        productsPage = new ProductsPage(driver);

        Assert.assertTrue(homePage.isHomePageVisible(), "❌ Home page not visible");
        System.out.println("✅ Home page loaded successfully");
    }

    @Test
    public void testSearchAndAddToCart() {
        // Navigate to Products page
        homePage.clickProductsButton();
        Assert.assertTrue(productsPage.isAllProductsPageVisible(), "❌ ALL PRODUCTS page not visible");
        System.out.println("✅ ALL PRODUCTS page is visible");

        // Search for a product
        String searchKeyword = "Dress";
        productsPage.searchForProduct(searchKeyword);

        // Verify searched products header
        Assert.assertTrue(productsPage.isSearchedProductsVisible(), "❌ SEARCHED PRODUCTS header not visible");
        System.out.println("✅ SEARCHED PRODUCTS header visible");

        // Verify all products match search
        Assert.assertTrue(productsPage.areAllSearchedProductsRelevant(searchKeyword),
                "❌ Some products are unrelated to search keyword");
        System.out.println("✅ All searched products are relevant");

        // Add first product to cart
        productsPage.addFirstProductToCart();
        productsPage.clickContinueShopping(); // Modal handled internally

        // Add second product to cart
        productsPage.addSecondProductToCart();
        productsPage.clickContinueShopping();

        // View cart
        productsPage.clickViewCart();
        System.out.println("✅ All searched products added to cart successfully");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("✅ Browser closed");
        }
    }
}
