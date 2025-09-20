package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.PaymentPage;
import pages.ProductsPage;
import pages.SignupPage;
import pages.AccountPage;

public class PlaceOrderRegisterWhileCheckoutTest extends BaseTest {

    @Test
    public void testPlaceOrderRegisterWhileCheckout() throws InterruptedException {
        // Initialize page objects
        HomePage home = new HomePage(driver);
        ProductsPage products = new ProductsPage(driver);
        CartPage cart = new CartPage(driver);
        SignupPage signup = new SignupPage(driver);
        PaymentPage payment = new PaymentPage(driver);
         AccountPage account = new AccountPage(driver);

        // STEP 1: Verify home page
        Assert.assertTrue(home.isHomePageVisible(), "Home page should be visible");

        // STEP 2: Go to products page
        home.clickProductsButton();
        Assert.assertTrue(products.isAllProductsPageVisible(), "Products page should be visible");

        // STEP 3: Add products to cart
        products.addFirstProductToCart();
        products.clickContinueShopping();
        products.addSecondProductToCart();
        products.clickViewCart();

        // STEP 4: Click Cart button and verify cart page
        cart.clickCartButton();
       // Assert.assertTrue(cart.isCartPageVisible(), "Cart page should be visible");

        // STEP 5: Click Proceed To Checkout
        cart.clickProceedToCheckout();

        // STEP 6: Click Register / Login during checkout
        cart.clickRegisterOrLogin();

        // STEP 7: Fill signup form
        String username = "ZiadTest";
        String email = "ziadtest" + System.currentTimeMillis() + "@example.com";
        signup.enterNameAndEmail(username, email);
        signup.clickSignupButton();

        // STEP 8: Verify account information page
        Assert.assertTrue(signup.isEnterAccountInformationVisible(), "Account info page should be visible");

        // STEP 9: Fill account details
        signup.fillAccountInformation("Mr", "Test@123", "1", "1", "2000");
        signup.selectNewsletterCheckbox();
        signup.selectOffersCheckbox();

        // STEP 10: Fill address information
        signup.fillAddressInformation(
                "Ziad", "Omran", "CompanyX", "123 Street", "Apt 4",
                "United States", "CA", "Los Angeles", "90001", "1234567890"
        );

        // STEP 11: Create account and verify success
        signup.clickCreateAccountButton();
        Assert.assertTrue(signup.isAccountCreatedVisible(), "Account created message should be visible");
        signup.clickContinueButton();
        Assert.assertTrue(signup.isLoggedInAsVisible(), "User should be logged in");

        // STEP 12: Click Cart button again
        cart.clickCartButton();
//        Assert.assertTrue(cart.isCartPageVisible(), "Cart page should be visible after login");

        // STEP 13: Click Proceed To Checkout
        cart.clickProceedToCheckout();

        // STEP 14: Verify Address Details and Review Your Order
        Assert.assertTrue(cart.isAddressDetailsVisible(), "Address Details section should be visible");
        Assert.assertTrue(cart.isReviewOrderVisible(), "Review Your Order section should be visible");
        // STEP 15: Enter comment and place order
        cart.enterOrderComment("Please deliver between 9 AM - 5 PM");
        cart.clickPlaceOrder();
        
        // STEP 16: Fill payment details
payment.fillPaymentDetails("Ziad Omran", "4111111111111111", "123", "12", "2025");

// STEP 17: Click Pay and Confirm Order
payment.clickPayAndConfirm();

// STEP 18: Verify order success message
Assert.assertTrue(payment.isOrderPlacedSuccessVisible(), "Order success message should be displayed");
        
        System.out.println("✅ Test completed successfully: Place Order with Register while Checkout");
        
   // STEP 19: Delete account
        account.clickDeleteAccount();

        // STEP 20: Verify deletion and click continue
        Assert.assertTrue(account.isAccountDeletedVisible(), "Account deleted message should be visible");
        account.clickContinueAfterDeletion();

        System.out.println("✅ Test completed successfully: Place Order with Register while Checkout + Account Deletion");
    }
}