package pages;

import elements.OrderItemElement;
import helper.CustomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class OrderConfirmationPage extends BasePage {
    private final By orderConfirmedTextLocator = By.xpath("//*[contains(@class, 'card-title')]");
    private final By contentWrapper = By.id("order-items");
    private final By productCard = By.xpath(".//div[contains(@class, 'order-line row')]");
    private final By subTotalLocator = By.xpath("//div[@id='order-items']/div[2]/table/tbody/tr[1]/td[2]");
    private final By shippingLocator = By.xpath("//div[@id='order-items']/div[2]/table/tbody/tr[2]/td[2]");
    private final By totalLocator = By.xpath("//div[@id='order-items']/div[2]/table/tbody/tr[3]/td[2]");

    public String getOrderConfirmedText() {
        return waitUntilVisible(orderConfirmedTextLocator, 10).getText();
    }

    public List<OrderItemElement> getAllOrderedProductCards() {
        List<OrderItemElement> productCards = new ArrayList<>();
        List<WebElement> cards = waitForVisibilityOfNestedElements(contentWrapper, productCard, 10);
        for (WebElement card : cards) {
            productCards.add(new OrderItemElement(card));
        }
        scrollIntoView(cards.get(0));
        return productCards;
    }

    public double getCardsTotalSum() {
        return this.getAllOrderedProductCards()
                .stream()
                .map(OrderItemElement::getTotalProductsAmount)
                .reduce(0.0, Double::sum);
    }

    public double getSubTotalAmount() {
        return CustomUtils.getPriceValueFromString(findElement(subTotalLocator).getText());
    }
    public double getShippingAmount() {
        return CustomUtils.getPriceValueFromString(findElement(shippingLocator).getText());
    }
    public double getTotalAmount() {
        return CustomUtils.getPriceValueFromString(findElement(totalLocator).getText());
    }
}
