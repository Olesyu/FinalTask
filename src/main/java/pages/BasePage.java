package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<JavascriptExecutor> JS_EXECUTOR_THREAD_LOCAL = new ThreadLocal<>();

    public static void setDriverThreadLocal(WebDriver webDriver) {
        DRIVER_THREAD_LOCAL.set(webDriver);
        JS_EXECUTOR_THREAD_LOCAL.set((JavascriptExecutor) webDriver);
    }

    public static ThreadLocal<WebDriver> getDriverThreadLocal() {
        return DRIVER_THREAD_LOCAL;
    }

    public static ThreadLocal<JavascriptExecutor> getJSExecutorThreadLocal() {
        return JS_EXECUTOR_THREAD_LOCAL;
    }

    public static final int secondsToWaitElement = 10;


    /**
     * Get selenium WebDriver
     * @return selenium WebDriver
     */
    public static WebDriver getDriver() {
        return DRIVER_THREAD_LOCAL.get();
    }

    public static JavascriptExecutor getJsExecutor() {
        return JS_EXECUTOR_THREAD_LOCAL.get();
    }

    public WebElement findElement(By locator) {
        return getDriver().findElement(locator);
    }

    public List<WebElement> findElements(By locator) {
        return getDriver().findElements(locator);
    }

    public WebElement waitUntilVisible(By locator, int seconds) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public List<WebElement> waitForVisibilityOfNestedElements(By parentLocator, By childLocator, int seconds) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(parentLocator, childLocator));
    }

    public void waitForIframeAndSwitchToIt(By locator, int secondsToWait) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(secondsToWait))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }

    public String getComputedStylePropertyTextTransform(WebElement element) {
        return getJsExecutor().executeScript("return getComputedStyle(arguments[0]).textTransform", element).toString();
    }

    public String getComputedStylePropertyOutlineColor(WebElement element) {
        return getJsExecutor().executeScript("return getComputedStyle(arguments[0]).outlineColor", element).toString();
    }

    public void waitUntilHasClass(WebElement element, String classToLook) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(secondsToWaitElement))
                .until(ExpectedConditions.attributeContains(element, "class", classToLook));
    }

    public void scrollIntoView(WebElement element) {
        getJsExecutor().executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void findScrollAndClick(By locator) {
        WebElement element = waitForElementToBePresent(locator, secondsToWaitElement);
        scrollIntoView(element);
        element.click();
    }
    public WebElement waitAndScroll(By locator) {
        WebElement element = waitForElementToBePresent(locator, secondsToWaitElement);
        scrollIntoView(element);
       return element;
    }

    public WebElement waitForElementToBePresent(By locator, int seconds) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(seconds))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitUntilPageLoadedCompletely(int seconds) {
        System.out.print( getJsExecutor().executeScript("return document.readyState"));
        new WebDriverWait(getDriver(), Duration.ofSeconds(seconds))
                .until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }
}
