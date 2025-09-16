package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LogoutTest extends BaseTest {

    @Test
    public void testLogoutUser() {
        LoginPage loginPage = new LoginPage(driver);

        System.out.println("Step 3: Verify home page is visible");
        Assert.assertTrue(driver.getTitle().contains("Automation Exercise"),
                "❌ Home page is NOT visible!");

        System.out.println("Step 4: Click 'Signup / Login' button");
        driver.findElement(org.openqa.selenium.By.xpath("//a[contains(text(),'Signup / Login')]")).click();

        System.out.println("Step 5: Verify 'Login to your account' is visible");
        Assert.assertTrue(loginPage.isLoginSectionVisible(),
                "❌ 'Login to your account' is NOT visible!");

        System.out.println("Step 6: Enter correct email and password");
         loginPage.enterLoginDetails(SignupTest.createdEmail, SignupTest.createdPassword);

        System.out.println("Step 7: Click 'login' button");
        loginPage.clickLoginButton();

        System.out.println("Step 8: Verify 'Logged in as username' is visible");
        Assert.assertTrue(loginPage.isLoggedInAsVisible(),
                "❌ 'Logged in as username' is NOT visible!");

        System.out.println("Step 9: Click 'Logout' button");
        driver.findElement(org.openqa.selenium.By.xpath("//a[contains(text(),'Logout')]")).click();

        System.out.println("Step 10: Verify user is navigated to login page");
        Assert.assertTrue(loginPage.isLoginSectionVisible(),
                "❌ User is NOT navigated back to Login page after logout!");
    }
}
