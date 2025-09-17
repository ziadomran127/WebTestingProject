package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.HomePage;
import pages.LoginPage;

public class RegisterExistingUserTest {

    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;

    // Use an actual existing account from the website
    private String existingEmail = "existinguser@test.com";
    private String existingName = "John Doe";

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testRegisterWithExistingEmail() {
        // Step 3: Verify that home page is visible
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is NOT visible!");

        // Step 4: Click on 'Signup / Login' button
        homePage.clickSignupLogin();

        // Step 5: Verify 'New User Signup!' is visible
        Assert.assertTrue(loginPage.isNewUserSignupVisible(), "'New User Signup!' is NOT visible!");

        // Step 6: Enter already registered name and email
        loginPage.enterSignupDetails(existingName, existingEmail);

        // Step 7: Click 'Signup' button
        loginPage.clickSignupButton();

        // Step 8: Verify error message 'Email Address already exist!' is visible
        Assert.assertTrue(loginPage.isEmailAlreadyExistsErrorVisible(),
                "'Email Address already exist!' error is NOT visible!");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}