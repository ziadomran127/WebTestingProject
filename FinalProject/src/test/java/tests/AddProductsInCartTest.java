package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.HomePage;
import pages.ProductsPage;

import java.time.Duration;
import java.util.List;

public class AddProductsInCartTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;
    private ProductsPage productsPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        homePage = new HomePage(driver);
        productsPage = new ProductsPage(driver);
    }

    @Test
    public void testAddProductsInCart() throws InterruptedException {
        System.out.println("🚀 Starting Test Case 12: Add Products in Cart");

        // Step 3: Verify home page
        Assert.assertTrue(homePage.isHomePageVisible(), "❌ Home page not visible!");

        // Step 4: Click 'Products'
        homePage.clickProductsButton();
        Assert.assertTrue(productsPage.isAllProductsPageVisible(), "❌ Products page not visible!");

        // Step 5 & 6: Add first product + continue shopping
        productsPage.addFirstProductToCart();
        productsPage.clickContinueShopping();

        // Step 7: Add second product
        productsPage.addSecondProductToCart();

        // Step 8: View Cart
        productsPage.clickViewCart();

        // Step 9: Wait for 2 products in cart
        wait.until(ExpectedConditions.numberOfElementsToBe(
                By.xpath("//table[@id='cart_info_table']//tbody/tr"), 2));

        List<WebElement> cartRows = driver.findElements(By.xpath("//table[@id='cart_info_table']//tbody/tr"));
        Assert.assertEquals(cartRows.size(), 2, "❌ Not 2 products in cart!");
        System.out.println("🛒 Items found in cart: " + cartRows.size());

        System.out.println("✅ Test Case 12 Passed Successfully!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
