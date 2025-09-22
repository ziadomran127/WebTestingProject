package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.ProductsPage;
import pages.SignupPage;
import pages.AccountPage;
import framework.SeleniumFrameWork;

import java.time.Duration;

public class TestCase23 {
    private WebDriver driver;
    private SeleniumFrameWork sf;
    private ProductsPage productsPage;
    private SignupPage signupPage;
    private AccountPage accountPage;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        sf = new SeleniumFrameWork(driver);
        productsPage = new ProductsPage(driver);
        signupPage = new SignupPage(driver);
         accountPage = new AccountPage(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void verifyAddressDetailsInCheckout() {
        // 1. Launch browser & 2. Navigate to URL
        driver.get("http://automationexercise.com");

        // 3. Verify home page visible
        Assert.assertTrue(driver.findElement(By.cssSelector(".features_items")).isDisplayed(),
                "❌ Home page is NOT visible");

        // 4. Click 'Signup / Login' button
        driver.findElement(By.xpath("//a[text()=' Signup / Login']")).click();

        // 5. Fill details in Signup
        String name = "Ziad";
        String email = "ziad" + System.currentTimeMillis() + "@test.com";

        signupPage.enterNameAndEmail(name, email);
        signupPage.clickSignupButton();

        // 6. Verify 'Enter Account Information'
        Assert.assertTrue(signupPage.isEnterAccountInformationVisible(),
                "❌ 'Enter Account Information' not visible");

        // 7. Fill account information
        signupPage.fillAccountInformation("Mr", "123456", "1", "1", "2000");
        signupPage.selectNewsletterCheckbox();
        signupPage.selectOffersCheckbox();

        // 8. Fill address info
        signupPage.fillAddressInformation(
                "Ziad", "Omran", "TestCompany", "Street 123", "Block A",
                "Canada", "Ontario", "Toronto", "12345", "01000000000"
        );

        // 9. Create account
        signupPage.clickCreateAccountButton();

        // 10. Verify 'ACCOUNT CREATED!' and click 'Continue'
        Assert.assertTrue(signupPage.isAccountCreatedVisible(), "❌ Account not created!");
        signupPage.clickContinueButton();

        // 11. Verify 'Logged in as username'
        Assert.assertTrue(signupPage.isLoggedInAsVisible(), "❌ 'Logged in as username' not visible");

        // 12. Add products to cart
        driver.findElement(By.xpath("//a[text()=' Products']")).click();
        Assert.assertTrue(productsPage.isAllProductsPageVisible(), "❌ Products page not visible");

        productsPage.addFirstProductToCart();
           productsPage.clickContinueShopping();
        productsPage.addSecondProductToCart();
        // 13. Click 'Cart' button
        productsPage.clickViewCart();

        // 14. Verify cart page displayed
        Assert.assertTrue(driver.findElement(By.xpath("//li[text()='Shopping Cart']")).isDisplayed(),
                "❌ Cart page not displayed");

        // 15. Click 'Proceed To Checkout'
        driver.findElement(By.xpath("//a[text()='Proceed To Checkout']")).click();

        // 16. Verify Address Details
        Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='Address Details']")).isDisplayed(),
                "❌ Address details not visible on checkout page");

        // Extra: check Delivery and Billing addresses contain signup info
        String deliveryAddress = driver.findElement(By.id("address_delivery")).getText();
        String billingAddress = driver.findElement(By.id("address_invoice")).getText();

        Assert.assertTrue(deliveryAddress.contains("Ziad"), "❌ Delivery address does not contain first name");
        Assert.assertTrue(deliveryAddress.contains("Street 123"), "❌ Delivery address does not match");
        Assert.assertTrue(billingAddress.contains("Ziad"), "❌ Billing address does not contain first name");
        Assert.assertTrue(billingAddress.contains("Street 123"), "❌ Billing address does not match");
        
       accountPage.clickDeleteAccount();
Assert.assertTrue(accountPage.isAccountDeletedVisible());
accountPage.clickContinueAfterDeletion();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
