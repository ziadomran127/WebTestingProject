package framework;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Function;

public class SeleniumFrameWork {
    private WebDriver driver;
    private WebDriverWait explicitWait;
    private final int DEFAULT_TIMEOUT = 10;

      // ✅ لازم تضيف الكونستركتور ده
    public SeleniumFrameWork(WebDriver driver) {
        this.driver = driver;
    }
     
    // Initialize the browser
    public void Edges_initializeBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        System.out.println("Edges: Browser Initialized.");
    }

    // Browser implicitly wait
    public void Edges_implicitWait(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
        System.out.println("Edges: Set Implicit Wait to " + seconds + " seconds.");
    }

    // Explicit wait for element presence
    public void Edges_explicitWait(By locator, int timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
        System.out.println("Edges: Explicit wait for presence of " + locator);
    }

    // Fluent wait for element visibility with customizable timeout and polling
    // interval
    public void Edges_fluentWait(By locator, int timeoutSeconds, int pollingMillis, String timeoutMessage) {
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofMillis(pollingMillis))
                .withMessage(timeoutMessage)
                .ignoring(NoSuchElementException.class);

        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        System.out.println("Edges: Fluent wait found element " + locator);
    }

    // Navigate to URL
    public void Edges_navigateToURL(String url) {
        driver.get(url);
        System.out.println("Edges: Navigated to URL: " + url);
    }

    // Get page title
    public String Edges_getPageTitle() {
        String title = driver.getTitle();
        System.out.println("Edges: Page title is '" + title + "'");
        return title;
    }

    // Get current URL
    public String Edges_getCurrentURL() {
        String currentURL = driver.getCurrentUrl();
        System.out.println("Edges: Current URL is '" + currentURL + "'");
        return currentURL;
    }

    // Click element using explicit wait
    public void Edges_click(By locator) {
        explicitWait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        System.out.println("Edges: Clicked element " + locator);
    }
    
    // Getter to expose WebDriver instance
public WebDriver getDriver() {
    return driver;
}


    // Right click (context click) on element
    public void Edges_rightClick(By locator) {
        WebElement element = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
        System.out.println("Edges: Right-clicked on element " + locator);
    }

    // Send keys to element
    public void Edges_sendKeys(By locator, String text) {
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
        System.out.println("Edges: Sent keys to element " + locator);
    }

    // Get text from element
    public String Edges_getText(By locator) {
        String text = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
        System.out.println("Edges: Got text from element " + locator + ": " + text);
        return text;
    }

    // Dropdown handling by visible text
    public void Edges_selectDropdownByVisibleText(By locator, String visibleText) {
        WebElement dropdown = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
        System.out.println("Edges: Selected dropdown value by visible text: " + visibleText);
    }

    // Dropdown handling by value
    public void Edges_selectDropdownByValue(By locator, String value) {
        WebElement dropdown = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(dropdown);
        select.selectByValue(value);
        System.out.println("Edges: Selected dropdown value by value: " + value);
    }

    // Dropdown handling by index
    public void Edges_selectDropdownByIndex(By locator, int index) {
        WebElement dropdown = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(dropdown);
        select.selectByIndex(index);
        System.out.println("Edges: Selected dropdown by index: " + index);
    }

    // Drag and drop element
    public void Edges_dragAndDrop(By sourceLocator, By targetLocator) {
        WebElement source = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(sourceLocator));
        WebElement target = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(targetLocator));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();
        System.out.println("Edges: Dragged element " + sourceLocator + " and dropped on " + targetLocator);
    }

    // Checkbox handling: check checkbox
    public void Edges_checkCheckbox(By locator) {
        WebElement checkbox = explicitWait.until(ExpectedConditions.elementToBeClickable(locator));
        if (!checkbox.isSelected()) {
            checkbox.click();
            System.out.println("Edges: Checked the checkbox " + locator);
        } else {
            System.out.println("Edges: Checkbox already checked " + locator);
        }
    }

    // Checkbox handling: uncheck checkbox
    public void Edges_uncheckCheckbox(By locator) {
        WebElement checkbox = explicitWait.until(ExpectedConditions.elementToBeClickable(locator));
        if (checkbox.isSelected()) {
            checkbox.click();
            System.out.println("Edges: Unchecked the checkbox " + locator);
        } else {
            System.out.println("Edges: Checkbox already unchecked " + locator);
        }
    }

    // Radio button handling: select radio button
    public void Edges_selectRadioButton(By locator) {
        WebElement radioButton = explicitWait.until(ExpectedConditions.elementToBeClickable(locator));
        if (!radioButton.isSelected()) {
            radioButton.click();
            System.out.println("Edges: Selected radio button " + locator);
        } else {
            System.out.println("Edges: Radio button already selected " + locator);
        }
    }

    // Window handle: switch to window by title
    public void Edges_switchToWindowByTitle(String windowTitle) {
        String currentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            driver.switchTo().window(window);
            if (driver.getTitle().equals(windowTitle)) {
                System.out.println("Edges: Switched to window with title: " + windowTitle);
                return;
            }
        }

        driver.switchTo().window(currentWindow);
        System.out.println("Edges: Window with title '" + windowTitle + "' not found. Stayed in original window.");
    }

    // Window handle: switch to window by handle
    public void Edges_switchToWindowByHandle(String windowHandle) {
        Set<String> allWindows = driver.getWindowHandles();
        if (allWindows.contains(windowHandle)) {
            driver.switchTo().window(windowHandle);
            System.out.println("Edges: Switched to window handle: " + windowHandle);
        } else {
            System.out.println("Edges: Window handle " + windowHandle + " does not exist. No switch performed.");
        }
    }

    // Close current window
    public void Edges_closeCurrentWindow() {
        driver.close();
        System.out.println("Edges: Closed current window.");
    }

    // Navigate back
    public void Edges_navigateBack() {
        driver.navigate().back();
        System.out.println("Edges: Navigated back.");
    }

    // Navigate forward
    public void Edges_navigateForward() {
        driver.navigate().forward();
        System.out.println("Edges: Navigated forward.");
    }

    // Refresh the page
    public void Edges_refreshPage() {
        driver.navigate().refresh();
        System.out.println("Edges: Page refreshed.");
    }

    // Scroll to element using JavaScript
    public void Edges_scrollToElement(By locator) {
        WebElement element = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Actions actions = new Actions(driver);
        actions.scrollToElement(element).perform();
        System.out.println("Edges: Scrolled to element " + locator + " using Actions.scrollToElement().");
    }

    // Handle alert: accept alert
    public void Edges_acceptAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        System.out.println("Edges: Alert accepted.");
    }

    // Handle alert: dismiss alert
    public void Edges_dismissAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();
        System.out.println("Edges: Alert dismissed.");
    }

    // Handle alert: get alert text
    public String Edges_getAlertText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        System.out.println("Edges: Alert text: " + text);
        return text;
    }

    public void Edges_sendTextToAlert(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(text);
        alert.accept();
        System.out.println("Edges: Sent text to alert and accepted it: " + text);
    }

    // Close the browser
    public void Edges_closeBrowser() {
        if (driver != null) {
            driver.quit();
            System.out.println("Edges: Browser Closed.");
        }
    }
}
