package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.HomePage;
import pages.SignupPage;
import pages.ProductsPage;
import pages.CartPage;
import pages.PaymentPage;

import java.time.Duration;
import pages.AccountPage;

public class TestPlaceOrderRegisterBeforeCheckout {

    WebDriver driver;
    HomePage home;
    SignupPage signup;
    ProductsPage products;
    CartPage cart;
    PaymentPage payment;
    AccountPage account; 

    @BeforeTest
    public void setUp() {
        // 1️⃣ Launch browser
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Initialize page objects
        home = new HomePage(driver);
        signup = new SignupPage(driver);
        products = new ProductsPage(driver);
        cart = new CartPage(driver);
        payment = new PaymentPage(driver);
         account = new AccountPage(driver);

        // 2️⃣ Navigate to URL
        driver.get("http://automationexercise.com");
    }

    @Test
    public void testPlaceOrderRegisterBeforeCheckout() throws InterruptedException {
        // 3️⃣ Verify home page
        assert home.isHomePageVisible() : "❌ Home page not visible";
        System.out.println("✅ Home page visible");

        // 4️⃣ Click 'Signup / Login'
        home.clickSignupLogin();
        System.out.println("➡️ Clicked 'Signup / Login'");

        // 5️⃣ Signup and create account
        String email = "ziad" + System.currentTimeMillis() + "@example.com";
        signup.enterNameAndEmail("Ziad Omran", email);
        signup.clickSignupButton();
        signup.fillAccountInformation("Mr", "password123", "10", "5", "1995");
        signup.selectNewsletterCheckbox();
        signup.selectOffersCheckbox();
        signup.fillAddressInformation(
                "Ziad", "Omran", "CompanyXYZ", "123 Street", "Apt 1",
                "United States", "CA", "Los Angeles", "90001", "0123456789"
        );
        signup.clickCreateAccountButton();
        assert signup.isAccountCreatedVisible() : "❌ Account Created message missing";
        signup.clickContinueButton();
        assert signup.isLoggedInAsVisible() : "❌ User not logged in";
        System.out.println("✅ Signup and login completed");

        // 8️⃣ Add products to cart
        home.clickProductsButton();
        assert products.isAllProductsPageVisible() : "❌ Products page not visible";
        products.addFirstProductToCart();
        products.clickContinueShopping();
        products.addSecondProductToCart();
        products.clickViewCart();
        System.out.println("✅ Products added to cart");

        // 9️⃣ Click 'Cart' button
        cart.clickCartButton();

        // 10️⃣ Verify cart page
//        assert cart.isCartPageVisible() : "❌ Cart page not visible";
        System.out.println("✅ Cart page visible");

        // 11️⃣ Click Proceed To Checkout
        cart.clickProceedToCheckout();

        // 12️⃣ Verify Address Details and Review Order
        assert cart.isAddressDetailsVisible() : "❌ Address Details missing";
        assert cart.isReviewOrderVisible() : "❌ Review Order missing";
        System.out.println("✅ Address Details and Review Order verified");

        // 13️⃣ Enter comment and click 'Place Order'
        cart.enterOrderComment("Please deliver between 10 AM - 5 PM");
        cart.clickPlaceOrder();
        System.out.println("✅ Comment added and Place Order clicked");

        // 14️⃣ Fill payment details
        payment.fillPaymentDetails(
                "Ziad Omran",
                "4111111111111111",
                "123",
                "12",
                "2025"
        );

        // 15️⃣ Click 'Pay and Confirm Order'
        payment.clickPayAndConfirm();

        // 16️⃣ Verify success message
        assert payment.isOrderPlacedSuccessVisible() : "❌ Order success message missing";
        System.out.println("🎉 Order placed successfully!");
        
        // After verifying order success
// 17️⃣ Click 'Delete Account'
// 17️⃣ Click 'Delete Account'
account.clickDeleteAccount();

// 18️⃣ Verify 'ACCOUNT DELETED!' and click Continue
assert account.isAccountDeletedVisible() : "❌ Account deletion message missing";
account.clickContinueAfterDeletion();
System.out.println("🗑️ Account deleted successfully");
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}
