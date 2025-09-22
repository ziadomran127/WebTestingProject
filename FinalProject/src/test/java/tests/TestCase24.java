package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.ProductsPage;
import pages.SignupPage;
import pages.AccountPage;
import pages.PaymentPage;
import framework.SeleniumFrameWork;

import java.time.Duration;

public class TestCase24 {
    private WebDriver driver;
    private SeleniumFrameWork sf;
    private ProductsPage productsPage;
    private SignupPage signupPage;
    private AccountPage accountPage;
    private PaymentPage paymentPage;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        sf = new SeleniumFrameWork(driver);
        productsPage = new ProductsPage(driver);
        signupPage = new SignupPage(driver);
        accountPage = new AccountPage(driver);
        paymentPage = new PaymentPage(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void downloadInvoiceAfterPurchase() {
        // 1. Launch browser & 2. Navigate to URL
        driver.get("http://automationexercise.com");

        // 3. Verify home page visible
        Assert.assertTrue(driver.findElement(By.cssSelector(".features_items")).isDisplayed(),
                "❌ Home page is NOT visible");

        // 4. Add products to cart
        driver.findElement(By.xpath("//a[text()=' Products']")).click();
        Assert.assertTrue(productsPage.isAllProductsPageVisible(), "❌ Products page not visible");

        productsPage.addFirstProductToCart();
        productsPage.clickContinueShopping();
        productsPage.addSecondProductToCart();

        // 5. Click 'Cart' button
        productsPage.clickViewCart();

        // 6. Verify cart page displayed
        Assert.assertTrue(driver.findElement(By.xpath("//li[text()='Shopping Cart']")).isDisplayed(),
                "❌ Cart page not displayed");

        // 7. Click 'Proceed To Checkout'
        driver.findElement(By.xpath("//a[text()='Proceed To Checkout']")).click();

        // 8. Click 'Register / Login'
        driver.findElement(By.xpath("//u[text()='Register / Login']")).click();

        // 9. Fill signup form
        String name = "Ziad";
        String email = "ziad" + System.currentTimeMillis() + "@test.com";

        signupPage.enterNameAndEmail(name, email);
        signupPage.clickSignupButton();

        // 10. Verify 'Enter Account Information'
        Assert.assertTrue(signupPage.isEnterAccountInformationVisible(),
                "❌ 'Enter Account Information' not visible");

        // Fill account info
        signupPage.fillAccountInformation("Mr", "123456", "1", "1", "2000");
        signupPage.selectNewsletterCheckbox();
        signupPage.selectOffersCheckbox();

        // Fill address info
        signupPage.fillAddressInformation(
                "Ziad", "Omran", "TestCompany", "Street 123", "Block A",
                "Canada", "Ontario", "Toronto", "12345", "01000000000"
        );

        // Create account
        signupPage.clickCreateAccountButton();

        // 10. Verify 'ACCOUNT CREATED!' and click 'Continue'
        Assert.assertTrue(signupPage.isAccountCreatedVisible(), "❌ Account not created!");
        signupPage.clickContinueButton();

        // 11. Verify 'Logged in as username'
        Assert.assertTrue(signupPage.isLoggedInAsVisible(), "❌ 'Logged in as username' not visible");

        // 12. Click 'Cart' button again
        productsPage.clickViewCart();

        // 13. Click 'Proceed To Checkout'
        driver.findElement(By.xpath("//a[text()='Proceed To Checkout']")).click();

        // 14. Verify Address & Order review
        Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='Address Details']")).isDisplayed(),
                "❌ Address details not visible");
        Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='Review Your Order']")).isDisplayed(),
                "❌ Review Order not visible");

        // 15. Enter comment & Place Order
        driver.findElement(By.name("message")).sendKeys("Please deliver quickly");
        driver.findElement(By.xpath("//a[text()='Place Order']")).click();

        // 16. Enter payment details
        paymentPage.fillPaymentDetails("Ziad Omran", "4111111111111111", "123", "12", "2025");

        // 17. Click Pay and Confirm Order
        paymentPage.clickPayAndConfirm();

        // 18. Verify success message
        Assert.assertTrue(paymentPage.isOrderPlacedSuccessVisible(),
                "❌ Order success message not visible");

        // === EXTRA Step: Download Invoice ===
        driver.findElement(By.xpath("//a[text()='Download Invoice']")).click();
        System.out.println("📥 Invoice downloaded successfully");

        // Cleanup account
        accountPage.clickDeleteAccount();
        Assert.assertTrue(accountPage.isAccountDeletedVisible(), "❌ Account not deleted");
        accountPage.clickContinueAfterDeletion();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
