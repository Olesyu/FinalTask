package blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SortBlock {
    private static WebDriver driver;


    public SortBlock(WebDriver webDriver) {
        driver = webDriver;
    }

    private final By sortDropDown = By.xpath("//button[contains(@aria-label, 'Sort by selection')]");
    private final By sortByNameAsc = By.xpath("//a[contains(@href, 'order=product.name.asc')]");
    private final By sortByNameDesc = By.xpath("//a[contains(@href, 'order=product.name.desc')]");
    private final By sortByPriceAsc = By.xpath("//a[contains(@href, 'order=product.price.asc')]");
    private final By sortByPriceDesc = By.xpath("//a[contains(@href, 'order=product.price.desc')]");

    public void clickOnSortDropdown() {
        WebElement dropDown = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(sortDropDown));
        dropDown.click();
    }

    public void setSortByNameAsc() {
        clickOnSortDropdown();
        driver.findElement(sortByNameAsc).click();
    }

    public void setSortByNameDesc() {
        clickOnSortDropdown();
        driver.findElement(sortByNameDesc).click();
    }

    public void setSortByPriceAsc() {
        clickOnSortDropdown();
        driver.findElement(sortByPriceAsc).click();
    }

    public void setSortByPriceDesc() {
        clickOnSortDropdown();
        driver.findElement(sortByPriceDesc).click();
    }
}
