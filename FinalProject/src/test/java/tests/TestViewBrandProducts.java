package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.BrandsPage;

import java.time.Duration;
import org.openqa.selenium.By;

public class TestViewBrandProducts {

    private WebDriver driver;
    private BrandsPage brandsPage;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        brandsPage = new BrandsPage(driver);

        driver.get("http://automationexercise.com");
    }

    @Test
    public void testViewBrandProducts() {
        // Step 3: Click on 'Products' button
        driver.findElement(By.xpath("//a[@href='/products']")).click();
        System.out.println("✅ Clicked 'Products' button");

        // Step 4: Verify Brands section is visible
        Assert.assertTrue(brandsPage.isBrandsVisible(), "❌ Brands section not visible");
        System.out.println("✅ Brands section visible");

        // Step 5: Click on a brand (example: 'Polo')
        String firstBrand = "Polo";
        brandsPage.clickBrand(firstBrand);

        // Step 6: Verify navigation to brand page
        String brandTitle = brandsPage.getBrandPageTitle();
        Assert.assertTrue(brandTitle.contains(firstBrand.toUpperCase()), 
                "❌ User is not on " + firstBrand + " brand page");
        System.out.println("✅ User navigated to '" + firstBrand + "' brand page");

        // Step 7: Click on another brand (example: 'Madame')
        String secondBrand = "Madame";
        brandsPage.clickBrand(secondBrand);

        // Step 8: Verify navigation to second brand page
        String secondBrandTitle = brandsPage.getBrandPageTitle();
        Assert.assertTrue(secondBrandTitle.contains(secondBrand.toUpperCase()), 
                "❌ User is not on " + secondBrand + " brand page");
        System.out.println("✅ User navigated to '" + secondBrand + "' brand page");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
