package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.HomePage;
import pages.ContactUsPage;

public class ContactUsTest {

    private WebDriver driver;
    private HomePage homePage;
    private ContactUsPage contactUsPage;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");

        homePage = new HomePage(driver);
        contactUsPage = new ContactUsPage(driver);
    }

    @Test
    public void testContactUsForm() {
        // Step 3: تحقق من الصفحة الرئيسية
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is NOT visible!");

        // Step 4: الضغط على زر Contact Us
        homePage.clickContactUs();

        // Step 5: تحقق من ظهور GET IN TOUCH
        Assert.assertTrue(contactUsPage.isGetInTouchVisible(), "'GET IN TOUCH' is NOT visible!");

        // Step 6: إدخال البيانات في النموذج
        contactUsPage.fillContactForm(
            "John Doe",
            "john@test.com",
            "Automation Testing",
            "This is a test message from Selenium"
        );

        // Step 7: رفع ملف
        contactUsPage.uploadFile("E:\\Movies\\oppenheimer\\1257951.jpeg");

        // Step 8: الضغط على زر Submit
        contactUsPage.clickSubmit();

        // Step 9: التعامل مع Alert - الضغط على OK
        Alert alert = driver.switchTo().alert();
        alert.accept();

        // Step 10: التحقق من رسالة النجاح
        Assert.assertTrue(contactUsPage.isSuccessMessageVisible(),
                "'Success! Your details have been submitted successfully.' is NOT visible!");

        // Step 11: الضغط على زر Home والتأكد من الرجوع للصفحة الرئيسية
        contactUsPage.clickHomeButton();
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is NOT visible after clicking Home!");
    }

    @AfterClass
    public void teardown() {
        //if (driver != null) {
          //  driver.quit();
        //}
    }
}
