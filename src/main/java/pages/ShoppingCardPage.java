package pages;

import blocks.CheckOutSummaryBlock;
import elements.ShoppingCartElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCardPage extends BasePage {

    private final By contentWrapper = By.xpath("//ul[contains(@class, 'cart-items')]");
    private final By productCard = By.xpath(".//li[contains(@class, 'cart-item')]");
    private final By proceedToCheckoutButtonLocator = By.xpath("//a[contains(@class, 'btn') and contains(text(), 'Proceed to checkout')]");

    private final CheckOutSummaryBlock checkOutSummaryBlock = new CheckOutSummaryBlock(getDriver());

    public CheckOutSummaryBlock getCheckoutSummeryBlock() {
        return checkOutSummaryBlock;
    }

    public List<ShoppingCartElement> getAllShoppingProductCards() {
        List<ShoppingCartElement> productCards = new ArrayList<>();
        List<WebElement> cards = waitForVisibilityOfNestedElements(contentWrapper, productCard, 10);
        for (WebElement card : cards) {
            productCards.add(new ShoppingCartElement(card));
        }
        scrollIntoView(cards.get(0));
        return productCards;
    }

    public double getSumOfCardsTotal() {
        return this.getAllShoppingProductCards()
                .stream()
                .map(ShoppingCartElement::getTotal)
                .reduce(0.0, Double::sum);
    }

    public double getPageTotalValue() {
        return checkOutSummaryBlock.getTotalValue();
    }

    public PersonalInformationCheckOutPage clickOnProceedToCheckoutButton() {
        findScrollAndClick(proceedToCheckoutButtonLocator);
        return new PersonalInformationCheckOutPage();
    }
}
