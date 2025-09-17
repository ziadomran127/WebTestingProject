package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.TestCasesPage;

public class VerifyTestCasesPageTest extends BaseTest {

    @Test
    public void testVerifyTestCasesPage() {
        // Step 3: Verify that home page is visible successfully
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is NOT visible!");

        // Step 4: Click on 'Test Cases' button
        homePage.clickTestCasesButton();

        // Step 5: Verify user is navigated to test cases page successfully
        TestCasesPage testCasesPage = new TestCasesPage(driver);
        Assert.assertTrue(testCasesPage.isTestCasesPageVisible(), "User is NOT on the Test Cases page!");
    }
}
