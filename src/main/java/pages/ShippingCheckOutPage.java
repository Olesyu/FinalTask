package pages;

import org.openqa.selenium.By;

public class ShippingCheckOutPage extends BasePage {

    private final By myCarrierLocator = By.id("delivery_option_2");
    private final By continueButton = By.name("confirmDeliveryOption");

    public ShippingCheckOutPage selectMyCarrierDeliveryMethod() {
        findScrollAndClick(myCarrierLocator);
        return this;
    }

    public PaymentCheckOutPage pressShippingContinueButton() {
        findScrollAndClick(continueButton);
        return new PaymentCheckOutPage();
    }
}
