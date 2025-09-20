package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;

    private By cartButton = By.xpath("//a[@href='/view_cart']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCartButton() {
        driver.findElement(cartButton).click();
        System.out.println("🛒 Clicked on Cart button");
    }

    public void scrollToFooter() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        System.out.println("📜 Scrolled to footer on Cart page");
    }
}
