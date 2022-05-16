package elements;

import helper.CustomUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
@AllArgsConstructor
@ToString
public class OrderItemElement {
    private String productTitle;
    private String unitPriceString;
    private String quantityString;
    private String totalProductsString;
    private double unitPrice;
    private double quantity;
    private double totalProductsAmount;
    private double calculatedTotalAmount;


    // Locators begin
    //---------------------------------------------
    @Getter(AccessLevel.NONE)
    @ToString.Exclude
    private final By productTitleLocator = By.xpath(".//div[contains(@class, 'details')]/span");

    @Getter(AccessLevel.NONE)
    @ToString.Exclude
    private final By unitPriceLocator = By.xpath(".//div[contains(@class, 'qty')]/div/div[contains(@class, 'text-xs-left')]");

    @Getter(AccessLevel.NONE)
    @ToString.Exclude
    private final By quantityLocator = By.xpath(".//div[contains(@class, 'qty')]/div/div[2]");

    @Getter(AccessLevel.NONE)
    @ToString.Exclude
    private final By totalProductsLocator = By.xpath(".//div[contains(@class, 'qty')]/div/div[contains(@class, 'text-xs-right')]");
    //---------------------------------------------
    // Locators end

    /**
     * Constructor
     *
     * @param cardElement Product card webElement
     */
    public OrderItemElement(WebElement cardElement) {

        this.productTitle = CustomUtils.tryToGetElementText(cardElement, productTitleLocator);
        this.unitPriceString = CustomUtils.tryToGetElementText(cardElement, unitPriceLocator);
        this.unitPrice = CustomUtils.getPriceValueFromString(this.unitPriceString);
        this.quantityString = CustomUtils.tryToGetElementText(cardElement, quantityLocator);
        this.quantity = CustomUtils.getPriceValueFromString(this.quantityString);
        this.totalProductsString = CustomUtils.tryToGetElementText(cardElement, totalProductsLocator);
        this.totalProductsAmount = CustomUtils.getPriceValueFromString(this.totalProductsString);
        this.calculatedTotalAmount = this.calculateTotalAmount();
    }

    private double calculateTotalAmount() {
        return CustomUtils.trimDouble(this.unitPrice * this.quantity);
    }
}
