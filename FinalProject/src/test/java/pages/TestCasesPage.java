package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import framework.SeleniumFrameWork;

public class TestCasesPage {
    private WebDriver driver;
    private SeleniumFrameWork sf;

    private By testCasesTitle = By.xpath("/html/body/section/div/div[1]/div/h2/b");

    public TestCasesPage(WebDriver driver) {
        this.driver = driver;
        this.sf = new SeleniumFrameWork(driver);
    }

    public boolean isTestCasesPageVisible() {
        sf.Edges_explicitWait(testCasesTitle, 3); 
        return driver.findElement(testCasesTitle).isDisplayed();
    }
}
