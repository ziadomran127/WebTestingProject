package tests;

import framework.SeleniumFrameWork;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    protected static WebDriver driver;
    protected static SeleniumFrameWork edges;

    @BeforeSuite
    public void globalSetUp() {
        edges = new SeleniumFrameWork(driver);
        edges.Edges_initializeBrowser();
        edges.Edges_navigateToURL("https://automationexercise.com");
        driver = edges.getDriver();
        System.out.println("✅ Browser launched and navigated to homepage.");
    }

    public WebDriver getDriver() {
        return driver;
    }

    @AfterSuite
    public void globalTearDown() {
        if (edges != null) {
            // edges.Edges_closeBrowser();
            System.out.println("✅ Browser closed after all tests.");
        }
    }
}
