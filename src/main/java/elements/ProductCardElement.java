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
public class ProductCardElement {

    private String productTitle;
    private String regularPriceString;
    private String priceString;
    private String discountString;
    private double regularPrice;
    private double price;
    private double discount;

    // Locators begin
    //---------------------------------------------
    @Getter(AccessLevel.NONE)
    @ToString.Exclude
    private final By productTitleLocator = By.xpath(".//*[contains(@class, 'product-title')]/a");

    @Getter(AccessLevel.NONE)
    @ToString.Exclude
    private final By regularPriceLocator = By.xpath(".//span[contains(@class, 'regular-price')]");

    @Getter(AccessLevel.NONE)
    @ToString.Exclude
    private final By priceLocator = By.xpath(".//span[@class='price']");

    @Getter(AccessLevel.NONE)
    @ToString.Exclude
    private final By discountLocator = By.xpath(".//li[contains(@class, 'product-flag discount')]");
    //---------------------------------------------
    // Locators end


    /**
     * Constructor
     *
     * @param cardElement Product card webElement
     */
    public ProductCardElement(WebElement cardElement) {

        this.productTitle = CustomUtils.tryToGetElementText(cardElement, productTitleLocator);
        this.regularPriceString = CustomUtils.tryToGetElementText(cardElement, regularPriceLocator);
        this.regularPrice = CustomUtils.getPriceValueFromString(this.regularPriceString);
        this.priceString = CustomUtils.tryToGetElementText(cardElement, priceLocator);
        this.price = CustomUtils.getPriceValueFromString(this.priceString);
        this.discountString = CustomUtils.tryToGetElementText(cardElement, discountLocator);
        this.discount = CustomUtils.getPriceValueFromString(this.discountString);
    }

    public boolean checkIfDiscountedPriceCalculatesCorrectly() {
        double calculatedDiscountPrice = CustomUtils.getDiscountedPriceOnPercentage(this.regularPrice, this.discount);
        return CustomUtils.checkDoubleEquals(calculatedDiscountPrice, this.price);
    }
}
