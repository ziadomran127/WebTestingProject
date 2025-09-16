package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.HomePage;
import pages.LoginPage;
import pages.SignupPage;

public class SignupTest {

    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private SignupPage signupPage;
    private String email;

    // ✅ Static variables to share test data with other test classes (like LoginTest)
    public static String createdEmail;
    public static String createdPassword = "Test@123";

    @BeforeClass
    public void setup() {
        // Launch Chrome browser
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to the test website
        driver.get("http://automationexercise.com");

        // Initialize Page Object instances for interacting with different pages
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        signupPage = new SignupPage(driver);
        
        // Generate a unique email address using current timestamp to avoid duplicate accounts
        email = "johndoe" + System.currentTimeMillis() + "@test.com";
        createdEmail = email; // Store the generated email in the static variable for other tests
    }

    @Test
    public void testUserSignup() {
        // Step 3: Verify that the home page loaded successfully
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is NOT visible!");

        // Step 4: Click on the 'Signup / Login' navigation button
        homePage.clickSignupLogin();

        // Step 5: Verify that the signup section is displayed on the login page
        Assert.assertTrue(loginPage.isNewUserSignupVisible(), "'New User Signup!' is NOT visible!");

        // Step 6: Enter name and the generated email in the signup form
        loginPage.enterSignupDetails("John Doe", email);
        
        // Step 7: Click the Signup button to proceed to account creation
        loginPage.clickSignupButton();

        // Step 8: Verify that the account information form is displayed
        Assert.assertTrue(signupPage.isEnterAccountInformationVisible(), "'ENTER ACCOUNT INFORMATION' is NOT visible!");

        // Step 9: Fill in basic account details (title, password, date of birth)
        signupPage.fillAccountInformation("Mr", "Test@123", "5", "5", "1995");

        // Step 10: Opt-in to receive newsletters
        signupPage.selectNewsletterCheckbox();

        // Step 11: Opt-in to receive special offers from partners
        signupPage.selectOffersCheckbox();
        
        // Step 12: Fill in the address information for the account
        signupPage.fillAddressInformation(
            "John", 
            "Doe", 
            "Test Company", 
            "123 Main St", 
            "Apt 4B",
            "United States", 
            "California", 
            "Los Angeles", 
            "90001", 
            "1234567890"
        );
        
        // Step 13: Submit the complete registration form
        signupPage.clickCreateAccountButton();
        
        // Step 14: Verify that account creation was successful
        Assert.assertTrue(signupPage.isAccountCreatedVisible(), "'ACCOUNT CREATED!' is NOT visible!");
        // Click Continue to proceed to the main application
        signupPage.clickContinueButton();
        
        // Step 15: Verify that the user is successfully logged in after account creation
        Assert.assertTrue(signupPage.isLoggedInAsVisible(), "'Logged in as username' is NOT visible!");
    }

    @AfterClass
    public void teardown() {
        // Clean up: Close the browser after test execution
        if (driver != null) {
            driver.quit();
        }
    }
}