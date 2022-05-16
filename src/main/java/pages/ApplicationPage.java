package pages;

import blocks.FooterBlock;
import blocks.MenuBlock;
import elements.ProductCardElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ApplicationPage extends BasePage {

    private final By contentWrapper = By.id("wrapper");
    private final By languageDropdownButton = By.xpath("//button[contains(@aria-label, 'Language dropdown')]");
    private final By listOfLanguages = By.xpath("//div[contains(@class, 'language-selector dropdown')]/ul/li");
    private final String lang = "//div[contains(@class, 'language-selector dropdown')]/ul/li/a[@data-iso-code='%s']";
    private final By signInButton = By.xpath("//div[@id='_desktop_user_info']/div/a");
    private final By nameOfLoggedInUser = By.xpath("//div[@id='_desktop_user_info']/div/a[@class='account']/span");
    private final By productCard = By.xpath(".//div[contains(@class, 'thumbnail-container reviews-loaded')]");
    private final By popularProductsSection = By.xpath("//section[@id='content']/section[contains(@class, 'featured-products')]");
    private final By allProductsLink = By.xpath("//section[@id='content']/section/a");

    private final FooterBlock footerBlock = new FooterBlock();

    public FooterBlock getFooterBlock() {
        return footerBlock;
    }

    private final MenuBlock menuBlock = new MenuBlock(getDriver());

    public MenuBlock getMenuBlock() {
        return menuBlock;
    }

    public ApplicationPage waitUntilApplicationLoaded(int seconds) {
        waitUntilVisible(contentWrapper, seconds);
        return this;
    }

    public ApplicationPage clickOnLanguageDropDownButton() {
        findScrollAndClick(languageDropdownButton);
        return this;
    }

    public int getNumberOfLanguages() {
        return findElements(listOfLanguages).size();
    }

    public boolean checkIfLanguageIsPresent(String language) {
        String locator = String.format(lang, language.trim());
        try {
            scrollIntoView(findElement(By.xpath(locator)));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public LogInPage clickOnSignInButton() {
        findScrollAndClick(signInButton);
        return new LogInPage();
    }

    public String getNameOfLoggedInUser() {
        try {
            return waitUntilVisible(nameOfLoggedInUser, 10).getText();
        } catch (TimeoutException e) {
            return "";
        }
    }

    public List<ProductCardElement> getAllPopularProductCards() {
        List<ProductCardElement> productCards = new ArrayList<>();
        List<WebElement> cards = waitForVisibilityOfNestedElements(popularProductsSection, productCard, 10);
        waitUntilHasClass(cards.get(0), "reviews-loaded");
        for (WebElement card : cards) {
            productCards.add(new ProductCardElement(card));
        }
        scrollIntoView(cards.get(0));
        return productCards;
    }

    public ProductsPage clickOnAllProductsLink() {
        findScrollAndClick(allProductsLink);
        return new ProductsPage();
    }

    public ProductsPage searchForProduct(String textToSearch) {
        menuBlock.makeASearch(textToSearch);
        return new ProductsPage();
    }

}
