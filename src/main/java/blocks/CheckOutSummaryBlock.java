package blocks;

import helper.CustomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckOutSummaryBlock {

    private static WebDriver driver;

    private final By subTotalLocator = By.xpath("//div[@id='cart-subtotal-products']/span[contains(@class, 'value')]");
    private final By shippingLocator = By.xpath("//div[@id='cart-subtotal-shipping']/span[contains(@class, 'value')]");
    private final By totalValueLocator = By.xpath("//div[contains(@class, 'cart-total')]/span[@class='value']");

    public CheckOutSummaryBlock(WebDriver webDriver) {
        driver = webDriver;
    }

    private String getStringValue(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(locator)).getText();
    }

    public double getSubtotalValue() {
        return CustomUtils.getPriceValueFromString(this.getStringValue(subTotalLocator));
    }

    public double getShippingValue() {
        return CustomUtils.getPriceValueFromString(this.getStringValue(shippingLocator));
    }

    public double getTotalValue() {
        return CustomUtils.getPriceValueFromString(this.getStringValue(totalValueLocator));
    }

    public double getSumOfSubtotalAndShipping() {
        return CustomUtils.trimDouble(this.getSubtotalValue() + this.getShippingValue());
    }
}
