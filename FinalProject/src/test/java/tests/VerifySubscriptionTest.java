package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.HomePage;

public class VerifySubscriptionTest {

    private WebDriver driver;
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");

        homePage = new HomePage(driver);
    }

    @Test
    public void testVerifySubscriptionInHomePage() {
        System.out.println("🚀 Starting Test Case 10: Verify Subscription in home page");

        // Step 3: Verify home page is visible successfully
        Assert.assertTrue(homePage.isHomePageVisible(), "❌ Home page is not visible!");

        // Step 4: Scroll down to footer
        homePage.scrollToFooter();

        // Step 5: Verify text 'SUBSCRIPTION'
        Assert.assertTrue(homePage.isSubscriptionTextVisible(), "❌ 'SUBSCRIPTION' text is not visible!");

        // Step 6: Enter email address in input and click arrow button
        String testEmail = "testuser" + System.currentTimeMillis() + "@mail.com";
        homePage.enterEmailAndSubscribe(testEmail);

        // Step 7: Verify success message
        Assert.assertTrue(homePage.isSubscriptionSuccessVisible(), "❌ Success message is not visible!");

        System.out.println("✅ Test Case 10 Passed Successfully!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
