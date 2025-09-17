package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage {
    private WebDriver driver;

    private By getInTouchTitle = By.xpath("//h2[text()='Get In Touch']");
    private By nameField = By.name("name");
    private By emailField = By.name("email");
    private By subjectField = By.name("subject");
    private By messageField = By.id("message");
    private By uploadFileInput = By.name("upload_file");
    private By submitButton = By.name("submit");
    private By successMessage = By.xpath("//div[@class='status alert alert-success']");
    private By homeButton = By.xpath("//a[@class='btn btn-success']");

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isGetInTouchVisible() {
        return driver.findElement(getInTouchTitle).isDisplayed();
    }

    public void fillContactForm(String name, String email, String subject, String message) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(subjectField).sendKeys(subject);
        driver.findElement(messageField).sendKeys(message);
    }

    public void uploadFile(String filePath) {
        driver.findElement(uploadFileInput).sendKeys(filePath);
    }

    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }

    public boolean isSuccessMessageVisible() {
        return driver.findElement(successMessage).isDisplayed();
    }

    public void clickHomeButton() {
        driver.findElement(homeButton).click();
    }
}
