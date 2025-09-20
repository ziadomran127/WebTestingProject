package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.HomePage;
import pages.CartPage;

public class VerifySubscriptionInCartPageTest {

    private WebDriver driver;
    private HomePage homePage;
    private CartPage cartPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");

        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    public void testVerifySubscriptionInCartPage() {
        System.out.println(" Starting Test Case 11: Verify Subscription in Cart page");

        // Step 3: Verify home page is visible successfully
        Assert.assertTrue(homePage.isHomePageVisible(), "❌ Home page is not visible!");

        // Step 4: Click 'Cart' button
        cartPage.clickCartButton();

        // Step 5: Scroll down to footer
        cartPage.scrollToFooter();

        // Step 6: Verify text 'SUBSCRIPTION'
        Assert.assertTrue(homePage.isSubscriptionTextVisible(), "❌ 'SUBSCRIPTION' text is not visible!");

        // Step 7: Enter email and click arrow
        String testEmail = "testuser" + System.currentTimeMillis() + "@mail.com";
        homePage.enterEmailAndSubscribe(testEmail);

        // Step 8: Verify success message is visible
        Assert.assertTrue(homePage.isSubscriptionSuccessVisible(), "❌ Success message is not visible!");

        System.out.println(" Test Case 11 Passed Successfully!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
