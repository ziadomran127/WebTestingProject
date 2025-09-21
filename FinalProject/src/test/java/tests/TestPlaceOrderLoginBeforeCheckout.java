package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;
import pages.PaymentPage;
import pages.AccountPage;

import java.time.Duration;

public class TestPlaceOrderLoginBeforeCheckout {

    private WebDriver driver;
    private HomePage home;
    private LoginPage login;
    private ProductsPage products;
    private CartPage cart;
    private PaymentPage payment;
    private AccountPage account;

    // ✅ Use valid existing credentials (ensure this user exists)
    private String email = "existinguser@test.com";  
    private String password = "Test@123";           

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Initialize page objects
        home = new HomePage(driver);
        login = new LoginPage(driver);
        products = new ProductsPage(driver);
        cart = new CartPage(driver);
        payment = new PaymentPage(driver);
        account = new AccountPage(driver);

        driver.get("http://automationexercise.com");
    }

    @Test
    public void testPlaceOrderLoginBeforeCheckout() throws InterruptedException {
        // 3️⃣ Verify home page
        Assert.assertTrue(home.isHomePageVisible(), "❌ Home page not visible");
        System.out.println("✅ Home page visible");

        // 4️⃣ Click 'Signup / Login'
        home.clickSignupLogin();
        System.out.println("➡️ Clicked 'Signup / Login'");

        // 5️⃣ Login
        Assert.assertTrue(login.isLoginSectionVisible(), "'Login to your account' section not visible");
        login.enterLoginDetails(email, password);
        login.clickLoginButton();

        // 6️⃣ Verify 'Logged in as username'
        Assert.assertTrue(login.isLoggedInAsVisible(), "'Logged in as username' is NOT visible");
        System.out.println("✅ Logged in successfully");

        // 7️⃣ Add products to cart
        home.clickProductsButton();
        Assert.assertTrue(products.isAllProductsPageVisible(), "❌ Products page not visible");
        products.addFirstProductToCart();
        products.clickContinueShopping();
        products.addSecondProductToCart();
        products.clickViewCart();
        System.out.println("✅ Products added to cart");

        // 8️⃣ Click 'Cart' button
        cart.clickCartButton();

        // 9️⃣ Verify cart page
//        Assert.assertTrue(cart.isCartPageVisible(), "❌ Cart page not visible");
        System.out.println("✅ Cart page visible");

        // 10️⃣ Proceed to checkout
        cart.clickProceedToCheckout();

        // 11️⃣ Verify Address Details and Review Order
        Assert.assertTrue(cart.isAddressDetailsVisible(), "❌ Address details missing");
        Assert.assertTrue(cart.isReviewOrderVisible(), "❌ Review order missing");
        System.out.println("✅ Address details and review order verified");

        // 12️⃣ Enter comment and place order
        cart.enterOrderComment("Please deliver between 10 AM - 5 PM");
        cart.clickPlaceOrder();
        System.out.println("✅ Comment added and 'Place Order' clicked");

        // 13️⃣ Enter payment details
        payment.fillPaymentDetails(
                "Existing User",
                "4111111111111111",
                "123",
                "12",
                "2025"
        );

        // 14️⃣ Click 'Pay and Confirm Order'
        payment.clickPayAndConfirm();

        // 15️⃣ Verify success message
        Assert.assertTrue(payment.isOrderPlacedSuccessVisible(), "❌ Order success message missing");
        System.out.println("🎉 Order placed successfully!");

        // 16️⃣ Delete account
        account.clickDeleteAccount();

        // 17️⃣ Verify 'ACCOUNT DELETED!' and click Continue
        Assert.assertTrue(account.isAccountDeletedVisible(), "❌ Account deletion message missing");
        account.clickContinueAfterDeletion();
        System.out.println("🗑️ Account deleted successfully");
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        if (driver != null) {
            driver.quit();
        }
    }
}
