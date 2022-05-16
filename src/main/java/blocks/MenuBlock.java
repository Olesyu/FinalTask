package blocks;

import helper.CustomUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import pages.BasePage;
import pages.ProductsPage;

public class MenuBlock {

    private static WebDriver driver;

    private static Actions actionProvider;

    public MenuBlock(WebDriver webDriver) {
        driver = webDriver;
        actionProvider = new Actions(driver);
    }

    private final By clothesMenuButton = By.xpath("//li[@id='category-3']");
    private final By accessoriesMenuButton = By.xpath("//li[@id='category-6']");
    private final By artMenuButton = By.xpath("//li[@id='category-7']");
    private final By menCategory = By.xpath("//li[@id='category-4']");
    private final By womenCategory = By.xpath("//li[@id='category-5']");
    private final By stationeryCategory = By.xpath("//li[@id='category-7']");
    private final By homeAccessoriesCategory = By.xpath("//li[@id='category-8']");
    private final By menuItemSubMenu = By.xpath("./div[contains(@class, 'popover sub-menu')]");
    private final By searchLocator = By.xpath("//div[@id='search_widget']/form/input[@name='s']");

    public MenuBlock hoverClothesMenu() {
        actionProvider.moveToElement(driver.findElement(clothesMenuButton)).build().perform();
        return this;
    }

    public MenuBlock hoverAccessoriesMenu() {
        actionProvider.moveToElement(driver.findElement(accessoriesMenuButton)).build().perform();
        return this;
    }

    public MenuBlock hoverArtMenu() {
        actionProvider.moveToElement(driver.findElement(artMenuButton)).build().perform();
        return this;
    }

    public boolean checkMenSubItemAppears() {
        return driver.findElement(menCategory).isDisplayed();
    }

    public boolean checkWomenSubItemAppears() {
        return driver.findElement(womenCategory).isDisplayed();
    }

    public boolean checkStationerySubItemAppears() {
        return driver.findElement(stationeryCategory).isDisplayed();
    }

    public boolean checkHomeAccessoriesSubItemAppears() {
        return driver.findElement(homeAccessoriesCategory).isDisplayed();
    }

    public boolean checkIfNoArtSubCategories() {
        try {
            driver.findElement(artMenuButton).findElement(menuItemSubMenu);
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    public ProductsPage makeASearch(String textToFind) {
        WebElement searchInput = driver.findElement(searchLocator);
        CustomUtils.scrollInToView(searchInput, driver);
        searchInput.click();
        searchInput.sendKeys(textToFind + Keys.ENTER);
        return new ProductsPage();
    }
}
