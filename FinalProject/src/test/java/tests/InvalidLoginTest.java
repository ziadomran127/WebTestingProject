package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class InvalidLoginTest extends BaseTest {

    @Test
    public void testInvalidLogin() {
        WebDriver driver = getDriver();

        // Step 1 & 2: Launch browser and navigate to URL
        driver.get("http://automationexercise.com");

        HomePage homePage = new HomePage(driver);

        // Step 3: Verify that home page is visible successfully
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible!");

        // Step 4: Click on 'Signup / Login' button
        homePage.clickSignupLogin();

        LoginPage loginPage = new LoginPage(driver);

        // Step 5: Verify 'Login to your account' is visible
        Assert.assertTrue(loginPage.isLoginSectionVisible(), "'Login to your account' is not visible!");

        // Step 6: Enter incorrect email address and password
        loginPage.enterLoginDetails("wrongemail@example.com", "wrongpassword");

        // Step 7: Click 'login' button
        loginPage.clickLoginButton();

        // Step 8: Verify error 'Your email or password is incorrect!' is visible
        Assert.assertTrue(loginPage.isLoginErrorVisible(), "Error message is not visible!");
    }
}
