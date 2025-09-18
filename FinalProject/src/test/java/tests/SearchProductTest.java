package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.HomePage;
import pages.ProductsPage;

public class SearchProductTest {

    private WebDriver driver;
    private HomePage homePage;
    private ProductsPage productsPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");

        homePage = new HomePage(driver);
        productsPage = new ProductsPage(driver);
    }

    @Test
    public void testSearchProduct() {
        System.out.println("🚀 Starting Test Case 9: Search Product");

        // Step 3: Verify home page is visible successfully
        Assert.assertTrue(homePage.isHomePageVisible(), "❌ Home page is not visible!");

        // Step 4: Click on 'Products' button
        homePage.clickProductsButton();

        // Step 5: Verify user is navigated to ALL PRODUCTS page
        Assert.assertTrue(productsPage.isAllProductsPageVisible(), "❌ Not on All Products page!");
        Assert.assertTrue(productsPage.isProductsListVisible(), "❌ No products are visible on All Products page!");

        // Step 6: Enter product name in search input and click search button
        String productName = "Top";
        productsPage.searchForProduct(productName);

        // Step 7 + 8: Verify 'SEARCHED PRODUCTS' is visible and products are displayed
        Assert.assertTrue(productsPage.isSearchedProductsVisible(), "❌ Searched products are not visible!");
        Assert.assertTrue(productsPage.areAllSearchedProductsRelevant(productName),
        "❌ Not all displayed products are related to the search keyword!");


        System.out.println("✅ Test Case 9 Passed Successfully!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
