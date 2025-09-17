package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;

    // 🔹 Locators
    private By logo = By.xpath("/html/body/header/div/div/div/div[1]/div/a/img");
    private By signupLoginLink = By.xpath("/html/body/header/div/div/div/div[2]/div/ul/li[4]/a");

    // 🏗️ Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // ✅ Verify home page by title
    public boolean isHomePageVisibleByTitle() {
        String expectedTitle = "Automation Exercise";
        return driver.getTitle().equals(expectedTitle);
    }

    // ✅ Verify home page by logo
    public boolean isHomePageVisibleByLogo() {
        return driver.findElement(logo).isDisplayed();
    }

    // ✅ Combined check
    public boolean isHomePageVisible() {
        return isHomePageVisibleByTitle() && isHomePageVisibleByLogo();
    }

    // 🖱️ Click on "Signup / Login"
    public void clickSignupLogin() {
        driver.findElement(signupLoginLink).click();
    }
    
    public void clickContactUs() {
    driver.findElement(By.xpath("//a[text()=' Contact us']")).click();
}
}
