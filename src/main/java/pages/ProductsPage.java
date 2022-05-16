package pages;

import elements.ProductCardElement;
import blocks.SortBlock;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {

    private final By loadingOverlay = By.xpath("//div[contains(@class, 'faceted-overlay')]");
    private final By contentWrapper = By.id("products");
    private final By productCard = By.xpath(".//div[contains(@class, 'thumbnail-container reviews-loaded')]");
    private final String productTitleLocator = ("//*[contains(@class, 'product-title')]/a[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz' ), translate('%s', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'))]");

    private final SortBlock sortBlock = new SortBlock(getDriver());

    public SortBlock getSortBlock() {
        return sortBlock;
    }

    public ProductsPage waitUntilOverlayHidden() {
        try {
            WebElement overlay =  findElement(loadingOverlay);
            new WebDriverWait(getDriver(), Duration.ofSeconds(20)).until(ExpectedConditions.invisibilityOf(overlay));
        } catch (NoSuchElementException e) {
            return this;
        }
    return this;
    }

    public List<ProductCardElement> getAllProductCards() {
        List<ProductCardElement> productCards = new ArrayList<>();
        List<WebElement> cards = waitForVisibilityOfNestedElements(contentWrapper, productCard, 10);
        waitUntilHasClass(cards.get(0), "reviews-loaded");
        for (WebElement card : cards) {
            productCards.add(new ProductCardElement(card));
        }
        scrollIntoView(cards.get(0));
        return productCards;
    }

    public ProductsPage sortByNameAscend() {
        sortBlock.setSortByNameAsc();
        return this;
    }
    public ProductsPage sortByNameDescend() {
        sortBlock.setSortByNameDesc();
        return this;
    }
    public ProductsPage sortByPriceAscend() {
        sortBlock.setSortByPriceAsc();
        return this;
    }
    public ProductsPage sortByPriceDescend() {
        sortBlock.setSortByPriceDesc();
        return this;
    }

    public ProductPage clickOnCertainProductName(String productName) {
        waitForVisibilityOfNestedElements(contentWrapper, productCard, 10);
        By locator = By.xpath(String.format(productTitleLocator, productName));
        findScrollAndClick(locator);
        return new ProductPage();
    }

}
