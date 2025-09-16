package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest {

    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testLoginFlow() {
        driver.get("http://automationexercise.com");

        homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible!");

        homePage.clickSignupLogin();

        loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginSectionVisible(), "'Login to your account' is not visible!");

        // ⬅️ استخدم الإيميل والباسورد اللي اتعملوا في SignupTest
        loginPage.enterLoginDetails(SignupTest.createdEmail, SignupTest.createdPassword);
        loginPage.clickLoginButton();

        // Assuming you already logged in successfully
Assert.assertTrue(loginPage.isLoggedInAsVisible(), "'Logged in as username' is NOT visible!");

// Delete the account
loginPage.clickDeleteAccountButton();

// Verify deletion and click continue
Assert.assertTrue(loginPage.isAccountDeletedVisible(), "'ACCOUNT DELETED!' is NOT visible!");
loginPage.clickContinueAfterDelete();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
