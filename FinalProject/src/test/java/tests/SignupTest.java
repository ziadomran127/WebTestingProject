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

    @BeforeClass
    public void setup() {
        // Launch browser
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to URL
        driver.get("http://automationexercise.com");

        // Initialize page objects
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        signupPage = new SignupPage(driver);
        
        // Generate unique email
        email = "johndoe" + System.currentTimeMillis() + "@test.com";
    }

    @Test
    public void testUserSignup() {
        // Step 3: Verify home page is visible
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is NOT visible!");

        // Step 4: Click on 'Signup / Login'
        homePage.clickSignupLogin();

        // Step 5: Verify 'New User Signup!' is visible
        Assert.assertTrue(loginPage.isNewUserSignupVisible(), "'New User Signup!' is NOT visible!");

        // Step 6: Enter name and email
        loginPage.enterSignupDetails("John Doe", email);
        
        // Step 7: Click Signup button
        loginPage.clickSignupButton();

        // Step 8: Verify 'ENTER ACCOUNT INFORMATION' is visible
        Assert.assertTrue(signupPage.isEnterAccountInformationVisible(), "'ENTER ACCOUNT INFORMATION' is NOT visible!");

        // Step 9: Fill details (Title, Password, DOB)
        signupPage.fillAccountInformation("Mr", "Test@123", "5", "5", "1995");

        // Step 10: Select newsletter checkbox
        signupPage.selectNewsletterCheckbox();

        // Step 11: Select offers checkbox
        signupPage.selectOffersCheckbox();
        
        // Step 12: Fill address information
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
        
        // Step 13: Click 'Create Account' button
        signupPage.clickCreateAccountButton();
        
        // Step 14: Verify that 'ACCOUNT CREATED!' is visible and click 'Continue'
        Assert.assertTrue(signupPage.isAccountCreatedVisible(), "'ACCOUNT CREATED!' is NOT visible!");
        signupPage.clickContinueButton();
        
        // Step 15: Verify that 'Logged in as username' is visible
        Assert.assertTrue(signupPage.isLoggedInAsVisible(), "'Logged in as username' is NOT visible!");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}