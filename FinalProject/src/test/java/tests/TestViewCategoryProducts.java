package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CategoryPage;

import java.time.Duration;

public class TestViewCategoryProducts {

    private WebDriver driver;
    private CategoryPage categoryPage;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        categoryPage = new CategoryPage(driver);

        driver.get("http://automationexercise.com");
    }

    @Test
    public void testViewCategoryProducts() {
        // Step 3: Verify categories are visible
        Assert.assertTrue(categoryPage.isCategoriesVisible(), "❌ Categories section not visible");
        System.out.println("✅ Categories section visible");

        // Step 4 & 5: Click 'Women' category and 'Dress' subcategory
        categoryPage.clickSubCategory("Women", "Dress");

        // Step 6: Verify Women category page is displayed
        String womenTitle = categoryPage.getCategoryPageTitle();
        Assert.assertTrue(womenTitle.contains("WOMEN - DRESS PRODUCTS"), 
                "❌ Women category page title mismatch");
        System.out.println("✅ Women category page displayed with correct title: " + womenTitle);

        // Step 7 & 8: Click 'Men' category and 'Tshirts' subcategory
        categoryPage.clickSubCategory("Men", "Tshirts");

        // Verify Men subcategory page is displayed
        String menTitle = categoryPage.getCategoryPageTitle();
        Assert.assertTrue(menTitle.contains("MEN - TSHIRTS PRODUCTS"), 
                "❌ Men sub-category page title mismatch");
        System.out.println("✅ Men sub-category page displayed successfully: " + menTitle);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
