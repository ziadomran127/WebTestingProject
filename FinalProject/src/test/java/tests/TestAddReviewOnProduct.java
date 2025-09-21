package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.ProductsPage;

import java.time.Duration;

public class TestAddReviewOnProduct {

    private WebDriver driver;
    private HomePage homePage;
    private ProductsPage productsPage;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Navigate to the website
        driver.get("http://automationexercise.com");

        // Initialize page objects
        homePage = new HomePage(driver);
        productsPage = new ProductsPage(driver);

        // Verify homepage
        Assert.assertTrue(homePage.isHomePageVisible(), "❌ Home page not visible");
        System.out.println("✅ Home page loaded successfully");
    }

    @Test
    public void testAddReviewOnProduct() {
        // Step 3: Click 'Products' button
        homePage.clickProductsButton();

        // Step 4: Verify ALL PRODUCTS page
        Assert.assertTrue(productsPage.isAllProductsPageVisible(), "❌ ALL PRODUCTS page not visible");
        System.out.println("✅ ALL PRODUCTS page is visible");

        // Step 5: Click on 'View Product' for first product
        productsPage.clickFirstProductViewButton();
        Assert.assertTrue(productsPage.isProductDetailVisible(), "❌ Product detail page not visible");
        System.out.println("✅ Navigated to product detail page");

        // Step 6: Add review
        String name = "Ziad Omran";
        String email = "ziad@example.com";
        String reviewText = "This product is excellent!";

        productsPage.enterReview(name, email, reviewText);
        productsPage.submitReview();

        Assert.assertTrue(productsPage.isReviewSubmittedMessageVisible(), "❌ Review submission message not visible");
        System.out.println("✅ Review submitted successfully");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("✅ Browser closed");
        }
    }
}
