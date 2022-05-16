package pages;

import helper.CustomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class ModalPopUpPurchasePage extends BasePage {
    private final By contentWrapper = By.id("blockcart-modal");
    private final By popUpTitleLocator = By.id("myModalLabel");
    private final By paperTypeLocator = By.xpath("//*[contains(@class, 'paper type')]/strong");
    private final By quantityLocator = By.xpath("//*[contains(@class, 'product-quantity')]/strong");
    private final By productPriceLocator = By.xpath("//p[@class= 'product-price']");
    private final By totalLocator = By.xpath("//p[@class= 'product-total']/span[@class='value']");
    private final By continueShoppingButtonLocator = By.xpath("//button[@data-dismiss='modal' and contains(text(), 'Continue shopping')]");
    private final By proceedToCheckOutButtonLocator = By.xpath("//a[contains(@class, 'btn-primary') and contains(text(), 'Proceed to checkout')]");


    public ModalPopUpPurchasePage waitUntilPageLoaded(int seconds) {
        waitUntilVisible(contentWrapper, seconds);
        return this;
    }

    public String getPopUpTitle() {
        return findElement(popUpTitleLocator).getText().trim();
    }

    public String getPaperType() {
        return findElement(paperTypeLocator).getText().trim();
    }

    public int getQuantity() {
        return Integer.parseInt(findElement(quantityLocator).getText());
    }

    public double getProductPrice() {
        return CustomUtils.getPriceValueFromString(findElement(productPriceLocator).getText());
    }

    public double getTotal() {
        return CustomUtils.getPriceValueFromString(findElement(totalLocator).getText());
    }

    public boolean checkIfTotalCorrect() {
        return getProductPrice() * getQuantity() == getTotal();
    }

    public ProductPage clickOContinueShoppingButton() {
        waitUntilVisible(continueShoppingButtonLocator, secondsToWaitElement).click();
        return new ProductPage();
    }

    public ShoppingCardPage clickOnProceedToCheckOutButton() {
        waitUntilVisible(proceedToCheckOutButtonLocator, secondsToWaitElement).click();
        return new ShoppingCardPage();
    }
}
