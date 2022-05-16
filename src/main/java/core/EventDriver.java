package core;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.events.WebDriverListener;

import java.util.Collection;
import java.util.List;

@Slf4j
public class EventDriver implements WebDriverListener {
    @Override
    public void beforeGet(WebDriver driver, String url) {
        log.info("Try to open page url: {}", url);
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        log.info("Page with url {} was opened", url);
    }

    @Override
    public void beforeClick(WebElement element) {
        log.info("Clicking on element: {}", element);
    }

    @Override
    public void afterClick(WebElement element) {
        log.info("Clicking on element {} successful", element);
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        log.info("Typing {} into field {}", keysToSend, element);
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        log.info("Text {} was saved in field {}", keysToSend, element);
    }

    @Override
    public void beforeExecuteScript(WebDriver driver, String script, Object[] args) {
        log.info("Executing JS script {} with params {}", script, args);
    }

    @Override
    public void afterExecuteScript(WebDriver driver, String script, Object[] args, Object result) {
        log.info("Executing JS script {} with params {} COMPLETE!", script, args);
    }

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        log.info("Try to find element by locator {}", locator);
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        log.info("Found element {} by locator {}", result, locator);
    }

    @Override
    public void beforeFindElement(WebElement element, By locator) {
        log.info("Try to find nested element by locator {} in element {}", locator, element);
    }

    @Override
    public void afterFindElement(WebElement element, By locator, WebElement result) {
        log.info("Found nested element {} in element {} by locator {}", result, element, locator);
    }

    @Override
    public void beforeFindElements(WebDriver driver, By locator) {
        log.info("Try to get elements by locator {}", locator);
    }

    @Override
    public void afterFindElements(WebDriver driver, By locator, List<WebElement> result) {
        log.info("Found elements {} with locator {}", result, locator);
    }

    @Override
    public void beforeFindElements(WebElement element, By locator) {
        log.info("Try to find all nested elements with locator {} in element {}", locator, element);
    }

    @Override
    public void afterFindElements(WebElement element, By locator, List<WebElement> result) {
        log.info("Found nested elements {} in element {} by locator {}", result, element, locator);
    }

    @Override
    public void beforePerform(WebDriver driver, Collection<Sequence> actions) {
        log.info("Perform 'Action' actions {}", actions);
    }

    @Override
    public void afterPerform(WebDriver driver, Collection<Sequence> actions) {
        log.info("Performed {} actions", actions);
    }

    @Override
    public void beforeGetText(WebElement element) {
        log.info("Getting text from {}", element);
    }

    @Override
    public void afterGetText(WebElement element, String result) {
        log.info("Text from {} received: {}", element, result);
    }

    @Override
    public void beforeQuit(WebDriver driver) {
        log.info("Quiting driver, closing browser");
    }

    @Override
    public void afterQuit(WebDriver driver) {
        log.info("Browser closed!");
    }

    @Override
    public void beforeGetCssValue(WebElement element, String propertyName) {
        log.info("Getting CSS value {} from element {}", propertyName, element);
    }

    @Override
    public void afterGetCssValue(WebElement element, String propertyName, String result) {
        log.info("Received CSS property {} by property name {} from {}", result, propertyName, element);
    }

    @Override
    public void beforeSetSize(WebDriver.Window window, Dimension size) {
        log.info("Setting window size {}", size);
    }

    @Override
    public void afterSetSize(WebDriver.Window window, Dimension size) {
        log.info("Window size {} applied", size);
    }
}
